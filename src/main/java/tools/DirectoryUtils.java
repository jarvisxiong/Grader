package tools;

import scala.Option;
import util.misc.Common;
import util.trace.Tracer;
import grader.language.LanguageDependencyManager;
import grader.project.file.ARootCodeFolder;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A set of utilities to assist with the recursive nature of directories.
 */
public class DirectoryUtils {

    /**
     * Finds the first file in the given folder that matches the provided filter
     * @param folder The folder to look in
     * @param filter The filter to apply
     * @return The first found file wrapped in an {@link Option} in case none was found.
     */
    public static Option<File> find(File folder, FileFilter filter) {
        File[] files = folder.listFiles(filter);
        if (files == null || files.length == 0) {
            return Option.empty();
        } else {
            return Option.apply(files[0]);
        }
    }

    /**
     * Looks for a folder with the given name recursively in a provided directory.
     * @param currentDir The directory to start looking in
     * @param folderName The name of the desired directory
     * @return The located directory wrapped in an {@link Option} in case it wasn't found
     */
    public static Option<File> locateFolder(File currentDir, final String folderName) {
        // Don't accept files (they don't make sense) or Mac meta folders
        if (!currentDir.isDirectory() || currentDir.getName().equals("__MACOSX")) {
            return Option.empty();
        } else {
            Option<File> folder = find(currentDir, new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().equals(folderName);
                }
            });
            if (folder.isDefined()) {
                return folder;
            } else {
                for (File file : currentDir.listFiles()) {
                    Option<File> possible = locateFolder(file, folderName);
                    if (possible.isDefined()) {
                        return possible;
                    }
                }
                return Option.empty();
            }
        }
    }

    /**
     * Looks for all files matching the provided filter recursively.
     * @param dir The directory to start looking from
     * @param filter The filter to apply
     * @return The set all of all files that matched the filter
     */
    public static Set<File> getFiles(File dir, final FileFilter filter) {
        // Get files in this folder
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !pathname.isDirectory() && filter.accept(pathname);
            }
        });
        Set<File> allFiles = new HashSet<File>();
        allFiles.addAll(Arrays.asList(files));

        // Get files in sub directories
        File[] directories = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        for (File directory : directories) {
            allFiles.addAll(getFiles(directory, filter));
        }

        return allFiles;
    }

	public static Set<File> getSourceFiles(File sourceFolder) {
	Set<File> javaFiles = getFiles(sourceFolder, new FileFilter() {
		@Override
		public boolean accept(File pathname) {
//			return pathname.getName().endsWith(".java");
			return pathname.getName().endsWith(LanguageDependencyManager.getSourceFileSuffix());

		}
	});
	return javaFiles;
	}
	
	public static boolean hasSuffix (String name, List<String> ignoreSuffixes) {
		for (String suffix:ignoreSuffixes) {
			if (name.endsWith(suffix)) {
                            return true;
                        }
			
		}
		return false; 
		
		
	}
	
	public static boolean compare (File correctDir, File testDir) {
		List<String> suffixes = new ArrayList<>();
		return compare(correctDir, testDir, suffixes);
		
	}
	
	public static boolean compare (File correctDir, File testDir, List<String> ignoreSuffixes) {
		boolean retVal = true;		
		if (!correctDir.isDirectory() || ! testDir.isDirectory()) {
			Tracer.error("test or corect dir not really directories");
			return false;
		}
		File[] correctChildren = correctDir.listFiles();
		File[] testChildren = testDir.listFiles();
		if (correctChildren.length != testChildren.length) {
			Tracer.error("correct and test dir not same size:" + correctDir.getAbsolutePath() + "(" + correctChildren.length + "," + testChildren.length + ")");
			Tracer.info(DirectoryUtils.class, "Correct:" +  Common.toString(correctChildren) + " Test:" + Common.toString(testChildren));
			retVal = false;
//			return false;
			
		}
		for (File correctChild:correctChildren) {
			File testChild = new File (testDir,  correctChild.getName());
			if (!testChild.exists()) {
				Tracer.error("test file does not exist:" + testChild.getName());
				retVal = false;
//				return false;
			}
			if (hasSuffix(correctChild.getName(), ignoreSuffixes)) {
                            continue;
                        }
			if (correctChild.isDirectory()) {
				if (!testChild.isDirectory()) {
					Tracer.error("Test file is not a directory:" + testChild.getName());
					retVal = false;
//					return false;
				}
					if (!compare(correctChild, testChild, ignoreSuffixes)) {
						retVal = false;
						continue;
//						return false;
					} else {
                                            continue;
                                }
				
			}
//			if (hasSuffix(correctChild.getName(), ignoreSuffixes))
//				continue;
			String correctText = Common.toText(correctChild).replaceAll("\r\n", "\n");
			String testText = Common.toText(testChild).replaceAll("\r\n", "\n");
			if (!correctText.equals(testText)) {
				Tracer.error("Not equal to test file:" + correctChild.getAbsolutePath());
				Tracer.info(DirectoryUtils.class, "-----------------Correct Text-----------------\n" + correctText);
				Tracer.info(DirectoryUtils.class,"-----------------Test Text-----------------\n" + testText);
				retVal = false;
//				return false;
			} else {
				Tracer.info(DirectoryUtils.class, "Equal to test file:" + correctChild.getAbsolutePath());
			}
			
			
		}		
		return retVal;
//		return true;
	}
	
	public static void main (String[] args) {
		File correctDir = new File ("Test Data/Correct 110 F13 Results/Assignment3");
		File testDir = new File ("Test Data/Test 110 F13 Assignments/Assignment3");
		String[] ignoreSuffixesArray = {".zip", ".ini", ".json"};
		List<String> ignoreSuffixesList = Arrays.asList(ignoreSuffixesArray);
		compare (correctDir, testDir, ignoreSuffixesList);
		
	}
}
