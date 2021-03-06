package game.ticTacToeGame.frameParts;

import game.Game;
import game.ticTacToeGame.TicTacToe;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class TicTacUI extends JPanel
{
	private TicTacToe owner;
    private Game ownerGame;
	private Board ticTacToeBoard;
	private ButtonPanel utilButtons;
	
    public TicTacUI(TicTacToe owner, Game ownerGame)
	{
        this.setLayout(new GridLayout(0, 1, 0, 20));

        this.setOpaque(false);

        this.owner = owner;
        this.ownerGame = ownerGame;

        ticTacToeBoard = new Board(this, ownerGame); //add way for people to pick
        add(ticTacToeBoard);

        utilButtons = new ButtonPanel(this, ownerGame); //in this constructor because it needs the game owner
        add(utilButtons);


		ticTacToeBoard.setBackground(this.getBackground());
		utilButtons.setBackground(this.getBackground());
	}

    /**
     * Method to set this panels background color and then set the background of its fields to the same color
     *
     * @param backgroundColor the color to be set as the background color
     */
    public void setPanelAndFieldBackground(Color backgroundColor)
    {
        this.setBackground(backgroundColor);

        ticTacToeBoard.setBackground(this.getBackground());
        utilButtons.setBackground(this.getBackground());
    }


	public void restart()
	{
		ticTacToeBoard.restart();
	}
	
    public TicTacToe getOwnerTicTacToe()
    {
        return owner;
    }

    public Game getOwnerGame()
    {
        return ownerGame;
    }
}
