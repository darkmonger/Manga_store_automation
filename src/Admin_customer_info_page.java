import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Admin_customer_info_page {

	JFrame frame;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField emailTextField;
	private JTextField phoneTextField;
	private JTextField addressTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_customer_info_page window = new Admin_customer_info_page();
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
	public Admin_customer_info_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1076, 767);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAdminMangaPage = new JLabel("Admin  manga page");
		lblAdminMangaPage.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAdminMangaPage.setBounds(10, 96, 275, 82);
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
		lblCustomerInfoPage.setBounds(322, 96, 275, 82);
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
		lblCustomerOrdersPage.setBounds(638, 96, 275, 82);
		frame.getContentPane().add(lblCustomerOrdersPage);
		lblCustomerOrdersPage.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 admin_customer_orders_page adminorder = new admin_customer_orders_page();
					adminorder.frame.setVisible(true);
					frame.dispose();
			    }
			});
		
		
		JTable table = new JTable();
        JScrollPane scrollPane1 = new JScrollPane(table);
        scrollPane1.setBounds(486, 163, 504, 366);
        frame.getContentPane().add(scrollPane1);
		
		JButton connect_db_button = new JButton("Connect to database");
		connect_db_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					Class. forName( "com.mysql.cj.jdbc.Driver") ;
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store","root","");
					 String sql = "SELECT customer_id,first_name, last_name, email, phone, address FROM customer";
					  // Execute the query
			            PreparedStatement statement = con.prepareStatement(sql);
			            ResultSet resultSet = statement.executeQuery();

			            DefaultTableModel tableModel = new DefaultTableModel();
			            tableModel.addColumn("Customer ID");
			            tableModel.addColumn("Name");
			            tableModel.addColumn("Surname");
			            tableModel.addColumn("Email");
			            tableModel.addColumn("Phone");
			            tableModel.addColumn("Adress");

			            while (resultSet.next()) {
			                int customerId = resultSet.getInt("customer_id");
			                String firstName = resultSet.getString("first_name");
			                String lastName = resultSet.getString("last_name");
			                String email = resultSet.getString("email");
			                String phone = resultSet.getString("phone");
			                String adress = resultSet.getString("address");

			                Object[] rowData = {customerId, firstName, lastName, email, phone, adress};
			                tableModel.addRow(rowData);
			            }

			            table.setModel(tableModel);

			            

			           
			
					
					
					}catch (Exception el) {
					// TODO Auto-generated catch block
					el. printStackTrace( ) ;
					}
				
				
				
				
			}
		});
		connect_db_button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		connect_db_button.setBounds(486, 572, 217, 43);
		frame.getContentPane().add(connect_db_button);
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            int customerId = (int) table.getValueAt(selectedRow, 0);
		            String customerName = (String) table.getValueAt(selectedRow, 1) + " " + table.getValueAt(selectedRow, 2);

		            int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete customer ID: " + customerId + ", Name: " + customerName + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
		            if (result == JOptionPane.YES_OPTION) {
		                try {
		                    Class.forName("com.mysql.cj.jdbc.Driver");
		                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
		                    String sql = "DELETE FROM customer WHERE customer_id = ?";
		                    PreparedStatement statement = con.prepareStatement(sql);
		                    statement.setInt(1, customerId);
		                    statement.executeUpdate();

		                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		                    tableModel.removeRow(selectedRow);

		                    JOptionPane.showMessageDialog(frame, "Customer deleted successfully!");

		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(frame, "Error deleting customer.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        } else {
		            JOptionPane.showMessageDialog(frame, "Please select a customer to delete.", "No Customer Selected", JOptionPane.WARNING_MESSAGE);
		        }
		    
			}
		});
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_delete.setBounds(860, 572, 130, 43);
		frame.getContentPane().add(btn_delete);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setColumns(10);
		firstNameTextField.setBounds(150, 186, 238, 41);
		frame.getContentPane().add(firstNameTextField);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setColumns(10);
		lastNameTextField.setBounds(150, 242, 238, 41);
		frame.getContentPane().add(lastNameTextField);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(150, 293, 238, 41);
		frame.getContentPane().add(emailTextField);
		
		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(150, 344, 238, 41);
		frame.getContentPane().add(phoneTextField);
		
		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(150, 395, 238, 41);
		frame.getContentPane().add(addressTextField);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFirstName.setBounds(20, 188, 100, 27);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLastName.setBounds(20, 242, 100, 27);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(20, 293, 100, 27);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhone.setBounds(20, 347, 100, 27);
		frame.getContentPane().add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(20, 398, 100, 27);
		frame.getContentPane().add(lblAddress);
		
		JButton btnGoBack_1 = new JButton("Add ");
		btnGoBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  String firstName = firstNameTextField.getText();
			        String lastName = lastNameTextField.getText();
			        String email = emailTextField.getText();
			        String phone = phoneTextField.getText();
			        String address = addressTextField.getText();

			        // Validate input data
			        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
			            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Incomplete Data", JOptionPane.WARNING_MESSAGE);
			   
			        } else {
			            try {
			                Class.forName("com.mysql.cj.jdbc.Driver");
			                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");

			                String sql = "INSERT INTO customer (first_name, last_name, email, phone, address) VALUES (?, ?, ?, ?, ?)";
			                PreparedStatement statement = con.prepareStatement(sql);
			                statement.setString(1, firstName);
			                statement.setString(2, lastName);
			                statement.setString(3, email);
			                statement.setString(4, phone);
			                statement.setString(5, address);

			                int rowsAffected = statement.executeUpdate();

			                if (rowsAffected > 0) {
			                    JOptionPane.showMessageDialog(frame, "Customer added successfully!");

			                    // Clear input fields
			                    firstNameTextField.setText("");
			                    lastNameTextField.setText("");
			                    emailTextField.setText("");
			                    phoneTextField.setText("");
			                    addressTextField.setText("");
			                } else {
			                    JOptionPane.showMessageDialog(frame, "Failed to add customer.", "Error", JOptionPane.ERROR_MESSAGE);
			                }

			            } catch (Exception ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(frame, "Error adding customer.", "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        }
			}
		});
		btnGoBack_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack_1.setBounds(202, 572, 130, 43);
		frame.getContentPane().add(btnGoBack_1);
		
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
				    // Get the customer details from the selected row
				    int customerId = (int) table.getValueAt(selectedRow, 0);
				    String firstName = (String) table.getValueAt(selectedRow, 1);
				    String lastName = (String) table.getValueAt(selectedRow, 2);
				    String email = (String) table.getValueAt(selectedRow, 3);
				    String phone = (String) table.getValueAt(selectedRow, 4);
				    String address = (String) table.getValueAt(selectedRow, 5);

				    // Display a form or input dialog to allow the user to enter new details
				    JTextField firstNameField = new JTextField(firstName);
				    JTextField lastNameField = new JTextField(lastName);
				    JTextField emailField = new JTextField(email);
				    JTextField phoneField = new JTextField(phone);
				    JTextField addressField = new JTextField(address);

				    Object[] fields = {"First Name:", firstNameField, "Last Name:", lastNameField, "Email:", emailField, "Phone:", phoneField, "Address:", addressField};

				    int result = JOptionPane.showConfirmDialog(frame, fields, "Edit Customer Details", JOptionPane.OK_CANCEL_OPTION);
				    if (result == JOptionPane.OK_OPTION) {
				        // Validate the input data type and format
				        try {
				            String newFirstName = firstNameField.getText();
				            String newLastName = lastNameField.getText();
				            String newEmail = emailField.getText();
				            String newPhone = phoneField.getText();
				            String newAddress = addressField.getText();

				        
				            // Update the row in the JTable
				            table.setValueAt(newFirstName, selectedRow, 1);
				            table.setValueAt(newLastName, selectedRow, 2);
				            table.setValueAt(newEmail, selectedRow, 3);
				            table.setValueAt(newPhone, selectedRow, 4);
				            table.setValueAt(newAddress, selectedRow, 5);

				            // Update the customer details in the database using an SQL update statement
				            try {
				                Class.forName("com.mysql.cj.jdbc.Driver");
				                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
				                String sql = "UPDATE customer SET first_name = ?, last_name = ?, email = ?, phone = ?, address = ? WHERE customer_id = ?";
				                PreparedStatement statement = con.prepareStatement(sql);
				                statement.setString(1, newFirstName);
				                statement.setString(2, newLastName);
				                statement.setString(3, newEmail);
				                statement.setString(4, newPhone);
				                statement.setString(5, newAddress);
				                statement.setInt(6, customerId);
				                statement.executeUpdate();

				                JOptionPane.showMessageDialog(frame, "Customer details updated successfully!");
				            } catch (Exception ex) {
				                ex.printStackTrace();
				                JOptionPane.showMessageDialog(frame, "Error updating customer details.", "Error", JOptionPane.ERROR_MESSAGE);
				            }

				        } catch (Exception ex) {
				            JOptionPane.showMessageDialog(frame, "Invalid input.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
				        }
				    }
				} else {
				    JOptionPane.showMessageDialog(frame, "Please select a customer to edit.", "No Customer Selected", JOptionPane.WARNING_MESSAGE);
				}
			        }
			
		});
		btn_update.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_update.setBounds(713, 572, 130, 43);
		frame.getContentPane().add(btn_update);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin_main_page admin_main = new  Admin_main_page();
	        	admin_main.frame.setVisible(true);
	        	frame.dispose();
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack.setBounds(62, 572, 130, 43);
		frame.getContentPane().add(btnGoBack);
		
		JLabel lblNewLabel = new JLabel("Manga Odyssey\r\n");
		lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 49));
		lblNewLabel.setBounds(10, 27, 439, 59);
		frame.getContentPane().add(lblNewLabel);
	}
}
