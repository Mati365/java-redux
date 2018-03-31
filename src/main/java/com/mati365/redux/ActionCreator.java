/* file name  : src/main/java/com/mati365/redux/ActionCreation.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : sob 31 mar 12:23:31 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.redux;

import javax.validation.constraints.NotNull;
import com.mati365.redux.Reducer;

/** 
 * Class that holds reference to reducer 
 *
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class ActionCreator<T extends Reducer<?, ?>> {
    protected T reducer = null;
    
    public ActionCreator(@NotNull T reducer) {
        this.reducer = reducer;
    }

    public T getReducer() { return this.reducer; }
}
