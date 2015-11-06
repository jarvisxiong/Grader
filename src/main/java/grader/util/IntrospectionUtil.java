package grader.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.annotations.Tags;
import framework.grading.testing.TestCase;
import framework.project.ClassDescription;
import framework.project.Project;
import grader.execution.ResultWithOutput;
import java.util.HashSet;
import java.util.Set;

public class IntrospectionUtil {
    
    public static Class<?> getClassForInterface(Project project, Class<?> target) {
        Set<ClassDescription> classes = project.getClassesManager().get().getClassDescriptions();
	for (ClassDescription classDescription : classes) {
            Class<?> clazz = classDescription.getJavaClass();
            if (target.isAssignableFrom(clazz)) {
                return clazz;
            }
        }
        return null;
    }
    
    public static Set<Class<?>> getClassesForInterface(Project project, Class<?> target) {
        Set<ClassDescription> classes = project.getClassesManager().get().getClassDescriptions();
        Set<Class<?>> ret = new HashSet<>();
	for (ClassDescription classDescription : classes) {
            Class<?> clazz = classDescription.getJavaClass();
            if (!target.equals(clazz) && target.isAssignableFrom(clazz)) {
                if (clazz.isInterface()) {
                    ret.addAll(getClassesForInterface(project, clazz));
                }
                ret.add(clazz);
            }
        }
        return ret;
    }
    
	public static Class findClass(Project aProject, String aName, String aTag,
			String aNameMatch, String aTagMatch) {
		List<ClassDescription> aClasses = aProject.getClassesManager().get().findClass(aName, aTag, aNameMatch, aTagMatch);
		for (ClassDescription aClass:aClasses) {
			if (aClass.getJavaClass().isInterface())
				continue;
			return aClass.getJavaClass();
		}
		return null;
		
//		if (aClasses.size() != 1) {
//        	return null;
//        }
//        return aClasses.get(0).getJavaClass();
		
	}
	public static List<Class> findClasses(Project aProject, String aName, String aTag,
			String aNameMatch, String aTagMatch) {
		List<Class> result = new ArrayList();
		List<ClassDescription> aClasses = aProject.getClassesManager().get().findClass(aName, aTag, aNameMatch, aTagMatch);
		for (ClassDescription aClass:aClasses) {
			if (aClass.getJavaClass().isInterface())
				continue;
			result.add(aClass.getJavaClass());
		}
		return result;
		
//		if (aClasses.size() != 1) {
//        	return null;
//        }
//        return aClasses.get(0).getJavaClass();
		
	}
	public static Class find(Project aProject, String aName, String aTag,
			String aNameMatch, String aTagMatch) {
		List<ClassDescription> aClasses = aProject.getClassesManager().get().findClass(aName, aTag, aNameMatch, aTagMatch);
		for (ClassDescription aClass:aClasses) {
			if (aClass.getJavaClass().isInterface())
				continue;
			return aClass.getJavaClass();
		}
		return null;
		
//		if (aClasses.size() != 1) {
//        	return null;
//        }
//        return aClasses.get(0).getJavaClass();
		
	}
	static String toClassDescriptor(String aClassName) {
		return aClassName + "." + "class";
	}
	static String toClassesDescriptor(String aClassName) {
		return aClassName + "." + "classes";
	}
	static String toInterfacesDescriptor(String aClassName) {
		return aClassName + "." + "interfaces";
	}
	static String toInterfaceDescriptor(String aClassName) {
		return aClassName + "." + "interface";
	}
	public static Class getOrFindClass(Project aProject, TestCase aTestCase, String aClassName) {
		Class aClassObject = (Class)aTestCase.getCheckable().getRequirements().getUserObject(toClassDescriptor(aClassName));
		if (aClassObject == null) {
			aClassObject = IntrospectionUtil.findClass(aProject, aClassName);
			aTestCase.getCheckable().getRequirements().putUserObject(toClassDescriptor(aClassName), aClassObject);
		}
		return aClassObject;
	}
	public static List<Class> getOrFindClasses(Project aProject, TestCase aTestCase, String aClassName) {
		List<Class> aClassObject = (List<Class>)aTestCase.getCheckable().getRequirements().getUserObject(toClassesDescriptor(aClassName));
		if (aClassObject == null) {
			aClassObject = IntrospectionUtil.findClasses(aProject, aClassName);
			aTestCase.getCheckable().getRequirements().putUserObject(toClassesDescriptor(aClassName), aClassObject);
		}
		return aClassObject;
	}
        public static List<Class> getOrFindInterfaces(Project aProject, TestCase aTestCase, String aClassName) {
		List<Class> aClassObject = (List<Class>)aTestCase.getCheckable().getRequirements().getUserObject(toInterfacesDescriptor(aClassName));
		if (aClassObject == null) {
			aClassObject = IntrospectionUtil.findInterfaces(aProject, aClassName);
			aTestCase.getCheckable().getRequirements().putUserObject(toInterfacesDescriptor(aClassName), aClassObject);
		}
		return aClassObject;
        }
	public static Class getOrFindInterface(Project aProject, TestCase aTestCase, String aClassName) {
		Class aClassObject = (Class)aTestCase.getCheckable().getRequirements().getUserObject(toInterfaceDescriptor(aClassName));
		if (aClassObject == null) {
			aClassObject = IntrospectionUtil.findInterface(aProject, aClassName);
			aTestCase.getCheckable().getRequirements().putUserObject(toInterfaceDescriptor(aClassName), aClassObject);
		}
		return aClassObject;
	}
        
        public static List<Method> getOrFindMethodList(Project aProject, TestCase aTestCase, Class aClass, String name, String aTag) {
		String aDescriptor = aClass.toString() + "." + name + "." + "list";
		List<Method> aMethodObject = (List<Method>) aTestCase.getCheckable().getRequirements().getUserObject(aDescriptor);
		if (aMethodObject == null) {
			aMethodObject = IntrospectionUtil.findMethod(aClass, name, aTag);
			aTestCase.getCheckable().getRequirements().putUserObject(aDescriptor, aMethodObject);
		}
		return aMethodObject;
	}
	
	public static List<Method> getOrFindMethodList(Project aProject, TestCase aTestCase, Class aClass, String aTag) {
		String aDescriptor = aClass.toString() + "." + aTag + "." + "list";
		List<Method> aMethodObject = (List<Method>) aTestCase.getCheckable().getRequirements().getUserObject(aDescriptor);
		if (aMethodObject == null) {
			aMethodObject = IntrospectionUtil.findMethod(aClass, aTag);
			aTestCase.getCheckable().getRequirements().putUserObject(aDescriptor, aMethodObject);
		}
		return aMethodObject;
	}
	static String toMethodDescriptor(Class aClass, String aTag) {
		return aClass.getCanonicalName() + "." + aTag;
	}
	public static Method getMethod(Project aProject, TestCase aTestCase, Class aClass, String aTag) {

		return (Method) aTestCase.getCheckable().getRequirements().getUserObject(toMethodDescriptor(aClass, aTag));
		
	}
	public static Method putMethod(Project aProject, TestCase aTestCase, Class aClass, String aTag, Method aMethod) {

		return (Method) aTestCase.getCheckable().getRequirements().getUserObject(toMethodDescriptor(aClass, aTag));
		
	}
	public static String toRegex(String aName) {
		return ".*" + aName + ".*";
	}
	public static Class findClass(Project aProject, String aName) {
		return findClass(aProject, null, aName, toRegex(aName), toRegex(aName) );
		
	}
	public static List<Class> findClasses(Project aProject, String aName) {
		return findClasses(aProject, null, aName, toRegex(aName), toRegex(aName) );
		
	}
	public static Class findInterface(Project aProject, String aName) {
		return findInterface(aProject, null, aName, toRegex(aName), toRegex(aName) );
		
	}
	public static List<Class> findInterfaces(Project aProject, String aName) {
		return findInterfaces(aProject, null, aName, toRegex(aName), toRegex(aName) );
		
	}
	public static List<ClassDescription> findClassesByTag(Project aProject, String aTag) {
		return aProject.getClassesManager().get().findClass(null, aTag, null, null);
		
//		if (aClasses.size() != 1) {
//        	return null;
//        }
//        return aClasses.get(0).getJavaClass();
		
	}
	
	public static Class findInterface(Project aProject, String aName, String aTag,
			String aNameMatch, String aTagMatch) {
		List<ClassDescription> aClasses = aProject.getClassesManager().get().findClass(aName, aTag, aNameMatch, aTagMatch);
		for (ClassDescription aClass:aClasses) {
			if (!aClass.getJavaClass().isInterface())
				continue;
			return aClass.getJavaClass();
		}
		return null;
		
//		if (aClasses.size() != 1) {
//        	return null;
//        }
//        return aClasses.get(0).getJavaClass();
		
	}
	public static List<Class> findInterfaces(Project aProject, String aName, String aTag,
			String aNameMatch, String aTagMatch) {
		List<Class> result = new ArrayList();
		List<ClassDescription> aClasses = aProject.getClassesManager().get().findClass(aName, aTag, aNameMatch, aTagMatch);
		for (ClassDescription aClass:aClasses) {
			if (!aClass.getJavaClass().isInterface())
				continue;
			result.add(aClass.getJavaClass());
		}
		return result;
		
//		if (aClasses.size() != 1) {
//        	return null;
//        }
//        return aClasses.get(0).getJavaClass();
		
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
			System.out.println("Property " +  aPropertyName + " not found");

			return null;
		}
		System.out.println("Property " +  aPropertyName + " not found");

		return null;
	}
	 public static String[] getTags(Method aMethod) {
	        try {
	            return aMethod.getAnnotation(Tags.class).value();
	        } catch (Exception e) {
	            return new String[]{};
	        }
	    }
	    /**
	     * Looks for all methods with a particular tag
	     *
	     * @param aSpecification The tag to search for
	     * @return The set of matching class descriptions
	     */
	    
	    public static List<Method>  findMethodByTag(Class aClass, String aSpecification) {
	        List<Method> result = new ArrayList<>();
	        for (Method aMethod : aClass.getMethods()) {
	        	
	            for (String t : getTags(aMethod)) {
	                if (t.equalsIgnoreCase(aSpecification)) {
	                    result.add(aMethod);
	                }
	            }
	        }
	        return result;
	    }
	    /**
	     * Looks for all methods with a particular tag match
	     *
	     * @param aSpecification The tag to search for
	     * @return The set of matching class descriptions
	     */
	    public static List<Method> findMethodByTagMatch(Class aClass, String aSpecification) {
	        List<Method> result = new ArrayList<>();
	        for (Method aMethod : aClass.getMethods()) {
	        	
	            for (String t : getTags(aMethod)) {
	                if (t.matches(aSpecification)) {
	                    result.add(aMethod);
	                }
	            }
	        }
	        return result;
	    }
	    /**
	     * Looks for all methods with a particular name
	     *     
	     */
	    public static List<Method> findMethodByNameMatch(Class aClass, String aSpecification) {
	        List<Method> result = new ArrayList<>();
	        for (Method aMethod : aClass.getMethods()) {
	        	
	                if (aMethod.getName().matches(aSpecification)) {
	                    result.add(aMethod);
	                }
	            
	        }
	        return result;
	    }
	    /**
	     * Looks for all methods with a particular name match
	     *     
	     */
	    public static List<Method> findMethodByName(Class aClass, String aSpecification) {
	        List<Method> result = new ArrayList<>();
	        for (Method aMethod : aClass.getMethods()) {
	        	
	                if (aMethod.getName().equalsIgnoreCase(aSpecification)) {
	                    result.add(aMethod);
	                }
	            
	        }
	        return result;
	    }
	    public static List<Method> findMethod (Class aJavaClass, String aName) {
	    	return findMethod(aJavaClass, null, aName, toRegex(aName), toRegex(aName));
	    }

            public static List<Method> findMethod (Class aJavaClass, String aName, String tag) {
	    	return findMethod(aJavaClass, aName, tag, toRegex(aName), toRegex(tag));
	    }
	    
	    public static List<Method> findMethod (Class aJavaClass, String aName, String aTag, String aNameMatch, String aTagMatch) {
	    	List<Method> result = new ArrayList();
	    	if (aName != null)
	    		result = findMethodByName(aJavaClass, aName);
	    	if (!result.isEmpty())
	    		return result;
	    	if (aTag != null)
	    		result = findMethodByTag(aJavaClass, aTag);    	
	    	if (!result.isEmpty())
	    		return result;
	    	if (aNameMatch != null) {
	    		result = findMethodByNameMatch(aJavaClass, aNameMatch);  		
	    	}
	    	if (!result.isEmpty())
	    		return result;
	    	if (aTagMatch != null) {
	    		result = findMethodByTagMatch(aJavaClass, aTagMatch);
	    	}
	    	return result;
	    }

	    public static List<Class> getAllInterfaces(Class aClass) {
	    	List<Class> retVal = new ArrayList();
	    	while (aClass != null) {
	    	   retVal.addAll(Arrays.asList(aClass.getInterfaces()));
	    	   aClass = aClass.getSuperclass();
	    	}
	    	return retVal;
	    	
	    }
	    public static Class getCommonInterface(Project project, String[][] aClassDescriptions) {
	        List<Class> interfaces = new ArrayList<>(5);
	        for(String[] classDescriptions : aClassDescriptions) {
	            Class aClass = IntrospectionUtil.findClass(project, 
	                            classDescriptions[0],
	                            classDescriptions[1],
	                            classDescriptions[2],
	                            classDescriptions[3]);
	            if (aClass == null) 
	            	continue;
	            	
	           List<Class> tokenInterfaces = IntrospectionUtil.getAllInterfaces(aClass);
	            if(tokenInterfaces.size() == 0) {
	                return null;
	            } else {
	                if (interfaces.isEmpty()) {
	                    interfaces.addAll(tokenInterfaces);
	                } else {
	                    interfaces.retainAll(tokenInterfaces);
	                }
	            }
	        }
	        if (interfaces.size() != 1) {
	            return null;
	        } else {
	            return interfaces.get(0);
	        }
	    }
//	public static String setPropertyInteractive (Class aClass, Method aWriteMethod, Object aValue) {
//		
//		
//	}

}
