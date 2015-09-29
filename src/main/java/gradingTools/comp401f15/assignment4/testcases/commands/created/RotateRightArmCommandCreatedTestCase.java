package gradingTools.comp401f15.assignment4.testcases.commands.created;


/**
 *
 * @author Andrew Vitkus
 */
public class RotateRightArmCommandCreatedTestCase extends AbstractCommandCreatedTestCase {

    public static final String TAG = "RotateRightArm";
    public String commandIdentifier() { return TAG;}
    protected String[] commandDescriptions() { return new String[] {null, TAG, ".*" + TAG + ".*", ".*" + TAG + ".*"};};
    protected String commandName() { return "rotateRightArm";}
        
    public RotateRightArmCommandCreatedTestCase() {
        super("Rotate Right Arm Command Token Created Test Case");
    }

    
}
