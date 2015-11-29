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

//	public SynchronizedAnimationTestCase(String classTag,String methodTag,  Object returnType, Object... parameterTypes) {
//		super(classTag, methodTag, returnType, parameterTypes);
//	}
//	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
//
//		parentThread = null;
//		childThread1 = null;
//		childThread2 = null;
//		child2AfterChild1 = false;
//		child1AfterChild2 = false;
//		TestCaseResult superValue = super.test(project, autoGrade);
//		if (foundMethod == null) {
//			return fail (superValue.getNotes());
//		}
//        ExecutionUtil.redirectOutput();
//
//		try {
//            Class<?> commandInterpreterClass = IntrospectionUtil.getOrFindClass(project, this, "CommandInterpreter");
//            Class<?> bridgeSceneClass = IntrospectionUtil.findClass(project, null, "BridgeScene", ".*[bB]ridge.*[sS]cene.*", ".*[bB]ridge[sS]cene.*");
//            Class<?> scannerBeanClass = IntrospectionUtil.findClass(project, null, "ScannerBean", ".*[sS]canner.*[bB]ean.*", ".*[sS]canner[bB]ean.*");
//
//            Constructor<?> commandInterpreterConstructor = null;
//            Constructor<?> bridgeSceneConstructor;
//            Constructor<?> scannerBeanConstructor;
//
//            boolean bridgeFirst = true;
//            try {
//                Constructor<?>[] commandInterpreterConstructors = commandInterpreterClass.getConstructors();
//                for(Constructor<?> c : commandInterpreterConstructors) {
//                    Object[] params = c.getParameterTypes();
//                    if (params.length != 2) {
//                        continue;
//                    }
//                    if (((Class<?>)params[0]).isAssignableFrom(bridgeSceneClass)
//                            && ((Class<?>)params[1]).isAssignableFrom(scannerBeanClass)) {
//                        commandInterpreterConstructor = c;
//                        bridgeFirst = true;
//                    } else if (((Class<?>)params[0]).isAssignableFrom(scannerBeanClass)
//                            && ((Class<?>)params[1]).isAssignableFrom(bridgeSceneClass)) {
//                        commandInterpreterConstructor = c;
//                        bridgeFirst = false;
//                    }
//                }
//                Objects.requireNonNull(commandInterpreterConstructor);
//                bridgeSceneConstructor = bridgeSceneClass.getConstructor();
//                scannerBeanConstructor = scannerBeanClass.getConstructor();
//            } catch(Exception e) {
//                e.printStackTrace(System.out);
//                return fail("Couldn't find correct constructor for CommandInterpreter, BridgeScene, or ScannerBean");
//            }
//            boolean[] ret = new boolean[3];
//            Object scannerBeanInstance = ExecutionUtil.timedInvoke(scannerBeanConstructor, new Object[]{});
//            Object bridgeSceneInstance = ExecutionUtil.timedInvoke(bridgeSceneConstructor, new Object[]{});
//            
//                   
//            
//            Object aCommandInterpreter;
//            if (bridgeFirst) {
//                aCommandInterpreter = ExecutionUtil.timedInvoke(commandInterpreterConstructor,
//                    new Object[]{bridgeSceneInstance, scannerBeanInstance});
//            } else {
//            	aCommandInterpreter = ExecutionUtil.timedInvoke(commandInterpreterConstructor,
//                    new Object[]{scannerBeanInstance, bridgeSceneInstance});
//            }
//            if (aCommandInterpreter == null || bridgeSceneInstance == null)
//            	return fail("Could not instantiate aCommand Interpreter");
//            parentThread = Thread.currentThread();
//    		ObjectAdapter anAdapter = ObjectEditor.toObjectAdapter(bridgeSceneInstance);
//    		TraceableBus.addTraceableListener(this);
//
//
//
//    		Object retVal = ExecutionUtil.timedInvoke(aCommandInterpreter, foundMethod);
//    		retVal = ExecutionUtil.timedInvoke(aCommandInterpreter, foundMethod);
//
//    		Thread.sleep(2000);
//    		if (childThread1 == null) {
//    			return fail ("No property notification");
//    		}
//    		if (childThread1 == parentThread) {
//    			return fail ("Command not executed in separate thread");
//    		}
//    		if (child1AfterChild2) {
//    			return fail ("Interleaved threads");
//    		}
//    		return pass();
////    		if (childThread1 != parentThread) {
////    			childThread1.stop();
////    			return pass ();
////    		}
////    		System.out.println("Executed method");
//
//		} catch (Exception e) {
//			return fail("Could not instantiate aCommand Interpreter");
//		} finally {
//			TraceableBus.removeTraceableListener(this);
//            String anOutput = restoreOutputAndGetRedirectedOutput();
//            if (anOutput != null && !anOutput.isEmpty()) {
//             	System.out.println(anOutput);
//             	RunningProject.appendToTranscriptFile(project, getCheckable().getName(), anOutput);
//            }
//        }
////		return superValue;
//		
//		
//	}
	
	Thread parentThread, childThread1, childThread2;
	protected boolean child2AfterChild1;
	protected boolean child1AfterChild2;
	@Override
	public void newEvent(Exception aTraceable) {
		if (aTraceable instanceof PropertyChangeEventInfo) {
			Thread currentThread = Thread.currentThread();
			if (childThread1 == null ) {
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
//			
//			else if ()
//			if (childThread2 == null ) {
//				childThread1 = Thread.currentThread();
//			} 
//			
//			else if (childThread1 != null && childThread)
//			childThread1 = Thread.currentThread();
//			PropertyChangeEventInfo aPropertyChange = (PropertyChangeEventInfo) aTraceable;
//			System.out.println("Property change event:" + aTraceable);
//			newThread.stop();
//			System.out.println ("New thread == old thread:" + (newThread == initialThread));
			
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
			return fail ("No property notification");
		}
		if (childThread1 == parentThread) {
			return fail ("Command not executed in separate thread");
		}
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
	@Override
	protected TestCaseResult callMethods() {
		System.out.println("calling methods");
		parentThread = null;
		childThread1 = null;
		childThread2 = null;
		child2AfterChild1 = false;
		child1AfterChild2 = false;
		parentThread = Thread.currentThread();
  		ObjectAdapter anAdapter = ObjectEditor.toObjectAdapter(bridgeSceneInstance);
  		TraceableBus.addTraceableListener(this);
  		callAsynchronousMethods();
//		computeResult();
		waitForThreads();
		if (childThread1 != null) {
			childThread1.stop();
		}
		if (childThread2 != null) {
			childThread2.stop();
		}
		// TODO Auto-generated method stub
		return computeResult();
	}
	@Override
	protected void processFinally() {
		TraceableBus.removeTraceableListener(this);

		// TODO Auto-generated method stub
		
	}
}
