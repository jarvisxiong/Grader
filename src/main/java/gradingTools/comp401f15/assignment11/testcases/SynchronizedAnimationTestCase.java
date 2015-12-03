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

public class SynchronizedAnimationTestCase extends AsynchronousCommandInterpreterAnimationTestCase  implements TraceableListener{
	public SynchronizedAnimationTestCase(String methodTag) {
		super(methodTag);
	}


	protected TestCaseResult computeResult() {
		if (childThread1 == null) {
			return fail ("No property notification");
		}
		if (childThread1 == parentThread) {
			return fail ("Command not executed in separate thread");
		}
		if (child1AfterChild2) {
			return fail ("Interleaved threads");
		}
		return pass();
	}

}
