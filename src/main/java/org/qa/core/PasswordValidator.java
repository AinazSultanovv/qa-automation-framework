
// Валидатор паролей с проверкой сложности
package org.qa.core;

public class PasswordValidator {
    public static void isValid(String password) {
        if (password.length() < 8) {
            return false;
        }
        if (password.contains(" ")) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasDigit = false;


        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;

            }

            return hasDigit && hasUpper;
        }
    }
}


