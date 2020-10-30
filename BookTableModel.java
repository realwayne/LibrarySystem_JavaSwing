package com.LibraryManagementSystem;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BookTableModel extends AbstractTableModel {
	private static final int FNAME = 0;
	private static final int LNAME = 1;
	private static final int TITLE = 2;
	private static final int BOOK_ID = 3;
	private static final int STOCK_QUANTITY = 4;
	private static final int RELEASED_YEAR = 5;
	private static final int PAGES = 6;

	private String[] colNames = { "FNAME", "LNAME", "TITLE", "BOOK_ID", "STOCK_QUANTITY", "RELEASED_YEAR", "PAGES" };
	private List<Book> books;

	public BookTableModel(List<Book> books) {
		this.books = books;
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public String getColumnName(int index) {
		return colNames[index];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book tempBook = books.get(rowIndex);
		switch (columnIndex) {
		case LNAME:
			return tempBook.getFname();
		case FNAME:
			return tempBook.getLname();
		case TITLE:
			return tempBook.getTitle();
		case BOOK_ID:
			return tempBook.getBookId();
		case STOCK_QUANTITY:
			return tempBook.getStock_quantity();
		case RELEASED_YEAR:
			return tempBook.getReleasedYear();
		default:
			return tempBook.getPages();
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

}
