package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class MoveCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "move";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "MoVe";};
	protected Object value() {return "move";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public MoveCommandBeanTestCase() {
        super("Move Command Bean Case");
    }

   
}

