package idv.Healthcare.repository;

import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String>{
    Optional<ApplicationUser> findByUsername(String username);
    Optional<ApplicationUser> findByUseremail(String useremail);
}