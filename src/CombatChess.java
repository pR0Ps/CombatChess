import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class CombatChess extends JFrame {

	private int turn; //player turn
	private ArrayList<Piece> player1;
	private ArrayList<Piece> player2;
	
	public CombatChess (){
		super ("Combat Chess");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension (500, 500));
		super.setLayout (new BorderLayout());
		super.setResizable(false);
		
		
		//TODO: set up GUI (menu bar, main panel, 2 panels along the bottom (turn display, state display)
		
		pack();
		setVisible(true);
	}
	
	//sets up a new game
	private void newGame(){
		
		//set up pieces
		player1 = new ArrayList<Piece> (16);
		player2 = new ArrayList<Piece> (16);
		for (int i = 0 ; i < 8 ; i++){
			player1.add(initPiece(Piece.PAWN, 1, i, 1));
			player2.add(initPiece(Piece.PAWN, 2, i, 7));
		}
		for (int i = 0 ; i < 2 ; i++){
			player1.add(initPiece(Piece.ROOK, 1, i*7, 0));
			player1.add(initPiece(Piece.KNIGHT, 1, i*5 + 1, 0));
			player1.add(initPiece(Piece.BISHOP, 1, i*3 + 2, 0));
			player2.add(initPiece(Piece.ROOK, 2, i*7, 7));
			player2.add(initPiece(Piece.KNIGHT, 2, i*5 + 1, 7));
			player2.add(initPiece(Piece.BISHOP, 2, i*3 + 2, 7));
		}
		player1.add(initPiece(Piece.QUEEN, 1, 3, 0));
		player1.add(initPiece(Piece.KING, 1, 4, 0));
		player2.add(initPiece(Piece.QUEEN, 2, 3, 7));
		player2.add(initPiece(Piece.KING, 2, 4, 7));
		
		turn = 1;
	}
	
	//sets up a chess piece
	private Piece initPiece(Piece type, int player, int x, int y){
		Piece p = type;
		p.setPos(x, y);
		return p;
	}
	
	
	//returns the selected piece
	private Piece selectedPiece(Point p, ArrayList<Piece> player){
		for (Piece pd : player){
			if (pd.getPos().equals(p)){
				return pd;
			}
		}
		return null;
	}
	private Piece selectedPiece (int x, int y, ArrayList<Piece> player){
		return selectedPiece(new Point (x, y), player);
	}
	
	//returns 0 for game not over, 1 or 2 is the player that won
	private int gameOver(){
		if (kingDead(player1)){
			return 2;
		}
		else if (kingDead(player2)){
			return 1;
		}
		return 0;
	}
	
	//checks if the king of a player is dead
	private boolean kingDead (ArrayList<Piece> player){
		for (Piece p : player){
			if (p.toString().equals("KING") && p.isDead()){
				return true;
			}
		}
		return false;
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
		ArrayList<Piece> pieces;
		
		//TODO: Add action listener for highlighting selected square and clicking
		public GamePanel(){
			super.setPreferredSize(new Dimension (SPACE_SIZE*8, SPACE_SIZE*8)); //50px per square
			board = new BufferedImage (SPACE_SIZE*8, SPACE_SIZE*8, BufferedImage.TYPE_INT_RGB);
		}
		
		public void updateBoard(ArrayList<Piece> player1, ArrayList<Piece> player2){
			this.pieces.clear();
			this.pieces.addAll(player1);
			this.pieces.addAll(player2);
			repaint();
		}
		
		public void paintComponent(Graphics g) {
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
			for (Piece p : pieces){
				g.drawImage(p.getImage(), (8 - p.getPos().getX()) * SPACE_SIZE, (8 - p.getPos().getY()) * SPACE_SIZE, null);
			}
		}

	}

}
