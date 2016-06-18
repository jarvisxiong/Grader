package grader.util;

import framework.execution.ARunningProject;
import framework.execution.BasicProcessRunner;
import framework.execution.NotRunnableException;
import framework.execution.ProcessRunner;
import framework.execution.ProcessRunnerFactory;
import framework.execution.Runner;
import framework.execution.RunnerSelector;
import framework.execution.RunningProject;
import framework.project.CurrentProjectHolder;
import framework.project.Project;
import framework.utils.BasicGradingEnvironment;
import grader.execution.AConstructorExecutionCallable;
import grader.execution.AMethodExecutionCallable;
import grader.execution.AResultWithOutput;
import grader.execution.JavaMainClassFinderSelector;
import grader.execution.ResultWithOutput;
import gradingTools.testables.comp999junit.assignment1.wrongangle.Main;
import gradingTools.utils.RunningProjectUtils;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.codehaus.jackson.map.ser.PropertyBuilder.EmptyStringChecker;

import util.misc.Common;
import util.misc.TeePrintStream;
import util.trace.Tracer;

public class ExecutionUtil {
	static boolean useMethodAndConstructorTimeOut = false;
	
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
	public static final int DEFAULT_CONSTRUCTOR_TIME_OUT = 2000;
	public static final int DEFAULT_METHOD_TIME_OUT = 2000;
	public static final int PROCESS_TIME_OUT = 4000;
	protected static int constructorTimeOut = DEFAULT_CONSTRUCTOR_TIME_OUT;
	
	protected static int methodTimeOut = DEFAULT_METHOD_TIME_OUT;

	static Object[] emptyObjectArray = {};
	public static int getConstructorTimeOut() {
		return constructorTimeOut;
	}

	public static void setConstructorTimeOut(int constructorTimeOut) {
		ExecutionUtil.constructorTimeOut = constructorTimeOut;
	}

	public static int getMethodTimeOut() {
		return methodTimeOut;
	}

	public static void setMethodTimeOut(int methodTimeOut) {
		ExecutionUtil.methodTimeOut = methodTimeOut;
	}
	public static Object timedInvoke(Object anObject, Method aMethod,
			long aMillSeconds, Object... anArgs) {
//		 ExecutorService executor = Executors.newSingleThreadExecutor();
		if (anArgs == null) {
			anArgs = emptyObjectArray;
		}

		Future future = executor.submit(new AMethodExecutionCallable(anObject,
				aMethod, anArgs));

		try {
			return future.get(aMillSeconds, TimeUnit.MILLISECONDS);
		} catch (CancellationException | InterruptedException | TimeoutException e) {
			e.printStackTrace(); 
			future.cancel(true); // not needed really
			System.err.println("Terminated execution after milliseconds:" + aMillSeconds);
			return null;
		} catch (ExecutionException e) {
			System.err.println("Future execution exception" );
			e.printStackTrace();
			return null;

		}
		finally {
//			executor.shutdownNow();
		}

	}
        
        public static Object timedInvokeWithExceptions(Object anObject, Method aMethod,
			long aMillSeconds, Object... anArgs) throws Exception{
//		 ExecutorService executor = Executors.newSingleThreadExecutor();


		Future future = executor.submit(new AMethodExecutionCallable(anObject,
				aMethod, anArgs));

		try {
			return future.get(aMillSeconds, TimeUnit.MILLISECONDS);
		} catch (CancellationException | InterruptedException e) {
			future.cancel(true); //not needed really
//			e.printStackTrace();
			System.err.println("Terminated execution after milliseconds:" + aMillSeconds);
			throw e;
		} catch (ExecutionException e) {
			System.err.println("Future execution exception" );
			throw e;

		}
		finally {
//			executor.shutdownNow();
		}

	}
        
	public static Object timedInvoke(Object anObject, Method aMethod,
			Object... anArgs) {
		if (isUseMethodAndConstructorTimeOut())
			return timedInvoke(anObject, aMethod, getMethodTimeOut(), anArgs);
		else {
			try {
				return aMethod.invoke(anObject, anArgs);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

	}
	public static boolean isUseMethodAndConstructorTimeOut() {
		return useMethodAndConstructorTimeOut;
	}

	public static void setUseMethodAndConstructorTimeOut(
			boolean useMethodAndConstructorTimeOut) {
		ExecutionUtil.useMethodAndConstructorTimeOut = useMethodAndConstructorTimeOut;
	}     
        
	public static Object timedInvokeWithExceptions(Object anObject, Method aMethod,
			Object... anArgs) throws Exception{
		return timedInvokeWithExceptions(anObject, aMethod, getMethodTimeOut(), anArgs);

	}
        
        public static Object timedInvoke(Constructor aConstructor, Object[] anArgs) {
        	if (isUseMethodAndConstructorTimeOut())
            return timedInvoke(aConstructor, anArgs, getConstructorTimeOut());
			else
				try {
					return aConstructor.newInstance(anArgs);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
        }
        
        public static Object timedInvokeWithExceptions(Constructor aConstructor, Object[] anArgs) throws Exception {
            return timedInvokeWithExceptions(aConstructor, anArgs, getConstructorTimeOut());
        }
        
	public static Object timedInvoke(Constructor aConstructor,
			Object[] anArgs, long aMillSeconds) {
//		 ExecutorService executor = Executors.newSingleThreadExecutor();


		Future future = executor.submit(new AConstructorExecutionCallable(
				aConstructor, anArgs));

		try {
			return future.get(aMillSeconds, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			future.cancel(true);
			System.out.println("Terminated!");
			return null;
		} finally {
			
//			executor.shutdownNow();
		}

	}
        
        public static Object timedInvokeWithExceptions(Constructor aConstructor,
			Object[] anArgs, long aMillSeconds) throws Exception {
//		 ExecutorService executor = Executors.newSingleThreadExecutor();


		Future future = executor.submit(new AConstructorExecutionCallable(
				aConstructor, anArgs));

		try {
			return future.get(aMillSeconds, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			future.cancel(true);
			System.out.println("Terminated!");
                        throw e;
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
			System.setOut(previousOut);

		}
	}
	static String tmpOutFilePrefix = "tmpMethodOut" ;
	static String tmpInFileName = "tmpIn";
//	static PrintStream previousOut = System.out;
//	static FileOutputStream aFileStream;
	static PrintStream teeStream;
//	static File tmpFile;
//	static boolean outputRedirected;
//	static int numRedirections = 0;
	static Stack<File> tmpFileStack = new Stack();
	static PrintStream previousOut = System.out;
	static InputStream originalIn = System.in;
	static FileInputStream newIn;
	static Stack<PrintStream> originalOutStack = new Stack();
	static Stack<FileOutputStream> fileOutStack = new Stack();
	static String computeNextTmpFileName () {
		return tmpOutFilePrefix + tmpFileStack.size() + ".txt";
	}
	public static synchronized void redirectInputOutput(String anInput) {
		try {
			
//			Common.writeText(tmpInFileName, anInput);
			try(  PrintWriter out = new PrintWriter( tmpInFileName )  ){
			    out.println( anInput );
			}
			newIn = new FileInputStream(
					tmpInFileName);
			System.setIn(newIn);
			redirectOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static synchronized void redirectOutput() {
//		if (outputRedirected) {
//			System.out.println ("Output already redirected, ignoring redirect call");
//			return;
//		}
//		numRedirections++;
		try {
			String aNextTempFileName = computeNextTmpFileName();
			FileOutputStream aFileStream = new FileOutputStream(
					aNextTempFileName);
			File aTmpFile = new File(aNextTempFileName);
			fileOutStack.push(aFileStream);
			tmpFileStack.push(aTmpFile);
			originalOutStack.push(previousOut); // this should be System.out;
			
			
			PrintStream teeStream = new TeePrintStream(aFileStream, previousOut);
			System.setOut(teeStream);
			previousOut = teeStream;
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public static synchronized String restoreOutputAndGetRedirectedOutput() {
		try {
//			System.out.flush();
//			originalOut.flush();
			FileOutputStream aFileStream = fileOutStack.pop();
			aFileStream.flush();
			aFileStream.close();
			File tmpFile = tmpFileStack.pop();
//			ThreadSupport.sleep(2000);
			String anOutput = Common.toText(tmpFile);
			tmpFile.delete();
			if (newIn != null) {
				newIn.close();
				File aTempInputFile = new File(tmpInFileName);
				if (aTempInputFile.exists()) {
					aTempInputFile.delete();
				}
				System.setIn(originalIn);
			}
			return anOutput;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			previousOut = originalOutStack.pop();
			System.setOut(previousOut);

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
//			anActualOutputs.put(CLASS_MATCHED, aClass.getCanonicalName());
			anActualOutputs.put(CLASS_MATCHED, aClass);

			Constructor aConstructor = aClass.getConstructor(aConstructorArgTypes);
			Object anObject = timedInvoke(aConstructor, aConstructorArgs, 600);
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
				timedInvoke(anObject, aWriteMethod, getMethodTimeOut(), new Object[]{aValue});
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
				Object result = timedInvoke(anObject, aReadMethod, getMethodTimeOut(), emptyArgs);
				anActualOutputs.put(anOutputPropertyName, result);				
			}
		}
		
		} catch (NoSuchMethodException e) {
			System.out.println("Constructor not found:" + e.getMessage());
			anActualOutputs.put(MISSING_CONSTRUCTOR, true);
//			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			anActualOutputs = null;

			e.printStackTrace();
		} finally {
			anOutput = restoreOutputAndGetRedirectedOutput();
			 if (anOutput != null && !anOutput.isEmpty()) {
             	ARunningProject.appendToTranscriptFile(aProject, aFeatureName, anOutput);
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
	 public static String forkProjectMainWithExplicitCommand(Class aProxyClass, String[] args,
				int timeout, String... input) throws NotRunnableException {
			Class aMainClass = IntrospectionUtil.findClass(CurrentProjectHolder.getOrCreateCurrentProject(), aProxyClass);
			// this should depend on whether class path
			
//		 	String aClassPath = System.getProperty("java.class.path");
		 	String aClassPath = BasicGradingEnvironment.get().getClasspath();
		 	String aMainClassName = aMainClass.getName();
	        String[] command = {"java",  "-cp",  aClassPath, aMainClassName};
	        
	        File aBuildFolder = null;
	        try {
	            aBuildFolder = CurrentProjectHolder.getOrCreateCurrentProject().getBuildFolder(aMainClassName);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	return null;
	        }
	        Runner processRunner = new BasicProcessRunner(aBuildFolder);

	       RunningProject aRunningProject = processRunner.run(null, command, RunningProjectUtils.toInputString(input), args, timeout);
	       return aRunningProject.await();

	}
	 public static String forkProjectMain(Class aProxyClass, String[] args,
				int timeout, String... input) throws NotRunnableException {	
		 	// use tags for search if necessary, hence the finding. Duplicated work in main class finder unfortunately
			Class aMainClass = IntrospectionUtil.findClass(CurrentProjectHolder.getOrCreateCurrentProject(), aProxyClass);
			String aMainClassName =null;
			if (aMainClass != null)
				aMainClassName = aMainClass.getName();
			return forkMain(aMainClassName, args, timeout, input);

//		   Runner aProcessRunner = RunnerSelector.createProcessRunner(CurrentProjectHolder.getOrCreateCurrentProject(), aMainClassName);
//
//	       RunningProject aRunningProject = aProcessRunner.run(input, args, timeout);
//	       return aRunningProject.await();

	}
	 public static String forkMain(String aMainClassName, String[] args,
				String... input) throws NotRunnableException {	
		 return forkMain(aMainClassName, args, PROCESS_TIME_OUT, input);
	 }
	 public static String forkMain(
				String[] args, String... input) throws NotRunnableException {	
		 return forkMain(null, args, PROCESS_TIME_OUT, input);
	 }
	 public static String forkMain(String aMainClassName, String[] args,
				int timeout, String... input) throws NotRunnableException {	
		 	

		   Runner aProcessRunner = RunnerSelector.createProcessRunner(CurrentProjectHolder.getOrCreateCurrentProject(), aMainClassName);

	       RunningProject aRunningProject = aProcessRunner.run(RunningProjectUtils.toInputString(input), args, timeout);
	       return aRunningProject.await();

	}
	 public static String forkMainWithExplicitCommand(Class aProxyClass, String[] args,String... input) {
		 return forkProjectMainWithExplicitCommand(aProxyClass, args, PROCESS_TIME_OUT, input);
	 }
	 public static String forkMain(Class aProxyClass, String[] args,String... input) {
		 return forkProjectMain(aProxyClass, args, PROCESS_TIME_OUT, input);
	 }
	 static final String[] emptyStringArray = new String[]{};
	 public static String callCorrespondingMain(Class aProxyClass, String... anInput) throws NotRunnableException {
		 return callCorrespondingMain(aProxyClass, emptyStringArray, anInput);
	 }
	 public static String callMain(String aMainName, String[] args,
				String... anInput) throws NotRunnableException {
		 if (BasicGradingEnvironment.get().isForkMain()) {
			 return forkMain(aMainName, args, anInput);
		 } else {
			 return invokeMain(aMainName, args, anInput);
		 }
	 }
	 public static String callMain(String... anInput) throws NotRunnableException {
		 if (BasicGradingEnvironment.get().isForkMain()) {
			 return forkMain( emptyStringArray, anInput);
		 } else {
			 return invokeMain(emptyStringArray, anInput);
		 }
	 }
	 public static String callMain(String[] args,
				String... anInput) throws NotRunnableException {
		 if (BasicGradingEnvironment.get().isForkMain()) {
			 return forkMain(anInput, args);
		 } else {
			 return invokeMain(args, anInput);
		 }
	 }
	 
	 public static String callCorrespondingMain(Class aProxyClass, String[] args,
				String... anInput) throws NotRunnableException {
		 if (BasicGradingEnvironment.get().isForkMain()) {
			 return forkMain(aProxyClass, args, anInput);
		 } else {
			 return invokeCorrespondingMain(aProxyClass, args, anInput);
		 }
	 }

	 public static String invokeCorrespondingMain(Class aProxyClass, String[] args,
				String... anInput) throws NotRunnableException {
		 try {
			Class aMainClass = IntrospectionUtil.findClass(CurrentProjectHolder.getOrCreateCurrentProject(), aProxyClass);
			if (aMainClass == null)
				return null;
			return invokeMain(aMainClass, args, anInput);		
		 } catch (Exception e) {
			 e.printStackTrace();
			 return null;
		 }

		}
	 public static String invokeMain(String aMainClassName, String[] args,
				String... anInput) throws NotRunnableException {
		 try {
			Class aMainClass = IntrospectionUtil.findClass(CurrentProjectHolder.getOrCreateCurrentProject(), aMainClassName);
			if (aMainClass == null)
				return null;
			return invokeMain(aMainClass, args, anInput);		
		 } catch (Exception e) {
			 e.printStackTrace();
			 return null;
		 }

		}
	 public static String invokeMain(String[] args,
				String... anInput) throws NotRunnableException {
		 try {
			Map<String, String> anEntryPoints = JavaMainClassFinderSelector.getMainClassFinder().getEntryPoints(CurrentProjectHolder.getOrCreateCurrentProject(), null);
			String aMainEntryPoint = anEntryPoints.get(BasicProcessRunner.MAIN_ENTRY_POINT);
			if (aMainEntryPoint == null) 
				throw new NotRunnableException("No entry point found");
		
			return invokeMain(aMainEntryPoint, args, anInput);		
		 } catch (Exception e) {
			 e.printStackTrace();
			 return null;
		 }

		}
	 
	 public static String invokeMain(Class aMainClass, String[] args,
				String... anInput) throws NotRunnableException {
		 try {
		 ExecutionUtil.redirectInputOutput(RunningProjectUtils.toInputString(anInput));		
			
			Method aMainMethod = IntrospectionUtil.findMethod(aMainClass, "main", new Class[] {String[].class});
			ExecutionUtil.timedInvoke(aMainClass, aMainMethod, new Object[] {args});
//			aMainMethod.invoke(aMainClass, new Object[] {args});		
			String anOutput = ExecutionUtil.restoreOutputAndGetRedirectedOutput();
			return anOutput;	
		 } catch (Exception e) {
			 e.printStackTrace();
			 return null;
		 }
		}
}
