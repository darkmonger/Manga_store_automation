import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin_main_page {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_main_page window = new Admin_main_page();
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
	public Admin_main_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1090, 698);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAdmin = new JLabel("Admin page");
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAdmin.setBounds(21, 76, 637, 82);
		frame.getContentPane().add(lblAdmin);
		
		JButton btnManga = new JButton("Mangas");
		btnManga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin_manga_page adminmanga = new Admin_manga_page();
				adminmanga.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnManga.setIcon(new ImageIcon("C:\\Users\\armi0\\Downloads\\chainsaw_ kopya2.jpg"));
		btnManga.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnManga.setBounds(21, 212, 275, 400);
		frame.getContentPane().add(btnManga);
		
		JButton btnCustomer = new JButton("");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Admin_customer_info_page admincustomer = new Admin_customer_info_page();
				admincustomer.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCustomer.setIcon(new ImageIcon("C:\\Users\\armi0\\Downloads\\customercrop.jpg"));
		btnCustomer.setBounds(306, 212, 340, 400);
		frame.getContentPane().add(btnCustomer);
		
		JButton BtnOrder = new JButton("New button");
		BtnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				admin_customer_orders_page adminorder = new admin_customer_orders_page();
				adminorder.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		BtnOrder.setIcon(new ImageIcon("C:\\Users\\armi0\\Downloads\\orderscrop.jpg"));
		BtnOrder.setBounds(656, 217, 302, 395);
		frame.getContentPane().add(BtnOrder);
		
		JLabel lblNewLabel = new JLabel("Mangas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(35, 168, 107, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblCustomers = new JLabel("Customers");
		lblCustomers.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCustomers.setBounds(306, 168, 107, 34);
		frame.getContentPane().add(lblCustomers);
		
		JLabel lblOrders = new JLabel("Orders");
		lblOrders.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOrders.setBounds(656, 173, 107, 34);
		frame.getContentPane().add(lblOrders);
		
		JLabel lblNewLabel_1 = new JLabel("Manga Odyssey\r\n");
		lblNewLabel_1.setFont(new Font("Algerian", Font.PLAIN, 49));
		lblNewLabel_1.setBounds(10, 22, 439, 59);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
