package game.ticTacToeGame.frameParts;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.BorderUIResource;
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
		
		blank = new ImageIcon(getClass().getResource("/Resources/Tiles/blank.png"));
		
		x = new ImageIcon(getClass().getResource("/Resources/Tiles/x.png"));
		o = new ImageIcon(getClass().getResource("/Resources/Tiles/o.png"));

        state = 0;
		setIcon(blank);
		addActionListener(this);

		this.setBorder(new BorderUIResource.EmptyBorderUIResource(0,0,0,0));
        this.setBorderPainted(false);

		this.setContentAreaFilled(false);
		this.setFocusPainted(false);

        this.setRolloverEnabled(false);

        this.setOpaque(false);
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
	
	public void updateState(EnumTileState newState)
	{
		switch(newState)
        {
            case BLANK :    state = 0;
                            break;

            case X_STATE :  state = 1;
                            break;

            case O_STATE :  state = 2;
                            break;
            default: return;
        }

		update();
	}

	private void update()
	{
		switch(state)
		{
			case 1: this.setIcon(x);
				    break;

			case 2: setIcon(o);
				    break;

			default:    setIcon(blank);
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
