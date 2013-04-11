package clueGame;

import java.util.Set;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String strColor, String name) {
		super(strColor, name);
		isHuman = true;
	}
	
	public HumanPlayer(String strColor, String name, int r, int c) {
		super(strColor, name, r, c);
		isHuman = true;
	}
	
	public void takeTurn(Set<BoardCell> targets){
		for(BoardCell b : targets) {
			b.setHumanTarget();
		}
		//insert logic here
		for(BoardCell b : targets) {
			//b.resetHumanTarget();
		}
		
	}
	
	public boolean isHuman() {
		return true;
	}
	
}
