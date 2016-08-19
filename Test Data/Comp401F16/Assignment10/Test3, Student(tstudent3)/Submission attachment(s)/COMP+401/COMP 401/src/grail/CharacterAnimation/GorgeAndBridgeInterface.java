package grail.CharacterAnimation;

public interface GorgeAndBridgeInterface {
	public LineInterface getGorge1();
	public LineInterface getGorge2();
	public LineInterface getGorge3();
	public LineInterface getGorge4();
	public LineInterface getGorge5();
	public LineInterface getGorge6();
	public LineInterface getGorge7();
	public LineInterface getGorge8();
	//public ArmLegVshape getTri1();
	//public ArmLegVshape getTri2();
	//public ArmLegVshape getTri3();
	//public ArmLegVshape getTri4();
	public LineInterface getBridge1(); 
	public LineInterface getBridge2(); 
	public LineInterface getBridge3(); 
	public LineInterface getBridge4(); 
	public CircleInterface getKnightArea();
	public CircleInterface getGuardArea();
	public HeadInterface getGorgeImg1();
	public HeadInterface getGorgeImg2();
	public void moveAll(int x , int y);
}
