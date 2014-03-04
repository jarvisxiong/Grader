package grader.settings;

import util.annotations.Column;
import util.annotations.ComponentHeight;
import util.annotations.ComponentWidth;
import util.annotations.Visible;
import util.models.ALabelBeanModel;
import util.models.LabelBeanModel;

public abstract class ALabelSetterModel implements LabelSetterModel{
	LabelBeanModel labelModel = new ALabelBeanModel(" A Label", null);
	String text;
	
	
	 void setText(String newValue) {		 
		labelModel.set(newValue, null);
	}
	
//	@ComponentWidth(350)
	@ComponentHeight(25)
	@Column(1)
	public LabelBeanModel getLabel() {
		return labelModel;
	}
	@Visible(false)
	public String getText() {
		return labelModel.getText();
	}

}
