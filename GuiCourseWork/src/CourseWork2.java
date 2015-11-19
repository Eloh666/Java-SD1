import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class CourseWork2 {

	private JFrame frmCourseworkGui;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseWork2 window = new CourseWork2();
					window.frmCourseworkGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public CourseWork2() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		
		Parser parser = new Parser(2004,2015);
		
		frmCourseworkGui = new JFrame();
		frmCourseworkGui.setAlwaysOnTop(true);
		frmCourseworkGui.setTitle("CourseWork 2 GUI");
		frmCourseworkGui.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmCourseworkGui.setBounds(100, 100, 852, 535);
		frmCourseworkGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCourseworkGui.getContentPane().setLayout(null);
		
		JTextPane formattedTextField = new JTextPane();
		formattedTextField.setBackground(Color.DARK_GRAY);
		formattedTextField.setForeground(Color.GREEN);
		formattedTextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		formattedTextField.setBounds(354, 36, 460, 412);
		frmCourseworkGui.getContentPane().add(formattedTextField);
		formattedTextField.setEditable(false);
		
		JCheckBox graphCheckBox = new JCheckBox("Grapical Output");
		graphCheckBox.setBackground(Color.LIGHT_GRAY);
		graphCheckBox.setBounds(354, 455, 208, 23);
		frmCourseworkGui.getContentPane().add(graphCheckBox);
		
		JCheckBox consoleCheckBox = new JCheckBox("Console Output");
		consoleCheckBox.setBackground(Color.LIGHT_GRAY);
		consoleCheckBox.setBounds(620, 455, 194, 23);
		frmCourseworkGui.getContentPane().add(consoleCheckBox);
		
		graphCheckBox.setSelected(true);
		
		
		JButton button1 = new JButton("Question 11");
		button1.setBackground(Color.GRAY);
		button1.setBounds(23, 36, 298, 51);
		frmCourseworkGui.getContentPane().add(button1);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(graphCheckBox.isSelected())
					formattedTextField.setText(parser.question11());
				if(consoleCheckBox.isSelected())
					System.out.println(parser.question11());
			}
		});		
		
		JButton button2 = new JButton("Question 12");
		button2.setBackground(Color.GRAY);
		button2.setBounds(23, 122, 298, 51);
		frmCourseworkGui.getContentPane().add(button2);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(graphCheckBox.isSelected())
					formattedTextField.setText(parser.question12());
				if(consoleCheckBox.isSelected())
					System.out.println(parser.question12());
			}
		});			
				
		JButton button3 = new JButton("Question 13");
		button3.setBackground(Color.GRAY);
		button3.setBounds(23, 210, 298, 51);
		frmCourseworkGui.getContentPane().add(button3);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(graphCheckBox.isSelected())
					formattedTextField.setText(parser.question13());
				if(consoleCheckBox.isSelected())
					System.out.println(parser.question13());
			}
		});	
		
		JButton button4 = new JButton("Question 14");
		button4.setBackground(Color.GRAY);
		button4.setBounds(23, 296, 298, 51);
		frmCourseworkGui.getContentPane().add(button4);
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(graphCheckBox.isSelected())
					formattedTextField.setText(parser.question14());
				if(consoleCheckBox.isSelected())
					System.out.println(parser.question14());
			}
		});	
		
		JButton button5 = new JButton("Question 15");
		button5.setBackground(Color.GRAY);
		button5.setBounds(23, 394, 298, 51);
		frmCourseworkGui.getContentPane().add(button5);
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(graphCheckBox.isSelected())
					formattedTextField.setText(parser.question15());
				if(consoleCheckBox.isSelected())
					System.out.println(parser.question15());
			}
		});	
	}
}
