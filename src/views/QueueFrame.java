package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class QueueFrame extends JFrame{
	
	private JPanel mainPanel;
	
	public QueueFrame()
	{
		this.setBounds(400, 200, 1000, 100);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.red);
		this.setTitle("Marvel Wars");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		
		ImageIcon logo = new ImageIcon("Marvel icon.jpg");
		ImageIcon labelIcon = new ImageIcon(new ImageIcon("marvel logo2.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));	//scaling an image
		Font myFont1 = new Font("Serif", Font.BOLD, 30);
		Font myFont2 = new Font("SansSerif", Font.BOLD, 35);
		this.setIconImage(logo.getImage());
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout());
		
		this.add(mainPanel);
		
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
}
