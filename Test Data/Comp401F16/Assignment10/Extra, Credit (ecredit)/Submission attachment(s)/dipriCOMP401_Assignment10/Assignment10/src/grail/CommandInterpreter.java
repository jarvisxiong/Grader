package grail;

public interface CommandInterpreter {
public String getCommand();
public void setCommand(String aString);
public void animateArthur();
public Runnable parseSay();
public Runnable parseMove();
public void animateRobin();
public void animateGalahad();
public void animateLancelot();
}
