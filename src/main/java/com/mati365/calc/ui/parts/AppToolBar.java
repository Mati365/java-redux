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
import javax.swing.Box;

import com.mati365.calc.logic.*;
import com.mati365.calc.ui.IconButton;
import com.mati365.calc.utils.AppDestroyer;

/** 
 * Component that hold toolbar items in app
 *
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class AppToolBar extends Logicable<SheetLogic> {
    private JToolBar toolbar = null;
    private IconButton undo = null;
    private IconButton redo = null;
    private IconButton override = null;

    public AppToolBar(SheetLogic logic) {
        super(logic);

        toolbar = new JToolBar();
        undo = new IconButton("undo", (MouseEvent) -> logic.undo());
        redo = new IconButton("redo", (MouseEvent) -> logic.redo());
        override = new IconButton("override", (MouseEvent) -> logic.exportOverridenState());

        toolbar.add(new IconButton("new", (MouseEvent) -> logic.clear()));
        
        toolbar.addSeparator();
        toolbar.add(undo); 
        toolbar.add(redo);
        toolbar.addSeparator();

        toolbar.add(override);
        toolbar.add(new IconButton("save", (MouseEvent) -> logic.exportState()));
        toolbar.add(new IconButton("open", (MouseEvent) -> logic.loadState())); 
    
        toolbar.addSeparator();
        toolbar.add(new IconButton("monkey", (MouseEvent) -> new AuthorDialog(toolbar))); 
        toolbar.add(new IconButton("help", (MouseEvent) -> new InfoDialog(toolbar))); 
        
        toolbar.addSeparator();
        toolbar.add(new IconButton("exit", (MouseEvent) -> AppDestroyer.tryKillApp(logic)));
        
        mountStateListeners();
    }
    
    private void mountStateListeners() { 
        SheetReducer reducer = logic.getReducer();
        reducer.subscribe((ArithmeticAction action, ArithmeticState state) -> {    
            undo.setEnabled(!reducer.getCachedStates().isEmpty()); 
            redo.setEnabled(!reducer.getCachedFutureStates().isEmpty());
            
            override.setEnabled(
                    state.unsavedChanges && (state.loadedFile != null));     
        });
    }

    public JToolBar getToolBar() { return this.toolbar; }
}
