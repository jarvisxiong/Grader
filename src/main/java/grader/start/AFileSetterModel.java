package grader.start;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import util.annotations.Column;
import util.annotations.ComponentHeight;
import util.annotations.ComponentWidth;
import util.models.LabelBeanModel;

public class AFileSetterModel extends ALabelSetterModel implements FileSetterModel {
	
	JFileChooser fileChooser = new JFileChooser();
	JFrame frame;
	public AFileSetterModel () {
//		frame = (JFrame) aFrame.getFrame().getPhysicalComponent();
	}
	
	public void initFrame(JFrame aFrame) {
		frame = aFrame;
	}

	@Override
	@Column(2)
//	@ComponentWidth(80)
	@ComponentHeight(25)
	public void change() {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            setText( fileChooser.getSelectedFile().getAbsolutePath());
        }
		
	}
	
	public static void main (String[] args) {
		AFileSetterModel aFileSetterModel = new AFileSetterModel();
		OEFrame oeFrame = ObjectEditor.edit(aFileSetterModel);
		aFileSetterModel.initFrame((JFrame) oeFrame.getFrame().getPhysicalComponent());
		oeFrame.setSize(600,  200);
	}

}
