package br.com.erudio.util;

import br.com.erudio.validation.NumericValidator;

public class MathUtils {
    private MathUtils() {
        throw new UnsupportedOperationException();
    }

    public static Double convertToDouble(String pNumber) {
        return convertToDouble(pNumber, true);
    }
    
    public static double convertToDouble(String pNumber, boolean pLenient) {
    	if (NumericValidator.isNumeric(pNumber, pLenient)) {
    	    String number = pNumber.replaceAll(",", ".");
    		return Double.parseDouble(number);
    	}
    	return 0D;
    }
    
    
}
