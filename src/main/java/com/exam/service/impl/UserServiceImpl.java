package com.exam.service.impl;

import com.exam.helper.Generator;
import com.exam.helper.UserFoundException;
import com.exam.model.JwtResponse;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.EmailService;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private EmailService emailService;
    private Generator generator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, EmailService emailService, Generator generator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.generator = generator;
    }


    //creating user
    @Override
    public boolean createUser(User user, Set<UserRole> userRoles) {
        Optional<User> local = this.userRepository.findByEmail(user.getEmail());

        if (local.isPresent() && !local.get().isEnabled()) {
            //email registered but not verified
            String verifyCode = generator.createVerifyCode();
            emailService.signupEmail(user.getEmail(), verifyCode);
            local.get().setOtp(verifyCode);
            this.userRepository.save(local.get());
            return true;
        } else if (local.isPresent() && local.get().isEnabled()) {
            //already verified email
            return false;
        }

//new user for email registration
        for (UserRole ur : userRoles) {
            roleRepository.save(ur.getRole());
        }

        user.getUserRoles().addAll(userRoles);
        this.userRepository.save(user);


        String verifyCode = generator.createVerifyCode();
        emailService.signupEmail(user.getEmail(), verifyCode);


        user.getUserRoles().addAll(userRoles);
        user.setEnabled(false);
        user.setOtp(verifyCode);

        this.userRepository.save(user);
        return true;
    }

    //getting user by username
    @Override
    public Optional<User> getUser(String username) {
        return this.userRepository.findByEmail(username);
    }

    @Override
    public Optional<User> getUserById(Long userID) {
        return this.userRepository.findById(userID);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public ResponseEntity<?> verifyAccount(String email, String otp) throws Exception {
        Optional<User> selectedUser = userRepository.findByEmail(email);
        if (selectedUser.isEmpty()) throw new UserFoundException();
        if (selectedUser.get().getOtp().equals(otp)) {
            User Activated = userRepository.save(selectedUser.get());
            return ResponseEntity.ok(Activated);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred: ");
        }

    }

    @Override
    public User resendMail(String email) {
        Optional<User> selectedUser = this.userRepository.findByEmail(email);
        String verifyCode = generator.createVerifyCode();
        emailService.signupEmail(email, verifyCode);
        selectedUser.get().setOtp(verifyCode);
        return this.userRepository.save(selectedUser.get());
    }

    @Override
    public User changeEmailRequest(String oldEmail, String newEmail) {
        Optional<User> selectedUser = this.userRepository.findByEmail(oldEmail);

        String verifyCode = generator.createVerifyCode();
        emailService.ChangeEmail(newEmail, verifyCode);
        selectedUser.get().setOtp(verifyCode);
        return this.userRepository.save(selectedUser.get());
    }

    @Override
    public User verifyNewMail(String otp, String newEmail, String oldEmail) throws Exception {
        Optional<User> selectedUser = userRepository.findByEmail(oldEmail);
        if (selectedUser.isEmpty()) throw new UserFoundException();
        System.out.println(otp);
        if (selectedUser.get().getOtp().equals(otp)) {
            //verify
            selectedUser.get().setEmail(newEmail);
            User Activated = userRepository.save(selectedUser.get());
            return Activated;
        } else {
            throw new Exception();
        }
    }

    @Override
    public User forgotPassword(String otp, String newPassword, String mail) throws Exception {

        Optional<User> selectedUser = this.userRepository.findByEmail(mail);
        if (selectedUser.get().getOtp().equals(otp)) {
            if (!selectedUser.get().isEnabled()) {
                selectedUser.get().setEnabled(true);
            }
            //change password
            selectedUser.get().setPassword(this.bCryptPasswordEncoder.encode(newPassword));
            this.userRepository.save(selectedUser.get());
            /*emailService.createEmail(mail, "Verify User",
                    emailBc);*/
            return selectedUser.get();
        } else {
            throw new Exception();
        }

    }
}


