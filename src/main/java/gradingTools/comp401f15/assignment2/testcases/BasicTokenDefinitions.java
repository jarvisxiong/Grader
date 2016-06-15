package gradingTools.comp401f15.assignment2.testcases;

import java.util.HashMap;
import java.util.Map;

import framework.project.Project;
import grader.util.IntrospectionUtil;

/**
 *
 * @author Andrew Vitkus
 */
public class BasicTokenDefinitions {
    public static final String[] minusTokenDescription = new String[]{null, "Minus", ".*Minus.*", ".*Minus.*"};
    public static final String[] numberTokenDescription = new String[]{null, "Number", ".*Number.*", ".*Number.*"};
    public static final String[] plusTokenDescription = new String[]{null, "Plus", ".*Plus.*", ".*Plus.*"};
    public static final String[] quoteTokenDescription = new String[]{null, "Quote", ".*Quote.*", ".*Quote.*"};
    public static final String[] wordTokenDescription = new String[]{null, "Word", ".*Word.*", ".*Word.*"};
    
    public static final Map<String, Class> basicTokenClassMap = new HashMap<>(5);
    
    private static Project project = null;
    
    public static void findTokens(Project p) {
        if (project == null) {
            project = p;
        } else {
            if (project.equals(p)) {
                return;
            } else {
                basicTokenClassMap.clear();
            }
        }
        for(String[] description : basicTokens()) {
            Class clazz = IntrospectionUtil.findClass(p, description[0],
                                                         description[1],
                                                         description[2],
                                                         description[3]);
            if (clazz != null) {
                basicTokenClassMap.put(description[1], clazz);
            }
        }
    }
    
    public static String[][] buildGroup(String[]... arrs) {
        return arrs;
    }
    
    public static String[][] buildGroup(String[][] base, String[]... add) {
        String[][] ret = new String[base.length + add.length][base[0].length];
        return ret;
    }
    
    public static String[][] basicTokens() {
        return buildGroup(minusTokenDescription, numberTokenDescription, plusTokenDescription,
                quoteTokenDescription, wordTokenDescription);
    }
}
