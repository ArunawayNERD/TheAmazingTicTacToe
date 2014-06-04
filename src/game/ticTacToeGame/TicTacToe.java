package game.ticTacToeGame;

import game.Game;
import game.ticTacToeGame.frameParts.TicTacUI;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

@SuppressWarnings("serial")
public class TicTacToe extends JPanel
{
    private Game ownerGame;
    private TicTacUI ticTacToeMenu;

    private Image backgroundImage;

    private Color backgroundColor = new Color(0,0,0,0);

    public TicTacToe(Game ownerGame)
    {
        backgroundImage = new ImageIcon(getClass().getResource("/Resources/Background/GameBackground.png")).getImage();
        this.setLayout(new FlowLayout());

        this.ownerGame = ownerGame;

        ticTacToeMenu = new TicTacUI(this, ownerGame); //in this constructor because it needs the game owner
        add(ticTacToeMenu);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0 , 350, 250, null);
    }

    public Image getBackgroundImage()
    {
        return backgroundImage;
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
