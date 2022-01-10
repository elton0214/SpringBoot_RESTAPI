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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Override //using username
//    public ApplicationUser saveApplicationUser(ApplicationUser appuser) {
//        log.info("Saving new user {} to the db", appuser.getUsername());
//        appuser.setPassword(passwordEncoder.encode(appuser.getPassword()));
//        return appuserRepo.save(appuser);
//    }
    @Override //using useremail
    public ApplicationUser saveApplicationUser(ApplicationUser appuser) {
        log.info("Saving new user {} to the db", appuser.getUseremail());
        appuser.setPassword(passwordEncoder.encode(appuser.getPassword()));
        return appuserRepo.save(appuser);
    }

    @Override
    public Optional<ApplicationUser> findByUsername(String username) {
        return appuserRepo.findByUsername(username);
    }

    @Override
    public Optional<ApplicationUser> findByUseremail(String useremail) {
        return appuserRepo.findByUseremail(useremail);
    }

    @Override
    public List<ApplicationUser> getApplicationUsers() {
        return appuserRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        Optional<ApplicationUser> applicationUser = appuserRepo.findByUseremail(useremail);
//        Optional<ApplicationUser> applicationUser = appuserRepo.findByUseremail(useremail);
        if(applicationUser.isPresent()) {
            log.info("User email found in db: {}", useremail);
        } else {
            log.error("User email not found in db");
            throw new UsernameNotFoundException("User not found in db");
        }

//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        applicationUser.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        })
//        return new ApplicationUser(applicationUser.get().getUsername(), applicationUser.get().getPassword());

        return new org.springframework.security.core.userdetails.User(applicationUser.get().getUseremail(), applicationUser.get().getPassword(), new ArrayList<>());
    }

    public String signUpUser(ApplicationUser applicationUser) {
        boolean userExists = appuserRepo
                .findByUseremail(applicationUser.getUseremail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email aleady token");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(applicationUser.getPassword());

        applicationUser.setPassword(encodedPassword);
        appuserRepo.save(applicationUser);

        return "it works";
    }

//    private final EmailValidator emailValidator;
//    @Override
    public String register(ApplicationUser applicationUser) {
//        boolean isValidEmail = emailValidator.
//                test(applicationUser.getUseremail());
//        if (!isValidEmail) {
//            throw new IllegalStateException("email not valid")
//        }
        return  this.signUpUser(new ApplicationUser(
                applicationUser.getUsername(),
                applicationUser.getPassword(),
                applicationUser.getUseremail(),
                applicationUser.getLocation(),
                applicationUser.getUser_mobile()
                )

        );
    }



//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<ApplicationUser> applicationUser = appuserRepo.findByUsername(username);
//        if(applicationUser.isPresent()) {
//            log.info("User found in db: {}", username);
//        } else {
//            log.error("User not found in db");
//            throw new UsernameNotFoundException("User not found in db");
//        }
//
////        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
////        applicationUser.getRoles().forEach(role -> {
////            authorities.add(new SimpleGrantedAuthority(role.getName()));
////        })
////        return new ApplicationUser(applicationUser.get().getUsername(), applicationUser.get().getPassword());
//
//        return new org.springframework.security.core.userdetails.User(applicationUser.get().getUsername(), applicationUser.get().getPassword(), new ArrayList<>());
//    }

}

