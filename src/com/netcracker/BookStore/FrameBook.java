package com.netcracker.BookStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

public class FrameBook extends JFrame {
    private Author auths;
    private Book newBook;
    private int countFrame = 1;
    public FrameBook(String head, Book currBook) {
        super(head);
        setSize(450, 500);
        setLocation(150, 100);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JPanel Panel = new JPanel(new GridLayout(8,2,0,10));
        JPanel jPanel1 = new JPanel(new GridLayout(1,2,10,0));
        JLabel lb1 = new JLabel("Authors:       ");
        JButton button = new JButton("Add");
        JButton remove = new JButton("Remove");
        if(currBook.getAuthors() != null){
            auths = currBook.getAuthors();
        }
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(auths != null){
                    JOptionPane.showMessageDialog(button,"Author already added");
                }
                else {
                    FrameAuthor auth = new FrameAuthor();
                    auth.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowDeactivated(WindowEvent e) {
                            //super.windowClosing(e);

                            if (auth.getCountFrame() == 0) {
                                auths = auth.author;
                                auth.setVisible(false);
                                // countFrame--;
                            }
                        }
                        @Override
                        public void windowClosing(WindowEvent e){
                            int result = JOptionPane.showConfirmDialog(auth,"Are you sure you want to close this window? " +
                                    "All unsaved data will be lost","Closing",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                            if(result == JOptionPane.YES_OPTION){
                                auth.setVisible(false);
                            }
                        }
                    });
                }
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(auths != null){
                    auths = null;
                }
                else{
                    message(remove,"authors");
                }
            }
        });

        jPanel1.add(button);
        jPanel1.add(remove);
        addRow(Panel,lb1,jPanel1);
        ///////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,0,0));
        lb1 = new JLabel("Title:         ");
        JTextField title = new JTextField(currBook.getTitle(),12);
        addRow(Panel,lb1,title);
        //////////////////////////////////////////////////////////////////////////
        lb1 = new JLabel("Genre:         ");
        Genre[] arr = {Genre.ActionAndAdventure,Genre.Drama,Genre.Horror,Genre.Mystery,Genre.Romance,Genre.Satire,Genre.ScienceFiction};
        JComboBox<Genre> genreJComboBox = new JComboBox<>(arr);
        genreJComboBox.setSelectedItem(currBook.getGenre());
        addRow(Panel,lb1,genreJComboBox);
        /////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,0,0));
        lb1 = new JLabel("Year:          ");
        JTextField year = new JTextField(currBook.getYear(),12);
        addRow(Panel,lb1,year);
        /////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,0,0));
        lb1 = new JLabel("Publish office:");
        JTextField office = new JTextField(currBook.getPublishOffice(),12);
        addRow(Panel,lb1,office);
        ///////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,0,0));
        lb1 = new JLabel("Qty:           ");
        JTextField qty = new JTextField(Integer.toString(currBook.getQty()),12);
        addRow(Panel,lb1,qty);
        ///////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,0,0));
        lb1 = new JLabel("Price:         ");
        JTextField price = new JTextField(Double.toString(currBook.getPrice()),12);
        addRow(Panel,lb1,price);
        ////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,0,0));
        lb1 = new JLabel("Age Require:   ");
        String[] age = {"0+", "6+", "12+", "16+", "18+"};
        JComboBox<String> ageJComboBox = new JComboBox<>(age);
        ageJComboBox.setSelectedItem(currBook.getAgeRequire());
        addRow(Panel,lb1,ageJComboBox);
        //////////////////////////////////////////////////////////////////////////
        JButton btn = new JButton("Submit");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(auths != null){
                    if(isValidYear(year.getText())) {
                        if (isValidQty(qty.getText())) {
                            if(isValidPrice(price.getText())) {
                                newBook = new Book(auths, title.getText(), (Genre) genreJComboBox.getSelectedItem(),office.getText(),
                                        year.getText(), Integer.parseInt(qty.getText()), Double.parseDouble(price.getText()),
                                        (String) ageJComboBox.getSelectedItem());
                                countFrame--;
                                setVisible(false);

                            }
                            else{
                                message(btn,"price");
                            }
                        }
                        else{
                            message(btn,"qty");
                        }
                    }
                    else{
                        message(btn,"year");
                    }
                }
                else {
                    message(btn,"authors");
                }
            }
        });

        JPanel Panel1 = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(btn);

        Panel1.add(Panel,BorderLayout.CENTER);
        Panel1.add(panel,BorderLayout.SOUTH);
        add(Panel1);
        setVisible(true);
    }

    private void message(JComponent comp,String str){
        countFrame++;
        if("authors".equals(str)){
            JOptionPane.showMessageDialog(comp,"You have't added any authors");
        }
        else {
            JOptionPane.showMessageDialog(comp, "You entered an incorrect " + str);
        }
        countFrame--;
    }

    private boolean isValidYear(String str){
        GregorianCalendar date = new GregorianCalendar();
        int currYear = date.get(Calendar.YEAR);
        try {
            int yearForm = Integer.parseInt(str);
            if(( yearForm <= currYear) && (yearForm > 1200) ){
                return true;
            }
            else{
                return false;
            }
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    private boolean isValidQty(String str){
        try {
            int qty = Integer.parseInt(str);
            if(qty > 0){
                return true;
            }
            else{
                return false;
            }
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    private boolean isValidPrice(String str){
        try {
            double price = Double.parseDouble(str);
            if(price > 0){
                return true;
            }
            else{
                return false;
            }
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public Book getNewBook() {
        return newBook;
    }

    public int getCountFrame() {
        return countFrame;
    }

    private void addRow(JPanel Panel,Component Label, Component other){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(Label);
        Panel.add(panel);
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(other);
        Panel.add(panel);
    }
}
