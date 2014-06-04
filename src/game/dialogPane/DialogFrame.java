package game.dialogPane;

import game.Game;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author John Pigott
 *         Created on 3/19/14 at 10:46 PM
 */
public class DialogFrame extends JFrame
{
    private Game owner;

    private DialogMessage msg;
    private ImageIcon backgroundImage;

    /**
     * Creates a new dialog frame with a JDialog that has no
     * parent, title, and content Pane. It also isn't modal
     */
    public DialogFrame(Game owner,DialogMessage message)
    {
        super("Game Over!");

        this.msg = message;

        if(owner != null)
        {
            setLocationRelativeTo(owner);
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        backgroundImage = new ImageIcon(this.getClass().getResource("/Resources/DialogPane/Background.png"));
        setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight() + 15);

        setVisible(true);

    }

    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(backgroundImage.getImage(), 0, 15, null); //draws the background image

        g2.drawImage(msg.getImageIcon().getImage(), 0, 30, null);
    }
}
