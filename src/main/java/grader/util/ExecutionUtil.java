package grader.util;

import framework.execution.RunningProject;
import framework.grading.testing.Checkable;
import framework.project.ClassDescription;
import framework.project.Project;
import grader.execution.AConstructorExecutionCallable;
import grader.execution.AMethodExecutionCallable;
import grader.execution.AResultWithOutput;
import grader.execution.ResultWithOutput;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import util.misc.Common;
import util.misc.TeePrintStream;
import util.misc.ThreadSupport;
import util.trace.Tracer;

public class ExecutionUtil {
	public static final String PRINTS = "System.out";
	public static final String MISSING_CLASS = "Status.NoClass";
	public static final String MISSING_PROPERTY = "Status.NoProperty";
	public static final String MISSING_WRITE = "Status.NoWrite";
	public static final String MISSING_READ = "Status.NoRead";
	public static final String GETS_EQUAL_SETS = "Status.GetsEqualSets";
	public static final String EXPECTED_EQUAL_ACTUAL = "Status.ExpectedEqualActual";

	public static final String MISSING_CONSTRUCTOR = "Status.MissingConstructor";
	public static final String CLASS_MATCHED = "Status.ClassMatched";
	
	static ExecutorService executor = Executors.newSingleThreadExecutor();


	public static Object timedInvoke(Object anObject, Method aMethod,
			Object[] anArgs, long aMillSeconds) {
//		 ExecutorService executor = Executors.newSingleThreadExecutor();


		Future future = executor.submit(new AMethodExecutionCallable(anObject,
				aMethod, anArgs));

		try {
			return future.get(aMillSeconds, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			future.cancel(true);
			System.out.println("Terminated execution after milliseconds:" + aMillSeconds);
			return null;
		} finally {
//			executor.shutdownNow();
		}

	}
	public static Object timedInvoke(Constructor aMethod,
			Object[] anArgs, long aMillSeconds) {
//		 ExecutorService executor = Executors.newSingleThreadExecutor();


		Future future = executor.submit(new AConstructorExecutionCallable(
				aMethod, anArgs));

		try {
			return future.get(aMillSeconds, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			future.cancel(true);
			System.out.println("Terminated!");
			return null;
		} finally {
			
//			executor.shutdownNow();
		}

	}

	public static ResultWithOutput timedInteractiveInvoke(Object anObject,
			Method aMethod, Object[] anArgs, long aMillSeconds) {
//		 ExecutorService executor = Executors.newSingleThreadExecutor();

//		PrintStream originalOut = System.out;
		

		try {

//			String tmpFileName = "tmpMethodOut.txt" ;
//			FileOutputStream aFileStream = new FileOutputStream(
//					tmpFileName);
//			File tmpFile = new File(tmpFileName);
//			PrintStream teeStream = new TeePrintStream(aFileStream, originalOut);
//			System.setOut(teeStream);
			redirectOutput();

			Callable aCallable = new AMethodExecutionCallable(anObject,
					aMethod, anArgs);
			Future future = executor.submit(aCallable);
			Object aResult = future.get(aMillSeconds, TimeUnit.MILLISECONDS);
			
			String anOutput = restoreOutputAndGetRedirectedOutput();
//
//			aFileStream.flush();
//			aFileStream.close();
//			String anOutput = Common.toText(tmpFile);
//			tmpFile.delete();
			
			return new AResultWithOutput(aResult, anOutput);
		} catch (Exception e) {
			return new AResultWithOutput(null, null);
		} finally {
			System.setOut(originalOut);

		}
	}
	static String tmpFileName = "tmpMethodOut.txt" ;
	static PrintStream originalOut = System.out;
	static FileOutputStream aFileStream;
	static PrintStream teeStream;
	static File tmpFile;
	public static void redirectOutput() {
	 
		try {
			aFileStream = new FileOutputStream(
					tmpFileName);
			tmpFile = new File(tmpFileName);
			PrintStream teeStream = new TeePrintStream(aFileStream, originalOut);
			System.setOut(teeStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public static String restoreOutputAndGetRedirectedOutput() {
		try {
			aFileStream.flush();
			aFileStream.close();
//			ThreadSupport.sleep(2000);
			String anOutput = Common.toText(tmpFile);
			tmpFile.delete();
			return anOutput;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			System.setOut(originalOut);

		}
		return null;
	}
	public static ResultWithOutput timedInteractiveInvoke(
			Constructor aConstructor, Object[] anArgs, long aMillSeconds) {
//		 ExecutorService executor = Executors.newSingleThreadExecutor();

//		PrintStream originalOut = System.out;
	

		try {
////			System.out.flush();
////			File aTempFile = File.createTempFile("tmpMethodOut", ".txt");
////			File aTempFile = File.createTempFile("tmpMethodOut", ".txt");
//			String tmpFileName = "tmpMethodOut.txt" ;
//			FileOutputStream aFileStream = new FileOutputStream(
//					tmpFileName);
//			File tmpFile = new File(tmpFileName);
//			PrintStream teeStream = new TeePrintStream(aFileStream, originalOut);
//			System.setOut(teeStream);
////			Object aResult = timedInvoke(anObject, aMethod, anArgs,
////					aMillSeconds);
			redirectOutput();
			
			Callable aCallable = new AConstructorExecutionCallable(
					aConstructor, anArgs);
			Future future = executor.submit(aCallable);
			Object aResult = future.get(aMillSeconds, TimeUnit.MILLISECONDS);
			String anOutput = restoreOutputAndGetRedirectedOutput();

////			System.out.flush();
//			aFileStream.flush();
//			aFileStream.close();
////			ThreadSupport.sleep(2000);
//			String anOutput = Common.toText(tmpFile);
//			tmpFile.delete();
			
			return new AResultWithOutput(aResult, anOutput);
		} catch (Exception e) {
			return new AResultWithOutput(null, null);
		} 
//		finally {
//			System.setOut(originalOut);
////			executor.shutdownNow();
//
//		}
	}
	static String[] emptyArgs = {};
	
	public static Map<String, Object> testBean (String aFeatureName, String aCheckName, Project aProject, 
			String[] aBeanDescriptions, 
			Class[] aConstructorArgTypes, 
			Object[] aConstructorArgs, Map<String, Object> anInputs, 
			String[] anOutputProperties ) {
		String anOutput;
		Map<String, Object> anActualOutputs = new HashMap();

		try {
//		String[] aBeanDescriptions = aBeanDescription.split(",");
		if (aBeanDescriptions.length != 4) {
			Tracer.error("Bean description  in testBean should have 4 elements instead of: " + aBeanDescriptions.length);
		}
		redirectOutput();
		System.out.println("Testcase:" + aCheckName);
		System.out.println ("Finding class matching:" + Common.toString(aBeanDescriptions));
		Class aClass = IntrospectionUtil.findClass(aProject, 
        		aBeanDescriptions[0],
        		aBeanDescriptions[1],
        		aBeanDescriptions[2],
        		aBeanDescriptions[3]);
		

		if (aClass == null) {
			System.out.println ("No class matching: " + Common.toString(aBeanDescriptions));
			anActualOutputs.put(MISSING_CLASS, true);
//			anActualOutputs = null;
		} else {
			System.out.println ("Finding constructor matching:" + Common.toString(aConstructorArgTypes));
			anActualOutputs.put(CLASS_MATCHED, aClass.getCanonicalName());
			Constructor aConstructor = aClass.getConstructor(aConstructorArgTypes);
			Object anObject = timedInvoke(aConstructor, aConstructorArgs, 300);
			for (String aPropertyName:anInputs.keySet()) {
				if (aPropertyName == null) continue;
				PropertyDescriptor aProperty = IntrospectionUtil.findProperty(aClass, aPropertyName);
				if (aProperty == null) {
					anActualOutputs.put(MISSING_PROPERTY, true);
					anActualOutputs.put(MISSING_PROPERTY+"." + aPropertyName, true);
//					System.out.println("Property " +  aPropertyName + "not found");
					continue;
				}
				Method aWriteMethod = aProperty.getWriteMethod();
				if (aWriteMethod == null) {
					anActualOutputs.put(MISSING_WRITE, true);
					anActualOutputs.put(MISSING_WRITE +"." + aPropertyName, true);
					System.out.println("Missing write method for property " +  aPropertyName);
					continue;
				}
				Object aValue = anInputs.get(aPropertyName);
				timedInvoke(anObject, aWriteMethod, new Object[]{aValue}, 300);
			}
			for (String anOutputPropertyName:anOutputProperties) {
				if (anOutputPropertyName == null)
					continue;
				PropertyDescriptor aProperty = IntrospectionUtil.findProperty(aClass, anOutputPropertyName);
				if (aProperty == null) {
					
//					System.out.println("Property " +  aPropertyName + "not found");
					continue;
				}
				Method aReadMethod = aProperty.getReadMethod();
				if (aReadMethod == null) {
					System.out.println("Missing read method for property " +  anOutputPropertyName);
					anActualOutputs.put(MISSING_READ, true);
					anActualOutputs.put(MISSING_READ +"." + anOutputPropertyName, true);
					continue;
				}
				Object result = timedInvoke(anObject, aReadMethod, emptyArgs, 300);
				anActualOutputs.put(anOutputPropertyName, result);				
			}
		}
		
		} catch (NoSuchMethodException e) {
			System.out.println("Constructor not found");
			anActualOutputs.put(MISSING_CONSTRUCTOR, true);
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			anActualOutputs = null;

			e.printStackTrace();
		} finally {
			anOutput = restoreOutputAndGetRedirectedOutput();
			 if (anOutput != null && !anOutput.isEmpty()) {
             	RunningProject.appendToTranscriptFile(aProject, aFeatureName, anOutput);
             }
			anActualOutputs.put(PRINTS, anOutput);
			
		}
		boolean getsReturnSets = ExecutionUtil.getsReturnedSets(anInputs, anActualOutputs);
		anActualOutputs.put(GETS_EQUAL_SETS, getsReturnSets);
		return anActualOutputs;
	}
	public static Map<String, Object> testBean (String aFeatureName, String aTestCase, Project aProject, 
			String[] aBeanDescriptions, 
			Class[] aConstructorArgTypes, 
			Object[] aConstructorArgs, Map<String, Object> anInputs, 
			String[] anOutputProperties, Object[] anExpectedValues) {
		if (anOutputProperties.length != anExpectedValues.length) {
			Tracer.error("output properties length not the same as expected values length");
			return null;
		}
		Map<String, Object> anActualOutputs = testBean(aFeatureName, aTestCase, aProject, aBeanDescriptions, aConstructorArgTypes, aConstructorArgs, anInputs, anOutputProperties);
		for (int i = 0; i < anOutputProperties.length;i++) {
			Object anExpectedOutput = anExpectedValues[i];
			Object anActualOutput;
			Object outputProperty = anOutputProperties[i];
			anActualOutput = outputProperty == null ?null:anActualOutputs.get(outputProperty);
//			anActualOutput = anActualOutputs.get(outputProperty);
			if (!Common.equal(anExpectedOutput, anActualOutput)) {
				anActualOutputs.put(EXPECTED_EQUAL_ACTUAL, false);
				anActualOutputs.put(EXPECTED_EQUAL_ACTUAL + "." + anOutputProperties[i], false);
			}
			
		}
		return anActualOutputs;
		
	}
	

	 public static boolean getsReturnedSets(Map<String, Object> anInputs, 
			 Map<String, Object> anActualOutputs) {
		 if (anInputs == null || anActualOutputs == null) 
			 return false;
		 Set<String> anOutputProperties = anActualOutputs.keySet();
		 for (String anInputProperty:anInputs.keySet()) {
			 if (!anOutputProperties.contains(anInputProperty))
				 continue;
			 Object aGetterValue = anActualOutputs.get(anInputProperty);
			 Object aSetterValue = anInputs.get(anInputProperty);
			if (! Common.equal(aGetterValue, aSetterValue) ) {
				return false;
			}
		 }
		 return true;
	    	
	    }
	 
	 public static Map<String, Object> testBeanWithStringConstructor (String aFeatureName, String aTestCase, Project aProject, 
				String[] aBeanDescriptions,  
				String aConstructorArg, String anIndependentPropertyName, Object anIndepentValue,
				String anOutputPropertyName, Object anExpectedOutputValue ) {
		 Class[] aConstructorArgTypes = new Class[]{String.class};
		 Object[] aConstructorArgs = new String[] {aConstructorArg};
		 String[] anOutputProperties = new String[] {anOutputPropertyName};
		 Object[] anExpectedValue = new Object[] {anExpectedOutputValue};
		 Map<String, Object> anInputs = new HashMap();
		 anInputs.put(anIndependentPropertyName, anIndepentValue);
		 return testBean(aFeatureName, aTestCase, aProject, aBeanDescriptions, aConstructorArgTypes,
				 aConstructorArgs, anInputs, anOutputProperties);
	 
	 }
	 public static Map<String, Object> testBeanWithNoConstructor (String aFeatureName, String aTestCase, Project aProject, 
				String[] aBeanDescriptions,  
				 String anIndependentPropertyName, Object anIndepentValue,
				String anOutputPropertyName, Object anExpectedOutputValue ) {
		 Class[] aConstructorArgTypes = new Class[]{String.class};
		 Object[] aConstructorArgs = new Object[] {};
		 String[] anOutputProperties = new String[] {anOutputPropertyName};
		 Object[] anExpectedValue = new Object[] {anExpectedOutputValue};
		 Map<String, Object> anInputs = new HashMap();
		 anInputs.put(anIndependentPropertyName, anIndepentValue);
		 return testBean(aFeatureName, aTestCase, aProject, aBeanDescriptions, aConstructorArgTypes,
				 aConstructorArgs, anInputs, anOutputProperties);
	 
	 }
	 public static Map<String, Object> testBeanWithStringConstructor (String aFeatureName, String aTestCase, Project aProject, 
				String[] aBeanDescriptions,  
				String aConstructorArg) {
		 Class[] aConstructorArgTypes = new Class[]{String.class};
		 Object[] aConstructorArgs = new String[] {aConstructorArg};
		 String[] anOutputProperties = new String[] {};
		 Object[] anExpectedValue = new Object[] {};
		 Map<String, Object> anInputs = new HashMap();
		 return testBean(aFeatureName, aTestCase, aProject, aBeanDescriptions, aConstructorArgTypes,
				 aConstructorArgs, anInputs, anOutputProperties);
	 
	 }
}
