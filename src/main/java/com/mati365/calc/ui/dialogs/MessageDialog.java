/* file name  : src/main/java/com/mati365/calc/ui/dialogs/AuthorDialog.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 22:29:27 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import javax.validation.constraints.NotNull;

import java.awt.BorderLayout;
import javax.swing.*;

/** 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class MessageDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    public MessageDialog(@NotNull JComponent parent, @NotNull String message) {
        super(
                (JFrame) SwingUtilities.getWindowAncestor(parent), 
                "Dialog");

        getContentPane().add(this.getMessagePanel(message));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    /** 
     * @param message 
     * @return 
     */
    private JPanel getMessagePanel(String message) { 
        JPanel panel = new JPanel();
        panel.add(new JLabel(message));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return panel;
    }    
}
