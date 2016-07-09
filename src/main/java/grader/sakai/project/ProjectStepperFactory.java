package grader.sakai.project;

import grader.steppers.AComplexProjectStepper;
import grader.steppers.OverviewProjectStepper;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/9/13
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProjectStepperFactory {
	static OverviewProjectStepper projectStepper;

    public static ProjectStepper createFromConfigurationFile() {

        // Load the class from the properties file
        try {
            Configuration config = new PropertiesConfiguration("config/config.properties");
            String stepperClassName = config.getString("stepper");
            Class<OverviewProjectStepper> stepperClass = (Class<OverviewProjectStepper>) ClassLoader.getSystemClassLoader().loadClass(stepperClassName);
//            return stepperClass.newInstance();
            projectStepper = stepperClass.newInstance();
            return projectStepper;


        } catch (Exception e) {

            // If it fails, use the standard one
            System.out.println("Unable to create project stepper... Using default.");
            return new AProjectStepper();
        }
    }
    public static OverviewProjectStepper createProjectStepper() {
//		if (projectStepper == null) {
			projectStepper = new AComplexProjectStepper();

			// projectStepper = new AMainProjectStepper();

			// projectStepper = new AnOverviewProjectStepper();
			// projectStepper = new ABasicProjectStepper();

			// projectStepper = new AProjectStepper();
//		}

		return projectStepper;
	}
	public static OverviewProjectStepper getProjectStepper() {
		return projectStepper;
	}
}
