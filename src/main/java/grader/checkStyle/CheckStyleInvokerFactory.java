package grader.checkStyle;

import grader.compilation.ClassFilesCompiler;

public class CheckStyleInvokerFactory {
	static CheckStyleInvoker checkStyleInvoker ;

	public static CheckStyleInvoker getSingleton() {
		if (checkStyleInvoker ==  null)
			checkStyleInvoker =  new ACheckStyleInvoker();
		return checkStyleInvoker;
	}
}

	