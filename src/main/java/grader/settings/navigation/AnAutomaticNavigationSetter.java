package grader.settings.navigation;

import util.annotations.Column;
import util.annotations.Row;
import bus.uigen.ObjectEditor;

public class AnAutomaticNavigationSetter implements AutomaticNavigationSetter{
	boolean animateGrades;
	int animationPauseTime = 1000;
//	@Row(0)
	@Column(0)

	public boolean getAnimateGrades() {
		return animateGrades;
	}
	public void setAnimateGrades(boolean animateGrades) {
		this.animateGrades = animateGrades;
	}
//	@Row(1)
	@Column(1)
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
