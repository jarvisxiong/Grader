package widgets;

import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import util.awt.ADelegateFrame;
import windowApp.ACursorTrackerOfDelegateFrame;

public class ATextFieldInputTracker extends ACursorTrackerOfDelegateFrame {
	public static int Y_OFFSET = 50;
	public static int X_OFFSET = 10;
	public static int  CHAR_WIDTH = 7;
	public static int ERROR = 3;
	StringBuffer stringBuffer = new StringBuffer();
	int insertionIndex = 0;
	FontMetrics metrics;
	List<TextFieldListener> listeners = new ArrayList();
	public ATextFieldInputTracker(ADelegateFrame theFrame) {
		super(theFrame);
	}
	public void mousePressed(MouseEvent event) {
		super.mousePressed(event);
		charY = Y_OFFSET;
		int[] positionAndCoordinate = toPositionAndCoordinate(charX);		
		insertionIndex = positionAndCoordinate[0];
		charX = positionAndCoordinate[1];
	}
	int toCoordinate(int position) {
		int retVal = X_OFFSET;
		for (int i = 0; i < stringBuffer.length(); i++)
			retVal += metrics.charWidth(stringBuffer.charAt(i));
		return retVal;
	}
	int[] toPositionAndCoordinate(int coordinate) {
		int indexFound = 0;
		int totalOffset = X_OFFSET;
		for (int i = 0; i < stringBuffer.length(); i++) {
			int charSize = metrics.charWidth(stringBuffer.charAt(i));			
			if (totalOffset + charSize - ERROR > coordinate)
				break;
			else {
				indexFound++;
				totalOffset += charSize;
			}
		}
		int[] retVal = {indexFound, totalOffset};
		return retVal;			
	}
	public void keyTyped(KeyEvent event) {
		super.keyTyped(event);
		//insert(insertionIndex, lastChar);
		stringBuffer.insert(insertionIndex, lastChar);
		notifyListeners(insertionIndex, lastChar);
		insertionIndex++;
		charX += metrics.charWidth(lastChar);
	}
	
	public void insert(int index, char ch) {
		stringBuffer.insert(index, ch);		
		//charX += metrics.charWidth(lastChar);
		delegateFrame.repaint();
		
	}
	void notifyListeners(int index, char ch) {
		for (TextFieldListener textFieldListener:listeners)
			textFieldListener.elementInserted(index, ch);
	}
	public void addTextFieldListener(TextFieldListener theListener) {
		listeners.add(theListener);
	}
	public String getName() {
		return delegateFrame.getTitle();
	}
	
}
