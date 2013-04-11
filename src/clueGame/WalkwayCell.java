package clueGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.Field;

public class WalkwayCell extends BoardCell {
	
	//methods
	@Override
	public boolean isWalkway(){
		return isWalkway;
	}

	@Override
	public void draw(Graphics g, Board b) {
		if(isTarget){
			g.setColor(Color.CYAN);
		}
		else{
			g.setColor(Color.YELLOW);
		}
		g.fillRect(col * SQUARE_SIZE,row * SQUARE_SIZE ,SQUARE_SIZE, SQUARE_SIZE);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(1));
		g.setColor(Color.BLACK);
		g.drawRect(col * SQUARE_SIZE ,row * SQUARE_SIZE ,SQUARE_SIZE, SQUARE_SIZE);
	}
	
	public Color convertColor(String strColor) {
		Color color; 
		try {     
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); } 
		catch (Exception e) {  
			color = null; // Not defined } 
		}
		return color;
	}
	
}
