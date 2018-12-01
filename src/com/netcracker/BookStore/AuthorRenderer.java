package com.netcracker.BookStore;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class AuthorRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        Author objectCell = (Author)value;
        JLabel label = new JLabel(objectCell.getName() + " \n " + objectCell.getEmail());
        if(objectCell.getGender() == 'm'){
            label.setIcon(new ImageIcon("user-male.png"));
        }
        else{
            label.setIcon(new ImageIcon("user-female.png"));
        }
        return label;
    }
}
