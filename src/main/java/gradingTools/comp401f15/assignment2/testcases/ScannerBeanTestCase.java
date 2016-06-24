package gradingTools.comp401f15.assignment2.testcases;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.util.List;

import wrappers.framework.project.ProjectWrapper;
import framework.execution.ARunningProject;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.OutputAndErrorCheckingTestCase;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;
import grader.execution.ResultWithOutput;
import grader.sakai.project.SakaiProject;
import grader.util.ProjectExecution;

public class ScannerBeanTestCase extends OutputAndErrorCheckingTestCase{

	public ScannerBeanTestCase() {
        super("Scanner Bean class test case");
    }
	
	static String[] emptyArgs = {};
	
	public Object createScannerBean (Class aClass) {
		Constructor aConstructor = null;
		try {
			aConstructor = aClass.getConstructor();
//			return aConstructor.newInstance();
			Object aResult = ProjectExecution.timedInvoke(aConstructor, emptyArgs, 300);
			return aResult;
		} catch (Exception e) {
			try {
				aConstructor = aClass.getConstructor(String.class);
				Object aResult = ProjectExecution.timedInvoke(aConstructor, new String[] {""}, 300);

				return aResult;
			} catch (Exception e1) {
				return null;
			}			
		}
		
		
		
	}

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();
     	SakaiProject aProject = ((ProjectWrapper) project).getProject();
        
        List<ClassDescription> aClasses = project.getClassesManager().get().findClassesAndInterfaces(null, "ScannerBean", ".*Bean.*", ".*Bean.*");
        if (aClasses.size() != 1) {
        	return  fail ("Cannot find unique scanner bean class");
        }
        ClassDescription description = aClasses.get(0);
//        
//        // Look in each class for something that satisfies the bean class requirements
//        for (ClassDescription description : project.getClassesManager().get().getClassDescriptions()) {

            // There should be a string property with a getter and setter
            try {
                BeanInfo info = Introspector.getBeanInfo(description.getJavaClass());
                for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {
                    if (descriptor.getName().equalsIgnoreCase("ScannedString") && descriptor.getPropertyType() == String.class && descriptor.getReadMethod() != null &&
                            descriptor.getWriteMethod() != null) {
                        Object anInstance = createScannerBean((description.getJavaClass()));
                        

                    	ResultWithOutput aResultWithOutput = ProjectExecution.timedInteractiveInvoke(anInstance, descriptor.getWriteMethod(), new String[]{"22 32 45 "}, 200);
                        String anOutput = aResultWithOutput.getOutput();
                        if (anOutput != null && !anOutput.isEmpty()) {
                        	ARunningProject.appendToTranscriptFile(aProject, getCheckable().getName(), anOutput);
                        }
                    	return pass(autoGrade);
                    }
                }
            } catch (IntrospectionException e) {
                // Do nothing if it fails
            }
        
        return fail("Couldn't find a class that satisfies the bean class requirements (string w/ a getter and setter).", autoGrade);
    }
}
