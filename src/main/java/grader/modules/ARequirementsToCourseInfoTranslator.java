package grader.modules;

import grader.config.StaticConfigurationUtils;
import grader.file.RootFolderProxy;
import grader.file.filesystem.AFileSystemRootFolderProxy;
import grader.file.zipfile.AZippedRootFolderProxy;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ARequirementsToCourseInfoTranslator {
    
    public static final String COURSE_PREFIX = "comp";
    public static final String MODULE_ROOT_PACKAGE = "gradingTools";

    public static RootFolderProxy findAssignmentsDirectory(PropertiesConfiguration aConfiguration) {
        String anImplicitRoot = StaticConfigurationUtils.getImplicitRequirementsRoot(aConfiguration);
        if (anImplicitRoot == null || anImplicitRoot.isEmpty()) {
            return null;
        }
//		InputStream retVal = this.getClass().getClassLoader().getResourceAsStream("bus/uigen/uiFrame.class");
//		InputStream retVal = this.getClass().getClassLoader().getResourceAsStream("bus");
//		URL url = getClass().getResource("bus/uigen/uiFrame.class");
//		URL url2 = getClass().getResource("/bus/uigen/uiFrame.class");
//		URL url3 = getClass().getResource("/oeall-22.jar");
//		URL url4 = getClass().getResource("oeall-22.jar");
        String[] aPathElements = System.getProperty("java.class.path").split(System.getProperty("path.separator"));

//        System.out.println("Path elements:" + aPathElements);
        String aGraderPath = getGraderPath(aPathElements);
        if (aGraderPath == null) {
            return null;
        }
        RootFolderProxy aGraderFileProxy;
        File aGraderFile = new File(aGraderPath);
        if (aGraderFile.isDirectory()) {
            aGraderFileProxy = new AFileSystemRootFolderProxy(aGraderFile.getAbsolutePath(), MODULE_ROOT_PACKAGE);
        } else {
            aGraderFileProxy = new AZippedRootFolderProxy(aGraderFile.getAbsolutePath());
        }
//        System.out.println("Grader file proxy:" + aGraderFileProxy);
        return aGraderFileProxy;
    }

    public static String getGraderPath(String[] aPathElements) {
        for (String aPathElement : aPathElements) {
            if (aPathElement.contains("rader")) {
                return aPathElement;
            }
        }
        return null;
    }

    public static String[] getRequirementModules(PropertiesConfiguration config) throws IOException {
        RootFolderProxy assignments = findAssignmentsDirectory(config);
        if (assignments == null) {
            return new String[]{};
        } else {
            final Path root = Paths.get(assignments.getAbsoluteName());
            final Set<String> modules = new HashSet<>();
            Files.walkFileTree(root, new FileVisitor<Path>() {

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.getFileName().toString().contains("Requirements")) {
                        Path relative = root.relativize(file);
                        String module = packageToModule(relative.toString());
                        if (!module.isEmpty()) {
                            modules.add(module);
                        }
                        return FileVisitResult.SKIP_SIBLINGS;
                    } else {
                        return FileVisitResult.CONTINUE;
                    }
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
                
            });
            return modules.toArray(new String[modules.size()]);
        }
    }
    
    /**
     * Expects assignments to be in one of these formats:
     * <ul><li>root.course.term.section.[lab].assignment</li>
     * <li>root. course.year.season.section.[lab].assignment</li></ul>
     * 
     * Formats to CourseTerm
     * @param pName string of assignment path (relative to root)
     * @return module name
     */
    private static String packageToModule(String pName) {
        String[] parts = pName.split("[/\\\\]");
        StringBuilder modName = new StringBuilder(10);
        
        int index = 0;
        if (parts[index++].equals(MODULE_ROOT_PACKAGE) && parts[index].toLowerCase().startsWith(COURSE_PREFIX)) {
            if (isModuleName(parts[index])) {
                if (isStrictModuleName(parts[index])) {
                    modName.append(parts[index]);
                } else {
                    modName.append(toStrictModuleName(parts[index]));
                }
            } else if (parts.length > 4) {
                modName.append(parts[index++].toLowerCase());
                modName.append("-");
                if (parts[index].matches("y[0-9]{4}")) {
                    modName.append(seasonToAbbr(parts[index+1]));
                    modName.append(parts[index].substring(parts[index++].length() - 2));
                    index++;
                } else {
                    modName.append(parts[index++]);
                }
                if (parts[index].matches("s[0-9]{3}")) {
                    modName.append("-").append(parts[index].substring(1));
                    
                }
            }
            
            if (!isStrictModuleName(modName.toString())) {
                modName.setLength(0);
            }
            
            if (modName.length() > 0) {
                System.out.println("Infering module '" + modName + "' from '" + pName + "'");
            }
        }
        return modName.toString();
    }
    
    private static String seasonToAbbr(String season) {
        switch(season) {
            case "f":
            case "fall":
                return "f";
            case "sp":
            case "spring":
                return "sp";
            case "mm":
            case "maymester":
                return "mm";
            case "s1":
            case "summer1":
                return "s1-";
            case "s2":
            case "summer2":
                return "s2-";
            default:
                return "";
        }
    }
    
    private static boolean isModuleName(String name) {
        return name.matches("comp[0-9]{3}[-]?(f|sp|mm|s1-?|s2-?)[0-9]{2}[-]?[0-9]{3}");
    }
    
    private static boolean isStrictModuleName(String name) {
        return name.matches("comp[0-9]{3}[-](f|sp|mm|s1-|s2-)[0-9]{2}[-][0-9]{3}");
    }
    
    private static String toStrictModuleName(String name) {
        String retVal;
        String[] nameParts = name.split("-");
        int index = 0;
        if (nameParts[index].matches("comp[0-9]{3}")) {
            retVal = nameParts[index++];
        } else {
            retVal = nameParts[index].substring(0, 7);
            nameParts[index] = nameParts[index].substring(7);
        }
        retVal += "-";
        if (nameParts[index].matches("(f|sp|mm)[0-9]{2}")) {
            retVal += nameParts[index++];
        } else if ("s1".equals(nameParts[index]) || "s2".equals(nameParts[index])) {
            retVal += nameParts[index++] + "-" + nameParts[index++];
        } else {
            retVal += nameParts[index].substring(0, nameParts[index].length() - 3);
            nameParts[index] = nameParts[index].substring(nameParts[index].length() - 3);
        }
        retVal += "-" + nameParts[index];
        
        return retVal;
    }
    
    public ARequirementsToCourseInfoTranslator() {

    }
}
