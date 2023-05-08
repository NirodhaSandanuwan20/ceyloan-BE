package com.exam.service.impl;

import com.exam.helper.Generator;
import com.exam.helper.ServerErorrException;
import com.exam.helper.UserFoundException;
import com.exam.helper.UserNotFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.EmailService;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
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
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        /*Optional<User> local = this.userRepository.findByEmail(user.getEmail());

        String verifyCode = generator.createVerifyCode();
        if (emailService.createEmail(user.getEmail(), "Regarding Login Verification",
                "<h1>Your Verification Code :" + verifyCode + "</h1>")) {
            //user create
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            user.setIsEnabled(false);
            user.setOtp(verifyCode);
            return this.userRepository.save(user);

        } else {
            throw new ServerErorrException();
        }*/

        String emailBody = new String("");

        Optional<User> local = this.userRepository.findByEmail(user.getEmail());
        if(!local.isEmpty()){
            throw new UserFoundException();
        }

        String verifyCode = generator.createVerifyCode();
        if (emailService.createEmail(user.getEmail(), "Regarding Login Verification",
                "<h1>Your Verification Code :" + verifyCode + "</h1>")) {
            //user create
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            user.setEnabled(false);
            user.setOtp(verifyCode);
            return this.userRepository.save(user);
        } else {
            throw new ServerErorrException();
        }


    }

    //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User verifyAccount(String email, String otp) throws Exception {
        Optional<User> selectedUser = userRepository.findByEmail(email);
        if (selectedUser.isEmpty()) throw new UserFoundException();
        System.out.println(otp);
        if (selectedUser.get().getOtp().equals(otp)) {
            //verify
            selectedUser.get().setEnabled(true);
            User Activated = userRepository.save(selectedUser.get());
            return Activated;
        } else {
            throw new Exception();
        }

    }

    @Override
    public User resendMail(String email) {
        Optional<User> selectedUser = this.userRepository.findByEmail(email);

        String verifyCode = generator.createVerifyCode();
        emailService.createEmail(email, "Verify User",
                "<h1>Your Verification Code :" + verifyCode + "</h1>");
        selectedUser.get().setOtp(verifyCode);
        return this.userRepository.save(selectedUser.get());
    }

    @Override
    public User forgotPassword(String otp,String newPassword,String mail) throws Exception {
        Optional<User> selectedUser = this.userRepository.findByEmail(mail);
        if (selectedUser.isEmpty()) throw new UserFoundException();
        if (selectedUser.get().getOtp().equals(otp)) {
            //change password
            selectedUser.get().setPassword(this.bCryptPasswordEncoder.encode(newPassword));
            this.userRepository.save(selectedUser.get());
            emailService.createEmail(mail, "Verify User",
                    "<h1>Your password has been changed successful.</h1>" );
            return selectedUser.get();
        } else {
            throw new Exception();
        }

    }
}


