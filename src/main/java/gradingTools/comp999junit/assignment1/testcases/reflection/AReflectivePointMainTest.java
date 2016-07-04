package gradingTools.comp999junit.assignment1.testcases.reflection;

import grader.basics.execution.BasicProjectExecution;
import gradingTools.comp999junit.assignment1.testables.wrongangle.Main;
import gradingTools.comp999junit.assignment1.testcases.multi.MultiAbstractPointMainTest;

import org.junit.Test;

import util.annotations.MaxValue;

@MaxValue(5)
public class AReflectivePointMainTest extends MultiAbstractPointMainTest {

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
		return BasicProjectExecution.callCorrespondingMain(Main.class, anArgs, anInput).out;


	}
	@Test
	public void test() {
		testMain(10, 10, 14.142, Math.PI/4); // 45 degree angle
	}
	

	

}
