package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class SleepCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "sleep";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "SLEEp";};
	protected Object value() {return "sleep";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public SleepCommandBeanTestCase() {
        super("Sleep Command Bean Case");
    }

   
}

