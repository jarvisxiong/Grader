package grader.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import util.trace.Tracer;
import edu.emory.mathcs.backport.java.util.Arrays;

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
		Method anActualMethod = BasicProjectIntrospection.findMethod(actualClass, aProxyMethod);
		if (anActualMethod == null) {
			return null;
		}
		if (args != null) {
		int[] aPermutedIndices= BasicProjectIntrospection.getArgIndices(actualClass, aProxyMethod);
		if (aPermutedIndices != null) {
			Object[] anOriginalArgs = Arrays.copyOf(args, args.length);
			for (int i = 0; i < args.length; i++) {
				args[i] = anOriginalArgs[aPermutedIndices[i]];
				
			}
		}
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof Proxy) {
				Object anActualObject = BasicProjectIntrospection.getRealObject(args[i]);
				if (anActualObject == null) {
					Tracer.error("Could not get real object for proxy:" + args[i]);
				}
				args[i] = anActualObject;
			}
		}
		}
		Object aRetVal = ProjectExecution.timedInvoke(actualObject, anActualMethod, args);
		if (aRetVal instanceof Object) {
			Object aProxy = BasicProjectIntrospection.getProxyObject(aRetVal);
			if (aProxy != null)
				return aProxy;
			
		}
		return aRetVal;
		
	}

}
