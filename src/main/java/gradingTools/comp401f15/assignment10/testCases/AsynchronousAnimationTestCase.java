package gradingTools.comp401f15.assignment10.testCases;

import grader.basics.junit.TestCaseResult;
import gradingTools.comp401f15.assignment11.testcases.CommandInterpreterAnimationTestCase;
import util.trace.TraceableListener;

public class AsynchronousAnimationTestCase extends 
	CommandInterpreterAnimationTestCase  implements TraceableListener{
	public AsynchronousAnimationTestCase(String methodTag) {
		super(methodTag);
	}
//	protected void waitForThreads() {
//		ThreadSupport.sleep(2000);
//	}
//	protected void callAsynchronousMethods() {
//		Object retVal = ExecutionUtil.timedInvoke(commandInterpreter, foundMethod);
//		retVal = ExecutionUtil.timedInvoke(commandInterpreter, foundMethod);
//		
//	}

	protected synchronized TestCaseResult computeResult() {
		if (childThread1 == null) {
			return fail ("No property notification from a thread");
		}
		if (eventInParentThread 
//				|| numThreadsAfterExecution <= numThreadsBeforeExecution
				) {
  			return fail ("No threads created");
  		}
		int numThreadsCreated = numThreadsAfterExecution - numThreadsBeforeExecution;
		if (numThreadsCreated < 1) {
			System.out.println("num threads after execution == before execution");
			return partialPass(0.2, "No  thread created (maybe race conditions) by:" + foundMethod);
		}
		return pass();
//		if (childThread2 == null) {
////			return fail ("No property notification from second thread");
//			return pass ();
//
//		}
//		if (child2AfterChild1 && !child1AfterChild2) {
//			return pass();
//		}	
//		return partialPass(0.3, "Interleaved threads");
	}

}
