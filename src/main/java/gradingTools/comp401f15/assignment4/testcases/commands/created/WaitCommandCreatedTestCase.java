package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class WaitCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "wait";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "wait";}
        
    public WaitCommandCreatedTestCase() {
        super("Wait Command Token Created Test Case");
    }

    
}
