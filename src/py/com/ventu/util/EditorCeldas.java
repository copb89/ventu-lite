package py.com.ventu.util;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author cpatino Ultima Modificacion: 22/02/2015
 *
 * Clase que sobrescribe los metodos del JTable.
 */
public class EditorCeldas extends DefaultCellEditor {

    private boolean keyTriggered;

    public EditorCeldas() {
        super(new JFormattedTextField());
        final JFormattedTextField textField = (JFormattedTextField) getComponent();
        textField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (!keyTriggered) {
                            textField.selectAll();
                        }
                    }
                });
            }
        });
    }

    public void setKeyTriggered(boolean keyTriggered) {
        this.keyTriggered = keyTriggered;
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {
        final JFormattedTextField textField = (JFormattedTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
        textField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        textField.selectAll();
        return textField;
    }

}
