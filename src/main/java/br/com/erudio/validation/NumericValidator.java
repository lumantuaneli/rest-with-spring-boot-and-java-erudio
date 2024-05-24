package br.com.erudio.validation;

import br.com.erudio.exceptions.MathValidationException;

public class NumericValidator {

    public static final String REGEX_DOUBLE_VALUE = "[-+]?\\d*([\\.,]\\d+)?";

    private NumericValidator() {
        throw new UnsupportedOperationException();
    }

    public static boolean isNumeric(String strNumber) {
        return isNumeric(strNumber, true);
    }

    public static boolean isNumeric(String pNumber, boolean pLenient) {
        if (pNumber == null) {
            return booleanValidation(false, pLenient, new MathValidationException("The number is null"));
        }

        return booleanValidation(pNumber.matches(REGEX_DOUBLE_VALUE), pLenient, new MathValidationException("The value is not numeric"));
    }

    public static boolean validateNumbers(String... pNumbers) {
        return validateNumbers(false, pNumbers);
    }

    public static boolean validateNumbers(boolean pLenient, String... pNumbers) {
        boolean result = true;
        for (String vNumber : pNumbers) {
            result = result && isNumeric(vNumber, pLenient);
        }
        return result;
    }

    private static boolean booleanValidation(Boolean pValue, boolean pLenient, RuntimeException pException) {
        if (pLenient || Boolean.TRUE.equals(pValue)) {
            return pValue;
        }

        throw pException;
    }

}
