package grader.settings.navigation;

import util.annotations.Column;

public interface AutomaticNavigationSetter {
	public boolean getAnimateGrades() ;
	
	public void setAnimateGrades(boolean animateGrades) ;
	public int getAnimationPauseTime() ;
	public void setAnimationPauseTime(int animationPauseTime) ;

}
