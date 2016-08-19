package mp.Scene;

import java.beans.PropertyChangeListener;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
public class TheGorge implements Gorge{

	Line gorgeLine1;
	Line gorgeLine2;
	ImageShape bridge;
	final int liubai=600;
	final int ling=0;
	final double jiushi=90.0;
	final double qibai=700.0;
	final int sanbaiyi=310;
	final int sibai=400;
	final int bashi=80;
	final int yiqian=1000;
	
	public TheGorge(){
		gorgeLine1= new ALine(liubai,ling,jiushi,qibai);
		gorgeLine2= new ALine(yiqian,ling,jiushi,qibai);
		bridge= new AImageShape("bridge.jpg",liubai,sanbaiyi,sibai,bashi);
	}
	
	public Line getG1(){
		return gorgeLine1;
	}
	public Line getG2(){
		return gorgeLine2;
	}
	public ImageShape getBeidge(){
		return bridge;
	}
	public static void addPropertyChangeListener(Gorge gg,PropertyChangeListener arg0){
		gg.getBeidge().addPropertyChangeListener(arg0);
		gg.getG1().addPropertyChangeListener(arg0);
		gg.getG2().addPropertyChangeListener(arg0);
	}
}
