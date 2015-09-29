package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class ProceedAllCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "proceedAll";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "PROCEEDall";};
	protected Object value() {return "proceedall";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public ProceedAllCommandBeanTestCase() {
        super("Proceed All Command Bean Case");
    }

   
}

