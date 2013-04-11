/*package clueGame;

import java.util.ArrayList;
import java.util.Set;


public class ComputerPlayer extends Player {
	private Suggestion sug;
	private ArrayList<Card> seenCards;
	
	public ComputerPlayer(String strColor, String name) {
		super(strColor, name);
		// TODO Auto-generated constructor stub
	}
	
	public void updateSeen(Card c) {
		
	}
	
	public void createSuggestion() {
		
	}
	
	public BoardCell pickTarget(Set<Integer> targets){
		return null;
	}
	
	//test purposes only
	public void setLocation(BoardCell loc) {
		
	}
	
	public Suggestion getSuggestion() {
		return null;
	}
	
	public void setSuggestion(Card a, Card b, Card c) {

	}
	
	public ArrayList<Card> getSeen() {
		return null;
	}
}*/

package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;


public class ComputerPlayer extends Player {
	private String name;
	private Suggestion sug;
	private ArrayList<Card> seenCards = new ArrayList<Card>();
	private ArrayList<BoardCell> cells = new ArrayList<BoardCell>();
	private ArrayList<Card> cards = new ArrayList<Card>();
	private Card compLocation;
	
	public ComputerPlayer() {
		
	}

	public ComputerPlayer(String strColor, String name) {
		super(strColor, name);
		// TODO Auto-generated constructor stub
	}
	
	public ComputerPlayer(String color, String name, ArrayList<Card> cards, ArrayList<BoardCell> cells) {
		super(color , name);
		this.cells = cells;
		isHuman = false;
		this.cards = cards;
	}
	
	public ComputerPlayer(String strColor, String name, int r, int c , ArrayList<Card> cards) {
		super(strColor, name, r, c);
		this.cards = cards;
		isHuman = false;
	}
	
	
	public boolean isHuman() {
		return false;
	}
	
	public void updateSeen(Card c) {
		seenCards.add(c);
	}
	
	public void clearSeen() {
	}
	
	public void createSuggestion() {
		Card p, r, w;
		ArrayList<Card> personList = new ArrayList<Card>();
		ArrayList<Card> roomList = new ArrayList<Card>();
		ArrayList<Card> weaponList = new ArrayList<Card>();
		for(Card c : cards){
			if(!seenCards.contains(c)){
				if(c.type.equals(Card.Type.PERSON)){
					personList.add(c);
				}
				if(c.type.equals(Card.Type.ROOM)){
					roomList.add(c);
				}
				if(c.type.equals(Card.Type.WEAPON)){
					weaponList.add(c);
				}
			}
		}
		int x = new Random().nextInt(personList.size());
		p = personList.get(x);
//		x = new Random().nextInt(roomList.size());
//		r = roomList.get(x);
		r = compLocation;
		x = new Random().nextInt(weaponList.size());
		w = weaponList.get(x);
		sug = new Suggestion(p, r, w);
	}
	
	public BoardCell pickTarget(Set<BoardCell> targets){
		int size = targets.size();
		int randInt = new Random().nextInt(size);
		int i = 0;
		for(BoardCell x : targets)
		{
		    if (i == randInt) {
		    	
		    	return x;
		    } else
		    	i = i + 1;
		}
		return null;
	}
	
	public void takeTurn(Set<BoardCell> targets) {
		Random rand = new Random();
		int i = 0;
		int index = rand.nextInt(targets.size());
		BoardCell selected = null;
		for(BoardCell b : targets) {
			if(i == index)
				selected = b;
			i++;
				
		}
		setBoardLocation(selected.row, selected.col);
	}
	
	//test purposes only
	public void setLocation(Card c) {
		compLocation = c;
	}
	
	public Card getLocation() {
		return compLocation;
	}
	
	public Suggestion getSuggestion() {
		return sug;
	}
	
	public void setSuggestion(Card a, Card b, Card c) {

	}
	
	public ArrayList<Card> getSeen() {
		return seenCards;
	}
}

