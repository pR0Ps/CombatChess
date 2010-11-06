import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


@SuppressWarnings({"serial", "unused"})
public class CombatChess extends JFrame {

	private int turn; //player turn
	private int state; //view, move or attack (0, 1, 2)
	private String[] stateNames = {"View", "Move", "Attack"};
	
	private ArrayList<Piece> pieces; //pieces on the board
	private GamePanel gamePanel;
	private JLabel turnLabel;
	private JLabel infoLabel;
	
	private boolean gameStarted = false;
	
	//player controls (only chars work with the keyTyped method);
	private final char[] UP = {'w', 'i'};
	private final char[] DOWN = {'s', 'k'};
	private final char[] LEFT = {'a', 'j'};
	private final char[] RIGHT = {'d', 'l'};
	private final char[] ACTION = {'q', 'u'};
	private final char[] BACK = {'e', 'o'};
	
	//selected piece
	private Point[] selPoint = new Point[2];
	//current point;
	private Point[] curPoint = new Point[2];
	//used for undo
	private Piece lastMove;
	private boolean didAction;
	
	public CombatChess (){
		super ("Combat Chess || Carey Metcalfe");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setLayout (new BorderLayout());
		super.setResizable(false);
		
		//attempt to adapt to the operating system's look
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (Exception e) {} //shit son, you get the crappy Java UI ;)
		
		//set up the labels
		turnLabel = new JLabel ("Combat Chess");
		infoLabel = new JLabel ("<html>Combat<br>Chess</html>");
		
		addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {
				String[] infoText = {"Nothing selected", "Nothing selected"};
				int tempIndex;
				Point tempPoint;
				int tempState;
				
				//for both players
				for (int i = 0 ; i < 2 ; i++){
					//movement
					tempPoint = curPoint[i];
					if (e.getKeyChar() == UP[i]) tempPoint = curPoint[i].add(new Point (0, 1));
					else if (e.getKeyChar() == DOWN[i])tempPoint = curPoint[i].add(new Point (0, -1));
					else if (e.getKeyChar() == LEFT[i]) tempPoint = curPoint[i].add(new Point (-1, 0));
					else if (e.getKeyChar() == RIGHT[i]) tempPoint = curPoint[i].add(new Point (1, 0));
					if (Piece.validPos(tempPoint)){
						curPoint[i] = (Point) tempPoint.clone();
					}
					if (pieceIndex(curPoint[i]) != -1){
						infoText[i] = pieces.get(pieceIndex(curPoint[i])).toString();
					}
					
					//selection and deselection
					if (turn - 1 == i){
						//deselection
						if (e.getKeyChar() == BACK[i]){
							if (state == 1){
								selPoint[i] = null;
								state = 0;
								infoText[i] = "Piece deselected. Entering view phase.";
							}
							else if (state == 2 && lastMove != null){
								tempIndex = pieceIndex(selPoint[i]);
								pieces.remove(tempIndex);
								pieces.add((Piece) lastMove.clone());
								selPoint[i] = null;
								state = 0;
								infoText[i] = "Piece reset and deselected. Entering view phase.";
							}
						}
						//selection
						else if (e.getKeyChar() == ACTION[i]){
							//its the right players turn
							switch (state){
							case 0: //view
								tempIndex = pieceIndex(curPoint[i]);
								if (tempIndex != -1 && pieces.get(tempIndex).getPlayer() == turn){
									//piece exists
									//back up and set up didAction flag
									lastMove = (Piece) pieces.get(tempIndex).clone();
									didAction = false;
									selPoint[i] = (Point) curPoint[i].clone();
									infoText[i] = "Selected piece! Entering movement phase.";
									state += 1;
								}
								else infoText[i] = "Invalid selection.";
								break;
							case 1: //move
								if (selPoint[i].equals(curPoint[i])){
									infoText[i] = "Piece not moved. Entering attack phase.";
									state += 1;
								}
								else if (movePieceTo(selPoint[i], curPoint[i])){
									infoText[i] = "Moved piece! Entering attack phase.";
									didAction = true;
									selPoint[i] = (Point) curPoint[i].clone();
									state += 1;
								}
								else infoText[i] = "Invalid move. Try another space.";
								break;
							case 2: //attack
								//return vals: -1 = impossible/nothing to hit, 0 = hit self, 1 = miss, 2 = hit, 3 = critical
								tempState = attackPiece(selPoint[i], curPoint[i]);
								if (tempState != -1){
									//valid move
									if (tempState != 0){
										//didnt hit self
										didAction = true;
										if (tempState != 1){
											//did damage
											infoText[i] = "Attacked target. " + (tempState == 3 ? "Critical hit!" : "");
											//check for death, remove if necessary
											tempIndex = pieceIndex(curPoint[i]);
											if (pieces.get(tempIndex).isDead()){
												pieces.remove(tempIndex);
												infoText[i] += " Killed target!";
											}
										}
										else infoText[i] = "You missed!";
									}
									else if (didAction == false){
										infoText[i] = "You must move and/or attack on your turn.";
										break;
									}
									else infoText[i] = "You didn't attack.";
									
									//end turn
									selPoint[i] = null;
									state = 0;
									if (turn == 1) turn = 2;
									else turn = 1;
									infoText[i] += " Your turn has ended.";
									lastMove = null;
								}
								else infoText[i] = "Invalid attack. Try another space.";
								break;
							}
						}
					}
					//else infoText[i] = "It's not your turn!";
				}
				turnLabel.setText("It is Player " + turn + "'s turn. (Phase: " + stateNames[state] + ")");
				infoLabel.setText("<html>Player 1: " + infoText[0] + "<br>Player 2: " + infoText[1] + "</html>");
				gamePanel.repaint();
			}
		});
		
		
		//set up the top menu (file -> new game, exit. help -> instructions, about)
		JMenuBar menuBar = new JMenuBar();
			JMenu fileMenu = new JMenu("File");
				JMenuItem newItem = new JMenuItem("New Game");
				newItem.addActionListener(new ActionListener (){
					public void actionPerformed(ActionEvent arg0) {
						try {
							newGame();
						} catch (Exception e) {
							System.err.println(e.toString());
							System.exit(0);
						}
					}
				});
				fileMenu.add(newItem);
				
				JMenuItem exitItem = new JMenuItem("Exit");
				exitItem.addActionListener(new ActionListener (){
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				fileMenu.add(exitItem);
			menuBar.add(fileMenu);
			
			JMenu helpMenu = new JMenu ("Help");
				JMenuItem instructionsItem = new JMenuItem("Instructions");
				instructionsItem.addActionListener(new ActionListener (){
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null, "This is basically chess with a twist.\n" +
								"Instead of taking out pieces by moving into their spot, you have to land beside them and defeat them in battle.\n" +
								"Each piece has a certain amount of health, attack power, defensive power, etc.\n" +
								"Only when a pieces' health has been depleated will it be removed from the game board.\n" +
								"Each turn you will get a movement phase and an attack phase.\n" +
								"Use your movement phase to position your piece beside an enemy piece and your attack phase to attack.\n\n" +
								"Some things to remember:\n" +
								"-To not move or not attack, simply select the piece you are working with and click on it again.\n" +
								"-You connot forfit your turn, you must move and/or attack a piece.\n" +
								"-Be careful, most pieces will take more than one hit to go down.\n" +
								"-An attack can be made on a piece in any square next to the attacking piece, including diagonals.\n" +
								"-Not every attack is a guarenteed hit, you will miss sometimes.\n" +
								"-Attacks have a slight chance to do critical damage (1.5x).\n\n" +
								"Controls:\n" +
								"Movement = 'wasd' (player 1) and 'ijkl' (player 2)\n" +
								"Action button = 'q' (player 1) and 'u' (player 2)\n" +
								"Back button = 'e' (player 1) and 'o' (player 2)\n" +
								"The action button selects, moves and attacks pieces\n" +
								"The back button resets the board to the beginning of your turn." +
								"", "Instructions", JOptionPane.PLAIN_MESSAGE);
					}
				});
				helpMenu.add(instructionsItem);
			
				JMenuItem aboutItem = new JMenuItem("About");
				aboutItem.addActionListener(new ActionListener (){
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null, "This game was developed for the QGDC Game in a Week challenge.\nCreated by Carey Metcalfe", "About", JOptionPane.PLAIN_MESSAGE);
					}
				});
				helpMenu.add(aboutItem);
			menuBar.add(helpMenu);
		
		//set up the main game panel
		gamePanel = new GamePanel();
		
		
		//add components to the frame
		add(turnLabel, BorderLayout.NORTH);
		add(infoLabel, BorderLayout.SOUTH);
		add(gamePanel, BorderLayout.CENTER);
		setJMenuBar(menuBar);
		
		pack();
		setVisible(true);
	}
	
	//sets up a new game
	private void newGame() throws Exception{
		
		//set up pieces
		pieces = new ArrayList<Piece> (32);
		pieces.clear();
		for (int i = 0 ; i < 8 ; i++){
			pieces.add(new Piece(Piece.PAWN, new Point(i, 1), 1));
			pieces.add(new Piece(Piece.PAWN, new Point(i, 6), 2));
		}
		for (int i = 0 ; i < 2 ; i++){
			pieces.add(new Piece(Piece.ROOK, new Point (i*7, 0), 1));
			pieces.add(new Piece(Piece.KNIGHT, new Point (i*5 + 1, 0), 1));
			pieces.add(new Piece(Piece.BISHOP, new Point (i*3 + 2, 0), 1));
			pieces.add(new Piece(Piece.ROOK, new Point (i*7, 7), 2));
			pieces.add(new Piece(Piece.KNIGHT, new Point (i*5 + 1, 7), 2));
			pieces.add(new Piece(Piece.BISHOP, new Point (i*3 + 2, 7), 2));
		}
		pieces.add(new Piece(Piece.QUEEN, new Point (3, 0), 1));
		pieces.add(new Piece(Piece.KING, new Point (4, 0), 1));
		pieces.add(new Piece(Piece.QUEEN, new Point (3, 7), 2));
		pieces.add(new Piece(Piece.KING, new Point (4, 7), 2));
		
		turn = 1;
		state = 0;
		
		//init cursors
		curPoint[0] = new Point (4, 0);
		curPoint[1] = new Point (4, 7);
		selPoint[0] = null;
		selPoint[1] = null;
			
		Thread.sleep(100); //wait for the images to be loaded
		
		gameStarted = true;
		gamePanel.repaint();
	}
	
	//move a piece absolute position (true if successful, false if impossible)
	private boolean movePieceTo (Point cur, Point p){
		if (pieceIndex(p) != -1){
			//piece already there
			return false;
		}
		int index = pieceIndex(cur);
		if (index != -1){
			Piece temp = pieces.get(index);
			if (temp.validAbsMove(p)){
				pieces.remove(index); //will screw up temp?
				try {
					temp.setPos(p);
				}catch (Exception e){
					//can't set, re-add the piece and return false
					pieces.add((Piece) temp.clone());
					return false;
				}
				pieces.add((Piece) temp.clone());
				return true;
			}
		}
		return false;
	}
	
	//attack a piece (-1 = impossible/nothing to hit, 0 = hit self, 1 = miss, 2 = hit, 3 = critical)
	private int attackPiece (Point attacker, Point defender){
		int indexA = pieceIndex(attacker);
		int indexD = pieceIndex(defender);
		int retVal = 1; //miss by default
		int damage = 0;
		Random r = new Random();
		
		//both exist
		if (indexA == -1 || indexD == -1) return -1;
		
		//hit itself (forfit attack phase)
		if (indexA == indexD) return 0;
		
		//attacking own piece
		if (pieces.get(indexA).getPlayer() == pieces.get(indexD).getPlayer()) return -1;
		
		//out of range
		if (attacker.distSquared(defender) > 2) return -1;
		
		//valid piece, attack it
		if (r.nextDouble() < .9){
			//hit
			retVal += 1;
			damage = pieces.get(indexA).getAttack();
			if (r.nextDouble() < 0.05){
				//critical
				retVal += 1;
				damage = (int) Math.round(damage * 1.5);
			}
			Piece temp = pieces.get(indexD);
			pieces.remove(indexD);
			temp.doDamage(damage);
			pieces.add((Piece) temp.clone());
		}
		return retVal;
	}

	//returns the index of a piece at p, -1 if it DNE
	private int pieceIndex(Point p){
		for (Piece pd : pieces){
			if (pd.getPos().equals(p)){
				return pieces.indexOf(pd);
			}
		}
		return -1;
	}
	private int pieceIndex (int x, int y){
		return pieceIndex(new Point (x, y));
	}
	
	//returns 0 for game not over, 1 or 2 is the player that lost
	private int gameOver(){
		for (Piece p : pieces){
			if (p.getType() == Piece.KING && p.isDead()){
				return p.getPlayer();
			}
		}
		return 0;
	}
	
	//launches the game
	public static void main(String[] args) {
		CombatChess c = new CombatChess();
		try {
			c.newGame();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(c, e.getMessage(), "ERROR:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//the panel that the game is rendered in
	private class GamePanel extends JPanel{
		
		private final Color BOARD_DARK = new Color (255, 206, 158);
		private final Color BOARD_LIGHT = new Color (209, 139, 71);
		private final Color[] SEL_PIECE = {Color.RED, Color.BLUE};
		private final Color[] CUR_POINT = {Color.RED, Color.BLUE};
		private final int SPACE_SIZE = 50;
		
		BufferedImage board;
		
		public GamePanel(){
			super.setPreferredSize(new Dimension (SPACE_SIZE * 8, SPACE_SIZE * 8));
			
			//set up the board image
			this.board = new BufferedImage (SPACE_SIZE * 8, SPACE_SIZE * 8, BufferedImage.TYPE_INT_RGB);
			Graphics g = this.board.getGraphics();
			for (int x = 0 ; x < 8 ; x++){
				for (int y = 0 ; y < 8 ; y++){
					if (x % 2 == 0){
						if (y % 2 == 0) g.setColor(BOARD_DARK);
						else g.setColor(BOARD_LIGHT);
					}
					else{
						if (y % 2 == 0) g.setColor(BOARD_LIGHT);
						else g.setColor(BOARD_DARK);
					}
					g.fillRect(x * SPACE_SIZE, y * SPACE_SIZE, SPACE_SIZE, SPACE_SIZE);
				}
			}
		}
		
		public void paintComponent(Graphics g) {
			//draw board
			g.drawImage(this.board, 0, 0, null);
			if (gameStarted){
				//draw pieces
				for (Piece p : pieces){
					g.drawImage(p.getImage(), p.getPos().getX() * SPACE_SIZE, (7-p.getPos().getY()) * SPACE_SIZE, null);
				}
				//draw selection boxes
				for (int i = 0 ; i < 2 ; i++){
					if (selPoint[i] != null){
						g.setColor(SEL_PIECE[i]);
						for (int j = 0 ; j < 5 ; j++){
							g.drawRect(selPoint[i].getX() * SPACE_SIZE + j, (7 - selPoint[i].getY()) * SPACE_SIZE + j, SPACE_SIZE - j*2, SPACE_SIZE - j*2);
						}
					}
					if (curPoint[i] != null){
						g.setColor(CUR_POINT[i]);
						for (int j = 0 ; j < 5 ; j++){
							g.drawRect(curPoint[i].getX() * SPACE_SIZE + j, (7 - curPoint[i].getY()) * SPACE_SIZE + j, SPACE_SIZE - j*2, SPACE_SIZE - j*2);
						}
					}
				}
			}
		}
	}
}
