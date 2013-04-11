package clueGame;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class NotesGui extends JFrame{

	private ArrayList<Card> cards = new ArrayList<Card>();
	ArrayList<Card> personList = new ArrayList<Card>();
	ArrayList<Card> roomList = new ArrayList<Card>();
	ArrayList<Card> weaponList = new ArrayList<Card>();
	public NotesGui(ArrayList<Card> cards) {
		this.cards = cards;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Detective Notes");
		setSize(600,600);
		setLayout(new GridLayout(3,2));
		setLists();
		add(addCheckBoxes(personList, "Person"));
		add(addComboBoxes(personList, "Person"));
		
		add(addCheckBoxes(roomList, "Room"));
		add(addComboBoxes(roomList, "Room"));
		
		add(addCheckBoxes(weaponList, "Weapon"));
		add(addComboBoxes(weaponList, "Weapon"));
	}

	public void setLists() {

		for(Card c : cards){
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
	
	private JPanel addCheckBoxes(ArrayList<Card> c, String type) {
		JPanel checkPanel = new JPanel();
		checkPanel.setLayout(new GridLayout(0,2));
		for(Card c2 : c) {
			checkPanel.add(new JCheckBox(c2.getName()));
		}
		checkPanel.setBorder((Border) new TitledBorder (new EtchedBorder(), type));
		return checkPanel;
	}
	
	private JComboBox addComboBoxes(ArrayList<Card> c, String type) {
		JComboBox comboBox = new JComboBox();
		for(Card c2 : c) {
			comboBox.addItem(c2.getName());
		}
		comboBox.setBorder((Border) new TitledBorder (new EtchedBorder(), type + " Guess"));
		return comboBox;
	}
	






	public static void main(String[] args) {

	}

}
