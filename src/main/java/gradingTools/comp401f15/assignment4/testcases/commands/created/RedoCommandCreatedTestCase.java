package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class RedoCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "redo";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "redo";}
        
    public RedoCommandCreatedTestCase() {
        super("Redo Command Token Created Test Case");
    }

    
}
