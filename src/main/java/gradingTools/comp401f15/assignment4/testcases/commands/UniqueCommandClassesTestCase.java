package gradingTools.comp401f15.assignment4.testcases.commands;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;
import java.util.HashSet;

/**
 *
 * @author Andrew
 */
public class UniqueCommandClassesTestCase extends BasicTestCase {
    
    private static final int CLASS_COUNT = 13;
    private static final String[][] tokenDescriptions = new String[][]{{null, "call", ".*call.*", ".*call.*"},
                                                                       {null, "define", ".*define.*", ".*define.*"},
                                                                       {null, "move", ".*move.*", ".*move.*"},
                                                                       {null, "proceedAll", ".*proceedAll.*", ".*proceedAll.*"},
                                                                       {null, "redo", ".*redo.*", ".*redo.*"},
                                                                       {null, "repeat", ".*repeat.*", ".*repeat.*"},
                                                                       {null, "rotateLeftArm", ".*rotateLeftArm.*", ".*rotateLeftArm.*"},
                                                                       {null, "rotateRightArm", ".*rotateRightArm.*", ".*rotateRightArm.*"},
                                                                       {null, "say", ".*say.*", ".*say.*"},
                                                                       {null, "sleep", ".*sleep.*", ".*sleep.*"},
                                                                       {null, "thread", ".*thread.*", ".*thread.*"},
                                                                       {null, "undo", ".*undo.*", ".*undo.*"},
                                                                       {null, "wait", ".*wait.*", ".*wait.*"}};

    public UniqueCommandClassesTestCase() {
        super("Unique Class Per Command Token Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        HashSet<Class> classesFound = new HashSet<>(13);
        HashSet<Class> classesUnique = new HashSet<>(13);
        
        for(String[] description : tokenDescriptions) {
            Class clazz = IntrospectionUtil.findClass(project, 
                                                    description[0],
                                                    description[1],
                                                    description[2],
                                                    description[3]);
            if (clazz != null) {
                if (classesFound.contains(clazz)) {
                    classesUnique.remove(clazz);
                } else {
                    classesUnique.add(clazz);
                }
                classesFound.add(clazz);
            }
        }
        
        int unique = classesUnique.size();
        
        if (unique == CLASS_COUNT) {
            return pass();
        } else if (unique == 0) {
            return fail("No unique classes");
        } else {
            return partialPass(((double)unique)/CLASS_COUNT, "Only " + unique + " unique classes found");
        }
    }

    
}
