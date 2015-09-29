package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class CallCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "call";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "CAll";};
	protected Object value() {return "call";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public CallCommandBeanTestCase() {
        super("Call Command Bean Case");
    }

   
}

