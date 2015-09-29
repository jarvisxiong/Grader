package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class RedoCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "redo";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "RedO";};
	protected Object value() {return "redo";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public RedoCommandBeanTestCase() {
        super("Redo Command Bean Case");
    }

   
}

