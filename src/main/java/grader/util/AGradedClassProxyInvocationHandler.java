package grader.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AGradedClassProxyInvocationHandler implements InvocationHandler {
	Class actualClass;
	Object actualObject;
	public AGradedClassProxyInvocationHandler (Object anActualObject) {
		actualObject = anActualObject;
		actualClass = anActualObject.getClass();
	}

	@Override
	public Object invoke(Object proxy, Method aProxyMethod, Object[] args)
			throws Throwable {
		Method anActualMethod = IntrospectionUtil.findMethod(actualClass, aProxyMethod);
		if (anActualMethod == null) {
			return null;
		}
		return ExecutionUtil.timedInvoke(actualObject, anActualMethod, args);
	}

}
