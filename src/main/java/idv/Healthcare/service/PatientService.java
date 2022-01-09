package idv.Healthcare.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

//import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.Model.Appointment;
import idv.Healthcare.Model.Patient;
//import idv.Healthcare.repository.ApplicationUserRepository;
import idv.Healthcare.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class PatientService implements IPatientService {

    private final PatientRepository patRepo;

    @Override
    public Patient savePatient(Patient pat) {
        return patRepo.save(pat);
    }



    @Override
    public Optional<Patient> findById(String patientid) {
        return patRepo.findById(patientid);
    }
//    @Override
//    public Patient getPatient(String patient_id) {
//        log.info("Fetching a Patient");
//        return patRepo.findByPatient_id(patient_id);
//    }

    @Override
    public List<Patient> getPatients(){
        log.info("Fetching all Patients");
        return patRepo.findAll();
    }

    // DELETE
    @Override
    public void deletePatient(String patientid) {
        patRepo.deleteById(patientid);
    }

//  // READ
//  public Optional<Patient> getPatient(String patId) {
//      return patRepo.findById(patId);
//  }


//

//
//  // UPDATE
//  public Patient updatePatient(String patId, Patient patientDetails) {
//          Patient pat = patRepository.findById(patId).get();
//          pat.setFirstName(patientDetails.getFirstName());
//          pat.setLastName(patientDetails.getLastName());
//          pat.setEmailId(patientDetails.getEmailId());
//
//          return patRepository.save(pat);
//  }

}
