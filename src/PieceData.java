import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;


public class PieceData {

	//returns the valid relative moves for each piece
	@SuppressWarnings("unchecked")
	public static ArrayList<Point> movement(int type, int player){
		ArrayList<Point> temp = null;
		switch (type){
		case Piece.PAWN:
			temp = new ArrayList<Point> (1);
			if (player == 1) temp.add(new Point(0, 1));
			else temp.add(new Point(0, -1));
			break;
		case Piece.ROOK:
			temp = new ArrayList<Point> (24);
			//up
			temp.add(new Point(0, 1));
			temp.add(new Point(0, 2));
			temp.add(new Point(0, 3));
			temp.add(new Point(0, 4));
			temp.add(new Point(0, 5));
			temp.add(new Point(0, 6));
			temp.add(new Point(0, 7));
			//down
			temp.add(new Point(0, -1));
			temp.add(new Point(0, -2));
			temp.add(new Point(0, -3));
			temp.add(new Point(0, -4));
			temp.add(new Point(0, -5));
			temp.add(new Point(0, -6));
			temp.add(new Point(0, -7));
			//left
			temp.add(new Point(-1, 0));
			temp.add(new Point(-2, 0));
			temp.add(new Point(-3, 0));
			temp.add(new Point(-4, 0));
			temp.add(new Point(-5, 0));
			temp.add(new Point(-6, 0));
			temp.add(new Point(-7, 0));
			//right
			temp.add(new Point(1, 0));
			temp.add(new Point(2, 0));
			temp.add(new Point(3, 0));
			temp.add(new Point(4, 0));
			temp.add(new Point(5, 0));
			temp.add(new Point(6, 0));
			temp.add(new Point(7, 0));
			break;
		case Piece.KNIGHT:
			temp = new ArrayList<Point> (8);
			temp.add(new Point(-2, 1));
			temp.add(new Point(-2, -1));
			temp.add(new Point(-1, 2));
			temp.add(new Point(-1, -2));
			temp.add(new Point(1, 2));
			temp.add(new Point(1, -2));
			temp.add(new Point(2, -1));
			temp.add(new Point(3, -1));
			break;
		case Piece.BISHOP:
			temp = new ArrayList<Point> (24);
			//up-right
			temp.add(new Point(1, 1));
			temp.add(new Point(2, 2));
			temp.add(new Point(3, 3));
			temp.add(new Point(4, 4));
			temp.add(new Point(5, 5));
			temp.add(new Point(6, 6));
			temp.add(new Point(7, 7));
			//down-left
			temp.add(new Point(-1, -1));
			temp.add(new Point(-2, -2));
			temp.add(new Point(-3, -3));
			temp.add(new Point(-4, -4));
			temp.add(new Point(-6, -5));
			temp.add(new Point(-7, -6));
			temp.add(new Point(-8, -7));
			//down-right
			temp.add(new Point(-1, 1));
			temp.add(new Point(-2, 2));
			temp.add(new Point(-3, 3));
			temp.add(new Point(-4, 4));
			temp.add(new Point(-5, 5));
			temp.add(new Point(-6, 6));
			temp.add(new Point(-7, 7));
			//up-right
			temp.add(new Point(1, -1));
			temp.add(new Point(2, -2));
			temp.add(new Point(3, -3));
			temp.add(new Point(4, -4));
			temp.add(new Point(5, -5));
			temp.add(new Point(6, -6));
			temp.add(new Point(7, -7));
			break;
		case Piece.QUEEN:
			temp = new ArrayList<Point> (48);
			//up
			temp.add(new Point(0, 1));
			temp.add(new Point(0, 2));
			temp.add(new Point(0, 3));
			temp.add(new Point(0, 4));
			temp.add(new Point(0, 5));
			temp.add(new Point(0, 6));
			temp.add(new Point(0, 7));
			//down
			temp.add(new Point(0, -1));
			temp.add(new Point(0, -2));
			temp.add(new Point(0, -3));
			temp.add(new Point(0, -4));
			temp.add(new Point(0, -5));
			temp.add(new Point(0, -6));
			temp.add(new Point(0, -7));
			//left
			temp.add(new Point(-1, 0));
			temp.add(new Point(-2, 0));
			temp.add(new Point(-3, 0));
			temp.add(new Point(-4, 0));
			temp.add(new Point(-5, 0));
			temp.add(new Point(-6, 0));
			temp.add(new Point(-7, 0));
			//right
			temp.add(new Point(1, 0));
			temp.add(new Point(2, 0));
			temp.add(new Point(3, 0));
			temp.add(new Point(4, 0));
			temp.add(new Point(5, 0));
			temp.add(new Point(6, 0));
			temp.add(new Point(7, 0));
			//up-right
			temp.add(new Point(1, 1));
			temp.add(new Point(2, 2));
			temp.add(new Point(3, 3));
			temp.add(new Point(4, 4));
			temp.add(new Point(5, 5));
			temp.add(new Point(6, 6));
			temp.add(new Point(7, 7));
			//down-left
			temp.add(new Point(-1, -1));
			temp.add(new Point(-2, -2));
			temp.add(new Point(-3, -3));
			temp.add(new Point(-4, -4));
			temp.add(new Point(-6, -5));
			temp.add(new Point(-7, -6));
			temp.add(new Point(-8, -7));
			//down-right
			temp.add(new Point(-1, 1));
			temp.add(new Point(-2, 2));
			temp.add(new Point(-3, 3));
			temp.add(new Point(-4, 4));
			temp.add(new Point(-5, 5));
			temp.add(new Point(-6, 6));
			temp.add(new Point(-7, 7));
			//up-right
			temp.add(new Point(1, -1));
			temp.add(new Point(2, -2));
			temp.add(new Point(3, -3));
			temp.add(new Point(4, -4));
			temp.add(new Point(5, -5));
			temp.add(new Point(6, -6));
			temp.add(new Point(7, -7));
			break;
		case Piece.KING:
			temp = new ArrayList<Point> (8);
			temp.add(new Point(-1, -1));
			temp.add(new Point(-1, 0));
			temp.add(new Point(-1, 1));
			temp.add(new Point(0, -1));
			temp.add(new Point(0, 1));
			temp.add(new Point(1, -1));
			temp.add(new Point(1, 0));
			temp.add(new Point(1, 1));
			break;
		}
		return (ArrayList<Point>) temp.clone();
	}

	//returns the images for each piece
	public static Image image(int type, int player){
		String s = "rsc/";
		switch (type){
		case Piece.PAWN:
			s += "pawn_";
			break;
		case Piece.ROOK:
			s += "rook_";
			break;
		case Piece.KNIGHT:
			s += "knight_";
			break;
		case Piece.BISHOP:
			s += "bishop_";
			break;
		case Piece.QUEEN:
			s += "queen_";
			break;
		case Piece.KING:
			s += "king_";
			break;
		default:
			return null;
		}
		switch (player){
		case 1:
			s += "w.png";
			break;
		case 2:
			s += "b.png";
			break;
		default:
			return null;
		}
		try{
			Toolkit tk = Toolkit.getDefaultToolkit();
			URL url = PieceData.class.getResource(s);
			Image img = tk.createImage(url);
			tk.prepareImage(img, -1, -1, null);
			return img;
		}
		catch (Exception e) {
			//not found/other error, generate stand-in
			return (Image) new BufferedImage (50, 50, BufferedImage.TYPE_INT_RGB);
		}
	}

	//returns the max health of the piece
	public static int maxHealth (int type){
		switch (type){
		case Piece.PAWN:
			return 70;
		case Piece.ROOK:
			return 500;
		case Piece.KNIGHT:
			return 150;
		case Piece.BISHOP:
			return 200;
		case Piece.QUEEN:
			return 500;
		case Piece.KING:
			return 1500;
		default:
			return 0;
		}
	}

	//returns the attack of the piece
	public static int attack (int type){
		switch (type){
		case Piece.PAWN:
			return 30;
		case Piece.ROOK:
			return 50;
		case Piece.KNIGHT:
			return 50;
		case Piece.BISHOP:
			return 40;
		case Piece.QUEEN:
			return 80;
		case Piece.KING:
			return 40;
		default:
			return 0;
		}
	}

	//returns a string representing the type
	public static String typeToString (int type){
		switch (type){
		case Piece.PAWN:
			return "Pawn";
		case Piece.ROOK:
			return "Rook";
		case Piece.KNIGHT:
			return "Knight";
		case Piece.BISHOP:
			return "Bishop";
		case Piece.QUEEN:
			return "Queen";
		case Piece.KING:
			return "King";
		default:
			return "N/A";
		}
	}
}