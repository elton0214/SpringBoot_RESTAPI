package idv.Healthcare.controller;

import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.Model.Appointment;
import idv.Healthcare.Model.Patient;
import idv.Healthcare.service.IApplicationUserService;
import idv.Healthcare.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApplicationUserController {
    private final IApplicationUserService iAppUsrService;

//    // GET a patient
//    @GetMapping("/viewprofile/{id}")
//    public @ResponseBody
//    Optional<ApplicationUser> getApplicationUser(@PathVariable("id") String id) throws Exception {
//        return iAppUsrService.findByUsername(id);
//    }

    // POST(create) a user
    @PostMapping("/register")
    public ApplicationUser createApplicationUser(@RequestBody ApplicationUser applicationUser) {
        return iAppUsrService.saveApplicationUser(applicationUser);
    }

    // GET users
    @GetMapping("/list")
    List<ApplicationUser> all() {
        return iAppUsrService.getApplicationUsers();
    }





    // GET a user
//    @GetMapping("/view/{id}")
//    public @ResponseBody
//    Optional<ApplicationUser> getApplicationUser(@PathVariable("id") String id) throws Exception {
//        return iAppUsrService.findByUsername(id);
//    }

}

