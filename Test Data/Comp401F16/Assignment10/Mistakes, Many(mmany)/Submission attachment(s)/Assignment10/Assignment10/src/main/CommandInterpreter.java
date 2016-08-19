package main;

import java.util.ArrayList;

import mp.helpers.Table;

public interface CommandInterpreter {
	public void setCommand(String command);
	public Table getTable();
	public ArrayList<String> getErrors();
	
	public void animateArthur();
	public void animateLancelot();
	public void animateGalahad();
	public void animateRobin();
	public void animateGuard();
}
