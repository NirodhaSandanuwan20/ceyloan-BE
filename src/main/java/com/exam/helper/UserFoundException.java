package com.exam.helper;

public class UserFoundException  extends  Exception{

    public UserFoundException() {
        super("Your email is already registered ! Please use another !!");
    }

    public UserFoundException(String msg)
    {
        super(msg);
    }
}
