package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class RepeatCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "repeat";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "repeat";}
        
    public RepeatCommandCreatedTestCase() {
        super("Repeat Command Token Created Test Case");
    }

    
}
