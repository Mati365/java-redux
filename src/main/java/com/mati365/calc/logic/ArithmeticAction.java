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
    public static final String CLEAR = "@arithmetic/clear";
    public static final String LOAD = "@arithmetic/load";
    public static final String FILL = "@arithmetic/fill";

    private Float[] args;

    public ArithmeticAction(String name, Float[] args) {
        super(name);
        this.args = args;
    }

    public Float[] getArgs()    { return args; }
}
