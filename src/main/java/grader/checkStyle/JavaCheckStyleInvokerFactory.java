package grader.checkStyle;


public class JavaCheckStyleInvokerFactory {
	static CheckStyleInvoker checkStyleInvoker ;

	public static CheckStyleInvoker getSingleton() {
		if (checkStyleInvoker ==  null)
			checkStyleInvoker =  new AJavaCheckStyleInvoker();
		return checkStyleInvoker;
	}
}

	