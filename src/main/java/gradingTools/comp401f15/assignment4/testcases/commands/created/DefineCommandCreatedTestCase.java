package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class DefineCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "define";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "define";}
        
    public DefineCommandCreatedTestCase() {
        super("Define Command Token Created Test Case");
    }

    
}
