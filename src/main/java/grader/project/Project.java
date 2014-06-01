package grader.project;

import com.thoughtworks.qdox.JavaDocBuilder;

import framework.grading.testing.Feature;
import grader.assignment.GradingFeature;
import grader.execution.MainClassFinder;
import grader.execution.ProxyBasedClassesManager;
import grader.execution.ProxyClassLoader;
import grader.project.file.RootCodeFolder;
import grader.project.source.ClassesTextManager;
import grader.project.view.ClassViewManager;

import java.util.List;

public interface Project {

    public ClassesManager getClassesManager();

    public void setClassesManager(ProxyBasedClassesManager aClassesManager);

    public ClassViewManager getClassViewManager();

    public void setClassViewManager(ClassViewManager aClassViewManager);

    public ClassesTextManager getClassesTextManager();

    public void setClassesTextManager(ClassesTextManager aClassesTextManager);

    public void setProjectFolder(String aProjectFolder);

    public String getProjectFolderName();

    public String getSourceProjectFolderName();

    public String getBinaryProjectFolderName();

    public List<Class> getImplicitlyLoadedClasses();

    public RootCodeFolder getRootCodeFolder();

    public ProxyClassLoader getClassLoader();

    public Thread run(String mainClassName, String[][] args, String[] anInputFiles, String[] anOutputFiles);

    String getOutputFolder();

    void setOutputFolder(String outputFolder);

    public void maybeMakeClassDescriptions();

    public void setHasBeenRun(boolean newVal);

    public void setCanBeRun(boolean newVal);

    public boolean canBeRun();

    public boolean hasBeenRun();

    public String getOutputFileName();

    String getSourceFileName();

    boolean runChecked();

    public boolean setRunParameters(String aMainClassName, String anArgs[][], String[] anInputFiles, String[] anOutputFiles, MainClassFinder aMainClassFinder);

    Thread runProject();

    JavaDocBuilder getJavaDocBuilder();

	StringBuffer getCurrentOutput();

	void clearOutput();

	void setCurrentOutput(StringBuffer currentOutput);

	Feature getCurrentGradingFeature();

	void setCurrentGradingFeature(Feature currentGradingFeature);

	String getCurrentInput();

	void setCurrentInput(String currentInput);

	String[] getCurrentArgs();

	void setCurrentArgs(String[] currentArgs);

	String getSourceSuffix();

	boolean isNoProjectFolder();

	void setNoProjectFolder(boolean noProjectFolder);
	
	boolean hasUnCompiledClasses();
	public List<String> getNonCompiledClasses();
	public void addNonCompiledClass(String newVal);
	public boolean hasCompiledClasses() ;

	public List<String> getCompiledClasses() ;

	public void addCompiledClass(String newVal) ;

}
