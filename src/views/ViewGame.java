package views;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class ViewGame extends JFrame
{
	private JPanel mainPanel;
	private JPanel boardPanel;
	private JPanel champSelectPanel;
	private JPanel playerNamesPanel;
	private JButton submit_Button;
	private JTextField player1Text;
	private JTextField player2Text;
	
	
	public JPanel getMainPanel() {
		return mainPanel;
	}



	public JPanel getBoardPanel() {
		return boardPanel;
	}



	public JPanel getChampSelectPanel() {
		return champSelectPanel;
	}



	public JPanel getPlayerNamesPanel() {
		return playerNamesPanel;
	}



	public JButton getSubmit_Button() {
		return submit_Button;
	}



	public ViewGame()
	{
		this.setBounds(140, 50, 1300, 750);
		this.setVisible(true);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.red);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Marvel Wars");
		ImageIcon logo = new ImageIcon("Marvel icon.jpg");
		ImageIcon labelIcon = new ImageIcon(new ImageIcon("marvel logo2.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));	//scaling an image
		this.setIconImage(logo.getImage());
		this.setLayout(new GridLayout(0, 1));
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(this.getWidth(), 70));
		mainPanel.setBounds(0, 0, this.getWidth(), 70);
		mainPanel.setBackground(Color.blue);
		mainPanel.setLayout(new FlowLayout());
		JLabel welcomeText = new JLabel("Welcome to Marvel Wars!!");
		welcomeText.setHorizontalTextPosition(JLabel.RIGHT);
		welcomeText.setIconTextGap(60); // sets a gap between the icon and the text in a label
		welcomeText.setVerticalTextPosition(JLabel.CENTER);
		welcomeText.setIcon(labelIcon);
		
		Font myFont2 = new Font("SansSerif", Font.BOLD, 20);

		welcomeText.setForeground(Color.white);
		welcomeText.setFont(new Font("Serif", Font.BOLD, 30));
		mainPanel.add(welcomeText);
		this.add(mainPanel, BorderLayout.NORTH);
		
		playerNamesPanel = new JPanel();
		playerNamesPanel.setLayout(new BorderLayout());
		JPanel player1Panel = new JPanel();
		JPanel player2Panel = new JPanel();
		
		player1Text = new JTextField();
		player2Text = new JTextField();
		
		JLabel player1Label = new JLabel("Please enter the first Player's name: ");
		player1Label.setForeground(Color.black);
		JLabel player2Label = new JLabel("Please enter the second Player's name: ");
		player2Label.setForeground(Color.black);
		player1Text.setPreferredSize(new Dimension(250, 40));
		
		submit_Button = new JButton("Submit");
		submit_Button.setBounds(0,0, 400,70);
		submit_Button.setPreferredSize(new Dimension(400, 70));
		submit_Button.setFont(myFont2);
		submit_Button.setBackground(Color.black);
		submit_Button.setForeground(Color.white);
		//submit_Button.addActionListener((ActionListener) this);
		
		player2Text.setPreferredSize(new Dimension(250, 40));
		player2Label.setFont(myFont2);
		player1Label.setFont(myFont2);
		
		JPanel submitPanel = new JPanel();
		submitPanel.setBackground(Color.red);
		submitPanel.add(submit_Button);
				
		
		
		player1Panel.add(player1Label, BorderLayout.WEST);
		player1Panel.add(player1Text, BorderLayout.EAST);
		player2Panel.add(player2Label, BorderLayout.WEST);
		player2Panel.add(player2Text, BorderLayout.EAST);
		

		player1Panel.setBackground(Color.red);
		player2Panel.setBackground(Color.red);
		this.add(player1Panel);
		this.add(player2Panel);
		this.add(submitPanel);
		

		this.revalidate();
		this.repaint();
	}
	
	

	public String getPlayer1Text() {
		return player1Text.getText();
	}



	public String getPlayer2Text() {
		return player2Text.getText();
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
