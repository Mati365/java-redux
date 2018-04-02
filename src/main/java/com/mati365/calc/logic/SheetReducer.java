
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
import java.util.Calendar;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.awt.Dimension;
import java.awt.Point;

import javax.validation.constraints.NotNull;

import com.mati365.calc.utils.Resources;
import com.mati365.calc.utils.Matrix;

import com.mati365.redux.history.TimeTravelReducer;

/** 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class SheetReducer extends TimeTravelReducer<ArithmeticAction, ArithmeticState> {
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
    private static ArithmeticState reduceClear(ArithmeticAction action, ArithmeticState state) {
        Float args = (Float) action.getPayload();

        state.operationResult = "";
        state.matrix.fill(args == null ? 0.f : args);
        return state;
    }

    /** 
     * @param action 
     * @param state 
     */
    private static ArithmeticState reduceCalcError(ArithmeticAction action, ArithmeticState state) {
        state.error = (String) action.getPayload();
        state.operationResult = "";
        return state;
    };

    private static final String getLastModifiedDate() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    };

    /** 
     * @param action 
     * @param state 
     */
    private static ArithmeticState reduceLoadCell(ArithmeticAction action, ArithmeticState state) {
        Float[] args = (Float[]) action.getPayload(); 
        state.lastModified = SheetReducer.getLastModifiedDate();
        state.matrix.load(
                new Point(
                    Math.round(args[0]), 
                    Math.round(args[1])),
                args[2]);
        return state;
    };
    
    /** 
     * @param action 
     * @param state 
     */
    private static ArithmeticState reduceMatrixOperation(ArithmeticAction action, ArithmeticState state) {
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
            return state;
        }

        state.operationResult = String.valueOf(value);
        return state;
    };
}

