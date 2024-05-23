package br.com.erudio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
	private static final String REGEX_MEAN_VALUES_SEPARATOR = "_";
	private static final String REGEX_DOUBLE_VALUE = "[-+]?\\d*([\\.,]\\d+)?";
	private static final String REGEX_UNDERLINE_SEPARATED_DOUBLE_VALUES = String.format("%s(?:\\s*%s\\s*%s)*", REGEX_DOUBLE_VALUE, REGEX_MEAN_VALUES_SEPARATOR, REGEX_DOUBLE_VALUE);
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping("/sum/{leftOperand}/{rightOperand}")
	public Double sum(@PathVariable(value = "leftOperand") String pLeftOperand, @PathVariable(value = "rightOperand") String pRightOperand) {
		validateNumbers(pLeftOperand, pRightOperand);
		
		return convertToDouble(pLeftOperand) + convertToDouble(pRightOperand);
	}
	
	@GetMapping("/subtraction/{leftOperand}/{rightOperand}")
	public Double subtraction(@PathVariable(value = "leftOperand") String pLeftOperand, @PathVariable(value = "rightOperand") String pRightOperand) {
		validateNumbers(pLeftOperand, pRightOperand);
		
		return convertToDouble(pLeftOperand) - convertToDouble(pRightOperand);
	}
	
	@GetMapping("/multiplication/{leftOperand}/{rightOperand}")
	public Double multiplication(@PathVariable(value = "leftOperand") String pLeftOperand, @PathVariable(value = "rightOperand") String pRightOperand) {
		validateNumbers(pLeftOperand, pRightOperand);
		
		return convertToDouble(pLeftOperand) * convertToDouble(pRightOperand);
	}
	
	@GetMapping("/division/{leftOperand}/{rightOperand}")
	public Double division(@PathVariable(value = "leftOperand") String pLeftOperand, @PathVariable(value = "rightOperand") String pRightOperand) {
		validateNumbers(pLeftOperand, pRightOperand);
		
		Double vRightOperand = convertToDouble(pRightOperand);
		if (vRightOperand.doubleValue() == 0D) {
			throw new UnsupportedMathOperationException("Cannot perform a division by zero");
		}
		return convertToDouble(pLeftOperand) / vRightOperand;
	}
	
	@GetMapping("/square-root/{operand}")
	public Double squareRoot(@PathVariable(value = "operand") String pOperand) {
		validateNumbers(pOperand);
		
		Double vOperand = convertToDouble(pOperand);
		if (vOperand.doubleValue() < 0D) {
			throw new UnsupportedMathOperationException("Cannot perform a squere root of a negative value");
		}
		return Math.sqrt(vOperand);
	}
	
	@GetMapping("/mean/{value1}/{value2}")
	public Double mean(@PathVariable(value = "value1") String pValue1, @PathVariable(value = "value2") String pValue2) {
		validateNumbers(pValue1, pValue2);
		
		return mean(convertToDouble(pValue1), convertToDouble(pValue2));
	}
	
	@GetMapping("/mean/{value1}/{value2}/{value3}")
	public Double mean(@PathVariable(value = "value1") String pValue1, @PathVariable(value = "value2") String pValue2, @PathVariable(value = "value3") String pValue3) {
		validateNumbers(pValue1, pValue2, pValue3);
		
		return mean(convertToDouble(pValue1), convertToDouble(pValue2), convertToDouble(pValue3));
	}
	
	@GetMapping("/mean/{value1}/{value2}/{value3}/{value4}")
	public Double mean(@PathVariable(value = "value1") String pValue1, @PathVariable(value = "value2") String pValue2, @PathVariable(value = "value3") String pValue3, @PathVariable(value = "value4") String pValue4) {
		validateNumbers(pValue1, pValue2, pValue3, pValue4);
		
		return mean(convertToDouble(pValue1), convertToDouble(pValue2), convertToDouble(pValue3), convertToDouble(pValue4));
	}
	
	@GetMapping("/mean/{semicolon-separated-values}")
	public Double mean(@PathVariable(value = "semicolon-separated-values") String pValues) {
		List<Double> vValues = splitUnderlineSeparatedValues(pValues);
		return mean(vValues.toArray(new Double[vValues.size()]));
	}
	
	private List<Double> splitUnderlineSeparatedValues(String pValues) {
		if (pValues == null) {
			throw new UnsupportedMathOperationException("No value to perform a mean");
		}
		
		if (!pValues.matches(REGEX_UNDERLINE_SEPARATED_DOUBLE_VALUES)) {
			throw new UnsupportedMathOperationException("Invalid semicolon separated value list");
		}
		
		List<Double> vValues = new ArrayList<>();
		for (String vStrValue : pValues.split(REGEX_MEAN_VALUES_SEPARATOR)) {
			vValues.add(convertToDouble(vStrValue));
		}
		return vValues;
	}

	private Double mean(Double... pValues) {
		if (pValues == null || pValues.length < 1) {
			return 0D;
		}
		
		if (pValues.length < 2) {
			return pValues[0];
		}
		
		double vSum = 0D;
		for (Double vValue : pValues) {
			vSum += vValue;
		}
		
		return vSum / pValues.length;
	}

	private void validateNumbers(String... pNumbers) {
		for (String vNumber : pNumbers) {
			if (!isNumeric(vNumber)) {
				throw new UnsupportedMathOperationException("Please set a numeric value");
			}
		}
	}
	
	private Double convertToDouble(String strNumber) {
		if (strNumber == null) {
			return 0D;
		}
		
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(strNumber)) {
			return Double.parseDouble(number);
		}
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null) {
			return false;
		}

		String number = strNumber.replaceAll(",", ".");
		return number.matches(REGEX_DOUBLE_VALUE);
	}
}
