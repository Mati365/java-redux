
/* file name  : src/main/java/com/mati365/calc/ui/parts/CellCoordinatePanel.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 23:26:59 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import javax.validation.constraints.NotNull;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;

import java.lang.NumberFormatException;

import java.awt.event.MouseEvent;
import java.awt.GridLayout;

import com.mati365.calc.utils.ClickMouseListener;
import com.mati365.calc.utils.Resources;
import com.mati365.calc.logic.SheetLogic;

/** 
 * Component that allows user to change data in cells 
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class OperationPanel {
    private static final long serialVersionUID = 1L;

    private SheetLogic logic;

    public OperationPanel(@NotNull SheetLogic logic) {
        this.logic = logic;    
    }

    public JPanel getPanel() { 
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 0));   
        
        JButton clear = new JButton(Resources.Translations.getString("clear"));
        clear.addMouseListener((ClickMouseListener) (MouseEvent) -> logic.clear());
        panel.add(clear);
            
        JButton load = new JButton(Resources.Translations.getString("open"));
        panel.add(load);

        JButton save = new JButton(Resources.Translations.getString("save"));
        panel.add(save);
        
        return panel;
    }
}
