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

import com.mati365.calc.utils.Matrix;
import com.mati365.calc.utils.Reducer;

/** 
 * Whole App logic  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class SheetLogic extends Reducer<ArithmeticAction, ArithmeticState> {
    public static final int SUM_MATRIX          = 1;
    public static final int AVG_MATRIX          = 2;
    public static final int MIN_MAX_MATRIX      = 3;

    public SheetLogic() {
        super(new ArithmeticState(), new LinkedHashMap<>() {{
            /** clear whole matrix */ put(ArithmeticAction.CLEAR, (ArithmeticAction action, ArithmeticState state) -> {
                state.matrix.fill(0.f);
                state.operationResult = "";
            });

            /** attach error */ put(ArithmeticAction.CALC_ERROR, (ArithmeticAction action, ArithmeticState state) -> {
                state.error = action.getMessage();
                state.operationResult = "";
            });

            /** load value */ put(ArithmeticAction.LOAD_CELL, (ArithmeticAction action, ArithmeticState state) -> {
                state.matrix.load(
                        new Point(
                            Math.round(action.getArgs()[0]), 
                            Math.round(action.getArgs()[1])),
                        action.getArgs()[2]);
            });
            /** mutate matrix */ put(ArithmeticAction.MATRIX_OPERATION, (ArithmeticAction action, ArithmeticState state) -> {
                Integer operation = Math.round(action.getArgs()[0]);
                Float value = null;

                if (operation == SheetLogic.AVG_MATRIX) {
                    Float sum = state.matrix.reduce(0.f, (acc, val) -> acc + val);
                    value = sum / state.matrix.getLength();

                } else if (operation == SheetLogic.SUM_MATRIX) 
                    value = state.matrix.reduce(0.f, (acc, val) -> acc + val);
               
                state.lastOperation = operation;
                state.operationResult = String.valueOf(value);
            });
        }});
        
        // live matrix calculation
        this.subscribe(
                (ArithmeticAction action, ArithmeticState state) -> {
                    if (action.getName() == ArithmeticAction.MATRIX_OPERATION)
                        return;

                    SheetLogic.this.dispatch(
                        new ArithmeticAction(
                            ArithmeticAction.MATRIX_OPERATION,
                            new Float[] {
                                Float.valueOf(state.lastOperation)
                            }
                        )); 
                });
    }
    
    /**
     * Clear whole array with default value
     *
     * @action  
     */
    public void clear() {
        this.dispatch(new ArithmeticAction(ArithmeticAction.CLEAR, null)); 
    }
    
    /** 
     * Executes operation on matrix  
     * 
     * @param operation 
     */
    public void operation(int operation) {
        this.dispatch(
                new ArithmeticAction(
                    ArithmeticAction.MATRIX_OPERATION,
                    new Float[] {
                       (float) operation 
                    }));
    }

    /** 
     * Loads value into cell
     *
     * @action 
     * @param cell 
     * @param value 
     */
    public void load(@NotNull Point cell, Float value) {
        Float[] args = {
            (float) cell.getX(),
            (float) cell.getY(),
            value
        };

        this.dispatch(
                new ArithmeticAction(
                    ArithmeticAction.LOAD_CELL,
                    args));
    }
}
