package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class CallCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "call";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "call";}
        
    public CallCommandCreatedTestCase() {
        super("Call Command Token Created Test Case");
    }

    
}
