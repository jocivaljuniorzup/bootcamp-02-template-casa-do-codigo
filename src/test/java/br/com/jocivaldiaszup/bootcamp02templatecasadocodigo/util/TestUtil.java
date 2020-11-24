package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.util;

import java.util.Random;

public class TestUtil {
    public static String generateString(int size, String letters) {
        Random random = new Random();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < size; i++) {
            out.append(letters.charAt(random.nextInt(letters.length())));
        }
        return out.toString();
    }
}
