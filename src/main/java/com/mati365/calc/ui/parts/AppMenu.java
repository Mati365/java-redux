/* file name  : src/main/java/com/mati365/calc/ui/AppMenu.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : czw 22 mar 21:04:22 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.validation.constraints.NotNull;

import com.mati365.calc.utils.Resources;
import com.mati365.calc.utils.AppDestroyer;

import com.mati365.calc.logic.SheetLogic;
import com.mati365.calc.logic.Logicable;

/** 
 * MenuItem with translation 
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
class TranslatedMenuItem extends JMenuItem {
    private static final long serialVersionUID = 1L;

    public TranslatedMenuItem(
            @NotNull String key, 
            String icon, 
            ActionListener listener) {
        super(
                Resources.Translations.getString(key),
                icon == null 
                    ? null
                    : Resources.Images.getScaledIcon(icon, new Dimension(20, 20)));
        if (listener != null)
            addActionListener(listener);
    }
}

/** 
 * Main App menu 
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class AppMenu extends Logicable<SheetLogic> {
    private JMenuBar menu = null;

    public AppMenu(@NotNull SheetLogic logic) {
        super(logic);
        menu = new JMenuBar(); 

        menu.add(this.getFileMenu());
        menu.add(this.getEditMenu());
        menu.add(this.getInfoMenu());
    }

    public JMenuBar getMenu() { return this.menu; }
    
    /** 
     * Create file menu entry and attach action listeners 
     * 
     * @return 
     */
    public JMenu getFileMenu() {
        JMenu menu = new JMenu(Resources.Translations.getString("file"));
        
        JMenuItem open = new TranslatedMenuItem(
                "open", "open", (MouseEvent) -> logic.loadState());
        menu.add(open);
 
        JMenuItem override = new TranslatedMenuItem(
                "override", "override", (MouseEvent) -> logic.exportOverridenState());
        menu.add(override);

        JMenuItem save = new TranslatedMenuItem(
                "save", "save", (MouseEvent) -> logic.exportState());
        menu.add(save);
        
        JMenuItem exit = new TranslatedMenuItem(
                "exit", "exit", (e) -> AppDestroyer.tryKillApp(logic));
        menu.addSeparator();
        menu.add(exit);

        return menu;
    }

    /** 
     * Create edit menu entry and attach action listeners 
     * 
     * @return 
     */
    public JMenu getEditMenu() {
        JMenu menu = new JMenu(Resources.Translations.getString("edit"));

        JMenuItem undo = new TranslatedMenuItem(
                "undo", "undo", (MouseEvent) -> logic.undo());
        menu.add(undo);

        JMenuItem redo = new TranslatedMenuItem(
                "redo", "redo", (MouseEvent) -> logic.redo());
        menu.add(redo);
        
        return menu;
    }

    /** 
     * Create info menu entry and attach action listeners 
     * 
     * @return 
     */
    public JMenu getInfoMenu() {
        JMenu menu = new JMenu(Resources.Translations.getString("info"));
        
        JMenuItem help = new TranslatedMenuItem(
                "help", "help", (MouseEvent) -> new InfoDialog(menu));
        menu.add(help);

        JMenuItem author = new TranslatedMenuItem(
                "author", "monkey", (MouseEvent) -> new AuthorDialog(menu));
        menu.add(author);
        
        return menu;
    }
}
