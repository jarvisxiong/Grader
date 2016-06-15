
import java.util.Scanner;


public class Main {
	public static void main(String args[]) {
		Scanner aScanner = new Scanner (System.in);
		String anXLine = aScanner.nextLine();
		String aYLine = aScanner.nextLine();
		int anX = Integer.parseInt(anXLine);
		int aY = Integer.parseInt(aYLine);
		Point aPoint =  new ACartesianPoint (anX, aY);
		System.out.println (aPoint.getRadius());
		System.out.println (aPoint.getAngle());
//		
	}

}
