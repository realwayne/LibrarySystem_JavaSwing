package com.LibraryManagementSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JLabel clock_lbl;
	private JTable table;
	private BookDAO getBook;
	private JTextField title_tf;
	private JTextField fname_tf;
	private JTextField lname_tf;
	private JTextField stock_tf;
	private JTextField released_tf;
	private JTextField pages_tf;

	public MainFrame() {
		getBook = new BookDAO();
		setTitle("Library Management System");
		startClock();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel top_pnl = new JPanel();
		top_pnl.setPreferredSize(new Dimension(100, 50));
		top_pnl.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		contentPane.add(top_pnl, BorderLayout.NORTH);
		top_pnl.setLayout(new GridLayout(1, 2, 0, 0));

		JLabel app_lbl = new JLabel("Library Management System");
		app_lbl.setFont(new Font("Tahoma", Font.BOLD, 25));
		app_lbl.setBounds(334, 5, 46, 13);
		top_pnl.add(app_lbl);

		clock_lbl = new JLabel("New label");
		clock_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		clock_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		clock_lbl.setBounds(385, 5, 46, 13);
		top_pnl.add(clock_lbl);

		JPanel workSpace_pnl = new JPanel();
		contentPane.add(workSpace_pnl, BorderLayout.CENTER);
		workSpace_pnl.setLayout(new BorderLayout(0, 0));

		JPanel main_pnl = new JPanel();
		main_pnl.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Transactions", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		workSpace_pnl.add(main_pnl);
		main_pnl.setLayout(new BorderLayout(0, 0));

		JPanel operations_pnl = new JPanel();
		operations_pnl.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		operations_pnl.setPreferredSize(new Dimension(250, 100));
		main_pnl.add(operations_pnl, BorderLayout.WEST);

		JButton btnShow = new JButton("Load DB");
		btnShow.setBounds(134, 45, 106, 21);
		btnShow.addActionListener((e) -> {
			refresh();
		});

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(10, 45, 114, 21);
		btnDelete.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (!(selectedRow < 0)) {
				int bookID = (int) table.getValueAt(selectedRow, 3);
				// table.getValueAt(selectedRow, BookTableModel.OBJECT_COL);
				getBook.delete(bookID);
				refresh();
			}
			return;

		});
		operations_pnl.setLayout(null);
		operations_pnl.add(btnDelete);
		operations_pnl.add(btnShow);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 94, 58, 13);
		operations_pnl.add(lblTitle);

		title_tf = new JTextField();
		title_tf.setBounds(78, 91, 162, 19);
		operations_pnl.add(title_tf);
		title_tf.setColumns(10);

		JLabel lblFname = new JLabel("Fname");
		lblFname.setBounds(10, 117, 46, 13);
		operations_pnl.add(lblFname);

		JLabel lblLname = new JLabel("Lname");
		lblLname.setBounds(10, 140, 58, 13);
		operations_pnl.add(lblLname);

		fname_tf = new JTextField();
		fname_tf.setBounds(78, 114, 162, 19);
		operations_pnl.add(fname_tf);
		fname_tf.setColumns(10);

		lname_tf = new JTextField();
		lname_tf.setBounds(78, 137, 162, 19);
		operations_pnl.add(lname_tf);
		lname_tf.setColumns(10);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 163, 58, 13);
		operations_pnl.add(lblStock);

		stock_tf = new JTextField();
		stock_tf.setBounds(78, 160, 162, 19);
		operations_pnl.add(stock_tf);
		stock_tf.setColumns(10);

		JLabel lblReleased = new JLabel("Released");
		lblReleased.setBounds(10, 184, 58, 13);
		operations_pnl.add(lblReleased);

		released_tf = new JTextField();
		released_tf.setBounds(78, 181, 162, 19);
		operations_pnl.add(released_tf);
		released_tf.setColumns(10);

		JLabel lblPages = new JLabel("Pages");
		lblPages.setBounds(10, 209, 46, 13);
		operations_pnl.add(lblPages);

		pages_tf = new JTextField();
		pages_tf.setBounds(78, 206, 162, 19);
		operations_pnl.add(pages_tf);
		pages_tf.setColumns(10);

		JButton add_btn = new JButton("Add");
		add_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = fname_tf.getText();
				String lname = lname_tf.getText();
				String title = title_tf.getText();
				int pages = Integer.parseInt(pages_tf.getText());
				int released_year = Integer.parseInt(released_tf.getText());
				int stock = Integer.parseInt(stock_tf.getText());

				Book bookToAdd = new Book(fname, lname, title, released_year, 0, stock, pages);
				getBook.add(bookToAdd);
				refresh();

			}
		});
		add_btn.setBounds(10, 248, 114, 21);
		operations_pnl.add(add_btn);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				String fname = fname_tf.getText();
				String lname = lname_tf.getText();
				String title = title_tf.getText();
				int pages = Integer.parseInt(pages_tf.getText());
				int released_year = Integer.parseInt(released_tf.getText());
				int stock = Integer.parseInt(stock_tf.getText());
				int book_id = (int) table.getValueAt(selectedRow, 3);
				System.out.println(book_id);
				Book bookToAdd = new Book(fname, lname, title, released_year, book_id, stock, pages);
				getBook.update(bookToAdd);
				refresh();

			}
		});
		btnUpdate.setBounds(129, 248, 111, 21);
		operations_pnl.add(btnUpdate);

		JPanel output_pnl = new JPanel();
		main_pnl.add(output_pnl, BorderLayout.CENTER);
		output_pnl.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				title_tf.setText((String) table.getValueAt(selectedRow, 2));
				fname_tf.setText((String) table.getValueAt(selectedRow, 0));
				lname_tf.setText((String) table.getValueAt(selectedRow, 1));
				stock_tf.setText(String.valueOf((int) table.getValueAt(selectedRow, 4)));
				released_tf.setText(String.valueOf((int) table.getValueAt(selectedRow, 5)));
				pages_tf.setText(String.valueOf((int) table.getValueAt(selectedRow, 6)));

			}
		});
		JScrollPane tableScroll = new JScrollPane(table);
		output_pnl.add(tableScroll, BorderLayout.CENTER);
		this.setVisible(true);
	}

	private void startClock() {
		Thread clock = new Thread() {
			public void run() {
				while (true) {
					Calendar dateTime = new GregorianCalendar();
					int year = dateTime.get(Calendar.YEAR);
					int month = dateTime.get(Calendar.MONTH);
					int day = dateTime.get(Calendar.DAY_OF_MONTH);
					int hour = dateTime.get(Calendar.HOUR);
					int minute = dateTime.get(Calendar.MINUTE);
					int second = dateTime.get(Calendar.SECOND);
					clock_lbl.setText("Date : " + year + "-" + month + "-" + day + " Time : " + hour + "-" + minute
							+ "-" + second);
				}
			}
		};
		clock.start();
	}

	private void refresh() {
		List<Book> books = getBook.getAll();
		BookTableModel bookModel = new BookTableModel(books);
		table.setModel(bookModel);
	}
}
