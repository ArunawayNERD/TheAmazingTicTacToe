package game.TicTacToeGame.frameParts;

import game.EnumState;
import game.Game;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
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
		
		String buttonText = "";
		
		for(int i = 0; i < buttons.length; i++)
		{
			switch(i)
			{
				case 0 : 	buttonText = "Restart";
							break;
							
				case 1 : 	buttonText = "Quit";
							break;
			}
			
			buttons[i] = new JButton(buttonText);
			buttons[i].setHorizontalTextPosition(AbstractButton.CENTER);
			buttons[i].addActionListener(this);
			buttons[i].setPreferredSize(new Dimension(98, 32));
			buttons[i].setFont(new Font(this.getFont().getName(),Font.BOLD, 18));
			
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
            ownerGame.switchState(EnumState.TITLE_STATE);
		}
	}
}
