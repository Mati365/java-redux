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
import javax.validation.constraints.NotNull;

import java.awt.Dimension;
import java.awt.Point;

import com.mati365.redux.ActionCreator;
import com.mati365.redux.history.TimeTravelReducer;

/** 
 * Whole App logic  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class SheetLogic extends ActionCreator<SheetReducer> {
    public SheetLogic() {
        super(new SheetReducer());
        
        reducer
            .setIgnoredActions(new String[] {
                ArithmeticAction.MATRIX_OPERATION
            })
            
            .subscribe((ArithmeticAction action, ArithmeticState state) -> {
                if (action.getName() == ArithmeticAction.MATRIX_OPERATION)
                    return;

                SheetLogic.this.reducer.dispatch(
                    new ArithmeticAction(
                        ArithmeticAction.MATRIX_OPERATION,
                        state.lastOperation)); 
            });
    }

    public SheetReducer getReducer() { return this.reducer; }

    /**
     * Clear whole array with value
     *
     * @action  
     */
    public void fill(Float value) {
        reducer.dispatch(
                new ArithmeticAction(
                    ArithmeticAction.CLEAR, 
                    value)); 
    }
    
    public void clear() {
        this.fill(0.f);
    }

    /** 
     * Executes operation on matrix  
     * 
     * @param operation 
     */
    public void operation(int operation) {
        reducer.dispatch(
                new ArithmeticAction(
                    ArithmeticAction.MATRIX_OPERATION,
                    operation));
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

        reducer.dispatch(
                new ArithmeticAction(
                    ArithmeticAction.LOAD_CELL,
                    args));
    }
    
    /**
     * Pops history and reverts app state
     */
    public void undo() {
        reducer.dispatch(
                new ArithmeticAction(TimeTravelReducer.UNDO));
    }
    
    /**
     * Pops future history and revertes app state
     */
    public void redo() {
        reducer.dispatch(
                new ArithmeticAction(TimeTravelReducer.REDO));
    }
}
