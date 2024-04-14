package DoctorAppointment.database;

import DoctorAppointment.exceptions.DoctorAlreadyPresentException;
import DoctorAppointment.exceptions.DoctorNotPresentException;
import DoctorAppointment.exceptions.NoSpecializationPresentException;
import DoctorAppointment.exceptions.SlotException;
import DoctorAppointment.model.AvailableDoctor;
import DoctorAppointment.model.Doctor;
import DoctorAppointment.model.Specialization;
import DoctorAppointment.model.TimeSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorsRepository {
    Map<Integer, Doctor> doctors = new HashMap<>();
    Map<Specialization, List<Doctor>> doctorsForSpecialization = new HashMap<>();

    public void registerDoctor(Doctor doctor){

        // "Doctor" already present
        if(doctors.containsKey(doctor.getDoctorId())){
            throw new DoctorAlreadyPresentException();
        }
        doctors.put(doctor.getDoctorId(), doctor);

        // if "Specialization" for doctor is not present yet
        if(!doctorsForSpecialization.containsKey(doctor.getSpecialization())){
            doctorsForSpecialization.put(doctor.getSpecialization(), new ArrayList<>());
        }

        // add "Doctor" for that "Specialization"
        doctorsForSpecialization.get(doctor.getSpecialization()).add(doctor);
    }

    public boolean isDoctorRegistered(Integer doctorId){

        return doctors.containsKey(doctorId);
    }

    public void addAvailability(Integer doctorId, TimeSlot timeSlot){

        // "Doctor" is not present
        if(!doctors.containsKey(doctorId)){
            throw new DoctorNotPresentException();
        }
        Doctor doctor = doctors.get(doctorId);
        HashMap<TimeSlot, Boolean> slots = doctor.getSlots();
        slots.put(timeSlot, true);
        // bcs you have updated the doctor data
        doctors.put(doctorId, doctor);
    }

    public List<Doctor> getDoctorsBySpeciality(Specialization specialization){

        // "Specialization" is not present
        if(!doctorsForSpecialization.containsKey(specialization)){
            throw new NoSpecializationPresentException();
        }
        return doctorsForSpecialization.get(specialization);
    }

    public List<AvailableDoctor> getAvailableTimeSlotsForAllDoctorsForSpecialization(List<Doctor> specializedDoctors) throws CloneNotSupportedException {

        List<AvailableDoctor> doctorsWithAvailableSlots = new ArrayList<>();

        for(Doctor doctor: specializedDoctors){
            AvailableDoctor availableDoctor = new AvailableDoctor();
            availableDoctor.setDoctor(doctor.clone());
            List<TimeSlot> availableSlots = new ArrayList<>();
            HashMap<TimeSlot, Boolean> slots = doctor.getSlots();
            for(Map.Entry<TimeSlot, Boolean> entry: slots.entrySet()){

                // if "True" then add in the availableSlots
                if(entry.getValue()){
                    availableSlots.add(entry.getKey());
                }
            }
            availableDoctor.setSlotList(availableSlots);
            doctorsWithAvailableSlots.add(availableDoctor);
        }
        return doctorsWithAvailableSlots;
    }

    public Doctor getDoctorDetails(Integer doctorId){

        return doctors.get(doctorId);
    }

    // When Booking is cancelled OR TimeSlot is completed OR TimeSlot is free
    // then we need to free up the slot
    public void freeSlot(Integer doctorId, TimeSlot timeSlot){

        Boolean put = doctors.get(doctorId).getSlots().put(timeSlot, true);

        // when "timeSlot" is not present i.e., "HashMap" will return "null"
        // if KEY is not present else will override the previous value
        if(put == null){
            doctors.get(doctorId).getSlots().remove(timeSlot);
            throw new SlotException("Slot Not found");
        }
    }

}
