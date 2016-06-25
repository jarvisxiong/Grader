package tools.classFinder2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import scala.Option;
import framework.grading.testing.NotAutomatableException;
import framework.project.ClassDescription;
import framework.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/8/13
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClassFinder {
    private static Map<Project, ClassFinder> projectCache = new HashMap<Project, ClassFinder>();

    public static ClassFinder get(Project project) {
        if (!projectCache.containsKey(project))
            projectCache.put(project, new ClassFinder(project));
        return projectCache.get(project);
    }

    private Project project;
    private Map<String, Option<ClassDescription>> classCache = new HashMap<String, Option<ClassDescription>>();

    private ClassFinder(Project project) {
        this.project = project;
    }

    /**
     * Finds the first class with the given tag. If no class is found then it asks the grader which one it is.
     * @param tag The tag to search for
     * @return The class description wrapped in an {@link Option} in case none was found
     */
    public Option<ClassDescription> findByTag(String tag, boolean autoGrade) throws NotAutomatableException {
        return findByTag(tag, autoGrade, ClassType.UNDEFINED);
    }
    
    /**
     * Finds the first class with the given tag. If no class is found then it asks the grader which one it is.
     * @param tag The tag to search for
     * @return The class description wrapped in an {@link Option} in case none was found
     */
    public Option<ClassDescription> findByTag(String tag, boolean autoGrade, ClassType type) throws NotAutomatableException {
        // First check the cache
        if (classCache.containsKey(tag)) {
            Option<ClassDescription> descOpt = classCache.get(tag);
            if (!descOpt.isEmpty()) {
                ClassDescription desc = descOpt.get();
                if (type.equals(ClassType.UNDEFINED) || type.equals(ClassType.getClassType(desc.getJavaClass()))) {
                    return classCache.get(tag);
                }
            }
            classCache.remove(tag);
        }

        // We haven't search for that yet. Let's look starting with the tags
        Set<ClassDescription> descriptions = project.getClassesManager().get().findByTag(tag);
        if (!type.equals(ClassType.UNDEFINED)) {
            for(ClassDescription description : descriptions.toArray(new ClassDescription[descriptions.size()])) {
                if (!ClassType.getClassType(description.getJavaClass()).equals(type)) {
                    descriptions.remove(description);
                }
            }
        }
        Option<ClassDescription> classDescription;
        if (descriptions.isEmpty()) {
            if (autoGrade)
                throw new NotAutomatableException();
            classDescription = ask(tag);
        } else
            classDescription = Option.apply(new ArrayList<ClassDescription>(descriptions).get(0));
        classCache.put(tag, classDescription);
        return classDescription;
    }

    private Option<ClassDescription> ask(String description) {
        List<ClassDescription> classDescriptions = new ArrayList<ClassDescription>(project.getClassesManager().get().getClassDescriptions());
        Collections.sort(classDescriptions, new Comparator<ClassDescription>() {
            @Override
            public int compare(ClassDescription o1, ClassDescription o2) {
                return o1.getJavaClass().getCanonicalName().compareTo(o2.getJavaClass().getCanonicalName());
            }
        });

        ClassDescription classDescription = (ClassDescription) JOptionPane.showInputDialog(null,
                "Which class is: \"" + description + "\"? (Click Cancel if doesn't exist)",
                "Class finder", JOptionPane.QUESTION_MESSAGE, null,
                classDescriptions.toArray(),
                null
        );
        if (classDescription == null)
            return Option.empty();
        return Option.apply(classDescription);
    }
}
