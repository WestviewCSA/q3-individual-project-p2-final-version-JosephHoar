import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class FileRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length > 0) {
			File mapa = new File(args[0]);
			Cords[][][] coorda = file(mapa);
			if(coorda != null) {
				stackRoute((coorda));
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
		System.out.println(visited);
		return visited;
	}
	public static String[][][] route(String[][][] map, ArrayList<String> visited){
		for(int i = 0; i < visited.size(); i++) {
			String[] e = visited.get(i).split(" ");
			int a = Integer.parseInt(e[1]);
			int b = Integer.parseInt(e[2]);
			int c = Integer.parseInt(e[3]);
			map[a][b][c] = "+";
		}
		for(int j = 0; j < map[0][0].length; j++) {
			for(int k = 0; k < map.length; k++) {
				String d = "";
				for(int l = 0; l < map[0].length; l++) {
					d += map[k][l][j] + " ";
				}
				System.out.println(d);
			}
			System.out.println(" ");
		}
		return map;
		
	}
}
