/* file name  : src/main/java/com/mati365/calc/logic/ArithmeticAction.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 21:08:24 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.logic;

import javax.validation.constraints.NotNull;
import com.mati365.redux.ReducerAction;

/** 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class ArithmeticAction extends ReducerAction {
    public static final String CLEAR            = "@arithmetic/clear";
    public static final String LOAD             = "@arithmetic/load";
    public static final String LOAD_CELL        = "@arithmetic/load_cell";
    public static final String FILL             = "@arithmetic/fill";
    public static final String CALC_ERROR       = "@arithmetic/calc_error";
    public static final String MATRIX_OPERATION = "@arithmetic/matrix_operation";
    
    public ArithmeticAction(@NotNull String name) {
        super(name);
    }

    public ArithmeticAction(@NotNull String name, Object payload) {
        super(name, payload);
    }
}
