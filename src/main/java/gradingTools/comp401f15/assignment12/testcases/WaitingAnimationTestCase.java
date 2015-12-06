package gradingTools.comp401f15.assignment12.testcases;

import java.lang.reflect.Method;
import java.util.List;

import bus.uigen.trace.PropertyChangeEventInfo;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCase;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.ExecutionUtil;
import grader.util.IntrospectionUtil;
import gradingTools.comp401f15.assignment11.testcases.CommandInterpreterAnimationTestCase;
import gradingTools.comp401f15.assignment11.testcases.SynchronizedAnimationTestCase;

public class WaitingAnimationTestCase extends CommandInterpreterAnimationTestCase{
	Method waitMethod1;
	Method waitMethod2;
//	Project project;
	public WaitingAnimationTestCase(String aProceedMethod, String aWaitMethod1, String aWaitMethod2) {
		super(aProceedMethod);
		
	}
	
	public TestCaseResult test(Project aProject, boolean autoGrade) throws NotAutomatableException, NotGradableException {
//		project = aProject;
		return super.test(aProject, autoGrade);
	}
	
//	protected Method getUniqueParameterlessMethod (String aMethodName) {
//		List<Method> methods = IntrospectionUtil.getOrFindMethodList(project, this, commandInterpreterClass, aMethodName, aMethodName);
//		if (methods.size() < 1) {
//			System.out.println ("Could not find:" + aMethodName);
//			throw new NotGradableException();
//		}
//		if (methods.get(0).getParameterTypes().length != 0) {
//			System.out.println (aMethodName + " takes more than one parameter");
//			throw new NotGradableException();
//		}
//		return methods.get(0);
//	}
	boolean proceedAllDone;
	boolean didNotWait;
	protected void callAsynchronousMethods() {
		Method waitMethod1 = getUniqueParameterlessMethod("waitingArthur");
		Method waitMethod2 = getUniqueParameterlessMethod("waitingLancelot");
		Object retVal = ExecutionUtil.timedInvoke(commandInterpreter, waitMethod1);
		retVal = ExecutionUtil.timedInvoke(commandInterpreter, waitMethod2);


		proceedAllDone = false;
		didNotWait = false;
		waitForThreads();

		if (childThread1 != null) {
			System.out.println("child thread started before proceed");
			didNotWait = true;
			return;
			
		}
		retVal = ExecutionUtil.timedInvoke(commandInterpreter, foundMethod);

		
	}
	protected  TestCaseResult computeResult() {
		if (didNotWait) {
			return fail ("Did not wait for startAnimation");
		}
		if (childThread1 != null && childThread2 != null) {
			return pass();			
		}
		if (childThread1 != null) {
			return partialPass(0.5, "Only one thread proceeded");
		}
		return fail ("No proeprty notification after proceedAll");
//		if (childThread1 == parentThread) {
//		if (eventInParentThread) {
//
//			return fail ("Command not executed in separate thread");
//		}

	}

}
