package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class GameFrame extends JFrame
{
	private JPanel boardPanel;
	private JLabel player1Label;
	private JLabel player2Label;
	private JPanel castingPanel;
	private JButton upMove;
	private JButton downMove;
	private JButton rightMove;
	private JButton leftMove;
	private JPanel attributesPanel;
	private JPanel mainAttributesPanel;
	private JPanel movePanel;
	private JButton attackUp;
	private JButton attackDown;
	private JButton attackRight;
	private JButton attackLeft;
	private JButton endButton;
	private JButton castButton;
	private JButton queueButton;
	private JButton playersButton;
	
	
	public GameFrame()
	{
		this.setBounds(140, 50, 1300, 750);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.red);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Marvel Wars");
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		
		ImageIcon logo = new ImageIcon("Marvel icon.jpg");
		ImageIcon labelIcon = new ImageIcon(new ImageIcon("marvel logo2.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));	//scaling an image
		Font myFont1 = new Font("Serif", Font.BOLD, 30);
		Font myFont2 = new Font("SansSerif", Font.BOLD, 35);
		this.setIconImage(logo.getImage());
		
		
		JLabel gameLabel = new JLabel("Now the game starts!");
		gameLabel.setFont(myFont1);
		gameLabel.setForeground(Color.white);
		gameLabel.setHorizontalTextPosition(JLabel.RIGHT);
		gameLabel.setHorizontalAlignment(JLabel.CENTER);
		gameLabel.setVerticalAlignment(JLabel.CENTER);
		gameLabel.setIconTextGap(40); // sets a gap between the icon and the text in a gameLabel
		gameLabel.setVerticalTextPosition(JLabel.CENTER);
		gameLabel.setIcon(labelIcon);
		
		player1Label = new JLabel();
		player1Label.setFont(new Font("SansSerif", Font.BOLD, 20));
		player1Label.setForeground(Color.white);
		player2Label = new JLabel();
		player2Label.setFont(new Font("SansSerif", Font.BOLD, 20));
		player2Label.setForeground(Color.white);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(this.getWidth(), 70));
		titlePanel.setBackground(Color.blue);
		titlePanel.setLayout(new BorderLayout());
		titlePanel.add(player1Label, BorderLayout.WEST);
		titlePanel.add(gameLabel, BorderLayout.CENTER);
		titlePanel.add(player2Label, BorderLayout.EAST);
		
		ImageIcon upArrow2 = new ImageIcon(new ImageIcon("upArrow2.jpg").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));	//scaling an image
		upMove = new JButton("Up");
		//upMove.setIcon(upArrow2);
		upMove.setFocusable(false);
		upMove.setPreferredSize(new Dimension(70, 20));
		upMove.setBackground(Color.white);
		
		
		ImageIcon downArrow = new ImageIcon(new ImageIcon("downArrow.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));	//scaling an image
		downMove = new JButton("Down");
		//downMove.setIcon(downArrow);
		downMove.setFocusable(false);
		downMove.setPreferredSize(new Dimension(70, 20));
		downMove.setBackground(Color.white);
		
		
		ImageIcon rightArrow = new ImageIcon(new ImageIcon("rightArrow.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));	//scaling an image
		rightMove = new JButton("Right");
		//rightMove.setIcon(rightArrow);
		rightMove.setFocusable(false);
		rightMove.setPreferredSize(new Dimension(70, 20));
		rightMove.setBackground(Color.white);
		
		ImageIcon leftArrow = new ImageIcon(new ImageIcon("leftArrow.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));	//scaling an image
		leftMove = new JButton("Left");
		//leftMove.setIcon(leftArrow);
		leftMove.setFocusable(false);
		leftMove.setPreferredSize(new Dimension(70, 20));
		leftMove.setBackground(Color.white);
		
		
		JLabel moveLabel = new JLabel("Move");
		moveLabel.setPreferredSize(new Dimension(50, 50));
		moveLabel.setBackground(Color.white);
		moveLabel.setFont(myFont1);
		moveLabel.setHorizontalAlignment(JLabel.CENTER);
		moveLabel.setForeground(Color.white);
		
		
		movePanel = new JPanel();
		movePanel.setLayout(new BorderLayout());
		movePanel.setBackground(Color.black);
		movePanel.setPreferredSize(new Dimension(250, 100));
		movePanel.add(upMove, BorderLayout.NORTH);
		movePanel.add(downMove, BorderLayout.SOUTH);
		movePanel.add(rightMove, BorderLayout.EAST);
		movePanel.add(leftMove, BorderLayout.WEST);
		movePanel.add(moveLabel, BorderLayout.CENTER);
		movePanel.revalidate();
		movePanel.repaint();
		
		
		mainAttributesPanel = new JPanel();
		mainAttributesPanel.setPreferredSize(new Dimension(250, this.getHeight()));
		mainAttributesPanel.setBackground(Color.black);
		mainAttributesPanel.setLayout(new GridLayout(0, 1));


		
		
		attributesPanel = new JPanel();
		attributesPanel.setPreferredSize(new Dimension(250, this.getHeight()));
		attributesPanel.setBackground(Color.black);
		attributesPanel.setLayout(new GridLayout(0, 1));
		
		

		
		
		castingPanel = new JPanel();
		castingPanel.setPreferredSize(new Dimension(250,150));
		castingPanel.setBounds(0,0, 250, 150);
		castingPanel.setLayout(new BorderLayout());
		
		
		mainAttributesPanel.add(attributesPanel, BorderLayout.NORTH);
		castingPanel.add(movePanel, BorderLayout.EAST);
		mainAttributesPanel.revalidate();
		mainAttributesPanel.repaint();

		
		endButton = new JButton("End Turn");
		endButton.setPreferredSize(new Dimension(200, 30));
		endButton.setFocusable(false);
		endButton.setBounds(0, 0, 200, 30);
		
		castButton = new JButton("Cast Ability");
		castButton.setPreferredSize(new Dimension(200, 30));
		castButton.setFocusable(false);
		castButton.setBounds(0, 0, 200, 30);
		
		
		queueButton = new JButton("View Order");
		queueButton.setPreferredSize(new Dimension(200, 30));
		queueButton.setFocusable(false);
		queueButton.setBounds(0, 0, 200, 30);
		
		
		playersButton = new JButton("Players Details");
		playersButton.setPreferredSize(new Dimension(200, 30));
		playersButton.setFocusable(false);
		playersButton.setBounds(0, 0, 200, 30);
		
		
		JPanel attackPanel = new JPanel();
		attackPanel.setLayout(new BorderLayout());
		attackPanel.setBackground(Color.black);
		attackPanel.setPreferredSize(new Dimension(250, 100));
		attackUp = new JButton("Up");
		attackUp.setForeground(Color.black);
		attackUp.setFocusable(false);
		attackUp.setBackground(Color.white);
		
		attackDown = new JButton("Down");
		attackDown.setForeground(Color.black);
		attackDown.setFocusable(false);
		attackDown.setBackground(Color.white);

		attackLeft = new JButton("Left");
		attackLeft.setForeground(Color.black);
		attackLeft.setFocusable(false);
		attackLeft.setBackground(Color.white);

		attackRight = new JButton("Right");
		attackRight.setForeground(Color.black);
		attackRight.setFocusable(false);
		attackRight.setBackground(Color.white);

		JLabel attackLabel = new JLabel("Attack");
		attackLabel.setFont(myFont1);
		attackLabel.setForeground(Color.white);
		attackLabel.setHorizontalAlignment(JLabel.CENTER);
		
		attackPanel.add(attackUp, BorderLayout.NORTH);
		attackPanel.add(attackLabel, BorderLayout.CENTER);
		attackPanel.add(attackDown, BorderLayout.SOUTH);
		attackPanel.add(attackRight, BorderLayout.EAST);
		attackPanel.add(attackLeft, BorderLayout.WEST);
		
		
		JPanel endPanel = new JPanel();
		endPanel.setBounds(0,0, 50, 50);
		endPanel.add(endButton);
		endPanel.setBackground(Color.black);
		endPanel.add(castButton);
		endPanel.add(queueButton);
		endPanel.add(playersButton);
		
		castingPanel.add(attackPanel, BorderLayout.CENTER);
		castingPanel.add(endPanel, BorderLayout.NORTH);
		
		
		
		
		
		
		
		
		
		
		boardPanel = new JPanel();
		boardPanel.setPreferredSize(new Dimension(550, 300));
		boardPanel.setBounds(0,0, 550,300);
		boardPanel.setLayout(new GridLayout(5,5));
		
		
		
		
		this.add(mainAttributesPanel, BorderLayout.EAST);
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(castingPanel, BorderLayout.SOUTH);

		
		
		
	}



	public JButton getPlayersButton() {
		return playersButton;
	}



	public JButton getCastButton() {
		return castButton;
	}



	public JButton getQueueButton() {
		return queueButton;
	}



	public JButton getAttackUp() {
		return attackUp;
	}



	public JButton getAttackDown() {
		return attackDown;
	}



	public JButton getAttackRight() {
		return attackRight;
	}



	public JButton getAttackLeft() {
		return attackLeft;
	}



	public JPanel getMainAttributesPanel() {
		return mainAttributesPanel;
	}



	public JPanel getMovePanel() {
		return movePanel;
	}



	public JButton getDownMove() {
		return downMove;
	}



	public JButton getRightMove() {
		return rightMove;
	}



	public JButton getLeftMove() {
		return leftMove;
	}



	public JButton getUpMove() {
		return upMove;
	}



	public JLabel getPlayer1Label() {
		return player1Label;
	}

	public JButton getEndButton() {
		return endButton;
	}



	public JLabel getPlayer2Label() {
		return player2Label;
	}

	public JPanel getCastingPanel() {
		return castingPanel;
	}

	public JPanel getAttributesPanel() {
		return attributesPanel;
	}

	public JPanel getBoardPanel() {
		return boardPanel;
	}
	

}
