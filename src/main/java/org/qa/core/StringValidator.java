
// Утилита валидации строк
package org.qa.core;

public class StringValidator {

    public static boolean isValidEmail(String email) {

        if (email == null || email.length() < 5) {
            return false;
        }

        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');


        return atIndex > 0 && dotIndex > atIndex + 1;
    }

    public static String normalize(String text) {
        if (text == null) return null;


        return text.trim()
                .toLowerCase()
                .replaceAll("\\s+", " ");
    }
}