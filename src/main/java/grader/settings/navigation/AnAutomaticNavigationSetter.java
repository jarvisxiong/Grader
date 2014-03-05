package grader.settings.navigation;

import bus.uigen.ObjectEditor;

public class AnAutomaticNavigationSetter implements AutomaticNavigationSetter{
	boolean animateGrades;
	int animationPauseTime = 1000;
	public boolean getAnimateGrades() {
		return animateGrades;
	}
	public void setAnimateGrades(boolean animateGrades) {
		this.animateGrades = animateGrades;
	}
	public int getAnimationPauseTime() {
		return animationPauseTime;
	}
	public void setAnimationPauseTime(int animationPauseTime) {
		this.animationPauseTime = animationPauseTime;
	}
	
	public static void main (String[] args) {
		AutomaticNavigationSetter navigationSetter = new AnAutomaticNavigationSetter();
		ObjectEditor.edit(navigationSetter);
	}
	

}
