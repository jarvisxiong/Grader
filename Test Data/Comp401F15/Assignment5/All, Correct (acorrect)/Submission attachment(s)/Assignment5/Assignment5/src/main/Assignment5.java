package main;

import grail.BridgeScene;
import grail.BridgeSceneInterface;
import grail.AvatarInterface;
import grail.Avatar;

//import java.util.Scanner;

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
 *This is for assignment5 from comp 401
 */
public class Assignment5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*
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
		 Shape sh=new RotateLine(rad,angle);
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
	*/
		
		BridgeSceneInterface bsi=new BridgeScene();
		OEFrame oeFrame = ObjectEditor.edit(bsi);
		oeFrame.setSize(800, 800);
		oeFrame.refresh();
		final int longSleepTime=3000;
		final int shortSleepTime=10;
		ThreadSupport.sleep(longSleepTime);
		
		//animate move, take arthur as an example
		//AvatarInterface ai=bsi.getArthur();
		for (int i=0;i<200;i++)
		{
			bsi.getArthur().move(1, 0);
			oeFrame.refresh();
			ThreadSupport.sleep(shortSleepTime);
		}
		
		//animate rotate, take Galahad as an example
		for(int i=0;i<100;i++)
		{
			bsi.getGalahad().getArms().getLeftLine().rotate(i);
			oeFrame.refresh();
			ThreadSupport.sleep(shortSleepTime);
		}
		
	//animate the scale	
		ThreadSupport.sleep(longSleepTime);
		bsi.getLancelot().scale(2);
		oeFrame.refresh();

	}
	
	/*rotate a line with different angles*/
	/*public static void rotateLine(Shape lineInstance, int units){
		lineInstance.changeRotateAngle(units);
	}*/
	
	/*move an object along the x axis*/
	/*public static void moveLine(Shape lineInstance, int x){
		lineInstance.setX(x); 
	}*/
}
