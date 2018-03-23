/* file name  : src/main/java/com/mati365/calc/logic/ArithmeticAction.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 21:08:24 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.logic;

import com.mati365.calc.utils.ReducerAction;

public class ArithmeticAction extends ReducerAction {
    public static final String CLEAR            = "@arithmetic/clear";
    public static final String LOAD             = "@arithmetic/load";
    public static final String FILL             = "@arithmetic/fill";
    public static final String CALC_ERROR       = "@arithmetic/calc_error";

    private Float[] args;
    private String message;

    public ArithmeticAction(String name, Float[] args) {
        super(name);
        this.args = args;
    }

    public ArithmeticAction(String name, Float[] args, String message) {
        this(name, args);
        this.message = message;
    }
    
    public Float[] getArgs()    { return this.args; }
    public String getMessage()  { return this.message; }
}
