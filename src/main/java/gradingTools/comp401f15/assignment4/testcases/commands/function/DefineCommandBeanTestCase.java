package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class DefineCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "define";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "dEfInE";};
	protected Object value() {return "define";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public DefineCommandBeanTestCase() {
        super("Define Command Bean Case");
    }

   
}

