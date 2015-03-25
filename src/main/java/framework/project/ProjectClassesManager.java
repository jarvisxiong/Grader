package framework.project;

import framework.execution.RunningProject;
import grader.compilation.JavaClassFilesCompilerSelector;
import grader.language.LanguageDependencyManager;
import grader.navigation.NavigationKind;
import grader.project.AProject;
import grader.project.file.ARootCodeFolder;
import grader.sakai.project.SakaiProject;
import grader.settings.GraderSettingsModelSelector;
import grader.trace.compilation.SourceFileCompiled;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;

import scala.Option;
import tools.DirectoryUtils;
import util.trace.javac.CompilerNotFound;

/**
 * @see ClassesManager
 */
public class ProjectClassesManager implements ClassesManager {

    private final File buildFolder;
    private final File sourceFolder;
    private ClassLoader classLoader;
    private final Set<ClassDescription> classDescriptions;
    List<String> classNamesToCompile = new ArrayList();
    SakaiProject project;

    public ProjectClassesManager(SakaiProject aProject, File buildFolder, File sourceFolder) throws IOException,
            ClassNotFoundException {
        project = aProject;
        // Set the build and source folders for the project
        this.buildFolder = buildFolder;
        this.sourceFolder = sourceFolder;

        // Create the Class Loader and load the classes
        if (AProject.isLoadClasses()) {
            classLoader = new URLClassLoader(new URL[]{buildFolder.toURI().toURL()});
        }
        classDescriptions = new HashSet<ClassDescription>();

        loadClasses(sourceFolder);
    }

    /**
     * This loads all the classes based on the source code files.
     *
     * @param sourceFolder The folder containing the source code
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private void loadClasses(File sourceFolder) throws ClassNotFoundException, IOException {
        Set<File> sourceFiles = DirectoryUtils.getSourceFiles(sourceFolder);
//		Set<File> javaFiles = DirectoryUtils.getFiles(sourceFolder, new FileFilter() {
//			@Override
//			public boolean accept(File pathname) {
//				return pathname.getName().endsWith(".java");
//			}
//		});
        if (AProject.isCompileMissingObjectCode()
                || AProject.isForceCompile()
                || AProject.isPreCompileMissingObjectCode()) {

            // Check if any files need to be compiled
            ArrayList<File> aFilesToCompile = new ArrayList<File>();
            for (File file : sourceFiles) {
                String className = getClassName(file);
                File classFile = getClassFile(className);
                if (shouldCompile(file, classFile)) {
                    classNamesToCompile.add(className);
                    aFilesToCompile.add(file);
                }
            }
            if (aFilesToCompile.size() > 0) {
                if (GraderSettingsModelSelector.getGraderSettingsModel() != null
                        && GraderSettingsModelSelector.getGraderSettingsModel().getNavigationSetter().getNavigationKind() != NavigationKind.AUTOMATIC
                        && !AProject.isPreCompileMissingObjectCode()) {
                    return;
                }
                try {
                    System.out.println("Attempting to compile files.");
    //				compile(aFilesToCompile);
                    //				JavaClassFilesCompilerSelector.getClassFilesCompiler().compile(buildFolder, aFilesToCompile);
                    RunningProject runningProject = LanguageDependencyManager.getSourceFilesCompiler().compile(sourceFolder, buildFolder, aFilesToCompile);
                    if (runningProject != null) {
                        //					String outputAndErrors = runningProject.getOutputAndErrors();
                        runningProject.appendOutputAndErrorsToTranscriptFile(project);

                    }
                    System.out.println("Compilation attempt finished.");
                } catch (Exception e) {
                    System.out.println("Compilation failed: " + e.toString());
                }
            }
        }

		for (File file : sourceFiles) {
			String className = getClassName(file);
			// System.out.println(className);
			try {
				Class c = null;
				if (AProject.isLoadClasses()) {
					c = classLoader.loadClass(className);
				}

				if (c != null) {
					classDescriptions.add(new BasicClassDescription(c, file));
				}
			} catch (UnsupportedClassVersionError e) {

				// } catch (UnsupportedClassVersionError |
				// IncompatibleClassChangeError e) {
				try {
					System.out
							.println("Class files are the incorrect version for the current Java version. Attempting to recompile files.");
//					List<File> recompiledFileList = new ArrayList<>();
//					recompiledFileList.add(file);
					List<File> recompiledFileList = new ArrayList<>(sourceFiles);
//					recompiledFileList.add(file);
					System.out.println("Recompiling files:" + recompiledFileList);
					RunningProject runningProject = LanguageDependencyManager
							.getSourceFilesCompiler().compile(sourceFolder,
									buildFolder, recompiledFileList);
					if (runningProject != null) {
						runningProject
								.appendOutputAndErrorsToTranscriptFile(project);

					}
					System.out.println("Compilation attempt finished.");

					Class c = null;
					if (AProject.isLoadClasses()) {
						c = classLoader.loadClass(className);
					}

					if (c != null) {
						classDescriptions
								.add(new BasicClassDescription(c, file));
					}
				} catch (Exception ex) {
					System.out.println("Compilation failed: " + ex.toString());
				}
			} catch (Exception e) {
				System.out.println("Could not load class:" + file);
				e.printStackTrace();
			} catch (Error e) {
				e.printStackTrace();
				throw new IOException(e.getMessage());
			}/*
			 * catch (Exception e) { throw new IOException(e.getMessage()); }
			 */

		}
    }

    /**
     * Given a file, this finds the canonical class name.
     *
     * @param file The Java file
     * @return The canonical class name.
     * @throws IOException
     */
    private String getClassName(File file) throws IOException {

        // Figure out the package
        List<String> lines = FileUtils.readLines(file, null);
        String packageName = "";
        for (String line : lines) {
            if (line.startsWith("package ")) {
                packageName = line.replace("package", "").replace(";", "").trim() + ".";
            }
        }

        // Figure out the class name and combine it with the package
//		String className = file.getName().replace(".java", "");
        String className = file.getName().replace(LanguageDependencyManager.getSourceFileSuffix(), "");

        return packageName + className;
    }

    /**
     * Given a Java class name, this finds associated .class file.
     *
     * @param className The canonical name of the Java class
     * @return The .class File.
     */
    private File getClassFile(String className) {

        File classFolder = buildFolder;
        String[] splitClassName = className.split("\\.");
        for (int i = 0; i < splitClassName.length - 1; i++) {
            String packagePart = splitClassName[i];
            classFolder = new File(classFolder, packagePart);
        }

        String classFileName;
        if (splitClassName.length > 0) {
//			classFileName = splitClassName[splitClassName.length - 1] + ".class";
            classFileName = splitClassName[splitClassName.length - 1] + LanguageDependencyManager.getBinaryFileSuffix();

        } else {
            classFileName = className + ".class";
        }

        return new File(classFolder, classFileName);
    }

    /**
     * Given a .java and .class file, returns whether the .java file needs to be
     * compiled or recompiled
     *
     * @param javaFile The Java file
     * @param classFile The class file
     * @return boolean true if should compile/recompile false otherwise
     */
    private boolean shouldCompile(File javaFile, File classFile) {
//		System.out.println("Class time:" + classFile.lastModified() + " source time:" + javaFile.lastModified());

        return AProject.isForceCompile()
                || !classFile.exists()
                || classFile.lastModified() < javaFile.lastModified();
//				(classFile.lastModified() - javaFile.lastModified()) < 1000;

    }

//	/**
//	 * Given an ArrayList of .javaFiles, returns whether the .java file needs to
//	 * be compiled or recompiled
//	 * 
//	 * @param javaFiles
//	 *            ArrayList of .java files
//	 * @throws IOException
//	 */
//	private void compile(ArrayList<File> javaFiles) throws IOException, IllegalStateException {
//
//		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//		if (compiler != null) {
//			StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
//
//			List<String> optionList = new ArrayList<String>();
//			// set the output directory for the compiler
//			String buildFolderPath = buildFolder.getCanonicalPath();
//			optionList.addAll(Arrays.asList("-d", buildFolderPath));
//			System.out.println(buildFolderPath);
//
//			Iterable<? extends JavaFileObject> compilationUnits = fileManager
//					.getJavaFileObjectsFromFiles(javaFiles);
//			compiler.getTask(null, fileManager, null, optionList, null, compilationUnits).call();
//			for (File javaFile:javaFiles) {
//				SourceFileCompiled.newCase(javaFile.getAbsolutePath(), this);
//				
//			}
//		} else {
////			throw new RuntimeException("Compiler not accessible");
//			throw CompilerNotFound.newCase(this);
//		}
//	}
    @Override
    public ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * Looks for a class description given a class name (simple or canonical)
     *
     * @param className The name of the class to find
     * @return The class description wrapped in a {@link Option} in case none
     * was found.
     */
    @Override
    public Option<ClassDescription> findByClassName(String className) {
        // First search the simple names
        for (ClassDescription description : classDescriptions) {
            if (description.getJavaClass().getSimpleName().equalsIgnoreCase(className)) {
                return Option.apply(description);
            }
        }

        // Next search the canonical names
        for (ClassDescription description : classDescriptions) {
            if (description.getJavaClass().getCanonicalName().equalsIgnoreCase(className)) {
                return Option.apply(description);
            }
        }
        return Option.empty();
    }

    /**
     * Looks for all class descriptions with a particular tag
     *
     * @param tag The tag to search for
     * @return The set of matching class descriptions
     */
    @Override
    public Set<ClassDescription> findByTag(String tag) {
        Set<ClassDescription> classes = new HashSet<ClassDescription>();
        for (ClassDescription description : classDescriptions) {
            for (String t : description.getTags()) {
                if (t.equalsIgnoreCase(tag)) {
                    classes.add(description);
                }
            }
        }
        return classes;
    }

    /**
     * @return All class descriptions
     */
    @Override
    public Set<ClassDescription> getClassDescriptions() {
        return classDescriptions;
    }

    @Override
    public List<String> getClassNamesToCompile() {
        return classNamesToCompile;
    }

    @Override
    public void setClassNamesToCompile(List<String> classNamesToCompile) {
        this.classNamesToCompile = classNamesToCompile;
    }
}
