/* file name  : src/main/java/com/mati365/redux/utils/ImportableState.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : czw 12 kwi 18:11:58 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.redux;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mati365.redux.ReducerState;

/** 
 * App state that contains dump file name  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public abstract class ImportableState implements ReducerState {
    @JsonIgnore public String loadedFile = null;
}

