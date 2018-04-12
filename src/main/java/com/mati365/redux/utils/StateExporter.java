/* file name  : src/main/java/com/mati365/calc/logic/exporter/ArithmeticStateExporter.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : pon  2 kwi 14:52:34 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.redux;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.NotNull;

import java.io.File;
import java.io.IOException;

/** 
 * Class that helps with read / save app state from JSON file  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class StateExporter {
    /**
     * Export app state to json file
     *
     * @param file      File path
     * @param state     App state
     */
    public static final <T extends ImportableState> void export(
            @NotNull File file,
            @NotNull T state) {
        try { 
            ObjectMapper mapper = new ObjectMapper();
            state.loadedFile = file.getPath();

            mapper.writeValue(file, state);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 
     * Imports state from file 
     * 
     * @param type      Generic class 
     * @param file      File path 
     * @return App state
     */
    public static final <T extends ImportableState> T load(
            @NotNull Class<? extends T> type, 
            @NotNull File file) {
        try {
            T state = new ObjectMapper().readValue(file, type);
            state.loadedFile = file.getPath();
            return state;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
