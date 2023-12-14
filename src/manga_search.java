import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.TableModel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class manga_search {

	JFrame frame;
	private JTextField searchTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manga_search window = new manga_search();
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
	public manga_search() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1137, 738);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manga Odyssey\r\n");
		lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 49));
		lblNewLabel.setBounds(10, 22, 439, 59);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(506, 93, 78, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		searchTextField = new JTextField();
		searchTextField.setColumns(10);
		searchTextField.setBounds(594, 91, 215, 39);
		frame.getContentPane().add(searchTextField);
		
		JButton btnCheckout = new JButton("Checkout\n");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				openCheckoutPage(basketItems);
			}
		});
		
		
		btnCheckout.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCheckout.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnCheckout.setBounds(951, 94, 148, 36);
		frame.getContentPane().add(btnCheckout);
		
		JButton btnSearch = new JButton("search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = searchTextField.getText().trim();
				// TODO Auto-generated method stub
				JPanel panel = new JPanel();
				panel.setBounds(64, 197, 1000, 500);
				panel.setLayout(new GridLayout(0, 5, 10, 10)); // 5 columns, 10px horizontal and vertical gaps
				panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the panel
				frame.getContentPane().add(panel);
				
				  try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
			            String sql = "SELECT Manga_id, Title, Author, Genre, Price, Stock FROM manga WHERE Title LIKE ?";
			            PreparedStatement statement = con.prepareStatement(sql);
			            statement.setString(1, "%" + searchText + "%");
			            ResultSet resultSet = statement.executeQuery();

			            panel.removeAll(); // Clear previous manga items

			            boolean hasResults = false;

			            while (resultSet.next()) {
			                hasResults = true;

			                // Get manga details from the ResultSet
			                int mangaId = resultSet.getInt("Manga_id");
			                String title = resultSet.getString("Title");
			                String author = resultSet.getString("Author");
			                String genre = resultSet.getString("Genre");
			                double price = resultSet.getDouble("Price");
			                int stock = resultSet.getInt("Stock");
			                
			                

			                // Create a custom panel for each manga item
			                JPanel mangaPanel = createMangaPanel(mangaId, title, author, genre, price, stock);
			                panel.add(mangaPanel); // Add the manga panel to the main panel
			            }

			            // If no matching books are found, display a message
			            if (!hasResults) {
			                JLabel messageLabel = new JLabel("Oops! No books found. Try another search term.");
			                panel.add(messageLabel); // Add the message label to the main panel
			            }

			            panel.revalidate(); // Revalidate the panel to update the layout

			            resultSet.close();
			            statement.close();
			            con.close();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
				  
				// Create a JScrollPane and add the panel to it
				  JScrollPane scrollPane = new JScrollPane(panel);
				  scrollPane.setBounds(64, 197, 891, 379);
				  scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Enable vertical scrolling
				  frame.getContentPane().add(scrollPane);
				  
				  
				  
				  
				  
				
				
				
			}
		});
		btnSearch.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSearch.setBounds(819, 93, 123, 36);
		frame.getContentPane().add(btnSearch);
		
		JButton btnNewButton = new JButton("Go back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_page  mainPage = new Main_page();
				mainPage.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(951, 599, 130, 43);
		frame.getContentPane().add(btnNewButton);
		
	
	}
	
	
		
		
	
	  private JPanel mangaPanel;

	public void setSearchText(String searchText) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 5, 10, 10)); // 5 columns, 10px horizontal and vertical gaps
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the panel
		

		
		
		  try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
	            String sql = "SELECT Manga_id, Title, Author, Genre, Price, Stock FROM manga WHERE Title LIKE ?";
	            PreparedStatement statement = con.prepareStatement(sql);
	            statement.setString(1, "%" + searchText + "%");
	            ResultSet resultSet = statement.executeQuery();

	            panel.removeAll(); // Clear previous manga items

	            boolean hasResults = false;

	            while (resultSet.next()) {
	                hasResults = true;

	                // Get manga details from the ResultSet
	                int mangaId = resultSet.getInt("Manga_id");
	                String title = resultSet.getString("Title");
	                String author = resultSet.getString("Author");
	                String genre = resultSet.getString("Genre");
	                double price = resultSet.getDouble("Price");
	                int stock = resultSet.getInt("Stock");
	                
	                

	                // Create a custom panel for each manga item
	                JPanel mangaPanel = createMangaPanel(mangaId, title, author, genre, price, stock);
	                panel.add(mangaPanel); // Add the manga panel to the main panel
	            }

	            
	            if (!hasResults) {
	                JLabel messageLabel = new JLabel("Oops! No books found. Try another search term.");
	                panel.add(messageLabel); 
	            }

	            panel.revalidate(); 

	            resultSet.close();
	            statement.close();
	            con.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		  
		  
		// Create a JScrollPane and add the panel to it
		  JScrollPane scrollPane = new JScrollPane(panel);
		  scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scroll bar
		  scrollPane.setBounds(64, 197, 891, 379);
		  frame.getContentPane().add(scrollPane);
	    }
	
	private List<OrderItem> basketItems = new ArrayList<>();
	
	private JPanel createMangaPanel(int mangaId, String title, String author, String genre, double price, int stock) {
	    // Create a custom panel for each manga item
	    JPanel mangaPanel = new JPanel();
	    mangaPanel.setLayout(new BoxLayout(mangaPanel, BoxLayout.Y_AXIS));

	    // Create labels for manga details
	    JLabel titleLabel = new JLabel(title);
	    JLabel priceLabel = new JLabel("Price: $" + price);

	    // Create an image label
	    JLabel imageLabel = new JLabel();

	    // Create a sub-panel for manga details
	    JPanel detailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    detailsPanel.add(titleLabel);
	    detailsPanel.add(priceLabel);

	    // Add the image label and details panel to the manga panel
	    mangaPanel.add(imageLabel);
	    mangaPanel.add(detailsPanel);

	    // Create a button to add the manga to the basket
	    JButton addButton = new JButton("Add to Basket");
	    addButton.setAlignmentX(Component.CENTER_ALIGNMENT);

	    addButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            OrderItem orderItem = new OrderItem(mangaId, title, author, genre, price, 1);
	            basketItems.add(orderItem);
	            JOptionPane.showMessageDialog(frame, "The book has been added to the basket.");
	        }
	    });

	    mangaPanel.add(addButton);

	    try {
	        // Get the image path from the database for the manga
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
	        String sql = "SELECT image_path FROM manga WHERE Manga_id = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, mangaId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            String imagePath = resultSet.getString("image_path");

	            // Load and set the image in the image label
	            ImageIcon imageIcon = new ImageIcon(imagePath);
	            Image image = imageIcon.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
	            imageIcon = new ImageIcon(image);
	            imageLabel.setIcon(imageIcon);
	        }

	        resultSet.close();
	        statement.close();
	        con.close();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    return mangaPanel;
	}
	
	 private void openCheckoutPage(List<OrderItem> basketItems) {
		    // Create the checkout page
		    //Chechout_page checkoutPage = new Chechout_page(basketItems);
		    Chechout_page.NewScreen(basketItems);
		    
	 }
	 private List<Manga> actionGenreMangas;

	 public void setActionGenreMangas(List<Manga> actionGenreMangas) {
	     this.actionGenreMangas = actionGenreMangas;
	     displayActionGenreMangas();
	 }

	 private void displayActionGenreMangas() {
	     JPanel panel = new JPanel();
	     panel.setBounds(64, 197, 891, 379);
	     panel.setLayout(new GridLayout(0, 5, 10, 10)); // 5 columns, 10px horizontal and vertical gaps
	     panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the panel
	     frame.getContentPane().add(panel);

	     if (actionGenreMangas.isEmpty()) {
	         JLabel messageLabel = new JLabel("Oops! No action genre mangas found.");
	         panel.add(messageLabel);
	     } else {
	         for (Manga manga : actionGenreMangas) {
	             JPanel mangaPanel = createMangaPanel(manga.getMangaId(), manga.getTitle(), manga.getAuthor(),
	                     manga.getGenre(), manga.getPrice(), manga.getQuantity());
	             panel.add(mangaPanel);
	         }
	     }

	     // Create a JScrollPane and add the panel to it
	     JScrollPane scrollPane = new JScrollPane(panel);
	     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scroll bar
	     scrollPane.setBounds(64, 197, 891, 379);
	     frame.getContentPane().add(scrollPane);

	     panel.revalidate(); // Revalidate the panel to update the layout
	 }

	 
	 private List<Manga> fantasyGenreMangas;
	 
	 public void setFantasyGenreMangas(List<Manga> fantasyGenreMangas) {
		    this.fantasyGenreMangas = fantasyGenreMangas;
		    displayFantasyGenreMangas();
		}

	 private void displayFantasyGenreMangas() {
		    JPanel panel = new JPanel();
		    panel.setBounds(64, 197, 891, 379);
		    panel.setLayout(new GridLayout(0, 5, 10, 10)); // 5 columns, 10px horizontal and vertical gaps
		    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the panel
		    frame.getContentPane().add(panel);

		    if (fantasyGenreMangas.isEmpty()) {
		        JLabel messageLabel = new JLabel("Oops! No fantasy genre mangas found.");
		        panel.add(messageLabel);
		    } else {
		        for (Manga manga : fantasyGenreMangas) {
		            JPanel mangaPanel = createMangaPanel(manga.getMangaId(), manga.getTitle(), manga.getAuthor(),
		                    manga.getGenre(), manga.getPrice(), manga.getQuantity());
		            panel.add(mangaPanel);
		        }
		    }
		    
		    

		    // Create a JScrollPane and add the panel to it
		    JScrollPane scrollPane = new JScrollPane(panel);
		    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scroll bar
		    scrollPane.setBounds(64, 197, 891, 379);
		    frame.getContentPane().add(scrollPane);

		    panel.revalidate(); // Revalidate the panel to update the layout
		}
	 
	 private List<Manga> horrorGenreMangas;

	 public void setHorrorGenreMangas(List<Manga> horrorGenreMangas) {
	     this.horrorGenreMangas = horrorGenreMangas;
	     displayHorrorGenreMangas();
	 }

	 private void displayHorrorGenreMangas() {
	     JPanel panel = new JPanel();
	     panel.setBounds(64, 197, 891, 379);
	     panel.setLayout(new GridLayout(0, 5, 10, 10)); // 5 columns, 10px horizontal and vertical gaps
	     panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the panel
	     frame.getContentPane().add(panel);

	     if (horrorGenreMangas.isEmpty()) {
	         JLabel messageLabel = new JLabel("Oops! No horror genre mangas found.");
	         panel.add(messageLabel);
	     } else {
	         for (Manga manga : horrorGenreMangas) {
	             JPanel mangaPanel = createMangaPanel(manga.getMangaId(), manga.getTitle(), manga.getAuthor(),
	                     manga.getGenre(), manga.getPrice(), manga.getQuantity());
	             panel.add(mangaPanel);
	         }
	     }

	     // Create a JScrollPane and add the panel to it
	     JScrollPane scrollPane = new JScrollPane(panel);
	     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scroll bar
	     scrollPane.setBounds(64, 197, 891, 379);
	     frame.getContentPane().add(scrollPane);

	     panel.revalidate(); // Revalidate the panel to update the layout
	 }
	 
	 private List<Manga> scienceGenreMangas;

	 public void setScienceGenreMangas(List<Manga> scienceGenreMangas) {
	     this.scienceGenreMangas = scienceGenreMangas;
	     displayScienceGenreMangas();
	 }

	 private void displayScienceGenreMangas() {
	     JPanel panel = new JPanel();
	     panel.setBounds(64, 197, 891, 379);
	     panel.setLayout(new GridLayout(0, 5, 10, 10)); // 5 columns, 10px horizontal and vertical gaps
	     panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the panel
	     frame.getContentPane().add(panel);

	     if (scienceGenreMangas.isEmpty()) {
	         JLabel messageLabel = new JLabel("Oops! No science genre mangas found.");
	         panel.add(messageLabel);
	     } else {
	         for (Manga manga : scienceGenreMangas) {
	             JPanel mangaPanel = createMangaPanel(manga.getMangaId(), manga.getTitle(), manga.getAuthor(),
	                     manga.getGenre(), manga.getPrice(), manga.getQuantity());
	             panel.add(mangaPanel);
	         }
	     }

	     // Create a JScrollPane and add the panel to it
	     JScrollPane scrollPane = new JScrollPane(panel);
	     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scroll bar
	     scrollPane.setBounds(64, 197, 891, 379);
	     frame.getContentPane().add(scrollPane);

	     panel.revalidate(); // Revalidate the panel to update the layout
	 }

	 private List<Manga> comedyGenreMangas;

	 public void setComedyGenreMangas(List<Manga> comedyGenreMangas) {
	     this.comedyGenreMangas = comedyGenreMangas;
	     displayComedyGenreMangas();
	 }

	 private void displayComedyGenreMangas() {
	     JPanel panel = new JPanel();
	     panel.setBounds(64, 197, 891, 379);
	     panel.setLayout(new GridLayout(0, 5, 10, 10)); // 5 columns, 10px horizontal and vertical gaps
	     panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the panel
	     frame.getContentPane().add(panel);

	     if (comedyGenreMangas.isEmpty()) {
	         JLabel messageLabel = new JLabel("Oops! No comedy genre mangas found.");
	         panel.add(messageLabel);
	     } else {
	         for (Manga manga : comedyGenreMangas) {
	             JPanel mangaPanel = createMangaPanel(manga.getMangaId(), manga.getTitle(), manga.getAuthor(),
	                     manga.getGenre(), manga.getPrice(), manga.getQuantity());
	             panel.add(mangaPanel);
	         }
	     }

	     // Create a JScrollPane and add the panel to it
	     JScrollPane scrollPane = new JScrollPane(panel);
	     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scroll bar
	     scrollPane.setBounds(64, 197, 891, 379);
	     frame.getContentPane().add(scrollPane);

	     panel.revalidate(); // Revalidate the panel to update the layout
	 }
}


