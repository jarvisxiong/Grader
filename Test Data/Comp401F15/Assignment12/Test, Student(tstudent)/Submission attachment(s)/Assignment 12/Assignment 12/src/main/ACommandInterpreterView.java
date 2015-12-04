package main;

import java.beans.PropertyChangeEvent;

import grail.CommandInterpreter;

public class ACommandInterpreterView implements CommandInterpreterView {

	public ACommandInterpreterView (CommandInterpreter interpreter) {
		interpreter.addPropertyChangeListener(this);
	}
	public void propertyChange(PropertyChangeEvent evt) {
		
	}

}
