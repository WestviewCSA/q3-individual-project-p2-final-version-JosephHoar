
public class Cords {
	private int x;
	private int y;
	private int z;
	private String symbol;
	private Cords prev;
	private int cost;
	
	public Cords(int xa, int ya, int za, String sym) {
		x = xa;
		y = ya;
		z = za;
		symbol = sym;
		prev = null;
		cost = 0;
	}
	public int getRow() {
		return x;
	}
	public int getCol() {
		return y;
	}
	public int getLayer() {
		return z;
	}
	public String getSym() {
		return symbol;
	}
	public Cords getPrev() {
		return prev;
	}
	public void setPrev(Cords pre) {
		prev = pre;
	}
	public void setSym(String symb) {
		symbol = symb;
	}
	public void heuristic(Cords goal) {
		int ca = Math.abs(goal.getCol() - getCol());
		int cb = Math.abs(goal.getRow() - getRow());
		cost = ca + cb;
	}
	public int getCost() {
		return cost;
	}

}
