package idv.Healthcare.service;

import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.Model.Appointment;
import idv.Healthcare.repository.ApplicationUserRepository;
import idv.Healthcare.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

//import idv.Healthcare.security.JwtUtil;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class ApplicationUserService implements IApplicationUserService, UserDetailsService {

    private final ApplicationUserRepository appuserRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApplicationUser saveApplicationUser(ApplicationUser appuser) {
        log.info("Saving new user {} to the db", appuser.getPassword());
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> applicationUser = appuserRepo.findByUsername(username);
        if(applicationUser.isPresent()) {
            log.info("User found in db: {}", username);
        } else {
            log.error("User not found in db");
            throw new UsernameNotFoundException("User not found in db");
        }

//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        applicationUser.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        })
//        return new ApplicationUser(applicationUser.get().getUsername(), applicationUser.get().getPassword());

        return new org.springframework.security.core.userdetails.User(applicationUser.get().getUsername(), applicationUser.get().getPassword(), new ArrayList<>());
    }
}

