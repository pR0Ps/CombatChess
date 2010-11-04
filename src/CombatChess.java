import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class CombatChess extends JFrame {

	private int turn; //player turn
	private ArrayList<PieceData> player1;
	private ArrayList<PieceData> player2;
	
	public CombatChess (){
		super ("Combat Chess");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension (500, 500));
		
		//set up GUI
		pack();
		setVisible(true);
	}
	
	//sets up a new game
	private void newGame(){
		
		//set up pieces
		player1 = new ArrayList<PieceData> (16);
		player2 = new ArrayList<PieceData> (16);
		for (int i = 0 ; i < 8 ; i++){
			player1.add(initPiece(PieceData.PAWN, i, 1));
			player2.add(initPiece(PieceData.PAWN, i, 7));
		}
		for (int i = 0 ; i < 2 ; i++){
			player1.add(initPiece(PieceData.ROOK, i*7, 0));
			player1.add(initPiece(PieceData.KNIGHT, i*5 + 1, 0));
			player1.add(initPiece(PieceData.BISHOP, i*3 + 2, 0));
			player2.add(initPiece(PieceData.ROOK, i*7, 7));
			player2.add(initPiece(PieceData.KNIGHT, i*5 + 1, 7));
			player2.add(initPiece(PieceData.BISHOP, i*3 + 2, 7));
		}
		player1.add(initPiece(PieceData.QUEEN, 3, 0));
		player1.add(initPiece(PieceData.KING, 4, 0));
		player2.add(initPiece(PieceData.QUEEN, 3, 7));
		player2.add(initPiece(PieceData.KING, 4, 7));
		
		turn = 1;
	}
	
	//sets up a chess piece
	private PieceData initPiece(PieceData type, int x, int y){
		PieceData p = type;
		p.setPos(x, y);
		return p;
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
	private boolean kingDead (ArrayList<PieceData> player){
		for (PieceData p : player){
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

}
