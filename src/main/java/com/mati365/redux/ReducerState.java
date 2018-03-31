/* file name  : src/main/java/com/mati365/redux/State.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : sob 31 mar 14:28:55 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.redux;

/** 
 * Branch is essentially cloning state. Its not 
 * Cloneable type. 
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public interface ReducerState { 
    /** 
     * Create new branch based on this state
     * 
     * @return 
     */
    default public ReducerState branch() { return null; } 
}
