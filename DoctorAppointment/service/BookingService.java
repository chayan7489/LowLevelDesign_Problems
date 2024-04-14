package DoctorAppointment.service;

import DoctorAppointment.Mode.Print;
import DoctorAppointment.database.DoctorsRepository;
import DoctorAppointment.database.PatientsRepository;
import DoctorAppointment.exceptions.BookingNotPresentException;
import DoctorAppointment.exceptions.DoctorNotPresentException;
import DoctorAppointment.exceptions.PatientAlreadyOccupiedException;
import DoctorAppointment.exceptions.PatientNotPresentException;
import DoctorAppointment.model.Booking;
import DoctorAppointment.model.Doctor;
import DoctorAppointment.model.Patient;
import DoctorAppointment.model.TimeSlot;
import java.util.*;

public class BookingService {
    DoctorsRepository doctorsRepository;
    PatientsRepository patientsRepository;
    Print print;
    HashMap<Integer, Booking> bookings = new HashMap<>();
    Map<Integer, List<TimeSlot>> patientSlots = new HashMap<>();
    Queue<Booking> waitListQueue = new LinkedList<>();

    static int index = 1;

    public BookingService(Print print, DoctorsRepository doctorsRepository, PatientsRepository patientsRepository){
        this.print = print;
        this.doctorsRepository = doctorsRepository;
        this.patientsRepository = patientsRepository;
    }


    // bookAppointment: (PatientA, Dr.Curious, 12:30)

    public void bookAppointment(Patient patient, Doctor doctor, String fromSlot){
        // Check Patient Exists
        if(!patientsRepository.isPatientRegistered(patient.getPatientId()))
            throw new PatientNotPresentException();
        if(!doctorsRepository.isDoctorRegistered(doctor.getDoctorId()))
            throw new DoctorNotPresentException();
        // Check if Patient already booked For that Slot
        if(patientSlots.containsKey(patient.getPatientId())){
            for(TimeSlot slot : patientSlots.get(patient.getPatientId())){
                if(slot.getStart().equals(fromSlot)){
                    throw new PatientAlreadyOccupiedException();
                }
            }
        }else{
            patientSlots.put(patient.getPatientId(), new ArrayList<>());
        }

        // Check if doctor Available for that Slot
        Doctor doctorDetails = doctorsRepository.getDoctorDetails(doctor.getDoctorId());
        HashMap<TimeSlot, Boolean> slots = doctorDetails.getSlots();
        for(Map.Entry<TimeSlot,Boolean> slot : slots.entrySet()){
            if(slot.getKey().getStart().equals(fromSlot) && slot.getValue()){
                // book "Doctor" for this slot
                slots.put(slot.getKey(), false);
                // add the "TimeSlot"
                patientSlots.get(patient.getPatientId()).add(slot.getKey());
                Booking booking = new Booking(index++, doctor, patient, slot.getKey());
                bookings.put(booking.getBookingId(), booking);
                print.printData("Appointment Booked Successfully Booking id "+ booking.getBookingId());
                return;
            }
        }
        print.printData("No available Slots");
        Booking booking = new Booking(index++, doctor, patient, new TimeSlot(fromSlot, fromSlot));
        booking.setWaitList(true);
        print.printData("Added to the waitlist. Booking id: "+booking.getBookingId());
        waitListQueue.add(booking);
    }

    public void cancelBooking(Integer bookingId){

        if(!bookings.containsKey(bookingId)){
            throw new BookingNotPresentException();
        }
        Booking booking = bookings.get(bookingId);
        doctorsRepository.freeSlot(booking.getDoctor().getDoctorId(), booking.getSlot());
        bookings.remove(bookingId);
        print.printData("Booking Cancelled");
        // remove TimeSlot from the patient list
        patientSlots.get(booking.getPatient().getPatientId()).remove(booking.getSlot());

        // we are calling "checkForFreeSlot" after cancelling booking because
        // there might be other booking which is waiting in the "Queue" for same Timeslot
        // so we will take that out from queue and schedule booking for it
        checkForFreeSlot(booking);
    }

    public void ShowBookedAppointments(){
        for(Map.Entry<Integer, Booking> bookingEntry: bookings.entrySet()){
            Booking booking = bookingEntry.getValue();
            print.printData(
                    booking.getBookingId()+" "+
                            booking.getDoctor().getDoctorName() + " "+
                            booking.getPatient().getPatientName()+ " "+
                            booking.getSlot().getStart()+" - "+ booking.getSlot().getEnd()
            );
        }
    }

    private void checkForFreeSlot(Booking booking){
        for(Booking waitListBooking: waitListQueue) {
            if(waitListBooking.getSlot().getStart().equals(booking.getSlot().getStart())){
                waitListBooking.getSlot().setEnd(booking.getSlot().getEnd());
                waitListBooking.setWaitList(false);
                Doctor doctorDetails = doctorsRepository.getDoctorDetails(waitListBooking.getDoctor().getDoctorId());
                HashMap<TimeSlot, Boolean> slots = doctorDetails.getSlots();

                // not checking "getValue()" for Doctor because there was already booking for it
                // and we are overriding it with different bookingId
                for(Map.Entry<TimeSlot, Boolean> slot: slots.entrySet()){
                    if(slot.getKey().getStart().equals(booking.getSlot().getStart())){
                        slots.put(slot.getKey(),false);
                        break;
                    }
                }
                // put "booking" that was waiting in "Queue"
                bookings.put(waitListBooking.getBookingId(), waitListBooking);
                waitListQueue.remove(waitListBooking);
                return;
            }
        }
    }

}