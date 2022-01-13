package idv.Healthcare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import idv.Healthcare.Model.Patient;
import idv.Healthcare.service.IPatientService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

  private final IPatientService iPatientService;

   // GET all employees
   @GetMapping("/list")
   List<Patient> all() {
     return iPatientService.getPatients();
   }

  // POST a patient
  @PostMapping("/register")
  public String createPatient(@RequestBody Patient patient) {
      return iPatientService.savePatient(patient);
  }

    // GET a patient
    @GetMapping("/view/{id}")
    public @ResponseBody
    Optional<Patient> getPatient(@PathVariable("id") String id) throws Exception {
        return iPatientService.findById(id);
    }

    // DELETE a patient
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void deletePatient(@PathVariable("id") String id) throws Exception {
        iPatientService.deletePatient(id);
    }

}

