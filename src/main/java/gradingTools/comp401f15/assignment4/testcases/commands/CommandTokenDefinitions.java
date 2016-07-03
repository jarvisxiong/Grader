package gradingTools.comp401f15.assignment4.testcases.commands;

import static gradingTools.comp401f15.assignment2.testcases.BasicTokenDefinitions.buildGroup;

import java.util.HashMap;
import java.util.Map;

import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.Project;

/**
 *
 * @author Andrew Vitkus
 */
public class CommandTokenDefinitions {
    public static final String[] callCommandTokenDescription = new String[]{null, "call", ".*call.*", ".*call.*"};
    public static final String[] defineCommandTokenDescription = new String[]{null, "define", ".*define.*", ".*define.*"};
    public static final String[] moveCommandTokenDescription = new String[]{null, "move", ".*move.*", ".*move.*"};
    public static final String[] proceedAllCommandTokenDescription = new String[]{null, "proceedAll", ".*proceedAll.*", ".*proceedAll.*"};
    public static final String[] redoCommandTokenDescription = new String[]{null, "redo", ".*redo.*", ".*redo.*"};
    public static final String[] repeatCommandTokenDescription = new String[]{null, "repeat", ".*repeat.*", ".*repeat.*"};
    public static final String[] rotateLeftArmCommandTokenDescription = new String[]{null, "rotateLeftArm", ".*rotateLeftArm.*", ".*rotateLeftArm.*"};
    public static final String[] rotateRightArmCommandTokenDescription = new String[]{null, "rotateRightArm", ".*rotateRightArm.*", ".*rotateRightArm.*"};
    public static final String[] sayCommandTokenDescription = new String[]{null, "say", ".*say.*", ".*say.*"};
    public static final String[] sleepCommandTokenDescription = new String[]{null, "sleep", ".*sleep.*", ".*sleep.*"};
    public static final String[] threadCommandTokenDescription = new String[]{null, "thread", ".*thread.*", ".*thread.*"};
    public static final String[] undoCommandTokenDescription = new String[]{null, "undo", ".*undo.*", ".*undo.*"};
    public static final String[] waitCommandTokenDescription = new String[]{null, "wait", ".*wait.*", ".*wait.*"};
    
    public static final Map<String, Class> commandTokenClassMap = new HashMap<>(13);
    private static Project project = null;
    
    public static void findTokens(Project p) {
        if (project == null) {
            project = p;
        } else {
            if (project.equals(p)) {
                return;
            } else {
                commandTokenClassMap.clear();
            }
        }
        for(String[] description : baseCommandTokens()) {
            Class clazz = BasicProjectIntrospection.findClass(p, description[0],
                                                         description[1],
                                                         description[2],
                                                         description[3]);
            if (clazz != null) {
                commandTokenClassMap.put(description[1], clazz);
            }
        }
    }
    
    public static String[][] baseCommandTokens() {
        return buildGroup(callCommandTokenDescription, defineCommandTokenDescription, moveCommandTokenDescription,
                proceedAllCommandTokenDescription, redoCommandTokenDescription, repeatCommandTokenDescription,
                rotateLeftArmCommandTokenDescription, rotateRightArmCommandTokenDescription, sayCommandTokenDescription,
                sleepCommandTokenDescription, threadCommandTokenDescription, undoCommandTokenDescription, waitCommandTokenDescription);
    }
}
