package grader.trace.settings;

import java.util.Date;

import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class AnimationPauseTimeUserChange extends GraderSettingsInfo {
	
	int animationPauseTime;
	
	
	public AnimationPauseTimeUserChange(String aMessage, int aAnimationPauseTime, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		super(aMessage, aGradingSettingsModel, aFinder);
		animationPauseTime = aAnimationPauseTime;
//		 gradingSettingsModel = aGradingSettingsModel;
	}
	public int getAnimationPauseTime() {
		return animationPauseTime;
	}
	public void setAnimationPauseTime(int animationPauseTime) {
		this.animationPauseTime = animationPauseTime;
	}
	@Override
	public String toCSVRow() {
		return super.toCSVRow() + "," + animationPauseTime;
	}
	
	
	public static AnimationPauseTimeUserChange newCase(int aAnimationPauseTime, GraderSettingsModel aGradingSettingsModel, Object aFinder) {
		String aMessage = "AnimationPauseTime Changed:" + aAnimationPauseTime;
		AnimationPauseTimeUserChange retVal = new AnimationPauseTimeUserChange(aMessage, aAnimationPauseTime,  aGradingSettingsModel, aFinder);
		retVal.announce();		
		return retVal;
	}

}
