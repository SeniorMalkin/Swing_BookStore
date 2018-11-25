package com.netcracker.BookStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum gender {male,female};
public class FrameAuthor extends JFrame {
    Author author;
    int countFrame = 1;
    public FrameAuthor(){
        super("  Author:  ");
        setSize(350, 250);
        setLocation(150, 100);
        JPanel Panel = new JPanel(new GridLayout(4,2,0,0));
        JPanel jPanel1 = new JPanel(new GridLayout(1,2,0,0));
        JLabel lb1 = new JLabel("Name:");
        JTextField text1 = new JTextField(12);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(text1);
        panel.add(jPanel1);
        Panel.add(panel);
        ///////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,0,0));
        lb1 = new JLabel("Email:");
        JTextField text2 = new JTextField(12);
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(text2);
        panel.add(jPanel1);
        Panel.add(panel);
        //////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,68,0));
        lb1 = new JLabel("Gender:");

        gender[] arr = {gender.male,gender.female};
        JComboBox<gender> comboBox = new JComboBox<>(arr);
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(comboBox);
        panel.add(jPanel1);
        Panel.add(panel);
        ///////////////////////////////////////////////////////////////////////////////
        JButton btn = new JButton("Add");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String val = text2.getText();
                int count = 0;
                for (char c: val.toCharArray()
                     ) {
                    if(c == '@'){
                        count++;
                    }
                }
                char sex;
                if(gender.male == comboBox.getSelectedItem()) {
                    sex = 'm';
                }
                else {
                    sex = 'f';
                }
                if(count == 1) {
                    author = new Author(text1.getText(),val,sex);
                    countFrame--;
                    setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(btn,"You entered an incorrect email");
                }
            }
        });
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(btn);
        Panel.add(panel);
        add(Panel);
        setVisible(true);
    }

    public int getCountFrame() {
        return countFrame;
    }
}
