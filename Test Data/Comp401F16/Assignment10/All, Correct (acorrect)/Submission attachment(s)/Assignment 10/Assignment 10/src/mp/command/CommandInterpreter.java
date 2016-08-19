package mp.command;


import java.beans.PropertyChangeListener;

import grail.ScannerBean;
import mp.Scene.BridgeScene;
import util.models.PropertyListenerRegisterer;

public interface CommandInterpreter extends PropertyListenerRegisterer{
	public Table getTable();
	public BridgeScene getTake();
	public ScannerBean getRoll();
	public void setCommand(String input);
	public String getCommand();
	public void addPropertyChangeListener(PropertyChangeListener arg0); 
	public void asyncArthur();
	public void asyncGalahad();
	public void asyncLancelot();
	public void asyncRobin();
	public void asyncGuard();
	public Runnable parseMove();
	public Runnable parseSay();
	
	
}
