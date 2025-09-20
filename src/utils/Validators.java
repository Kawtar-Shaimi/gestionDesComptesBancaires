package utils;

import java.util.regex.Pattern;

public final class Validators {
    // private static final Pattern CODE_PATTERN = Pattern.compile("^CPT-\\d{5}$");

    private Validators() {}


    public static void requireValidCode(String code) {
        if (code == null || !code.matches("^CPT-\\d{5}$")) {
            throw new ValidationException("Code compte invalide. Format attendu: CPT-12345");
        }
    }
}


