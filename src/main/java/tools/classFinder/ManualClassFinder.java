package tools.classFinder;

import java.util.HashMap;
import java.util.Map;

import scala.Option;
import grader.basics.project.ClassDescription;
import grader.basics.project.Project;

/**
 * This is part of the manual class locator system.
 */
public class ManualClassFinder {

    private static Map<Project, CachedProjectClassFinder> cache = new HashMap<Project, CachedProjectClassFinder>();

    public static Option<ClassDescription> find(Project project, String tag) {
        // Get the project cache
        if (!cache.containsKey(project))
            cache.put(project, new CachedProjectClassFinder(project));
        return cache.get(project).get(tag);
    }

}
