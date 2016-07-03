package gradingTools.sharedTestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.ClassesManager;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

public class MethodFinderAndRunner extends BasicTestCase {

	private MethodMatcher matcher;
	private Object[][] argSets;
	private Object[] expectedRetVals;
	private long timeout;

	private RunnableMethod runnableMethod;

	protected String notes;
	protected double percentage;

	boolean throwNotAutomateable = false;
	boolean correctOutput;

	public MethodFinderAndRunner(MethodMatcher matcher, Object[][] argSets,
			Object[] expectedRetVals, long timeout) {
		super("Method " + matcher.getLabel() + " test case");
		this.matcher = matcher;
		this.expectedRetVals = expectedRetVals;
		this.argSets = argSets;
		this.timeout = timeout;
	}

	private void runMethod(RunnableMethod method) {
		int modifiers = method.getMethod().getModifiers();
		if (!Modifier.isPublic(modifiers)) {
			throwNotAutomateable = true;
			return;
		}

		for (int i = 0; i < argSets.length; i++) {
			Object[] args = argSets[i];
			Object expectedRetVal = expectedRetVals[i];
			try {
				Object retVal = method.run(args);
				String argsStr = "(";
				for (Object arg : args) {
					argsStr += arg + ",";
				}
				argsStr = argsStr.substring(0, argsStr.length() - 1) + ")";
				System.out.println("args:" + argsStr);
				System.out.println("expected output:" + expectedRetVal);
				System.out.println("actual output:  " + retVal);
				if (expectedRetVal == null) {
					correctOutput = retVal != null;
				} else {
					correctOutput = expectedRetVal.equals(retVal);
					if (!correctOutput && retVal != null && retVal instanceof String) {
						correctOutput = expectedRetVal.equals(((String) retVal).trim());
					}
				}
				if (!correctOutput) {
					return;
				}
			} catch (IllegalArgumentException e) {
				percentage -= 0.5;
				notes += "Method " + method.getMethod().getName()
						+ " does not take the correct arguments\n";
			} catch (IllegalAccessException e) {
				throwNotAutomateable = true;
				return;
			} catch (InvocationTargetException e) {
				throwNotAutomateable = true;
				return;
			} catch (InstantiationException e) {
				throwNotAutomateable = true;
				return;
			}
		}
	}

	private RunnableMethod findMethod(ClassesManager manager) {
		for (ClassDescription description : manager.getClassDescriptions()) {
			Class<?> javaClass = description.getJavaClass();
			for (Method method : javaClass.getDeclaredMethods()) {

				if (matcher.matches(method)) {
					return new RunnableMethod(javaClass, method);
				}
			}
		}
		return null;
	}

	private RunnableMethod findMethodIgnoreCase(ClassesManager manager) {
		for (ClassDescription description : manager.getClassDescriptions()) {
			Class<?> javaClass = description.getJavaClass();
			for (Method method : javaClass.getDeclaredMethods()) {

				if (matcher.matchesIgnoreCase(method)) {
					return new RunnableMethod(javaClass, method);
				}
			}
		}
		return null;
	}

	@Override
	public synchronized TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {

		// You can get the classes and information about them from the project
		// using the ClassesManager
		if (project.getClassesManager().isEmpty())
			throw new NotAutomatableException();
		ClassesManager manager = project.getClassesManager().get();
		if (manager.getClassDescriptions().isEmpty())
			throw new NotGradableException();
		notes = "";
		percentage = 1.0;

		runnableMethod = findMethod(manager);
		if (runnableMethod == null) {
			runnableMethod = findMethodIgnoreCase(manager);
			if (runnableMethod == null) {
				return fail("No method " + matcher.getLabel());
			} else {
				notes += "- Method " + runnableMethod.getMethod().getName()
						+ "does not use proper capitalization\n";
				percentage -= 0.25;
			}
		}

		final Thread parentThread = Thread.currentThread();
		throwNotAutomateable = false;
		correctOutput = true;
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				runMethod(runnableMethod);
				parentThread.interrupt();
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
		try {
			Thread.sleep(timeout);
			if (thread.isAlive()) {
				// thread.stop();
			}
			notes += "- Method " + runnableMethod.getMethod().getName()
					+ " did not complete within " + ((double) timeout / 1000)
					+ "seconds.  Likely an infinite loop.\n";
			percentage -= 0.5;
		} catch (InterruptedException e) {
			// If we reach here, the child thread interrupted us because it
			// finished running the method
		}

		if (throwNotAutomateable) {
			throw new NotAutomatableException();
		}

		if (!correctOutput) {
			percentage -= 0.5;
			notes += "- Method " + runnableMethod.getMethod().getName()
					+ " does not generate the correct output.\n";
		}

		if (percentage == 1.0) {
			return pass();
		}

		return partialPass(percentage, notes);

	}
}
