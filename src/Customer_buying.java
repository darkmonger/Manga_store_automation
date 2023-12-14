import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Customer_buying {

	  JFrame frame;
	  List<OrderItem> basketItems;

	    /**
	     * Launch the application.
	     */
	  

	    public Customer_buying(List<OrderItem> basketItems) {
	        this.basketItems = basketItems;
	        initialize();
	    }
	    /**
	     * Initialize the contents of the frame.
	     */
	    private void initialize() {
	        frame = new JFrame();
	        frame.setBounds(100, 100, 1027, 639);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().setLayout(null);

	        JLabel lblNewLabel = new JLabel("Manga Odyssey\r\n");
	        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 49));
	        lblNewLabel.setBounds(37, 10, 439, 59);
	        frame.getContentPane().add(lblNewLabel);

	        JTextArea textArea = new JTextArea();
	        textArea.setBounds(222, 150, 282, 34);
	        frame.getContentPane().add(textArea);

	        JTextArea textArea_1 = new JTextArea();
	        textArea_1.setBounds(222, 209, 282, 34);
	        frame.getContentPane().add(textArea_1);

	        JTextArea textArea_2 = new JTextArea();
	        textArea_2.setBounds(222, 253, 282, 34);
	        frame.getContentPane().add(textArea_2);

	        JTextArea textArea_3 = new JTextArea();
	        textArea_3.setBounds(222, 308, 282, 34);
	        frame.getContentPane().add(textArea_3);

	        JTextArea textArea_4 = new JTextArea();
	        textArea_4.setBounds(222, 355, 282, 117);
	        frame.getContentPane().add(textArea_4);

	        JLabel lblNewLabel_1 = new JLabel("First name ");
	        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblNewLabel_1.setBounds(83, 151, 104, 33);
	        frame.getContentPane().add(lblNewLabel_1);

	        JLabel lblNewLabel_1_1 = new JLabel("Surname");
	        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblNewLabel_1_1.setBounds(83, 210, 104, 33);
	        frame.getContentPane().add(lblNewLabel_1_1);

	        JLabel lblNewLabel_1_2 = new JLabel("Email");
	        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblNewLabel_1_2.setBounds(83, 254, 104, 33);
	        frame.getContentPane().add(lblNewLabel_1_2);

	        JLabel lblNewLabel_1_3 = new JLabel("Phone ");
	        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblNewLabel_1_3.setBounds(83, 309, 104, 33);
	        frame.getContentPane().add(lblNewLabel_1_3);

	        JLabel lblNewLabel_1_4 = new JLabel("Address");
	        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lblNewLabel_1_4.setBounds(83, 356, 104, 33);
	        frame.getContentPane().add(lblNewLabel_1_4);

	        JButton btnGoBack = new JButton("Go back");
	        btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnGoBack.setBounds(576, 525, 130, 43);
	        frame.getContentPane().add(btnGoBack);

	        JButton btnOrder = new JButton("Order");
	        btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnOrder.setBounds(736, 525, 130, 43);
	        frame.getContentPane().add(btnOrder);

	        JLabel lblCustomerInformation = new JLabel("Customer information");
	        lblCustomerInformation.setFont(new Font("Tahoma", Font.PLAIN, 24));
	        lblCustomerInformation.setBounds(34, 59, 637, 82);
	        frame.getContentPane().add(lblCustomerInformation);

	        // Action listener for the "Order" button
	        btnOrder.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String firstName = textArea.getText();
	                String surname = textArea_1.getText();
	                String email = textArea_2.getText();
	                String phone = textArea_3.getText();
	                String address = textArea_4.getText();

	                // Check if any of the fields are empty
	                if (firstName.isEmpty() || surname.isEmpty() || email.isEmpty() || phone.isEmpty()
	                        || address.isEmpty()) {
	                    JOptionPane.showMessageDialog(frame, "Please fill in all the fields.");
	                    return;
	                }

	                try {
	                    // Save the order and order items in the database
	                	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
	                	
	                	for (OrderItem item : basketItems) {
	                	    int mangaId = item.getMangaId();
	                	    int quantity = item.getQuantity();
	                	    // Retrieve the manga's stock from the database or any other source
	                	    int availableStock = getMangaStockFromDatabase(mangaId);

	                	    if (availableStock < quantity) {
	                	        JOptionPane.showMessageDialog(frame, "Oops! The book is out of stock. Please update your order.");
	                	        return; // Cancel the buying process
	                	    }
	                	}
	                            

	                    // Insert into orders table
	                    String insertOrderQuery = "INSERT INTO customer (first_name,last_name, email, phone, address) VALUES (?, ?, ?, ?, ?)";
	                    PreparedStatement insertOrderStmt = connection.prepareStatement(insertOrderQuery,
	                            Statement.RETURN_GENERATED_KEYS);
	                    insertOrderStmt.setString(1, firstName);
	                    insertOrderStmt.setString(2, surname);
	                    insertOrderStmt.setString(3, email);
	                    insertOrderStmt.setString(4, phone);
	                    insertOrderStmt.setString(5, address);
	                    int affectedRows = insertOrderStmt.executeUpdate();

	                    if (affectedRows == 0) {
	                        throw new SQLException("Creating order failed, no rows affected.");
	                    }

	                    // Get the generated order ID
	                    ResultSet generatedKeys = insertOrderStmt.getGeneratedKeys();
	                    int customerId;
	                    if (generatedKeys.next()) {
	                    	customerId = generatedKeys.getInt(1);
	                    } else {
	                        throw new SQLException("Creating order failed, no ID obtained.");
	                    }

	                    try {
	                        Class.forName("com.mysql.cj.jdbc.Driver");
	                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
	             
	                     // Insert into orders table
	                        String orderSql = "INSERT INTO orders (customer_id, order_date, total_amount) VALUES (?, ?, ?)";
	                        PreparedStatement orderStatement = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
	                        orderStatement.setInt(1, customerId); // Replace 'customerId' with the actual customer ID
	                        orderStatement.setDate(2, new Date(System.currentTimeMillis()));
	                        orderStatement.setDouble(3,calculateTotalPrice(basketItems));
	                        orderStatement.executeUpdate();

	                        ResultSet generatedKeys1 = orderStatement.getGeneratedKeys();
	                        int orderId = -1;
	                        if (generatedKeys1.next()) {
	                            orderId = generatedKeys1.getInt(1);
	                        }
	                        
	                        

	                     // Insert into order items table
	                        String orderItemSql = "INSERT INTO order_items (order_id, manga_id, quantity, price) VALUES (?, ?, ?, ?)";
	                        PreparedStatement orderItemStatement = con.prepareStatement(orderItemSql, Statement.RETURN_GENERATED_KEYS);

	                        for (OrderItem item : basketItems) {
	                            orderItemStatement.setInt(1, orderId); // Assuming orderId is the ID of the order from the orders table
	                            orderItemStatement.setInt(2, item.getMangaId());
	                            orderItemStatement.setInt(3, item.getQuantity());
	                            orderItemStatement.setDouble(4, item.getPrice());
	                            orderItemStatement.executeUpdate();
	                        }

	                        // Decrease the quantity of the books in the database
	                        String updateBookQuery = "UPDATE manga SET Stock = Stock - ? WHERE Manga_id = ?";
	                        PreparedStatement updateBookStmt = connection.prepareStatement(updateBookQuery);
	                        for (OrderItem item : basketItems) {
	                            updateBookStmt.setInt(1, item.getQuantity());
	                            updateBookStmt.setInt(2, item.getMangaId());
	                            updateBookStmt.addBatch();
	                        }
	                        updateBookStmt.executeBatch();
	                            
	                            
	                        

	                        orderItemStatement.close();
	                        orderStatement.close();
	                        con.close();
	                    } catch (Exception ex) {
	                        ex.printStackTrace();
	                    }
	                


	                    JOptionPane.showMessageDialog(frame, "Order placed successfully!");
	                    connection.close();
	                    closeWindow();
	                    
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(frame, "Failed to place the order.");
	                }
	              
	            }

				private int getMangaStockFromDatabase(int mangaId) {
					 int stock = 0;
					    
					    try {
					        // Establish a database connection
					        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
					        
					        // Prepare the SQL query to retrieve the stock
					        String query = "SELECT stock FROM manga WHERE Manga_id = ?";
					        PreparedStatement statement = connection.prepareStatement(query);
					        statement.setInt(1, mangaId);
					        
					        // Execute the query
					        ResultSet resultSet = statement.executeQuery();
					        
					        // Check if a result is returned and retrieve the stock value
					        if (resultSet.next()) {
					            stock = resultSet.getInt("stock");
					        }
					        
					        // Close the resources
					        resultSet.close();
					        statement.close();
					        connection.close();
					    } catch (SQLException e) {
					        e.printStackTrace();
					        // Handle any potential exceptions that occurred during the database operations
					    }
					    
					    return stock;
				}
	        });

	        // Action listener for the "Go back" button
	        btnGoBack.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                frame.dispose(); // Close the current window
	                // Reopen the checkout page
	                openCheckoutPage(basketItems);
	            }
	        });
	    }

	    private void openCheckoutPage(List<OrderItem> basketItems) {
	        // Create the checkout page
	        Chechout_page checkoutPage = new Chechout_page(basketItems);
	        checkoutPage.frame.setVisible(true);
	    }
	    
	    
	 // Calculate total price based on basketItems
	    private double calculateTotalPrice(List<OrderItem> basketItems) {
	        double totalPrice = 0.0;
	        for (OrderItem item : basketItems) {
	            totalPrice += item.getPrice() * item.getQuantity();
	        }
	        return totalPrice;
	    }
	    
	    public void closeWindow() {
	        frame.dispose();
	    }
	}
