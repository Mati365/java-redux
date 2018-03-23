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

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import com.mati365.calc.logic.SheetLogic;

/** 
 * Main app layer that contains sheet  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class CalcPanel {
    private SheetLogic logic = new SheetLogic();

    private JPanel getCalcContentPanel() {
        JPanel panel = new JPanel();
        panel.add(new ArithmeticSheet(this.logic).getTable());
        return panel;
    }

    public JPanel getDefaultPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel toolbars = new JPanel(new GridLayout(0, 1));
        toolbars.add(new AppMenu(this.logic).getMenu());
        toolbars.add(new AppToolBar().getToolBar());

        panel.add(
                toolbars,
                BorderLayout.NORTH);
        
        panel.add(
                this.getCalcContentPanel(), 
                BorderLayout.CENTER);
        return panel;
    }
}
