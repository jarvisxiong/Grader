package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class FailCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "fail";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "fail";}
        
    public FailCommandCreatedTestCase() {
        super("Fail Command Token Created Test Case");
    }

    
}
