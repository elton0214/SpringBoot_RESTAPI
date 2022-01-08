//PatientController.java

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



  // PatientController(PatientRepository repository) {
  //   this.repository = repository;
  // }

//  @RequestMapping(value="/patients", method=RequestMethod.POST)
//  public Patient createPatient(@RequestBody Patient patient) {
//      return patientService.createPatient(patient);
//  }
//
//  @RequestMapping(value="/patients", method=RequestMethod.GET)
//  public List<Patient> readPatients() {
//      return patientService.getPatients();
//  }

//    @PostMapping("/patients")
//    public Patient createPatient(Patient pat) {
//        return patientService.setPatient(pat);
//    }

//    // show all employees via GET
//    @GetMapping("/list")
//    public ResponseEntity<List<Patient>> getPatients() {
//        return ResponseEntity.ok().body(patientService.getPatients());
//    }

   // GET all employees
   @GetMapping("/list")
   List<Patient> all() {
     return iPatientService.getPatients();
   }

  // POST a patient
  @PostMapping("/register")
    public Patient createPatient(@RequestBody Patient patient) {
      return iPatientService.savePatient(patient);
  }

    // GET a patient
    @GetMapping("/view/{id}")
    public @ResponseBody
    Optional<Patient> getPatient(@PathVariable("id") String id) throws Exception {
        return iPatientService.findById(id);
    }
//    Patient getPatient(String patient_id) {
//        return IPatientService.findById(id);
//    };


    // DELETE a patient
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void deletePatient(@PathVariable("id") String id) throws Exception {
        iPatientService.deletePatient(id);
    }

//    @GetMapping("/view/{id}")
//    Patient one(@PathVariable String id) {
//
//        return patientService.getPatient(id);
//        // .orElseThrow(() -> new EmployeeNotFoundException(id));
//    }

   /////////


    //https://www.tejatechview.com/2021/11/spring-boot-api-cantabilefp-hands-on.html
//    @GetMapping("hospitals/{id}")
//    public @ResponseBody Hospital getHospital(@PathVariable("id") int id) throws Exception {
//        return hospitalService.getHospital(id);
//    }
//
//    @GetMapping("hospitals/")
//    public @ResponseBody List<Hospital> getAllHospitals() throws Exception {
//        return hospitalService.getAllHospitals();
//    }



}

