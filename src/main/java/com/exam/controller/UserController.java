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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
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


    @PostMapping("/")
    public boolean createUser(@RequestBody User user) throws Exception {


        user.setProfile("default.png");
        /*user.setPassword(this.bCryptPasswordEncoder.encode("abcasc"));*/

        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);
        System.out.println(user.getEmail());


        return this.userService.createUser(user, roles);

    }


    @PostMapping(value = "/{otp}", params = "email")
    public User verifyUser(@PathVariable String otp, @RequestParam String email) throws Exception {
        User user = userService.verifyAccount(email, otp);
        return user;
    }

    @PostMapping(value = "/forgot")
    public User forgotPassword(@RequestParam String otp,@RequestParam String newPassword, @RequestParam String mail) throws Exception {
        User user = userService.forgotPassword(otp,newPassword,mail);
        return user;
    }

    @PostMapping(value = "/", params = "email")
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
    public User getUser(@PathVariable("username") String username) {
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
