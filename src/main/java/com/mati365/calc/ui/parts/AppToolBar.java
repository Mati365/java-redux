/* file name  : src/main/java/com/mati365/calc/ui/parts/AppToolBar.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 21:59:43 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import java.awt.event.MouseEvent;

import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.JButton;

import com.mati365.calc.ui.IconButton;
import com.mati365.calc.logic.SheetLogic;

/** 
 * Component that hold toolbar items in app
 *
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class AppToolBar {
    private JToolBar toolbar = null;

    public AppToolBar(SheetLogic logic) {
        toolbar = new JToolBar();
        toolbar.add(new IconButton("new", (MouseEvent e) -> {
            logic.clear();
        }));
        toolbar.add(new IconButton("undo"));
        toolbar.add(new IconButton("redo"));
        toolbar.add(new IconButton("add"));
        toolbar.add(new IconButton("exit", (MouseEvent e) -> {
            System.exit(0);
        }));  
        
    }

    public JToolBar getToolBar() { return this.toolbar; }
}
