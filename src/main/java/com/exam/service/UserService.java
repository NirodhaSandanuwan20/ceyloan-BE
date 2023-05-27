package com.exam.service;

import com.exam.helper.UserFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    //creating user
    public boolean createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public User getUser(String username);
    public Optional<User> getUserById(Long userID);

    //delete user by id
    public void deleteUser(Long userId);

    public User verifyAccount(String email, String otp) throws Exception;
    public User resendMail(String email);
    public User changeEmailRequest(String oldEmail,String newEmail);

    public User verifyNewMail(String otp, String newEmail, String oldEmail) throws Exception;

    User forgotPassword(String otp ,String newPassword,String mail) throws Exception;
}
