package gradingTools.comp401f15.assignment11.testcases;

import grader.checkers.ACheckResult;
import grader.checkers.AnAbstractFeatureChecker;
import grader.checkers.CheckResult;
import grader.project.flexible.FlexibleClassDescription;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/19/13
 * Time: 9:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class AdditionalCommandObjectsChecker extends AnAbstractFeatureChecker {

    @Override
    public CheckResult check() {
        CheckResult result = new ACheckResult();

        String[] tags = new String[] {
                "approach command", "pass command", "fail command"
        };
        double validCount = 0;
        for (String tag : tags) {

            // Get the class with the tag
            Set<FlexibleClassDescription> descriptions = project.getClassesManager().tagToClassDescriptions(tag);
            if (descriptions.isEmpty())
                result.getLog().add("No class tagged with " + tag);
            else {
                FlexibleClassDescription description = new ArrayList<FlexibleClassDescription>(descriptions).get(0);
                if (Runnable.class.isAssignableFrom(description.getJavaClass()))
                    validCount++;
                else
                    result.getLog().add("Class is not Runnable: " + tag);
            }
        }

        result.setScore((validCount / 3.0) * feature.getMax());
        return result;
    }
}
