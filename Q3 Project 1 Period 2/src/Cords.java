
public class Cords {
	private int x;
	private int y;
	private int z;
	private String symbol;
	private Cords prev;
	private int h;
	private int g;
	
	public Cords(int xa, int ya, int za, String sym) {
		x = xa;
		y = ya;
		z = za;
		symbol = sym;
		prev = null;
		h = 0;
		g = 0;
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
	public void calcH(Cords goal) {
		int ca = Math.abs(goal.getCol() - getCol());
		int cb = Math.abs(goal.getRow() - getRow());
		h = ca + cb;
		if(goal.getLayer() != getLayer()) {
			h = 10000000;
		}
	}
	public int getH() {
		return h;
	}
	public void calcG(Cords Start) {
		int ca = Math.abs(Start.getCol() - getCol());
		int cb = Math.abs(Start.getRow() - getRow());
		g = ca + cb;
	}
	public int getCost() {
		return h + g;
	}

}
