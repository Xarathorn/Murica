import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class dev {
	public static void main(String[] args) throws Exception{
		getStates();
	}
	public static void getStates() throws Exception{
		System.out.println("Starting...");
		Scanner in = new Scanner (new File("states.txt"));
		PrintWriter st8s = new PrintWriter("stateName.txt", "UTF-8");
		PrintWriter abrv = new PrintWriter("stateAbrv.txt", "UTF-8");
		st8s.write("(");
		abrv.write("(");
		in.nextLine();
		for (int i = 0; i < 50; i++) {
			String st8Stuf = in.nextLine();
			st8s.write(", \""+st8Stuf.substring(0, st8Stuf.length()-2).trim()+"\"");
			abrv.write(", \""+st8Stuf.substring(st8Stuf.length()-2)+"\"");
			System.out.println(st8Stuf);
		}
		st8s.write(")");
		abrv.write(")");
		st8s.close();
		abrv.close();
		in.close();
		System.out.print("Done!");
	}
}