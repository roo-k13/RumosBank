package com.rumos.rumosbank.domain.shared;

import java.util.Random;

public class Generators {
    public String generateRandomNumber(int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomInt = r.nextInt(10);
            sb.append(randomInt);
        }

        return sb.toString();
    }
}