package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class RotateRightArmCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "rotateRightArm";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "rOtAtErIgHtArM";};
	protected Object value() {return "rotaterightarm";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public RotateRightArmCommandBeanTestCase() {
        super("Rotate Right Arm Command Bean Case");
    }

   
}

