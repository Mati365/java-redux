/* file name  : src/main/java/com/mati365/calc/ui/Window.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : czw 22 mar 18:14:41 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.Color;
import java.awt.Dimension;

import com.mati365.calc.logic.Resources;

/** 
 * Main app class, opens window
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class AppWindow {
    private static final Dimension WINDOW_SIZE = new Dimension(600, 500);

    /** 
     * Creates blank window for APP 
     */
    static JFrame createWindow() {
        JFrame window = new JFrame(Resources.Translations.getString("app_name"));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(AppWindow.WINDOW_SIZE);
        window.setContentPane(new CalcPanel().getDefaultPanel());
        window.pack();
        window.setLocationRelativeTo(null);
        return window;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                UIManager.put("Table.gridColor", new ColorUIResource(Color.gray));
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                AppWindow
                    .createWindow()
                    .setVisible(true);
            }
        });
    }
}
