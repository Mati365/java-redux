/* file name  : src/main/java/com/mati365/calc/ui/CalcPanel.java
 * authors    : Mateusz Bagiński (cziken58@gmail.com)
 * created    : czw 22 mar 19:17:24 2018
 * copyright  : MIT
 *
 * modifications:
 *
 */
package com.mati365.calc.ui;

import net.miginfocom.swing.MigLayout;
import javax.validation.constraints.NotNull;
import javax.swing.event.ListSelectionEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JList;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import com.mati365.calc.logic.*;
import com.mati365.calc.utils.HiddenValueItem;
import com.mati365.calc.utils.Resources;

/** 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
class ExpressionPanel extends Logicable<SheetLogic> { 
    private static final HiddenValueItem[] OPERATIONS = {
        new HiddenValueItem(
                Resources.Translations.getString("elements_sum"),
                SheetReducer.SUM_MATRIX),
        
        new HiddenValueItem(
                Resources.Translations.getString("elements_avg"),
                SheetReducer.AVG_MATRIX),

        new HiddenValueItem(
                Resources.Translations.getString("elements_max_min"),
                SheetReducer.MIN_MAX_MATRIX)
    };
    
    public ExpressionPanel(@NotNull SheetLogic logic) { super(logic); }
    
    private JTextArea getLinkedExpressionInput() { 
        JTextArea input = new JTextArea(3, 10);
        input.setEditable(false);
        input.setBorder(
                BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(),
                    Resources.Translations.getString("expression_value")));

        logic
            .getReducer()
            .subscribe((ArithmeticAction action, ArithmeticState state) -> { 
                input.setText(state.operationResult);
            });
        return input; 
    }

    public JPanel getPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));

        JList<HiddenValueItem> operations = new JList<>(ExpressionPanel.OPERATIONS);
        operations.setSelectedIndex(1);
        operations.addListSelectionListener((ListSelectionEvent e) -> {
            logic.operation(operations.getSelectedValue().getValue()); 
        });

        panel.add(operations);
        panel.add(this.getLinkedExpressionInput());

        return panel;
    }
}

/** 
 * Main app layer that contains sheet  
 * 
 * @author Mateusz Bagiński (cziken58@gmail.com)
 */
public class CalcPanel extends Logicable<SheetLogic> {
    public CalcPanel(@NotNull SheetLogic logic) {
        super(logic);
    }

    private JPanel getCalcContentPanel() {
        JPanel panel = new JPanel(new MigLayout("insets 0 10 10 10",  "[grow]", "[top]"));
        panel.add(
                new JScrollPane(new ArithmeticSheet(this.logic).getTable()),
                "growx");

        return panel;
    }
    
    public JPanel getEvaluationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new CellCoordinatePanel(this.logic), BorderLayout.NORTH);
        panel.add(this.getCalcContentPanel(), BorderLayout.CENTER);
        
        panel.add(
                new ExpressionPanel(this.logic).getPanel(), 
                BorderLayout.SOUTH);
        return panel;
    }

    public JPanel getDefaultPanel() {
        JPanel panel = new JPanel(new BorderLayout()); 
        panel.add(
                new AppToolBar(CalcPanel.this.logic).getToolBar(),
                BorderLayout.NORTH);
        
        panel.add(
                this.getEvaluationPanel(), 
                BorderLayout.CENTER);
        return panel;
    }
}
