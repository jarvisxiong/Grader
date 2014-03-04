package grader.settings;

import util.annotations.Visible;
import util.models.LabelBeanModel;

public interface LabelSetterModel {
	public void change();
	public LabelBeanModel getLabel() ;
	public String getText();
}
