package game;

import game.ticTacToeGame.TicTacToe;
import game.TitleScreen.TitleScreen;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends JFrame
{
    private TitleScreen title;
    private TicTacToe game;

    private JPanel currentPane;

    public Game()
    {
        super("The Amazing Tic Tac Toe");
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Resources/icon.png")).getImage());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(355, 275);
        this.setLocation(5, 5);
        this.setResizable(false);

        title = new TitleScreen(this);
        game = new TicTacToe(this);

        this.setState(title);
    }


    public void switchState(EnumState switchToState)
    {
        switch(switchToState)
        {
            case TITLE_STATE:  changeState(title);
                                break;

            case GAME_STATE:    game = new TicTacToe(this); //remakes the game so that the last tile states aren't still maintained
                                changeState(game);
                                break;
        }
    }

    private void changeState(JPanel state)
    {
        this.remove(currentPane);
        this.setState(state);
    }

    private void setState(JPanel state)
    {
        this.add(state);
        this.currentPane = state;

        //makes the new state actually show up
        this.revalidate();
        this.repaint();
    }
    public static void main(String[] args)
    {
        Game ticTacToe = new Game();

        ticTacToe.setVisible(true);
    }

    public enum EnumState
    {
        TITLE_STATE, GAME_STATE, HELP_STATE, OPTTION_STATE;
    }
}
