package clueGame;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class ClueControl extends JPanel {
	private ClueGame clueGame;
	private String currentPlayer;
	private Board board;
	private int roll;
/*	private TurnPanel turn;
	private NextButton n;
	private AccusationButton b;
	private GuessPanel g;
	private GuessResultPanel grp;
	private RollPanel r;*/
	
	public ClueControl(ClueGame clueGame) {
		this.clueGame = clueGame;
		board = clueGame.board;
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setTitle("Clue");

/*		turn = new TurnPanel(clueGame);
		add(turn);
		n = new NextButton(clueGame, board);
		add(n);
		b = new AccusationButton();
		add(b);
		r = new RollPanel();
		add(r);
		g = new GuessPanel();
		add(g);
		grp = new GuessResultPanel();
		add(grp);*/
		
		
		createPanels();
	}
	
	private void createPanels() {
		setSize(600, 200);
		setLayout(new GridLayout(2,3));
		
		JPanel turn = new JPanel();
		JTextField ans = new JTextField();
		JLabel label = new JLabel("Whose turn?");
		currentPlayer = clueGame.getCurrentPlayer().getName();
		ans = new JTextField(currentPlayer);
		turn.setLayout(new GridLayout(2,1));
		turn.add(label);
		turn.add(ans);
		add(turn);
		
		JPanel n = new JPanel();
		JButton next = new JButton("Next player");
		next.addActionListener(new NextButtonListener());
		n.add(next);
		add(n);
		
		JPanel a = new JPanel();
		JButton accuse = new JButton("Make an accusation");
		a.add(accuse);
		add(a);
		
		JPanel r = new JPanel();

		r.setLayout(new GridLayout(1,2));
		JLabel rollLabel = new JLabel("Roll");
		JTextField rolled = new JTextField(roll + "");
		r.add(rollLabel);
		r.add(rolled);
		r.setBorder((Border) new TitledBorder (new EtchedBorder(), "Die"));
		add(r);
		
		JPanel g = new JPanel();
		g.setLayout(new GridLayout(1,2));
		JLabel guess = new JLabel("Guess");
		JTextField guessed = new JTextField(20);
		g.add(guess);
		g.add(guessed);
		g.setBorder((Border) new TitledBorder (new EtchedBorder(), "Guess"));
		add(g);
		
		JPanel grp = new JPanel();
		grp.setLayout(new GridLayout(1,2));
		JLabel response = new JLabel("Response");
		JTextField answer = new JTextField(20);
		grp.add(response);
		grp.add(answer);
		grp.setBorder((Border) new TitledBorder (new EtchedBorder(), "Guess Result"));
		add(grp);
	}
	
	private class NextButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			clueGame.takeTurn();
			System.out.println("you rolled a: " +  roll);
			
		}
	}
	
/*	public TurnPanel getTurn() {
		return turn;
	}



	public NextButton getN() {
		return n;
	}



	public AccusationButton getB() {
		return b;
	}



	public GuessPanel getG() {
		return g;
	}



	public GuessResultPanel getGrp() {
		return grp;
	}



	public RollPanel getR() {
		return r;
	}*/

	public Board getBoard() {
		return board;
	}
	

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}


	
	

}
