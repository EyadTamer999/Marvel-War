package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CastFrame extends JFrame{
	
	private JButton leaderButton;
	
	private JPanel abilitiesPanel;
	private JPanel ability1Panel;
	private JPanel ability2Panel;
	private JPanel ability3Panel;
	
	public CastFrame()
	{
		this.setBounds(430, 150, 600, 600);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.red);
		this.setTitle("Marvel Wars");
		this.setLayout(new BorderLayout());
		
		ImageIcon logo = new ImageIcon("Marvel icon.jpg");
		ImageIcon labelIcon = new ImageIcon(new ImageIcon("marvel logo2.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));	//scaling an image
		Font myFont1 = new Font("Serif", Font.BOLD, 30);
		Font myFont2 = new Font("SansSerif", Font.BOLD, 35);
		this.setIconImage(logo.getImage());
		
		JLabel gameLabel = new JLabel("Choose Ability to cast!");
		gameLabel.setFont(myFont1);
		gameLabel.setForeground(Color.white);
		gameLabel.setHorizontalTextPosition(JLabel.RIGHT);
		gameLabel.setHorizontalAlignment(JLabel.CENTER);
		gameLabel.setVerticalAlignment(JLabel.CENTER);
		gameLabel.setIconTextGap(40); // sets a gap between the icon and the text in a gameLabel
		gameLabel.setVerticalTextPosition(JLabel.CENTER);
		gameLabel.setIcon(labelIcon);

		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(this.getWidth(), 70));
		titlePanel.setBackground(Color.blue);
		titlePanel.setLayout(new BorderLayout());
		titlePanel.add(gameLabel, BorderLayout.CENTER);
		
		this.add(titlePanel, BorderLayout.NORTH);
		//test
		
		leaderButton = new JButton("Leader Ability");
		leaderButton.setFocusable(false);
		
		abilitiesPanel = new JPanel();
		
		abilitiesPanel.setBackground(Color.black);
		
		abilitiesPanel.add(leaderButton);
		
		
		ability1Panel = new JPanel();
		ability1Panel.setBackground(Color.black); 
		ability1Panel.setPreferredSize(new Dimension(100, 100));
		ability1Panel.setBounds(0, 0, 100, 100);
		
		ability2Panel = new JPanel();
		ability2Panel.setBackground(Color.black);
		ability2Panel.setPreferredSize(new Dimension(100, 100));
		ability2Panel.setLayout(new GridLayout(0,1));
		ability2Panel.setBounds(0, 0, 100, 100);;

		
		ability3Panel = new JPanel();
		ability3Panel.setBackground(Color.black);
		ability3Panel.setPreferredSize(new Dimension(100, 100));
		ability3Panel.setBounds(0, 0, 100, 100);

		
		this.add(abilitiesPanel, BorderLayout.SOUTH);
		this.add(ability1Panel, BorderLayout.WEST);
		this.add(ability2Panel, BorderLayout.CENTER);
		//this.add(ability3Panel, BorderLayout.EAST);
		
		
		
	}


	public JPanel getAbility1Panel() {
		return ability1Panel;
	}


	public JPanel getAbility2Panel() {
		return ability2Panel;
	}


	public JPanel getAbility3Panel() {
		return ability3Panel;
	}


	public JButton getLeaderButton() {
		return leaderButton;
	}


	public JPanel getAbilitiesPanel() {
		return abilitiesPanel;
	}
	
	

}
