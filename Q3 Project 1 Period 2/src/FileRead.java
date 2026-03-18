import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Comparator;
public class FileRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length > 0) {
			File mapa = new File(args[0]);
			Cords[][][] coord = file(mapa);
			if(coord != null) {
				ArrayList<Cords> visited = AStar((coord));
			}
		}else {
			System.out.println("File Not Found");
		}
		

	}
	public static Cords[][][] file(File map){
		try {
			Scanner scan = new Scanner(map);
			int h = scan.nextInt();
			int w = scan.nextInt();
			int l = scan.nextInt();
			Cords[][][] coords = new Cords[h][w][l];
			for(int i = 0; i < l; i++) {
				for(int j = 0; j < h; j++) {
					String a = "";
					for(int k = 0; k < w; k++) {
						Cords loc = new Cords(j,k,i,scan.next());
						coords[j][k][i] = loc;
						a += loc.getSym() + " ";
					}
					System.out.println(a);
				}
				System.out.println(" ");
			}
			return coords;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		}
	public static ArrayList<String> cords(String[][][] a) {
		ArrayList<String> b = new ArrayList<String>();
		for(int i = 0; i < a[0][0].length; i++) {
			for(int j = 0; j < a.length; j++) {
				for(int k = 0; k < a[0].length; k++) {
					String c = a[j][k][i] + " " + j + " " + k + " " + i;
					b.add(c);
					//System.out.println(c);
				}
			}
		}
		return b;
		
	}
	public static String[][][] co(String [][][] map){
		String[][][] res = new String[map.length][map[0].length][map[0][0].length];
		for(int i = 0; i < map[0][0].length; i++) {
			for(int j = 0; j < map.length; j++) {
				for(int k = 0; k < map[0].length; k++) {
					String s = map[j][k][i] + " " + j + " " + k + " " + i;
					res[j][k][i] = s;
						//System.out.println(res[j][k][i]);
				}
			}
		}
		return res;
	
	}
	public static Cords checkup(Cords[][][] map, int a, int b, int c) {
		return map[a-1][b][c];
	}
	public static Cords checkdown(Cords[][][] map, int a, int b, int c) {
		return map[a+1][b][c];
	}
	public static Cords checkright(Cords[][][] map, int a, int b, int c) {
		return map[a][b+1][c];
	}
	public static Cords checkleft(Cords[][][] map, int a, int b, int c) {
		return map[a][b-1][c];
	}
	public static Cords findStart(Cords[][][] map, int a, int b, int c) {
		for(int j = 0; j < map.length; j++) {
			for(int k = 0; k < map[0].length; k++) {
				if(map[j][k][c].getSym().equals("W")) {
					a = j;
					b = k;
				}
			}
		}
		return map[a][b][c];
	}
	public static Cords findGoal(Cords[][][] map, int l) {
		for(int j = 0; j < map.length; j++) {
			for(int k = 0; k < map[0].length; k++) {
				if(map[j][k][l].getSym().equals("$") || map[j][k][l].getSym().equals("|")) {
					return map[j][k][l];
				}
			}
		}
		return null;
	}
	public static ArrayList<Cords> queueRoute(Cords[][][] map){
		ArrayList<Cords> visited = new ArrayList<>();
		LinkedList<Cords> queue = new LinkedList<>();
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		boolean start = false;
		boolean found = false;
		while(found == false) {
			if(!start) {
				queue.add(findStart(map, a, b, c));
				start = true;
			}
			if(!queue.isEmpty()) {
				visited.add(queue.remove());
				Cords e = visited.get(d);
				a = e.getRow();
				b = e.getCol();
				c = e.getLayer();
				String s = e.getSym() + " " + a + " " + b + " " + c;
				System.out.println(s);
				d++;
				if(e.getSym().equals("|")) {
					start = false;
					c++;
					continue;
				}
			}
			if(a-1>= 0 && a < map.length) {
				Cords co = checkup(map,a,b,c);
				co.setPrev(map[a][b][c]);
				String s = co.getSym();
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!queue.contains(co) && !visited.contains(co)) {
						queue.add(co);
					}
				}
			}
			if(a + 1 < map.length && a < map.length) {
				Cords co = checkdown(map,a,b,c);
				co.setPrev(map[a][b][c]);
				String s = co.getSym();
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!queue.contains(co) && !visited.contains(co)) {
						queue.add(co);
					}
				}
			}
			if(b + 1 < map[0].length && b < map[0].length) {
				Cords co = checkright(map,a,b,c);
				co.setPrev(map[a][b][c]);
				String s = co.getSym();
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!queue.contains(co) && !visited.contains(co)) {
						queue.add(co);
					}
				}
			}
			if(b-1 >= 0 && b < map[0].length) {
				Cords co = checkleft(map,a,b,c);
				co.setPrev(map[a][b][c]);
				String s = co.getSym();
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!queue.contains(co) && !visited.contains(co)) {
						queue.add(co);
					}
				}
			}
			
		}
		System.out.println(visited);
		return visited;
	}
	public static ArrayList<Cords> stackRoute(Cords[][][] map){
		Stack<Cords> stack = new Stack<Cords>();
		ArrayList<Cords> visited = new ArrayList<Cords>();
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		boolean start = false;
		boolean found = false;
		while(found == false) {
			if(!start) {
				stack.push(findStart(map, a, b, c));
				start = true;
			}
			if(!stack.isEmpty()) {
				visited.add(stack.pop());
				Cords e = visited.get(d);
				a = e.getRow();
				b = e.getCol();
				c = e.getLayer();
				String s = e.getSym() + " " + a + " " + b + " " + c;
				System.out.println(s);
				d++;
				if(e.getSym().equals("|")) {
					start = false;
					c++;
					continue;
				}
			}
			if(a-1>= 0 && a < map.length) {
				Cords co = checkup(map,a,b,c);
				co.setPrev(map[a][b][c]);
				String s = co.getSym();
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!stack.contains(co) && !visited.contains(co)) {
						stack.push(co);
					}
				}
			}
			if(a + 1 < map.length && a < map.length) {
				Cords co = checkdown(map,a,b,c);
				co.setPrev(map[a][b][c]);
				String s = co.getSym();
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!stack.contains(co) && !visited.contains(co)) {
						stack.push(co);
					}
				}
			}
			if(b + 1 < map[0].length && b < map[0].length) {
				Cords co = checkright(map,a,b,c);
				co.setPrev(map[a][b][c]);
				String s = co.getSym();
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!stack.contains(co) && !visited.contains(co)) {
						stack.push(co);
					}
				}
			}
			if(b-1 >= 0 && b < map[0].length) {
				Cords co = checkleft(map,a,b,c);
				co.setPrev(map[a][b][c]);
				String s = co.getSym();
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!stack.contains(co) && !visited.contains(co)) {
						stack.push(co);
					}
				}
			}
			
		}
		return visited;
	}
	public static ArrayList<Cords> AStar(Cords[][][] map){
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		Cords goal = findGoal(map, c);
		Cords starts = findStart(map, a,b,c);
		Comparator<Cords> comparator = new MyComparator();
		ArrayList<Cords> visited = new ArrayList<Cords>();
		Queue<Cords> queue = new PriorityQueue<>(10000000, comparator);
		queue.add(starts);
		boolean start = true;
		boolean found = false;
		while(found == false) {
			if(!start) {
				starts = findStart(map, a, b, c);
				goal = findGoal(map, c);
				queue.add(starts);
				start = true;
			}
			if(!queue.isEmpty()) {
				Cords see = (queue.poll());
				while(see.getLayer() != goal.getLayer()) {
					see = queue.poll();
				}
				visited.add(see);
				Cords e = visited.get(d);
				a = e.getRow();
				b = e.getCol();
				c = e.getLayer();
				String s = e.getSym() + " " + a + " " + b + " " + c;
				System.out.println(s);
				d++;
				if(e.getSym().equals("|")) {
					start = false;
					c++;
					continue;
				}
			}
			if(a-1>= 0 && a < map.length) {
				Cords co = checkup(map,a,b,c);
				co.setPrev(map[a][b][c]);
				String s = co.getSym();
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!queue.contains(co) && !visited.contains(co)) {
						co.calcG(starts);
						co.calcH(goal);
						queue.add(co);
					}
				}
			}
			if(a + 1 < map.length && a < map.length) {
				Cords co = checkdown(map,a,b,c);
				co.setPrev(map[a][b][c]);
				String s = co.getSym();
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!queue.contains(co) && !visited.contains(co)) {
						co.calcG(starts);
						co.calcH(goal);
						queue.add(co);
					}
				}
			}
			if(b + 1 < map[0].length && b < map[0].length) {
				Cords co = checkright(map,a,b,c);
				co.setPrev(map[a][b][c]);
				String s = co.getSym();
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!queue.contains(co) && !visited.contains(co)) {
						co.calcG(starts);
						co.calcH(goal);
						queue.add(co);
					}
				}
			}
			if(b-1 >= 0 && b < map[0].length) {
				Cords co = checkleft(map,a,b,c);
				co.setPrev(map[a][b][c]);
				String s = co.getSym();
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!queue.contains(co) && !visited.contains(co)) {
						co.calcG(starts);
						co.calcH(goal);
						queue.add(co);
					}
				}
			}
			
		}
		route(map, visited);
		return visited;

	}
	public static void route(Cords[][][] map, ArrayList<Cords> visited){
		boolean nn = true;
		Cords f = visited.get(visited.size()-1);
		while(nn) {
			int j = f.getRow();
			int k = f.getCol();
			int i = f.getLayer();
			map[j][k][i].setSym("+");
			if(f.getPrev() == null) {
				nn = false;
			}else {
				f = map[j][k][i].getPrev();
			}
		}
		for(int i = 0; i < map[0][0].length; i++) {
			for(int j = 0; j < map.length; j++) {
				String a = "";
				for(int k = 0; k < map[0].length; k++) {
					Cords loc = map[j][k][i];
					a += loc.getSym() + " ";
				}
				System.out.println(a);
			}
			System.out.println(" ");
		}
	}
	
}
