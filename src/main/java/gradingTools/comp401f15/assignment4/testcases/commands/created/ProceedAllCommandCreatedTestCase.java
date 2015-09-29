package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class ProceedAllCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "ProceedAll";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "proceedAll";}
        
    public ProceedAllCommandCreatedTestCase() {
        super("Proceed All Command Token Created Test Case");
    }

    
}
