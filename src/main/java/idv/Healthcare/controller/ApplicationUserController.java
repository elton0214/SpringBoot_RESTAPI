package idv.Healthcare.controller;

import idv.Healthcare.Exception.ApiRequestException;
import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.service.IApplicationUserService;
import idv.Healthcare.Model.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ApplicationUserController {
    private final IApplicationUserService iAppUsrService;

    // POST(create) a user
    @PostMapping("/register")
    public String saveUser(@RequestBody ApplicationUser applicationUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
        return iAppUsrService.saveApplicationUser(applicationUser);
    }

    @PostMapping("/editprofile")
    public String modifyUser(@RequestBody ApplicationUser applicationUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
        return iAppUsrService.editApplicationUser(applicationUser);
    }

    @PostMapping("/saverole")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/saverole").toUriString());
        return ResponseEntity.created(uri).body(iAppUsrService.saveRole(role));
    }

    @PostMapping("/addroletouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        iAppUsrService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }

    // GET users
    @GetMapping("/list")
    public ResponseEntity<List<ApplicationUser>> getUsers() {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/list").toUriString());
        return ResponseEntity.created(uri).body(iAppUsrService.getUsers());
    }

    //GET a user
    @GetMapping("/viewprofile/{id}")
    public ResponseEntity<ApplicationUser> getApplicationUser(@PathVariable("id") Long id) throws Exception {

//        Optional<ApplicationUser> appuser = iAppUsrService.getUser(id);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.setAccessControlAllowCredentials(true);
        responseHeaders.setAccessControlAllowOrigin("*");

//        if (x.isEmpty()) {
//            throw new ApiRequestException("123");
//        }
        return new ResponseEntity<ApplicationUser>(iAppUsrService.getUser(id).get(), responseHeaders, HttpStatus.CREATED);

//        return iAppUsrService.getUser(id);
    }

    //DELETE a user
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void deleteApplicationUser(@PathVariable("id") Long id) throws Exception {
        iAppUsrService.deleteApplicationUser(id);
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String rolename;
}
