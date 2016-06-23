package framework.project;

import java.util.List;
import java.util.Set;

/**
 * Based on {@link grader.project.flexible.FlexibleClassesManager}
 */
public interface ClassesManager {

    /**
     * @return The {@link ClassLoader} created by this manager.
     */
    public ClassLoader getClassLoader();

    /**
     * Attempts to find a class description based on the name of the class
     *
     * @param className The name of the class to find
     * @return The {@link ClassDescription} wrapped in an {@link scala.Option} in case none is found.
     */
    public List<ClassDescription> findByClassOrInterfaceName(String className);

    /**
     * Attempts to find a class description based on a tag
     *
     * @param tag The tag to search for
     * @return The {@link ClassDescription} wrapped in an {@link scala.Option} in case none is found.
     */
    public List<ClassDescription> findClassesAndInterfacesByTag(String tag);

    /**
     * @return All the {@link ClassDescription}
     */
    public Set<ClassDescription> getClassDescriptions();

	List<String> getClassNamesToCompile();

	void setClassNamesToCompile(List<String> classNamesToCompile);


	List<ClassDescription> findByTagMatch(String regex);

	List<ClassDescription> findByClassOrInterfaceNameMatch(String className);

	List<ClassDescription> findClassesAndInterfaces(String aName, String aTag,
			String aNameMatch, String aTagMatch);

	List<ClassDescription> findClassesAndInterfacesByPattern(String tag);

	List<ClassDescription> findClassesAndInterfacesByTag(String[] aTags);

	List<ClassDescription> findClassAndInterfaces(String aName, String[] aTag,
			String aNameMatch, String aTagMatch);

//	List<Method> findMethodByName(Class aClass, String aSpecification);
//
//	List<Method> findMethodByNameMatch(Class aClass, String aSpecification);
//
//	List<Method> findMethodByTagMatch(Class aClass, String aSpecification);
//
//	List<Method> findMethodByTag(Class aClass, String aSpecification);

}
