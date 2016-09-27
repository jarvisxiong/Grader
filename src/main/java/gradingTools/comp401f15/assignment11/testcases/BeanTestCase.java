package gradingTools.comp401f15.assignment11.testcases;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import wrappers.framework.project.ProjectWrapper;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.sakai.project.SakaiProject;
import gradingTools.sharedTestCase.checkstyle.OldOutputAndErrorCheckingTestCase;

public class BeanTestCase extends OldOutputAndErrorCheckingTestCase{
	String tag;
	List<String> readOnlyProperties;
	List<String> editableProperties;
	public BeanTestCase(String aTag, List<String> aReadOnlyProperties, List<String> anEditableProperties) {
        super("Bean class test case");
        tag = aTag;
        editableProperties = anEditableProperties;
        readOnlyProperties = aReadOnlyProperties;
    }
	
	static String[] emptyArgs = {};
	
	protected String getMatchedProperty(PropertyDescriptor aDescriptor, List<String> aProperties) {
		for (String aProperty:aProperties) {
			if (aDescriptor.getName().equalsIgnoreCase(aProperty))
				return aProperty;
		}
		return null;
	}

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();
        
     	SakaiProject aProject = ((ProjectWrapper) project).getProject();
     	Class aParserClass = BasicProjectIntrospection.findClass(project, tag);
     	if (aParserClass == null) {
     		return fail ("Did not find unique class for tag:" + tag);
     	}
        
        List<String> anUnMatchedEditableProperties = new ArrayList(editableProperties);
        List<String> anUnMatchedReadOnlyProperties = new ArrayList(readOnlyProperties);
        List<String> anUnMatchedProperties = new ArrayList(editableProperties);
        anUnMatchedProperties.addAll(readOnlyProperties);
        
//        // Look in each class for something that satisfies the bean class requirements
//        for (ClassDescription description : project.getClassesManager().get().getClassDescriptions()) {

            // There should be a string property with a getter and setter
            try {
            	
                BeanInfo info = Introspector.getBeanInfo(aParserClass);
                for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {
                	String aMatchedProperty = getMatchedProperty(descriptor, anUnMatchedProperties);
                	if (aMatchedProperty != null) {
                		anUnMatchedProperties.remove(aMatchedProperty);
                		if (descriptor.getWriteMethod() == null) {
                			anUnMatchedReadOnlyProperties.remove(aMatchedProperty);
                		} else {
                			anUnMatchedEditableProperties.remove(aMatchedProperty);
                		}
                	}
                 
                }
                double aFractionMatched = (editableProperties.size() + readOnlyProperties.size()- anUnMatchedProperties.size()) / 
                		(editableProperties.size() + readOnlyProperties.size());
                double aFractionEditableMatched = (editableProperties.size() - anUnMatchedEditableProperties.size())/ 
                		editableProperties.size();
                double aFractionReadonlyMatched = (readOnlyProperties.size() - anUnMatchedReadOnlyProperties.size())/ 
                		readOnlyProperties.size();
                StringBuilder aMessage = new StringBuilder();
                if (aFractionMatched != 1) {
                	aMessage.append("Unmatched properties:" + anUnMatchedProperties);
                }
                if (aFractionEditableMatched != 1) {
                	aMessage.append("Unmatched editable properties:" + anUnMatchedEditableProperties);
                }
                if (aFractionReadonlyMatched != 1) {
                	aMessage.append("Unmatched readonly properties:" + anUnMatchedReadOnlyProperties);
                }
                double allMatched = aFractionMatched + aFractionEditableMatched + aFractionReadonlyMatched;

                if (allMatched == 3)
                	return pass();
                return partialPass(allMatched/3.0, aMessage.toString());

            } catch (IntrospectionException e) {
                // Do nothing if it fails
            	return fail ("Could not introspect class");
            }
        
    }
}
