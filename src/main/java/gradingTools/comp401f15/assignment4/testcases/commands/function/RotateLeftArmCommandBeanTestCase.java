package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class RotateLeftArmCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "rotateLeftArm";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "rOtAtElEfTaRm";};
	protected Object value() {return "rotateleftarm";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public RotateLeftArmCommandBeanTestCase() {
        super("Rotate Left Arm Command Bean Case");
    }

   
}

