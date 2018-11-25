package com.netcracker.BookStore;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.GregorianCalendar;

public class Swing extends JFrame {
    public Swing(){
    super("Books Store");
    setSize(500, 500);
    setLocation(150, 100);
    setDefaultCloseOperation( EXIT_ON_CLOSE );
    JButton add = new JButton("Add");
    JButton remove = new JButton("Remove");
    JButton edit = new JButton("Edit");
    JButton save = new JButton("Save");
    List<Author> authors = new ArrayList<>();
    authors.add(new Author("Dan Braun" , "sismos@julvar", 'm'));
    Book first = new Book(authors,"Inferno",Genre.ActionAndAdventure,400);
    BookModel m = new BookModel();
    for(int i=0;i<100;i++) {
        m.addBook(first);
        first = new Book(authors,"Inferno" + i,Genre.ActionAndAdventure,400);
    }
    JTable table = new JTable(m);
    JPanel panel = new JPanel(new GridLayout(1,2,17,0));
    panel.add(add);
    panel.add(remove);
    JPanel panel2 = new JPanel(new GridLayout(1,2,17,0));
    panel2.add(edit);
    panel2.add(save);

    JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel1.add(panel);
    JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    panel3.add(panel2);
    JPanel panell = new JPanel(new GridLayout(1,2,0,0));
    panell.add(panel1);
    panell.add(panel3);
    add(panell,BorderLayout.SOUTH);
    remove.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selectedRows = table.getSelectedRows();
            BookModel model = (BookModel) table.getModel();
                    for(int i = selectedRows.length - 1; i >= 0; i--) {
                        int selIndex = selectedRows[i];
                        m.removeBook(model.getBook(selIndex));
                    }
            table.updateUI();
        }
    });


    add.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            FrameBook book = new FrameBook();
            book.addWindowListener(new WindowAdapter() {

                @Override
                public void windowDeactivated(WindowEvent e) {
                    //super.windowClosing(e);
                    if(book.getCountFrame() == 0) {
                        m.addBook(book.getNewBook());
                        book.dispose();
                    }
                }
            });

        }
    });



    JScrollPane jScrollPane = new JScrollPane(table);
    add(jScrollPane,BorderLayout.CENTER);
    setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Swing();
            }
        });
    }
}
