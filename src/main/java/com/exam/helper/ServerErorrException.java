package com.exam.helper;

import java.rmi.ServerException;

public class ServerErorrException extends ServerException {
    public ServerErorrException() {
        super("Internal Server Error");
    }

    public ServerErorrException(String s, Exception ex) {
        super(s, ex);
    }
}
