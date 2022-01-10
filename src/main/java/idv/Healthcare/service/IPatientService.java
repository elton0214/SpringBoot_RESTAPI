package idv.Healthcare.service;


import idv.Healthcare.Model.Appointment;
import idv.Healthcare.Model.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {

    // CREATE
//    Patient savePatient(Patient pat);
    String savePatient(Patient pat);

    // READ
    // 1.list of all patients
    List<Patient>getPatients();

    // 2.details of a specified patient id
    Optional<Patient> findById(String pat_id);
//    Patient getPatient(String patient_id);

    // DELETE
    void deletePatient(String pat_id);


}
