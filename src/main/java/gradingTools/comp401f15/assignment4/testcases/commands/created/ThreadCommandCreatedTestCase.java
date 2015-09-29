package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class ThreadCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "thread";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "thread";}
        
    public ThreadCommandCreatedTestCase() {
        super("Thread Command Token Created Test Case");
    }

    
}
