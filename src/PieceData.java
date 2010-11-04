import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;


public class PieceData {
	
	//returns the valid relative moves for each piece
	public static ArrayList<Point> pawnMovement(){
		ArrayList<Point> temp = new ArrayList<Point> (1);
		temp.add(new Point(0, 1));
		return temp;
	}
	
	public static ArrayList<Point> rookMovement(){
		ArrayList<Point> temp = new ArrayList<Point> (24);
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
		return temp;
	}
	
	public static ArrayList<Point> knightMovement(){
		ArrayList<Point> temp = new ArrayList<Point> (8);
		temp.add(new Point(-2, 1));
		temp.add(new Point(-2, -1));
		temp.add(new Point(-1, 2));
		temp.add(new Point(-1, -2));
		temp.add(new Point(1, 2));
		temp.add(new Point(1, -2));
		temp.add(new Point(2, 1));
		temp.add(new Point(3, -1));
		return temp;
	}
	
	public static ArrayList<Point> bishopMovement(){
		ArrayList<Point> temp = new ArrayList<Point> (24);
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
		return temp;
	}
	
	public static ArrayList<Point> queenMovement(){
		ArrayList<Point> temp = new ArrayList<Point> (48);
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
		return temp;
	}
	
	public static ArrayList<Point> kingMovement(){
		ArrayList<Point> temp = new ArrayList<Point> (8);
		temp.add(new Point(-1, -1));
		temp.add(new Point(-1, 0));
		temp.add(new Point(-1, 1));
		temp.add(new Point(0, -1));
		temp.add(new Point(0, 1));
		temp.add(new Point(1, -1));
		temp.add(new Point(1, 0));
		temp.add(new Point(1, 1));
		return temp;
	}
	
	//returns the images for each piece
	public static Image[] pawnImage(){
		return new Image[] {getImage ("rsc/pawn_w.png"), getImage ("rsc/pawn_b.png")};
	}
	
	public static Image[] rookImage(){
		return new Image[] {getImage ("rsc/rook_w.png"), getImage ("rsc/rook_b.png")};
	}
	
	public static Image[] knightImage(){
		return new Image[] {getImage ("rsc/knight_w.png"), getImage ("rsc/knight_b.png")};
	}
	
	public static Image[] bishopImage(){
		return new Image[] {getImage ("rsc/bishop_w.png"), getImage ("rsc/bishop_b.png")};
	}
	
	public static Image[] queenImage(){
		return new Image[] {getImage ("rsc/queen_w.png"), getImage ("rsc/queen_b.png")};
	}
	
	public static Image[] kingImage(){
		return new Image[] {getImage ("rsc/king_w.png"), getImage ("rsc/king_b.png")};
	}
	
	//loads the image from the file
	private static Image getImage(String path){
		try{
			Toolkit tk = Toolkit.getDefaultToolkit();
			URL url = PieceData.class.getResource(path);
			Image img = tk.createImage(url);
			tk.prepareImage(img, -1, -1, null);
			return img;
		}
		catch (Exception e) {
			//not found, generate replacement
			return (Image) new BufferedImage (20, 29, BufferedImage.TYPE_INT_RGB);
		}
	}
}
