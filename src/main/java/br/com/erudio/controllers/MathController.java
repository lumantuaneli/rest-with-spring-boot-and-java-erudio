package br.com.erudio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.services.MathService;
import br.com.erudio.util.InputUtil;
import br.com.erudio.util.MathUtils;

@RestController
public class MathController {
    @Autowired
    private MathService mathService;

    @GetMapping("/sum/{leftOperand}/{rightOperand}")
    public Double sum(@PathVariable(value = "leftOperand") String pLeftOperand, @PathVariable(value = "rightOperand") String pRightOperand) {
        Double vLeftOperand = MathUtils.convertToDouble(pLeftOperand, false);
        Double vRighOperand = MathUtils.convertToDouble(pRightOperand, false);
        return mathService.sum(vLeftOperand, vRighOperand);
    }

    @GetMapping("/subtraction/{leftOperand}/{rightOperand}")
    public Double subtraction(@PathVariable(value = "leftOperand") String pLeftOperand, @PathVariable(value = "rightOperand") String pRightOperand) {
        Double vLeftOperand = MathUtils.convertToDouble(pLeftOperand, false);
        Double vRighOperand = MathUtils.convertToDouble(pRightOperand, false);
        return mathService.subtract(vLeftOperand, vRighOperand);
    }

    @GetMapping("/multiplication/{leftOperand}/{rightOperand}")
    public Double multiplication(@PathVariable(value = "leftOperand") String pLeftOperand, @PathVariable(value = "rightOperand") String pRightOperand) {
        Double vLeftOperand = MathUtils.convertToDouble(pLeftOperand, false);
        Double vRighOperand = MathUtils.convertToDouble(pRightOperand, false);
        return mathService.multiply(vLeftOperand, vRighOperand);
    }

    @GetMapping("/division/{leftOperand}/{rightOperand}")
    public Double division(@PathVariable(value = "leftOperand") String pLeftOperand, @PathVariable(value = "rightOperand") String pRightOperand) {
        Double vLeftOperand = MathUtils.convertToDouble(pLeftOperand, false);
        Double vRighOperand = MathUtils.convertToDouble(pRightOperand, false);
        return mathService.divide(vLeftOperand, vRighOperand);
    }

    @GetMapping("/square-root/{operand}")
    public Double squareRoot(@PathVariable(value = "operand") String pOperand) {
        Double vOperand = MathUtils.convertToDouble(pOperand, false);
        return mathService.squareRoot(vOperand);
    }

    @GetMapping("/mean/{value1}/{value2}")
    public Double mean(@PathVariable(value = "value1") String pValue1, @PathVariable(value = "value2") String pValue2) {
        Double vValue1 = MathUtils.convertToDouble(pValue1, false);
        Double vValue2 = MathUtils.convertToDouble(pValue2, false);
        return mathService.mean(vValue1, vValue2);
    }

    @GetMapping("/mean/{value1}/{value2}/{value3}")
    public Double mean(@PathVariable(value = "value1") String pValue1, @PathVariable(value = "value2") String pValue2, @PathVariable(value = "value3") String pValue3) {
        Double vValue1 = MathUtils.convertToDouble(pValue1, false);
        Double vValue2 = MathUtils.convertToDouble(pValue2, false);
        Double vValue3 = MathUtils.convertToDouble(pValue3, false);
        return mathService.mean(vValue1, vValue2, vValue3);
    }

    @GetMapping("/mean/{value1}/{value2}/{value3}/{value4}")
    public Double mean(@PathVariable(value = "value1") String pValue1, @PathVariable(value = "value2") String pValue2, @PathVariable(value = "value3") String pValue3, 
        @PathVariable(value = "value4") String pValue4) {
        Double vValue1 = MathUtils.convertToDouble(pValue1, false);
        Double vValue2 = MathUtils.convertToDouble(pValue2, false);
        Double vValue3 = MathUtils.convertToDouble(pValue3, false);
        Double vValue4 = MathUtils.convertToDouble(pValue4, false);
        return mathService.mean(vValue1, vValue2, vValue3, vValue4);
    }

    @GetMapping("/mean/{semicolon-separated-values}")
    public Double mean(@PathVariable(value = "semicolon-separated-values") String pValues) {
        double[] vValues = InputUtil.splitUnderlineSeparatedValues(pValues);
        return mathService.mean(vValues);
    }

}
