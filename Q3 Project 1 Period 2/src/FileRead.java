import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length > 0) {
			File mapa = new File(args[0]);
			String[][][] coorda = file(mapa);
			if(coorda != null) {
				queueRoute(co(coorda));
				//cords(coorda);
			}
		}else {
			System.out.println("File Not Found");
		}
		

	}
	public static String[][][] file(File map){
		try {
			Scanner scan = new Scanner(map);
			int h = scan.nextInt();
			int w = scan.nextInt();
			int l = scan.nextInt();
			String[][][] coords = new String[h][w][l];
			for(int i = 0; i < l; i++) {
				for(int j = 0; j < h; j++) {
					String a = "";
					for(int k = 0; k < w; k++) {
						coords[j][k][i] = scan.next();
						a += coords[j][k][i] + " ";
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
	public static String checkup(String[][][] map, int a, int b, int c) {
		return map[a-1][b][c];
	}
	public static String checkdown(String[][][] map, int a, int b, int c) {
		return map[a+1][b][c];
	}
	public static String checkleft(String[][][] map, int a, int b, int c) {
		return map[a][b+1][c];
	}
	public static String checkright(String[][][] map, int a, int b, int c) {
		return map[a][b-1][c];
	}
	public static String findStart(String[][][] map, int a, int b, int c) {
		for(int j = 0; j < map.length; j++) {
			for(int k = 0; k < map[0].length; k++) {
				if(map[j][k][c].startsWith("W")) {
					a = j;
					b = k;
				}
			}
		}
		return map[a][b][c];
	}
	public static ArrayList<String> queueRoute(String[][][] map){
		ArrayList<String> visited = new ArrayList<String>();
		LinkedList<String> queue = new LinkedList<>();
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
				String[] e = visited.get(d).split(" ");
				a = Integer.parseInt(e[1]);
				b = Integer.parseInt(e[2]);
				c = Integer.parseInt(e[3]);
				System.out.println(visited.get(d));
				d++;
				if(e[0].equals("|")) {
					start = false;
					c++;
					continue;
				}
			}
			if(a-1>= 0 && a < map.length) {
				String s = checkup(map,a,b,c);
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!queue.contains(s) && !visited.contains(s)) {
						queue.add(s);
					}
				}
			}
			if(a + 1 < map.length && a < map.length) {
				String s = checkdown(map,a,b,c);
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!queue.contains(s) && !visited.contains(s)) {
						queue.add(s);
					}
				}
			}
			if(b + 1 < map[0].length && b < map[0].length) {
				String s = checkleft(map,a,b,c);
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!queue.contains(s) && !visited.contains(s)) {
						queue.add(s);
					}
				}
			}
			if(b-1 > 0 && b < map[0].length) {
				String s = checkright(map,a,b,c);
				if(s.startsWith("$")) {
					found = true;
					break;
				}
				if(s.startsWith(".") || s.startsWith("|")) {
					if(!queue.contains(s) && !visited.contains(s)) {
						queue.add(s);
					}
				}
			}
			
		}
		System.out.println(visited);
		return visited;
	}

}
