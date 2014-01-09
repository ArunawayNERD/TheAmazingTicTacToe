package game;
 /**
  * Simply a class to hold two ints. X and Y Position for example. </br>
  * Main purpose is to be used instead of an int array
  * 
  * @version 11/30/13
  */
public class Point
{
	private int xPos;
	private int yPos;
	
	/**
	 * Default constructor sets the fields to -1 since 0 is a valid position in ticTacToe
	 */
	public Point()
	{
		xPos = -1;
		yPos = -1;
	}
	public Point(int x, int y)
	{
		setXPos(x);
		setYPos(y);
	}

	public Point getNeighbor(int direction)
	{
		Point neighbor;
		
		switch(direction)
		{
			case 0 : neighbor = new Point(xPos, yPos - 1); //North
					 break;
					 
			case 1 : neighbor = new Point(xPos + 1, yPos - 1);//North East
					 break;
					 
			case 2 : neighbor = new Point(xPos + 1, yPos); //East
					 break;
				
			case 3 : neighbor = new Point(xPos + 1, yPos + 1); //South East
					 break;
			
			case 4 : neighbor = new Point(xPos, yPos + 1); //South
					 break;
					
			case 5 : neighbor = new Point(xPos -1, yPos + 1); //South West
					 break;
			
			case 6 : neighbor = new Point(xPos -1, yPos); //West
					 break;
					
			case 7 : neighbor = new Point(xPos -1, yPos - 1); //North West
					 break;
					 
		    default : neighbor = new Point(); 
		    		  break;
		}
		
		return neighbor;
			
	}
	public int getXPos() 
	{
		return xPos;
	}

	public void setXPos(int xPos) 
	{
		this.xPos = xPos;
	}

	public int getYPos() 
	{
		return yPos;
	}

	public void setYPos(int yPos) 
	{
		this.yPos = yPos;
	}
}
