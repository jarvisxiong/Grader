package grader.project.file;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.maven.project.MissingProjectException;

import util.misc.Common;
import grader.compilation.ClassFilesCompiler;
import grader.compilation.JavaClassFilesCompilerSelector;
import grader.compilation.c.CFilesCompilerSelector;
import grader.execution.AMainClassFinder;
import grader.execution.AnExecutableFinder;
import grader.execution.ExecutableFinderSelector;
import grader.execution.JavaMainClassFinderSelector;
import grader.execution.MainClassFinder;
import grader.file.FileProxy;
import grader.file.RootFolderProxy;
import grader.language.LanguageDependencyManager;
import grader.trace.project.BinaryFolderIdentified;
import grader.trace.project.BinaryFolderNotFound;
import grader.trace.project.ProjectFolderNotFound;
import grader.trace.project.SourceFolderAssumed;
import grader.trace.project.SourceFolderIdentified;
import grader.trace.project.SourceFolderNotFound;
import java.nio.file.Paths;
//a root folder containing source and binary directories

public class ARootCodeFolder implements RootCodeFolder {

    public static final String SOURCE = "src";
    public static final String BINARY = "bin";
    public static final String BINARY_2 = "out";
    public static final String BINARY_3 = "build"; // net beans
//	 static  String sourceFileSuffix = ".java";
//	 static Map<String, String> languageToSourceFileSuffix = new HashMap<>();
//	 static Map<String, String> languageToBinaryFileSuffix = new HashMap<>();
//	 static Map<String, MainClassFinder> languageToMainClassFinder = new HashMap();
//	 static Map<String, ClassFilesCompiler> languageToCompiler = new HashMap();
//
//	 static String language;
//		
//		public static String JAVA_LANGUAGE = "Java";
//		public static String C_LANGUAGE = "C";
//
//	
//	public static  String binaryFileSuffix = ".class";

    String sourceFolderName = SOURCE;
    String binaryFolderName = BINARY;
    RootFolderProxy root;
    String projectFolder;
    // changing sourceFolder to rootfolderproxy from FileProxy as we do not need the more general type
    RootFolderProxy sourceFolder, binaryFolder;
//	FileProxy sourceFolder, binaryFolder;

    boolean separateSourceBinary = true;
    boolean hasSource;
    boolean hasBinary;
    boolean hasSourceFile;
    boolean hasBinaryFile;

    public ARootCodeFolder(RootFolderProxy aRoot, String aSourceFolder, String aBinaryFolder) {
        root = aRoot;
        sourceFolderName = aSourceFolder;
        binaryFolderName = aBinaryFolder;
        SourceFolderIdentified.newCase(sourceFolderName, this);
        BinaryFolderIdentified.newCase(binaryFolderName, this);

    }

    String findParentOfSomeSourceFile(RootFolderProxy aRoot) { // will  not work with packages
        List<FileProxy> entries = aRoot.getFileEntries();
        for (FileProxy aFile : entries) {
//			if (aFile.getAbsoluteName().endsWith(sourceFileSuffix)) {
            if (aFile.getAbsoluteName().endsWith(LanguageDependencyManager.getSourceFileSuffix())) {

                return aFile.getParentFolderName();
            }
        }
        return null;

    }

    public ARootCodeFolder(RootFolderProxy aRoot) {
//		if (aRoot.getAbsoluteName().indexOf("erichman") != -1) {
//			System.out.println (" found erichman");
//		}
        // this code is a bit of a mess, need to clean this up
        root = aRoot;
        setSeparateSourceBinary();
        // should we not be doing this only if there is a separate source and binary?
        sourceFolderName = getFolderWithName(aRoot, SOURCE);
        binaryFolderName = getFolderWithName(aRoot, BINARY);
        // allow a set here
        if (binaryFolderName == null) {
            binaryFolderName = getFolderWithName(aRoot, BINARY_2);
        }
        if (binaryFolderName == null) {
            binaryFolderName = getFolderWithName(aRoot, BINARY_3);
        }
//		if (sourceFolderName == null || binaryFolderName == null) {
        if (sourceFolderName == null && binaryFolderName == null) {
            System.out.println(SourceFolderNotFound.newCase(root.getLocalName(), this).getMessage());
            sourceFolderName = findParentOfSomeSourceFile(aRoot);
            if (sourceFolderName != null) {
                SourceFolderAssumed.newCase(sourceFolderName, this);

//			sourceFolderName = sourceFolder.getAbsoluteName();
                binaryFolderName = sourceFolderName;
            } else {
                throw ProjectFolderNotFound.newCase(aRoot.getLocalName(), this);

//		sourceFolderName = aRoot.getAbsoluteName();
//		binaryFolderName = sourceFolderName;
//		// this should go
//		if (separateSourceBinary) {
//			sourceFolderName +=  "/" + SOURCE;
//			binaryFolderName += "/" + BINARY;
//			
//		}
            }
        }

        if (separateSourceBinary) {
            if (hasValidBinaryFolder()) {
                projectFolder = Common.getParentFileName(binaryFolderName);
                sourceFolder = root.getFileEntry(sourceFolderName);
//			if (sourceFolder ==null ) {
//				System.out.println("All children" + root.getEntryNames());
//				System.out.println("not found:" + sourceFolderName);
//			}
                binaryFolder = root.getFileEntry(binaryFolderName);
            } else {
                binaryFolder = root; // will this cause problems?
                binaryFolderName = root.getAbsoluteName();
                projectFolder = binaryFolderName;
            }
        } else {
            projectFolder = binaryFolderName;
            //added this.
            sourceFolder = root.getFileEntry(sourceFolderName + "/"); //no idea whey I need sometimes ending backslash, need to debu

//			sourceFolder = root;
        }

        SourceFolderIdentified.newCase(sourceFolderName, this);
        if (hasBinaryFile) {
            BinaryFolderIdentified.newCase(binaryFolderName, this);
        } else {
            BinaryFolderNotFound.newCase(root.getAbsoluteName(), this);
        }

    }

    public boolean hasValidBinaryFolder() {
        return hasBinaryFile;
    }

    void setSeparateSourceBinary() {
        Set<String> names = root.getEntryNames();
        String srcPattern = SOURCE + "/";
        String binPattern = BINARY + "/";
        for (String name : names) {
            if (!hasSource && name.indexOf(srcPattern) != -1) {
                hasSource = true;
            }
            if (!hasBinary && name.indexOf(binPattern) != -1) {
                hasBinary = true;
            }
//			if (!hasSourceFile && name.indexOf(SOURCE_FILE_SUFFIX) != -1) {
            if (!hasSourceFile && name.endsWith(LanguageDependencyManager.getSourceFileSuffix())) {

                hasSourceFile = true;
            }
//			if (!hasBinaryFile && name.indexOf(BINARY_FILE_SUFFIX) != -1) {// .classpath will fool this
            if (!hasBinaryFile && name.endsWith(LanguageDependencyManager.getBinaryFileSuffix())) {

                hasBinaryFile = true;
            }
            if (hasSource && hasBinary && hasSourceFile && hasBinaryFile) {
                break;
            }
        }
        separateSourceBinary = hasSource || hasBinary;

    }

    public String toString() {
        return root.getLocalName();
    }
//	public static String getEntryWithSuffix (RootFolderProxy aRoot, String suffix) {
//		Set<String> nameSet = aRoot.getEntryNames();
//		for (String name:nameSet) {
//			int index = name.indexOf(suffix);
//			if (index < 0)
//				continue;
//			
//			// if name ends with suffix we should proceed, or if suffix/ is an intermediate directory in zip file path we should proceed
//			if (!name.endsWith(suffix) && name.indexOf(suffix + "/") < 0)
////			if (!(name.endsWith(suffix)|| name.indexOf(suffix + "/") >= 0))
//
//				continue; // in case src and bin are not followed by / and are in intermediate directories
////			if (name.charAt(0) == '_')
////				continue;
////			if (name.indexOf("_macos") != -1)
////				continue;
//			FileProxy proxy = aRoot.getFileEntry(name);
//			String mixedCaseProxy = proxy.getMixedCaseAbsoluteName();
//			if (name.endsWith(suffix))
////			return name.substring(0, index + suffix.length());
//			return mixedCaseProxy.substring(0, index + suffix.length());
//			else {
//				return mixedCaseProxy.substring(0, index + suffix.length());
//
////			if (name.endsWith(suffix))
////				return name;
//		}
//		return null;
//	}
    
    public static String getEntryWithSuffixAndrew(RootFolderProxy aRoot, String suffix) {
        Set<String> nameSet = aRoot.getEntryNames();
        String zipSuffix = "";
        for (String name : nameSet) {
            String filename = Paths.get(name).getFileName().toString();
            if (filename.endsWith(suffix)
                    || filename.endsWith(suffix + System.getProperty("path.separator"))) {
                if (name.contains(".zip")) {
                    zipSuffix = name;
                } else {
                    return name;
                }
            }
        }
        if (!zipSuffix.isEmpty()) {
            return zipSuffix;
        }
        return null;
    }
		/*
     * We get names of files, not directories, for zips
     * for file system, we get both
     * So a bin must be extracted from file names.
     * Two cases: b
     */

    public static String getFolderWithName(RootFolderProxy aRoot, String aName) {
        Set<String> nameSet = aRoot.getEntryNames();
        String separator = System.getProperty("path.separator");
        for (String name : nameSet) {
            
            int index = name.indexOf(aName);
            if (index < 0) {
                continue;
            }
//            int intermediateIndex = name.indexOf(aName + "/");
            int intermediateIndex = name.indexOf(aName + separator);
            boolean nameEndsWithSuffix = name.length() == index + aName.length();
            // if name ends with suffix we should proceed, or if suffix/ is an intermediate directory in zip file path we should proceed
//				if (!name.endsWith(suffix) && name.indexOf(suffix + "/") < 0)
//				if (!(name.endsWith(suffix)|| name.indexOf(suffix + "/") >= 0))
            if (intermediateIndex < 0 // not an intermediate directory)
                    && !nameEndsWithSuffix // not an end name
                    ) {
                continue; // in case src and bin are not followed by / and are in intermediate directories
            }//				if (name.charAt(0) == '_')
//					continue;
//				if (name.indexOf("_macos") != -1)
//					continue;
            FileProxy proxy = aRoot.getFileEntry(name);
            String mixedCaseProxy = proxy.getMixedCaseAbsoluteName();
            if (nameEndsWithSuffix) //				return name.substring(0, index + suffix.length());
            {
                return mixedCaseProxy.substring(0, index + aName.length());
            } else {
                return mixedCaseProxy.substring(0, intermediateIndex + aName.length());
            }

//				if (name.endsWith(suffix))
//					return name;
        }
        return null;
    }

    @Override
    public FileProxy sourceFile(String aClassName) {
        String sourceFileName = Common.classNameToSourceFileName(aClassName);
        if (separateSourceBinary) {
            sourceFileName = SOURCE + "/" + sourceFileName;
        }
        return root.getFileEntry(sourceFileName);
    }

    @Override
    public RootFolderProxy getRootFolder() {
        return root;
    }

    @Override
    public String getProjectFolderName() {
        return projectFolder;
    }

    @Override
    public FileProxy binaryFile(String aClassName) {
        String binaryFileName = Common.classNameToBinaryFileName(aClassName);
        if (separateSourceBinary) {
            binaryFileName += BINARY + "/" + binaryFileName;
        }
        return root.getFileEntry(binaryFileName);
    }

    @Override
    public String getAbsoluteName() {
        // TODO Auto-generated method stub
        return root.getAbsoluteName();
    }
    
    @Override
    public String getMixedCaseAbsoluteName() {
        // TODO Auto-generated method stub
        return root.getMixedCaseAbsoluteName();
    }
    

    @Override
    public String getLocalName() {
        // TODO Auto-generated method stub
        return root.getAbsoluteName();
    }

    @Override
    public List<FileProxy> getFileEntries() {
        // TODO Auto-generated method stub
        return root.getFileEntries();
    }

    @Override
    public FileProxy getFileEntry(String name) {
        // TODO Auto-generated method stub
        return root.getFileEntry(name);
    }

    @Override
    public String getSourceProjectFolderName() {
        return sourceFolderName;
    }

    @Override
    public String getBinaryProjectFolderName() {
        return binaryFolderName;
    }

    @Override
    public Set<String> getEntryNames() {
        return root.getEntryNames();
    }

    public boolean hasSource() {
        return hasSource;
    }

    public boolean hasBinary() {
        return hasBinary;
    }

    public boolean hasSeparateSourceBinary() {
        return separateSourceBinary;
    }

    @Override
    public String getMixedCaseSourceProjectFolderName() {
        // TODO Auto-generated method stub
        return sourceFolder.getMixedCaseAbsoluteName();
    }
//	public static String getSourceFileSuffix() {
//		return LanguageDependencyManager.getSourceFileSuffix();
//	}
//
//	public static void setSourceFileSuffix(String sourceFileSuffix) {
//		LanguageDependencyManager.setSourceFileSuffix(sourceFileSuffix);
//	}
//	public static String getBinaryFileSuffix() {
//		return LanguageDependencyManager.getBinaryFileSuffix();
//	}

    @Override
    public RootFolderProxy getSourceFolder() {
        return sourceFolder;
    }

    @Override
    public RootFolderProxy getBinaryFolder() {
        return binaryFolder;
    }

//	public static void setBinaryFileSuffix(String binaryFileSuffix) {
//		LanguageDependencyManager.setBinaryFileSuffix(binaryFileSuffix);
//	}
//	static void setComputedSuffixes() {
//		
//		
//	}
//	public static String getLanguage() {
//		return LanguageDependencyManager.getLanguage();
//	}
//	public static void setLanguage(String language) {
////		ARootCodeFolder.language = language;
////		sourceFileSuffix = languageToSourceFileSuffix.get(getLanguage());
////		binaryFileSuffix = languageToBinaryFileSuffix.get(getLanguage());
//		LanguageDependencyManager.setLanguage(language);
//		
//	}
//	public static MainClassFinder getMainClassFinder() {
////		return languageToMainClassFinder.get(getLanguage());
//		return LanguageDependencyManager.getMainClassFinder();
//	}
//	public static ClassFilesCompiler getSourceFilesCompiler() {
////		return languageToCompiler.get(getLanguage());
//		return LanguageDependencyManager.getSourceFilesCompiler();
//	}
//	public static boolean isJava() {
////		return getLanguage() == JAVA_LANGUAGE;
//		return LanguageDependencyManager.isJava();
//	}
//	static {
//		languageToSourceFileSuffix.put(JAVA_LANGUAGE, ".java");
//		languageToBinaryFileSuffix.put(JAVA_LANGUAGE, ".class");
//		languageToSourceFileSuffix.put(C_LANGUAGE, ".c");
//		languageToBinaryFileSuffix.put(C_LANGUAGE, ".obj");
//		
//		languageToMainClassFinder.put(JAVA_LANGUAGE, JavaMainClassFinderSelector.getMainClassFinder());
//		languageToMainClassFinder.put(C_LANGUAGE, ExecutableFinderSelector.getMainClassFinder());
//		
//		languageToCompiler.put(JAVA_LANGUAGE, JavaClassFilesCompilerSelector.getClassFilesCompiler() );
//		languageToCompiler.put(C_LANGUAGE, CFilesCompilerSelector.getClassFilesCompiler());
//
//		
//	}
}
