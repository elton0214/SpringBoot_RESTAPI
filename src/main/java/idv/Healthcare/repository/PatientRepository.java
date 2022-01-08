package idv.Healthcare.repository;

import idv.Healthcare.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,String>{
    Patient findByPatientid(String patient_id);
}
