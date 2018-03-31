
/* file name  : src/main/java/com/mati365/calc/logic/SheetLogic.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 15:26:31 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.logic;

import java.util.function.BiConsumer;
import java.util.Observable;
import java.util.LinkedHashMap;

import java.awt.Dimension;
import java.awt.Point;

import javax.validation.constraints.NotNull;

import com.mati365.calc.utils.Resources;
import com.mati365.calc.utils.Matrix;
import com.mati365.redux.Reducer;

/** 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class SheetReducer extends Reducer<ArithmeticAction, ArithmeticState> {
    public static final int SUM_MATRIX          = 1;
    public static final int AVG_MATRIX          = 2;
    public static final int MIN_MAX_MATRIX      = 3;
    
    public SheetReducer() {
        super(new ArithmeticState(), new LinkedHashMap<>() {{
            put(ArithmeticAction.CLEAR, SheetReducer::reduceClear);
            put(ArithmeticAction.CALC_ERROR, SheetReducer::reduceCalcError);
            put(ArithmeticAction.LOAD_CELL, SheetReducer::reduceLoadCell);
            put(ArithmeticAction.MATRIX_OPERATION, SheetReducer::reduceMatrixOperation);
        }});
    }

    /** 
     * @param action 
     * @param state 
     */
    private static void reduceClear(ArithmeticAction action, ArithmeticState state) {
        Float args = (Float) action.getPayload();

        state.operationResult = "";
        state.matrix.fill(args == null ? 0.f : args);
    }

    /** 
     * @param action 
     * @param state 
     */
    private static void reduceCalcError(ArithmeticAction action, ArithmeticState state) {
        state.error = (String) action.getPayload();
        state.operationResult = "";
    };

    /** 
     * @param action 
     * @param state 
     */
    private static void reduceLoadCell(ArithmeticAction action, ArithmeticState state) {
        Float[] args = (Float[]) action.getPayload();
        state.matrix.load(
                new Point(
                    Math.round(args[0]), 
                    Math.round(args[1])),
                args[2]);
    };
    
    /** 
     * @param action 
     * @param state 
     */
    private static void reduceMatrixOperation(ArithmeticAction action, ArithmeticState state) {
        Integer operation = Math.round((int)action.getPayload());
        Float value = null;

        state.lastOperation = operation;
        if (operation == SheetReducer.AVG_MATRIX) {
            Float sum = state.matrix.reduce(0.f, (acc, val) -> acc + val);
            value = sum / state.matrix.getLength();

        } else if (operation == SheetReducer.SUM_MATRIX) { 
            value = state.matrix.reduce(0.f, (acc, val) -> acc + val);
        } else if (operation == SheetReducer.MIN_MAX_MATRIX) {
            Float min = state.matrix.reduce(Float.MAX_VALUE, (acc, val) -> Math.min(acc, val));
            Float max = state.matrix.reduce(Float.MIN_VALUE, (acc, val) -> Math.max(acc, val));
            
            state.operationResult = Resources.Translations.getString(
                    "min_max_value", 
                    min, max);
            return;
        }

        state.operationResult = String.valueOf(value);
    };
}

