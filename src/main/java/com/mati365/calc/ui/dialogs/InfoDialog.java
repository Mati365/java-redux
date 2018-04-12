/* file name  : src/main/java/com/mati365/calc/ui/dialogs/InfoDialog.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : czw 12 kwi 19:24:22 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import javax.validation.constraints.NotNull;
import javax.swing.JComponent;

import com.mati365.calc.utils.Resources;

/** 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class InfoDialog extends MessageDialog {
    private static final long serialVersionUID = 1l;

    public InfoDialog(@NotNull JComponent parent) {
        super(
                parent, 
                Resources.Translations.getString("info_message"));
    }
}
