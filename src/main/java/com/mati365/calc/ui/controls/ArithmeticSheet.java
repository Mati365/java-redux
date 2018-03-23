
/* file name  : src/main/java/com/mati365/calc/ui/CalcPanel.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : czw 22 mar 19:17:24 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;

import javax.swing.table.AbstractTableModel;
import javax.validation.constraints.NotNull;

import com.mati365.calc.logic.SheetLogic;
import com.mati365.calc.utils.Matrix;

/**
 * UI representation of SheetLogic
 *
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class ArithmeticSheet { 
    private JTable table = null;
    private SheetLogic logic = null;

    public ArithmeticSheet(@NotNull SheetLogic logic) {
        this.logic = logic;
        this.table = new JTable(
                ArithmeticSheet.getMatrixAbstractModel(logic.getState().matrix)) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, col);
                c.setHorizontalAlignment(JLabel.RIGHT);
                return c;
            }
        };

        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
    }

    public SheetLogic getLogic()        { return this.logic; }
    public JTable getTable()            { return this.table; }
     
    /** 
     * Creates matrix abstract model linked to index 
     * 
     * @return 
     */
    private static AbstractTableModel getMatrixAbstractModel(@NotNull Matrix<Float> matrix) { 
        return new AbstractTableModel() {
            @Override
            public int getColumnCount() { return matrix.getWidth(); }
            
            @Override
            public int getRowCount() { return matrix.getHeight(); }
            
            @Override
            public boolean isCellEditable(int row, int cell) { return false; }

            @Override
            public Object getValueAt(int row, int col) {
                return matrix.getCellAt(new Point(col, row)); 
            }
        };    
    }
}
