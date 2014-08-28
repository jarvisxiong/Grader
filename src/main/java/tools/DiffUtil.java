package tools;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import util.misc.Common;

public class DiffUtil {
	
	public static void main (String[] args) {
	   String diffTool =	"D:/Program Files/Git/bin/diff.exe";
	   String aCanonicalPath = "\"" + diffTool + "\"";
//		ProcessBuilder aProcessBuilder = new ProcessBuilder(aCanonicalPath, correctChild.getAbsolutePath(), testChild.getAbsolutePath(), ">", testChild.getAbsolutePath()+"diff" );
		ProcessBuilder aProcessBuilder = new ProcessBuilder(aCanonicalPath );

		aProcessBuilder.redirectError(Redirect.INHERIT);
		aProcessBuilder.redirectOutput(Redirect.INHERIT);

		try {
			Process aProcess = aProcessBuilder.start();

			aProcess.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String diffText = Common.toText(new File(testChild.getAbsolutePath()+"diff"));
 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
