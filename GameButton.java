import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

public class GameButton extends Button {
		
	private int player;
	
	private int x;
	
	private int y;
	
	private GameButton prevMove = null;
	
	GameButton(int x, int y) {
		super();		
		this.x = x;
		this.y = y;
		setShape(new Circle(20));
		setMinSize(50, 50);
	}
	
	public void setCoors(int x, int y) {
		this.x = x;
		this.y =y;
	}
	
	public void setPrevMove(GameButton prev) {
		prevMove = prev;
	}
	
	public GameButton getprevMove() {
		return prevMove;
	}
	
	public void setPlayer(int p) {
		player = p;
	}
	
	public int getPlayer() {
		return player;
	}
	
	public int getx() {
		return x;
	}
	
	public int gety() {
		return y;
	}
}
