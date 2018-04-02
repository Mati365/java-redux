
/* file name  : src/main/java/com/mati365/calc/ui/CalcPanel.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : czw 22 mar 19:17:24 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import javax.validation.constraints.NotNull;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.*;

import com.mati365.calc.logic.*;
import com.mati365.calc.utils.*;

/**
 * UI representation of SheetLogic
 *
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class ArithmeticSheet { 
    /** 
     * Default centered table headers
     * 
     * @author Mateusz Bagiński (cziken58@gmail.com)
     */
    private static class HeaderRenderer implements TableCellRenderer {
        DefaultTableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col) {
            return renderer.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, col);
        }
    }

    private JTable table = null;
    private SheetLogic logic = null;

    public ArithmeticSheet(@NotNull SheetLogic logic) {
        this.logic = logic;
        this.table = new JTable(
                ArithmeticSheet.getMatrixAbstractModel(logic)) {
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
        table.setRowSelectionAllowed(false);
        table
            .getTableHeader()
            .setDefaultRenderer(new HeaderRenderer(table));

        mountStateListeners();
    }

    public SheetLogic getLogic()        { return this.logic; }
    public JTable getTable()            { return this.table; }
    
    /** 
     * Add repaint handler to table 
     */
    private void mountStateListeners() {
        logic
            .getReducer()
            .subscribe((ArithmeticAction action, ArithmeticState state) -> {
                this.table.repaint();
            });
    }

    /** 
     * Creates matrix abstract model linked to index 
     * 
     * @return 
     */
    private static AbstractTableModel getMatrixAbstractModel(@NotNull SheetLogic logic) {
        SheetReducer reducer = logic.getReducer();

        return new AbstractTableModel() {
            @Override
            public String getColumnName(int col) {
                return String.valueOf(col + 1);
            }
            
            @Override
            public int getColumnCount() { 
                return reducer.getState().matrix.getWidth(); 
            }
            
            @Override
            public int getRowCount() { 
                return reducer.getState().matrix.getHeight(); 
            }
            
            @Override
            public boolean isCellEditable(int row, int cell) { return true; }
            
            @Override
            public void setValueAt(Object value, int row, int col) {
                MessageUtils.messagedNumberParse(
                        (String) value,
                        (Float parsed) -> {
                            logic.load(new Point(col, row), parsed);
                        });
            }

            @Override
            public Object getValueAt(int row, int col) {
                return reducer
                    .getState()
                    .matrix.getCellAt(new Point(col, row)); 
            }
        };    
    }
}
