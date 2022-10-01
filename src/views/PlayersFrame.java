package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayersFrame extends JFrame{
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	
	
	public PlayersFrame()
	{
		this.setBounds(400, 200, 1000, 500);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.black);
		this.setTitle("Marvel Wars");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		
		ImageIcon logo = new ImageIcon("Marvel icon.jpg");
		ImageIcon labelIcon = new ImageIcon(new ImageIcon("marvel logo2.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));	//scaling an image
		Font myFont1 = new Font("Serif", Font.BOLD, 30);
		Font myFont2 = new Font("SansSerif", Font.BOLD, 35);
		this.setIconImage(logo.getImage());
		
		
		panel1 = new JPanel();
		panel1.setBackground(Color.black);
		panel1.setLayout(new GridLayout(0, 1));
		
		panel2 = new JPanel();
		panel2.setBackground(Color.black);
		panel2.setLayout(new GridLayout(0, 1));

		panel3 = new JPanel();
		panel3.setBackground(Color.black);
		panel3.setLayout(new GridLayout(0, 1));

		
		
		this.add(panel1, BorderLayout.WEST);
		this.add(panel2, BorderLayout.CENTER);
		this.add(panel3, BorderLayout.EAST);
	}


	public JPanel getPanel1() {
		return panel1;
	}


	public JPanel getPanel2() {
		return panel2;
	}


	public JPanel getPanel3() {
		return panel3;
	}
	
	

}
