import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 869, 677);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Main Label
		
		JLabel mainLabel = new JLabel("New label");
		mainLabel.setBounds(307, 100, 524, 455);
		frame.getContentPane().add(mainLabel);
	
		// Button 0
		
		JButton button = new JButton("Question 11");	
		button.setBounds(28, 100, 256, 54);
		frame.getContentPane().add(button);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		
		// Button 1
		
		JButton button_1 = new JButton("Question 12");
		button_1.setBounds(28, 200, 256, 54);
		frame.getContentPane().add(button_1);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		
		// Button 2
		
		JButton button_2 = new JButton("Question 13");
		button_2.setBounds(28, 300, 256, 54);
		frame.getContentPane().add(button_2);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		
		// Button 3
		
		JButton button_3 = new JButton("Question 14");
		button_3.setBounds(28, 400, 256, 54);
		frame.getContentPane().add(button_3);
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mainLabel.setText("Diocan2");
			}
		});
		
		// Button 4
		
		JButton button_4 = new JButton("Question 15");
		button_4.setBounds(28, 500, 256, 54);
		frame.getContentPane().add(button_4);
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mainLabel.setText("Diocan");
			}
		});


	}
}
