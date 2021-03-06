/* file name  : src/main/java/com/mati365/redux/TimeTravelReducer.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : sob 31 mar 12:58:37 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.redux.history;

import java.util.function.BiFunction;
import java.util.LinkedHashMap;
import java.util.Arrays;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;

import com.mati365.redux.*;

/** 
 * Reducer that saves N states in history and 
 * adds UNDO / REDO action listeners  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class TimeTravelReducer<Action extends ReducerAction, State extends ReducerState> extends Reducer<Action, State> {
    public static final String UNDO = "@time/undo";
    public static final String REDO = "@time/redo";

    private byte maxHistoryLength = 8;
    private String[] ignoredActions = {};

    /** whole app history should be fully serializable */
    private LinkedList<State> cachedStates = new LinkedList<State>();
    private LinkedList<State> cachedFutureStates = new LinkedList<State>();

    public TimeTravelReducer(
            @NotNull State initialState, 
            @NotNull LinkedHashMap<String, BiFunction<Action, State, State>> reducerMap) {    
        super(initialState, reducerMap);
        attachTimeReducers();
    }

    public TimeTravelReducer(
            @NotNull State initialState, 
            @NotNull LinkedHashMap<String, BiFunction<Action, State, State>> reducerMap,
            byte maxHistoryLength) {    
        super(initialState, reducerMap);
        this.maxHistoryLength = maxHistoryLength;
        
        attachTimeReducers();
    }
    
    public LinkedList<State> getCachedStates()          { return this.cachedStates; }
    public LinkedList<State> getCachedFutureStates()    { return this.cachedFutureStates; }

    /**
     * Set list of actions that don't generate history
     *
     * @param actions
     */
    public TimeTravelReducer<Action, State> setIgnoredActions(@NotNull String[] actions) {
        ignoredActions = actions;
        return this;
    }

    /** 
     * Adds undo / redo action listener  
     */
    @SuppressWarnings("unchecked")
    private void attachTimeReducers() {
       super
          /** UNDO */
          .addActionReducer(
                  TimeTravelReducer.UNDO,
                  (Action action, State state) -> {
                      cachedFutureStates.push((State) state.branch());
                      return cachedStates.isEmpty() 
                          ? state 
                          : cachedStates.poll();
                  })
          /** REDO */
          .addActionReducer(
                  TimeTravelReducer.REDO,
                  (Action action, State state) -> {
                      return cachedFutureStates.isEmpty() 
                          ? state 
                          : cachedFutureStates.poll();
                  });
    }

    @Override
    @SuppressWarnings("unchecked")
    public State reduce(@NotNull Action action) {
        if (cachedStates.size() >= maxHistoryLength)
            cachedStates.removeLast();
       
        String name = action.getName(); 
        if (!name.equals(TimeTravelReducer.UNDO) 
                && !Arrays.asList(ignoredActions).contains(name)
                && !name.equals(TimeTravelReducer.REDO)) {
            cachedStates.push((State) this.getState().branch());
        }
        
        return super.reduce(action);
    }
}
