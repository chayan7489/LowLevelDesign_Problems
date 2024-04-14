package DoctorAppointment.database;

import DoctorAppointment.exceptions.PatientNotPresentException;
import DoctorAppointment.model.Patient;

import java.util.HashMap;
import java.util.Map;

public class PatientsRepository {
    Map<Integer, Patient> patients = new HashMap<>();

    public void registerPatient(Patient patient){

        // "Patient" already present
        if(patients.containsKey(patient.getPatientId())){
            // patient already present exception
            throw new PatientNotPresentException();
        }
        patients.put(patient.getPatientId(), patient);
    }

    public boolean isPatientRegistered(Integer patientId){

        return patients.containsKey(patientId);
    }

}
