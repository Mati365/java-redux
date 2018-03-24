/* file name  : src/main/java/com/mati365/calc/logic/Logicable.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : sob 24 mar 10:35:59 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.logic;

import javax.validation.constraints.NotNull;

public class Logicable<T> {
    protected T logic;

    public Logicable(@NotNull T logic) {
        this.logic = logic;
    }

    public T getLogic() { return this.logic; }
}

