import java.util.Comparator;
public class MyComparator implements Comparator<Cords> {
	public int compare(Cords a, Cords b) {
		// TODO Auto-generated method stub
		if(a.getCost() == b.getCost()) {
			return a.getH() - b.getH();
		}
		return a.getCost() - b.getCost();
	}

}
