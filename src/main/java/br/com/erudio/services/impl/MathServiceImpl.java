package br.com.erudio.services.impl;

import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.services.MathService;

@Service
public class MathServiceImpl implements MathService {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double sum(double pLeftOperand, double pRightOperand) {
        return pLeftOperand + pRightOperand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double subtract(double pLeftOperand, double pRightOperand) {
        return pLeftOperand - pRightOperand;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double multiply(double pLeftOperand, double pRightOperand) {
        return pLeftOperand * pRightOperand;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double divide(double pLeftOperand, double pRightOperand) {
        if (pRightOperand == 0D) {
            throw new UnsupportedMathOperationException("Cannot perform a division by zero");
        }
        return pLeftOperand / pRightOperand;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double squareRoot(double pOperand) {
        if (pOperand < 0D) {
            throw new UnsupportedMathOperationException("Cannot perform a squere root of a negative value");
        }
        return Math.sqrt(pOperand);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double mean(double... pValues) {
        if (pValues == null || pValues.length < 1) {
            return 0D;
        }
        
        if (pValues.length < 2) {
            return pValues[0];
        }
        
        double vSum = 0D;
        for (double vValue : pValues) {
            vSum += vValue;
        }
        
        return vSum / pValues.length;
    }
    
}
