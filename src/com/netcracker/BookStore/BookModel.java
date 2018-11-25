package com.netcracker.BookStore;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BookModel extends AbstractTableModel implements TableModel {
    private List<Book> books= new LinkedList<>();
    private List<String> columns = new ArrayList<>();
    private String[] columnsName = {"Authors",
        "Title",
        "Genre",
        "Publish Office",
        "Year",
        "Qty",
        "Price",
        "AgeRequire"};

    private int columnsCount = 8;

    public BookModel() {
        columns.addAll(Arrays.asList(columnsName));
    }

    public BookModel(Book book){
        columns.addAll(Arrays.asList(columnsName));
        books.add(book);
    }

    public BookModel(List<Book> list){
        columns.addAll(Arrays.asList(columnsName));
        books.addAll(list);
    }

    public void addBook(Book book){
        books.add(book);
        fireTableDataChanged();
    }

    public void removeBook(Book book){
        books.remove(book);
        fireTableDataChanged();
    }

    public void editBook(Book newBook,Book oldBook){
        books.remove(oldBook);
        books.add(newBook);
        fireTableDataChanged();
    }

    Book getBook(int rowInd){
        Book res = books.get(rowInd);
        return res;
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Book book = books.get(0);
        Class<?> result = null;
        switch(columnIndex){
            case 0:
                result = book.getAuthors().getClass();
                break;
            case 1:
                result = book.getTitle().getClass();
                break;
            case 2:
                result = book.getGenre().getClass();
                break;
            case 3:
                result = book.getPublishOffice().getClass();
                break;
            case 4:
                result = book.getYear().getClass();
                break;
            case 5:
            case 6:
                result = Integer.class;
                break;
            case 7:
                result = book.getAgeRequire().getClass();
                break;
        }
        return result;
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column);
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return columnsCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            Book book = books.get(rowIndex);
            switch(columnIndex){
                case 0:
                    return book.getAuthors();
                case 1:
                    return book.getTitle();
                case 2:
                    return book.getGenre();
                case 3:
                    return book.getPublishOffice();
                case 4:
                    return book.getYear();
                case 5:
                    return book.getQty();
                case 6:
                    return book.getPrice();
                case 7:
                    return book.getAgeRequire();
            }
        return null;
    }
}
