package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class UndoCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "undo";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "undo";}
        
    public UndoCommandCreatedTestCase() {
        super("Undo Command Token Created Test Case");
    }

    
}
