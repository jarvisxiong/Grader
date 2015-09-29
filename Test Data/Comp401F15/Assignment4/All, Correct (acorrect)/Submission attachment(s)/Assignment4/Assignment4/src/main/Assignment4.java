package main;

import grail.BeanStringScanner4;
import grail.ScannerInterface;
import grail.Line;
import grail.Shape;

import java.util.Scanner;

import bus.uigen.ObjectEditor;
import bus.uigen.OEFrame;
import util.annotations.WebDocuments;
import util.misc.ThreadSupport;

//import lectures.animation.threads_commands.ThreadSupport;
/**
 * 
 * @author Dong Nie
 * dongnie@cs.unc.edu
 *
 *This is for assignment4 from comp 401
 */
public class Assignment4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		System.out.println("please enter a string:");
		Scanner scan = new Scanner(System.in);
		String line="";
		
		while(true)
		{
		 line = scan.nextLine();
		// if(line.equals(""))
			// continue;
		 if (line.charAt(0)=='.')
		 {
			 break;
		 }
		 //System.out.println("Processed by ordinary class");
		 //StringScanner.myScanner(line); 
		 //System.out.println("Processed by bean class");
		 //test bean scanner
		 bss.setScannedString(line);
		 //ObjectEditor.edit(bss);
	     
		}*/
		final int intervalTime=3000;
		ScannerInterface bss=new BeanStringScanner4();

		 OEFrame oeFrame = ObjectEditor.edit(bss);
		 bss.setScannedString("MoVe 050 { saY \"hi!\" } ");
		 oeFrame.refresh();
		 ThreadSupport.sleep(intervalTime);
		 bss.setScannedString("RotateLeftArm 5 rotateLeftArm ");
		 oeFrame.refresh();
		 ThreadSupport.sleep(intervalTime); // 3 second delay should be enough
		 bss.setScannedString("Call Define 5 Turnleft ");
		 oeFrame.refresh();
		 ThreadSupport.sleep(intervalTime); // 3 second delay should be enough
		 
		 //set the x,y point
		 
		 final double rad=30,angle=1.71;
		 Shape sh=new Line(rad,angle);
		 oeFrame=ObjectEditor.edit(sh);
		 
		 //animate the movement
		 final int moveLen=550;
		 final int timeSleep=10;
		 for(int i = 0;i < moveLen; i++){
				ThreadSupport.sleep(timeSleep);	
				moveLine(sh,i);
				oeFrame.refresh();
			}
		 //animate rotation
			ThreadSupport.sleep(intervalTime);	
			oeFrame.refresh();
			final int coor=50;
			sh.setX(coor);
			sh.setY(coor);
		 int units=0;
		 for(int i = 0;i < moveLen; i++){
				ThreadSupport.sleep(timeSleep);
			 	units=i;
				rotateLine(sh,i+1);
				oeFrame.refresh();
			}
		 
		 //animate move and rotate
		 ThreadSupport.sleep(intervalTime);	
			oeFrame.refresh();
			sh.setX(coor);
			sh.setY(coor);
		 for(int i = 0;i < moveLen; i++){
				ThreadSupport.sleep(timeSleep);
			 	units=i;
			 	sh.setX(i);
				rotateLine(sh,i+1);
				oeFrame.refresh();
			}
	
	}
	
	/*rotate a line with different angles*/
	public static void rotateLine(Shape lineInstance, int units){
		lineInstance.changeRotateAngle(units);
	}
	
	/*move an object along the x axis*/
	public static void moveLine(Shape lineInstance, int x){
		lineInstance.setX(x); 
	}
}
