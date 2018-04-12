/* file name  : src/main/java/com/mati365/calc/logic/ArithmeticState.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 21:07:32 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.logic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotNull;

import java.util.Observable;
import java.awt.Dimension;

import com.mati365.redux.Reducer;
import com.mati365.redux.ImportableState;
import com.mati365.calc.utils.Matrix;

/** 
 * Whole app state  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class ArithmeticState extends ImportableState {
    public String error = null;
    public Integer lastOperation = 2;
    public String operationResult = ""; 
    public Matrix<Float> matrix = new Matrix<>(new Dimension(5, 5), Float.class).fill(0.f);
    public String lastModified = null;
    
    @JsonIgnore public Boolean unsavedChanges = false;

    @Override
    @JsonIgnore
    public ArithmeticState branch() {
        ArithmeticState state = new ArithmeticState();

        state.error = this.error;
        state.lastModified = this.lastModified;
        state.lastOperation = this.lastOperation;
        state.operationResult = this.operationResult;
        state.unsavedChanges = this.unsavedChanges;     
        state.loadedFile = this.loadedFile;
        state.matrix = new Matrix<>(Float.class, this.matrix);
        return state;
    }
}

