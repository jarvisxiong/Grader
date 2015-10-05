package grail.graphics;

import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.IsAtomicShape;
import util.annotations.PropertyNames;

@Tags({"BridgeScene"})
@IsAtomicShape(false)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Arthur", "Lancelot", "Robin", "Galahad", "Guard", "KnightArea", 
	"GuardArea", "Gorge", "KnightTurn", "Occupied"})
public class ABridgeScene implements BridgeScene {
	private static final int LANCELOT_X = 25;
	private static final int LANCELOT_Y = 200;
	private static final int ROBIN_X = 200;
	private static final int ROBIN_Y = 70;
	private static final int GALAHAD_X = 250;
	private static final int GALAHAD_Y = 220;
	private static final int GUARD_X = 870;
	private static final int GUARD_Y = 150;
	private static final int KNIGHT_AREA_X = 570;
	private static final int KNIGHT_AREA_Y = 270;
	private static final int GUARD_AREA_X = 850;
	private static final int GUARD_AREA_Y = 270;
	private static final int STAND_AREA = 90; //Circle size
	private static final int KNIGHT_X_ADJUSTMENT = 20;   //These are used to position the knight in
	private static final int KNIGHT_Y_ADJUSTMENT = -120;   //  the Knight Area, given the head is their x, y
	private static final int PASS_X_MOVE = 900;  //Used to move knight over bridge
	private static final int FAIL_KNIGHT_MOVE = 500;   //Both used to move avatars into gorge
	private static final int FAIL_GUARD_X_MOVE = 200;  // " "
	
	private Avatar arthur;
	private Avatar lancelot;
	private Avatar robin;
	private Avatar galahad;
	private Avatar guard;
	private ImageShape gorge;
	private Oval knightArea;
	private Oval guardArea;
	private boolean occupied;
	private boolean knightTurn;
	private Avatar currentKnight;
	
	public ABridgeScene() {
		arthur = new AnAvatar("arthur.jpg");
		lancelot = new AnAvatar("lancelot.jpg", LANCELOT_X, LANCELOT_Y);
		robin = new AnAvatar("robin.jpg", ROBIN_X, ROBIN_Y);
		galahad = new AnAvatar("galahad.jpg", GALAHAD_X, GALAHAD_Y);
		guard = new AnAvatar("guard.jpg", GUARD_X, GUARD_Y);
		knightArea = new AnOval(KNIGHT_AREA_X, KNIGHT_AREA_Y, STAND_AREA, STAND_AREA);
		guardArea = new AnOval(GUARD_AREA_X, GUARD_AREA_Y, STAND_AREA, STAND_AREA);
		gorge = new AShapeImage("gorge.jpg", 0, 0);
		occupied = false;
		knightTurn = false;
	}
	public ImageShape getGorge() { return gorge; }
	public Avatar getArthur() { return arthur; }
	public Avatar getLancelot() { return lancelot; }
	public Avatar getRobin() { return robin; }
	public Avatar getGalahad() { return galahad; }
	public Avatar getGuard() { return guard; }
	public Oval getKnightArea() { return knightArea; }
	public Oval getGuardArea() { return guardArea; }
	public boolean getOccupied() { return occupied; }
	public boolean getKnightTurn() { return knightTurn; }
	
	@Tags({"approach"})
	public void approach(Avatar newKnight) {
		newKnight.move(KNIGHT_AREA_X - newKnight.getX() + KNIGHT_X_ADJUSTMENT, 
							KNIGHT_AREA_Y - newKnight.getY() + KNIGHT_Y_ADJUSTMENT);
		currentKnight = newKnight;
		occupied = true;
	}
	
	@Tags({"say"})
	public void say(String newSpeech) {
		if (occupied) {
			if (!knightTurn) {
				guard.setText(newSpeech);
				currentKnight.setText("");
				knightTurn = true;
			}
			else {
				currentKnight.setText(newSpeech);
				guard.setText("");
				knightTurn = false;
			}
		}
	}
	
	@Tags({"passed"})
	public void passed() {
		guard.setText("");
		currentKnight.setText("");
		if (!knightTurn) {
			currentKnight.move(PASS_X_MOVE, 0);
			occupied = false;
			currentKnight = null;
		}
	}
	
	@Tags({"failed"}) 
	public void failed() {
		if (knightTurn) {
			currentKnight.move(FAIL_KNIGHT_MOVE, FAIL_KNIGHT_MOVE);
			guard.setText("");
			occupied = false;
			currentKnight = null;
		}
		else {
			guard.move(FAIL_GUARD_X_MOVE, FAIL_KNIGHT_MOVE);
		}
	}
}
