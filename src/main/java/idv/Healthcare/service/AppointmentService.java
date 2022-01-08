package idv.Healthcare.service;

import java.util.List;
import java.util.Optional;

import idv.Healthcare.Model.Appointment;
import idv.Healthcare.Model.Patient;
import idv.Healthcare.repository.AppointmentRepository;
import idv.Healthcare.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class AppointmentService implements IAppointmentService{
    private final AppointmentRepository appmtRepo;
    private final PatientRepository patRepo;

    @Override
    public Appointment saveAppointment(Appointment appmt) {
        return appmtRepo.save(appmt);
    }

    @Override
    public Patient savePatient(Patient pat) {
        return patRepo.save(pat);
    }

    @Override
    public void addPatientToAppointment(String booking_id, String patient_id) {
//        Patient patient = patRepo.findByPatientid(patient_id);
        Optional<Appointment> appointment = appmtRepo.findByBookingid(booking_id);
//        appointment.get().setPatient(patient);
        appointment.get().setPatientid(patient_id);

        //user.getRoles().add(role);
    }

//    @Override
//    public Patient getPatient(Patient patient_id) {
//        return appmtRepo.findByPatientid(patient_id);
//    }

    @Override
    public Optional<Appointment> findByBookingid(String booking_id) {
        return appmtRepo.findByBookingid(booking_id);
    }

    @Override
    public List<Optional<Appointment>> findByPatientid(String pat_id) {
        return appmtRepo.findByPatientid(pat_id);
    }

    @Override
    public List<Appointment> getAppointments() {
        return appmtRepo.findAll();
    }

    @Override
    public void deleteAppointment(String booking_id) {
        appmtRepo.deleteById(booking_id);
    }
}
