/* file name  : src/main/java/com/mati365/calc/logic/exporter/ArithmeticStateExporter.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : pon  2 kwi 14:52:34 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import javax.validation.constraints.NotNull;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.mati365.redux.StateExporter;
import com.mati365.calc.logic.ArithmeticState;

/** 
 * Class that helps with read / save app state and displays modal
 *
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class ExporterDialog {
    /**
     * Export app state to json file
     *
     * @param state     App state
     */
    public static final void export(@NotNull ArithmeticState state) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            StateExporter.export(
                    fileChooser.getSelectedFile(),
                    state);
        }
    }

    /** 
     * Imports state from file 
     * 
     * @param type      Generic class 
     * @return App state
     */
    public static final ArithmeticState load() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            return StateExporter.load(ArithmeticState.class, fileChooser.getSelectedFile());

        return null;
    }
}
