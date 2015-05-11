package grader.executor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import framework.utils.GradingEnvironment;
import grader.compilation.c.CFilesCompilerSelector;

public class AnExecutor implements Executor {
	String executorDirectory;
	public static final String SOURCE_FILE = "executor";
	public  final List<File> sourceFiles;
	public AnExecutor() {
		sourceFiles = new ArrayList();
		sourceFiles.add(new File(SOURCE_FILE + ".c"));
		
	}
	
	/* (non-Javadoc)
	 * @see grader.executor.Executor#compile()
	 */
	@Override
	public void compile() {
		
		try {
			CFilesCompilerSelector.getClassFilesCompiler().compile(new File(executorDirectory + "/src") , 
					new File (executorDirectory + "/bin"), sourceFiles);
			if (GradingEnvironment.get().isNotWindows()) {
				String fileNames = executorDirectory + "/bin/" + SOURCE_FILE + "*";
				Runtime.getRuntime().exec("setuid nobody " + fileNames);
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	/* (non-Javadoc)
	 * @see grader.executor.Executor#execute(java.lang.String[])
	 */
	@Override
	public void execute(String[] args) {
		
	}
	/* (non-Javadoc)
	 * @see grader.executor.Executor#setExecutorDirectory(java.lang.String)
	 */
	@Override
	public void setExecutorDirectory(String newVal) {
		executorDirectory = newVal;
//		File f = new File(newVal);
//		System.out.println ("Exists:" + f.exists());
	}

}
