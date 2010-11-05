import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


@SuppressWarnings({"serial", "unused"})
public class CombatChess extends JFrame {

	private int turn; //player turn
	private ArrayList<Piece> pieces;
	
	public CombatChess (){
		super ("Combat Chess");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension (500, 500));
		super.setLayout (new BorderLayout());
		super.setResizable(false);
		
		//TODO: set up GUI (menu bar, main panel, 2 panels (turn display on top, state/info display on bottom)
		//TODO: set up game mechanics
		
		//set up the top menu (file -> new game, exit. help -> instructions, help)
		JMenuBar menuBar = new JMenuBar();
			JMenu fileMenu = new JMenu("File");
			JMenu helpMenu = new JMenu ("Help");
		
		//set up the main game panel
		GamePanel gamePanel = new GamePanel();
		
		
		//add components to the frame
		
		
		pack();
		setVisible(true);
	}
	
	//sets up a new game
	private void newGame() throws Exception{
		
		//set up pieces
		pieces = new ArrayList<Piece> (32);
		for (int i = 0 ; i < 8 ; i++){
			pieces.add(new Piece(Piece.PAWN, new Point(i, 1), 1));
			pieces.add(new Piece(Piece.PAWN, new Point(i, 7), 2));
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
		
		BufferedImage board;
		int selX;
		int selY;
		
		//TODO: Add action listeners for highlighting selected square and clicking
		
		public GamePanel(){
			super.setPreferredSize(new Dimension (SPACE_SIZE * 8, SPACE_SIZE * 8));
			
			//set up the board image
			board = new BufferedImage (SPACE_SIZE * 8, SPACE_SIZE * 8, BufferedImage.TYPE_INT_RGB);
			Graphics g = board.getGraphics();
			for (int x = 0 ; x < 8 ; x++){
				for (int y = 0 ; y < 8 ; y++){
					//test this, not sure
					if (x % 2 == 0 && y % 2 == (x % 2)){
						g.setColor(BOARD_DARK);
					}
					else{
						g.setColor(BOARD_LIGHT);
					}
					g.drawRect(x * SPACE_SIZE, y * SPACE_SIZE, SPACE_SIZE, SPACE_SIZE);
				}
			}
		}
		
		public void updateBoard(int selX, int selY){
			this.selX = selX;
			this.selY = selY;
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			g.drawImage(board, 0, 0, null);
			for (Piece p : pieces){
				g.drawImage(p.getImage(), (8 - p.getPos().getX()) * SPACE_SIZE, (8 - p.getPos().getY()) * SPACE_SIZE, null);
			}
		}

	}

}
