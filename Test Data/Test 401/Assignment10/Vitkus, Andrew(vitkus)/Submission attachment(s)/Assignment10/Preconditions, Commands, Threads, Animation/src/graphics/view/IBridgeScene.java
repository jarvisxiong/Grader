package graphics.view;

import util.models.PropertyListenerRegisterer;
import bus.uigen.OEFrame;
import graphics.avatar.IAvatar;
import graphics.shapes.IOvalShape;
import graphics.shapes.IRectangleShape;

public interface IBridgeScene extends PropertyListenerRegisterer {
	public IAvatar getArthur();
	public IAvatar getLancelot();
	public IAvatar getRobin();
	public IAvatar getGalahad();
	public IAvatar getGuard();
	public IRectangleShape getCloseLand();
	public IRectangleShape getBridge();
	public IRectangleShape getFarLand();
	public IOvalShape getGuardArea();
	public IOvalShape getKnightArea();
	public boolean preApproach();
	public void approach(IAvatar knight);
	public void walk(IAvatar avatar, int dx, int dy);
	public void walkTo(IAvatar avatar, int newX, int newY);
	public void flail(IAvatar avatar, int newX, int newY);
	public boolean isOccupied();
	public boolean isKnightTurn();
	public boolean preSay();
	public void say(String text);
	public boolean prePass();
	public void pass();
	public boolean preFail();
	public void fail();
	public OEFrame getOEFrame();
	public void setOEFrame(OEFrame frame);
	public void angleNeck(IAvatar avatar, double target, boolean clockwise);
	public void angleBody(IAvatar avatar, double target, boolean clockwise);
	public void angleRightArm(IAvatar avatar, double target, boolean clockwise);
	public void angleLeftArm(IAvatar avatar, double target, boolean clockwise);
	public void angleRightLeg(IAvatar avatar, double target, boolean clockwise);
	public void angleLeftLeg(IAvatar avatar, double target, boolean clockwise);
	public void angleNeck(IAvatar avatar, int steps);
	public void angleBody(IAvatar avatar, int steps);
	public void angleRightArm(IAvatar avatar, int steps);
	public void angleLeftArm(IAvatar avatar, int steps);
	public void angleRightLeg(IAvatar avatar, int steps);
	public void angleLeftLeg(IAvatar avatar, int steps);
	public void scaleAvatar(IAvatar avatar, double scaleFactor);
}
