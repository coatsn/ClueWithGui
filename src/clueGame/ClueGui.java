package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ClueGui extends JFrame {

	private NotesGui notesGui;
	public ClueGui() throws FileNotFoundException, BadConfigFormatException {
		ClueGame clueGame = new ClueGame();
		
		notesGui = new NotesGui(clueGame.getCards());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue");
		setSize(600,600);
		add(clueGame.getBoard(), BorderLayout.CENTER);
		ClueControl cControl = new ClueControl(clueGame);
		add(cControl, BorderLayout.SOUTH);
		JMenuBar bar = new JMenuBar();
		bar.add(createMenu());
		add(bar, BorderLayout.NORTH);
		Player human = clueGame.getPlayers().get(0);
		
		
		HumanHandPanel hPanel = new HumanHandPanel(human.getCards());
		add(hPanel, BorderLayout.EAST);
		//createPanels();
		
	}
	
	public void createPanels() {
		
	}
	
	
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
		
		ClueGui gui = new ClueGui();
		JOptionPane p = new JOptionPane();
		p.showMessageDialog(gui,"You are Colonel Mustard, press next player to begin.", "Welcome", JOptionPane.INFORMATION_MESSAGE);
		gui.setVisible(true);
	}

}
