package clueGame;

import java.awt.Graphics;

public abstract class BoardCell {
	//variables
	protected int row, col;
	protected boolean isWalkway = false;
	protected boolean isDoor = false, isRoom = false;
	protected final int SQUARE_SIZE = 18;
	protected boolean isName = false;
	protected boolean isTarget = false;
	
	//methods
	public boolean isWalkway(){
		return false;
	}
	
	public void setHumanTarget() {
		isTarget = true;
	}
	
	public void resetHumanTarget() {
		isTarget = false;
	}
	public boolean isRoom(){
		return false;
	}
	
	public boolean isDoorway(){
		return false;
	}
	
	public int getIndex(){
		return (row * 25) + col;
	}
	
	public boolean equals(BoardCell b) {
		if(getIndex() == b.getIndex())
			return true;
		else
			return false;
	}
	
	public abstract void draw(Graphics g, Board b);
	

}
