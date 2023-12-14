import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Chechout_page					 {

	  JFrame frame;
	    JList<String> itemList;
	    DefaultListModel<String> listModel;
	    List<OrderItem> basketItems;

	    /**
	     * Launch the application.
	     */
	    public static void NewScreen(List<OrderItem> basketItems) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    Chechout_page window = new Chechout_page(basketItems);
	                    window.frame.setVisible(true);

	                    // Call the openCustomerBuyingWindow method in the Chechout_page class
	                    //window.openCustomerBuyingWindow();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }

	    /**
	     * Create the application.
	     *
	     * @param basketItems
	     */
	    public Chechout_page(List<OrderItem> basketItems) {
	        this.basketItems = basketItems;
	        initialize();
	        displayBasketItems(basketItems);
	    }

	    /**
	     * Initialize the contents of the frame.
	     */
	    private void initialize() {
	        frame = new JFrame();
	        frame.setBounds(100, 100, 1025, 674);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().setLayout(null);

	        JLabel lblNewLabel = new JLabel("Your Basket");
	        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
	        lblNewLabel.setBounds(20, 73, 637, 82);
	        frame.getContentPane().add(lblNewLabel);

	        listModel = new DefaultListModel<>();
	        itemList = new JList<>(listModel);
	        itemList.setBounds(35, 148, 743, 397);
	        frame.getContentPane().add(itemList);

	        JButton btnNewButton = new JButton("Go back");
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		  manga_search mangaSearch = new manga_search();
	        		  mangaSearch.frame.setVisible(true);
	        		  frame.dispose();
	        	}
	        });
	        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnNewButton.setBounds(571, 572, 130, 43);
	        frame.getContentPane().add(btnNewButton);

	        JButton btnBuy = new JButton("Buy");
	        btnBuy.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnBuy.setBounds(753, 572, 130, 43);
	        frame.getContentPane().add(btnBuy);

	        JButton btnDelete = new JButton("Delete");
	        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnDelete.setBounds(792, 169, 130, 43);
	        frame.getContentPane().add(btnDelete);
	        
	        JLabel lblNewLabel_1 = new JLabel("Manga Odyssey\r\n");
	        lblNewLabel_1.setFont(new Font("Algerian", Font.PLAIN, 49));
	        lblNewLabel_1.setBounds(10, 21, 439, 59);
	        frame.getContentPane().add(lblNewLabel_1);

	        // Add action listener to the delete button
	        btnDelete.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                int selectedIndex = itemList.getSelectedIndex();
	                if (selectedIndex != -1) { // Check if an item is selected
	                    listModel.remove(selectedIndex); // Remove the selected item from the list
	                    basketItems.remove(selectedIndex); // Remove the selected item from the basketItems list
	                }
	            }
	        });

	        // Add action listener to the buy button
	        btnBuy.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (!basketItems.isEmpty()) {
	                    // Update book quantities and save the order
	                    //updateBookQuantities();
	                    //saveOrder();

	                    // Open the customer buying window
	                    openCustomerBuyingWindow(basketItems);
	                  

	                    // Clear the basket items
	                    basketItems.clear();
	                    listModel.clear();
	                }
	                frame.dispose();
	            }
	        });
	    }
	    private void displayBasketItems(List<OrderItem> basketItems) {
	    	 listModel.clear(); // Clear previous items

	         if (basketItems.isEmpty()) {
	             listModel.addElement("Oops! No books found in your basket.");
	         } else {
	             for (OrderItem item : basketItems) {
	                 listModel.addElement(item.getTitle() + " - $" + item.getPrice());
	             }
	         }
	     }
	    
	    
	    /*private void updateBookQuantities() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
	            String updateSql = "UPDATE manga SET Stock = Stock - ? WHERE Manga_id = ?";

	            for (OrderItem item : basketItems) {
	                PreparedStatement updateStatement = con.prepareStatement(updateSql);
	                updateStatement.setInt(1, item.getQuantity());
	                updateStatement.setInt(2, item.getMangaId());
	                updateStatement.executeUpdate();
	                updateStatement.close();
	            }

	            con.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    /**
	     * Save the order information to the database.
	     */
	   /* private void saveOrder() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");

	            // Insert into orders table
	            String orderSql = "INSERT INTO orders (order_date, total_amount) VALUES (?, ?)";
	            PreparedStatement orderStatement = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
	            orderStatement.setDate(1, new Date(System.currentTimeMillis()));
	            orderStatement.setDouble(2, calculateTotalPrice());
	            orderStatement.executeUpdate();

	            ResultSet generatedKeys = orderStatement.getGeneratedKeys();
	            int orderId = -1;
	            if (generatedKeys.next()) {
	                orderId = generatedKeys.getInt(1);
	            }

	            // Insert into order items table
	            String orderItemSql = "INSERT INTO order_items (order_id, manga_id, quantity,price) VALUES (?, ?, ?, ?)";
	            PreparedStatement orderItemStatement = con.prepareStatement(orderItemSql);

	            for (OrderItem item : basketItems) {
	                orderItemStatement.setInt(1, orderId);
	                orderItemStatement.setInt(2, item.getMangaId());
	                orderItemStatement.setInt(3, item.getQuantity());
	                orderItemStatement.setDouble(4, item.getPrice());
	                orderItemStatement.executeUpdate();
	            }

	            orderItemStatement.close();
	            orderStatement.close();
	            con.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    /**
	     * Calculate the total price of the items in the basket.
	     *
	     * @return the total price
	     */
	    /*private double calculateTotalPrice() {
	        double totalPrice = 0;
	        for (OrderItem item : basketItems) {
	            totalPrice += item.getPrice() * item.getQuantity();
	        }
	        return totalPrice;
	    }

	    /**
	     * Open the customer buying window.
	     */
	    private void openCustomerBuyingWindow(List<OrderItem> basketItems) {
	        
	    	Customer_buying customerBuying = new Customer_buying(new ArrayList<>(basketItems));
	        customerBuying.frame.setVisible(true);
	    	
	    }
	}
	 