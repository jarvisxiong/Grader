package mp.Scene;

import java.beans.PropertyChangeListener;

public interface Avatar {
	public void setText( String input);
	public StringShape getText();
	public ImageShape getHead();
	public VClass getArms();
	public VClass getLegs();
	public Line getTorso();
	public void moveThis(int deltaX, int deltaY);
	public void connect();
	public void scale(double a);
	public void flashThis(int newX, int newY);
	
}
