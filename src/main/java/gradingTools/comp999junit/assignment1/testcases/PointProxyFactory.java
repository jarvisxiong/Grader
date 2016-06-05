package gradingTools.comp999junit.assignment1.testcases;

import gradingTools.comp999junit.assignment1.testcases.reflection.AReflectivePointProxy;

public class PointProxyFactory {
	static PointProxy pointProxy = new AReflectivePointProxy();
	public static PointProxy getPointProxy() {
		return pointProxy;		
	}
	public static void setPointProxy(PointProxy newVal) {
		pointProxy = newVal;
	}

}
