package grader.execution;

import grader.file.FileProxy;
import grader.project.Project;
import grader.project.file.RootCodeFolder;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AProxyProjectClassLoader extends ClassLoader implements ProxyClassLoader {
    public static final String CLASS_MAIN = "main.finalAssignment";

    RootCodeFolder rootCodeFolder;
    String projectFolderName, binaryFolderName;
    List<Class> classesLoaded = new ArrayList();
    
    Map<String, Class> dynamicallyCompiledClass = new HashMap();

    public AProxyProjectClassLoader(RootCodeFolder aRootCodeFolder) {
        init(aRootCodeFolder);
    }

    void init(RootCodeFolder aRootCodeFolder) {
        rootCodeFolder = aRootCodeFolder;
        binaryFolderName = rootCodeFolder.getBinaryProjectFolderName();
        projectFolderName = rootCodeFolder.getProjectFolderName();
//        if (projectFolderName == null)
//        	projectFolderName = binaryFolderName; // bin folder was missing so bin folder became project folder and hence this confusion
    }

    public InputStream getResourceAsStream(String name) {
        InputStream retVal = super.getResourceAsStream(name);
        if (retVal != null)
            return retVal;
        String aFullFileName = projectFolderName + "/" + name;
        FileProxy fileProxy = rootCodeFolder.getRootFolder().getFileEntry(aFullFileName);
        InputStream inputStream = fileProxy.getInputStream();
        return inputStream;
    }
    @Override
    public Collection<Class> getDynamicallyCompiledClasses() {
    	return dynamicallyCompiledClass.values();
    }
    @Override
    public Class defineDynamicallyCompiledClass (String aClassName, byte[] aBytes) {
    		Class aClass = dynamicallyCompiledClass.get(aClassName); 
    		if (aClass == null)
    	
    			aClass = super.defineClass(aClassName, aBytes, 0, aBytes.length);
            if (aClass != null)
            	dynamicallyCompiledClass.put(aClassName, aClass);
            return aClass;
        
    }

    public Class findClass(String aClassName) throws ClassNotFoundException {
        try {
        	Class retVal = dynamicallyCompiledClass.get(aClassName);
        	if (retVal != null)
        		return retVal;
            byte classBytes[];
            String aFileName = aClassName.replaceAll("\\.", "/") + ".class";
            String aFullFileName = binaryFolderName + "/" + aFileName;
            FileProxy ClassFile = rootCodeFolder.getFileEntry(aFullFileName);
            InputStream inputStream = ClassFile.getInputStream();

            classBytes = new byte[inputStream.available()];
            inputStream.read(classBytes);
            Class classObject = defineClass(aClassName, classBytes, 0, classBytes.length);
            classesLoaded.add(classObject);
            return classObject;
        } catch (Exception e) {
            return null;
        }
    }

    public Class loadClass(String aClassName) throws ClassNotFoundException {
    	try {
        return super.loadClass(aClassName);
    	} catch (Exception e) {
    		return null;
    	}
    }

    public static void run(Project aProject, String mainClassName) {
        try {
            ClassLoader classLoader = new AProxyProjectClassLoader(aProject.getRootCodeFolder());
            Class mainClass = classLoader.loadClass(mainClassName);
            Method mainMethod = mainClass.getMethod("main", String[].class);
            String[] strings = {"move 10 100"};
            Object[] myArgs = {strings};
            mainMethod.invoke(mainClass, myArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Class> getClassesLoaded() {
        return classesLoaded;
    }
}
