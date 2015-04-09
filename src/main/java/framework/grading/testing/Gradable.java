package framework.grading.testing;

import framework.grading.ProjectRequirements;
import grader.permissions.Permissible;

/**
 * Anything that is gradable should have a name and a point value
 */
public interface Gradable extends Describable, Permissible {

    /**
     * @return The name of the gradable item
     */
    public String getName();

    /**
     * @return The max possible point that can be awarded
     */
    public double getPoints();

	ProjectRequirements getRequirements();

	void initRequirements(ProjectRequirements requirements);
	
}
