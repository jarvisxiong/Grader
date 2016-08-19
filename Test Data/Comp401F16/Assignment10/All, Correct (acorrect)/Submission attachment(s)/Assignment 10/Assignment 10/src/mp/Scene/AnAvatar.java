package mp.Scene;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({"Avatar"})
@PropertyNames({"head","arms","legs","torso","text"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
public class AnAvatar implements Avatar {
	StringShape text;
	ImageShape head;
	VClass arms,legs;
	Line torso;
	int x,y,iconWid,iconHeight;
	final double torsoangle=90.0;
	 int torsoheight;
//	double angle;
//	double radius;
	final int textloc=10;
	
	
	public AnAvatar(ImageShape theHead){
		head= theHead;
	}
	
	public AnAvatar(String saying,String filename,int iniX, int iniY,int tH){
		x=iniX;
		y=iniY;
		torsoheight=tH;
		Icon icon=new ImageIcon(filename);
		iconWid=icon.getIconWidth();
		iconHeight=icon.getIconHeight();
		arms= new TheVClass(x,y);
		legs= new TheVClass(x,y);
		torso=new ALine(x,y,torsoangle,torsoheight);
		head= new AImageShape(filename,x,y,iconWid, iconHeight);
		text= new AStringShape(x,y,saying);
		connect();
	}
	
	public void setText(String input){
		text.setText(input);
	}
	
	public StringShape getText(){
		return text;
	}
	public ImageShape getHead(){
		return head;
	}
	public VClass getArms(){
		return arms;
	}
	public VClass getLegs(){
		return legs;
	}
	public Line getTorso(){
		return torso;
	}
	
	@Tags({"move"})
	public void moveThis(int deltaX, int deltaY){
		x+=deltaX;
		y+=deltaY;
		connect();
	}
	public void flashThis(int newX, int newY){
		x=newX;
		y=newY;
		connect();
	}
	
	@Tags({"scale"})
	public void scale(double a){
		torsoheight*=a;
		torso.scale(a);
		arms.getLeftLine().scale(a);
		arms.getRightLine().scale(a);
		legs.getLeftLine().scale(a);
		legs.getRightLine().scale(a);
		connect();
	}
	
	public void connect(){
		head.setX(x);
		head.setY(y);
		arms.getLeftLine().setX(x+iconWid/2);
		arms.getRightLine().setX(x+iconWid/2);
		arms.getLeftLine().setY(y+iconHeight);
		arms.getRightLine().setY(y+iconHeight);
		torso.setX(x+iconWid/2);
		torso.setY(y+iconHeight);
		legs.getLeftLine().setX(x+iconWid/2);
		legs.getRightLine().setX(x+iconWid/2);
		legs.getLeftLine().setY(y+torsoheight+iconHeight);
		legs.getRightLine().setY(y+torsoheight+iconHeight);
		text.setX(x+iconWid);
		text.setY(y-textloc);
	}

	
	public static void addPropertyChangeListener(Avatar aa,PropertyChangeListener arg0) {
		TheVClass.addPropretyChangeListener(aa.getArms(), arg0);
		TheVClass.addPropretyChangeListener(aa.getLegs(), arg0);
		aa.getHead().addPropertyChangeListener(arg0);
		aa.getText().addPropertyChangeListener(arg0);
		aa.getTorso().addPropertyChangeListener(arg0);
	}
}
