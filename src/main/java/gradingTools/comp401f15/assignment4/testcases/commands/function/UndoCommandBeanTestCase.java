package gradingTools.comp401f15.assignment4.testcases.commands.function;

import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class UndoCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "undo";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "uNDo";};
	protected Object value() {return "undo";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public UndoCommandBeanTestCase() {
        super("Undo Command Bean Case");
    }

   
}

