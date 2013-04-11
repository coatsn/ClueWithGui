package clueGame;

public class Suggestion {
	private Card roomSug, playerSug, weaponSug;
	
	public Suggestion(Card p, Card r, Card w) {
		playerSug = p;
		roomSug = r;
		weaponSug = w;
	}
	
	public boolean equals(Suggestion s) {
		if(s.getPlayerSug().equals(playerSug) && s.getRoomSug().equals(roomSug) && s.getWeaponSug().equals(weaponSug))
			return true;
		else
			return false;
	}

	public Card getRoomSug() {
		return roomSug;
	}

	public Card getPlayerSug() {
		return playerSug;
	}

	public Card getWeaponSug() {
		return weaponSug;
	}
	
}
