package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class ThreadCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "thread";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "tHReaD";};
	protected Object value() {return "thread";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public ThreadCommandBeanTestCase() {
        super("Thread Command Bean Case");
    }

   
}

