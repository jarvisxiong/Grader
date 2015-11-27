package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class FailCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "fail";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "FAIL";};
	protected Object value() {return "fail";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public FailCommandBeanTestCase() {
        super("Fail Command Bean Case");
    }

   
}

