package game.TicTacToeGame;

import game.Game;
import game.SettingsManager;
import game.TicTacToeGame.frameParts.TicTacUI;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;

@SuppressWarnings("serial")
public class TicTacToe extends JPanel
{
    private Game ownerGame;
    private TicTacUI ticTacToeMenu;



    private Color backgroundColor = new Color(0,0,0, 0);

	private TicTacToe() //private constructor because it needs the other constructor to function correctly
	{
		this.setSize(325,250);

        SettingsManager settings = new SettingsManager(); //loads settings from the settings file

		this.setLayout(new FlowLayout());
	}

    public TicTacToe(Game ownerGame)
    {
        this();
        this.ownerGame = ownerGame;

        ticTacToeMenu = new TicTacUI(this, ownerGame); //in this constructor because it needs the game owner
        ticTacToeMenu.setPanelAndFieldBackground(backgroundColor);
        add(ticTacToeMenu);
    }


    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Image background = new ImageIcon(this.getClass().getResource("/Resources/Background/GameBackground.png")).getImage();

        g.drawImage(background, 0 ,0, background.getWidth(null), background.getHeight(null), null);
    }

    public Game getOwnerGame()
    {
        return ownerGame;
    }

    public Color getBackgroundColor()
    {
        return backgroundColor;
    }
}
