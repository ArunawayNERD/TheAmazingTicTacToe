package game.dialogPane;

import javax.swing.ImageIcon;

/**
 * @author John Pigott
 *         Created on 5/23/14 at 11:27 AM
 */
public enum DialogMessage
{
    TIE(0),
    PLAYER_ONE(1),
    PLAYER_TWO(2);

    private ImageIcon messageImage;

    private DialogMessage(int icon)
    {
        switch(icon)
        {
            case 0: messageImage = new ImageIcon(this.getClass().getResource("/Resources/DialogPane/Tie.png"));
                break;

            case 1: messageImage = new ImageIcon(this.getClass().getResource("/Resources/DialogPane/PlayerOne.png"));
                break;

            case 2: messageImage = new ImageIcon(this.getClass().getResource("/Resources/DialogPane/PlayerTwo.png"));
                break;
        }
    }

    public ImageIcon getImageIcon()
    {
        return messageImage;
    }
}
