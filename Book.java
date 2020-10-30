package com.LibraryManagementSystem;

class Author {
	private String fname, lname;

	Author(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}

	void setFname(String fname) {
		this.fname = fname;
	}

	void setLname(String lname) {
		this.lname = lname;
	}

	String getLname() {
		return lname;
	}

	String getFname() {
		return fname;
	}

	@Override
	public String toString() {
		return fname + " " + lname;
	}
}

public class Book {
	private Author author;
//	private String fname;
//	private String lname;
	private String title;
	private int releasedYear;
	private int bookId;
	private int stock_quantity;
	private int pages;

	Book(String fname, String lname, String title, int releasedYear, int bookId, int stock_quantity, int pages) {
		super();
		author=new Author(fname, lname);
//		this.fname = fname;
//		this.lname = lname;
		this.title = title;
		this.releasedYear = releasedYear;
		this.bookId = bookId;
		this.stock_quantity = stock_quantity;
		this.pages = pages;
	}

	public String getFname() {
		return author.getFname();
	}

	public String getLname() {
		return author.getLname();
	}

	public String getTitle() {
		return title;
	}

	public int getReleasedYear() {
		return releasedYear;
	}

	public int getBookId() {
		return bookId;
	}

	public int getStock_quantity() {
		return stock_quantity;
	}

	public int getPages() {
		return pages;
	}

	public void setFname(String fname) {
		this.author.setFname(fname);
	}

	public void setLname(String lname) {
		this.author.setLname(lname);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

}
