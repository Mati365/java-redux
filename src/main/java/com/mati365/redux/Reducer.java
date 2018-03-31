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
import java.util.function.BiFunction;

import java.util.LinkedHashMap;
import java.util.UUID;

public class Reducer<Action extends ReducerAction, State extends ReducerState> {
    private State state = null;
    
    private LinkedHashMap<UUID, BiConsumer<Action, State>> consumers = new LinkedHashMap<>();
    private LinkedHashMap<String, BiFunction<Action, State, State>> reducerMap = null;

    public Reducer(
            @NotNull State initialState, 
            @NotNull LinkedHashMap<String, BiFunction<Action, State, State>> reducerMap) {
        this.state = initialState;
        this.reducerMap = reducerMap;
    }
    
    public State getState() { return this.state; }
    public LinkedHashMap<String, BiFunction<Action, State, State>> getReducerMap() {
        return this.reducerMap;
    }

    /**
     * Reduces state value, default is identity
     *
     * @param action    Action description  
     * @return 
     */
    protected State reduce(@NotNull Action action) { 
        BiFunction<Action, State, State> reducer = reducerMap.get(action.getName());
        if (reducer != null)
            state = reducer.apply(action, state);

        return state; 
    }
    
    /**
     * @param   key     
     * @param   listener
     */
    public Reducer<Action, State> addActionReducer(
            @NotNull String key, 
            @NotNull BiFunction<Action, State, State> listener) {
        reducerMap.put(key, listener);
        return this;
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
