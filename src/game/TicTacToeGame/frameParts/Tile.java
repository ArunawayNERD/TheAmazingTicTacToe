package game.TicTacToeGame.frameParts;
import game.SettingsManager;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class Tile extends JButton implements ActionListener 
{
	private Board myBoard;
	
	private int ID;
	private int state; //0 = blank, 1 = x, 2 = O
	
	private Icon x;
	private Icon o;
	
	private Icon blank;
	
	public Tile()
	{
		
		blank = new ImageIcon(getClass().getResource("/Resources/blank.png"));
		
		x = new ImageIcon(getClass().getResource("/Resources/" + SettingsManager.getX() +"/x.png"));
		o = new ImageIcon(getClass().getResource("/Resources/" + SettingsManager.getO() +"/o.png"));

		
		setIcon(blank);
		addActionListener(this);
		
		this.setBorder(null);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
//        this.setRolloverEnabled(false);
        this.setBackground(new Color(0,0,0,0));

        this.setAutoscrolls(false);
		state = 0;
	}
	
	public Tile(int ID)
	{
		this();
		this.ID = ID;
	}

	public Tile(int ID, Board myBoard)
	{
		this(ID);
		this.myBoard = myBoard;
	}
	
	public void updateState(int newState)
	{
		state = newState;
		update();
	}
	
	public void update()
	{
		switch(state)
		{
			case 1: 
				this.setIcon(x);
				break;
				
			case 2: 
				setIcon(o);
				break;
				
			default:
				setIcon(blank);
				break;			
		}
	}
	
	public int getID() 
	{
		return ID;
	}

    public int getState()
	{
		return state;
	}
	
	public void actionPerformed(ActionEvent event) 
	{
		Tile clickedTile  = (Tile) event.getSource();	
		
		if(myBoard !=  null) 
		{
			myBoard.clickReceived(clickedTile.getID());
		}
	}
}
