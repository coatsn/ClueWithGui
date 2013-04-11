package clueGame;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class HumanHandPanel extends JPanel{
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public HumanHandPanel(ArrayList<Card> cards) {
		this.cards = cards;
		setLayout(new GridLayout(0,1));
		add(cardPanel(Card.Type.PERSON));
		add(cardPanel(Card.Type.ROOM));
		add(cardPanel(Card.Type.WEAPON));
		
	}
	
	private JPanel cardPanel(Card.Type t) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,1));
		for(Card c : cards) {
			if(c.getType().equals(t)){
				p.add(new JTextField(c.getName()));
			}
		}
		p.setBorder((Border) new TitledBorder (new EtchedBorder(), t.toString()));
		return p;
	}

}
