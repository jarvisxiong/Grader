package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class RotateLeftArmCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "RotateLeftArm";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "rotateLeftArm";}
        
    public RotateLeftArmCommandCreatedTestCase() {
        super("Rotate Left Arm Command Token Created Test Case");
    }

    
}
