package grader.checkStyle;

import grader.compilation.ClassFilesCompiler;

public class JavaCheckStyleInvokerFactory {
	static CheckStyleInvoker checkStyleInvoker ;

	public static CheckStyleInvoker getSingleton() {
		if (checkStyleInvoker ==  null)
			checkStyleInvoker =  new AJavaCheckStyleInvoker();
		return checkStyleInvoker;
	}
}

	