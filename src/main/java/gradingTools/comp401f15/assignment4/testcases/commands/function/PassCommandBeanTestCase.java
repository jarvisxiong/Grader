package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class PassCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "pass";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "PASS";};
	protected Object value() {return "pass";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public PassCommandBeanTestCase() {
        super("Pass Command Bean Case");
    }

   
}

