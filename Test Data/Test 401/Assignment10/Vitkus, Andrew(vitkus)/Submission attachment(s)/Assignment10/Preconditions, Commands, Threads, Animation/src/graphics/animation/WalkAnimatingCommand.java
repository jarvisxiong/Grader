package graphics.animation;

import graphics.avatar.IAvatar;
import graphics.shapes.IRotatingLine;

import util.annotations.Tags;

@Tags({ "animating command" })
public class WalkAnimatingCommand implements IWalkAnimatingCommand {
	private IAvatar avatar;
	private int idx, idy;
	
	private static final int DELAY = 15;
	
	public WalkAnimatingCommand(IAvatar avatar, int idx, int idy) {
		this.avatar = avatar;
		this.idx = idx;
		this.idy = idy;
	}

	@Override
	public void run() {
		double dx = idx;
		double dy = idy;
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
				sleep(DELAY);
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
				sleep(DELAY);
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
				sleep(DELAY);
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
				sleep(DELAY);
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
				sleep(DELAY);
			}
		}
	}


	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
