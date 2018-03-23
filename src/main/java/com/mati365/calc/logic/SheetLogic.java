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

import javax.validation.constraints.NotNull;

import com.mati365.calc.utils.Matrix;
import com.mati365.calc.utils.Reducer;

/** 
 * Whole App logic  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class SheetLogic extends Reducer<ArithmeticAction, ArithmeticState> {
    public SheetLogic() {
        super(new ArithmeticState(), new LinkedHashMap<>() {{
            /** clear whole matrix */ put(ArithmeticAction.CLEAR, (ArithmeticAction action, ArithmeticState state) -> {
                state.matrix.fill(0.f);
            });

            /** attach error */ put(ArithmeticAction.CALC_ERROR, (ArithmeticAction action, ArithmeticState state) -> {
                state.error = action.getMessage();
            });
        }});
    }
    
    /**
     * Clear whole array with default value
     *
     * @action  
     */
    public void clear() {
        this.dispatch(new ArithmeticAction(ArithmeticAction.CLEAR, null)); 
    }
}
