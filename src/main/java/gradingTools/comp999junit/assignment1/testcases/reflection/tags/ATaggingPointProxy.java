package gradingTools.comp999junit.assignment1.testcases.reflection.tags;

import framework.project.CurrentProjectHolder;
import framework.project.Project;
import grader.junit.JUnitUtils;
import grader.util.IntrospectionUtil;
import gradingTools.comp999junit.assignment1.testcases.PointProxy;
import gradingTools.comp999junit.assignment1.testcases.reflection.AReflectivePointProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.Explanation;
import util.annotations.Group;
import util.annotations.IsExtra;
import util.annotations.IsRestriction;
import util.annotations.MaxValue;
import util.introspect.ClassLoaderFactory;

@MaxValue(6)
public class ATaggingPointProxy extends AReflectivePointProxy implements PointProxy{

	@Test
	public void test() {
		super.test();
	}	

	
	public Class getCartesianPointClass() throws ClassNotFoundException  {
		Project aProject = CurrentProjectHolder.getOrCreateCurrentProject();
		return IntrospectionUtil.findClass(aProject, "ACartesianPoint");

	}

	
	public Method findRadiusMethod() throws Exception {
		return IntrospectionUtil.findMethod(aCartesianPointClass, "getRadius", emptyClassArray);
	}
	
	public Method findAngleMethod() throws Exception {
		return IntrospectionUtil.findMethod(aCartesianPointClass, "getAngle", emptyClassArray);
	}



}
