package grader.util;

import framework.project.ClassDescription;
import framework.project.Project;
import grader.execution.AConstructorExecutionCallable;
import grader.execution.AMethodExecutionCallable;
import grader.execution.AResultWithOutput;
import grader.execution.ResultWithOutput;

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
	public static Map<String, Object> testBean (Project aProject, String aBeanDescription, Map<String, Object> anInputs, Map<String, Object> anOutputs ) {
		Map<String, Object> anActualOutputs = new HashMap();
		String[] aBeanDescriptions = aBeanDescription.split(",");
		if (aBeanDescriptions.length != 4) {
			Tracer.error("Bean description  in testBean should have 4 elements instead of: " + aBeanDescriptions.length);
		}
		
        List<ClassDescription> aClasses = aProject.getClassesManager().get().findClass(
        		aBeanDescriptions[0],
        		aBeanDescriptions[1],
        		aBeanDescriptions[2],
        		aBeanDescriptions[3]);
        return null;

	}

	
}
