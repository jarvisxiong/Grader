package gradingTools.comp401f15.assignment6.testcases.commands;

import gradingTools.comp401f15.assignment4.testcases.commands.function.*;
import gradingTools.comp401f15.assignment3.testcases.AbstractTokenBeanTestCase;


public class ApproachCommandBeanTestCase extends AbstractTokenBeanTestCase {
	public static final String TAG = "approach";
	public  String classIdentifier() { return TAG;}
	protected String[] beanDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
	
	protected String input(){ return "APPROACH";};
	protected Object value() {return "approach";};
	protected String inputPropertyName() { return "Input";};
	protected String outputPropertyName() { return "Value";};

    public ApproachCommandBeanTestCase() {
        super("Approach Command Bean Case");
    }

   
}

