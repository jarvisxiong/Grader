package grail.scanner;

import grail.shape.BridgeSceneInterface;

public interface CommandInterpreterInterface {
	public ScannerInterface getBeanScanner();
	public BridgeSceneInterface getBriScene();
	public void setCommand(String str);
	public String getErrorCommand();
	public String getCommand();
	public void asynchronousArthur();
	public void asynchronousRobin();
	public void asynchronousLancelot();
	public void asynchronousGalahad();
	public void asynchronousGuard();
}
