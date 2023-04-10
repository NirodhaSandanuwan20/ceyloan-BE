package com.exam.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Generator {
    private static String NUMERIC = "0123456789";

    public String createVerifyCode() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(NUMERIC.charAt(new Random().nextInt(NUMERIC.length() - 1)));
        }
        return builder.toString();
    }
}
