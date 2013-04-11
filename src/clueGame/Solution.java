package clueGame;

public class Solution {
	Card person, weapon, room;
	String p, w, r;
	
	Solution(String p, String w, String r){
		this.p = p;
		this.w = w;
		this.r = r;
	}
	
	public String getP() {
		return p;
	}

	public String getW() {
		return w;
	}

	public String getR() {
		return r;
	}

	Solution(Card person, Card weapon, Card room){
		this.person = person;
		this.weapon = weapon;
		this.room = room;
	}
	
	public boolean equals(Solution s) {
		if(person.equals(s.getPerson()) && weapon.equals(s.getWeapon()) && room.equals(s.getRoom())) 
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return person.getName() + " " + weapon.getName() + " " + room.getName();
	}

	public Card getPerson() {
		return person;
	}

	public Card getWeapon() {
		return weapon;
	}

	public Card getRoom() {
		return room;
	}
	
	
}
