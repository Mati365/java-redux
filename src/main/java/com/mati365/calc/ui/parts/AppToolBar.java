package com.mati365.calc.ui;

import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.JButton;

import com.mati365.calc.ui.IconButton;

/** 
 * Component that hold toolbar items in app
 *
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class AppToolBar {
    private JToolBar toolbar = null;

    public AppToolBar() {
        toolbar = new JToolBar();
        toolbar.add(new IconButton("new"));
        toolbar.add(new IconButton("undo"));
        toolbar.add(new IconButton("redo"));
        toolbar.add(new IconButton("add"));
        toolbar.add(new IconButton("exit"));  
        
    }

    public JToolBar getToolBar() { return this.toolbar; }
}
