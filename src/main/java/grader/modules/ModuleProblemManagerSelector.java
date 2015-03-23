package grader.modules;

public class ModuleProblemManagerSelector {
	static ModuleProblemManager moduleProblemManager = new AModuleProblemManager();

	public static ModuleProblemManager getModuleProblemManager() {
		return moduleProblemManager;
	}

	public static void setModuleProblemManager(
			ModuleProblemManager moduleProblemManager) {
		ModuleProblemManagerSelector.moduleProblemManager = moduleProblemManager;
	}

}
