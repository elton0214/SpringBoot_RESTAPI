package idv.Healthcare.controller;

import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.Model.Patient;
import idv.Healthcare.service.IApplicationUserService;
import idv.Healthcare.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class ApplicationUserController {
    private final IApplicationUserService iAppUsrService;

    // GET users
    @GetMapping("/list")
    List<ApplicationUser> all() {
        return iAppUsrService.getApplicationUsers();
    }

    // POST a user
    @PostMapping("/register")
    public ApplicationUser createPatient(@RequestBody ApplicationUser applicationUser) {

        return iAppUsrService.saveApplicationUser(applicationUser);
    }

    // GET a user
//    @GetMapping("/view/{id}")
//    public @ResponseBody
//    Optional<ApplicationUser> getApplicationUser(@PathVariable("id") String id) throws Exception {
//        return iAppUsrService.findByUsername(id);
//    }

}

