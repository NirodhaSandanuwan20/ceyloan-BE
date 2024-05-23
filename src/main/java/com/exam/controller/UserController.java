package com.exam.controller;

import com.exam.helper.HashCodeGenerator;
import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private HashCodeGenerator hashCodeGenerator;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test")
    public String test(){
        return "WELL COME TO API OF CEYLON PAPERS HUB";
    }



    //method for register user email
    @PostMapping("/send")
    public boolean createUser(@RequestBody User user) throws Exception {


        user.setProfile("default.png");

        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);


        return this.userService.createUser(user, roles);

    }

    //method for Verify user email
    @PostMapping(value = "/verify/{otp}", params = "email")
    public ResponseEntity<?> verifyUser(@PathVariable String otp, @RequestParam String email) throws Exception {
        ResponseEntity<?> user = userService.verifyAccount(email, otp);
        return user;
    }


    //method for Verify user email and save user in db
    @PostMapping(value = "/forgot")
    public User forgotPassword(@RequestParam String otp,@RequestParam String newPassword, @RequestParam String mail) throws Exception {
        User user = userService.forgotPassword(otp,newPassword,mail);
        return user;
    }

    //method for resend otp while user signup
    @PostMapping(value = "/resend", params = "email")
    public User resendMail(@RequestParam String email) throws Exception {
        User user = userService.resendMail(email);
        return user;
    }

    @PostMapping(value = "/change-mail")
    public User changeEmailRequest(@RequestParam String oldEmail, @RequestParam String newEmail) throws Exception {
        User user = userService.changeEmailRequest(oldEmail,newEmail);
        return user;
    }

    @PostMapping(value = "/verify-newMail")
    public User verifyNewMail(@RequestParam String otp, @RequestParam String newEmail, @RequestParam String oldEmail) throws Exception {
        User user = userService.verifyNewMail(otp,newEmail,oldEmail);
        return user;
    }

    @GetMapping("/{username}")
    public Optional<User> getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }
    @GetMapping("/id/{userId}")
    public Optional<User> getUserByID(@PathVariable("userId") Long userID) {
        return this.userService.getUserById(userID);
    }

    //delete the user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }


    //update api


    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }





}
