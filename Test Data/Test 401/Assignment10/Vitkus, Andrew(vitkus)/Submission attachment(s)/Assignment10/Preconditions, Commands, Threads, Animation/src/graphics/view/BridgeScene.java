package graphics.view;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import bus.uigen.OEFrame;
import graphics.avatar.Avatar;
import graphics.avatar.IAvatar;
import graphics.avatar.IHead.AvatarName;
import graphics.shapes.IOvalShape;
import graphics.shapes.IRectangleShape;
import graphics.shapes.IRotatingLine;
import graphics.shapes.AnOvalShape;
import graphics.shapes.ARectangleShape;
import util.APropertyChangeListenerManager;
import util.IPropertyChangeListenerManager;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.IsCompositeShape;
import util.annotations.Visible;

@PropertyNames({ "Arthur", "Lancelot", "Robin", "Galahad", "Guard",
		"GuardArea", "KnightArea", "Bridge", "CloseLand", "FarLand",
		"Occupied", "KnightTurn", "OEFrame" })
@EditablePropertyNames({ "OEFrame" })
@Tags({ "Bridge Scene" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@IsCompositeShape(true)
public class BridgeScene implements IBridgeScene {
	private IAvatar arthur, lancelot, robin, galahad, guard, occupyingKnight;
	private ArrayList<IAvatar> resolved;
	private IRectangleShape closeLand, bridge, farLand;
	private IOvalShape knightArea, guardArea;
	private boolean occupied, knightTurn;
	private OEFrame frame;
	private IPropertyChangeListenerManager pclm;

	public BridgeScene() {
		arthur = new Avatar(AvatarName.ARTHUR, 50, 175, Color.YELLOW);
		lancelot = new Avatar(AvatarName.LANCELOT, 150, 175, Color.BLACK);
		robin = new Avatar(AvatarName.ROBIN, 100, 325, Color.GREEN);
		galahad = new Avatar(AvatarName.GALAHAD, 200, 325, Color.RED);
		guard = new Avatar(AvatarName.GUARD, 500, 250, Color.GRAY);
		closeLand = new ARectangleShape(0, 150, 600, 300, Color.DARK_GRAY);
		bridge = new ARectangleShape(600, 200, 100, 50, Color.getHSBColor(
				(float) (28. / 255), (float) (80. / 255), (float) (51. / 255)));
		farLand = new ARectangleShape(700, 150, 100, 300, Color.DARK_GRAY);
		guardArea = new AnOvalShape(450, 260, 100, 50, true, Color.WHITE);
		knightArea = new AnOvalShape(325, 260, 100, 50, true, Color.WHITE);
		occupied = false;
		occupyingKnight = null;
		knightTurn = false;
		resolved = new ArrayList<>();
		pclm = new APropertyChangeListenerManager(this);
	}

	@Override
	public void setOEFrame(OEFrame frame) {
		this.frame = frame;
	}

	@Visible(false)
	@Override
	public OEFrame getOEFrame() {
		return frame;
	}

	@Override
	public IAvatar getArthur() {
		return arthur;
	}

	@Override
	public IAvatar getLancelot() {
		return lancelot;
	}

	@Override
	public IAvatar getRobin() {
		return robin;
	}

	@Override
	public IAvatar getGalahad() {
		return galahad;
	}

	@Override
	public IAvatar getGuard() {
		return guard;
	}

	@Override
	public IRectangleShape getCloseLand() {
		return closeLand;
	}

	@Override
	public IRectangleShape getBridge() {
		return bridge;
	}

	@Override
	public IRectangleShape getFarLand() {
		return farLand;
	}

	@Override
	public IOvalShape getGuardArea() {
		return guardArea;
	}

	@Override
	public IOvalShape getKnightArea() {
		return knightArea;
	}

	@Override
	public boolean preApproach() {
		return !occupied;
	}

	@Tags({ "Approach" })
	@Override
	public void approach(IAvatar knight) {
		if (knight == null || resolved.contains(knight)) {
			return;
		}
		assert preApproach() : "Cannot approach when knight area is alraedy occupied!";
		walkTo(knight, 375, 285 - knight.getLegs().getLeftLine().getHeight());
		occupyingKnight = knight;
		occupied = true;
		knightTurn = false;
		firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "approach", false));
		firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "say", true));
		firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "pass", true));
		firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "fail", true));
	}

	@Override
	public void walk(IAvatar avatar, int dx, int dy) {
		walk(avatar, (double) dx, (double) dy);
	}

	private void walk(IAvatar avatar, double dx, double dy) {
		if (avatar == null) {
			return;
		}
		double dydx = (double) dy / dx;
		double dxdy = (double) dx / dy;

		// System.out.println("dydx: " + dydx + ", dxdy: " + dxdy);
		int turnDir = -1;
		int xDir = dx >= 0 ? 1 : -1;
		int yDir = dy >= 0 ? 1 : -1;

		dx = Math.abs(dx);
		dy = Math.abs(dy);

		IRotatingLine leftLeg = avatar.getLegs().getLeftLine();

		// System.out.println(dydx);

		if (dx == 0) {
			for (; dy > 0; dy--) {
				avatar.rotateLeftLeg(turnDir);
				avatar.rotateRightLeg(-turnDir);
				avatar.move(0, yDir);
				if (leftLeg.getHeight() < 4 * leftLeg.getRadius() / 5) {
					turnDir *= -1;
				}
				// frame.refresh();
				sleep(2);
			}
		} else if (dy == 0) {
			for (; dx > 0; dx--) {
				avatar.rotateLeftLeg(turnDir);
				avatar.rotateRightLeg(-turnDir);
				avatar.move(xDir, 0);
				if (leftLeg.getHeight() < 4 * leftLeg.getRadius() / 5) {
					turnDir *= -1;
				}
				// frame.refresh();
				sleep(2);
			}
		} else if (dydx < 1) {
			for (; dx > 0; dx--) {
				avatar.rotateLeftLeg(turnDir);
				avatar.rotateRightLeg(-turnDir);
				avatar.move(xDir, 0);
				if ((int) (dy - dydx) != (int) dy) {
					avatar.move(0, yDir);
				}
				dy -= dydx;
				if (leftLeg.getHeight() < 4 * leftLeg.getRadius() / 5) {
					turnDir *= -1;
				}
				// frame.refresh();
				sleep(2);
			}
		} else if (Math.abs(dydx - 1) < 0.01) {
			for (; dx > 0; dx--) {
				avatar.rotateLeftLeg(turnDir);
				avatar.rotateRightLeg(-turnDir);
				avatar.move(xDir, yDir);
				if (leftLeg.getHeight() < 4 * leftLeg.getRadius() / 5) {
					turnDir *= -1;
				}
				// frame.refresh();
				sleep(2);
			}
		} else {
			for (; dy > 0; dy--) {
				avatar.rotateLeftLeg(turnDir);
				avatar.rotateRightLeg(-turnDir);
				avatar.move(0, yDir);
				if ((int) (dx - dydx) != (int) dx) {
					avatar.move(xDir, 0);
					dx -= dxdy;
				}
				dx -= dxdy;
				if (leftLeg.getHeight() < 4 * leftLeg.getRadius() / 5) {
					turnDir *= -1;
				}
				// frame.refresh();
				sleep(2);
			}
		}
	}

	@Override
	public void walkTo(IAvatar avatar, int newX, int newY) {
		if (avatar == null) {
			return;
		}
		int dx = newX - avatar.getX();
		int dy = newY - avatar.getY();
		walk(avatar, dx, dy);
	}

	@Override
	public void flail(IAvatar avatar, int newX, int newY) {
		if (avatar == null) {
			return;
		}
		double dx = newX - avatar.getX();
		double dy = newY - avatar.getY();
		double dydx = dy / dx;
		double dxdy = dx / dy;
		int xDir = dx >= 0 ? 1 : -1;
		int yDir = dy >= 0 ? 1 : -1;

		dx = Math.abs(dx);
		dy = Math.abs(dy);

		// System.out.println(dydx + ", " + dx + ", " + dy);

		if (dx == 0) {
			for (; dy > 0; dy--) {
				avatar.move(0, yDir);
				// System.out.println("(" + avatar.getX() + ", " + avatar.getY()
				// + ")");
				avatar.rotateLeftArm(1);
				avatar.rotateRightArm(-1);
				// frame.refresh();
				sleep(1);
			}
		} else if (Math.abs(dydx) <= 0.99) {
			for (; dx > 0; dx--) {
				avatar.move(xDir, 0);
				if ((int) (dy - dydx) != (int) dy) {
					avatar.move(0, yDir);
				}

				avatar.rotateLeftArm(1);
				avatar.rotateRightArm(-1);
				// System.out.println("(" + avatar.getX() + ", " + avatar.getY()
				// + ")");
				dy -= dydx;
				// frame.refresh();
				sleep(1);
			}
		} else if (Math.abs(dydx - 1) < 0.01) {
			for (; dx > 0; dx--) {
				avatar.move(xDir, yDir);
				avatar.rotateLeftArm(1);
				avatar.rotateRightArm(-1);
				// System.out.println("(" + avatar.getX() + ", " + avatar.getY()
				// + ")");
				// frame.refresh();
				sleep(1);
			}
		} else {
			for (; dy > 0; dy--) {
				avatar.move(0, yDir);
				if ((int) (dx - dydx) != (int) dx) {
					avatar.move(xDir, 0);
					dx -= dxdy;
				}
				avatar.rotateLeftArm(1);
				avatar.rotateRightArm(-1);
				// System.out.println("(" + avatar.getX() + ", " + avatar.getY()
				// + ")");
				dx -= dxdy;
				// frame.refresh();
				sleep(1);
			}
		}
	}

	@Override
	public boolean isOccupied() {
		return occupied;
	}

	@Override
	public boolean isKnightTurn() {
		return occupied;
	}

	@Override
	public boolean preSay() {
		return occupied && guard.getY() < 600;
	}
	
	@Override
	public void say(String text) {
		assert preSay() : "Cannot talk unless knight area occupied and guard not failed";
		if (knightTurn) {
			guard.getText().setText("");
			occupyingKnight.getText().setText(text);
			// frame.refresh();
		} else {
			occupyingKnight.getText().setText("");
			guard.getText().setText(text);
			// frame.refresh();
		}
		knightTurn = !knightTurn;
	}

	@Override
	public boolean prePass() {
		return occupied && !knightTurn;
	}

	@Tags({ "pass" })
	@Override
	public void pass() {
		assert prePass() : "Cannot pass if knight area unoccupied or not guard's turn";
		knightTurn = false;
		occupyingKnight.getText().setText("");
		walkTo(occupyingKnight, 550, 200);
		walkTo(occupyingKnight, 750, 200);
		resolved.add(occupyingKnight);
		firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "approach", true));
		firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "say", false));
		firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "pass", false));
		firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "fail", false));
		firePropertyChangeEvent(new PropertyChangeEvent(this, "this", occupyingKnight.getHead().getAvatarName().name().toLowerCase(), false));
		occupyingKnight = null;
		occupied = false;
	}
	
	@Override
	public boolean preFail() {
		return occupied && guard.getY() < 600;
	}

	@Tags({ "fail" })
	@Override
	public void fail() {
		assert preFail() : "Cannot fail if the knight area is unoccupied or the guard has already failed";
		if (knightTurn == false) {
			occupyingKnight.getText().setText("");
			flail(occupyingKnight, 650, 200);
			flail(occupyingKnight, 650, 600);
			resolved.add(occupyingKnight);
			guard.getText().setText("");
			firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "say", false));
			firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "approach", true));
			firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "pass", false));
			firePropertyChangeEvent(new PropertyChangeEvent(this, "this", occupyingKnight.getHead().getAvatarName().name().toLowerCase(), false));
			occupyingKnight = null;
			occupied = false;
		} else {
			guard.getText().setText("");
			flail(guard, 650, 200);
			flail(guard, 650, 620);
			resolved.add(guard);
			occupyingKnight.getText().setText("");
			firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "guard", false));
		}
		firePropertyChangeEvent(new PropertyChangeEvent(this, "this", "fail", false));
		knightTurn = false;
	}
	
	@Override
	public void angleNeck(IAvatar avatar, double target, boolean clockwise) {
		double angle = avatar.getNeck().getAngle();

		if (clockwise) {
			if (angle > target) {
				angle -= 2 * Math.PI;
			}
			for (; angle < target; angle += Math.PI / 32) {
				avatar.angleNeck(angle);
				sleep(25);
			}
		} else {
			if (angle < target) {
				angle += 2 * Math.PI;
			}
			for (; angle > target; angle -= Math.PI / 32) {
				avatar.angleNeck(angle);
				sleep(25);
			}
		}
	}

	@Override
	public void angleBody(IAvatar avatar, double target, boolean clockwise) {
		double angle = avatar.getBody().getAngle();

		if (clockwise) {
			while (angle > target) {
				angle -= 2. * Math.PI;
			}
			for (; angle < target; angle += Math.PI / 32.) {
				avatar.angleBody(angle);
				sleep(50);
			}
		} else {
			while (angle < target) {
				// System.out.println(angle);
				angle += 2. * Math.PI;
			}
			for (; angle > target; angle -= Math.PI / 32.) {
				// System.out.println(angle);
				avatar.angleBody(angle);
				sleep(50);
			}
		}
	}

	@Override
	public void angleRightArm(IAvatar avatar, double target, boolean clockwise) {
		double angle = avatar.getArms().getRightLine().getAngle();

		if (clockwise) {
			while (angle > target) {
				angle -= 2. * Math.PI;
			}
			for (; angle < target; angle += Math.PI / 32.) {
				avatar.angleRightArm(angle);
				sleep(50);
			}
		} else {
			while (angle < target) {
				// System.out.println(angle);
				angle += 2. * Math.PI;
			}
			for (; angle > target; angle -= Math.PI / 32.) {
				// System.out.println(angle);
				avatar.angleRightArm(angle);
				sleep(25);
			}
		}
	}

	@Override
	public void angleLeftArm(IAvatar avatar, double target, boolean clockwise) {
		double angle = avatar.getArms().getLeftLine().getAngle();

		if (clockwise) {
			while (angle > target) {
				angle -= 2. * Math.PI;
			}
			for (; angle < target; angle += Math.PI / 32.) {
				avatar.angleLeftArm(angle);
				sleep(50);
			}
		} else {
			while (angle < target) {
				// System.out.println(angle);
				angle += 2. * Math.PI;
			}
			for (; angle > target; angle -= Math.PI / 32.) {
				// System.out.println(angle);
				avatar.angleLeftArm(angle);
				sleep(25);
			}
		}
	}

	@Override
	public void angleRightLeg(IAvatar avatar, double target, boolean clockwise) {
		double angle = avatar.getLegs().getRightLine().getAngle();

		if (clockwise) {
			while (angle > target) {
				angle -= 2. * Math.PI;
			}
			for (; angle < target; angle += Math.PI / 32.) {
				avatar.angleRightLeg(angle);
				sleep(50);
			}
		} else {
			while (angle < target) {
				// System.out.println(angle);
				angle += 2. * Math.PI;
			}
			for (; angle > target; angle -= Math.PI / 32.) {
				// System.out.println(angle);
				avatar.angleRightLeg(angle);
				sleep(25);
			}
		}
	}

	@Override
	public void angleLeftLeg(IAvatar avatar, double target, boolean clockwise) {
		double angle = avatar.getLegs().getLeftLine().getAngle();

		if (clockwise) {
			while (angle > target) {
				angle -= 2. * Math.PI;
			}
			for (; angle < target; angle += Math.PI / 32.) {
				avatar.angleLeftLeg(angle);
				sleep(50);
			}
		} else {
			while (angle < target) {
				// System.out.println(angle);
				angle += 2. * Math.PI;
			}
			for (; angle > target; angle -= Math.PI / 32.) {
				// System.out.println(angle);
				avatar.angleLeftLeg(angle);
				sleep(25);
			}
		}
	}

	@Override
	public void angleNeck(IAvatar avatar, int steps) {
		int dir = steps > 0 ? 1 : steps < 0 ? -1 : 0;
		steps = Math.abs(steps);
		for(int i = 0; i < steps; i ++) {
			avatar.rotateNeck(dir);
			sleep(25);
		}
	}

	@Override
	public void angleBody(IAvatar avatar, int steps) {
		int dir = steps > 0 ? 1 : steps < 0 ? -1 : 0;
		steps = Math.abs(steps);
		for(int i = 0; i < steps; i ++) {
			avatar.rotateBody(dir);
			sleep(50);
		}
	}

	@Override
	public void angleRightArm(IAvatar avatar, int steps) {
		int dir = steps > 0 ? 1 : steps < 0 ? -1 : 0;
		steps = Math.abs(steps);
		for(int i = 0; i < steps; i ++) {
			avatar.rotateRightArm(dir);
			sleep(50);
		}
	}

	@Override
	public void angleLeftArm(IAvatar avatar, int steps) {
		int dir = steps > 0 ? 1 : steps < 0 ? -1 : 0;
		steps = Math.abs(steps);
		for(int i = 0; i < steps; i ++) {
			avatar.rotateLeftArm(dir);
			sleep(50);
		}
	}

	@Override
	public void angleRightLeg(IAvatar avatar, int steps) {
		int dir = steps > 0 ? 1 : steps < 0 ? -1 : 0;
		steps = Math.abs(steps);
		for(int i = 0; i < steps; i ++) {
			avatar.rotateRightLeg(dir);
			sleep(25);
		}
	}

	@Override
	public void angleLeftLeg(IAvatar avatar, int steps) {
		int dir = steps > 0 ? 1 : steps < 0 ? -1 : 0;
		steps = Math.abs(steps);
		for(int i = 0; i < steps; i ++) {
			avatar.rotateLeftLeg(dir);
			sleep(25);
		}
	}

	@Override
	public void scaleAvatar(IAvatar avatar, double scaleFactor) {
		avatar.setSizeScale(scaleFactor);
	}
	
	private void firePropertyChangeEvent(PropertyChangeEvent evt) {
		pclm.firePropertyChange(evt);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pclm.addPropertyChangeListener(listener);
	}

	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
