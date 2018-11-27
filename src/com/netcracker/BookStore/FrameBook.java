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
    public FrameBook(String head, Book currBook) {
        super(head);
        setSize(450, 500);
        setLocation(150, 100);
        JPanel Panel = new JPanel(new GridLayout(9,2,0,0));
        JPanel jPanel1 = new JPanel(new GridLayout(1,3,16,0));
        JLabel lb1 = new JLabel("Authors:");
        JButton button = new JButton("Add Author");
        JButton remove = new JButton("Remove Authors");
        if(currBook.getAuthors() != null){
            auths = currBook.getAuthors();
        }
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
                           // countFrame--;
                        }
                    }
                });
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(auths.size() !=0){
                    auths.clear();
                }
                else{
                    message(remove,"authors");
                }
            }
        });
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(button);
        jPanel1.add(remove);
        panel.add(jPanel1);
        Panel.add(panel);
        ///////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,8,0));
        lb1 = new JLabel("Title:");
        JTextField title = new JTextField(currBook.getTitle(),12);
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
        genreJComboBox.setSelectedItem(currBook.getGenre());
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(genreJComboBox);
        panel.add(jPanel1);
        Panel.add(panel);
        /////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,30,0));
        lb1 = new JLabel("Year:");
        JTextField year = new JTextField(currBook.getYear(),10);
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(year);
        panel.add(jPanel1);
        Panel.add(panel);
        /////////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,8,0));
        lb1 = new JLabel("Publish office:");
        JTextField office = new JTextField(currBook.getPublishOffice(),12);
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(office);
        panel.add(jPanel1);
        Panel.add(panel);
        ///////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,8,0));
        lb1 = new JLabel("Qty:");
        JTextField qty = new JTextField(Integer.toString(currBook.getQty()),12);
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(lb1);
        jPanel1.add(qty);
        panel.add(jPanel1);
        Panel.add(panel);
        ///////////////////////////////////////////////////////////////////////
        jPanel1 = new JPanel(new GridLayout(1,2,8,0));
        lb1 = new JLabel("Price:");
        JTextField price = new JTextField(Double.toString(currBook.getPrice()),12);
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
        ageJComboBox.setSelectedItem(currBook.getAgeRequire());
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
                if(auths.size() != 0){
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
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(btn);
        Panel.add(panel);
        add(Panel);
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
}
