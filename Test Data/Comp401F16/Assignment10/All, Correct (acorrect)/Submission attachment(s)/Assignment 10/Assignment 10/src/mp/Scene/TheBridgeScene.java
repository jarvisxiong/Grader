package mp.Scene;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mp.APropertyListenerSupport;
import mp.PropertyListenerSupport;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@Tags({"BridgeScene"})
@PropertyNames({"arthur","galahad","robin","lancelot","guard","gorge","knightturn","occupied","knightarea","guardarea"}) 

public class TheBridgeScene implements BridgeScene{
	final String arthur1="arthur.jpg";
	final String galahad1="galahad.jpg";
	final String guard1="guard.jpg";
	final String lancelot1="lancelot.jpg";
	final String robin1="robin.jpg"; 
	final int five2five= 525;
	final int two00= 200;
	final int six0= 60;
	final int thirty=30;
	final int onethirty=130;
	final int twothirty=230;
	final int threethirty=330;
	final int six00=600;
	final int three10=310;
	final int eighty=80;
	final int five00=500;
	final int threefifty=350;
	final int three75=375;
	final int five=5;
	final int three=3;
	final int yibai=100;
	
	PropertyListenerSupport aListener = new APropertyListenerSupport();
	
	boolean occupied=false;
	boolean knightTurn=false;
	boolean oldPA=preApproach();
	boolean oldPS=preSayScene();
	boolean oldPF=preFailedScene();
	boolean oldPP=prePassedScene();
	
	Avatar xMan;
	
	Avatar guard= new AnAvatar("", guard1, five2five,two00,six0);
	
	Avatar arthur= new AnAvatar("", arthur1, thirty,thirty,six0);
	Avatar galahad= new AnAvatar("", galahad1, onethirty,thirty,six0);
	Avatar lancelot= new AnAvatar("", lancelot1, twothirty,thirty,six0);
	Avatar robin= new AnAvatar("", robin1, threethirty,thirty,six0);
	
	Gorge gorge= new TheGorge();
	BoundedShape guardArea= new AOval(five00,three10,eighty,eighty);
	BoundedShape knightArea= new AOval(threefifty,three10,eighty,eighty);
	
	@Tags({"gorge"})
	public Gorge getGorge(){
		return gorge;
	}
	
	@Tags({"scroll"})
	public void scroll(int deltaX, int deltaY){
		gorge.getBeidge().setX(gorge.getBeidge().getX()+deltaX);
		gorge.getBeidge().setY(gorge.getBeidge().getY()+deltaY);
		gorge.getG1().setX(gorge.getG1().getX()+deltaX);
		gorge.getG1().setY(gorge.getG1().getY()+deltaY);
		gorge.getG2().setX(gorge.getG2().getX()+deltaX);
		gorge.getG2().setY(gorge.getG2().getY()+deltaY);
		guardArea.setX(guardArea.getX()+deltaX);
		guardArea.setY(guardArea.getY()+deltaY);
		knightArea.setX(knightArea.getX()+deltaX);
		knightArea.setY(knightArea.getY()+deltaY);
		guard.moveThis(deltaX,deltaY);
		arthur.moveThis(deltaX,deltaY);
		galahad.moveThis(deltaX,deltaY);
		lancelot.moveThis(deltaX,deltaY);
		robin.moveThis(deltaX,deltaY);	
	}
	
	@Tags({"say"})
	public void sayScene(String saying){
			assert preSayScene();
			if(!getKnightTurn()){
				xMan.setText("");
				guard.setText(saying);
				knightTurn=true;	
				checkAndNotifyChange();
			}
			else if(getKnightTurn()){
				guard.setText("");
				xMan.setText(saying);
				knightTurn=false;
				checkAndNotifyChange();
			}
			
	}
	public boolean preSayScene(){
		return getOccupied();
	}
	
	
	public boolean getKnightTurn(){
		return knightTurn;
	}
	
	public boolean getOccupied(){
		return occupied;
	}
	
	@Tags({"passed"})
	public void passedScene(){
		assert prePassedScene();
		xMan.moveThis(six00+six0, 0);
		occupied=false;
		checkAndNotifyChange();
		knightTurn=false;
		checkAndNotifyChange();
	}
	public boolean prePassedScene(){
		return !getKnightTurn()&&getOccupied();
	}
	
	@Tags({"failed"})
	public void failedScene(){
			assert preFailedScene();
			if(!getKnightTurn()){
				xMan.moveThis(2*two00, -two00);
				occupied=false;
				checkAndNotifyChange();
				knightTurn=false;
				checkAndNotifyChange();
			}
			else if(getKnightTurn()){
				guard.moveThis(2*two00, -yibai);
			}
			
	}
	public boolean preFailedScene(){
		return getOccupied();
	}
	
	public void pass1(){
		xMan.moveThis(three, 0);
	}
	public void fail1(){
		xMan.moveThis(five, -1);
	}
	
	@Tags({"approach"})
	public void approach(Avatar goo){
		assert preApproach();
		goo.flashThis(three75,two00);
		occupied=true;
		checkAndNotifyChange();
		xMan=goo;
		knightTurn=false;
		checkAndNotifyChange();
	}
	public boolean preApproach(){
		return !getOccupied();
	}
	
	public void checkAndNotifyChange(){
		if(preApproach()!=oldPA){
			aListener.notifyAllListeners(new PropertyChangeEvent (this, "this", "approach", preApproach()));
			oldPA=preApproach();
		}
		if(preSayScene()!=oldPS){
			aListener.notifyAllListeners(new PropertyChangeEvent (this, "this", "say", preSayScene()));
			oldPS=preSayScene();
		}
		if(prePassedScene()!=oldPP){
			aListener.notifyAllListeners(new PropertyChangeEvent (this, "this", "pass", prePassedScene()));
			oldPP=prePassedScene();
		}
		if(preFailedScene()!=oldPF){
			aListener.notifyAllListeners(new PropertyChangeEvent (this, "this", "fail", preFailedScene()));
			oldPF=preFailedScene();
		}
	}
	
	@Tags({"KnightArea"})
	public BoundedShape getKnightArea(){
		return knightArea;
	}
	@Tags({"GuardArea"})
	public BoundedShape getGuardArea(){
		return guardArea;
	}

	public Avatar getArthur(){
		return arthur;
	}
	public Avatar getGalahad(){
		return galahad;
	}
	public Avatar getGuard(){
		return guard;
	}
	public Avatar getLancelot(){
		return lancelot;
	}
	public Avatar getRobin(){
		return robin;
	}
	
	public static void addPropertyChangeListener(BridgeScene bb,PropertyChangeListener arg0){
		AnAvatar.addPropertyChangeListener(bb.getArthur(), arg0);
		AnAvatar.addPropertyChangeListener(bb.getGalahad(), arg0);
		AnAvatar.addPropertyChangeListener(bb.getRobin(), arg0);
		AnAvatar.addPropertyChangeListener(bb.getLancelot(), arg0);
		AnAvatar.addPropertyChangeListener(bb.getGuard(), arg0);
		bb.getGuardArea().addPropertyChangeListener(arg0);
		bb.getKnightArea().addPropertyChangeListener(arg0);
		TheGorge.addPropertyChangeListener(bb.getGorge(), arg0);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		aListener.add(arg0);
	}
	
	
	
}
