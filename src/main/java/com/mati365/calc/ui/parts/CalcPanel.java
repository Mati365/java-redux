/* file name  : src/main/java/com/mati365/calc/ui/CalcPanel.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : czw 22 mar 19:17:24 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import com.mati365.calc.logic.SheetLogic;

/** 
 * Main app layer that contains sheet  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class CalcPanel {
    private SheetLogic logic = new SheetLogic();

    private JPanel getCalcContentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4); 
        
        c.fill = GridBagConstraints.BOTH;

        c.weightx = 0.7;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(
                new JScrollPane(new ArithmeticSheet(this.logic).getTable()),
                c);

        c.weightx = 0.3;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(
                new JButton("AA"),
                c);
        return panel;
    }

    public JPanel getDefaultPanel() {
        JPanel panel = new JPanel(new BorderLayout()); 
        JPanel toolbars = new JPanel(new GridLayout(0, 1)) {{
            add(new AppMenu(CalcPanel.this.logic).getMenu());
            add(new AppToolBar(CalcPanel.this.logic).getToolBar());
        }}; 
        panel.add(
                toolbars,
                BorderLayout.NORTH);
        
        panel.add(
                this.getCalcContentPanel(), 
                BorderLayout.CENTER);
        return panel;
    }
}
