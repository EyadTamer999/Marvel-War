package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import model.world.Champion;

import java.util.ArrayList;


public class ChampSelectFrame extends JFrame{
	private ArrayList<Champion> availableChampions;
	private JLabel label;
	private JPanel championsPanel;
	private JPanel champAttributesPanel;
	private JPanel champAddPanel;
	private JButton addButton;
	


	public JLabel getLabel() {
		return label;
	}


	public JPanel getChampionsPanel() {
		return championsPanel;
	}


	public ArrayList<Champion> getAvailableChampions() {
		return availableChampions;
	}


	public ChampSelectFrame() 
	{		
		this.setBounds(140, 50, 1300, 750); 
		this.setVisible(true);
		this.getContentPane().setBackground(Color.red);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Marvel Wars");
		this.setResizable(false);
		
		ImageIcon logo = new ImageIcon("Marvel icon.jpg");
		ImageIcon labelIcon = new ImageIcon(new ImageIcon("marvel logo2.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));	//scaling an image
		Font myFont1 = new Font("Serif", Font.BOLD, 30);
		Font myFont2 = new Font("SansSerif", Font.BOLD, 35);
		this.setIconImage(logo.getImage());

		
		label = new JLabel("Each Player choose 3 champions, first champion choosen is the leader.");
		label.setFont(myFont1);
		label.setForeground(Color.white);
		label.setHorizontalTextPosition(JLabel.RIGHT);
		label.setIconTextGap(60); // sets a gap between the icon and the text in a label
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setIcon(labelIcon);
		
		
		championsPanel = new JPanel();
		championsPanel.setLayout(new GridLayout(0, 3));
		
		
		champAttributesPanel = new JPanel();
		champAttributesPanel.setPreferredSize(new Dimension(250, this.getHeight()));
		champAttributesPanel.setBackground(Color.black);
		champAttributesPanel.setLayout(new GridLayout(0, 1));
		
		champAddPanel = new JPanel();
		champAddPanel.setPreferredSize(new Dimension(750, 70));
		champAddPanel.setBackground(Color.blue);
		
		addButton = new JButton("Add Champion");
		addButton.setPreferredSize(new Dimension(500, 50));
		addButton.setBackground(Color.yellow);
		addButton.setFont(myFont1);
		addButton.setFocusable(false);
		champAddPanel.add(addButton);
		
		
		JLabel detailsLabel = new JLabel("Each Player choose 3 champs, first champ choosen is the leader.");
		
		
		
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(this.getWidth(), 70));
		titlePanel.setBackground(Color.blue);
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(label);
		
		
		
		this.getContentPane().add(titlePanel, BorderLayout.NORTH);
		this.getContentPane().add(championsPanel, BorderLayout.CENTER);
		this.getContentPane().add(champAttributesPanel, BorderLayout.EAST);
		this.getContentPane().add(champAddPanel, BorderLayout.SOUTH);
		this.revalidate();
		this.repaint();

		this.setIconImage(logo.getImage());
		this.setLayout(new BorderLayout(0, 0));
	}


	public JPanel getChampAttributesPanel() {
		return champAttributesPanel;
	}


	public JPanel getChampAddPanel() {
		return champAddPanel;
	}


	public JButton getAddButton() {
		return addButton;
	}
		
	

}
