package main;

import java.util.Scanner;

public class Assignment3 {
	private Scanner input;
	private ScannerBean scannerBean;
	
	public Assignment3(){
		input = new Scanner(System.in);
		scannerBean = new AScannerBean();
	}
	
	public void run(){
		while(true){
			String line = input.nextLine();
			if(line.charAt(0) == '.'){
				System.out.println(" - Program terminated - ");
				System.exit(-1);
			}
			scannerBean.setScannedString(line);
		}
	}
	
	public static void main(String[] args){
		new Assignment3().run();
	}
}
