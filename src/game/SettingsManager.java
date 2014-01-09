package game;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to hold settings for things such as background color, and x/o colors
 * 
 * <b> Working but no where as complete as I would like</b>
 *
 */
public class SettingsManager 
{
	private static Color background = new Color(230,230,230); 
	private static String x = "Black";
	private static String o = "Black";
	
	public SettingsManager()
	{
		load();
	}

	private void load()
	{
		Scanner in;

        File settingsFile = new File(getClass().getResource("/Settings/Settings.txt").getPath());

        try
        {
            in = new Scanner(settingsFile);
		}
		catch (FileNotFoundException e)
		{
            System.err.println("FileNotFoundException");
			return;
		}

		ArrayList <String> lines  = new ArrayList<String>();
				
		while(in.hasNext())
		{
			lines.add(in.nextLine());
		}
	
		for(String line : lines)
		{
			switch(line.charAt(0))
			{
				case 'b' : background = lineToColor(line);
						   break;
						   
				case 'x' : x = line.substring(line.indexOf('=') + 1);
						   break;
						   
				case 'o' : o = line.substring(line.indexOf('=') + 1);
						   break;
			}
		}
	}

	private static Color lineToColor(String line)
	{
		int charLoc = line.indexOf('=');
		int [] valueRGB = new int[3];
		
		//substring between equals and first comma
		valueRGB[0] = Integer.valueOf(line.substring(charLoc + 1, line.indexOf(',')));
		
		charLoc = line.indexOf(',');
		 
		//substring between first comma and second comma
		valueRGB[1] = Integer.valueOf(line.substring(charLoc + 1, line.lastIndexOf(',')));
		
		charLoc = line.lastIndexOf(',');
		
		valueRGB[2] = Integer.valueOf(line.substring(charLoc + 1));
		
		for(int i : valueRGB)
		{
			if(i < 0 || i > 255)
			{
				return Color.black;
			}
		}
		
		return new Color(valueRGB[0], valueRGB[1], valueRGB[2]);
	}

	public static Color getBackground() {
		return background;
	}

	public static String getX() {
		return x.trim();
	}

	public static String getO() {
		return o.trim();
	}
}
