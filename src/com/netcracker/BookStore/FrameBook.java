package com.netcracker.BookStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

public class FrameBook extends JFrame {
    private List<Author> auths =  new ArrayList<>();
    private Book newBook;
    private int countFrame = 1;
    public FrameBook() {
        super("  Add Book:  ");
        setSize(400, 500);
        setLocation(150, 100);
        JPanel Panel = new JPanel(new GridLayout(9,2,0,0));
        JPanel jPanel1 = new JPanel(new GridLayout(1,2,47,0));
        JLabel lb1 = new JLabel("Authors:");
        JButton button = new JButton("Add Author");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameAuthor auth = new FrameAuthor();
                auth.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowDeactivated(WindowEvent e) {
                        //super.windowClosing(e);
                        if(auth.getCountFrame() == 0) {
                            auths.add(auth.author);
                            auth.dispose();
                            countFrame--;
                        }
                    }
                });
            }
        });
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(button);
        panel.add(jPanel1);
        Panel.add(panel);
        ///////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,8,0));
        lb1 = new JLabel("Title:");
        JTextField title = new JTextField(12);
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(title);
        panel.add(jPanel1);
        Panel.add(panel);
        //////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,0,0));
        lb1 = new JLabel("Genre:");
        Genre[] arr = {Genre.ActionAndAdventure,Genre.Drama,Genre.Horror,Genre.Mystery,Genre.Romance,Genre.Satire,Genre.ScienceFiction};
        JComboBox<Genre> genreJComboBox = new JComboBox<>(arr);
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(genreJComboBox);
        panel.add(jPanel1);
        Panel.add(panel);
        /////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,8,0));
        lb1 = new JLabel("Year:");
        JTextField year = new JTextField("YYYY",10);
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(year);
        panel.add(jPanel1);
        Panel.add(panel);
        /////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,8,0));
        lb1 = new JLabel("Publish office:");
        JTextField office = new JTextField(12);
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(office);
        panel.add(jPanel1);
        Panel.add(panel);
        ///////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,8,0));
        lb1 = new JLabel("Qty:");
        JTextField qty = new JTextField(12);
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(qty);
        panel.add(jPanel1);
        Panel.add(panel);
        ///////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,8,0));
        lb1 = new JLabel("Price:");
        JTextField price = new JTextField(12);
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(price);
        panel.add(jPanel1);
        Panel.add(panel);
        ////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,72,0));
        lb1 = new JLabel("Age Require:");
        String[] age = {"0+", "6+", "12+", "16+", "18+"};
        JComboBox<String> ageJComboBox = new JComboBox<>(age);
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(ageJComboBox);
        panel.add(jPanel1);
        Panel.add(panel);
        //////////////////////////////////////////////////////////////////////////
        JButton btn = new JButton("Submit");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(auths.size() == 0){
                    JOptionPane.showMessageDialog(btn,"You have't added any authors");
                }
                else {
                    GregorianCalendar date = new GregorianCalendar();
                    int currYear = date.get(Calendar.YEAR);
                    int yearForm =Integer.parseInt(year.getText());
                    if(( yearForm <= currYear) && (yearForm > 1200) ) {
                        newBook = new Book(auths, title.getText(), (Genre)genreJComboBox.getSelectedItem(),year.getText(),
                                office.getText(),Integer.parseInt(qty.getText()),Double.parseDouble(price.getText()),
                                (String)ageJComboBox.getSelectedItem());
                        setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(btn, "You entered an incorrect year");
                    }
                }
            }
        });
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(btn);
        Panel.add(panel);
        add(Panel);
        setVisible(true);
    }

    public Book getNewBook() {
        return newBook;
    }

    public int getCountFrame() {
        return countFrame;
    }
}
