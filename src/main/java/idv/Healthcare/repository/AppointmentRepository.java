package idv.Healthcare.repository;

import idv.Healthcare.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,String>{
    Optional<Appointment> findByBookingid(String booking_id);
    List<Optional<Appointment>> findByPatientid(String patient_id);
}
