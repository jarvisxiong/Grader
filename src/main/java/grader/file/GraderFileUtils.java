package grader.file;

import java.nio.file.Path;
import java.nio.file.Paths;
import util.trace.Tracer;

/**
 *
 * @author Andrew Vitkus
 */
public class GraderFileUtils {

    public static String toRelativeName(String aParentName, String aChildName) {
        try {
            //System.out.println("toRelativeName was called");
            Path parent = Paths.get(aParentName);
            Path child = Paths.get(aChildName);
            Path relative = parent.relativize(child);
            String retVal = relative.toString();
            return retVal;
        } catch (Exception e) {
            Tracer.error(aParentName + " is not in " + aParentName);
            e.printStackTrace();
            return null;
        }
    }
}
