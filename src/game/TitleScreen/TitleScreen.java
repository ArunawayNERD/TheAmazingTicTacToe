package game.TitleScreen;

import game.Game;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class TitleScreen extends JPanel implements ActionListener
{
    private Game owner;
    private JButton [] buttons = new JButton[4];

    private JLabel title;

    private Image backgroundImage;

	public TitleScreen()
	{
        this.setLayout(new GridBagLayout());

        GridBagConstraints constraint = new GridBagConstraints();

        //set Up Background Image
        backgroundImage = new ImageIcon(this.getClass().getResource("/Resources/Background/background.png")).getImage();

        //sets up ands adds title
        title = new JLabel(new ImageIcon(getClass().getResource("/Resources/TitleScreen/Title.png")));
        title.setHorizontalAlignment(JLabel.CENTER);

        constraint.gridwidth = 2;
        constraint.weightx = 1;
        constraint.anchor = GridBagConstraints.PAGE_START;

        add(title, constraint);

        // sets up and adds buttons
        for(int i = 0; i < buttons.length; i++)
        {
            constraint = new GridBagConstraints();

            switch(i)
            {
                case 0 :    buttons[i] = new JButton(new ImageIcon(getClass().getResource("/Resources/TitleScreen/Play.png")));
                            buttons[i].setRolloverIcon(new ImageIcon(getClass().getResource("/Resources/TitleScreen/PlayActive.png")));
                            break;

                case 1 :    buttons[i] = new JButton(new ImageIcon(getClass().getResource("/Resources/TitleScreen/Help.png")));
                            buttons[i].setRolloverIcon(new ImageIcon(getClass().getResource("/Resources/TitleScreen/HelpActive.png")));
                            break;

                case 2 :    buttons[i] = new JButton(new ImageIcon(getClass().getResource("/Resources/TitleScreen/Options.png")));
                            buttons[i].setRolloverIcon(new ImageIcon(getClass().getResource("/Resources/TitleScreen/OptionsActive.png")));
                            break;

                case 3 :    buttons[i] = new JButton(new ImageIcon(getClass().getResource("/Resources/TitleScreen/Exit.png")));
                            buttons[i].setRolloverIcon(new ImageIcon(getClass().getResource("/Resources/TitleScreen/ExitActive.png")));
                            break;
            }

            constraint.gridx = 0;
            constraint.gridy = 1 + i;
            constraint.weightx = .01;
            constraint.weighty = .01;
            constraint.gridwidth = 1;
            constraint.ipadx = 0;
            constraint.anchor = GridBagConstraints.WEST;

            buttons[i].setBorderPainted(false);
            buttons[i].setFocusPainted(false);
            buttons[i].setContentAreaFilled(false);

            buttons[i].addActionListener(this);

            this.add(buttons[i], constraint);
        }
	}

    public TitleScreen(Game owner)
    {
        this();
        this.owner = owner;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null), null);
    }

    public Image getBackgroundImage()
    {
        return backgroundImage;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton clicked = (JButton) e.getSource();

        {
            if(clicked == buttons[0])
            {
                if(owner != null)
                {
                    owner.switchState(Game.EnumState.GAME_STATE);
                }
            }
            else if(clicked == buttons[1])
            {
                JOptionPane.showMessageDialog(this, "Help Info will be here at a later date");
            }
            else if(clicked == buttons[2])
            {
                JOptionPane.showMessageDialog(this, "Options will be here at a later date");
            }
            else if(clicked == buttons[3])
            {
                System.exit(0);
            }
        }
    }
}
