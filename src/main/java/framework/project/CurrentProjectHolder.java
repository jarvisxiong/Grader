package framework.project;

import java.io.File;
import java.io.FileNotFoundException;

public class CurrentProjectHolder {
	static Project currentProject;
	public static Project getOrCreateCurrentProject() {
		if (currentProject == null) {
			currentProject = createCurrentProject(); 
		}
		return getCurrentProject();
	}
	public static Project getCurrentProject() {
		return currentProject;
	}
	public static void setProject(Project aProject) {
		currentProject = aProject;
	}
	public static Project createCurrentProject() {
		try {
			return new BasicProject(null, new File("."), null, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
