import java.awt.EventQueue;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
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

public class Main_page {

	JFrame frame;
	private JTextField searchTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_page window = new Main_page();
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
	public Main_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1150, 809);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manga Odyssey\r\n");
		lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 49));
		lblNewLabel.setBounds(66, 10, 439, 59);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnAction = new JButton("Action\r\n");
		btnAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
			            String sql = "SELECT Manga_id, Title, Author, Genre, Price, Stock FROM manga WHERE Genre = 'Action'";
			            PreparedStatement statement = con.prepareStatement(sql);
			            ResultSet resultSet = statement.executeQuery();

			            List<Manga> actionGenreMangas = new ArrayList<>();

			            while (resultSet.next()) {
			                int mangaId = resultSet.getInt("Manga_id");
			                String title = resultSet.getString("Title");
			                String author = resultSet.getString("Author");
			                String genre = resultSet.getString("Genre");
			                double price = resultSet.getDouble("Price");
			                int stock = resultSet.getInt("Stock");

			                Manga manga = new Manga(mangaId, title, author, genre, price, stock);
			                actionGenreMangas.add(manga);
			            }

			            // Pass the action genre mangas to the manga search page
			            manga_search window = new manga_search();
			            window.setActionGenreMangas(actionGenreMangas);
			            window.frame.setVisible(true);

			            resultSet.close();
			            statement.close();
			            con.close();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			
		});
		btnAction.setIcon(new ImageIcon("C:\\Users\\armi0\\Downloads\\chainsaw_ kopya2.jpg"));
		btnAction.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAction.setBounds(10, 193, 200, 520);
		frame.getContentPane().add(btnAction);
		
		JButton btnDarkFantasty = new JButton("\r\nFantasty\r\n");
		btnDarkFantasty.setIcon(new ImageIcon("C:\\Users\\armi0\\Downloads\\bb_ kopya.1jpg.jpg"));
		btnDarkFantasty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
			            String sql = "SELECT Manga_id, Title, Author, Genre, Price, Stock FROM manga WHERE Genre = 'Fantasy'";
			            PreparedStatement statement = con.prepareStatement(sql);
			            ResultSet resultSet = statement.executeQuery();

			            List<Manga> fantasyGenreMangas = new ArrayList<>();

			            while (resultSet.next()) {
			                int mangaId = resultSet.getInt("Manga_id");
			                String title = resultSet.getString("Title");
			                String author = resultSet.getString("Author");
			                String genre = resultSet.getString("Genre");
			                double price = resultSet.getDouble("Price");
			                int stock = resultSet.getInt("Stock");

			                Manga manga = new Manga(mangaId, title, author, genre, price, stock);
			                fantasyGenreMangas.add(manga);
			            }

			            // Pass the fantasy genre mangas to the manga search page
			            manga_search window = new manga_search();
			            window.setFantasyGenreMangas(fantasyGenreMangas);
			            window.frame.setVisible(true);

			            resultSet.close();
			            statement.close();
			            con.close();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			
		});
		btnDarkFantasty.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnDarkFantasty.setBounds(220, 193, 200, 520);
		frame.getContentPane().add(btnDarkFantasty);
		
		JButton btnScienceFiction = new JButton("Science \r\n");
		btnScienceFiction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
			            String sql = "SELECT Manga_id, Title, Author, Genre, Price, Stock FROM manga WHERE Genre = 'Science'";
			            PreparedStatement statement = con.prepareStatement(sql);
			            ResultSet resultSet = statement.executeQuery();

			            List<Manga> ScienceGenreMangas = new ArrayList<>();

			            while (resultSet.next()) {
			                int mangaId = resultSet.getInt("Manga_id");
			                String title = resultSet.getString("Title");
			                String author = resultSet.getString("Author");
			                String genre = resultSet.getString("Genre");
			                double price = resultSet.getDouble("Price");
			                int stock = resultSet.getInt("Stock");

			                Manga manga = new Manga(mangaId, title, author, genre, price, stock);
			                ScienceGenreMangas.add(manga);
			            }

			           
			            manga_search window = new manga_search();
			            window.setFantasyGenreMangas(  ScienceGenreMangas);
			            window.frame.setVisible(true);

			            resultSet.close();
			            statement.close();
			            con.close();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
				
				
			}
		});
		btnScienceFiction.setIcon(new ImageIcon("C:\\Users\\armi0\\Downloads\\20 kopya.jpg"));
		btnScienceFiction.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnScienceFiction.setBounds(660, 193, 200, 520);
		frame.getContentPane().add(btnScienceFiction);
		
		JButton btnHorror = new JButton("Horror");
		btnHorror.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
			            String sql = "SELECT Manga_id, Title, Author, Genre, Price, Stock FROM manga WHERE Genre = 'horror'";
			            PreparedStatement statement = con.prepareStatement(sql);
			            ResultSet resultSet = statement.executeQuery();

			            List<Manga> HorrorGenreMangas = new ArrayList<>();

			            while (resultSet.next()) {
			                int mangaId = resultSet.getInt("Manga_id");
			                String title = resultSet.getString("Title");
			                String author = resultSet.getString("Author");
			                String genre = resultSet.getString("Genre");
			                double price = resultSet.getDouble("Price");
			                int stock = resultSet.getInt("Stock");

			                Manga manga = new Manga(mangaId, title, author, genre, price, stock);
			                HorrorGenreMangas.add(manga);
			            }

			           
			            manga_search window = new manga_search();
			            window.setHorrorGenreMangas( HorrorGenreMangas);
			            window.frame.setVisible(true);

			            resultSet.close();
			            statement.close();
			            con.close();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
				
				
			}
			
		});
		btnHorror.setIcon(new ImageIcon("C:\\Users\\armi0\\Downloads\\uz_ kopya.jpg"));
		btnHorror.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnHorror.setBounds(430, 193, 220, 520);
		frame.getContentPane().add(btnHorror);
		
		JButton btnComedy = new JButton("Comedy\r\n");
		btnComedy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
			            String sql = "SELECT Manga_id, Title, Author, Genre, Price, Stock FROM manga WHERE Genre = 'Comedy'";
			            PreparedStatement statement = con.prepareStatement(sql);
			            ResultSet resultSet = statement.executeQuery();

			            List<Manga> ComedyGenreMangas = new ArrayList<>();

			            while (resultSet.next()) {
			                int mangaId = resultSet.getInt("Manga_id");
			                String title = resultSet.getString("Title");
			                String author = resultSet.getString("Author");
			                String genre = resultSet.getString("Genre");
			                double price = resultSet.getDouble("Price");
			                int stock = resultSet.getInt("Stock");

			                Manga manga = new Manga(mangaId, title, author, genre, price, stock);
			                ComedyGenreMangas.add(manga);
			            }

			            // Pass the fantasy genre mangas to the manga search page
			            manga_search window = new manga_search();
			            window.setScienceGenreMangas( ComedyGenreMangas);
			            window.frame.setVisible(true);

			            resultSet.close();
			            statement.close();
			            con.close();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
				
				
				
			}
		});
		btnComedy.setIcon(new ImageIcon("C:\\Users\\armi0\\Downloads\\mob kopya.jpg"));
		btnComedy.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnComedy.setBounds(870, 193, 215, 520);
		frame.getContentPane().add(btnComedy);
	
		searchTextField = new JTextField();
		searchTextField.setBounds(630, 85, 215, 39);
		frame.getContentPane().add(searchTextField);
		searchTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(561, 87, 78, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Admin");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin_login_page adminlogin = new  Admin_login_page();
				adminlogin.frame.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(25, 79, 123, 45);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnCheckout = new JButton("Exit");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			frame.dispose();
			//Chechout_page checkout = new Chechout_page();
			//checkout.NewScreen();
				
				
			}
		});
		btnCheckout.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCheckout.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnCheckout.setBounds(978, 88, 148, 36);
		frame.getContentPane().add(btnCheckout);
		
		JButton btnSearch = new JButton("search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = searchTextField.getText().trim();
				
				manga_search searchFrame = new manga_search();
				searchFrame.frame.setVisible(true);
				
				  searchFrame.setSearchText(searchText);
				  frame.dispose();
			}
		});
		
				
			
	
		btnSearch.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSearch.setBounds(855, 88, 123, 36);
		frame.getContentPane().add(btnSearch);
		
		JLabel lblNewLabel_2 = new JLabel("Action");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(57, 153, 130, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Fantasy");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(270, 153, 130, 30);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Horror");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(468, 153, 130, 30);
		frame.getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Science ");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_3.setBounds(683, 153, 130, 30);
		frame.getContentPane().add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Comedy");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_4.setBounds(910, 153, 130, 30);
		frame.getContentPane().add(lblNewLabel_2_4);
	}
	
	
	public ResultSet getActionGenreMangas(String searchText) {
	    ResultSet resultSet = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store", "root", "");
	        String sql = "SELECT Manga_id, Title, Author, Genre, Price, Stock FROM manga WHERE Genre = 'Action' AND Title LIKE ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, "%" + searchText + "%");
	        resultSet = statement.executeQuery();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    return resultSet;
	}
}
