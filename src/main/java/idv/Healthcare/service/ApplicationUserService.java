package idv.Healthcare.service;

import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.Model.Appointment;
import idv.Healthcare.repository.ApplicationUserRepository;
import idv.Healthcare.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//import idv.Healthcare.security.JwtUtil;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class ApplicationUserService implements IApplicationUserService{

    private final ApplicationUserRepository appuserRepo;

    @Override
    public ApplicationUser saveApplicationUser(ApplicationUser appuser) {
        return appuserRepo.save(appuser);
    }

    @Override
    public Optional<ApplicationUser> findByUsername(String username) {
        return appuserRepo.findByUsername(username);
    }

    @Override
    public List<ApplicationUser> getApplicationUsers() {
        return appuserRepo.findAll();
    }
}

