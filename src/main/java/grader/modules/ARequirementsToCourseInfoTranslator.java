package grader.modules;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import grader.config.StaticConfigurationUtils;
import grader.file.FileProxy;
import grader.file.RootFolderProxy;
import grader.file.filesystem.AFileSystemRootFolderProxy;
import grader.file.zipfile.AZippedRootFolderProxy;

import org.apache.commons.configuration.PropertiesConfiguration;

public class ARequirementsToCourseInfoTranslator {
	public ARequirementsToCourseInfoTranslator() {
		
	}
	
	public void findAssignmentsDirectory(PropertiesConfiguration aConfiguration) {
		String anImplicitRoot = StaticConfigurationUtils.getImplicitRequirementsRoot(aConfiguration);
		if (anImplicitRoot == null || anImplicitRoot.isEmpty())
			return ;
//		InputStream retVal = this.getClass().getClassLoader().getResourceAsStream("bus/uigen/uiFrame.class");
//		InputStream retVal = this.getClass().getClassLoader().getResourceAsStream("bus");
//		URL url = getClass().getResource("bus/uigen/uiFrame.class");
//		URL url2 = getClass().getResource("/bus/uigen/uiFrame.class");
//		URL url3 = getClass().getResource("/oeall-22.jar");
//		URL url4 = getClass().getResource("oeall-22.jar");
		String[] aPathElements = System.getProperty( "java.class.path" ).split( ";" );

		System.out.println("Path elements:" + aPathElements);
		String aGraderPath = getGraderPath(aPathElements);
		if (aGraderPath == null)
			return;
		RootFolderProxy aGraderFileProxy;
		File aGraderFile = new File(aGraderPath);
		if (aGraderFile.isDirectory()) {
			aGraderFileProxy = new AFileSystemRootFolderProxy(aGraderFile.getAbsolutePath(), "gradingTools");
		} else {
			aGraderFileProxy = new AZippedRootFolderProxy(aGraderFile.getAbsolutePath());
		}
		System.out.println("Grader file proxy:" + aGraderFileProxy);
		
	}
	
	public String getGraderPath(String[] aPathElements) {
		for (String aPathElement:aPathElements) {
			if (aPathElement.contains("rader")) return aPathElement;
		}
		return null;
	}

}
