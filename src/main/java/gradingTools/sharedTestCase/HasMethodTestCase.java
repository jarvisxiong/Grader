package gradingTools.sharedTestCase;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;
import gradingTools.sharedTestCase.utils.MethodPropertyChecker;

public class HasMethodTestCase extends BasicTestCase {
	private String methodName;
	private String requiredClassName;
	private ArrayList<String> ignoredClasses;
	private Class<?> returnType;
	private String returnTypeName;
	private Class<?>[] expectedParamTypes;
	private ArrayList<MethodPropertyChecker> propertyCheckers = new ArrayList<MethodPropertyChecker>();

	// Class is not known, return type is standard class, and has parameters
	public HasMethodTestCase(String methodName, ArrayList<String> ignoredClasses,
			Class<?> returnType, Class<?>[] expectedParameters) {
		super("Class contains a working " + methodName + " test case");
		init(methodName, null, ignoredClasses, returnType, null, expectedParameters);
	}

	// Class is not known, return type is not a standard class, and has
	// parameters
	public HasMethodTestCase(String methodName, ArrayList<String> ignoredClasses,
			String returnType, Class<?>[] expectedParameters) {
		super("Class contains a working " + methodName + " test case");
		init(methodName, null, ignoredClasses, null, returnType, expectedParameters);
	}

	// Class is not known, return type is standard class, and has no parameters
	public HasMethodTestCase(String methodName, ArrayList<String> ignoredClasses,
			Class<?> returnType) {
		super("Class contains a working " + methodName + " test case");
		init(methodName, null, ignoredClasses, returnType, null, null);
	}

	// Class is not known, return type is not a standard class, and has no
	// parameters
	public HasMethodTestCase(String methodName, ArrayList<String> ignoredClasses, String returnType) {
		super("Class contains a working " + methodName + " test case");
		init(methodName, null, ignoredClasses, null, returnType, null);
	}

	// All parts known and return type is a standard class
	public HasMethodTestCase(String methodName, String requiredClassName,
			ArrayList<String> ignoredClasses, Class<?> returnType, Class<?>[] expectedParameters) {
		super("Class contains a working " + methodName + " test case");
		init(methodName, requiredClassName, ignoredClasses, returnType, null, expectedParameters);
	}

	// All parts known and return type is not a standard class
	public HasMethodTestCase(String methodName, String requiredClassName,
			ArrayList<String> ignoredClasses, String returnType, Class<?>[] expectedParameters) {
		super("Class contains a working " + methodName + " test case");
		init(methodName, requiredClassName, ignoredClasses, null, returnType, expectedParameters);
	}

	private void init(String methodName, String requiredClassName,
			ArrayList<String> ignoredClasses, Class<?> returnType, String returnTypeName,
			Class<?>[] expectedParameters) {

		this.requiredClassName = requiredClassName;
		this.ignoredClasses = ignoredClasses;
		this.methodName = methodName;
		this.returnType = returnType;
		this.returnTypeName = returnTypeName;
		this.expectedParamTypes = expectedParameters;

	}
	
	public void addPropertyChecker(MethodPropertyChecker propertyChecker) {
		propertyCheckers.add(propertyChecker);
	}

	private String getParametersMessage(Method method) {
		Class<?>[] paramTypes = method.getParameterTypes();
		if (paramTypes.length == 0) { // If there are no parameters
			if (expectedParamTypes == null || expectedParamTypes.length == 0) {
				return "method takes in a parameter when it should not";
			} else {
				return "";
			}
		} else {
			if (expectedParamTypes == null || expectedParamTypes.length == 0) {
				return "method does not take in a parameter when it should";
			}
			if (expectedParamTypes.length != paramTypes.length) {
				return "method has an incorrect number of parameters";
			}
			for (int i = 0; i < paramTypes.length; i++) {
				if (!paramTypes[i].equals(expectedParamTypes[i])) {
					return "method takes in at least one incorrect parameter";
				}
			}
			return "";
		}
	}

	private boolean checkReturnType(Method method) {
		if (returnType != null) {
			return method.getReturnType().equals(returnType);
		} else {
			return method.getReturnType().getName().equalsIgnoreCase(returnTypeName);
		}
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		if (project.getClassesManager().isEmpty()) {
			throw new NotAutomatableException();
		}
		ClassesManager manager = project.getClassesManager().get();

		for (ClassDescription description : manager.getClassDescriptions()) {
			Class<?> javaClass = description.getJavaClass();

			// Skip if it is an ignored class
			boolean isIgnoredClass = false;
			for (String ignoredClassName : ignoredClasses) {
				if (ignoredClassName.equalsIgnoreCase(javaClass.getName())) {
					isIgnoredClass = true;
					break;
				}
			}
			if (isIgnoredClass) {
				break;
			}

			for (Method method : javaClass.getDeclaredMethods()) {
				boolean correctName = method.getName().toLowerCase()
						.equalsIgnoreCase(requiredClassName);// decided to
																// ignore case
																// here
				boolean correctVisibility = Modifier.isPublic(method.getModifiers()); // should
																						// be
																						// public
				boolean correctReturnType = checkReturnType(method);

				String parameterTypesMessage = getParametersMessage(method);
				boolean correctParameterTypes = parameterTypesMessage.length() == 0;
				boolean checkersPass = true;
				String[] checkerMessages = new String[propertyCheckers.size()];
				for(int i=0; i<propertyCheckers.size(); i++) {
					checkerMessages[i] = propertyCheckers.get(i).getMessageOnIncorrect(method);
					if (checkerMessages[i] != null) {
						checkersPass = false;
					}
				}

				if (correctName && correctVisibility && correctReturnType && correctParameterTypes && checkersPass) {
					return pass();
				} else if (correctName) {
					int incorrectCount = 0;
					String message = "";
					if (!correctVisibility) {
						incorrectCount++;
						message += "method not public, ";
					}
					if (!correctReturnType) {
						incorrectCount++;
						message += "method does not return correct type, ";
					}
					if (!correctParameterTypes) {
						incorrectCount++;
						message += parameterTypesMessage + ", ";
					}
					
					if (!checkersPass) {
						for (String checkerMessage : checkerMessages) {
							if (checkerMessage != null) {
								incorrectCount++;
								message += checkerMessage + ", ";
							}
						}
					}

					message = message.substring(0, message.length() - 2);
					double maxCount = 4.0 + propertyCheckers.size();
					return partialPass((maxCount - incorrectCount) / (maxCount), message);
				}
			}
		}
		return fail("No method " + methodName + " found in program");
	}

}
