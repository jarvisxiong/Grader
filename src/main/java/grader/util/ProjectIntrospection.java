package grader.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import framework.grading.testing.TestCase;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.Project;

public class ProjectIntrospection extends BasicProjectIntrospection {
	public static Object getInstance(Project aProject, TestCase aTestCase,
			String aClassName) {
		return aTestCase.getCheckable().getRequirements()
				.getUserObject(toObjectDescriptor(aClassName));

	}
	public static Class getOrFindClass(Project aProject, TestCase aTestCase,
			String aClassName) {
		Class aClassObject = getClass(aProject, aTestCase, aClassName);
		// Class aClassObject =
		// (Class)aTestCase.getCheckable().getRequirements().getUserObject(toClassDescriptor(aClassName));
		if (aClassObject == null) {
			aClassObject = BasicProjectIntrospection.findClass(aProject, aClassName);
			putClass(aProject, aTestCase, aClassName, aClassObject);
			// aTestCase.getCheckable().getRequirements().putUserObject(toClassDescriptor(aClassName),
			// aClassObject);
		}
		return aClassObject;
	}

	public static Class getClass(Project aProject, TestCase aTestCase,
			String aClassName) {
		return (Class) aTestCase.getCheckable().getRequirements()
				.getUserObject(toClassDescriptor(aClassName));

	}

	public static void putClass(Project aProject, TestCase aTestCase,
			String aClassName, Class aClassObject) {
		aTestCase.getCheckable().getRequirements()
				.putUserObject(toClassDescriptor(aClassName), aClassObject);

	}

	public static Set<Class> getOrFindClasses(Project aProject,
			TestCase aTestCase, String aClassName) {
		Set<Class> aClassObject = (Set<Class>) aTestCase.getCheckable()
				.getRequirements()
				.getUserObject(toClassesDescriptor(aClassName));
		if (aClassObject == null) {
			aClassObject = BasicProjectIntrospection.findClasses(aProject, aClassName);
			aTestCase
					.getCheckable()
					.getRequirements()
					.putUserObject(toClassesDescriptor(aClassName),
							aClassObject);
		}
		return aClassObject;
	}

	public static Set<Class> getOrFindInterfaces(Project aProject,
			TestCase aTestCase, String aClassName) {
		Set<Class> aClassObject = (Set<Class>) aTestCase.getCheckable()
				.getRequirements()
				.getUserObject(toInterfacesDescriptor(aClassName));
		if (aClassObject == null) {
			aClassObject = BasicProjectIntrospection.findInterfaces(aProject,
					aClassName);
			aTestCase
					.getCheckable()
					.getRequirements()
					.putUserObject(toInterfacesDescriptor(aClassName),
							aClassObject);
		}
		return aClassObject;
	}

	public static Class getOrFindInterface(Project aProject,
			TestCase aTestCase, String aClassName) {
		Class aClassObject = (Class) aTestCase.getCheckable().getRequirements()
				.getUserObject(toInterfaceDescriptor(aClassName));
		if (aClassObject == null) {
			aClassObject = BasicProjectIntrospection
					.findInterface(aProject, aClassName);
			aTestCase
					.getCheckable()
					.getRequirements()
					.putUserObject(toInterfaceDescriptor(aClassName),
							aClassObject);
		}
		return aClassObject;
	}

	public static List<Method> getOrFindMethodList(Project aProject,
			TestCase aTestCase, Class aClass, String name, String[] aTag) {
		String aDescriptor = aClass.toString() + "." + name + "." + "list";
		List<Method> aMethodObject = (List<Method>) aTestCase.getCheckable()
				.getRequirements().getUserObject(aDescriptor);
		if (aMethodObject == null) {
			aMethodObject = BasicProjectIntrospection.findMethod(aClass, name, aTag);
			aTestCase.getCheckable().getRequirements()
					.putUserObject(aDescriptor, aMethodObject);
		}
		return aMethodObject;
	}

	public static List<Method> getOrFindMethodList(Project aProject,
			TestCase aTestCase, Class aClass, String name, String aTag) {
		return getOrFindMethodList(aProject, aTestCase, aClass, name,
				new String[] { aTag });
	}

	public static List<Method> getOrFindMethodList(Project aProject,
			TestCase aTestCase, Class aClass, String aTag) {
		String aDescriptor = aClass.toString() + "." + aTag + "." + "list";
		List<Method> aMethodObject = (List<Method>) aTestCase.getCheckable()
				.getRequirements().getUserObject(aDescriptor);
		if (aMethodObject == null) {
			aMethodObject = BasicProjectIntrospection.findMethod(aClass, aTag);
			aTestCase.getCheckable().getRequirements()
					.putUserObject(aDescriptor, aMethodObject);
		}
		return aMethodObject;
	}

	public static Method getOrFindUniqueMethod(Project aProject,
			TestCase aTestCase, Class aClass, String aTag) {
		List<Method> retVal = getOrFindMethodList(aProject, aTestCase, aClass,
				aTag);
		if (retVal.isEmpty() || retVal.size() > 1)
			return null;
		return retVal.get(0);
	}
	public static Method getMethod(Project aProject, TestCase aTestCase,
			Class aClass, String aTag) {

		return (Method) aTestCase.getCheckable().getRequirements()
				.getUserObject(toMethodDescriptor(aClass, aTag));

	}

	public static Method putMethod(Project aProject, TestCase aTestCase,
			Class aClass, String aTag, Method aMethod) {

		return (Method) aTestCase.getCheckable().getRequirements()
				.getUserObject(toMethodDescriptor(aClass, aTag));

	}
	public static void putInstance(Project aProject, TestCase aTestCase,
			String aClassName, Object anObject) {
		aTestCase.getCheckable().getRequirements()
				.putUserObject(toObjectDescriptor(aClassName), anObject);

	}
	

}
