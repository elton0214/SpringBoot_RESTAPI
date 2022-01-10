package idv.Healthcare.service;

import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.Model.Appointment;

import java.util.List;
import java.util.Optional;

public interface IApplicationUserService {

    // CREATE
    ApplicationUser saveApplicationUser(ApplicationUser appuser);

    // READ
//    ApplicationUser getApplicationUser(String user_name);
    Optional<ApplicationUser> findByUsername(String username);
    List<ApplicationUser>getApplicationUsers();

    Optional<ApplicationUser> findByUseremail(String useremail);

    // UPDATE



}
