import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;


public class PieceData {

	//local arrays
	private static Image[][] pieceImages = null; 
	private static String[] names = {"Pawn", "Rook", "Knight", "Bishop", "Queen", "King"};
	private static int[] attack = {30, 50, 50, 40, 80, 40};
	private static int[] maxHealth = {70, 500, 150, 200, 500, 1500};
	
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

	//loads the images into memory
	public static void initImages(){
		pieceImages = new Image[2][6];
		String[][] filenames = {{"pawn_w.png", "rook_w.png", "knight_w.png", "bishop_w.png", "queen_w.png", "king_w.png"}, {"pawn_b.png", "rook_b.png", "knight_b.png", "bishop_b.png", "queen_b.png", "king_b.png"}};
		for (int i = 0 ; i < 2 ; i++){
			for (int j = 0 ; j < 6 ; j++){
				try{
					Toolkit tk = Toolkit.getDefaultToolkit();
					URL url = PieceData.class.getResource("rsc/" + filenames[i][j]);
					Image img = tk.createImage(url);
					tk.prepareImage(img, -1, -1, null);
					pieceImages[i][j] = img;
				}
				catch (Exception e) {
					//not found/other error, generate stand-in
					pieceImages[i][j] = (Image) new BufferedImage (50, 50, BufferedImage.TYPE_INT_RGB);
				}
			}
		}
	}
	
	//returns the images for each piece
	public static Image image(int type, int player){
		if (pieceImages == null) initImages();
		try{
			return pieceImages[player - 1][type - 1];
		}
		catch (Exception e){
			return (Image) new BufferedImage (50, 50, BufferedImage.TYPE_INT_RGB);
		}
	}

	//returns the max health of the piece
	public static int maxHealth (int type){
		try{
			return maxHealth[type - 1];
		}
		catch (Exception e){
			return -1;
		}
	}

	//returns the attack of the piece
	public static int attack (int type){
		try{
			return attack[type -1];
		}
		catch (Exception e){
			return -1;
		}
	}

	//returns a string representing the type
	public static String typeToString (int type){
		try{
			return names[type -1];
		}
		catch (Exception e){
			return "N/A";
		}
	}
}