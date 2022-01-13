package idv.Healthcare.service;

import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.repository.ApplicationUserRepository;
import idv.Healthcare.repository.RoleRepository;
import idv.Healthcare.Model.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import javax.transaction.Transactional;
import java.util.Optional;
//import idv.Healthcare.security.JwtUtil;

@Service
@RequiredArgsConstructor //create a constructor and make all arguments are passes into this constructor = DI
@Transactional //make everything in this class are transactional
@Slf4j //for log
public class ApplicationUserService implements IApplicationUserService, UserDetailsService {

    private final ApplicationUserRepository appuserRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    private ApplicationUserRepository applicationUserRepository;
//    public void UserSecurityService(ApplicationUserRepository applicationUserRepository) {
//        this.applicationUserRepository=applicationUserRepository;
//    }

//    @Override //using username
//    public ApplicationUser saveApplicationUser(ApplicationUser appuser) {
//        log.info("Saving new user {} to the db", appuser.getUsername());
//        appuser.setPassword(passwordEncoder.encode(appuser.getPassword()));
//        return appuserRepo.save(appuser);
//    }
    @Override //using useremail (register)
    public String saveApplicationUser(ApplicationUser appuser) {
        log.info("Saving new user {} ({}) to the db", appuser.getUsername1(),appuser.getUseremail());
        appuser.setPassword(passwordEncoder.encode(appuser.getPassword()));
//        this.addRoleToUser(appuser.getUsername1(),"ROLE_USER"); // DEFAULT to ROLE_USER
        log.info("appuserRepo.toString()"+appuserRepo.toString());

        try {
            appuserRepo.save(appuser);
            return "{message=\"Registration successful\"}";
        } catch(Exception ex) {
            // not working now
            return "{message=\"Registration failure\"}";
        }

//        return appuserRepo.save(appuser);
    }

    @Override //using useremail
    public String editApplicationUser(ApplicationUser appuser) {
        log.info("Saving modification of user {} ({}) to the db", appuser.getUsername1(),appuser.getUseremail());
//        appuser.setPassword(passwordEncoder.encode(appuser.getPassword()));
//        this.addRoleToUser(appuser.getUsername1(),"ROLE_USER"); // DEFAULT to ROLE_USER
        try {
            appuserRepo.save(appuser);
            return "{message=\"Modification successful\"}";
        } catch(Exception ex) {
            // not working now
            return "{message=\"Password or username policy failed\"}";
        }
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the db", role.getRolename());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username1, String rolename) {
        log.info("Adding role {} to user {}", rolename, username1);
        if (username1.equals("sean")) {
            log.info("username1.equals(\"sean\") = true ");
        } else {
            log.info("username1.equals(\"sean\") = false "); log.info("username1 ="+username1);
        }
        Optional<ApplicationUser> appUser = appuserRepo.findByUsername1(username1);


        if(appUser.isPresent()) {
            log.info("User email found in db: {}", appUser.get().getUseremail());
            Role role = roleRepo.findByRolename(rolename);
            appUser.get().getRoles().add(role);
        } else {
            log.error("User email not found in db");
            throw new UsernameNotFoundException("User not found in db");
        }

    }

    @Override
    public Optional<ApplicationUser> getUser(Long id) {
        Optional<ApplicationUser> x = appuserRepo.findById(id);

        log.info("Fetching user id: {}", id);
//        if (!x.isPresent()) {
//            throw new UnauthorizedException("we");
//        }
        return appuserRepo.findById(id);
    }

    @Override
    public Optional<ApplicationUser> findByUseremail(String useremail) {
        log.info("findByUseremail {} ", useremail);
        return appuserRepo.findByUseremail(useremail);
    }

    @Override
    public void deleteApplicationUser(Long userid) {
        appuserRepo.deleteById(userid);
    }

    @Override
    public List<ApplicationUser> getUsers() {
        return appuserRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        Optional<ApplicationUser> applicationUser = appuserRepo.findByUseremail(useremail);
//        applicationUser.orElseThrow(()->new UsernameNotFoundException("User not found"));
        if(applicationUser.isPresent()) {
            log.info("User email found in db: {}", useremail);
        } else {
            log.error("User email not found in db");
//            throw new UnauthorizedException("w", HttpStatus.UNAUTHORIZED);
//            throw new UsernameNotFoundException("User not found in db");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        applicationUser.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        });

        return new org.springframework.security.core.userdetails.User(applicationUser.get().getUseremail(), applicationUser.get().getPassword(), authorities);
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

//    @Override
    public String register(ApplicationUser applicationUser) {
//        boolean isValidEmail = emailValidator.
//                test(applicationUser.getUseremail());
//        if (!isValidEmail) {
//            throw new IllegalStateException("email not valid")
//        }
        return  this.signUpUser(new ApplicationUser(
                applicationUser.getId(),
                applicationUser.getUsername1(),
                applicationUser.getPassword(),
                applicationUser.getUseremail(),
                applicationUser.getLocation(),
                applicationUser.getUser_mobile(),
                applicationUser.getRoles()
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

