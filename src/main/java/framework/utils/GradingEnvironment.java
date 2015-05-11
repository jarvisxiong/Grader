package framework.utils;

import grader.config.AConfigurationManager;
import grader.config.ConfigurationManager;

import java.io.File;
import java.io.IOException;

/**
 * A singleton that investigates the machine for certain things. It looks for:
 * <ul>
 *     <li>Operating System</li>
 *     <li>Text Editor (OS specific)</li>
 *     <li>File Browser (OS specific)</li>
 *     <li>Classpath (OS specific because Windows delimits with ';' rather than ':'</li>
 * </ul>
 */
public class GradingEnvironment {

    private static final String[] macEditors = new String[]{
        "/Applications/Sublime Text 2.app/Contents/SharedSupport/bin/subl",
        "/usr/local/bin/edit"
    };

    private static final String[] linuxEditors = new String[]{
        "/usr/local/bin/gvim",
        "/usr/local/bin/emacs",
        "/usr/local/bin/gedit",
    };

    private static final String[] windowsEditors = new String[]{
        "C:\\Program Files\\Sublime Text 2\\sublime_text.exe",
        "C:\\Program Files (x86)\\Sublime Text 2\\sublime_text.exe",
        "C:\\Program Files\\Notepad++\\notepad++.exe",
        "C:\\Program Files (x86)\\Notepad++\\notepad++.exe",
        "notepad"
    };
    String userName;
   

	private String osName;
    private String editor;
    protected String diff;
    private String browser;
    private String classpath;
    private String assignmentName;
    private String defaulAssignmentsDataFolderName;
//    ConfigurationManager configurationManager;  // maybe it does not belong here

	

	public String getDefaultAssignmentsDataFolderName() {
		return defaulAssignmentsDataFolderName;
	}

	public void setDefaultAssignmentsDataFolderName(
			String newVal) {
		this.defaulAssignmentsDataFolderName = newVal;
	}
	public boolean isNotWindows() {
		return !osName.equals("Windows");
	}

	private GradingEnvironment() {
        osName = System.getProperty("os.name");
        userName = System.getProperty("user.name");
        if (osName.equals("Mac OS X")) {
            osName = "Mac";
            browser = "open";
            editor = findEditor(macEditors);
            classpath = findClasspath(":");
        } else if (osName.equals("Linux")) {
            browser = "nautilus";
            editor = findEditor(linuxEditors);
            classpath = findClasspath(":");
        } else {
            osName = "Windows";
            browser = "explorer";
            editor = findEditor(windowsEditors);
            classpath = findClasspath(";");
        }
    }

    private static String findEditor(String[] editors) {
        for (String editor : editors) {
            if (new File(editor).exists())
                return editor;
        }
        return "";
    }

    private static String findClasspath(String separator) {
    	String myClassPath = System.getProperty("java.class.path");
        File oe = new File("oeall-22.jar");
//        String[] paths = new String[] { ".", "..", oe.getAbsolutePath()};
        String[] paths = new String[] { ".", "..", myClassPath};

        String classpath = "";
        for (String path : paths)
            classpath += (classpath.isEmpty() ? "" : separator) + path;
        return classpath;
    }

    public String getClasspath() {
        return classpath;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getEditor() {
        return editor;
    }
    
    public String getDiff() {
        return diff;
    }
    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getOsName() {
        return osName;
    }
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    /**
     * Opens a directory in the file browser
     * @param file The directory
     */
    public void open(File file) {
        try {
            new ProcessBuilder(browser, file.getAbsolutePath()).start();
        } catch (IOException e) {
            System.out.println("Can't open file");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    
    private void editSubFiles(File folder) {
    	File[] subFiles = folder.listFiles();
    	for (File subFile: subFiles) {
    		if (subFile.isDirectory()) {
    			editSubFiles(subFile);
    		} else {
    			edit(subFile);
    		}
    	}
    }

    /**
     * Edits a directory or file in the text editor
     * @param file The directory or file
     */
    public void edit(File file) {

    	if (file.isDirectory() && osName.equals("Linux")) {
    		editSubFiles(file);
    		return;
    	}
    	
        try {
            new ProcessBuilder(editor, file.getAbsolutePath()).start();
        } catch (IOException e) {
            System.out.println("Can't edit file/folder");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    // Singleton methods
    private static GradingEnvironment singleton = null;

    public static GradingEnvironment get() {
        if (singleton == null)
            singleton = new GradingEnvironment();
        return singleton;
    }
//    public ConfigurationManager getConfigurationManager() {
//		return configurationManager;
//	}
//
//	public void setConfigurationManager(ConfigurationManager configurationManager) {
//		this.configurationManager = configurationManager;
//	}

}
