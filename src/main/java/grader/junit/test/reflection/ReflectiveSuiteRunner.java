package grader.junit.test.reflection;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public class ReflectiveSuiteRunner extends Suite{

//	public ReflectiveSuiteRunner(Class<?> klass, Class<?>[] suiteClasses)
//			throws InitializationError {
//		super(klass, suiteClasses);
//		// TODO Auto-generated constructor stub
//	}
//
//	public ReflectiveSuiteRunner(Class<?> klass, List<Runner> runners)
//			throws InitializationError {
//		super(klass, runners);
//		// TODO Auto-generated constructor stub
//	}

	public ReflectiveSuiteRunner(Class<?> klass, RunnerBuilder builder)
			throws InitializationError {
		super(klass, builder);
		// TODO Auto-generated constructor stub
	}

//	public ReflectiveSuiteRunner(RunnerBuilder builder, Class<?> klass,
//			Class<?>[] suiteClasses) throws InitializationError {
//		super(builder, klass, suiteClasses);
//		// TODO Auto-generated constructor stub
//	}
//
//	public ReflectiveSuiteRunner(RunnerBuilder builder, Class<?>[] classes)
//			throws InitializationError {
//		super(builder, classes);
//		// TODO Auto-generated constructor stub
//	}

}
