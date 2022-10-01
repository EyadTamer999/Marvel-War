package views;

import engine.Game;
import engine.Player;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.*;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;


public class Controller implements ActionListener, MouseListener{
	private ViewGame view;
	private Game game;
	private GameFrame boardGame;
	private ChampSelectFrame champSelect;
	private ArrayList<JButton> buttons;
	private JButton[][] boardButtons = new JButton[5][5];
	private JLabel nameLabel = new JLabel();
	private JLabel typeLabel = new JLabel();
	private JLabel manaLabel = new JLabel();
	private JLabel rangeLabel = new JLabel();
	private JLabel damageLabel = new JLabel();
	private JLabel speedLabel = new JLabel();
	private JLabel currenthpLabel = new JLabel();
	private JLabel maxhpLabel = new JLabel();
	private JLabel abilitiesLabel = new JLabel();
	private JLabel ability1Label = new JLabel();
	private JLabel ability2Label = new JLabel();
	private JLabel ability3Label = new JLabel();
	private JLabel labelAbility;
	private JLabel currentActionPointsLabel = new JLabel();
	private JLabel actionPointsLabel = new JLabel();
	private JLabel appliedEffectsLabel = new JLabel();
	private int buttonIndex;
	private ArrayList<JButton> abilityButtons = new ArrayList<>();
	private QueueFrame queueFrame;
	private CastFrame castFrame;
	private int selectedI;
	private int selectedJ;
	private JButton selectedButton;
	private ArrayList<JButton> champion1Buttons = new ArrayList<>();
	private ArrayList<JButton> champion2Buttons = new ArrayList<>();
	private PlayersFrame playerDetails;

	




	
	public Controller()
	{
		this.buttons = new ArrayList<>();
		view = new ViewGame();
		view.getSubmit_Button().addActionListener(this);
	}
	
	public static void main(String args[])
	{
		new Controller();
	}
	
	
	public void selectChampions()
	{
		Font myFont2 = new Font("SansSerif", Font.BOLD, 20);
		champSelect = new ChampSelectFrame();
		champSelect.getAddButton().addActionListener(this);
		for(int i = 0; i < Game.getAvailableChampions().size(); i++)
		{
			JButton b = new JButton();
			b.setFocusable(false);
			b.setBackground(Color.red);
			b.setForeground(Color.black);
			b.setFont(myFont2);
			buttons.add(b);
			b.addActionListener(this);
			b.setBorder(BorderFactory.createLineBorder(Color.black, 10));
			b.setText(Game.getAvailableChampions().get(i).getName());
			champSelect.getChampionsPanel().add(b);
		}		
	}
	
	
	public void boardLoadout()
	{
		boardGame = new GameFrame();
		boardGame.getPlayer1Label().setText("First Player: " + game.getFirstPlayer().getName());
		boardGame.getPlayer2Label().setText("Second Player: " + game.getSecondPlayer().getName());

		castFrame = new CastFrame();
		castFrame.setVisible(false);
		Font myFont2 = new Font("SansSerif", Font.BOLD, 25);
		reDrawBoard();
		updateChampAttributes();
		
		
		
		boardGame.getUpMove().addActionListener(this);
		boardGame.getDownMove().addActionListener(this);
		boardGame.getRightMove().addActionListener(this);
		boardGame.getLeftMove().addActionListener(this);
		boardGame.getAttackUp().addActionListener(this);
		boardGame.getAttackDown().addActionListener(this);
		boardGame.getAttackRight().addActionListener(this);
		boardGame.getAttackLeft().addActionListener(this);
		boardGame.getEndButton().addActionListener(this);
		boardGame.getQueueButton().addActionListener(this);
		boardGame.getCastButton().addActionListener(this);
		boardGame.getPlayersButton().addActionListener(this);
		
		
		
	}
	
	public boolean isContained(Object[][] board, JButton b)
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				if(board[i][j].equals(b))
				{
					selectedI = i;
					selectedJ = j;
					return true;
				}
			}
		}
		return false;
	}
	
	
	public void reDrawBoard()
	{
		ImageIcon cone = new ImageIcon(new ImageIcon("cone.jpg").getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));	//scaling an image
		Font myFont2 = new Font("SansSerif", Font.BOLD, 15);
		boardGame.getBoardPanel().removeAll();

		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				JButton b = new JButton();
				if(game.getBoard()[i][j] == null)
				{
					b.setBackground(Color.red);
				}
				else if(game.getBoard()[i][j] instanceof Cover)
				{
					b.setText("Cover " + ((Cover)game.getBoard()[i][j]).getCurrentHP() + "HP");
					b.setBackground(Color.green);

				}
				else
				{
					b.setText(((Champion)game.getBoard()[i][j]).getName() + "  " + ((Champion)game.getBoard()[i][j]).getCurrentHP() + "HP");
					if(((Champion)game.getBoard()[i][j]).equals(game.getCurrentChampion()))
					{
						b.setBackground(Color.magenta);
					}
					else
					{
						if(game.getFirstPlayer().getTeam().contains((Champion)game.getBoard()[i][j]) )
						{
							b.setBackground(Color.yellow);
						}
						else
						{
							b.setBackground(Color.blue);
						}
 
					}
						
					
				}
				b.setFocusable(false);
				b.setFont(myFont2);
				b.addActionListener(this);
				
				b.setForeground(Color.black);
				b.setBorder(BorderFactory.createLineBorder(Color.black, 10));
				boardButtons[i][j] = b;
				boardGame.getBoardPanel().add(b);
				boardGame.getBoardPanel().revalidate();
				boardGame.getBoardPanel().repaint();
				
			}
		}
	}
	
	
	public void queueLoader()
	{
		System.out.println("Reached!");
		queueFrame = new QueueFrame();
		
		ArrayList<Champion> current = new ArrayList<>();
		while(!game.getTurnOrder().isEmpty())
		{
			current.add((Champion)game.getTurnOrder().peekMin());
			game.getTurnOrder().remove();
		}
		
		for(int i = 0; i < current.size(); i++)
		{
			JButton b = new JButton();
			b.setBackground(Color.black);
			b.setForeground(Color.white);
			b.setFocusable(false);
			b.setBounds(0, 0 , 200, 100);
			b.setText(current.get(i).getName());
			queueFrame.getMainPanel().add(b);
			game.getTurnOrder().insert(current.get(i));
		}
		queueFrame.getMainPanel().revalidate();
		queueFrame.getMainPanel().repaint();
		queueFrame.revalidate();
		queueFrame.repaint();
	}
	
	public void castLoader()
	{
		castFrame = new CastFrame();
		castFrame.setVisible(true);
		Champion c = game.getCurrentChampion();
		abilityButtons = new ArrayList<>();
		
		for(Ability a: c.getAbilities())
		{
			JButton b  = new JButton(a.getName());
			b.setFocusable(false);
			b.addActionListener(this);
			b.addMouseListener(this);
			abilityButtons.add(b);
 			castFrame.getAbility1Panel().add(b);

		}
		
		
		
		castFrame.getLeaderButton().addActionListener(this);

	}
	
	
	public void labelizeAbilities(Ability a, JPanel p)
	{
		labelAbility = new JLabel();
		labelAbility.setPreferredSize(new Dimension(100,100));
		labelAbility.setBounds(20, 0, 100, 100);
		labelAbility.setHorizontalAlignment(JLabel.CENTER);
		String type;
		String abilityExtra;
		labelAbility.setForeground(Color.white);
		if(a instanceof DamagingAbility)
		{
			type = "Damaging Ability";
			abilityExtra = "Damaging Amount: " + ((DamagingAbility)a).getDamageAmount();
		}
		else if(a instanceof HealingAbility)
		{
			type = "Healing Ability";
			abilityExtra = "Healing Amount: " + (((HealingAbility) a).getHealAmount());
		}
		else
		{
			type = "Crowd Control Ability";
			abilityExtra = "Effect: " + ((CrowdControlAbility)a).getEffect().getName() + "   Effect Duration: " 
			+ ((CrowdControlAbility)a).getEffect().getDuration();
		}
		String s = "Ability Name: " + a.getName();
		labelAbility.setText(s);
		p.add(labelAbility);
		labelAbility = new JLabel();
		labelAbility.setForeground(Color.white);
		labelAbility.setHorizontalAlignment(JLabel.CENTER);


		s = "Ability Type: " + type;
		labelAbility.setText(s);
		p.add(labelAbility);
		labelAbility = new JLabel();
		labelAbility.setForeground(Color.white);
		labelAbility.setHorizontalAlignment(JLabel.CENTER);


		s = "Area Of Effect: " + a.getCastArea();
		labelAbility.setText(s);
		p.add(labelAbility);
		labelAbility = new JLabel();
		labelAbility.setForeground(Color.white);
		labelAbility.setHorizontalAlignment(JLabel.CENTER);


		s = "Cast Range: " + a.getCastRange();
		labelAbility.setText(s);
		p.add(labelAbility);
		labelAbility = new JLabel();
		labelAbility.setForeground(Color.white);
		labelAbility.setHorizontalAlignment(JLabel.CENTER);


		s = "Mana Cost: " + a.getManaCost();
		labelAbility.setText(s);
		p.add(labelAbility);
		labelAbility = new JLabel();
		labelAbility.setForeground(Color.white);
		labelAbility.setHorizontalAlignment(JLabel.CENTER);


		s = "Actions Cost: " + a.getRequiredActionPoints();
		labelAbility.setText(s);
		p.add(labelAbility);
		labelAbility = new JLabel();
		labelAbility.setForeground(Color.white);
		labelAbility.setHorizontalAlignment(JLabel.CENTER);


		s = "Current Cooldown: " + a.getCurrentCooldown();
		labelAbility.setText(s);
		p.add(labelAbility);
		labelAbility = new JLabel();
		labelAbility.setForeground(Color.white);
		labelAbility.setHorizontalAlignment(JLabel.CENTER);


		s = "Base Cooldown: " + a.getBaseCooldown();
		labelAbility.setText(s);
		p.add(labelAbility);
		labelAbility = new JLabel();
		labelAbility.setForeground(Color.white);
		labelAbility.setHorizontalAlignment(JLabel.CENTER);


		s = abilityExtra;
		labelAbility.setText(s);
		p.add(labelAbility);
				
		
	}
	
	public void disableEverything()
	{
		Font myFont2 = new Font("SansSerif", Font.BOLD, 20);
		boardGame.getBoardPanel().removeAll();

		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				JButton b = new JButton();
				if(game.getBoard()[i][j] == null)
				{
					b.setBackground(Color.red);
				}
				else if(game.getBoard()[i][j] instanceof Cover)
				{
					b.setText("Cover");
					b.setBackground(Color.green);

				}
				else
				{
					b.setText(((Champion)game.getBoard()[i][j]).getName());
					if(game.getFirstPlayer().getTeam().contains((Champion)game.getBoard()[i][j]) )
					{
						b.setBackground(Color.yellow);
					}
					else
					{
						b.setBackground(Color.white);
					}

				}
				b.setFocusable(false);
				b.setFont(myFont2);
				b.setEnabled(false);
				
				b.setForeground(Color.black);
				b.setBorder(BorderFactory.createLineBorder(Color.black, 10));
				boardGame.getBoardPanel().add(b);
				boardGame.getBoardPanel().revalidate();
				boardGame.getBoardPanel().repaint();
				
				
				boardGame.getAttackRight().setEnabled(false);
				boardGame.getAttackLeft().setEnabled(false);
				boardGame.getAttackUp().setEnabled(false);
				boardGame.getAttackDown().setEnabled(false);
				boardGame.getUpMove().setEnabled(false);
				boardGame.getDownMove().setEnabled(false);
				boardGame.getRightMove().setEnabled(false);
				boardGame.getLeftMove().setEnabled(false);
				boardGame.getCastButton().setEnabled(false);
				boardGame.getQueueButton().setEnabled(false);
				boardGame.getEndButton().setEnabled(false);
				
				boardGame.getBoardPanel().revalidate();
				boardGame.getBoardPanel().repaint();
			}
		}
	}
	
	public void playerDetailsLoad()
	{
		Font myFont2 = new Font("SansSerif", Font.BOLD, 20);
		champion1Buttons = new ArrayList<>();
		champion2Buttons = new ArrayList<>();
		playerDetails = new PlayersFrame();
		JLabel label1 = new JLabel("First Player: " + game.getFirstPlayer().getName());
		label1.setFont(myFont2);
		label1.setForeground(new Color(0xff0000));
		playerDetails.getPanel1().add(label1);
		label1 = new JLabel("Leader Ability Used: " + game.isFirstLeaderAbilityUsed());
		label1.setFont(myFont2);
		label1.setForeground(new Color(0x00ffff));
		playerDetails.getPanel1().add(label1);

		label1 = new JLabel("Leader: " + game.getFirstPlayer().getLeader().getName());
		playerDetails.getPanel1().add(label1);

		label1.setFont(myFont2);
		label1.setForeground(new Color(0x00ffff));
		playerDetails.getPanel1().add(label1);
		
		for(Champion c: game.getFirstPlayer().getTeam())
		{
			JButton b = new JButton(c.getName());
			b.setBackground(Color.black);
			b.setForeground(Color.white);
			b.setFocusable(false);
			b.setFont(myFont2);
			b.setBorder(null);
			champion1Buttons.add(b);
			b.addActionListener(this);
			playerDetails.getPanel1().add(b);
		}
		JLabel label2 = new JLabel("Second Player: " + game.getSecondPlayer().getName());
		label2.setForeground(new Color(0xff0000));
		label2.setFont(myFont2);
		playerDetails.getPanel3().add(label2);
		label2 = new JLabel("Leader Ability Used: " + game.isSecondLeaderAbilityUsed());
		label2.setFont(myFont2);
		label2.setForeground(new Color(0x00ffff));
		playerDetails.getPanel3().add(label2);
		label2 = new JLabel("Leader: " + game.getSecondPlayer().getLeader().getName());
		label2.setFont(myFont2);
		label2.setForeground(new Color(0x00ffff));
		playerDetails.getPanel3().add(label2);
		
		for(Champion c: game.getSecondPlayer().getTeam())
		{
			JButton b = new JButton(c.getName());
			b.setBackground(Color.black);
			b.setForeground(Color.white);
			b.setFocusable(false);
			b.setFont(myFont2);
			b.setBorder(null);
			champion2Buttons.add(b);
			b.addActionListener(this);
			playerDetails.getPanel3().add(b);
		}
	}
	
	public void playerPanel2Update(Champion c)
	{
		playerDetails.getPanel2().removeAll();
		if(c instanceof Hero)
		{
			typeLabel.setText("Hero type: Hero");
		}
		else if(c instanceof Villain)
		{
			typeLabel.setText("Hero type: Villain");
		}
		else
		{
			typeLabel.setText("Hero type: AntiHero");
		}
		typeLabel.setForeground(Color.red);
		typeLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setText("Champion Name: " + c.getName());
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		manaLabel.setText("Mana: " + c.getMana()); 
		manaLabel.setHorizontalAlignment(JLabel.CENTER);
		rangeLabel.setText("Attack Range: " + c.getAttackRange());
		rangeLabel.setHorizontalAlignment(JLabel.CENTER);
		damageLabel.setText("Attack Damage: " + c.getAttackDamage());
		damageLabel.setHorizontalAlignment(JLabel.CENTER);
		speedLabel.setText("Speed: " + c.getSpeed()); 
		speedLabel.setHorizontalAlignment(JLabel.CENTER);
		currenthpLabel.setText("Current Health Points: " + c.getCurrentHP());
		currenthpLabel.setHorizontalAlignment(JLabel.CENTER);
		currenthpLabel.setForeground(new Color(0x00ffff));
		maxhpLabel.setText("Maximum Health Points: " + c.getMaxHP());
		maxhpLabel.setHorizontalAlignment(JLabel.CENTER);
		abilitiesLabel.setText("Avaliable Abilities: ");
		abilitiesLabel.setHorizontalAlignment(JLabel.CENTER);
		ability1Label.setText(c.getAbilities().get(0).getName() + ", ");
		ability1Label.setHorizontalAlignment(JLabel.CENTER);
		ability2Label.setText(c.getAbilities().get(1).getName() + ", ");
		ability2Label.setHorizontalAlignment(JLabel.CENTER);
		ability3Label.setText(c.getAbilities().get(2).getName());
		ability3Label.setHorizontalAlignment(JLabel.CENTER);
		currentActionPointsLabel.setText("Current Action Points: " + c.getCurrentActionPoints());
		currentActionPointsLabel.setForeground(new Color(0x00ffff));
		currentActionPointsLabel.setHorizontalAlignment(JLabel.CENTER);
		actionPointsLabel.setText("Maximum Action Points: " + c.getMaxActionPointsPerTurn());
		actionPointsLabel.setHorizontalAlignment(JLabel.CENTER);
		
		String s = "Applied Effects: ";
		for (int i = 0; i < c.getAppliedEffects().size(); i++)
		{
			s+= c.getAppliedEffects().get(i).getName() + "   Effect Duration: " + c.getAppliedEffects().get(i).getDuration();
			
		}
		appliedEffectsLabel.setText(s);
		appliedEffectsLabel.setHorizontalAlignment(JLabel.CENTER);

		appliedEffectsLabel.setForeground(Color.red);
		
		
		playerDetails.getPanel2().revalidate();
		playerDetails.getPanel2().repaint();
		
		
		
		playerDetails.getPanel2().add(nameLabel);
		playerDetails.getPanel2().add(typeLabel);
		playerDetails.getPanel2().add(manaLabel);
		playerDetails.getPanel2().add(rangeLabel);
		playerDetails.getPanel2().add(damageLabel);
		playerDetails.getPanel2().add(speedLabel);
		playerDetails.getPanel2().add(currenthpLabel);
		playerDetails.getPanel2().add(maxhpLabel);
		playerDetails.getPanel2().add(abilitiesLabel);
		playerDetails.getPanel2().add(ability1Label);
		playerDetails.getPanel2().add(ability2Label);
		playerDetails.getPanel2().add(ability3Label);
		playerDetails.getPanel2().add(currentActionPointsLabel);
		playerDetails.getPanel2().add(actionPointsLabel);
		playerDetails.getPanel2().add(appliedEffectsLabel);
		playerDetails.getPanel2().revalidate();
		playerDetails.getPanel2().repaint();
		
		playerDetails.getPanel2().revalidate();
		playerDetails.getPanel2().repaint();
	}
	
	
	public void updateChampAttributes()
	{
		Font myFont2 = new Font("SansSerif", Font.BOLD, 15);

		boardGame.getAttributesPanel().removeAll();
		Champion c = game.getCurrentChampion();
		if(c instanceof Hero)
		{
			typeLabel.setText("Hero type: Hero");
		}
		else if(c instanceof Villain)
		{
			typeLabel.setText("Hero type: Villain");
		}
		else
		{
			typeLabel.setText("Hero type: AntiHero");
		}
		typeLabel.setForeground(Color.red);
		typeLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setText("Champion Name: " + c.getName());
		nameLabel.setFont(myFont2);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setForeground(new Color(0x00FFFF));
		manaLabel.setText("Mana: " + c.getMana()); 
		manaLabel.setHorizontalAlignment(JLabel.CENTER);
		rangeLabel.setText("Attack Range: " + c.getAttackRange());
		rangeLabel.setHorizontalAlignment(JLabel.CENTER);
		manaLabel.setHorizontalAlignment(JLabel.CENTER);
		damageLabel.setText("Attack Damage: " + c.getAttackDamage());
		damageLabel.setHorizontalAlignment(JLabel.CENTER);
		speedLabel.setText("Speed: " + c.getSpeed()); 
		speedLabel.setHorizontalAlignment(JLabel.CENTER);
		currenthpLabel.setText("Current Health Points: " + c.getCurrentHP());
		currenthpLabel.setForeground(new Color(0x00ffff));
		currenthpLabel.setHorizontalAlignment(JLabel.CENTER);
		maxhpLabel.setText("Maximum Health Points: " + c.getMaxHP());
		maxhpLabel.setHorizontalAlignment(JLabel.CENTER);
		abilitiesLabel.setText("Avaliable Abilities: ");
		abilitiesLabel.setHorizontalAlignment(JLabel.CENTER);
		ability1Label.setText(c.getAbilities().get(0).getName() + ", ");
		ability1Label.setHorizontalAlignment(JLabel.CENTER);
		ability2Label.setText(c.getAbilities().get(1).getName() + ", ");
		ability2Label.setHorizontalAlignment(JLabel.CENTER);
		ability3Label.setText(c.getAbilities().get(2).getName());
		ability3Label.setHorizontalAlignment(JLabel.CENTER);
		currentActionPointsLabel.setText("Current Action Points: " + c.getCurrentActionPoints());
		currentActionPointsLabel.setHorizontalAlignment(JLabel.CENTER);
		currentActionPointsLabel.setForeground(new Color(0x00ffff));
		actionPointsLabel.setText("Maximum Action Points: " + c.getMaxActionPointsPerTurn());
		actionPointsLabel.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		
		boardGame.getMovePanel().revalidate();
		boardGame.getMovePanel().repaint();
		
		boardGame.getAttributesPanel().add(nameLabel);
		boardGame.getAttributesPanel().add(typeLabel);
		boardGame.getAttributesPanel().add(manaLabel);
		boardGame.getAttributesPanel().add(rangeLabel);
		boardGame.getAttributesPanel().add(damageLabel);
		boardGame.getAttributesPanel().add(speedLabel);
		boardGame.getAttributesPanel().add(currenthpLabel);
		boardGame.getAttributesPanel().add(maxhpLabel);
		boardGame.getAttributesPanel().add(abilitiesLabel);
		boardGame.getAttributesPanel().add(ability1Label);
		boardGame.getAttributesPanel().add(ability2Label);
		boardGame.getAttributesPanel().add(ability3Label);
		boardGame.getAttributesPanel().add(currentActionPointsLabel);
		boardGame.getAttributesPanel().add(actionPointsLabel);
		
		appliedEffectsLabel.setText("Applied Effects: ");
		appliedEffectsLabel.setHorizontalAlignment(JLabel.CENTER);
		appliedEffectsLabel.setForeground(Color.red);
		boardGame.getAttributesPanel().add(appliedEffectsLabel);
		
		for (int i = 0; i < c.getAppliedEffects().size(); i++)
		{
			appliedEffectsLabel = new JLabel();
			appliedEffectsLabel.setHorizontalAlignment(JLabel.CENTER);
			appliedEffectsLabel.setForeground(Color.green);
			appliedEffectsLabel.setText(c.getAppliedEffects().get(i).getName() + "   Effect Duration: " + c.getAppliedEffects().get(i).getDuration());
			boardGame.getAttributesPanel().add(appliedEffectsLabel);
		}

		
		boardGame.getAttributesPanel().revalidate();
		boardGame.getAttributesPanel().repaint();
		
		boardGame.getMovePanel().revalidate();
		boardGame.getMovePanel().repaint();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getSubmit_Button())
		{
			if(view.getPlayer1Text().equals("") ||  view.getPlayer2Text().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Player names are empty, try again", "Fail", 0, null);
			}
			else
			{
				Player p1 = new Player(view.getPlayer1Text());
				Player p2 = new Player(view.getPlayer2Text());
				game = new Game(p1, p2);
				
				try { 
					Game.loadAbilities("Abilities.csv");
					Game.loadChampions("Champions.csv");

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Players are successfully created!", "Success", 1, null);
//				JPanel SuccessfulPanel = new JPanel();
//				SuccessfulPanel.add(new JLabel("Players Created Successfully!"));
//				view.add(SuccessfulPanel, BorderLayout.SOUTH);
				// we need to somehow add an event listener here to remove the player names panel and go to the select Champion panel
				view.setVisible(false);
				
				this.selectChampions();
				

				


			}
			
			
		}
		else if(buttons.contains(e.getSource()))
		{
			buttonIndex = buttons.indexOf(e.getSource());
			JButton selectedButton = buttons.get(buttonIndex);

			Champion c = this.game.getAvailableChampions().get(buttonIndex);
			nameLabel.setText("Champion Name: " + c.getName());
			manaLabel.setText("Mana: " + c.getMana()); 
			rangeLabel.setText("Attack Range: " + c.getAttackRange());
			damageLabel.setText("Attack Damage: " + c.getAttackDamage());
			speedLabel.setText("Speed: " + c.getSpeed()); 
			maxhpLabel.setText("Maximum Health Points: " + c.getMaxHP());
			abilitiesLabel.setText("Avaliable Abilities: ");
			ability1Label.setText(c.getAbilities().get(0).getName() + ", ");
			ability2Label.setText(c.getAbilities().get(1).getName() + ", ");
			ability3Label.setText(c.getAbilities().get(2).getName());
			actionPointsLabel.setText("Maximum Action Points: " + c.getMaxActionPointsPerTurn()); 
			
			
			nameLabel.setForeground(new Color(0x00ffff));
			nameLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
			nameLabel.setHorizontalAlignment(JLabel.CENTER);
			
			manaLabel.setForeground(Color.white);
			manaLabel.setHorizontalAlignment(JLabel.CENTER);
			
			rangeLabel.setForeground(Color.white);
			rangeLabel.setHorizontalAlignment(JLabel.CENTER);
			
			damageLabel.setForeground(Color.white);
			damageLabel.setHorizontalAlignment(JLabel.CENTER);
			
			speedLabel.setForeground(Color.white);
			speedLabel.setHorizontalAlignment(JLabel.CENTER);
			
			maxhpLabel.setForeground(Color.white);
			maxhpLabel.setHorizontalAlignment(JLabel.CENTER);
			
			abilitiesLabel.setForeground(Color.red);
			abilitiesLabel.setHorizontalAlignment(JLabel.CENTER);
			
			ability1Label.setForeground(Color.yellow);
			ability1Label.setHorizontalAlignment(JLabel.CENTER);
			
			ability2Label.setForeground(Color.yellow);
			ability2Label.setHorizontalAlignment(JLabel.CENTER);
			
			ability3Label.setForeground(Color.yellow);
			ability3Label.setHorizontalAlignment(JLabel.CENTER);
			
			actionPointsLabel.setForeground(Color.white);
			actionPointsLabel.setHorizontalAlignment(JLabel.CENTER);
			
			champSelect.getChampAttributesPanel().add(nameLabel);
			champSelect.getChampAttributesPanel().add(manaLabel);
			champSelect.getChampAttributesPanel().add(rangeLabel);
			champSelect.getChampAttributesPanel().add(damageLabel);
			champSelect.getChampAttributesPanel().add(speedLabel);
			champSelect.getChampAttributesPanel().add(maxhpLabel);
			champSelect.getChampAttributesPanel().add(abilitiesLabel);
			champSelect.getChampAttributesPanel().add(ability1Label);
			champSelect.getChampAttributesPanel().add(ability2Label);
			champSelect.getChampAttributesPanel().add(ability3Label);
			champSelect.getChampAttributesPanel().add(actionPointsLabel);
			champSelect.getChampAttributesPanel().revalidate();
			champSelect.getChampAttributesPanel().repaint();
			
			
 		}
		else if(e.getSource() == champSelect.getAddButton())
		{
			if(buttonIndex == -1)
			{
				JOptionPane.showMessageDialog(null, "Champion already choosen, choose another.", "Fail", 2, null);

			}
			else
			{
				if(game.getFirstPlayer().getTeam().size() < 3)
				{
					Champion c = game.getAvailableChampions().get(buttonIndex);
					game.getFirstPlayer().getTeam().add(c);
					if(game.getFirstPlayer().getLeader() == null)
					{
						game.getFirstPlayer().setLeader(c);
					}
					buttons.get(buttonIndex).setEnabled(false);
					buttons.get(buttonIndex).setBackground(Color.green);
				}
				else if(game.getSecondPlayer().getTeam().size() < 3)
				{
					Champion c = game.getAvailableChampions().get(buttonIndex);
					game.getSecondPlayer().getTeam().add(c);
					if(game.getSecondPlayer().getLeader() == null)
					{
						game.getSecondPlayer().setLeader(c);
					}
					buttons.get(buttonIndex).setEnabled(false);
					buttons.get(buttonIndex).setBackground(Color.gray);
					if(game.getSecondPlayer().getTeam().size() >= 3)
					{
						champSelect.setVisible(false);
						game = new Game(game.getFirstPlayer(), game.getSecondPlayer());
						boardLoadout();
					}
				}
				buttonIndex = -1;
			}
			
				
		}
		else if(e.getSource() == boardGame.getUpMove())
		{
			try {
				game.move(Direction.DOWN);
				reDrawBoard();
				updateChampAttributes();
			} catch (UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null, "Unallowed Movement", "Fail", 2, null);
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Resources", "Fail", 2, null);
			}
			if(game.getCurrentChampion().getCurrentActionPoints() <= 0)
			{
				game.endTurn();
				castFrame.setVisible(false);
				updateChampAttributes();
			}
		}
		else if(e.getSource() == boardGame.getDownMove())
		{
			try {
				game.move(Direction.UP);
				reDrawBoard();
				updateChampAttributes();
			} catch (UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null, "Unallowed Movement", "Fail", 2, null);
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Resources", "Fail", 2, null);
			}
			if(game.getCurrentChampion().getCurrentActionPoints() <= 0)
			{
				game.endTurn();
				castFrame.setVisible(false);
				updateChampAttributes();
				reDrawBoard();
			}
		}
		else if(e.getSource() == boardGame.getRightMove())
		{
			try {
				game.move(Direction.RIGHT);
				reDrawBoard();
				updateChampAttributes();
			} catch (UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null, "Unallowed Movement", "Fail", 2, null);
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Resources", "Fail", 2, null);
			}
			if(game.getCurrentChampion().getCurrentActionPoints() <= 0)
			{
				game.endTurn();
				castFrame.setVisible(false);
				updateChampAttributes();
				reDrawBoard();
			}
		}
		else if(e.getSource() == boardGame.getLeftMove())
		{
			try {
				game.move(Direction.LEFT);
				reDrawBoard();
				updateChampAttributes();
			} catch (UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null, "Unallowed Movement", "Fail", 2, null);
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Resources", "Fail", 2, null);
			}
			if(game.getCurrentChampion().getCurrentActionPoints() <= 0)
			{
				game.endTurn();
				castFrame.setVisible(false);
				updateChampAttributes();
				reDrawBoard();
			}
		}
		else if(e.getSource() == boardGame.getAttackUp())
		{
			try {
				System.out.println("Reached");
				game.attack(Direction.DOWN);
			} catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null, "Champion is Disarmed!", "Fail", 2, null);
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Resources!", "Fail", 2, null);
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, "Invalid Target!", "Fail", 2, null);
			}
			reDrawBoard();
			updateChampAttributes();
			if(game.checkGameOver() != null)
			{
				Player p = game.checkGameOver();
				String s = "Congratulations " + p.getName() + ", You have won!!";
				JOptionPane.showMessageDialog(null, s, "Game Over", 1, null);
				disableEverything();

			}
			if(game.getCurrentChampion().getCurrentActionPoints() <= 0)
			{
				game.endTurn();
				castFrame.setVisible(false);
				updateChampAttributes();
				reDrawBoard();
			}
			
			
		}
		else if(e.getSource() == boardGame.getAttackDown())
		{
			try {
				game.attack(Direction.UP);
				
			} catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null, "Champion is Disarmed!", "Fail", 2, null);
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Resources!", "Fail", 2, null);
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, "Invalid Target!", "Fail", 2, null);
			}
			reDrawBoard();
			updateChampAttributes();
			if(game.getCurrentChampion().getCurrentActionPoints() <= 0)
			{
				game.endTurn();
				castFrame.setVisible(false);
				updateChampAttributes();
				reDrawBoard();
			}
			if(game.checkGameOver() != null)
			{
				Player p = game.checkGameOver();
				String s = "Congratulations " + p.getName() + ", You have won!!";
				JOptionPane.showMessageDialog(null, s, "Game Over", 1, null);
				disableEverything();

			}
			
		}
		else if(e.getSource() == boardGame.getAttackRight())
		{
			try {
				game.attack(Direction.RIGHT);
			} catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null, "Champion is Disarmed!", "Fail", 2, null);
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Resources!", "Fail", 2, null);
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, "Invalid Target!", "Fail", 2, null);
			}
			reDrawBoard();
			updateChampAttributes();
			if(game.getCurrentChampion().getCurrentActionPoints() <= 0)
			{
				game.endTurn();
				castFrame.setVisible(false);
				updateChampAttributes();
				reDrawBoard();
			}
			if(game.checkGameOver() != null)
			{
				Player p = game.checkGameOver();
				String s = "Congratulations " + p.getName() + ", You have won!!";
				JOptionPane.showMessageDialog(null, s, "Game Over", 1, null);
				disableEverything();

			}
			
		}
		else if(e.getSource() == boardGame.getAttackLeft())
		{
			try {
				game.attack(Direction.LEFT);
			} catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null, "Champion is Disarmed!", "Fail", 2, null);
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Resources!", "Fail", 2, null);
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, "Invalid Target!", "Fail", 2, null);
			}
			reDrawBoard();
			updateChampAttributes();
			if(game.getCurrentChampion().getCurrentActionPoints() <= 0)
			{
				game.endTurn();
				castFrame.setVisible(false);
				updateChampAttributes();
				reDrawBoard();
			}
			if(game.checkGameOver() != null)
			{
				Player p = game.checkGameOver();
				String s = "Congratulations " + p.getName() + ", You have won!!";
				JOptionPane.showMessageDialog(null, s, "Game Over", 1, null);
				disableEverything();

			}
			
		}
		else if(e.getSource().equals(boardGame.getEndButton()) )
		{
			game.endTurn();
			castFrame.setVisible(false);
			reDrawBoard();
			updateChampAttributes();
		}
		
		else if(e.getSource().equals(boardGame.getQueueButton()) )
		{
			queueLoader();
		}
		else if(e.getSource().equals(boardGame.getCastButton()) )
		{
			castLoader();
		}
		else if(e.getSource().equals(castFrame.getLeaderButton()))
		{
			try {
				game.useLeaderAbility();
			} catch (LeaderAbilityAlreadyUsedException e1) {
				JOptionPane.showMessageDialog(null, "Leader Ability already used", "Fail", 2, null);
			} catch (LeaderNotCurrentException e1) {
				JOptionPane.showMessageDialog(null, "You need the leader to cast this ability", "Fail", 2, null);
			}
			if(game.checkGameOver() != null)
			{
				Player p = game.checkGameOver();
				String s = "Congratulations " + p.getName() + ", You have won!!";
				JOptionPane.showMessageDialog(null, s, "Game Over", 1, null);
				disableEverything();
			}

		}
		else if(abilityButtons.contains(e.getSource()))
		{
			int abilityIndex = abilityButtons.indexOf(e.getSource());
			Ability a = game.getCurrentChampion().getAbilities().get(abilityIndex);
			if(a.getCastArea() == AreaOfEffect.DIRECTIONAL)
			{
				String input = JOptionPane.showInputDialog("Enter cast direction of the ability");
				input = input.toUpperCase(); 
				switch (input)
				{
				
				case "UP": try {
						game.castAbility(a, Direction.DOWN);
					} catch (AbilityUseException e1) {
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(null, "You don't have enough resources to cast this ability", "Fail", 2, null);
					} catch (CloneNotSupportedException e1) {
						JOptionPane.showMessageDialog(null, "Cannot clone this ability", "Fail", 2, null);
					}break;
					
				case "DOWN": try {
						game.castAbility(a, Direction.UP);
					} catch (AbilityUseException e1) {
						JOptionPane.showMessageDialog(null, "Object out of range or Ability on cooldown", "Fail", 2, null);
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(null, "You don't have enough resources to cast this ability", "Fail", 2, null);
					} catch (CloneNotSupportedException e1) {
						JOptionPane.showMessageDialog(null, "Cannot clone this ability", "Fail", 2, null);
					}break;
					
				case "RIGHT": try {
						game.castAbility(a, Direction.RIGHT);
					} catch (AbilityUseException e1) {
						JOptionPane.showMessageDialog(null, "Object out of range or Ability on cooldown", "Fail", 2, null);
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(null, "You don't have enough resources to cast this ability", "Fail", 2, null);
					} catch (CloneNotSupportedException e1) {
						JOptionPane.showMessageDialog(null, "Cannot clone this ability", "Fail", 2, null);
					}break;
					
				case "LEFT": try {
						game.castAbility(a, Direction.LEFT);
					} catch (AbilityUseException e1) {
						JOptionPane.showMessageDialog(null, "Object out of range or Ability on cooldown", "Fail", 2, null);
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(null, "You don't have enough resources to cast this ability", "Fail", 2, null);
					} catch (CloneNotSupportedException e1) {
						JOptionPane.showMessageDialog(null, "Cannot clone this ability", "Fail", 2, null);
					}break;
					default:
						JOptionPane.showMessageDialog(null, "Invalid Direction", "Fail", 2, null);

				
				}
				reDrawBoard();
				updateChampAttributes();
				labelizeAbilities(a, castFrame.getAbility2Panel());
				if(game.getCurrentChampion().getCurrentActionPoints() <= 0)
				{
					game.endTurn();
					castFrame.setVisible(false);
					updateChampAttributes();
				}
			}
			else if(a.getCastArea() == AreaOfEffect.SINGLETARGET)
			{
				try {
					game.castAbility(a, selectedI, selectedJ);
				} catch (AbilityUseException e1) {
					JOptionPane.showMessageDialog(null, "Object out of range or Ability on cooldown", "Fail", 2, null);
				} catch (NotEnoughResourcesException e1) {
					JOptionPane.showMessageDialog(null, "You don't have enough resources to cast this ability", "Fail", 2, null);
				} catch (InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null, "Invalid Target", "Fail", 2, null);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reDrawBoard();
				updateChampAttributes();
				labelizeAbilities(a, castFrame.getAbility2Panel());
				
				if(game.getCurrentChampion().getCurrentActionPoints() <= 0)
				{
					game.endTurn();
					castFrame.setVisible(false);
					updateChampAttributes();
				}
				if(game.checkGameOver() != null)
				{
					Player p = game.checkGameOver();
					String s = "Congratulations " + p.getName() + ", You have won!!";
					JOptionPane.showMessageDialog(null, s, "Game Over", 1, null);
					disableEverything();

				}
			}
			else
			{
				try {
					game.castAbility(a);
				} catch (AbilityUseException e1) {
					JOptionPane.showMessageDialog(null, "Object out of range or Ability on cooldown", "Fail", 2, null);
				} catch (NotEnoughResourcesException e1) {
					JOptionPane.showMessageDialog(null, "You don't have enough resources to cast this ability", "Fail", 2, null);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reDrawBoard();
				updateChampAttributes();
				if(game.getCurrentChampion().getCurrentActionPoints() <= 0)
				{
					game.endTurn();
					castFrame.setVisible(false);
					updateChampAttributes();
				}
				
			}
			labelizeAbilities(a, castFrame.getAbility2Panel());

			if(game.getCurrentChampion().getCurrentActionPoints() <= 0)
			{
				game.endTurn();
				castFrame.setVisible(false);
				castFrame.setVisible(false);
			}
			if(game.checkGameOver() != null)
			{
				Player p = game.checkGameOver();
				String s = "Congratulations " + p.getName() + ", You have won!!";
				JOptionPane.showMessageDialog(null, s, "Game Over", 1, null);
				disableEverything();

			}
		}
		else if(isContained(boardButtons,(JButton) e.getSource()))
		{
			// stores the selected's i in selectedI and j in selectedJ
		}
		else if(e.getSource().equals(boardGame.getPlayersButton()))
		{
			playerDetailsLoad();
		}
		else if(champion1Buttons.contains(e.getSource()))
		{
			int index = champion1Buttons.indexOf(e.getSource());
			Champion c = game.getFirstPlayer().getTeam().get(index);
			playerPanel2Update(c);
		}
		else if(champion2Buttons.contains(e.getSource()))
		{
			int index = champion2Buttons.indexOf(e.getSource());
			Champion c = game.getSecondPlayer().getTeam().get(index);
			playerPanel2Update(c);
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(abilityButtons.contains(e.getSource()))
		{
			int abilityIndex = abilityButtons.indexOf(e.getSource());
			Ability a = game.getCurrentChampion().getAbilities().get(abilityIndex);
			castFrame.getAbility2Panel().removeAll();
			labelizeAbilities(a, castFrame.getAbility2Panel());
			castFrame.getAbility2Panel().revalidate();
			castFrame.getAbility2Panel().repaint();
			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
