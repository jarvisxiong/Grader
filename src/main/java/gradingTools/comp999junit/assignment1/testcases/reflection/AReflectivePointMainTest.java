package gradingTools.comp999junit.assignment1.testcases.reflection;

import java.lang.reflect.Method;

import org.junit.Test;

import framework.project.CurrentProjectHolder;
import grader.util.ExecutionUtil;
import grader.util.IntrospectionUtil;
import gradingTools.comp999junit.assignment1.testcases.AnAbstractPointMainTest;
import gradingTools.comp999junit.assignment1.testcases.AnAbstractPointTest;
import gradingTools.testables.comp999junit.assignment1.wrongangle.Main;


public class AReflectivePointMainTest extends AnAbstractPointMainTest {

	@Override
	protected String runMain(String[] anArgs, String... anInput) {
//		try {
//		ExecutionUtil.redirectInputOutput(anInput);		
//		Class aMainClass = IntrospectionUtil.findClass(CurrentProjectHolder.getOrCreateCurrentProject(), Main.class);
//		
//		Method aMainMethod = IntrospectionUtil.findMethod(aMainClass, "main", new Class[] {String[].class});
//		aMainMethod.invoke(aMainClass, new Object[] { new String[]{}});		
//		String anOutput = ExecutionUtil.restoreOutputAndGetRedirectedOutput();
//		return anOutput;	
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "";
//		}
//		return ExecutionUtil.invokeCorrespondingMain(Main.class, anInput, anArgs);
//		return ExecutionUtil.forkProjectMain(Main.class, anInput, anArgs);
		return ExecutionUtil.callCorrespondingMain(Main.class, anArgs, anInput);


	}
	@Test
	public void test() {
		testMain(10, 10, 14.142, Math.PI/4); // 45 degree angle
	}
	

	

}
