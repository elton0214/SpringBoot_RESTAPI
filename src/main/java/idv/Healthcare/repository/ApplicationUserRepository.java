package idv.Healthcare.repository;

import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long>{
    Optional<ApplicationUser> findByUsername1(String username);
    Optional<ApplicationUser> findByUseremail(String useremail);
    Optional<ApplicationUser> findById(Long id);
}