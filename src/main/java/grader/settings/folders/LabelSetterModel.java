package grader.settings.folders;

import util.annotations.Visible;
import util.models.LabelBeanModel;

public interface LabelSetterModel {
	public void change();
	public LabelBeanModel getLabel() ;
	public String getText();
	void setText(String newValue);
}
