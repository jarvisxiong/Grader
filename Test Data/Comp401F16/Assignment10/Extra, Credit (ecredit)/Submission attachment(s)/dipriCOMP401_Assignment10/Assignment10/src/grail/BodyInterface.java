package grail;
import util.annotations.Tags;
import grail.*;

@Tags({"Avatar"})
public interface BodyInterface {
public void moveBody(int x, int y);
public Head getAHead();
public Line getBody();
public AngleInterface getArms();
public AngleInterface getLegs();
public StringShape getText();
}
