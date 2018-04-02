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

import java.util.Optional;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.JButton;

import com.mati365.calc.logic.*;
import com.mati365.calc.ui.IconButton;

/** 
 * Component that hold toolbar items in app
 *
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class AppToolBar extends Logicable<SheetLogic> {
    private JToolBar toolbar = null;
    private IconButton undo = null;
    private IconButton redo = null;

    public AppToolBar(SheetLogic logic) {
        super(logic);

        toolbar = new JToolBar();
        undo = new IconButton("undo", (MouseEvent) -> logic.undo());
        redo = new IconButton("redo", (MouseEvent) -> logic.redo());
        
        toolbar.add(new IconButton("new", (MouseEvent) -> logic.clear()));
        toolbar.add(undo); 
        toolbar.add(redo);
        toolbar.add(new IconButton("save", (MouseEvent e) -> {
            ExporterDialog.export(logic.getReducer().getState());
        }));

        toolbar.add(new IconButton("open", (MouseEvent e) -> {
            Optional
                .ofNullable(ExporterDialog.load())
                .ifPresent((ArithmeticState state) -> {
                    logic
                        .getReducer()
                        .setState(state);
                });
        }));
        
        toolbar.add(new IconButton("exit", (MouseEvent e) -> {
            System.exit(0);
        }));

        mountStateListeners();
    }
    
    private void mountStateListeners() { 
        SheetReducer reducer = logic.getReducer();
        reducer.subscribe((ArithmeticAction action, ArithmeticState state) -> {    
            undo.setEnabled(!reducer.getCachedStates().isEmpty()); 
            redo.setEnabled(!reducer.getCachedFutureStates().isEmpty());
        });
    }

    public JToolBar getToolBar() { return this.toolbar; }
}
