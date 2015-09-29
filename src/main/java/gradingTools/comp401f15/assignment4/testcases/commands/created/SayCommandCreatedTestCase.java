package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class SayCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "say";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "say";}
        
    public SayCommandCreatedTestCase() {
        super("Say Command Token Created Test Case");
    }

    
}
