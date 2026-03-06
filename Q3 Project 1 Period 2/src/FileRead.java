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
				queueRoute(co(mapa));
				cords(coorda);
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
					System.out.println(c);
				}
			}
		}
		return b;
		
	}
	public static String[][][] co(File map){
		try {
			Scanner scan = new Scanner(map);
			int h = scan.nextInt();
			int w = scan.nextInt();
			int l = scan.nextInt();
			String[][][] res = new String[h][w][l];
			for(int i = 0; i < h*l + l; i++) {
				scan.nextLine();
			}
			for(int i = 0; i < l; i++) {
				for(int j = 0; j < h; j++) {
					for(int k = 0; k < w; k++) {
						String s = scan.next() + " " + j + " " + k + " " + i;
						scan.next();
						scan.next();
						scan.next();
						res[j][k][i] = s;
						System.out.println(res[j][k][i]);
					}
				}
			}
			System.out.println();
			return res;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
	public static void checkup(String[][][] map, int a, int b, int c, LinkedList queue, ArrayList<String> visited, boolean found) {
		if(!map[a-1][b][c].startsWith("@") && !visited.contains(map[a-1][b][c]) && !queue.contains(map[a-1][b][c])) {
			if(map[a-1][b][c].startsWith("$")) {
				found = true;
			}else{
				queue.add(map[a-1][b][c]);
			}
		}
	}
	public static void checkdown(String[][][] map, int a, int b, int c, LinkedList queue, ArrayList<String> visited, boolean found) {
		if(!map[a+1][b][c].startsWith("@") && !visited.contains(map[a+1][b][c]) && !queue.contains(map[a+1][b][c])) {
			if(map[a-1][b][c].startsWith("$")) {
				found = true;
			}else{
				queue.add(map[a+1][b][c]);
			}
		}
	}
	public static void checkleft(String[][][] map, int a, int b, int c, LinkedList queue, ArrayList<String> visited, boolean found) {
		if(!map[a][b+1][c].startsWith("@") && !visited.contains(map[a][b+1][c]) && !queue.contains(map[a][b+1][c])) {
			if(map[a][b+1][c].startsWith("$")) {
				found = true;
			}else{
				queue.add(map[a][b+1][c]);
			}
		}
	}
	public static void checkright(String[][][] map, int a, int b, int c, LinkedList queue, ArrayList<String> visited, boolean found) {
		if(!map[a][b-1][c].startsWith("@") && !visited.contains(map[a][b-1][c]) && !queue.contains(map[a][b-1][c])) {
			if(map[a-1][b][c].startsWith("$")) {
				found = true;
			}else{
				queue.add(map[a][b-1][c]);
			}
		}
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
		while(!found) {
			while(start == false) {
				for(int i = 0; i < map[0][0].length; i++) {
					for(int j = 0; j < map.length; j++) {
						for(int k = 0; k < map[0].length; k++) {
							if(map[j][k][i].startsWith("W") && !visited.contains(map[j][k][i])) {
								queue.add(map[j][k][i]);
								a = j;
								b = k;
								c = i;
								break;
							}
						}
					}
				}
				start = true;
			}
			visited.add(queue.remove());
			String[] e = visited.get(d).split(" ");
			a = Integer.parseInt(e[1]);
			b = Integer.parseInt(e[2]);
			c = Integer.parseInt(e[3]);
			d++;
			if(a-1>= 0 && a < map.length) {
				checkup(map,a,b,c,queue,visited,found);
			}
			if(a + 1 < map.length && a < map.length) {
				checkdown(map,a,b,c,queue,visited,found);
			}
			if(b + 1 < map[0].length && b < map[0].length) {
				checkleft(map,a,b,c,queue,visited,found);
			}
			if(b-1 > 0 && b < map[0].length) {
				checkright(map,a,b,c,queue,visited,found);
			}
			
		}
		System.out.println(visited);
		return visited;
	}

}
