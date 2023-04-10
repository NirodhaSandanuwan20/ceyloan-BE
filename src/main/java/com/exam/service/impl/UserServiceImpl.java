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
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private EmailService emailService;
    private Generator generator;

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

        User local = this.userRepository.findByEmail(user.getEmail());

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
            local = this.userRepository.save(user);
            return local;
        } else {
            throw new ServerErorrException();
        }

        /*   String emailBody = new String("");*/

        /*User local = this.userRepository.findByEmail(user.getEmail());
        if (local != null) {
            System.out.println("Your email is already registered ! Please use another !!");
            throw new UserFoundException();
        } else {
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
                local = this.userRepository.save(user);
                return local;
            } else {
                throw new ServerErorrException();
            }

        }*/


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


}
