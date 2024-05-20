package com.exam.service;

public interface EmailService {

    public boolean signupEmail(String email , String otp);
    public boolean ChangeEmail(String email , String otp);
    public boolean welcomeEmail(String email, String firstName);
}
