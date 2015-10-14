package gradingTools.comp401f15.assignment6.commands;

import gradingTools.comp401f15.assignment4.testcases.commands.created.*;


/**
 *
 * @author Andrew Vitkus
 */
public class ApproachCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "approach";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "call";}
        
    public ApproachCommandCreatedTestCase() {
        super("Approach Command Token Created Test Case");
    }

    
}
