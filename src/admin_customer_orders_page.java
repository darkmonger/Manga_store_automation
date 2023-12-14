import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.sql.Date;
import javax.swing.JLabel;

public class admin_customer_orders_page {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_customer_orders_page window = new admin_customer_orders_page();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public admin_customer_orders_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1086, 743);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JTable table = new JTable();
		JScrollPane scrollPane1 = new JScrollPane(table);
		scrollPane1.setBounds(67, 158, 504, 366);
		frame.getContentPane().add(scrollPane1);

		JTable table2 = new JTable();
		JScrollPane scrollPane2 = new JScrollPane(table2); // Corrected: Use table2 instead of table
		scrollPane2.setBounds(600, 158, 462, 366);
		frame.getContentPane().add(scrollPane2);

		JButton connect_db_button = new JButton("Connect to database");
		connect_db_button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
		            String sql = "SELECT order_id, customer_id, order_date, total_amount FROM orders";
		            PreparedStatement statement = con.prepareStatement(sql);
		            ResultSet resultSet = statement.executeQuery();

		            DefaultTableModel tableModel = new DefaultTableModel();
		            tableModel.addColumn("Order ID");
		            tableModel.addColumn("Customer ID");
		            tableModel.addColumn("Order Date");
		            tableModel.addColumn("Total Amount");

		            while (resultSet.next()) {
		                int orderId = resultSet.getInt("order_id");
		                int customerId = resultSet.getInt("customer_id");
		                Date orderDate = resultSet.getDate("order_date");
		                double totalAmount = resultSet.getDouble("total_amount");

		                Object[] rowData = {orderId, customerId, orderDate, totalAmount};
		                tableModel.addRow(rowData);
		            }

		            table.setModel(tableModel);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(frame, "Error retrieving order details.", "Error", JOptionPane.ERROR_MESSAGE);
		        }


		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
		            String sql = "SELECT order_item_id, order_id, manga_id, quantity, price FROM order_items";

		            PreparedStatement statement = con.prepareStatement(sql);
		            ResultSet resultSet = statement.executeQuery();

		            DefaultTableModel tableModel2 = new DefaultTableModel();
		            tableModel2.addColumn("Order Item ID");
		            tableModel2.addColumn("Order ID");
		            tableModel2.addColumn("Manga ID");
		            tableModel2.addColumn("Quantity");
		            tableModel2.addColumn("Price");

		            while (resultSet.next()) {
		                int orderItemId = resultSet.getInt("order_item_id");
		                int orderId = resultSet.getInt("order_id");
		                int mangaId = resultSet.getInt("manga_id");
		                int quantity = resultSet.getInt("quantity");
		                double price = resultSet.getDouble("price");

		                Object[] rowData = {orderItemId, orderId, mangaId, quantity, price};
		                tableModel2.addRow(rowData);
		            }

		            table2.setModel(tableModel2);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(frame, "Error retrieving order item details.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		connect_db_button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		connect_db_button.setBounds(356, 546, 217, 43);
		frame.getContentPane().add(connect_db_button);
		
		JLabel lblNewLabel_1_2 = new JLabel("Manga Odyssey\r\n");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 49));
		lblNewLabel_1_2.setBounds(10, 10, 439, 59);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblAdminMangaPage = new JLabel("Admin  manga page");
		lblAdminMangaPage.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAdminMangaPage.setBounds(10, 66, 275, 82);
		frame.getContentPane().add(lblAdminMangaPage);
		lblAdminMangaPage.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 Admin_manga_page adminmanga = new Admin_manga_page();
					adminmanga.frame.setVisible(true);
					frame.dispose();
			    }
			});
		
		
		JLabel lblCustomerInfoPage = new JLabel("Customer info page");
		lblCustomerInfoPage.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCustomerInfoPage.setBounds(321, 66, 275, 82);
		frame.getContentPane().add(lblCustomerInfoPage);
		lblCustomerInfoPage.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 Admin_customer_info_page admincustomer = new Admin_customer_info_page();
					admincustomer.frame.setVisible(true);
					frame.dispose();
			    }
			});
		
		JLabel lblCustomerOrdersPage = new JLabel("Customer orders page");
		lblCustomerOrdersPage.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCustomerOrdersPage.setBounds(643, 66, 275, 82);
		frame.getContentPane().add(lblCustomerOrdersPage);
		lblCustomerOrdersPage.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 admin_customer_orders_page adminorder = new admin_customer_orders_page();
					adminorder.frame.setVisible(true);
					frame.dispose();
			    }
			});
		
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin_main_page admin_main = new  Admin_main_page();
	        	admin_main.frame.setVisible(true);
	        	frame.dispose();
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack.setBounds(67, 546, 130, 43);
		frame.getContentPane().add(btnGoBack);
	}

}
