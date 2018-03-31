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
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.lang.NumberFormatException;
import java.util.function.Consumer;

import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.awt.Point;

import com.mati365.calc.utils.ClickMouseListener;

import com.mati365.calc.utils.Resources;
import com.mati365.calc.logic.SheetLogic;

/** 
 * Component that allows user to change data in cells 
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class CellCoordinatePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private SheetLogic logic;
    private JTextField number = new JTextField();
    private JSpinner row;
    private JSpinner col;

    public CellCoordinatePanel(@NotNull SheetLogic logic) {
        this.logic = logic;    
        setLayout(new GridLayout(1, 7));
        
        row = new JSpinner(
                new SpinnerNumberModel(
                    0, 0, 
                    logic.getReducer().getState().matrix.getWidth() - 1, 1));

        add(new JLabel(
                    Resources.Translations.getString("row"),
                    SwingConstants.RIGHT));
        add(row);

        col = new JSpinner(
                new SpinnerNumberModel(
                    0, 0, 
                    logic.getReducer().getState().matrix.getHeight() - 1, 1));

        add(new JLabel(
                    Resources.Translations.getString("col"),
                    SwingConstants.RIGHT));
        add(col);

        number = new JTextField();
        number.setColumns(8);
        
        add(number);
        add(this.getEnterButton());
        add(this.getFillButton());
    }
    
    private void safeValueOperator(Consumer<Float> fn) { 
        try {
            Float value = Float.parseFloat(number.getText());
            fn.accept(value);
        } catch(NumberFormatException exception) {
            JOptionPane.showMessageDialog(
                null,
                Resources.Translations.getString("incorrect_number_format"),
                Resources.Translations.getString("error"),
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private JButton getFillButton() {
        JButton btn = new JButton(Resources.Translations.getString("fill")); 
        btn.addMouseListener((ClickMouseListener) (MouseEvent e) -> {
            safeValueOperator(logic::fill);
        });
        return btn;
    }

    private JButton getEnterButton() { 
        JButton btn = new JButton(Resources.Translations.getString("enter"));
        btn.addMouseListener((ClickMouseListener) (MouseEvent e) -> {
            safeValueOperator((value) -> {
                logic.load(
                        new Point(
                            (Integer)col.getValue(),
                            (Integer)row.getValue()),
                        value);
            });    
        });
        return btn;
    }
}
