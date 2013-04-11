package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;


public class Player {
	private ArrayList<BoardCell> targets = new ArrayList<BoardCell>();
	protected boolean isHuman;
	private String name;
	private Color color;
	private ArrayList<Card> cards;
	private int row, col;

	public Player() {
		cards = new ArrayList<Card>();
	}
	
	public Player(String strColor, String name, int r, int c) {
		cards = new ArrayList<Card>();
		this.name = name;
		color = convertColor(strColor);
		row = r;
		col = c;
	}
	
	public Player(String strColor, String name) {
		cards = new ArrayList<Card>();
		this.name = name;
		color = convertColor(strColor);
	}
	
	public Card disproveSuggestion(Card person, Card room, Card weapon){
		ArrayList<Card> tempCards = new ArrayList<Card>();
		for(Card c : cards){
			if(c.equals(person) || c.equals(room) || c.equals(weapon)){
				tempCards.add(c);
			}
		}
		Random randInts = new Random();
		if(tempCards.isEmpty()){
			return null;
		}
		int randInt = randInts.nextInt(tempCards.size());
		return tempCards.get(randInt);
	}

	public void deal(Card c) {
		cards.add(c);
	}

	public int getSize(){
		return cards.size();
	}
	
	public boolean equals(Player other){
		if(name.equals(other.name)){
			return true;
			
		}
		else{
			return false;
		}
	}

	public ArrayList<Card> getCards(){
		return cards;
	}

	// Be sure to trim the color, we don't want spaces around the name
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
	
	public void setRow(int r) {
		row = r;
	}
	
	public void setCol(int c) {
		col = c;
	}
	
	public void setBoardLocation(int r, int c) {
		row = r;
		col = c;
	}
	
	public void draw(Graphics g, Board b) {
		g.setColor(color);
		g.fillOval(col * b.SQUARE_SIZE,row * b.SQUARE_SIZE,b.SQUARE_SIZE,b.SQUARE_SIZE);
	}
	
	public String getName() {
		return name;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}
