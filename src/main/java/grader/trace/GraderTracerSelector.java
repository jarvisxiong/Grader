package grader.trace;

public class GraderTracerSelector {
	static GraderTracer graderTracer = new AGraderTracer();

	public static GraderTracer getGraderTracer() {
		return graderTracer;
	}

	public static void setGraderTracer(GraderTracer graderTracer) {
		GraderTracerSelector.graderTracer = graderTracer;
	}

}
