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
            Path parent = Paths.get(aParentName.toLowerCase());
            
            Path child = Paths.get(aChildName.toLowerCase());
//            String parentLC = parent.toLowerCase(); 
            Path relative = parent.relativize(child);
            String relativeName = relative.toString();
            int relativeNameIndex = aChildName.length() - relativeName.length();
            String retVal = aChildName.substring(relativeNameIndex);
           
            retVal = retVal.replaceAll("\\\\", "/");
            return retVal;
        } catch (Exception e) {
            Tracer.error(aParentName + " is not in " + aParentName);
            e.printStackTrace();
            return null;
        }
    }
}
