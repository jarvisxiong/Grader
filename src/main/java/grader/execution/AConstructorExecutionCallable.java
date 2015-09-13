package grader.execution;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class AConstructorExecutionCallable implements Callable{
	Constructor constructor;
	Object object;
	Object[] args;
	public AConstructorExecutionCallable(Object anObject, Constructor aConstructor, Object[] anArgs) {
		constructor = aConstructor;
		object = anObject;
		args = anArgs;
	}
	@Override
	public Object call() throws Exception {
		System.out.println ("calling method: " + constructor + " " + args);
		Object retVal = constructor.newInstance(object, args);
		System.out.println ("called method: " + constructor + " " + args);

		return retVal;
	}
	

}
