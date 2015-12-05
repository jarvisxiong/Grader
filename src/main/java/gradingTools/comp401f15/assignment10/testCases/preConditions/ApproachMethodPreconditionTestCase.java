package gradingTools.comp401f15.assignment10.testCases.preConditions;

import gradingTools.comp401f15.assignment6.testcases.commands.methods.*;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCase;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.ExecutionUtil;
import grader.util.IntrospectionUtil;
import gradingTools.sharedTestCase.MethodExecutionTestCase;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodEnvironment;
import util.trace.TraceableBus;
import util.trace.TraceableListener;

import static grader.util.ExecutionUtil.restoreOutputAndGetRedirectedOutput;

import java.beans.PropertyChangeEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import bus.uigen.ObjectEditor;
import bus.uigen.oadapters.ObjectAdapter;
import bus.uigen.trace.ObjectAdapterReceivedPropertyChangeEvent;
import bus.uigen.trace.PropertyChangeEventInfo;

/**
 *
 * @author Andrew
 */
public class ApproachMethodPreconditionTestCase extends BasicTestCase implements TraceableListener {

    public ApproachMethodPreconditionTestCase() {
        super("Approach Method Precondition Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {
    	ExecutionUtil.redirectOutput();
    	Class bridgeSceneClass = IntrospectionUtil.getOrFindClass(project, this, "BridgeScene");
        List<Method> approachMList = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "approach");
        if (approachMList == null || approachMList.isEmpty()) {
            return fail("Can't find approach method in class " + bridgeSceneClass.getTypeName());
        }
        Method approach = approachMList.get(0);
        Constructor<?> bridgeSceneConstructor;
        try {
            bridgeSceneConstructor = bridgeSceneClass.getConstructor();
        } catch (NoSuchMethodException | SecurityException ex) {
            return fail("Can't access BridgeScene constructor");
        }
        if (bridgeSceneConstructor == null) {
            return fail("Can't find empty BridgeScene constructor");
        }
        
        Method say = null;
        Method passed = null;
        Method failed = null;
        Method preSay = null;
        Method prePassed = null;
        Method preFailed = null;
        Method preApproach = null;
        Method getArthur = null;
        Method getLancelot = null;
        
        try {

            say = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "say").get(0);
            getArthur = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Arthur").get(0);
            getLancelot = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Lancelot").get(0);

            passed = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "passed").get(0);
            failed = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "failed").get(0);

            prePassed = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "prePassed").get(0);
            preApproach = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "preApproach").get(0);
            preFailed = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "preFailed").get(0);
            prePassed = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "prePassed").get(0);


        } catch (Exception e) {
            return fail("Can't find at least one of the following: say, getArthur, getLancelot, passed, failed");
        }

//        boolean[] results = checkMovement(bridgeSceneConstructor, approach, say, passed, failed, getArthur, getLancelot);
        boolean[] results = checkMovement(bridgeSceneConstructor, approach, preSay, passed, failed, getArthur, getLancelot, preApproach, prePassed, preFailed);

        int correct = count(results, true);
        if (correct == 0) {
            return fail("Incorrect or no approach");
        } else if (correct == 3) {
            return pass();
        } else {
            int raw = results[0] ? 1 : 0;
            raw += results[1] ? 2 : 0;
            raw += results[2] ? 2 : 0;
            double score = ((double)raw) / 5;
            String message = buildMessage(results);
            return partialPass(score, message);
        }
        } finally {
        	String anOutput = restoreOutputAndGetRedirectedOutput();
            if (anOutput != null && !anOutput.isEmpty()) {
             	System.out.println(anOutput);
                RunningProject.appendToTranscriptFile(project, getCheckable().getName(), anOutput);
            }
        }
    }
    
    protected boolean[] checkMovement(Constructor<?> bridgeSceneConstructor,
    		Method approach, 
    		Method say,
    		Method passed,
    		Method failed,
    		Method getArthur, 
    		Method getLancelot,
    		Method preApproach,
    		Method prePassed,
    		Method preFailed) {
        boolean[] ret = new boolean[]{false, false, false};
        // should also call pre methods
        MethodEnvironment[] methods = new MethodEnvironment[]{
            MethodEnvironment.get(getArthur),                               // 1
            MethodEnvironment.get(getLancelot),                             // 2
           
            MethodEnvironment.get(approach, MethodExecutionTestCase.M0_RET),// 5
            MethodEnvironment.get(preApproach),// approach == false
            MethodEnvironment.get(prePassed),// passed == true
            MethodEnvironment.get(preFailed),// failed == true
            MethodEnvironment.get(say, "Arthur"),// 5
            MethodEnvironment.get(passed),
            MethodEnvironment.get(preApproach),// approach == true
            MethodEnvironment.get(preFailed),// failed == false
            MethodEnvironment.get(prePassed),// passed == false
            MethodEnvironment.get(approach, MethodExecutionTestCase.M1_RET),// let us not check approach again            
            MethodEnvironment.get(say, "Lancelot"),
            MethodEnvironment.get(failed), 
            MethodEnvironment.get(preApproach),// approach == true
            MethodEnvironment.get(preFailed),// failed == false
            MethodEnvironment.get(prePassed),// passed == false

        };
        Object bridgeSceneInstance = null;
        try {
         bridgeSceneInstance = ExecutionUtil.timedInvokeWithExceptions(bridgeSceneConstructor, new Object[]{});
        } catch (Exception e) {
        	return new boolean[] {false};
        	
        }
        ObjectAdapter anObjectAdapter = ObjectEditor.toObjectAdapter(bridgeSceneInstance);
        preTags.clear(); 
        preValues.clear();
        TraceableBus.addTraceableListener(this);
        Object[] exData = MethodExecutionTestCase.invoke(bridgeSceneInstance, methods);
        TraceableBus.removeTraceableListener(this);
        System.out.println("Pre tags:" + preTags);
        System.out.println("Pre values:" + preValues);

       
        
        return ret;
    }
//    protected Integer getTransition (String aTag, Boolean fromValue, int fromIndex) {
//    	boolean foundOriginal = false;
//    	
//    	for (int i = fromIndex; i < preTags.size(); i++ ) {
//    		if (!foundOriginal && aTag.equals(preTags.get(i)) && 
//    				fromValue.equals(preValues.get(i))){
//    			foundOriginal = true;
//    		}
//    	}
//    	
//    }
    
    private static int checkEqualValue(Object[] resutls, int a, int b) {
        return checkCompare(resutls, a, b, 0);
    }
    
    private static int checkLTValue(Object[] resutls, int a, int b) {
        return checkCompare(resutls, a, b, -1);
    }
    
    private static int checkGTValue(Object[] resutls, int a, int b) {
        return checkCompare(resutls, a, b, 1);
    }
    
    private static int checkCompare(Object[] results, int a, int b, int sign) {
        if (results.length < a || results.length < b) {
            return 1; // checking out of bounds
        }
        Object oA = results[a];
        Object oB = results[b];
        if (oA instanceof Exception || oB instanceof Exception) {
            return 2; // error in execution
        }
        if (!(oA instanceof Comparable) || !(oB instanceof Comparable)) {
            return 3; // not comparable
        }
        Comparable cA = (Comparable)oA;
        Comparable cB = (Comparable)oB;
        if (Math.signum(cA.compareTo(cB)) != sign) {
            return 4; // check comparison
        }
        
        return 0;
    }
    
    private static String buildMessage(boolean[] notes) {
        StringBuilder ret = new StringBuilder();
        if (notes[0] == false) {
            ret.append("Does not set occupied properly\n");
        }
        if (notes[1] == false) {
            ret.append("Does not move knight when not occupied\n");
        }
        if (notes[2] == false) {
            ret.append("Moves knigth when occupied");
        }
        return ret.toString();
    }
    
    private static int count(boolean[] arr, boolean value) {
        int count = 0;
        for(boolean bool : arr) {
            if (bool == value) {
                count ++;
            }
        }
        return count;
    }
    protected List<Object> preTags = new ArrayList();
    protected List<Object> preValues = new ArrayList();
    protected void getPre (PropertyChangeEvent anEvent) {
    	Object tag = anEvent.getOldValue();
    	int i = 0;
    	if (!"this".equalsIgnoreCase(anEvent.getPropertyName()) 
//    			|| !(newValue instanceof String) 
    			) 
    			return ;
    	preTags.add(tag);
    	preValues.add(anEvent.getNewValue());
    }

    public void newEvent(Exception aTraceable) {
		if (aTraceable instanceof ObjectAdapterReceivedPropertyChangeEvent) { // multiple PropertyChangeeventInfo will be sent
			PropertyChangeEventInfo aPropertyChange = (PropertyChangeEventInfo) aTraceable;
			getPre(aPropertyChange.getPropertyChangeEvent());
//			System.out.println("Property change:" + aPropertyChange.getPropertyChangeEvent() );
			
			}
			
			
				
	}
}
