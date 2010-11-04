import java.util.ArrayList;


public enum PieceData {
	PAWN (70, 30, Movement.pawn()),
	ROOK (500, 50, Movement.rook()),
	KNIGHT (150, 50, Movement.knight()),
	BISHOP (200, 40, Movement.bishop()),
	QUEEN (500, 80, Movement.queen()),
	KING (1500, 40, Movement.king());
	
	private int maxHealth;
	private int curHealth;
	private int attack;
	private ArrayList<Point> moves;
	private Point curPos = null;
	
	private PieceData (int health, int attack, ArrayList<Point> moves){
		this.maxHealth = health;
		this.curHealth = health;
		this.attack = attack;
		this.moves = moves;
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
		return this.moves;
	}
	public Point getPos(){
		return this.curPos;
	}
	public boolean isDead(){
		return this.curHealth >= 0;
	}
	//relative move
	public boolean validRelMove(Point p){
		return getMoves().contains(p);
	}
	//absolute position move
	public boolean validAbsMove(Point p){
		return validRelMove(p.subtract(this.getPos()));
	}
	
	//modifiers
	public void setPos (int x, int y){
		this.curPos = new Point (x, y);
	}
	public void setPos (Point p){
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
}