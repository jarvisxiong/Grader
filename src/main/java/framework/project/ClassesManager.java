package framework.project;

import scala.Option;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * Based on {@link grader.project.ClassesManager}
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
    public List<ClassDescription> findByClassName(String className);

    /**
     * Attempts to find a class description based on a tag
     *
     * @param tag The tag to search for
     * @return The {@link ClassDescription} wrapped in an {@link scala.Option} in case none is found.
     */
    public List<ClassDescription> findClassByTag(String tag);

    /**
     * @return All the {@link ClassDescription}
     */
    public Set<ClassDescription> getClassDescriptions();

	List<String> getClassNamesToCompile();

	void setClassNamesToCompile(List<String> classNamesToCompile);


	List<ClassDescription> findByTagMatch(String regex);

	List<ClassDescription> findByClassNameMatch(String className);

	List<ClassDescription> findClass(String aName, String aTag,
			String aNameMatch, String aTagMatch);

	List<ClassDescription> findClassByPattern(String tag);

	List<ClassDescription> findClassByTag(String[] aTags);

	List<ClassDescription> findClass(String aName, String[] aTag,
			String aNameMatch, String aTagMatch);

//	List<Method> findMethodByName(Class aClass, String aSpecification);
//
//	List<Method> findMethodByNameMatch(Class aClass, String aSpecification);
//
//	List<Method> findMethodByTagMatch(Class aClass, String aSpecification);
//
//	List<Method> findMethodByTag(Class aClass, String aSpecification);

}
