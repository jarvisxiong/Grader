package grail.interfaces;
import java.util.Iterator;

public interface MutableIterator extends Iterator<String> {

	public void setString(String input);
	public String getString();
	public void setCurrentPosition(int position);
	public int getCurrentPosition();

}
