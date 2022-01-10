package idv.Healthcare.service;

import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.Model.Appointment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
//@Transactional(readOnly = true)
public interface IApplicationUserService {

    // CREATE
    ApplicationUser saveApplicationUser(ApplicationUser appuser);
    String register(ApplicationUser applicationUser);

    // READ
//    ApplicationUser getApplicationUser(String user_name);
    Optional<ApplicationUser> findByUsername(String username);
    List<ApplicationUser>getApplicationUsers();

    Optional<ApplicationUser> findByUseremail(String useremail);

    // UPDATE



}
