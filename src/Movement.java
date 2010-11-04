import java.util.ArrayList;


public class Movement {

	public static ArrayList<Point> pawn(){
		ArrayList<Point> temp = new ArrayList<Point> (1);
		temp.add(new Point(0, 1));
		return temp;
	}
	
	public static ArrayList<Point> rook(){
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
	
	public static ArrayList<Point> knight(){
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
	
	public static ArrayList<Point> bishop(){
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
	
	public static ArrayList<Point> queen(){
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
	
	public static ArrayList<Point> king(){
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
}
