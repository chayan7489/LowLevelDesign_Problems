package DoctorAppointment.service;

import DoctorAppointment.Mode.Print;
import DoctorAppointment.database.PatientsRepository;
import DoctorAppointment.model.*;
public class PatientService {
    PatientsRepository patientsRepository;
    Print print;

    public PatientService(Print print, PatientsRepository patientsRepository){
        this.print = print;
        this.patientsRepository = patientsRepository;
    }

    public void registerPatient(Patient patient){
        patientsRepository.registerPatient(patient);
        print.printData(patient.getPatientName()+ " registered successfully.");
    }
}
