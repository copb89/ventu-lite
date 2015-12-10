package py.com.ventu.util;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

/**
 * @author cpatino Ultima Modificacion: 22/02/2015
 *
 * Clase que sobrescribe los metodos del JTable.
 */
public class TextFieldCellEditor extends JTextField implements TableCellEditor {

    private CellEditorListener cellEditorListener = null;

    private boolean isInteger = false;
    private Object oldValue;

    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj, boolean isSelected, int row, int column) {

        /* SwingUtilities.invokeLater(new Runnable() {
         public void run() {
         TextFieldCellEditor.this.selectAll();
         }
         });*/
        if (isSelected) {
            this.selectAll();
        }
        return this;
    }

    // Retrieve e dited value
    @Override
    public Object getCellEditorValue() {
         //Verifica que sea de tipo entero y lo formatea o alinea
        
        /*if (isInteger) {
         // Try to convert to integer. If input is invalid, revert.
         try {
         return new Integer(super.getText());
         } catch (NumberFormatException e) {
         return oldValue;
         }
         }*/
        return super.getText();
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject e) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        cellEditorListener.editingStopped(new ChangeEvent(this));
        return true;
    }

    @Override
    public void cancelCellEditing() {
        cellEditorListener.editingCanceled(new ChangeEvent(this));
    }

    @Override
    public void addCellEditorListener(CellEditorListener celleditorlistener) {
        cellEditorListener = celleditorlistener;
    }

    @Override
    public void removeCellEditorListener(CellEditorListener celleditorlistener) {
        if (cellEditorListener == cellEditorListener) {
            cellEditorListener = null;
        }
    }

}
