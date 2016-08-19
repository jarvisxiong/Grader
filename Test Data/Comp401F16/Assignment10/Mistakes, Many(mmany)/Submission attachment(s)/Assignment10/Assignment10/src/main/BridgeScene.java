package main;

import mp.shapes.AreaShape;
import mp.shapes.Avatar;
import mp.shapes.Gorge;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.annotations.Visible;
import util.models.PropertyListenerRegisterer;

@Tags({"BridgeScene"})
@StructurePattern("Bean Pattern")
@PropertyNames({"arthur", "lancelot", "robin", "galahad", "guard", "knightArea", "gorge", "guardArea", "knightTurn", "occupied"})

public interface BridgeScene extends PropertyListenerRegisterer{
	public Avatar getArthur();
	public Avatar getLancelot();
	public Avatar getRobin();
	public Avatar getGalahad();
	public Avatar getGuard();
	public AreaShape getKnightArea();
	public AreaShape getGuardArea();
	public Gorge getGorge();
	
	public boolean getKnightTurn();
	public boolean getOccupied();
	
	@Visible(false)
	public Avatar getCurrentKnight();
	
	@Tags({"approach"})
	public void approach(Avatar knight);
	
	@Tags({"say"})
	public void say(String text);
	
	@Tags({"passed"})
	public void passed();
	
	@Tags({"failed"})
	public void failed();
	
	@Tags({"scroll"})
	public void scroll(int xOffset, int yOffset);
	
	public boolean preFailed();
	public boolean preApproach();
	public boolean prePassed();
	public boolean preSay();
	
	public void reset();
}
