package com.LibraryManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

	private Connection connection;
	private Statement statement;
	private ResultSet result;
	private List<Book> books;

	public BookDAO() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_shop", "root", "admin");
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Book> getAll() {
		books = new ArrayList<Book>();
		try {
			result = statement.executeQuery("SELECT * FROM books");
//					.executeQuery("SELECT CONCAT(author_fname, ' ', author_lname), title,  released_year FROM books");

			while (result.next()) {
				books.add(new Book(result.getString(3), result.getString(4), result.getString(2), result.getInt(5),
						result.getInt(1), result.getInt(6), result.getInt(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	public boolean add(Book book) {
		try {
			PreparedStatement state = connection.prepareStatement(
					"INSERT INTO books(title, author_fname, author_lname, released_year, stock_quantity, pages ) VALUES(?, ?, ?, ?, ?, ?)");
			state.setString(1, book.getTitle());
			state.setString(2, book.getFname());
			state.setString(3, book.getLname());
			state.setInt(4, book.getReleasedYear());
			state.setInt(5, book.getStock_quantity());
			state.setInt(6, book.getPages());

			state.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean delete(int bookID) {
		try {
//			statement.executeUpdate("DELETE FROM books WHERE id");
			PreparedStatement state = connection.prepareStatement("DELETE FROM books WHERE book_id=?");
			state.setInt(1, bookID);
			state.executeUpdate();
			state.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void update(Book book) {
		int book_id = book.getBookId();
		try {
			PreparedStatement state = connection.prepareStatement(
					"UPDATE books SET title=?, author_fname=?, author_lname=?, released_year=?,stock_quantity=?, pages=?  WHERE book_id=?");

			state.setString(1,  book.getTitle());
			state.setString(2,  book.getFname());
			state.setString(3, book.getLname());
			state.setInt(4, book.getReleasedYear());
			state.setInt(5,  book.getStock_quantity());
			state.setInt(6,  book.getPages());
			state.setInt(7,  book_id);
			state.executeUpdate();
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// s.executeUpdate("UPDATE books SET ")
	}

}
