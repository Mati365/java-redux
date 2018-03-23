/* file name  : src/main/java/com/mati365/calc/logic/ArithmeticState.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 21:07:32 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.logic;

import java.util.Observable;
import java.awt.Dimension;

import javax.validation.constraints.NotNull;

import com.mati365.calc.utils.Matrix;
import com.mati365.calc.utils.Reducer;

/** 
 * Whole app state  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class ArithmeticState {
    public String error = null;
    public Matrix<Float> matrix = new Matrix<Float>(new Dimension(5, 5), Float.class).fill(0.f);
}

