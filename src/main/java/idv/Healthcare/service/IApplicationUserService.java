package idv.Healthcare.service;

import idv.Healthcare.Model.ApplicationUser;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import idv.Healthcare.Model.Role;

@Repository
//@Transactional(readOnly = true)
public interface IApplicationUserService {

    // CREATE
    String saveApplicationUser(ApplicationUser appuser);
    Role saveRole(Role role);
    void addRoleToUser(String username1, String rolename);
    String register(ApplicationUser applicationUser);

    // READ
//    ApplicationUser getApplicationUser(String user_name);
    Optional<ApplicationUser> getUser(Long id);
    List<ApplicationUser> getUsers();

    Optional<ApplicationUser> findByUseremail(String useremail);

    // UPDATE
    String editApplicationUser(ApplicationUser applicationUser);

    // DELETE
    void deleteApplicationUser(Long userid);



}
