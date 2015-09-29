package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class SleepCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "sleep";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "sleep";}
        
    public SleepCommandCreatedTestCase() {
        super("Sleep Command Token Created Test Case");
    }

    
}
