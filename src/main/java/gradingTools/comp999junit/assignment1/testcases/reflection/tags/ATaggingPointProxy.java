package gradingTools.comp999junit.assignment1.testcases.reflection.tags;

import framework.project.CurrentProjectHolder;
import framework.project.Project;
import grader.util.BasicProjectIntrospection;
import gradingTools.comp999junit.assignment1.testcases.PointProxy;
import gradingTools.comp999junit.assignment1.testcases.reflection.AReflectivePointProxy;

import java.lang.reflect.Method;

import org.junit.Test;

import util.annotations.MaxValue;

@MaxValue(6)
public class ATaggingPointProxy extends AReflectivePointProxy implements PointProxy{

	@Test
	public void test() {
		super.test();
	}	

	
	public Class getCartesianPointClass() throws ClassNotFoundException  {
		Project aProject = CurrentProjectHolder.getOrCreateCurrentProject();
		return BasicProjectIntrospection.findClass(aProject, "ACartesianPoint");
//		return IntrospectionUtil.findUniqueClassByTag(aProject, new String[] {"cartesian", "point"});

	}

	
	public Method findRadiusMethod() throws Exception {
//		return IntrospectionUtil.findMethod(aCartesianPointClass, "getRadius", emptyClassArray);
		return BasicProjectIntrospection.findUniqueMethodByTag(aCartesianPointClass, new String[] {"Radius", "Getter"}, emptyClassArray);
	}
	
	public Method findAngleMethod() throws Exception {
		return BasicProjectIntrospection.findMethod(aCartesianPointClass, "getAngle", emptyClassArray);
	}



}
