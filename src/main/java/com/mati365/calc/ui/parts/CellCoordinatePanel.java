/* file name  : src/main/java/com/mati365/calc/ui/parts/CellCoordinatePanel.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : ptk 23 mar 23:26:59 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import net.miginfocom.swing.MigLayout;
import javax.validation.constraints.NotNull;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import java.util.function.Consumer;

import java.awt.event.MouseEvent;
import java.awt.Point;

import com.mati365.calc.utils.*;
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
        setLayout(new MigLayout("inset 10 10 5 10 10"));

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
    
    private JButton getFillButton() {
        IconButton btn = new IconButton(
                "fill",
                Resources.Translations.getString("fill"),
                (ClickMouseListener) (e) -> {
                    MessageUtils.messagedNumberParse(
                            number.getText(), 
                            logic::fill);
                }); 
        return btn;
    }

    private JButton getEnterButton() { 
        IconButton btn = new IconButton( 
                "enter", 
                Resources.Translations.getString("enter"), 
                (ClickMouseListener) (e) -> {
                    MessageUtils.messagedNumberParse(number.getText(), (value) -> {
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
