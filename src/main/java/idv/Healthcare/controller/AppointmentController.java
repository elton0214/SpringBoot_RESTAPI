package idv.Healthcare.controller;

import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.Model.Appointment;
import idv.Healthcare.Model.Patient;
import idv.Healthcare.service.IAppointmentService;
import idv.Healthcare.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final IAppointmentService iAppointmentService;


    // POST a appointment
    @PostMapping("/register")
    public String createAppointment(@RequestBody Appointment appointment) {

        return iAppointmentService.saveAppointment(appointment);
    }

    // GET an appointment
    @GetMapping("/view/{id}")
    public @ResponseBody
    Optional<Appointment> getAppointment(@PathVariable("id") String id) throws Exception {
        return iAppointmentService.findByBookingid(id);
    }

    // GET appointment list appointed by a patient
    //appointment/list/{patientid}
    @GetMapping("/list/{id}")
    public @ResponseBody
    List<Optional<Appointment>> getAppmtsFromPatient(@PathVariable("id") String id) throws Exception {
        return iAppointmentService.findByPatientid(id);
    }

    // GET appointments
    @GetMapping("/list")
    List<Appointment> all() {
        return iAppointmentService.getAppointments();
    }


    // DELETE a appointment
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void deleteAppointment(@PathVariable("id") String id) throws Exception {
        iAppointmentService.deleteAppointment(id);
    }
}
