package utils;

import java.util.regex.Pattern;

public final class Validators {
    // private static final Pattern CODE_PATTERN = Pattern.compile("^CPT-\\d{5}$");

    private Validators() {}

    public static void requirePositiveAmount(double amount, String fieldName) throws ValidationException {
        if (amount <= 0) {
            throw new ValidationException(fieldName + " doit etre strictement positif");
        }
    }

    public static void requireValidCode(String code) throws ValidationException {
        if (code == null || !code.matches("^CPT-\\d{5}$")) {
            throw new ValidationException("Code compte invalide. Format attendu: CPT-12345");
        }
    }


   
}


