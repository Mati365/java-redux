/* file name  : src/main/java/com/mati365/calc/logic/ArithmeticState.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 21:07:32 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.logic;

import javax.validation.constraints.NotNull;

import java.util.Observable;
import java.awt.Dimension;

import com.mati365.redux.Reducer;
import com.mati365.calc.utils.Matrix;

/** 
 * Whole app state  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class ArithmeticState {
    public String error = null;
    public Integer lastOperation = 2;
    public String operationResult = "";
    public Matrix<Float> matrix = new Matrix<Float>(new Dimension(5, 5), Float.class).fill(0.f);
    
    public ArithmeticState() {}

    public ArithmeticState(ArithmeticState previousState) {
        this.error = previousState.error;
        this.lastOperation = lastOperation;
        this.operationResult = operationResult;
        this.matrix = new Matrix<>(previousState.matrix);
    }
}

