import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultListModel;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Admin_manga_page {

	JFrame frame;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtGenre;
	private JTextField txtPrice;
	private JTextField txtStockQuantity;
	private JTextField txtImagePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_manga_page window = new Admin_manga_page();
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
	public Admin_manga_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1084, 731);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(227, 154, 238, 41);
		frame.getContentPane().add(txtTitle);
		txtTitle.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(227, 214, 238, 41);
		frame.getContentPane().add(txtAuthor);
		
		txtGenre = new JTextField();
		txtGenre.setColumns(10);
		txtGenre.setBounds(227, 269, 238, 41);
		frame.getContentPane().add(txtGenre);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(227, 328, 238, 41);
		frame.getContentPane().add(txtPrice);
		
		txtStockQuantity = new JTextField();
		txtStockQuantity.setColumns(10);
		txtStockQuantity.setBounds(227, 379, 238, 41);
		frame.getContentPane().add(txtStockQuantity);
		
		txtImagePath = new JTextField();
		txtImagePath.setColumns(10);
		txtImagePath.setBounds(227, 430, 238, 41);
		frame.getContentPane().add(txtImagePath);
		
		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(117, 157, 100, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAuthor.setBounds(117, 214, 100, 27);
		frame.getContentPane().add(lblAuthor);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGenre.setBounds(117, 269, 100, 27);
		frame.getContentPane().add(lblGenre);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(117, 328, 100, 27);
		frame.getContentPane().add(lblPrice);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStock.setBounds(117, 379, 100, 27);
		frame.getContentPane().add(lblStock);
		
		JLabel lblImage = new JLabel("Image");
		lblImage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblImage.setBounds(117, 430, 100, 27);
		frame.getContentPane().add(lblImage);
		
		JLabel lblAdminMangaPage = new JLabel("Admin  manga page");
		lblAdminMangaPage.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAdminMangaPage.setBounds(25, 62, 275, 82);
		frame.getContentPane().add(lblAdminMangaPage);
		lblAdminMangaPage.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 Admin_manga_page adminmanga = new Admin_manga_page();
					adminmanga.frame.setVisible(true);
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
		btnGoBack.setBounds(227, 531, 130, 43);
		frame.getContentPane().add(btnGoBack);
		
		JButton btnGoBack_1 = new JButton("Add ");
		btnGoBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class. forName( "com.mysql.cj.jdbc.Driver") ;
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store","root","");
					 String sql = "INSERT INTO manga (Title, Author, Genre, Price, Stock, Image_path) VALUES ( ?, ?, ?, ?, ?, ?)";
			            PreparedStatement statement = con.prepareStatement(sql);

			            
			            String Title = txtTitle.getText();
			            String Author = txtAuthor.getText();
			            String Genre = txtGenre.getText();
			            double Price = Double.parseDouble(txtPrice.getText());
			            int Stock = Integer.parseInt(txtStockQuantity.getText());
			            String Image_Path = txtImagePath.getText();
			            
			            
			            
			            
			            
			            // Set the parameter values for the prepared statement
			            statement.setString(1, Title);
			            statement.setString(2, Author);
			            statement.setString(3, Genre);
			            statement.setDouble(4, Price);
			            statement.setInt(5, Stock);
			            statement.setString(6, Image_Path);
			          

			            // Execute the insert statement
			            int rowsInserted = statement.executeUpdate();

			            if (rowsInserted > 0) {
			                System.out.println("Data inserted successfully.");
			            } else {
			                System.out.println("Failed to insert data.");
			            }
					
					}catch (Exception el) {
					// TODO Auto-generated catch block
					el. printStackTrace( ) ;
					}
					
			
				
				
				
			}
		});
		btnGoBack_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack_1.setBounds(367, 531, 130, 43);
		frame.getContentPane().add(btnGoBack_1);
		
		JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(500, 154, 504, 366);
        frame.getContentPane().add(scrollPane);
		
		JButton connect_db_button = new JButton("Connect to database");
		connect_db_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class. forName( "com.mysql.cj.jdbc.Driver") ;
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store","root","");
					 String sql = "SELECT Manga_id,Title, Author, Genre, Price, Stock FROM manga";
					  // Execute the query
			            PreparedStatement statement = con.prepareStatement(sql);
			            ResultSet resultSet = statement.executeQuery();

			            DefaultTableModel tableModel = new DefaultTableModel();
			            tableModel.addColumn("Manga ID");
			            tableModel.addColumn("Title");
			            tableModel.addColumn("Author");
			            tableModel.addColumn("Genre");
			            tableModel.addColumn("Price");
			            tableModel.addColumn("Stock");

			            while (resultSet.next()) {
			                int mangaId = resultSet.getInt("Manga_id");
			                String title = resultSet.getString("Title");
			                String author = resultSet.getString("Author");
			                String genre = resultSet.getString("Genre");
			                double price = resultSet.getDouble("Price");
			                int stock = resultSet.getInt("Stock");

			                Object[] rowData = {mangaId, title, author, genre, price, stock};
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
		connect_db_button.setBounds(507, 531, 217, 43);
		frame.getContentPane().add(connect_db_button);
		
		//JList list = new JList();
		//list.setBounds(541, 157, 500, 300);
		//frame.getContentPane().add(list);
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int selectedRow = table.getSelectedRow();
				 if (selectedRow != -1) {
			            try {
			                Class.forName("com.mysql.cj.jdbc.Driver");
			                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
			                String sql = "DELETE FROM manga WHERE Manga_id = ?";
			                PreparedStatement statement = con.prepareStatement(sql);

			                // Get the manga_id from the selected row
			                int mangaId = (int) table.getValueAt(selectedRow, 0);

			                // Set the manga_id as the parameter value for the prepared statement
			                statement.setInt(1, mangaId);

			                // Execute the deletion
			                statement.executeUpdate();

			                // Remove the selected row from the table model
			                ((DefaultTableModel) table.getModel()).removeRow(selectedRow);

			                JOptionPane.showMessageDialog(frame, "Book deleted successfully!");

			            } catch (Exception ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(frame, "Error deleting book.", "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        } else {
			            JOptionPane.showMessageDialog(frame, "Please select a book to delete.", "No Book Selected", JOptionPane.WARNING_MESSAGE);
			        }
			    }
			
		});
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_delete.setBounds(734, 531, 130, 43);
		frame.getContentPane().add(btn_delete);
		
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 int selectedRow = table.getSelectedRow();
			        if (selectedRow != -1) {
			            // Get the book details from the selected row
			            int mangaId = (int) table.getValueAt(selectedRow, 0);
			            String title = (String) table.getValueAt(selectedRow, 1);
			            String author = (String) table.getValueAt(selectedRow, 2);
			            String genre = (String) table.getValueAt(selectedRow, 3);
			            double price = (double) table.getValueAt(selectedRow, 4);
			            int stock = (int) table.getValueAt(selectedRow, 5);

			            // Display a form or input dialog to allow the user to enter new details
			            JTextField titleField = new JTextField(title);
			            JTextField authorField = new JTextField(author);
			            JTextField genreField = new JTextField(genre);
			            JTextField priceField = new JTextField(String.valueOf(price));
			            JTextField stockField = new JTextField(String.valueOf(stock));

			            Object[] fields = {"Title:", titleField, "Author:", authorField, "Genre:", genreField, "Price:", priceField, "Stock:", stockField};

			            int result = JOptionPane.showConfirmDialog(frame, fields, "Edit Book Details", JOptionPane.OK_CANCEL_OPTION);
			            if (result == JOptionPane.OK_OPTION) {
			                // Validate the input data type
			                try {
			                    double newPrice = Double.parseDouble(priceField.getText());
			                    int newStock = Integer.parseInt(stockField.getText());

			                    // Update the row in the JTable
			                    table.setValueAt(titleField.getText(), selectedRow, 1);
			                    table.setValueAt(authorField.getText(), selectedRow, 2);
			                    table.setValueAt(genreField.getText(), selectedRow, 3);
			                    table.setValueAt(newPrice, selectedRow, 4);
			                    table.setValueAt(newStock, selectedRow, 5);

			                    // Update the book details in the database using an SQL update statement
			                    try {
			                        Class.forName("com.mysql.cj.jdbc.Driver");
			                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
			                        String sql = "UPDATE manga SET Title = ?, Author = ?, Genre = ?, Price = ?, Stock = ? WHERE Manga_id = ?";
			                        PreparedStatement statement = con.prepareStatement(sql);
			                        statement.setString(1, titleField.getText());
			                        statement.setString(2, authorField.getText());
			                        statement.setString(3, genreField.getText());
			                        statement.setDouble(4, newPrice);
			                        statement.setInt(5, newStock);
			                        statement.setInt(6, mangaId);
			                        statement.executeUpdate();

			                        JOptionPane.showMessageDialog(frame, "Book details updated successfully!");
			                    } catch (Exception ex) {
			                        ex.printStackTrace();
			                        JOptionPane.showMessageDialog(frame, "Error updating book details.", "Error", JOptionPane.ERROR_MESSAGE);
			                    }

			                } catch (NumberFormatException ex) {
			                    JOptionPane.showMessageDialog(frame, "Invalid input for Price or Stock. Please enter numeric values.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
			                }
			            }
			        } else {
			            JOptionPane.showMessageDialog(frame, "Please select a book to edit.", "No Book Selected", JOptionPane.WARNING_MESSAGE);
			        }
				
			}
		});
		btn_update.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_update.setBounds(874, 531, 130, 43);
		frame.getContentPane().add(btn_update);
		
		JLabel lblCustomerInfoPage = new JLabel("Customer info page");
		lblCustomerInfoPage.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCustomerInfoPage.setBounds(261, 62, 275, 82);
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
		lblCustomerOrdersPage.setBounds(517, 62, 275, 82);
		frame.getContentPane().add(lblCustomerOrdersPage);
		
		JLabel lblNewLabel_1 = new JLabel("Manga Odyssey\r\n");
		lblNewLabel_1.setFont(new Font("Algerian", Font.PLAIN, 49));
		lblNewLabel_1.setBounds(10, 23, 439, 59);
		frame.getContentPane().add(lblNewLabel_1);
		lblCustomerOrdersPage.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 admin_customer_orders_page adminorder = new admin_customer_orders_page();
					adminorder.frame.setVisible(true);
					frame.dispose();
			    }
			});
	}
}
