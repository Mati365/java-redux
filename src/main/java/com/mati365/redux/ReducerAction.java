/* file name  : src/main/java/com/mati365/calc/utils/ReducerAction.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 21:28:17 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.redux;

import javax.validation.constraints.NotNull;

/** 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class ReducerAction {
    protected String name = null;
    protected Object payload = null;

    public ReducerAction(@NotNull String name) {
        this.name = name;
    }

    public ReducerAction(@NotNull String name, Object payload) {
        this.name = name;
        this.payload = payload;
    }

    public String getName()     { return this.name; }
    public Object getPayload()  { return this.payload; }
}
