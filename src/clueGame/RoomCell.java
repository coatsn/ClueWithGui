package clueGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RoomCell extends BoardCell {
	
	//enumerated type door direction
	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE};
	
	//variables
	private DoorDirection doorDirection;
	char roomInitial;
		
	//methods
	@Override
	public boolean isRoom(){
		//isRoom = true;
		return isRoom;
	}
	
	@Override
	public boolean isDoorway(){
		//isDoor = true;
		return isDoor;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public char getRoomInitial() {
		return roomInitial;
	}

	public void setDoorDirection(DoorDirection doorDirection) {
		this.doorDirection = doorDirection;
	}

	@Override
	public void draw(Graphics g, Board b) {
		g.setColor(Color.GRAY);
		if(isTarget){
			g.setColor(Color.CYAN);
		}
		g.fillRect(col * SQUARE_SIZE,row * SQUARE_SIZE ,SQUARE_SIZE, SQUARE_SIZE);
		g.setColor(Color.BLUE);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(3));
		switch(doorDirection) {
		case UP: g.drawLine(col * SQUARE_SIZE,row * SQUARE_SIZE + 1,(col + 1)* SQUARE_SIZE,row * SQUARE_SIZE + 1);
		break;
		case DOWN: g.drawLine(col * SQUARE_SIZE,(row + 1) * SQUARE_SIZE - 1,(col + 1)* SQUARE_SIZE,(row + 1) * SQUARE_SIZE - 1);
		break;
		case LEFT: g.drawLine(col * SQUARE_SIZE + 1,row * SQUARE_SIZE,col * SQUARE_SIZE + 1, (row + 1) * SQUARE_SIZE);
		break;
		case RIGHT: g.drawLine((col + 1) * SQUARE_SIZE-1,row * SQUARE_SIZE,(col + 1)* SQUARE_SIZE-1, (row + 1) * SQUARE_SIZE);
		break;
		}
		if(isName){
			Map<Character,String> tempRooms = b.getRooms();
			g.drawString(tempRooms.get(roomInitial), col * SQUARE_SIZE, row * SQUARE_SIZE);
		}
		
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

	
	
	//setter for door direction
	
	
	//@Override
	//public void draw(){};
	
}
