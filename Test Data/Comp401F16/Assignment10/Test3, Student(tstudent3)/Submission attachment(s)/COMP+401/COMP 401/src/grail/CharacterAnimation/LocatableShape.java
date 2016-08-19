package grail.CharacterAnimation;

import java.beans.PropertyChangeEvent;



import java.beans.PropertyChangeListener;

import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.PropertyNames;


@PropertyNames({"x","y"})
@EditablePropertyNames({"x","y"})
@ObserverRegisterer(value = "LocatableShape")
public class LocatableShape implements LocatableShapeInt {

    public final int MAX_SIZE = 50;
    PropertyChangeListener[] contents = new PropertyChangeListener[MAX_SIZE];
    int size = 0;
    PropertyListenerSupport propertySupport = new APropertyListenerSupport();
    
	protected int x, y;

	public int getX() { return x; }
	public int getY() { return y; }
	
	public void setX(int newX) {
		int oldVal = x;
		x = newX;
		notifyAllListeners(new PropertyChangeEvent(this, "X", oldVal, newX));
		}
	
	public void setY(int newY) {
		int oldVal = y;
		y = newY;
		notifyAllListeners(new PropertyChangeEvent(this, "Y", oldVal, newY));
		}
	
	public void setXY(int newX, int newY){
		int oldValX = x;
		int oldValY = y;
		x = newX;
		y = newY;
		notifyAllListeners(new PropertyChangeEvent(this, "x", oldValX, newX));
		notifyAllListeners(new PropertyChangeEvent(this, "y", oldValY, newY));
	}
	
	public int size() {
        return size;
    }
    
    public PropertyChangeListener get (int index) {
        return contents[index];
    }

    public boolean isFull() {
        return size == MAX_SIZE;

    }

    public void add(PropertyChangeListener l) {
        if (isFull()) {
			System.out.println("Adding item to a full collection");
		}
        else {
            contents[size] = l;
            size++;
        }
    }  
    public void notifyAllListeners(PropertyChangeEvent event) {
        for (int index = 0; index < size(); index++) {
            get(index).propertyChange(event);
        }
    }
    public void remove(PropertyChangeListener element) {
        contents[indexOf(element)] = contents[size - 1];
        size--;
    }

    public void remove(int startIndex) {
        shiftUp(startIndex);
        size--;
    }

    void shiftUp(int startIndex) {
        for (int index = startIndex; index + 1 < size; index++) {
			contents[index] = contents[index + 1];
		}
    }

    public int indexOf(PropertyChangeListener element) {
        int index;
        for (index = 0; index < size && !element.equals(contents[index]); index++) {
		}
        return index;
    }

    public boolean member(PropertyChangeListener element) {
        return indexOf(element) < size;

    }

    public void clear() {
        size = 0;
    }
    @ObserverRegisterer(value = "LocatableShape")
    public void addPropertyChangeListener(PropertyChangeListener listener) {
		add(listener);
		
	}

}
