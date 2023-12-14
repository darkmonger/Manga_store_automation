import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Results_page {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Results_page window = new Results_page();
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
	public Results_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 652);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manga Odyssey\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 49));
		lblNewLabel.setBounds(22, 10, 439, 59);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblResults = new JLabel("Results");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblResults.setBounds(22, 88, 637, 82);
		frame.getContentPane().add(lblResults);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCheckout.setBounds(725, 521, 130, 43);
		frame.getContentPane().add(btnCheckout);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack.setBounds(551, 521, 130, 43);
		frame.getContentPane().add(btnGoBack);
	}

}
