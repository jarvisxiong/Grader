package grader.settings.navigation;

import grader.settings.GraderSettingsModel;
import util.annotations.Column;
import util.annotations.Explanation;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import bus.uigen.ObjectEditor;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class AnAutomaticNavigationSetter implements AutomaticNavigationSetter{
	boolean animateGrades;
	int animationPauseTime = 1;
	GraderSettingsModel graderSettings;
	public AnAutomaticNavigationSetter(GraderSettingsModel aGraderSettings) {
		graderSettings = aGraderSettings;
	}
	public boolean preGetAnimateGrades() {
		return graderSettings == null || !graderSettings.isGraderStarted();
	}
//	@Row(0)
	@Column(0)
	@Override
	@Explanation("Should the student records be displayed during automatic navigation")
	public boolean getAnimateGrades() {
		return animateGrades;
	}
	@Override
	public void setAnimateGrades(boolean animateGrades) {
		this.animateGrades = animateGrades;
	}
//	@Row(1)
	@Column(1)
	@Override
	@Explanation("Time, in seconds, to wait, before automatically moving to the next student record")
	public int getAnimationPauseTime() {
		return animationPauseTime;
	}
	@Override
	public void setAnimationPauseTime(int animationPauseTime) {
		this.animationPauseTime = animationPauseTime;
	}
	
	public static void main (String[] args) {
		AutomaticNavigationSetter navigationSetter = new AnAutomaticNavigationSetter(null);
		ObjectEditor.edit(navigationSetter);
	}
	

}
