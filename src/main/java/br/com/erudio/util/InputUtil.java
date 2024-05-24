package br.com.erudio.util;

import br.com.erudio.exceptions.InputException;
import br.com.erudio.validation.NumericValidator;

public class InputUtil {

    public static final String REGEX_MEAN_VALUES_SEPARATOR = "_";
    public static final String REGEX_UNDERLINE_SEPARATED_DOUBLE_VALUES = String.format("%s(?:\\s*%s\\s*%s)*", 
        NumericValidator.REGEX_DOUBLE_VALUE, REGEX_MEAN_VALUES_SEPARATOR, NumericValidator.REGEX_DOUBLE_VALUE);

    private InputUtil() {
        throw new UnsupportedOperationException();
    }

    public static double[] splitUnderlineSeparatedValues(String pValues) {
        if (pValues == null) {
            throw new InputException("No value to perform a mean");
        }

        if (!pValues.matches(REGEX_UNDERLINE_SEPARATED_DOUBLE_VALUES)) {
            throw new InputException("Invalid semicolon separated value list");
        }

        String[] vSplitedValues = pValues.split(REGEX_MEAN_VALUES_SEPARATOR);
        double[] vValues = new double[vSplitedValues.length];
        for (int i = 0; i < vSplitedValues.length; i++) {
            vValues[i] = MathUtils.convertToDouble(vSplitedValues[i]);
        }
        return vValues;
    }

}
