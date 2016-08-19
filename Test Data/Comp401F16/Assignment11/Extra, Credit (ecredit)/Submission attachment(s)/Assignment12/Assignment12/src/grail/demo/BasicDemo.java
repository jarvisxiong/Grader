package grail.demo;

import java.beans.PropertyChangeListener;

import grail.model.APropertyListenerSupport;
import grail.model.PropertyListenerSupport;

public class BasicDemo implements Demo {
	PropertyListenerSupport propertyListener;
	
	public BasicDemo() {
		this.propertyListener = new APropertyListenerSupport();
	}
	
	public void run() {
		System.out.println("Default Demo. Nothing was run.");
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		this.propertyListener.addElement(arg0);
		
	}

}
