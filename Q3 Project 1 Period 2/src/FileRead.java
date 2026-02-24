import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length > 0) {
			File mapa = new File(args[0]);
			String[][][] coorda = file(mapa);
			co(mapa);
			if(coorda != null) {
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
	public static ArrayList<String> co(File map){
		try {
			Scanner scan = new Scanner(map);
			int h = scan.nextInt();
			int w = scan.nextInt();
			int l = scan.nextInt();
			ArrayList<String> res = new ArrayList<String>();
			for(int i = 0; i < h*l + l; i++) {
				scan.nextLine();
			}
			while(scan.hasNext()) {
				String c = scan.next() + " " + scan.nextInt() + " " + scan.nextInt() + " " + scan.nextInt();
				res.add(c);
				System.out.println(c);
			}
			System.out.println();
			return res;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}

}
