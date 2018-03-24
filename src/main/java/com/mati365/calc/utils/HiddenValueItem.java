/* file name  : src/main/java/com/mati365/calc/utils/HiddenValueItem.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : sob 24 mar 10:38:40 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.utils;

import javax.validation.constraints.NotNull;

/** 
 * Allows input field to contain hidden values 
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class HiddenValueItem {
    private String name;
    private int value;

    public HiddenValueItem(@NotNull String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName()     { return this.name;  }
    public int getValue()       { return this.value; }

    @Override
    public String toString()    { return this.name; }
}

