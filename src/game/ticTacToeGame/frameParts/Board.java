package game.ticTacToeGame.frameParts;

import game.Game;
import game.Point;
import game.dialogPane.DialogFrame;
import game.dialogPane.DialogMessage;

import javax.swing.JPanel;
import java.awt.*;

/**
 * This class holds the "board" for the game. It holds all the game tiles and handles their logic
 * 
 * @version 11/27/13
 */
@SuppressWarnings("serial")
public class Board extends JPanel
{
    private Tile[] gameBoard = new Tile[9];
	
	private final int numRows = 3; //no touching or will break logic
	private final int numCols = 3; //same goes for this one >:(
	
	private int verticalPadding = 14; 
	private int horizontalPadding = 14;
	
	private int player = 1;
	
	private boolean gameWon = false;

    private TicTacUI owner;
    private Game ownerGame;
	
	/**
	 * Constructs a new gameBoard with a GridLayout
	 * and 9 tiles placed in that layout
	 */
    public Board(TicTacUI owner, Game ownerGame)
    {
        this.setLayout(new GridBagLayout());

        for(int i = 0; i < gameBoard.length; i++)
        {
            gameBoard[i] = new Tile(i, this);

            GridBagConstraints constraint = new GridBagConstraints();

            constraint.gridx = i % numCols;
            constraint.gridy = i / numCols;
            constraint.ipadx = horizontalPadding;
            constraint.ipady = verticalPadding;

            this.add(gameBoard[i], constraint);

            this.setOpaque(false);
        }

        this.ownerGame = ownerGame;
        this.owner = owner;
    }


	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
		Rectangle line;
		
		for(int i = 0; i < 4; i++)
		{
			line = new Rectangle();

   			switch(i)
			{				
							//gets the x pos and adds the width (adjusted for the padding). then adds 5 to put it in the middle
				case 0 : 	line.x = gameBoard[0].getX() + (gameBoard[0].getWidth() - horizontalPadding / 2) + 5; //pos
							line.y = 0;
							line.width = 4;
							line.height = this.getHeight();
							break;
							
							//gets the x pos and adds the width (adjusted for the padding). then adds 5 to put it in the middle
				case 1 : 	line.x = gameBoard[1].getX() + (gameBoard[1].getWidth() - horizontalPadding / 2) + 5;
							line.y = 0;
							line.width = 4;
							line.height = this.getHeight();
							break;
							
				case 2 :	line.x = gameBoard[0].getX();
							
							//gets the y pos and adds the height(adjusted for the padding). then adds 5 to put it in the middle
							line.y = gameBoard[0].getY() + (gameBoard[0].getHeight() - verticalPadding / 2) + 5;
							line.width = ((gameBoard[5].getX() + gameBoard[5].getWidth()) - line.x); //distance from the left x pos to the right x pos
							line.height = 4;
							break;
							
				case 3 :	line.x = gameBoard[3].getX();
					
							//gets the y pos and adds the height(adjusted for the padding). then adds 5 to put it in the middle
							line.y = gameBoard[3].getY() + (gameBoard[3].getHeight() - verticalPadding / 2) + 5;
							line.width = ((gameBoard[5].getX() + gameBoard[5].getWidth()) - line.x); //distance from the left x pos to the right x pos
							line.height = 4;
							break;
			}
			
			g2.fill(line);
		}
	}

	/**
	 * Method called by the tile's action listener whenever a tile is clicked
	 * updates that tile with the right status, checks winning logic, etc
	 * 
	 * @param clickedTileID - the tile id that was clicked. (corresponds to the index in the tile array field)
	 */
	public void clickReceived(int clickedTileID)
	{
		Tile clickedTile = gameBoard[clickedTileID];
        DialogFrame endDialog;
		//if the tile has already been clicked or the game is over. exit the method
		if(clickedTile.getState().getState() == 0 && !gameWon)
        {
			if(player == 1)
            {
                clickedTile.updateState(TileState.X_STATE);
            }
            else if(player == 2)
            {
                clickedTile.updateState(TileState.O_STATE);
            }
        }
		else
        {
			return;
        }
			
		//Logic to check for win
		if(checkIfGameWon(clickedTile)) //drawing the line is done by checkIfGameWon()
		{
			gameWon = true;

            DialogMessage msgType;

            if(player == 1)
            {
                msgType = DialogMessage.PLAYER_ONE;
            }
            else
            {
                msgType = DialogMessage.PLAYER_TWO;
            }
//			JOptionPane.showMessageDialog(this.getParent(), "Player " + player + "has won the game!");
//            JOptionPane.showMessageDialog(this.getParent(), new DialogPanel("Player " + player + "has won the game!"), " ", JOptionPane.PLAIN_MESSAGE);
//            endDialog = new DialogFrame(ownerGame, "Game Over!", true, new DialogPanel("Player " + player + "has won the game!"));
            new DialogFrame(ownerGame, msgType);
//            endDialog.showMessage();
		}

		//checks for tie because no one won.
		if(boardFull()&& !gameWon)
		{
			gameWon = true;
            new DialogFrame(ownerGame, DialogMessage.TIE);
		}

		
		//once single player logic is done or it's multiplier. the player switches
		if(player == 1)
			player = 2;
		else if(player == 2)
			player = 1;
	}

	private boolean boardFull()
	{
		
		for(int i = 0; i < gameBoard.length; i++)
		{
			if(gameBoard[i].getState().getState() == 0)
			{
				return false; //if any tile is empty ie state = 0 then the board isn't full
			}
		}
		
		return true;
	}

	/**
	 * Checks the tiles to see if the clicked tile causes a win.<br />
	 * What happens when a win occurs is not implemented here. 
	 * 
	 * @param clickedTile - The tile that was clicked
	 * @return true if click resulted in a win. 
	 */
	private boolean checkIfGameWon(Tile clickedTile) 
	{
		Point tilePoint = new Point(clickedTile.getID() % numCols, clickedTile.getID() / numCols);
		
		boolean verTwoSideTest = false;
		boolean horTwoSideTest = false;
		
		boolean diaTest = false; // checking along the diagonals 
		
		Point [] testCords;
		
		boolean gameWon = false;
		
		//if its in the middle col then we need to left and right for a win.
		if(tilePoint.getXPos() == 1)
		{
			horTwoSideTest = true;
		}
		
		//if its in the middle row then we need to above and below for a win.
		if(tilePoint.getYPos() == 1)
		{
			verTwoSideTest = true;
		}
		
		//if its the corner or center then we need to check for wins along a diagonal 
		if(((tilePoint.getXPos() == 0 || tilePoint.getXPos() == 2) && (tilePoint.getYPos() == 0 || tilePoint.getYPos() == 2)) || 
			(tilePoint.getXPos() == 1 && tilePoint.getYPos() == 1))
		{
			diaTest = true;
		}
		
		
		for(int i = 0; i < 8; i++)
		{
			testCords = getTestCords(i, horTwoSideTest, verTwoSideTest, diaTest, tilePoint); //generates points to check
			
			gameWon = isGameOver(testCords, clickedTile); //checks those points and returns true if they win.
			
			if(gameWon)
			{
//				drawWinningLine(this.getGraphics(), clickedTile, testCords);
				return true;
			}
		}
		
		return gameWon;
	}

	/**
	 * This method determines what other points must be check to determine if the game is own or not </br>
	 * Test points are based on a direction and the clicked point	
	 * 
	 * @param dir - Direction to generate test points. Think of them like a compass 0 = North, 1 = North East, etc
	 * @param horTwoSideTest - Does the point need to check on both sides for horizontal directions
	 * @param verTwoSideTest - Does the point need to check on both sides for vertical directions
	 * @param diaTest - Does the point need to check points along a diagonal for wins
	 * @param tilePoint - The clicked tiles point
	 * 
	 * @return The points to be checked for a win
	 */
	private Point[] getTestCords(int dir, boolean horTwoSideTest, boolean verTwoSideTest, boolean diaTest, Point tilePoint) 
	{
		Point [] testPoints = {new Point(), new Point()};
		
		//directions North or South
		if(dir == 0 || dir == 4)
		{
			if(verTwoSideTest) //with a two side test the returned points will be the same for both directions.
			{
				testPoints[0] = tilePoint.getNeighbor(0);
				testPoints[1] = tilePoint.getNeighbor(4);
			}
			else
			{
				testPoints[0] = tilePoint.getNeighbor(dir); //gets the neighbor in the correct direction
				testPoints[1] = testPoints[0].getNeighbor(dir);  //gets 2nd tile by getting the neighbor of the first neighbor
			}
			
			return testPoints;
		}
		
		//directions East or West
		if(dir == 2 || dir == 6)
		{
			if(horTwoSideTest) //with a two side test the returned points will be the same for both directions.
			{
				testPoints[0] = tilePoint.getNeighbor(2);
				testPoints[1] = tilePoint.getNeighbor(6);
			}
			else
			{
				testPoints[0] = tilePoint.getNeighbor(dir); //gets the neighbor in the correct direction
				testPoints[1] = testPoints[0].getNeighbor(dir); //gets 2nd tile by getting the neighbor of the first neighbor
			}
			
			return testPoints;
		}
		
		//get the correct test points for diagonal checks.
		if(dir == 1 || dir == 3 || dir == 5 || dir == 7)
		{	
			if(diaTest)
			{
				//only need to check one. The only tile with diaTest and a twoSideTest is the center. It has two side in all directions. 
				if(verTwoSideTest)
				{
					if(dir == 1 || dir == 5) //Points in directions North East and South West
					{
						testPoints[0] = tilePoint.getNeighbor(1);
						testPoints[1] = tilePoint.getNeighbor(5);
					}
					else if(dir == 3 || dir == 7) //Points in directions north West and South East
					{
						testPoints[0] = tilePoint.getNeighbor(3);
						testPoints[1] = tilePoint.getNeighbor(7);
					}
				}
				else
				{
					testPoints[0] = tilePoint.getNeighbor(dir); //gets the neighbor in the correct direction
					testPoints[1] = testPoints[0].getNeighbor(dir); //gets 2nd tile by getting the neighbor of the first neighbor
				}
				
				return testPoints;
			}
		}
		
		return testPoints; //will be returned if its a invalid direction || diagonal direction with no diagonal check needed
	}
	
	private boolean isGameOver(Point [] testPoints, Tile clickedTile)
	{
		for(int i = 0; i < 2; i++)
		{
			Point currentPoint = testPoints[i];
			Tile currentTile;
			
			int arrayIndex;
			
			
			//checks to make sure the test point isn't outside the board. 
			if(currentPoint.getXPos() < 0 || currentPoint.getXPos() > (numCols - 1) ||  
			   currentPoint.getYPos() < 0 || currentPoint.getYPos() > (numRows - 1))
			{
				return false;
			}
			
			arrayIndex = currentPoint.getYPos() * numRows + currentPoint.getXPos(); //converts 2d position to 1d index
			
			currentTile = gameBoard[arrayIndex];
			
			if(clickedTile.getState() != currentTile.getState()) //if it doesn't match, can't be 3 in a row
			{
				return false;
			}
		}
		
		//if it makes it out of the for loop that all the points were in the board
		//and they all have the same state so the game is won.
		return true;
	}
	
	public void setPlayer(int player)
	{
		if(player == 2 || player == 1)
		{
			this.player = player;
		}
	}
	
	public int getCurrentPlyer()
	{
		return player;
	}
	
	public void restart()
	{
		for(Tile t : gameBoard)
		{
			t.updateState(TileState.BLANK);
		}
		
		player = 1;
		gameWon = false;
	}

    public TicTacUI getOwnerTicTacToe()
    {
        return owner;
    }

    public Game getOwnerGame()
    {
        return ownerGame;
    }
}
