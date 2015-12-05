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
import scala.PartialFunction;
import util.misc.ThreadSupport;
import util.trace.TraceableBus;
import util.trace.TraceableListener;

public class SynchronizedAnimationTestCase extends AsynchronousCommandInterpreterAnimationTestCase  implements TraceableListener{
	public SynchronizedAnimationTestCase(String methodTag) {
		super(methodTag);
	}
	protected void waitForThreads() {
		ThreadSupport.sleep(2000);
	}
	protected void callAsynchronousMethods() {
		Object retVal = ExecutionUtil.timedInvoke(commandInterpreter, foundMethod);
		retVal = ExecutionUtil.timedInvoke(commandInterpreter, foundMethod);
		
	}

	protected TestCaseResult computeResult() {
		if (childThread1 == null) {
			return fail ("No property notification from a thread");
		}
		if (eventInParentThread 
//				|| numThreadsAfterExecution <= numThreadsBeforeExecution
				) {
  			return fail ("No threads created");
  		}
		int numThreadsCreated = numThreadsAfterExecution - numThreadsBeforeExecution;
		if (numThreadsCreated < 2) {
			return partialPass(0.5, "A single thread created:");
		}
		if (childThread2 == null) {
//			return fail ("No property notification from second thread");
			return pass ();

		}
		if (child2AfterChild1 && !child1AfterChild2) {
			return pass();
		}	
		return partialPass(0.3, "Interleaved threads");
	}

}
