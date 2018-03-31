/* file name  : /Users/mateuszbaginski/Projekty/SheetCalc/src/main/java/com/mati365/calc/logic/Reducer.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 20:16:16 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.redux;

import javax.validation.constraints.NotNull;

import java.util.function.BiConsumer;
import java.util.LinkedHashMap;
import java.util.UUID;

public class Reducer<Action extends ReducerAction, State> {
    protected State state = null;
    private LinkedHashMap<UUID, BiConsumer<Action, State>> consumers = new LinkedHashMap<>();
    private LinkedHashMap<String, BiConsumer<Action, State>> reducerMap = null;

    public Reducer(State initialState, LinkedHashMap<String, BiConsumer<Action, State>> reducerMap) {
        this.state = initialState;
        this.reducerMap = reducerMap;
    }
    
    public State getState() { return this.state; }
    
    /**
     * Reduces state value, default is identity
     */
    protected State reduce(Action action) { 
        BiConsumer<Action, State> reducer = reducerMap.get(action.getName());
        if (reducer != null)
            reducer.accept(action, state);

        return state; 
    }

    /** 
     * Add function that watches state 
     * 
     * @param consumer 
     * @return 
     */
    public UUID subscribe(@NotNull BiConsumer<Action, State> consumer) {
        UUID uid = UUID.randomUUID();
        consumers.put(uid, consumer);
        return uid;
    }

    /** 
     * Remove subscriber by UUID  
     * 
     * @param uid 
     */
    public void unsubscribe(@NotNull UUID uid) {
        consumers.remove(uid);
    }

    /** 
     * Reduces action and trigger all consumers  
     * 
     * @param action 
     */
    public void dispatch(@NotNull Action action) {
        state = this.reduce(action);
        consumers.forEach((key, consumer) -> {
            consumer.accept(action, state);
        });
    }
}
