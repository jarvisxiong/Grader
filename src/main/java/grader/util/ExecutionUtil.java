package grader.util;

import grader.execution.AMethodExecutionCallable;
import grader.execution.AResultWithOutput;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
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
			System.out.println("Terminated!");
			return null;
		} finally {
//			executor.shutdownNow();
		}

	}

	public static Object timedInteractiveInvoke(Object anObject,
			Method aMethod, Object[] anArgs, long aMillSeconds) {
//		 ExecutorService executor = Executors.newSingleThreadExecutor();

		PrintStream originalOut = System.out;
		Callable aCallable = new AMethodExecutionCallable(anObject,
				aMethod, anArgs);
		Future future = executor.submit(aCallable);

		try {
//			System.out.flush();
//			File aTempFile = File.createTempFile("tmpMethodOut", ".txt");
//			File aTempFile = File.createTempFile("tmpMethodOut", ".txt");
			String tmpFileName = "tmpMethodOut.txt" ;
			FileOutputStream aFileStream = new FileOutputStream(
					tmpFileName);
			File tmpFile = new File(tmpFileName);
			PrintStream teeStream = new TeePrintStream(aFileStream, originalOut);
			System.setOut(teeStream);
//			Object aResult = timedInvoke(anObject, aMethod, anArgs,
//					aMillSeconds);
			Object aResult = future.get(aMillSeconds, TimeUnit.MILLISECONDS);

//			System.out.flush();
			aFileStream.flush();
			aFileStream.close();
//			ThreadSupport.sleep(2000);
			String anOutput = Common.toText(tmpFile);
			tmpFile.delete();
			
			return new AResultWithOutput(aResult, anOutput);
		} catch (Exception e) {
			return new AResultWithOutput(null, null);
		} finally {
			System.setOut(originalOut);
//			executor.shutdownNow();

		}

	}
}
