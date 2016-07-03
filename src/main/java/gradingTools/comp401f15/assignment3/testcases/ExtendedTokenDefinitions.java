package gradingTools.comp401f15.assignment3.testcases;

import static gradingTools.comp401f15.assignment2.testcases.BasicTokenDefinitions.basicTokens;
import static gradingTools.comp401f15.assignment2.testcases.BasicTokenDefinitions.buildGroup;

import java.util.HashMap;
import java.util.Map;

import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.Project;
import gradingTools.comp401f15.assignment2.testcases.BasicTokenDefinitions;

/**
 *
 * @author Andrew Vitkus
 */
public class ExtendedTokenDefinitions {
    public static final String[] endTokenDescription = new String[]{null, "End", ".*End.*", ".*End.*"};
    public static final String[] startTokenDescription = new String[]{null, "Start", ".*Start.*", ".*Start.*"};
    
    public static final Map<String, Class> extendedTokenClassMap = new HashMap<>(7);
    
    private static Project project = null;
    
    public static void findTokens(Project p) {
        if (project == null) {
            project = p;
        } else {
            if (project.equals(p)) {
                return;
            } else {
                extendedTokenClassMap.clear();
            }
        }
        if (BasicTokenDefinitions.basicTokenClassMap.isEmpty()) {
            BasicTokenDefinitions.findTokens(p);
        }
        extendedTokenClassMap.putAll(BasicTokenDefinitions.basicTokenClassMap);
        for(String[] description : added()) {
            Class clazz = BasicProjectIntrospection.findClass(p, description[0],
                                                         description[1],
                                                         description[2],
                                                         description[3]);
            if (clazz != null) {
                extendedTokenClassMap.put(description[1], clazz);
            }
        }
    }
    
    public static String[][] extendedTokens() {
        return buildGroup(basicTokens(), added());
    }
    
    private static String[][] added() {
        return buildGroup(endTokenDescription, startTokenDescription);
    }
}
