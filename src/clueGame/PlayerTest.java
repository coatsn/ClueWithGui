package clueGame;

//NOTE: pair programming was used to write all of this, as both of us live in the same house


import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {

	private ClueGame game;
	private ComputerPlayer player;
	private Board board;
	private Card knife, revolver, candlestick, wrench , pipe, green,
	peacock, plum, mustard, white, kitchen, lounge, ballroom, 
	conservatory, hall;
	private ArrayList<Card> cards = new ArrayList<Card>();
	private final int NUMBER_OF_CARDS = 22;
	private final int NUMBER_OF_PLAYERS = 6;

	@Before
	public void setUp() throws BadConfigFormatException, FileNotFoundException {
	//	board.calcAdjacencies();
		board = new Board("ConfigLayout.csv", "ConfigRooms.txt");
		game = new ClueGame();
		player = new ComputerPlayer("Red", "Mrs. White", game.cards, board.getAllCells());
		knife = new Card(Card.Type.WEAPON, "Knife");
		revolver = new Card(Card.Type.WEAPON, "Revolver");
		candlestick = new Card(Card.Type.WEAPON, "Candlestick");
		wrench = new Card(Card.Type.WEAPON, "Wrench");
		pipe = new Card(Card.Type.WEAPON, "Pipe");
		green = new Card(Card.Type.PERSON, "Mr. Green");
		peacock = new Card(Card.Type.PERSON, "Mrs. Peacock");
		plum = new Card(Card.Type.PERSON, "Prof. Plum");
		mustard = new Card(Card.Type.PERSON, "Col. Mustard");
		white = new Card(Card.Type.PERSON, "Mrs. White");
		kitchen = new Card(Card.Type.ROOM, "Kitchen");
		lounge = new Card(Card.Type.ROOM, "Lounge");
		conservatory = new Card(Card.Type.ROOM, "Conservatory");
		hall = new Card(Card.Type.ROOM, "Hall");
		ballroom = new Card(Card.Type.ROOM, "Ballroom");
		
		cards.add(knife);
		cards.add(revolver);
		cards.add(candlestick);
		cards.add(wrench);
//		cards.add(pipe);
		cards.add(green);
		cards.add(peacock);
		cards.add(plum);
//		cards.add(mustard);
		cards.add(white);
		cards.add(kitchen);
		cards.add(lounge);
		cards.add(conservatory);
		cards.add(hall);
		cards.add(ballroom);

	}

	@Test
	public void testLoadCards() throws FileNotFoundException{
		assertEquals(game.cards.size(), NUMBER_OF_CARDS);
		assert(game.cards.contains(knife));
		assert(game.cards.contains(green));
		assert(game.cards.contains(kitchen));
		
	}
	
	@Test
	public void testLoadPlayers() throws FileNotFoundException{
		assertEquals(game.players.size(), NUMBER_OF_PLAYERS);
	}
	
	@Test
	public void testDeal() throws FileNotFoundException{
//		game.deal();
		assert(Math.abs(game.players.get(1).getSize() - game.players.get(3).getSize() ) < 2);
		ArrayList<Card> tempList = new ArrayList<Card>();
		for(int x = 0; x < game.players.size(); x++){
			ArrayList<Card> pCards = game.players.get(x).getCards();
	//		System.out.println("player: " + x + " size : " + pCards.size());
			for(Card c: pCards){
				assertFalse(tempList.contains(c));
				tempList.add(c);
			}
		}
		assertEquals(tempList.size(), NUMBER_OF_CARDS);
		
	}
	
	@Test
	public void testAccusation(){
		game.solution = new Solution("Green", "Knife", "Kitchen");
		assertTrue(game.checkAccusation(new Solution("Green", "Knife" , "Kitchen" )));
		assertFalse(game.checkAccusation(new Solution("Peacock", "Knife" , "Kitchen" )));
		assertFalse(game.checkAccusation(new Solution("Green", "Revolver" , "Kitchen" )));
		assertFalse(game.checkAccusation(new Solution("Green", "Knife" , "Ballroom" )));
	}
	
	@Test
	public void testSelectLocation() {
		board.calcAdjacencies();
		int loc1, loc2, loc3, locVisitR1, locVisitR2, locVisitR3, locVisitR4, locVisitR5, locVisitR6, locVisitRoom;
		loc1 = board.calcIndex(9,0);
		loc2 = board.calcIndex(3, 3);
		loc3 = board.calcIndex(24,24);
		locVisitR1 = 0;
		locVisitR2 = 0;
		locVisitR3 = 0;
		locVisitR4 = 0;
		locVisitR5 = 0;
		locVisitRoom = 0;
		//list of targets that does not include a room
		board.startTargets(loc1, 2);
		for(int i = 0; i < 100; i++) {
			BoardCell b = player.pickTarget(board.getTargets());

			if(b.equals(board.getCells(board.calcIndex(9, 2))))
				locVisitR1++;
			else if(b.equals(board.getCells(board.calcIndex(10,1))))
				locVisitR2++;
			else {
				fail("Target is not valid");
			}
		}
		
		assertEquals(locVisitR1 + locVisitR2, 100);
		assertTrue(locVisitR1 > 10);
		assertTrue(locVisitR2 > 10);
		
		
		//list of targets starting from a room
		board.startTargets(loc2,2);
		System.out.println(board.getTargets().size());
		for(int i = 0; i < 100; i++) {
			BoardCell b = player.pickTarget(board.getTargets());
			
			if(b.equals(board.getCells(board.calcIndex(3, 5))))
				locVisitR3++;
			else if(b.equals(board.getCells(board.calcIndex(4,4))))
				locVisitR4++;
			else if(b.equals(board.getCells(board.calcIndex(2,4))))
				locVisitR5++;
			else
				fail("Target is not valid");
		}
		
		
		assertEquals(locVisitR3 + locVisitR4 + locVisitR5, 100);
		assertTrue(locVisitR3 > 10);
		assertTrue(locVisitR4 > 10);
		assertTrue(locVisitR5 > 10);
		
		locVisitR3 = 0;
		locVisitR4 = 0;
		
		//list of targets that include a room
		board.startTargets(loc3,2);
		for(int i = 0; i < 100; i++) {
			BoardCell b = player.pickTarget(board.getTargets());
			
			if(b.equals(board.getCells(board.calcIndex(24, 22))))
				locVisitRoom++;
			else if(b.equals(board.getCells(board.calcIndex(22, 24))))
				locVisitR3++;
			else if(b.equals(board.getCells(board.calcIndex(23, 23))))
				locVisitR4++;
			else
				fail("Invalid target, should be a room");
		}
		
		assertEquals(locVisitRoom + locVisitR3 + locVisitR4, 100);
		assertTrue(locVisitR3 > 10);
		assertTrue(locVisitR4 > 10);
		assertTrue(locVisitRoom > 10);
		
	}
	
	@Test
	public void testDisprove() {
		//One player, one correct match
		ComputerPlayer cp = new ComputerPlayer("Red", "Mrs. White", game.cards, board.getAllCells());
		cp.deal(knife);
		cp.deal(revolver);
		cp.deal(peacock);
		cp.deal(plum);
		cp.deal(kitchen);
		cp.deal(ballroom);
		
		assertEquals(cp.disproveSuggestion(peacock, lounge, candlestick), peacock);
		assertEquals(cp.disproveSuggestion(mustard, lounge, revolver), revolver);
		assertEquals(cp.disproveSuggestion(mustard, kitchen, candlestick), kitchen);
		assertEquals(cp.disproveSuggestion(mustard, lounge, candlestick), null);
		
		//One player, multiple matches
		int pCount, kCount;
		pCount = 0;
		kCount = 0;
		ComputerPlayer cp2 = new ComputerPlayer("Red", "Mrs. White", game.cards, board.getAllCells());
		cp2.deal(knife);
		cp2.deal(revolver);
		cp2.deal(peacock);
		cp2.deal(plum);
		cp2.deal(kitchen);
		cp2.deal(ballroom);
		
		for(int i = 0; i < 100; i++) {
			Card dis = cp2.disproveSuggestion(plum, kitchen, candlestick);
			if(dis == plum)
				pCount++;
			else if(dis == kitchen)
				kCount++;
			else
				fail("Not a valid card");
		}
		
		assertEquals(pCount + kCount, 100);
		assertTrue(pCount > 10);
		assertTrue(kCount > 10);
		
		//Makes sure every player is queried
		game.players.add(new HumanPlayer("Red", "Player 0"));
		for(int i = 1; i < 6; i++)
			game.players.add(new ComputerPlayer("Red", "Player " + i));
		game.players.get(0).deal(candlestick);
		game.players.get(0).deal(kitchen);
		game.players.get(0).deal(plum);
		
		game.players.get(1).deal(revolver);
		game.players.get(1).deal(lounge);
		game.players.get(1).deal(mustard);
		
		game.players.get(2).deal(knife);
		game.players.get(2).deal(ballroom);
		game.players.get(2).deal(green);
		
		game.players.get(3).deal(wrench);
		game.players.get(3).deal(conservatory);
		game.players.get(3).deal(peacock);
		
		game.players.get(4).deal(revolver);
		game.players.get(4).deal(ballroom);
		game.players.get(4).deal(peacock);
		
		game.players.get(5).deal(candlestick);
		game.players.get(5).deal(lounge);
		game.players.get(5).deal(green);
		
		Card c0, c1, c2, c3, c4, c5;
		
		//suggestion that no player can disprove
		game.handleSuggestion(white, hall, pipe, game.players.get(0));
		c1 = game.players.get(1).disproveSuggestion(white, hall, pipe);
		c2 = game.players.get(2).disproveSuggestion(white, hall, pipe);
		c3 = game.players.get(3).disproveSuggestion(white, hall, pipe);
		c4 = game.players.get(4).disproveSuggestion(white, hall, pipe);
		c5 = game.players.get(5).disproveSuggestion(white, hall, pipe);
		Boolean b = (c1 == null && c2 == null && c3 == null && c4 == null && c5 == null);
		assertTrue(b);
		
		//suggestion that only the human player can disprove
		game.handleSuggestion(plum, hall, pipe, game.players.get(1));
		c0 = game.players.get(0).disproveSuggestion(plum, hall, pipe);
		assertEquals(c0, plum);
		
		//suggestion made by the only player that can disprove
		game.handleSuggestion(plum, hall, pipe, game.players.get(0));
		c1 = game.players.get(1).disproveSuggestion(plum, hall, pipe);
		c2 = game.players.get(2).disproveSuggestion(plum, hall, pipe);
		c3 = game.players.get(3).disproveSuggestion(plum, hall, pipe);
		c4 = game.players.get(4).disproveSuggestion(plum, hall, pipe);
		c5 = game.players.get(5).disproveSuggestion(plum, hall, pipe);
		b = (c1 == null && c2 == null && c3 == null && c4 == null && c5 == null);
		assertTrue(b);
		
		//suggestion that two players can disprove
		int d1, d2;
		d1 = 0;
		d2 = 0;
		for(int i = 0; i < 100; i++) {
			c0 = game.handleSuggestion(plum, ballroom, pipe, game.players.get(1));
			if(c0.equals(plum))
				d1++;
			else if(c0.equals(ballroom))
				d2++;
			else
				fail("Incorrect card handling");
		}
		
		assertEquals(d1 + d2, 100);
		assertTrue(d1 > 2);
		assertTrue(d2 > 2);
		
		
	}
	
	@Test
	public void testComputerSuggestion() throws FileNotFoundException {
		game.loadCards();
		//the computer player has seen every card but mustard and pipe
		ComputerPlayer comp = new ComputerPlayer("Red", "Mrs. White", game.cards, board.getAllCells());
		comp.setLocation(kitchen);
		for(Card c : cards) {
			if(!c.equals(mustard) && !c.equals(pipe))
				comp.updateSeen(c);
		}
		comp.createSuggestion();
		Suggestion s = comp.getSuggestion();
		ArrayList<Card> seen = comp.getSeen();
		assertTrue(s.getRoomSug() == comp.getLocation());
		assertFalse(seen.contains(mustard));
		assertFalse(seen.contains(pipe));
		
	}
	
}
