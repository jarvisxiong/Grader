package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class MoveCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "move";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "move";}
        
    public MoveCommandCreatedTestCase() {
        super("Move Command Token Created Test Case");
    }

    
}
