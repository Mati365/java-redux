/* file name  : src/main/java/com/mati365/calc/ui/parts/StatusBar.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : pon  2 kwi 18:25:23 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import javax.validation.constraints.NotNull;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import java.awt.BorderLayout;

import com.mati365.calc.logic.*;
import com.mati365.calc.utils.Resources;

public class StatusBar extends Logicable<SheetLogic> {
    private JLabel status = new JLabel("", SwingConstants.RIGHT);

    public StatusBar(@NotNull SheetLogic logic) {
        super(logic);
        mountStateListeners();
    }

    /** 
     * Add status change handler to label
     */
    private void mountStateListeners() {
        logic
            .getReducer()
            .subscribe((action, state) -> {
                String text = Resources.Translations.getString(
                            state.lastModified == null
                                ? "default_status" 
                                : "modified", 
                            state.lastModified
                        );
                status.setText(text); 
            });
    }
    
    public JPanel getDefaultPanel() {
        JPanel panel = new JPanel(new BorderLayout()); 
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        panel.add(status);
        return panel;
    }
}
