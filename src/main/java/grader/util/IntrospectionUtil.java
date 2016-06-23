package grader.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import util.annotations.Tags;
import util.introspect.JavaIntrospectUtility;
import framework.grading.testing.TestCase;
import framework.project.ClassDescription;
import framework.project.CurrentProjectHolder;
import framework.project.Project;

public class IntrospectionUtil {

	public static Class<?> getClassForInterface(Project project, Class<?> target) {
		Set<ClassDescription> classes = project.getClassesManager().get()
				.getClassDescriptions();
		for (ClassDescription classDescription : classes) {
			Class<?> clazz = classDescription.getJavaClass();
			if (target.isAssignableFrom(clazz)) {
				return clazz;
			}
		}
		return null;
	}

	public static Set<Class<?>> getClassesForInterface(Project project,
			Class<?> target) {
		Set<ClassDescription> classes = project.getClassesManager().get()
				.getClassDescriptions();
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
	static String[] emptyStrings = {};
	public static Class findClass(Project aProject, String aName, String aTag,
			String aNameMatch, String aTagMatch) {
		String[] aTags = (aTag == null)? emptyStrings:new String[] { aTag };
	
		return findClass(aProject, aName, aTags, aNameMatch,
				aTagMatch);
	}
	//not unique class
	// why is this not calling fincClasses
//	public static Class findClass(Project aProject, String aName,
//			String[] aTag, String aNameMatch, String aTagMatch) {
//		int i = 0;
//		List<ClassDescription> aClasses = aProject.getClassesManager().get()
//				.findClass(aName, aTag, aNameMatch, aTagMatch);
//		for (ClassDescription aClass : aClasses) {
//			if (aClass.getJavaClass().isInterface())
//				continue;
//			return aClass.getJavaClass();
//		}
//		return null;
//
//		// if (aClasses.size() != 1) {
//		// return null;
//		// }
//		// return aClasses.get(0).getJavaClass();
//
//	}
	public static Class findClass(Project aProject, String aName,
			String[] aTag, String aNameMatch, String aTagMatch) {
		List<Class> aClasses = findClasses(aProject, aName, aTag, aNameMatch, aTagMatch);
		
//		int i = 0;
//		List<ClassDescription> aClasses = aProject.getClassesManager().get()
//				.findClass(aName, aTag, aNameMatch, aTagMatch);
//		for (ClassDescription aClass : aClasses) {
//			if (aClass.getJavaClass().isInterface())
//				continue;
//			return aClass.getJavaClass();
//		}
//		return null;

		 if (aClasses.size() != 1) {
		 return null;
		 }
		 return aClasses.get(0);

	}
	public static Class findClassByMethods(Project aProject, Method[] aMethods) {
		Set<Class> aClasses = findClassesByMethods(aProject, aMethods);
		
		
//		int i = 0;
//		List<ClassDescription> aClasses = aProject.getClassesManager().get()
//				.findClass(aName, aTag, aNameMatch, aTagMatch);
//		for (ClassDescription aClass : aClasses) {
//			if (aClass.getJavaClass().isInterface())
//				continue;
//			return aClass.getJavaClass();
//		}
//		return null;

		 if (aClasses.size() != 1) {
		 return null;
		 }
		 return aClasses.iterator().next();

	}
	public static boolean implementsOneOf (Class aClass, Set<Class> anInterfaces) {
		// recursively get all interfaces
		Vector<Class> aClassInterfaces = new Vector();
		JavaIntrospectUtility.addInterfaces(aClassInterfaces, aClass);
		Set<Class> aClassInterfacesSet = new HashSet(aClassInterfaces);
		aClassInterfacesSet.retainAll(anInterfaces);
		return aClassInterfacesSet.size() != 0;		
	}
	
	public static boolean matchesParameters(Class[] aCandidates, Class[] aProxies) {
		if (aCandidates.length != aProxies.length) return false;
		for (int anIndex = 0; anIndex < aProxies.length; anIndex++) {
			if (aCandidates[anIndex] == aProxies[anIndex])
				continue;
			if (aProxies[anIndex].getCanonicalName().startsWith("java")) {
				return false; // means this is not a user defined class
			}
		}
		return true;		
		
	}
	public static Set<Class> findClassesByMethods(Project aProject,  Method[] aMethods) {
		 Set<ClassDescription> aClassDescriptions = aProject.getClassesManager().get().getClassDescriptions();
		 Set<Class> aResult = new HashSet();
		 for (ClassDescription aClassDescription : aClassDescriptions) {
				Class aClass = aClassDescription.getClass();
				if (aClass.isInterface()) {
					continue;
				}
				if (matchesMethods(aClass, aMethods))
					aResult.add(aClass);
				
			}
		 return aResult;		 
	}
	// why are these not all sets?
	public static List<Class> findClasses(Project aProject, String aName,
			String aTag, String aNameMatch, String aTagMatch) {
		List<Class> result = new ArrayList();
		List<ClassDescription> aClasses = aProject.getClassesManager().get()
				.findClassesAndInterfaces(aName, aTag, aNameMatch, aTagMatch);
		Set<Class> aMatchedInterfaces = new HashSet();
		for (ClassDescription aClass : aClasses) {
			if (aClass.getJavaClass().isInterface()) {
				aMatchedInterfaces.add(aClass.getJavaClass());
				continue;
			}
			result.add(aClass.getJavaClass());
		}
		if (!result.isEmpty())
			return result;
		if (aMatchedInterfaces.isEmpty())
			return result;
		// now see if ant of the classes implement any of the matched interfaces
		for (ClassDescription aClassDescription : aClasses) {
			Class aClass = aClassDescription.getJavaClass();
			if (aClass.isInterface()) {
				continue;
			}
			if (implementsOneOf(aClass, aMatchedInterfaces)) {
			   result.add(aClassDescription.getJavaClass());
			}
		}
		return result;
		// if (aClasses.size() != 1) {
		// return null;
		// }
		// return aClasses.get(0).getJavaClass();

	}
	// why are these not all sets?
		public static List<Class> findClasses(Project aProject, String aName,
				String[] aTag, String aNameMatch, String aTagMatch) {
			List<Class> result = new ArrayList();
			List<ClassDescription> aClasses = aProject.getClassesManager().get()
					.findClassAndInterfaces(aName, aTag, aNameMatch, aTagMatch);
			Set<Class> aMatchedInterfaces = new HashSet();
			for (ClassDescription aClass : aClasses) {
				if (aClass.getJavaClass().isInterface()) {
					aMatchedInterfaces.add(aClass.getJavaClass());
					continue;
				}
				result.add(aClass.getJavaClass());
			}
			if (!result.isEmpty())
				return result;
			if (aMatchedInterfaces.isEmpty())
				return result;
			// now see if ant of the classes implement any of the matched interfaces
			for (ClassDescription aClassDescription : aClasses) {
				Class aClass = aClassDescription.getJavaClass();
				if (aClass.isInterface()) {
					continue;
				}
				if (implementsOneOf(aClass, aMatchedInterfaces)) {
				   result.add(aClassDescription.getJavaClass());
				}
			}
			return result;
			// if (aClasses.size() != 1) {
			// return null;
			// }
			// return aClasses.get(0).getJavaClass();

		}

	public static Class find(Project aProject, String aName, String aTag,
			String aNameMatch, String aTagMatch) {
		List<ClassDescription> aClasses = aProject.getClassesManager().get()
				.findClassesAndInterfaces(aName, aTag, aNameMatch, aTagMatch);
		for (ClassDescription aClass : aClasses) {
			if (aClass.getJavaClass().isInterface())
				continue;
			return aClass.getJavaClass();
		}
		return null;

		// if (aClasses.size() != 1) {
		// return null;
		// }
		// return aClasses.get(0).getJavaClass();

	}

	static String toClassDescriptor(String aClassName) {
		return aClassName + "." + "class";
	}

	static String toObjectDescriptor(String aTag) {
		return aTag + "." + "instance";
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

	public static void putInstance(Project aProject, TestCase aTestCase,
			String aClassName, Object anObject) {
		aTestCase.getCheckable().getRequirements()
				.putUserObject(toObjectDescriptor(aClassName), anObject);

	}

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
			aClassObject = IntrospectionUtil.findClass(aProject, aClassName);
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

	public static List<Class> getOrFindClasses(Project aProject,
			TestCase aTestCase, String aClassName) {
		List<Class> aClassObject = (List<Class>) aTestCase.getCheckable()
				.getRequirements()
				.getUserObject(toClassesDescriptor(aClassName));
		if (aClassObject == null) {
			aClassObject = IntrospectionUtil.findClasses(aProject, aClassName);
			aTestCase
					.getCheckable()
					.getRequirements()
					.putUserObject(toClassesDescriptor(aClassName),
							aClassObject);
		}
		return aClassObject;
	}

	public static List<Class> getOrFindInterfaces(Project aProject,
			TestCase aTestCase, String aClassName) {
		List<Class> aClassObject = (List<Class>) aTestCase.getCheckable()
				.getRequirements()
				.getUserObject(toInterfacesDescriptor(aClassName));
		if (aClassObject == null) {
			aClassObject = IntrospectionUtil.findInterfaces(aProject,
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
			aClassObject = IntrospectionUtil
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
			aMethodObject = IntrospectionUtil.findMethod(aClass, name, aTag);
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
			aMethodObject = IntrospectionUtil.findMethod(aClass, aTag);
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

	static String toMethodDescriptor(Class aClass, String aTag) {
		return aClass.getCanonicalName() + "." + aTag;
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

	public static String toRegex(String aName) {
		if (aName == null)
			return null;
		return ".*" + aName + ".*";

	}
	

	public static Class findClass(Project aProject, Class aProxyClass) {
		String[] aTags = getTags(aProxyClass);
		Class retVal = findUniqueClassByTag(aProject, aTags);
		if (retVal != null)
			return retVal;
		retVal = findClass(aProject, aProxyClass.getCanonicalName());
		if (retVal != null) 
			return retVal;
		retVal = findClass(aProject, aProxyClass.getSimpleName());
		if (retVal != null)
			return retVal;
		return findClassByMethods(aProject, aProxyClass.getMethods());
	}

	public static Class findClass(Project aProject, String aName) {
		Class aClass = tagsToClass.get(aName);
		if (aClass == null) {
			aClass = findClass(aProject, aName, aName, aName, aName);
			if (aClass == null) {
				aClass = Object.class;
			}
			tagsToClass.put(aName, aClass);
		}
		if (aClass == Object.class) {
			return null;
		}
		return aClass;
		// return findClass(aProject, null, aName, toRegex(aName),
		// toRegex(aName) );
		// return findClass(aProject, aName, aName, aName, aName );
	}

	public static List<Class> findClasses(Project aProject, String aName) {
		return findClasses(aProject, null, aName, toRegex(aName),
				toRegex(aName));

	}

	public static Class findInterface(Project aProject, String aName) {
		return findInterface(aProject, null, aName, toRegex(aName),
				toRegex(aName));

	}

	public static List<Class> findInterfaces(Project aProject, String aName) {
		return findInterfaces(aProject, null, new String[] { aName },
				toRegex(aName), toRegex(aName));

	}

	public static List<ClassDescription> findClassesByTag(Project aProject,
			String aTag) {
		return aProject.getClassesManager().get()
				.findClassAndInterfaces(null, new String[] { aTag }, null, null);

		// if (aClasses.size() != 1) {
		// return null;
		// }
		// return aClasses.get(0).getJavaClass();

	}

	static Map<String, Class> tagsToClass = new HashMap();
	static Map<String, Method> tagsToMethod = new HashMap();

	public static void clearProjectCaches() {
		tagsToClass.clear();
		tagsToMethod.clear();
	}

	public static Class findUniqueClassByTag(Project aProject, Class aProxy) {
		String[] aTags = getTags(aProxy);
		return findUniqueClassByTag(aProject, aTags);
	}
	public static Class findUniqueClassByTag(Project aProject, String aTag) {
		return findUniqueClassByTag(aProject, new String[] {aTag});
	}

	public static Class findUniqueClassByTag(Project aProject, String[] aTags) {
		if (aTags.length == 0) {
			return null;
		}		
		Arrays.sort(aTags);
		String aKey = Arrays.toString(aTags);
		Class aCachedClass = tagsToClass.get(aKey);
		if (aCachedClass == null) {

			ClassDescription aClassDescription = findUniqueClassDescriptionByTag(
					aProject, aTags);
			if (aClassDescription == null) {
				aCachedClass = Object.class;

			}

			aCachedClass = aClassDescription.getJavaClass();
			tagsToClass.put(aKey, aCachedClass);

		}
		if (aCachedClass == Object.class) {
			return null;
		}
		return aCachedClass;
	}

	public static ClassDescription findUniqueClassDescriptionByTag(
			Project aProject, String[] aTags) {
		List<ClassDescription> aClasses = findClassesByTag(aProject, aTags);
		if (aClasses.size() != 1)
			return null;
		return aClasses.get(0);

		// if (aClasses.size() != 1) {
		// return null;
		// }
		// return aClasses.get(0).getJavaClass();

	}

	public static List<ClassDescription> findClassesByTag(Project aProject,
			String[] aTag) {
		// List<String> aSortableTagList = new ArrayList(Arrays.asList(aTag));

		return aProject.getClassesManager().get()
				.findClassAndInterfaces(null, aTag, null, null);

		// if (aClasses.size() != 1) {
		// return null;
		// }
		// return aClasses.get(0).getJavaClass();

	}

	public static Class findInterface(Project aProject, String aName,
			String aTag, String aNameMatch, String aTagMatch) {
		List<ClassDescription> aClasses = aProject.getClassesManager().get()
				.findClassesAndInterfaces(aName, aTag, aNameMatch, aTagMatch);
		for (ClassDescription aClass : aClasses) {
			if (!aClass.getJavaClass().isInterface())
				continue;
			return aClass.getJavaClass();
		}
		return null;

		// if (aClasses.size() != 1) {
		// return null;
		// }
		// return aClasses.get(0).getJavaClass();

	}

	public static List<Class> findInterfaces(Project aProject, String aName,
			String aTag, String aNameMatch, String aTagMatch) {
		return findInterfaces(aProject, aName, new String[] { aTag },
				aNameMatch, aTagMatch);
	}

	public static List<Class> findInterfaces(Project aProject, String aName,
			String[] aTag, String aNameMatch, String aTagMatch) {
		List<Class> result = new ArrayList();
		List<ClassDescription> aClasses = aProject.getClassesManager().get()
				.findClassAndInterfaces(aName, aTag, aNameMatch, aTagMatch);
		for (ClassDescription aClass : aClasses) {
			if (!aClass.getJavaClass().isInterface())
				continue;
			result.add(aClass.getJavaClass());
		}
		return result;

		// if (aClasses.size() != 1) {
		// return null;
		// }
		// return aClasses.get(0).getJavaClass();

	}

	public static PropertyDescriptor findProperty(Class aClass,
			String aPropertyName) {
		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(aClass);

			for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {
				if (descriptor.getName().equalsIgnoreCase(aPropertyName)) {
					return descriptor;
				}
			}
		} catch (IntrospectionException e) {
			System.out.println("Property " + aPropertyName + " not found");

			return null;
		}
		System.out.println("Property " + aPropertyName + " not found");

		return null;
	}

	public static String[] getTags(Method aMethod) {
		try {
			return aMethod.getAnnotation(Tags.class).value();
		} catch (Exception e) {
			return new String[] {};
		}
	}

	public static String[] getTags(Class<?> aClass) {
		try {
			return aClass.getAnnotation(Tags.class).value();
		} catch (Exception e) {
			return new String[] {};
		}
	}

	public static Method findUniqueMethodByTag(Class aClass, Method aProxy) {
		String[] aTags = getTags(aProxy);
		Class[] aParameterTypes = aProxy.getParameterTypes();
		return findUniqueMethodByTag(aClass, aTags, aParameterTypes);
	}

	static Class[] emptyClassArray = {};

	public static Method findUniqueMethodByTag(Class aClass,
			String[] aSpecification, Class[] aParameterTypes) {
		try {
			Arrays.sort(aSpecification);
			String aKey = aClass.getName() + ":"
					+ Arrays.toString(aSpecification) + ":"
					+ Arrays.toString(aParameterTypes);
			Method aMethod = tagsToMethod.get(aKey);
			if (aMethod == null) {
				List<Method> aMethods = findMethodsByTag(aClass, aSpecification);
				aMethod = selectMethod(aMethods, aParameterTypes);
				if (aMethod == null) {

					aMethod = Object.class.getMethod("wait", emptyClassArray);
				}
				tagsToMethod.put(aKey, aMethod);
			}
			if (aMethod == Object.class.getMethod("wait", emptyClassArray)) {
				return null;
			}
			return aMethod;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// if (aMethods.size() != 1)
		// return null;
		// return aMethods.get(0);
	}

	/**
	 * Looks for all methods with a particular tag
	 *
	 * @param aSpecification
	 *            The tag to search for
	 * @return The set of matching class descriptions
	 */
	public static List<Method> findMethodsByTag(Class aClass,
			String[] aSpecification) {
		// Set aSpecificationSet = new HashSet(Arrays.asList(aSpecification));
		normalizeTags(aSpecification);
		List aSpecificationList = Arrays.asList(aSpecification);

		List<Method> result = new ArrayList<>();
		for (Method aMethod : aClass.getMethods()) {

			if (matchesTags(aSpecificationList, getTags(aMethod))) {
				result.add(aMethod);
			}
			// Set anActualSet = new
			// HashSet(Arrays.asList(getTags(aMethod).clone())) ;
			// if (anActualSet.containsAll(aSpecificationList)) {
			// result.add(aMethod);
			// }
			// for (String t : getTags(aMethod)) {
			// if (t.equalsIgnoreCase(aSpecification)) {
			// result.add(aMethod);
			// }
			// }
		}
		return result;
	}

	public static void normalizeTags(String[] aTags) {

		for (int i = 0; i < aTags.length; i++) {
			aTags[i] = aTags[i].replaceAll("\\s", "").toLowerCase();
		}
	}

	public static boolean matchesTags(List<String> aSpecification,
			String[] anActual) {
		if (aSpecification == null) return true;
		String[] anActualsClone = anActual.clone();
		normalizeTags(anActualsClone);
		Set anActualSet = new HashSet(Arrays.asList(anActualsClone));
		return (anActualSet.containsAll(aSpecification));
	}

	/**
	 * Looks for all methods with a particular tag match
	 *
	 * @param aSpecification
	 *            The tag to search for
	 * @return The set of matching class descriptions
	 */
	public static List<Method> findMethodByTagMatch(Class aClass,
			String aSpecification) {
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
	public static List<Method> findMethodByNameMatch(Class aClass,
			String aSpecification) {
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
	public static List<Method> findMethodByName(Class aClass,
			String aSpecification) {
		if (aClass == null) {
			return null;
		}
		List<Method> result = new ArrayList<>();
		for (Method aMethod : aClass.getMethods()) {

			if (aMethod.getName().equalsIgnoreCase(aSpecification)) {
				result.add(aMethod);
			}

		}
		return result;
	}
	// we have tried equals
	public static boolean isPermutationOf(Class[] anActualParameterTypes, Class[] aTargetParameterTypes) {
		if (anActualParameterTypes.length != aTargetParameterTypes.length) return false;
		if (aTargetParameterTypes.length == 1) return false; // no new permutations
		Permutations<Class> permutations = new Permutations<Class> (aTargetParameterTypes);
		while (permutations.hasNext()) {
			Class[] aPermutedTargetParameterTypes = permutations.next();
			if (matchesParameters(anActualParameterTypes, aPermutedTargetParameterTypes)) {// we have alrady tried this
				return true;
			}
		}
		return false;
	}

	public static Method selectMethod(List<Method> aMethods,
			Class[] aParameterTypes) {
		Method aRetVal = null;
		for (Method aMethod : aMethods) {
			if (Arrays.equals(aMethod.getParameterTypes(), aParameterTypes)) {
				if (aRetVal != null)
					return null;
				aRetVal = aMethod;
				// return aMethod; // there can be other matching methods
			}
		}
		if (aRetVal != null) {
			return aRetVal;
		}
		// now we should try all permutations of method parameter types
		for (Method aMethod : aMethods) {
			if (isPermutationOf(aMethod.getParameterTypes(), aParameterTypes)) {
				if (aRetVal != null)
					return null;
				aRetVal = aMethod;
				// return aMethod; // there can be other matching methods
			}
		}
		return aRetVal;
	}

	public static Method findMethod(Class aJavaClass, Method aProxy) {
		return findMethod(aJavaClass, aProxy.getName(),
				aProxy.getParameterTypes());
	}
	public static boolean matchesMethods(Class aJavaClass, Method[] aProxyMethods) {
		for (Method aProxyMethod:aProxyMethods) {
			if (findMethod(aJavaClass, aProxyMethod) == null)
				return false;
		}
		return true;
	}

	// returns the first matching method
	public static Method findMethod(Class aJavaClass, String aName,
			Class[] aParameterTypes) {
		try {
			String aTag = aName + ":" + Arrays.toString(aParameterTypes);
			Method aMethod = tagsToMethod.get(aTag);
			if (aMethod == null) {

				List<Method> aMethods = findMethod(aJavaClass, aName);
				aMethod = selectMethod(aMethods, aParameterTypes);
				if (aMethod == null) {
					// should we now try again with null name and select all methods 
					// and use argument types instead
					aMethod = Object.class.getMethod("wait");
				}
				tagsToMethod.put(aTag, aMethod);
			}
			if (aMethod == Object.class.getMethod("wait")) {
				return null;
			}
			return aMethod;
			// Method aRetVal = null;
			// for (Method aMethod: aMethods) {
			// if (Arrays.equals(aMethod.getParameterTypes(), aParameterTypes))
			// {
			// return aMethod; // there can be other matching methods
			// }
			// }
			// return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public static List<Method> findMethod(Class aJavaClass, String aName) {
		// return findMethod(aJavaClass, null, aName, toRegex(aName),
		// toRegex(aName)); //why was there no name,
		String[] aTags = aName == null?emptyStringArray:new String[] { aName };
		return findMethod(aJavaClass, aName, 
				aTags,
//				new String[] { aName },
				toRegex(aName), toRegex(aName)); // to be consistent with clases
													// should not have regexes

	}

	public static String[] emptyStringArray = {};

	public static List<Method> findMethod(Class aJavaClass, String aName,
			String[] tag) {

		return findMethod(aJavaClass, aName, tag, toRegex(aName),
				tag.length == 0 ? "" : toRegex(tag[0]));
	}

	public static List<Method> findMethod(Class aJavaClass, String aName,
			String aTag, String aNameMatch, String aTagMatch) {
		return findMethod(aJavaClass, aName, new String[] { aTag }, aNameMatch,
				aTagMatch);
	}
	public static Class[] toClasses (Object[] anObjects) {
		Class[] aClasses = new Class[anObjects.length];
		for (int i = 0; i < anObjects.length; i++) {
			aClasses[i] = anObjects[i].getClass();
		}
		return aClasses;
	}
	
	static Map<Class, Class> classToType = new HashMap();
	static {
		classToType.put(Integer.class, Integer.TYPE);
		classToType.put(Long.class, Long.TYPE);
		classToType.put(Short.class, Short.TYPE);
		classToType.put(Double.class, Double.TYPE);
		classToType.put(Boolean.class, Boolean.TYPE);
		classToType.put(Character.class, Character.TYPE);
		// more later

	}
	
	
	public static void toPrimitiveTypes(Class[] aClasses) {
		for (int i = 0; i < aClasses.length; i++ ) {
			Class aMaybePrimitive = classToType.get(aClasses[i]);
			if (aMaybePrimitive != null) {
				aClasses[i] = aMaybePrimitive;
			}
		}
	}
	
	public static Object createInstance(Class aProxyClass) {
		return createInstance(aProxyClass, emptyClassArray, new Object[]{});
	}
	
	public static Object createInstance(Class aProxyClass, Object[] anArgs) {
		Class[] aParameterTypes = toClasses(anArgs);
		toPrimitiveTypes(aParameterTypes);
		return createInstance(aProxyClass, aParameterTypes, anArgs);
	}

	public static Object createInstance(Class aProxyClass,
			Class[] aConctructArgsTypes, Object[] anArgs) {
		try {
			Class anActualClass = IntrospectionUtil.findClass(
					CurrentProjectHolder.getCurrentProject(), aProxyClass);
			if (anActualClass == null) {
				return null;
			}
			Constructor aConstructor = anActualClass
					.getConstructor(aConctructArgsTypes);
//			Object anActualObject = aConstructor.newInstance(anArgs);
			Object anActualObject = ExecutionUtil.timedInvoke(aConstructor, anArgs);

			InvocationHandler aHandler = new AGradedClassProxyInvocationHandler(
					anActualObject);
			return Proxy.newProxyInstance(aProxyClass.getClassLoader(), // should this not be actual class?
					aProxyClass.getInterfaces(), aHandler);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static List<Method> findMethod(Class aJavaClass, String aName,
			String[] aTag, String aNameMatch, String aTagMatch) {
		List<Method> result = new ArrayList();
		if (aName != null)
			result = findMethodByName(aJavaClass, aName);
		if (result != null && !result.isEmpty())
			return result;
		if (aTag != null)
			result = findMethodsByTag(aJavaClass, aTag);
		if (result != null && !result.isEmpty())
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
	

	public static Class getCommonInterface(Project project,
			String[][] aClassDescriptions) {
		List<Class> interfaces = new ArrayList<>(5);
		for (String[] classDescriptions : aClassDescriptions) {
			Class aClass = IntrospectionUtil.findClass(project,
					classDescriptions[0], classDescriptions[1],
					classDescriptions[2], classDescriptions[3]);
			if (aClass == null)
				continue;

			List<Class> tokenInterfaces = IntrospectionUtil
					.getAllInterfaces(aClass);
			if (tokenInterfaces.size() == 0) {
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
	// public static String setPropertyInteractive (Class aClass, Method
	// aWriteMethod, Object aValue) {
	//
	//
	// }
	// public static void main (String[] args) {
	// String[] a1 = {"Hello", new String ("Goodbye")};
	// String[] a2 = {"Goodbye", "Hello"};
	// Set<String> a1Set = new HashSet( Arrays.asList(a1));
	// Set<String> a2Set = new HashSet( Arrays.asList(a2));
	// System.out.println (a1Set.equals(a2Set));
	// }
}
