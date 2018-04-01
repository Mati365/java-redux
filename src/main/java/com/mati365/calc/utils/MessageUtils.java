/* file name  : src/main/java/com/mati365/calc/utils/NumericUtils.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ndz  1 kwi 16:46:38 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.utils;

import javax.validation.constraints.NotNull;
import javax.swing.JOptionPane;

import java.lang.NumberFormatException;
import java.util.function.Consumer;

import com.mati365.calc.utils.Resources;

/** 
 * Swing based utilities to validate numbers  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class MessageUtils {  
    /** 
     * Converts number from string to Float. Displays modal if error
     * 
     * @param number 
     * @param fn 
     */
    public static final void messagedNumberParse(String number, @NotNull Consumer<Float> fn) { 
        try {
            Float value = Float.parseFloat(number);
            fn.accept(value);
        } catch(NumberFormatException exception) {
            JOptionPane.showMessageDialog(
                null,
                Resources.Translations.getString("incorrect_number_format"),
                Resources.Translations.getString("error"),
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
