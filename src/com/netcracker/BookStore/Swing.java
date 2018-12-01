package com.netcracker.BookStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;


public class Swing extends JFrame {
    public Swing() {
        super("Books Store");
        try {
            setSize(500, 500);
            setLocation(150, 100);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            JButton add = new JButton("Add");
            JButton remove = new JButton("Remove");
            JButton edit = new JButton("Edit");
            JButton save = new JButton("Save");

            FileInputStream fis = new FileInputStream("BookStore.dat");
            ObjectInputStream in = new ObjectInputStream(fis);
            BookModel m = (BookModel)in.readObject();

            JTable table = new JTable(m);
            table.setDefaultRenderer(Author.class,new AuthorRenderer());
            table.setRowHeight(30);
            JPanel panel = new JPanel(new GridLayout(1, 2, 17, 0));
            panel.add(add);
            panel.add(remove);
            JPanel panel2 = new JPanel(new GridLayout(1, 2, 17, 0));
            panel2.add(edit);
            panel2.add(save);

            JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel1.add(panel);
            JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panel3.add(panel2);
            JPanel panell = new JPanel(new GridLayout(1, 2, 0, 0));
            panell.add(panel1);
            panell.add(panel3);
            add(panell, BorderLayout.SOUTH);


            remove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int[] selectedRows = table.getSelectedRows();
                    BookModel model = (BookModel) table.getModel();
                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        int selIndex = selectedRows[i];
                        m.removeBook(model.getBook(selIndex));
                    }
                    table.updateUI();
                }
            });

            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Book defBook = new Book(null, " ", Genre.ActionAndAdventure, " ", "YYYY", 0, 0, "0+");
                    FrameBook book = new FrameBook(" Add Book ", defBook);
                    book.addWindowListener(new WindowAdapter() {

                        @Override
                        public void windowDeactivated(WindowEvent e) {
                            //super.windowClosing(e);
                            if (book.getCountFrame() == 0) {
                                m.addBook(book.getNewBook());
                                book.setVisible(false);
                            }
                        }
                        @Override
                        public void windowClosing(WindowEvent e){
                            int result = JOptionPane.showConfirmDialog(book,"Are you sure you want to close this window? " +
                                    "All unsaved data will be lost","Closing",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                            if(result == JOptionPane.YES_OPTION){
                                book.setVisible(false);
                            }
                        }
                    });

                }
            });

            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int[] selectedRows = table.getSelectedRows();
                    if (selectedRows.length != 1) {
                        JOptionPane.showMessageDialog(edit, "Please,select only one row");
                    } else {
                        BookModel model = (BookModel) table.getModel();
                        Book selBook = model.getBook(selectedRows[0]);
                        FrameBook book = new FrameBook(" Edit Book ", selBook);
                        book.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowDeactivated(WindowEvent e) {
                                //super.windowClosing(e);
                                if (book.getCountFrame() == 0) {
                                    m.editBook(book.getNewBook(), selBook);
                                    book.dispose();
                                    table.updateUI();
                                }
                            }
                        });

                    }
                }
            });

            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        FileOutputStream fos = new FileOutputStream("BookStore.dat");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(m);
                        oos.flush();
                        oos.close();
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                }
            });

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e){
                    int result = JOptionPane.showConfirmDialog(table,"Are you sure you want to exit? " +
                            "All unsaved data will be lost","Closing",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if(result == JOptionPane.YES_OPTION){
                        Swing.this.setVisible(false);
                        System.exit(0);
                    }
                }
            });

            JScrollPane jScrollPane = new JScrollPane(table);
            add(jScrollPane, BorderLayout.CENTER);
            setVisible(true);

        } catch ( ClassNotFoundException exp){
            exp.printStackTrace();
        } catch(IOException eof){
            BookModel m = new BookModel();
            try {
                FileOutputStream fos = new FileOutputStream("BookStore.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(m);
                oos.flush();
                oos.close();
                JOptionPane.showMessageDialog(this,"Please,rerun application!");
                System.exit(0);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }



    public static void main(String[] args)  {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new Swing();
            }
        });
    }
}
