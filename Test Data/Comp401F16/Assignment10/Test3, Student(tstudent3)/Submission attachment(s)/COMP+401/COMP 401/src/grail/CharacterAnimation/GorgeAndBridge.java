package grail.CharacterAnimation;

import util.annotations.StructurePattern;
import util.annotations.Tags;

@StructurePattern("Bean Pattern")
public class GorgeAndBridge implements GorgeAndBridgeInterface {

	LineInterface gorge1, gorge2, gorge3, gorge4, gorge5, gorge6, gorge7, gorge8;
	//ArmLegVshape tri1, tri2, tri3, tri4;
	LineInterface bridge1, bridge2, bridge3, bridge4;
	Point ref;
	CircleInterface knightArea, guardArea;
	HeadInterface gorgeImg1, gorgeImg2;
	
	public GorgeAndBridge(){
		final int mod500 = 500;
		final int mod100 = 100;
		final int mod600 = 600;
		final int mod900 = 900;
		final int mod1050 = 1050;
		final int mod1000 = 1000;
		final int mod550 = 550;
		final int mod1150 = 1150;
		final int mod850 = 850;
		final int mod700 = 700;
		final int mod650 = 650;
		final int mod250 = 350;
		final int mod675 = 675;
		final int mod450 = 450;
		final int mod825 = 825;
		final int mod25 = 25;
		final int mod302 = 302;
		
		
		gorge1 = new Line(mod500,0,mod100,mod100);
		gorge2 = new Line(mod600,mod100,0,mod550);
		gorge3 = new Line(mod600,mod900,0,mod100);
		gorge4 = new Line(mod600,mod1000,-mod100,mod100);
		gorge5 = new Line(mod1150,0,-mod100,mod100);
		gorge6 = new Line(mod1050,mod100,0,mod550);
		gorge7 = new Line(mod1050,mod900,0,mod100);
		gorge8 = new Line(mod1050,mod1000,mod100,mod100);
		bridge1 = new Line(mod550,mod900,mod550,0);
		bridge2 = new Line(mod550,mod850,mod550,0);
		bridge3 = new Line(mod550,mod700,mod550,0);
		bridge4 = new Line(mod550,mod650,mod550,0);
		knightArea = new Circle(mod250,mod675,mod100,mod100);
		guardArea = new Circle(mod450,mod825,mod100,mod100);
		gorgeImg1 = new CharacterHead("gorge1.jpg", mod600,mod25,mod650,mod450);
		gorgeImg2 = new CharacterHead("gorge2.jpg", mod600,mod900,mod302,mod450);
	}
	public void moveAll(int x , int y){
		gorge1.getLocation().setXY(gorge1.getLocation().getX() + x, gorge1.getLocation().getY() + y);
		gorge2.getLocation().setXY(gorge2.getLocation().getX() + x, gorge2.getLocation().getY() + y);
		gorge3.getLocation().setXY(gorge3.getLocation().getX() + x, gorge3.getLocation().getY() + y);
		gorge4.getLocation().setXY(gorge4.getLocation().getX() + x, gorge4.getLocation().getY() + y);
		gorge5.getLocation().setXY(gorge5.getLocation().getX() + x, gorge5.getLocation().getY() + y);
		gorge6.getLocation().setXY(gorge6.getLocation().getX() + x, gorge6.getLocation().getY() + y);
		gorge7.getLocation().setXY(gorge7.getLocation().getX() + x, gorge7.getLocation().getY() + y);
		gorge8.getLocation().setXY(gorge8.getLocation().getX() + x, gorge8.getLocation().getY() + y);
		bridge1.getLocation().setXY(bridge1.getLocation().getX() + x, bridge1.getLocation().getY() + y);
		bridge2.getLocation().setXY(bridge2.getLocation().getX() + x, bridge2.getLocation().getY() + y);
		bridge3.getLocation().setXY(bridge3.getLocation().getX() + x, bridge3.getLocation().getY() + y);
		bridge4.getLocation().setXY(bridge4.getLocation().getX() + x, bridge4.getLocation().getY() + y);
		knightArea.getLocation().setXY(knightArea.getLocation().getX() + x, knightArea.getLocation().getY() + y);
		guardArea.getLocation().setXY(guardArea.getLocation().getX() + x, guardArea.getLocation().getY() + y);
		gorgeImg1.setXY(gorgeImg1.getX() + x, gorgeImg1.getY() + y);
		gorgeImg2.setXY(gorgeImg2.getX() + x, gorgeImg2.getY() + y);
		
	}
	
	public LineInterface getGorge1() {
		
		return gorge1;
	}

	
	public LineInterface getGorge2() {
	
		return gorge2;
	}

	
	public LineInterface getGorge3() {

		return gorge3;
	}

	
	public LineInterface getGorge4() {
		
		return gorge4;
	}

	
	public LineInterface getGorge5() {
	
		return gorge5;
	}

	
	public LineInterface getGorge6() {

		return gorge6;
	}

	
	public LineInterface getGorge7() {

		return gorge7;
	}

	public LineInterface getGorge8() {
		
		return gorge8;
	}

	/*
	public ArmLegVshape getTri1() {
	
		return null;
	}

	
	public ArmLegVshape getTri2() {
	
		return null;
	}

	
	public ArmLegVshape getTri3() {
		
		return null;
	}


	public ArmLegVshape getTri4() {

		return null;
	}
	*/

	public LineInterface getBridge1() {
	
		return bridge1;
	}


	public LineInterface getBridge2() {

		return bridge2;
	}


	public LineInterface getBridge3() {

		return bridge3;
	}


	public LineInterface getBridge4() {

		return bridge4;
	}
	
	@Tags({"KnightArea"})
	public CircleInterface getKnightArea(){

		return knightArea;
	}
	
	@Tags({"GuardArea"})
	public CircleInterface getGuardArea(){
		
		return guardArea;
	}
	
	public HeadInterface getGorgeImg1(){
		
		return gorgeImg1;
	}
	
	public HeadInterface getGorgeImg2(){

		return gorgeImg2;
	}
}
