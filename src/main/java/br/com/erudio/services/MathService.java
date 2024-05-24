package br.com.erudio.services;

public interface MathService {

    double sum(double pLeftOperand, double pRightOperand);

    double subtract(double pLeftOperand, double pRightOperand);

    double multiply(double pLeftOperand, double pRightOperand);

    double divide(double pLeftOperand, double pRightOperand);

    double squareRoot(double pOperand);

    double mean(double... pValues);

}
