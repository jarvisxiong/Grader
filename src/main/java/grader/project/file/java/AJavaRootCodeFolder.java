package grader.project.file.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.maven.project.MissingProjectException;

import util.misc.Common;
import grader.file.FileProxy;
import grader.file.RootFolderProxy;
import grader.project.AMainClassFinder;
import grader.project.ExecutableFinderSelector;
import grader.project.JavaMainClassFinderSelector;
import grader.project.MainClassFinder;
import grader.project.file.RootCodeFolder;
import grader.trace.project.BinaryFolderIdentified;
import grader.trace.project.BinaryFolderNotFound;
import grader.trace.project.ProjectFolderNotFound;
import grader.trace.project.SourceFolderAssumed;
import grader.trace.project.SourceFolderIdentified;
import grader.trace.project.SourceFolderNotFound;
//a root folder containing source and binary directories
public class AJavaRootCodeFolder implements RootCodeFolder {
	public static final String SOURCE = "/src";
	public static final String BINARY = "/bin";
	public static final String BINARY_2 = "out";
	public static final String BINARY_3 = "build"; // net beans
	 static  String sourceFileSuffix = ".java";
	 static Map<String, String> languageToSourceFileSuffix = new HashMap<>();
	 static Map<String, String> languageToBinaryFileSuffix = new HashMap<>();
	 static Map<String, MainClassFinder> languageToMainClassFinder = new HashMap();
	 static String language;
		
		public static String JAVA_LANGUAGE = "Java";
		public static String C_LANGUAGE = "C";

	
	public static  String binaryFileSuffix = ".class";

	
	
	String sourceFolderName = SOURCE;
	String binaryFolderName = BINARY;
	RootFolderProxy root;
	String projectFolder; 
	// changing sourceFolder to rootfolderproxy from FileProxy as we do not need the more general type
	RootFolderProxy sourceFolder, binaryFolder;
	
	boolean separateSourceBinary = true;
	boolean hasSource;
	boolean hasBinary;
	boolean hasSourceFile;
	boolean hasBinaryFile;
	public AJavaRootCodeFolder(RootFolderProxy aRoot, String aSourceFolder, String aBinaryFolder) {
		root = aRoot;
		sourceFolderName = aSourceFolder;
		binaryFolderName = aBinaryFolder;
		SourceFolderIdentified.newCase(sourceFolderName, this);
		BinaryFolderIdentified.newCase(binaryFolderName, this);
		
	}
	
	String findParentOfSomeSourceFile(RootFolderProxy aRoot) { // will  not work with packages
		List<FileProxy> entries = aRoot.getFileEntries();
		for (FileProxy aFile:entries) {
			if (aFile.getAbsoluteName().endsWith(sourceFileSuffix)) {
				return aFile.getParentFolderName();
			}
		}
		return null;
		
		
	}

	public AJavaRootCodeFolder(RootFolderProxy aRoot) {
//		if (aRoot.getAbsoluteName().indexOf("erichman") != -1) {
//			System.out.println (" found erichman");
//		}
		// this code is a bit of a mess, need to clean this up
		root = aRoot;
		setSeparateSourcBinary();
		// should we not be doing this only if there is a separate source and binary?
		sourceFolderName = getEntryWithSuffix(aRoot, SOURCE);
		binaryFolderName = getEntryWithSuffix(aRoot, BINARY);
		// allow a set here
		if (binaryFolderName == null)
			binaryFolderName = getEntryWithSuffix(aRoot, BINARY_2);
		if (binaryFolderName == null)
			binaryFolderName = getEntryWithSuffix(aRoot, BINARY_3);

	
		
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
		if (hasBinaryFile)
		BinaryFolderIdentified.newCase(binaryFolderName, this);
		else
			BinaryFolderNotFound.newCase(root.getAbsoluteName(), this);
		
	}
	public boolean hasValidBinaryFolder() {
		return hasBinaryFile;
	}
	void setSeparateSourcBinary() {
		Set<String> names = root.getEntryNames();
		String srcPattern = SOURCE + "/";
		String binPattern = BINARY + "/";
		for (String name:names ) {
			if (!hasSource && name.indexOf(srcPattern) != -1) {
				hasSource = true;
			}
			if (!hasBinary && name.indexOf(binPattern) != -1) {
				hasBinary = true;
			}
//			if (!hasSourceFile && name.indexOf(SOURCE_FILE_SUFFIX) != -1) {
			if (!hasSourceFile && name.endsWith(sourceFileSuffix)) {

				hasSourceFile = true;
			}
//			if (!hasBinaryFile && name.indexOf(BINARY_FILE_SUFFIX) != -1) {// .classpath will fool this
			if (!hasBinaryFile && name.endsWith(getBinaryFileSuffix())) {

				hasBinaryFile = true;
			}
			if (hasSource && hasBinary && hasSourceFile && hasBinaryFile)
				break;
		}
		separateSourceBinary = hasSource || hasBinary;
		
	}
	public String toString() {
		return root.getLocalName();
	}
	public static String getEntryWithSuffix (RootFolderProxy aRoot, String suffix) {
		Set<String> nameSet = aRoot.getEntryNames();
		for (String name:nameSet) {
			int index = name.indexOf(suffix);
			if (index < 0)
				continue;
			if (!name.endsWith(suffix) && name.indexOf(suffix + "/") < 0)
				continue; // in case src and bin are not followed by / and are in intermediate directories
//			if (name.charAt(0) == '_')
//				continue;
//			if (name.indexOf("_macos") != -1)
//				continue;
			FileProxy proxy = aRoot.getFileEntry(name);
			String mixedCaseProxy = proxy.getMixedCaseAbsoluteName();
//			return name.substring(0, index + suffix.length());
			return mixedCaseProxy.substring(0, index + suffix.length());

//			if (name.endsWith(suffix))
//				return name;
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
	public static String getSourceFileSuffix() {
		return sourceFileSuffix;
	}

	public static void setSourceFileSuffix(String sourceFileSuffix) {
		AJavaRootCodeFolder.sourceFileSuffix = sourceFileSuffix;
	}
	public static String getBinaryFileSuffix() {
		return binaryFileSuffix;
	}

	public static void setBinaryFileSuffix(String binaryFileSuffix) {
		AJavaRootCodeFolder.binaryFileSuffix = binaryFileSuffix;
	}
	static void setComputedSuffixes() {
		
		
	}
	public static String getLanguage() {
		return language;
	}

	public static void setLanguage(String language) {
		AJavaRootCodeFolder.language = language;
		sourceFileSuffix = languageToSourceFileSuffix.get(getLanguage());
		binaryFileSuffix = languageToBinaryFileSuffix.get(getLanguage());
		
	}
	public static MainClassFinder getMainClassFinder() {
		return languageToMainClassFinder.get(getLanguage());
	}
	public static boolean isJava() {
		return getLanguage() == JAVA_LANGUAGE;
	}
	static {
		languageToSourceFileSuffix.put(JAVA_LANGUAGE, ".java");
		languageToBinaryFileSuffix.put(JAVA_LANGUAGE, ".class");
		languageToSourceFileSuffix.put(C_LANGUAGE, ".c");
		languageToBinaryFileSuffix.put(C_LANGUAGE, ".o");
		languageToMainClassFinder.put(JAVA_LANGUAGE, JavaMainClassFinderSelector.getMainClassFinder());

		languageToMainClassFinder.put(C_LANGUAGE, ExecutableFinderSelector.getMainClassFinder());

		
	}

}
