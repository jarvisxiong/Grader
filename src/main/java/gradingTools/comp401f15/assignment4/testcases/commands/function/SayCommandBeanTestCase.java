package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class SayCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "say";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "sAy";};
	protected Object value() {return "say";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public SayCommandBeanTestCase() {
        super("Say Command Bean Case");
    }

   
}

