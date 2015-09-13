package grader.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

import framework.project.ClassDescription;
import framework.project.Project;
import grader.execution.ResultWithOutput;

public class IntrospectionUtil {
	public static Class findClass(Project aProject, String aName, String aTag,
			String aNameMatch, String aTagMatch) {
		List<ClassDescription> aClasses = aProject.getClassesManager().get().findClass(null, "ScannerBean", ".*Bean.*", ".*Bean.*");
        if (aClasses.size() != 1) {
        	return null;
        }
        return aClasses.get(0).getJavaClass();
		
	}
	
	public static PropertyDescriptor findProperty (Class aClass, String aPropertyName) {
		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(aClass);
		
        for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {
            if (descriptor.getName().equalsIgnoreCase(aPropertyName) ) {
               return descriptor;
            }
        }
		} catch (IntrospectionException e) {
			return null;
		}
		return null;
	}

	
//	public static String setPropertyInteractive (Class aClass, Method aWriteMethod, Object aValue) {
//		
//		
//	}

}
