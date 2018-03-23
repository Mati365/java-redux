/* file name  : src/main/java/com/mati365/calc/utils/ReducerAction.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 21:28:17 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.utils;

import javax.validation.constraints.NotNull;

public class ReducerAction {
    protected String name = null;

    public ReducerAction(@NotNull String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}
