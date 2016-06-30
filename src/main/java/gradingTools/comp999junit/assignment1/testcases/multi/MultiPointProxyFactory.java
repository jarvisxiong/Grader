package gradingTools.comp999junit.assignment1.testcases.multi;

import gradingTools.comp999junit.assignment1.testcases.reflection.AReflectivePointProxy;

public class MultiPointProxyFactory {
	static MultiPointProxy pointProxy = new AReflectivePointProxy();
	public static MultiPointProxy getPointProxy() {
		return pointProxy;		
	}
	public static void setPointProxy(MultiPointProxy newVal) {
		pointProxy = newVal;
	}

}
