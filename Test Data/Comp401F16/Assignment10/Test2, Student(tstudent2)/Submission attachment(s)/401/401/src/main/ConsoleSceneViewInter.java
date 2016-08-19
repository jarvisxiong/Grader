package main;

import java.beans.PropertyChangeListener;

import util.models.PropertyListenerRegisterer;

public interface ConsoleSceneViewInter extends PropertyChangeListener {
	public void register (PropertyListenerRegisterer aPropertyChangeRegister);
}
