package game.ticTacToeGame.frameParts;

import game.Game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.BorderUIResource;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel implements ActionListener
{
    private TicTacUI owner;
    private Game ownerGame;

    private JButton [] buttons = new JButton [2];

	public ButtonPanel()
	{
		this.setOpaque(false);

        //sets the space between components and the frame to be 5 pixels
        FlowLayout frameLayout = (FlowLayout) this.getLayout();
        frameLayout.setHgap(5);

		for(int i = 0; i < buttons.length; i++)
		{
			switch(i)
			{
				case 0 : 	buttons[i] = new JButton(new ImageIcon(getClass().getResource("/Resources/ButtonPanel/Restart.png")));
                            buttons[i].setRolloverIcon(new ImageIcon(this.getClass().getResource("/Resources/ButtonPanel/RestartActive.png")));
							break;
							
				case 1 : 	buttons[i] = new JButton(new ImageIcon(getClass().getResource("/Resources/ButtonPanel/Exit.png")));
                            buttons[i].setRolloverIcon(new ImageIcon(this.getClass().getResource("/Resources/ButtonPanel/ExitActive.png")));
							break;
			}

            buttons[i].setBorderPainted(false);
            buttons[i].setBorder(new BorderUIResource.EmptyBorderUIResource(0,0,0,0));
            buttons[i].setFocusPainted(false);
            buttons[i].setContentAreaFilled(false);

            buttons[i].addActionListener(this);
//			buttons[i].setPreferredSize(new Dimension(98, 32));

			add(buttons[i]);
		}
		
	}
	
	public ButtonPanel(TicTacUI owner, Game ownerGame)
	{
		this();
		this.owner = owner;
        this.ownerGame = ownerGame;
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		JButton source = (JButton) event.getSource();

        if(owner != null && source.equals(buttons[0])) // button object for restart
        {
            owner.restart();
        }
        else if(ownerGame != null && source.equals(buttons[1]))
		{
            ownerGame.switchState(Game.EnumState.TITLE_STATE);
		}
	}
}
