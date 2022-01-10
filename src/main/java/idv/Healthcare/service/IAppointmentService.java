package idv.Healthcare.service;

import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.Model.Appointment;
import idv.Healthcare.Model.Patient;

import java.util.List;
import java.util.Optional;

public interface IAppointmentService {

    //CREATE
    Appointment saveAppointment(Appointment appmt);
    Patient savePatient(Patient pat);

    void addPatientToAppointment(String booking_id, String patient_id);
//    Patient getPatient(Patient pat);

    // READ
    // 1.list of all appointments
    List<Appointment> getAppointments();

    // 2.details of a specified appointment id
    Optional<Appointment> findByBookingid(String booking_id);

    // 3.list of all appointments of a specified patient id
    List<Optional<Appointment>> findByPatientid(String pat_id);


    // DELETE (delete a specified appointment Id)
    void deleteAppointment(String appmt);
}
