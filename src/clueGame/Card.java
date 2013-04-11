package clueGame;

public class Card{
	private String name;
	Type type;
	
	public enum Type{
		WEAPON,ROOM,PERSON
	}
	
	public Card() {
		
	}
	
	Card(Type n, String name){
		this.name = name;
		type = n;
	}
	
	public boolean equals(Card other){
		if(name.equals(other.name)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public Type getType() {
		return type;
	}
}
