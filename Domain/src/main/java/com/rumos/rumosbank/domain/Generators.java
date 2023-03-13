package com.rumos.rumosbank.domain;

import java.util.Random;
import java.util.random.RandomGenerator;

public class Generators {
    public String generateRandomNumber(int length) {
        RandomGenerator r = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomInt = r.nextInt(10);
            sb.append(randomInt);
        }

        return sb.toString();
    }
}