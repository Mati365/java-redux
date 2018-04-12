/* file name  : src/main/java/com/mati365/calc/utils/AppDestroyer.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : czw 12 kwi 17:16:56 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.utils;

import javax.validation.constraints.NotNull;
import javax.swing.*;

import com.mati365.calc.utils.Resources;
import com.mati365.calc.logic.SheetLogic;

/** 
 * Class that helps with killing app  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class AppDestroyer {
    public static void tryKillApp(@NotNull SheetLogic logic) {
        String[] options = {
            Resources.Translations.getString("yes"),
            Resources.Translations.getString("nope"),
        };

        Integer result = null;
        if (logic.getReducer().getState().unsavedChanges) {
            result = JOptionPane.showOptionDialog(
                        null, 
                        Resources.Translations.getString("confirm_exit_discard_changes"),
                        Resources.Translations.getString("confirm_exit"),
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[1]); 
        } else
            result = JOptionPane.YES_OPTION;

        if (result == JOptionPane.YES_OPTION)
            System.exit(0);
    }
}
