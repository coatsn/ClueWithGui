package clueGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class ClueGame extends JFrame {
	ArrayList<Card> cards = new ArrayList<Card>();
	ArrayList<Card> dealtCards = new ArrayList<Card>();
	ArrayList<Player> players = new ArrayList<Player>();
	Board board;
	NotesGui notesGui;
	ClueControl controls;
	HumanPlayer player;
	int turn;
	Solution solution;
	int index = 0;
	private int diceRoll;

	ClueGame() throws FileNotFoundException, BadConfigFormatException{
		loadPlayers();
		loadCards();
		selectAnswer();
		deal();
		controls = new ClueControl(this);
		board = new Board("ConfigLayout.csv", "ConfigRooms.txt", players);
		
		notesGui = new NotesGui(getCards());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue");
		setSize(600,600);
		add(board, BorderLayout.CENTER);
		add(controls, BorderLayout.SOUTH);
		JMenuBar bar = new JMenuBar();
		bar.add(createMenu());
		add(bar, BorderLayout.NORTH);
		Player human = players.get(0);
		
		
		HumanHandPanel hPanel = new HumanHandPanel(human.getCards());
		add(hPanel, BorderLayout.EAST);
		//player = (HumanPlayer)players.get(0);
	}

	public void deal() throws FileNotFoundException {
		ArrayList<Card> temp = dealtCards;
		Card card = new Card();
		int playerCount = 0;
		int count = 0;
		Collections.shuffle(temp);
		for(Card c : temp) {
			/*if(count < 3) {
				players.get(playerCount).deal(c);
				count++;
			} else {
				count = 0;
				players.get(playerCount).deal(c);
				playerCount++;
			}*/
			players.get(playerCount%6).deal(c);
			playerCount++;
		}


	}

	public void nextPlayer() {
		index++;
	}
	
	public void roll() {
		Random rand = new Random();
		diceRoll = rand.nextInt(6) + 1;
	}

	public void takeTurn() {
		roll();
		controls.setRoll(diceRoll);
	//	controls.updateFields();
		boolean playerTakenTurn = false;
		if(isCurrentHuman()) {
	//		System.out.println("human");
			HumanPlayer human = (HumanPlayer) getCurrentPlayer();
			board.startTargets(board.calcIndex(human.getRow(), human.getCol()), diceRoll);
	//		System.out.println("you actually rolled a: " + diceRoll);
			human.takeTurn(board.getTargets());
			playerTakenTurn = true;

		} else {
	//		System.out.println("computer");
			ComputerPlayer comp = (ComputerPlayer) getCurrentPlayer();
			board.startTargets(board.calcIndex(comp.getRow(), comp.getCol()), diceRoll);
	//		System.out.println("you actually rolled a: " + diceRoll);
			comp.takeTurn(board.getTargets());
			playerTakenTurn = true;
		}
	//	System.out.println(getCurrentPlayer().getName());
		if(playerTakenTurn)
			nextPlayer();
	}

	public boolean checkAccusation(Solution solution){
		boolean b;
		if(solution.equals(this.solution))
			b = true;
		else
			b = false;

		return b;
	}

	public void loadPlayers() throws FileNotFoundException{
		FileReader reader = new FileReader("players.txt");
		Scanner scan = new Scanner(reader);
		boolean isPlayer = true;

		while(scan.hasNextLine()) {
			int r, c;
			r = scan.nextInt();
			c = scan.nextInt();
			String color, name;
			color = scan.next();
			name = scan.nextLine();
			if(isPlayer){
				players.add(new HumanPlayer(color,name, r, c));
			}
			else{
				players.add(new ComputerPlayer(color,name, r, c, cards));
			}
			isPlayer = false;
		}


	}

	public void loadCards() throws FileNotFoundException{
		FileReader reader = new FileReader("cards.txt");
		Scanner scan = new Scanner(reader);
		String room, name;


		while(scan.hasNextLine()) {
			room = scan.next();
			name = scan.nextLine();
			switch(room) {
			case ("weapon") :
				cards.add(new Card(Card.Type.WEAPON, name));
			break;
			case ("room") :
				cards.add(new Card(Card.Type.ROOM, name));
			break;
			case ("person") :
				cards.add(new Card(Card.Type.PERSON, name));
			break;
			}
		}
	}

	public Card handleSuggestion(Card person, Card room, Card weapon, Player suggestingPlayer){
		ArrayList<Card> tempList = new ArrayList<Card>();
		for(Player p: players){
			if(!p.equals(suggestingPlayer)){
				if(p.disproveSuggestion(person, room, weapon) != null){
					tempList.add(p.disproveSuggestion(person, room, weapon));
				}
			}
		}
		if(tempList.isEmpty()){
			return null;
		}
		int randInt = new Random().nextInt(tempList.size());
		return tempList.get(randInt);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void selectAnswer(){
		Card p, r, w;
		ArrayList<Card> personList = new ArrayList<Card>();
		ArrayList<Card> roomList = new ArrayList<Card>();
		ArrayList<Card> weaponList = new ArrayList<Card>();
		for(Card c : cards){
			//		if(!seenCards.contains(c)){
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
		//}
		int x = new Random().nextInt(personList.size());
		p = personList.get(x);

		x = new Random().nextInt(roomList.size());
		r = roomList.get(x);

		x = new Random().nextInt(weaponList.size());
		w = weaponList.get(x);

		for(Card c : cards) {
			if(!c.equals(p) && !c.equals(r) && !c.equals(w))
				dealtCards.add(c);
		}
		solution = new Solution(p,w,r);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public ArrayList<Card> getDealtCards() {
		return dealtCards;
	}

	public Player getCurrentPlayer() {
		return players.get(index % 6);
	}

	public boolean isCurrentHuman() {
		return players.get(index % 6).isHuman;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public int getDiceRoll() {
		return diceRoll;
	}
	//*************************************** GUI STUFF*************************************************
	
	private JMenu createMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createFileExitItem());
		menu.add(createNotesItem());
		
		return menu;
	}
	

	
	private JMenuItem createNotesItem() {
		JMenuItem item = new JMenuItem("Detective Notes");
		
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				notesGui.setVisible(true);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	public static void main(String[] args) throws FileNotFoundException, BadConfigFormatException {
		ClueGame game = new ClueGame();
		JOptionPane p = new JOptionPane();
		p.showMessageDialog(game,"You are Colonel Mustard, press next player to begin.", "Welcome", JOptionPane.INFORMATION_MESSAGE);
		game.setVisible(true);
	}
}
