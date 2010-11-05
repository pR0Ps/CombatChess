import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private int state; //move or attack
	private ArrayList<Piece> pieces; //pieces on the board
	private GamePanel gamePanel;
	private JLabel turnLabel;
	private JLabel infoLabel;
	
	public CombatChess (){
		super ("Combat Chess");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setLayout (new BorderLayout());
		super.setResizable(false);
		
		//attempt to adapt to the operating system's look
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			//shit son, you get the crappy Java UI ;)
		}
		
		//TODO: set up game mechanics
		
		//set up the labels
		turnLabel = new JLabel ("Turn display");
		infoLabel = new JLabel ("Info display");
		
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
								"Use your movement phase to position your piece beside an enemy piece and your attack phase to attack.\n" +
								"Some things to remember:\n\n" +
								"-Be careful, most pieces will take more than one hit to go down.\n" +
								"-An attack can come from any square next to a piece, including diagonals.\n" +
								"-Not every attack is a guarenteed hit, you will miss sometimes.\n" +
								"-Attacks have a slight chance to do critical damage (1.5x)." +
								"", "Instructions", JOptionPane.PLAIN_MESSAGE);
					}
				});
				helpMenu.add(instructionsItem);
			
				JMenuItem aboutItem = new JMenuItem("About");
				aboutItem.addActionListener(new ActionListener (){
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null, "This game was developed for the QGDC Game in a Week challenge", "About", JOptionPane.PLAIN_MESSAGE);
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
		
		Thread.sleep(100); //wait for the images to be loaded
		gamePanel.updateBoard(0, 0);
	}
	
	//move a piece absolute position (true if successful, false if impossible)
	private boolean movePieceTo (Point cur, Point p) throws Exception{
		if (pieceIndex(p) == -1){
			//piece already there
			return false;
		}
		int index = pieceIndex(cur);
		if (index != -1){
			Piece temp = pieces.get(index);
			if (temp.validAbsMove(p)){
				pieces.remove(index); //will screw up temp?
				temp.setPos(p);
				pieces.add((Piece) temp.clone());
				return true;
			}
		}
		return false;
	}
	
	//attack a piece (true if successful (even with miss), false if impossible)
	private boolean attackPiece (Point attacker, Point defender){
		int indexA = pieceIndex(attacker);
		int indexD = pieceIndex(defender);
		//both exist
		if (!(indexA != -1 || indexD != -1)){
			//in range
			if (attacker.distSquared(defender) <= 2){
				Random r = new Random();
				int damage = 0;
				if (r.nextDouble() < .9){
					//hit
					damage = pieces.get(indexA).getAttack();
					if (r.nextDouble() < 0.05){
						//critical
						damage = (int) Math.round(damage * 1.5);
					}
				}
				Piece temp = pieces.get(indexD);
				pieces.remove(indexD);
				temp.doDamage(damage);
				pieces.add((Piece) temp.clone());
				return true;
			}
		}
		return false;
	}
	
	//select a piece and update information
	private void selectPiece(Point p){
		
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
			if (p.toString().equals("KING") && p.isDead()){
				return p.getPlayer();
			}
		}
		return 0;
	}
	
	//launches the game
	public static void main(String[] args) {
		new CombatChess();
	}
	
	//the panel that the game is rendered in
	private class GamePanel extends JPanel{
		
		private final Color BOARD_DARK = new Color (255, 206, 158);
		private final Color BOARD_LIGHT = new Color (209, 139, 71);
		private final int SPACE_SIZE = 50;
		
		private boolean drawPieces;
		BufferedImage board;
		int selX;
		int selY;
		
		//TODO: Add action listeners for highlighting selected square and clicking
		
		public GamePanel(){
			super.setPreferredSize(new Dimension (SPACE_SIZE * 8, SPACE_SIZE * 8));
			this.drawPieces = false; //don't draw anything until a new game starts
			
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
			
			addMouseListener(new MouseListener (){
				public void mouseClicked(MouseEvent arg0) {}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent arg0) {}
				
			});
			addMouseMotionListener(new MouseMotionListener() {
				public void mouseMoved(MouseEvent e) {}
				public void mouseDragged(MouseEvent e) {}
			});
		}
		
		public void updateBoard(int selX, int selY){
			this.drawPieces = true;
			this.selX = selX;
			this.selY = selY;
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			g.drawImage(this.board, 0, 0, null);
			if (this.drawPieces){
				for (Piece p : pieces){
					g.drawImage(p.getImage(), (7-p.getPos().getX()) * SPACE_SIZE, (7-p.getPos().getY()) * SPACE_SIZE, null);
				}
			}
		}
	}
}
