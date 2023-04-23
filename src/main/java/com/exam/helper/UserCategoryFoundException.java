package com.exam.helper;

public class UserCategoryFoundException extends  Exception{
    public UserCategoryFoundException() {
        super("This Category is already subscribed ! Please try another !!");
    }

    public UserCategoryFoundException(String msg)
    {
        super(msg);
    }
}
