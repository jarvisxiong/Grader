package gradingTools.comp401f15.assignment5.testcases.commands;

import static gradingTools.comp401f15.assignment2.testcases.BasicTokenDefinitions.buildGroup;
import static gradingTools.comp401f15.assignment4.testcases.commands.CommandTokenDefinitions.baseCommandTokens;

import java.util.HashMap;
import java.util.Map;

import framework.project.Project;
import grader.util.IntrospectionUtil;
import gradingTools.comp401f15.assignment4.testcases.commands.CommandTokenDefinitions;

/**
 *
 * @author Andrew Vitkus
 */
public class ExtendedCommandTokenDefinitions {
    public static final String[] approachCommandTokenDescription = new String[]{null, "approach", ".*approach.*", ".*approach.*"};
    
    public static final Map<String, Class> extendedCommandTokenClassMap = new HashMap<>(13);
    private static Project project = null;
    
    public static void findTokens(Project p) {
        if (project == null) {
            project = p;
        } else {
            if (project.equals(p)) {
                return;
            } else {
                extendedCommandTokenClassMap.clear();
            }
        }
        if (CommandTokenDefinitions.commandTokenClassMap.isEmpty()) {
            CommandTokenDefinitions.findTokens(p);
        }
        extendedCommandTokenClassMap.putAll(CommandTokenDefinitions.commandTokenClassMap);
        for(String[] description : added()) {
            Class clazz = IntrospectionUtil.findClass(p, description[0],
                                                         description[1],
                                                         description[2],
                                                         description[3]);
            if (clazz != null) {
                extendedCommandTokenClassMap.put(description[1], clazz);
            }
        }
    }
    
    public static String[][] extendedCommandTokens() {
        return buildGroup(baseCommandTokens(), added());
    }
    
    private static String[][] added() {
        return buildGroup(approachCommandTokenDescription);
    }
}
