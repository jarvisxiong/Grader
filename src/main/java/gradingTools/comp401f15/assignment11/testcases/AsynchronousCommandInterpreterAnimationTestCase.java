package gradingTools.comp401f15.assignment11.testcases;

import static grader.util.ExecutionUtil.restoreOutputAndGetRedirectedOutput;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Objects;

import bus.uigen.ObjectEditor;
import bus.uigen.oadapters.ObjectAdapter;
import bus.uigen.trace.PropertyChangeEventInfo;
import framework.execution.RunningProject;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.ExecutionUtil;
import grader.util.IntrospectionUtil;
import gradingTools.sharedTestCase.MethodDefinedTestCase;
import gradingTools.sharedTestCase.MethodExecutionTestCase;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodEnvironment;
import util.misc.ThreadSupport;
import util.trace.TraceableBus;
import util.trace.TraceableListener;

public  class AsynchronousCommandInterpreterAnimationTestCase extends CommandIntrepreterMethodCallTestCase  implements TraceableListener{
	public AsynchronousCommandInterpreterAnimationTestCase(String methodTag) {
		super("CommandInterpreter", methodTag, Void.TYPE);
	}


	
	protected Thread parentThread;
	protected Thread childThread1;
	protected Thread childThread2;
	protected boolean child2AfterChild1;
	protected boolean child1AfterChild2;
	protected boolean eventInParentThread;
	@Override
	public void newEvent(Exception aTraceable) {
		if (aTraceable instanceof PropertyChangeEventInfo) {
			Thread currentThread = Thread.currentThread();
			if (currentThread == parentThread && !eventInParentThread) {
				
				System.out.println("event in parent thread");
				eventInParentThread = true;

			} else if (childThread1 == null ) {
				childThread1 = currentThread;
				System.out.println("child 1 starts");
			} else if (childThread1 != currentThread && childThread2 == null){
				System.out.println("child 2 starts");
				childThread2 = currentThread;
			} else if (childThread1 != null && childThread2 != null && childThread2 == currentThread) {
				child2AfterChild1 = true;
				System.out.println("child 2 executes after child 1");

			} else if  (childThread1 != null && childThread2 != null && childThread1 == currentThread) {
				child1AfterChild2 = true;
				System.out.println("child 1 executes after child 2");
			}

			
		}
		
	}
	protected void waitForThreads() {
		ThreadSupport.sleep(1000);
	}
	protected void callAsynchronousMethods() {
		Object retVal = ExecutionUtil.timedInvoke(commandInterpreter, foundMethod);
		retVal = ExecutionUtil.timedInvoke(commandInterpreter, foundMethod);
		
	}
	
	protected  TestCaseResult computeResult() {
		if (childThread1 == null) {
			if (eventInParentThread) {
				return fail ("Command not executed in separate thread");
			}
			return fail ("No property notification from thread");
		}
//		if (childThread1 == parentThread) {
//		if (eventInParentThread) {
//
//			return fail ("Command not executed in separate thread");
//		}
		return pass();
	}
//	protected TestCaseResult computeResult() {
//		if (childThread1 == null) {
//			return fail ("No property notification");
//		}
//		if (childThread1 == parentThread) {
//			return fail ("Command not executed in separate thread");
//		}
//		if (child1AfterChild2) {
//			return fail ("Interleaved threads");
//		}
//		return pass();
//	}
	protected void stopThread(Thread aThread) {
		System.out.println ("Stropping thread:" + aThread);
//		aThread.interrupt();
		aThread.suspend();
		aThread.stop();
	}
	@Override
	protected TestCaseResult callMethods() {
		try {
		System.out.println("calling methods");
		parentThread = null;
		childThread1 = null;
		childThread2 = null;
		child2AfterChild1 = false;
		child1AfterChild2 = false;
		eventInParentThread = false;
		parentThread = Thread.currentThread();
  		ObjectAdapter anAdapter = ObjectEditor.toObjectAdapter(bridgeSceneInstance);
		System.out.println ("Adding traceable listener");
  		TraceableBus.addTraceableListener(this);
  		callAsynchronousMethods();
  		System.out.println ("waiting for threads");
//		computeResult();
		waitForThreads();
  		System.out.println ("Finished waiting for threads");
  		if (eventInParentThread) {
  			System.out.println ("Found Event in Parent Thread");
  		}

		if (childThread1 != null) {
			stopThread(childThread1);
		}
		if (childThread2 != null) {
			stopThread(childThread2);
//			childThread1.interrupt();
//			childThread1.suspend();
//			childThread2.stop();
		}
		// TODO Auto-generated method stub
		return computeResult();
		} finally {
			processFinally();
		}
	}
	protected void processFinally() {
		System.out.println ("Removing traceable listener");
		TraceableBus.removeTraceableListener(this);

		// TODO Auto-generated method stub
		
	}
}
