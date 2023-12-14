import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Admin_login_page {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_login_page window = new Admin_login_page();
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
	public Admin_login_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 708, 557);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Admin Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(71, 188, 176, 33);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Admin_password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(71, 258, 176, 33);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JTextArea username_textField = new JTextArea();
		username_textField.setBounds(271, 188, 282, 34);
		frame.getContentPane().add(username_textField);
		
		JTextArea password_textField = new JTextArea();
		password_textField.setBounds(271, 271, 282, 34);
		frame.getContentPane().add(password_textField);
		
		JButton btnLogIn = new JButton("Login");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class. forName( "com.mysql.cj.jdbc.Driver") ;
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/manga_store","root","");
					 String sql = "SELECT 	admin_username,admin_password FROM admin";
					 PreparedStatement statement = con.prepareStatement(sql);
					 ResultSet resultSet = statement.executeQuery();
					 
					 
			         if (resultSet.next()) {
			        	 
			        	 String dbUsername = resultSet.getString("admin_username");
			        	 String dbPassword = resultSet.getString("admin_password");
			        	 
			        	 String admin_username =username_textField.getText();
				         String admin_password =password_textField.getText();

			        	    if (admin_username.equals(dbUsername) && admin_password.equals(dbPassword)) {
			        	        JOptionPane.showMessageDialog(frame, "Admin login successful!");
			        	        
			        	    } else {
			        	        JOptionPane.showMessageDialog(frame, "Invalid admin credentials. Please try again.");
			        	    }
			        	} else {
			        	    JOptionPane.showMessageDialog(frame, "Admin account not found. Please contact the administrator.");
			        	}

			        	resultSet.close();
			        	statement.close();
			        	con.close();
			        	
			        	frame.dispose();
			        	Admin_main_page admin_main = new  Admin_main_page();
			        	admin_main.frame.setVisible(true);
					 
					 
					 
					 
					 
				}catch (Exception el) {
					// TODO Auto-generated catch block
					el. printStackTrace( ) ;
					}
				
				
				
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogIn.setBounds(360, 399, 130, 43);
		frame.getContentPane().add(btnLogIn);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack.setBounds(178, 399, 130, 43);
		frame.getContentPane().add(btnGoBack);
		
		JLabel lblNewLabel = new JLabel("Manga Odyssey\r\n");
		lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 49));
		lblNewLabel.setBounds(10, 21, 439, 59);
		frame.getContentPane().add(lblNewLabel);
	}

}
