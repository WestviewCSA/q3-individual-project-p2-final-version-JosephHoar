
public class Cords {
	private int x;
	private int y;
	private int z;
	private String symbol;
	private Cords prev;
	
	public Cords(int xa, int ya, int za, String sym) {
		x = xa;
		y = ya;
		z = za;
		symbol = sym;
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

}
