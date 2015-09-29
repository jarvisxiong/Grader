package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class RepeatCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "repeat";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "RePeAt";};
	protected Object value() {return "repeat";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public RepeatCommandBeanTestCase() {
        super("Repeat Command Bean Case");
    }

   
}

