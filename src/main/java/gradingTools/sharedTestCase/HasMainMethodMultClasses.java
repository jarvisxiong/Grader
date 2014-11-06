package gradingTools.sharedTestCase;


import gradingTools.sharedTestCase.utils.MethodPropertyChecker;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class HasMainMethodMultClasses extends HasMethodTestCase {

	private static final Class<?> MAIN_RETURN_TYPE = Void.TYPE;
	private static final Class<?>[] MAIN_PARAMETER_TYPES = {new String[0].getClass()};
	
    public HasMainMethodMultClasses(ArrayList<String> ignoredClasses) {
    	super("main", ignoredClasses, MAIN_RETURN_TYPE, MAIN_PARAMETER_TYPES);
    	super.addPropertyChecker(new MethodPropertyChecker() {
			
			@Override
			public String getMessageOnIncorrect(Method method) {
				if (!Modifier.isStatic(method.getModifiers())) {
					return "main method not static";
				} else {
					return null;
				}
			}
		});
    }
}

