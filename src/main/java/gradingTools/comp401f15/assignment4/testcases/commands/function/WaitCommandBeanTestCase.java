package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class WaitCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "wait";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "WAIT";};
	protected Object value() {return "wait";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public WaitCommandBeanTestCase() {
        super("Wait Command Bean Case");
    }

   
}

