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
    public String savePatient(Patient pat) {
        try {
            patRepo.save(pat);
            return "{message=\"Registration successful\"}";
        } catch(Exception ex) {
            // not working now
            return "{message=\"Registration failure\"}";
        }

    }

    @Override
    public Optional<Patient> findById(String patientid) {
        return patRepo.findById(patientid);
    }

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

}
