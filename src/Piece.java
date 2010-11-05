import java.awt.Image;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Piece {
	
	public static final int PAWN = 1;
	public static final int ROOK = 2;
	public static final int KNIGHT = 3;
	public static final int BISHOP = 4;
	public static final int QUEEN = 5;
	public static final int KING = 6;
	
	private int type;
	private int player;
	private int maxHealth;
	private int curHealth;
	private int attack;
	private Image image;
	private ArrayList<Point> moves;
	private Point curPos;
	
	public Piece (int type, Point curPos, int player, int curHealth) throws Exception{
		if (type != PAWN && type != ROOK && type != KNIGHT && type != BISHOP && type != QUEEN && type != KING){
			throw new Exception ("Invalid piece type supplied");
		}
		if (player != 1 && player != 2){
			throw new Exception ("Invalid player number supplied");
		}
		if (!validPos(curPos)){
			throw new Exception ("Invalid starting position supplied");
		}
		this.type = type; //saved for cloning
		this.maxHealth = PieceData.maxHealth(type);
		this.curHealth = curHealth;
		this.attack = PieceData.attack(type);
		this.moves = PieceData.movement(type);
		this.player = player;
		this.curPos = (Point) curPos.clone();
		this.image = PieceData.image(type, player);
	}
	
	public Piece (int type, Point curPos, int player) throws Exception{
		//type could be invalid so PieceData.maxHealth(type) would return a bad value
		//It will be caught in the main constructor.
		this (type, curPos, player, PieceData.maxHealth(type));
	}
	
	//accessors
	public int getMaxHealth(){
		return this.maxHealth;
	}
	public int getCurrentHealth(){
		return this.curHealth;
	}
	public double getPercentageHealth(){
		return this.curHealth/this.maxHealth;
	}
	public int getAttack(){
		return this.attack;
	}
	public ArrayList<Point> getMoves(){
		return (ArrayList<Point>) this.moves.clone();
	}
	public Point getPos(){
		return this.curPos;
	}
	public boolean isDead(){
		return this.curHealth <= 0;
	}
	public int getPlayer(){
		return this.player;
	}
	public Image getImage(){
		return image;
	}
	
	
	//valid position on board
	private boolean validPos(Point p){
		return p.getX() < 8  && p.getX() > -1 && p.getY() < 8 && p.getX() > -1;
	}
	//relative move
	public boolean validRelMove(Point p){
		return validAbsMove(this.getPos().add(p));
	}
	//absolute position/move
	public boolean validAbsMove(Point p){
		return this.moves.contains(p.subtract(this.getPos())) && validPos(p);
	}
	
	
	//modifiers
	public void setPos (int x, int y) throws Exception{
		this.setPos(new Point (x, y));
	}
	public void setPos (Point p) throws Exception{
		if (!validPos(p)){
			throw new Exception ("Invalid starting position supplied");
		}
		this.curPos = p;
	}
	public void setHealth (int h){
		this.curHealth = h;
	}
	public void doDamage (int d){
		this.curHealth = this.curHealth - d;
	}
	public void doHeal (int h){
		this.curHealth = this.curHealth + h;
	}
	
	public Object clone(){
		try {
			return new Piece (this.type, this.curPos, this.player, this.curHealth);
		} catch (Exception e) {
			//will never happen
			return null;
		}
	}
}