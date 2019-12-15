package vn.gamatra.service.impl;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CommonFunction {
    private static final String CHARACTER_SET = "0123456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
    private static Random rnd = new Random();

    public String generateRandomString(int length){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++){
            builder.append(CHARACTER_SET.charAt(rnd.nextInt(CHARACTER_SET.length())));
        }
        return builder.toString();
    }
}
