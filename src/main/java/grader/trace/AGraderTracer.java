package grader.trace;

import framework.utils.GradingEnvironment;
import grader.config.ConfigurationManagerSelector;
import grader.modules.ModuleProblemManagerSelector;
import grader.trace.stepper.ProjectStepperEnded;
import grader.trace.trace.InteractionLogFileCreatedOrLoaded;
import grader.trace.trace.InteractionLogFolderCreated;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.trace.TraceableBus;

public class AGraderTracer implements GraderTracer {
	File logFile;
	Logger logger;
	public static final String LOG_FILE_PREFIX = "interactionLog";
	String fileName;
	PrintWriter out = null;
    BufferedWriter bufWriter;
	public AGraderTracer() {
		long currentTime = System.currentTimeMillis();
		Date currentDate = new Date(currentTime);
//		String monthDay = currentDate.getMonth() + "" + currentDate.getDay();
		String dateString = currentDate.toString();
		String[] parts = dateString.split(" ");
		String suffix = parts[1] + parts[2] + parts[5];
		String interactionLogFolder = 
				ConfigurationManagerSelector.getConfigurationManager().
					getStaticConfiguration().getString("grader.logger.interactionLogDirectory");
		File folder = new File(interactionLogFolder);
		if (!folder.exists()) {
			folder.mkdirs();
			InteractionLogFolderCreated.newCase(folder.getAbsolutePath(), this);
		}
		String userName = GradingEnvironment.get().getUserName();

		if (userName == null || userName.isEmpty())
			userName = "";
		else
			userName = userName + "_";
		fileName = interactionLogFolder + "/" + userName + LOG_FILE_PREFIX + suffix + ".csv";
		
//		logFile = new File(fileName);
//		if (!logFile.exists())
//			try {
//				logFile.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		logger = Logger.getLogger(fileName);
//		logger.log(Level.INFO, "foo, bar, foobar");
		out = null;
	    
	    try{
	        bufWriter =
	            Files.newBufferedWriter(
	                Paths.get(fileName),
	                Charset.forName("UTF8"),
	                StandardOpenOption.WRITE, 
	                StandardOpenOption.APPEND,
	                StandardOpenOption.CREATE);
	        out = new PrintWriter(bufWriter, true);
	    }catch(IOException e){
	    	e.printStackTrace();
	    	//Oh, no! Failed to create PrintWriter
	    }
	    
	    InteractionLogFileCreatedOrLoaded.newCase(fileName, this);
//	    TraceableBus.addTraceableListener(this);

//	    //After successful creation of PrintWriter
//	    out.println("Text to be appended, more text");
//	    out.flush();
//	    out.println("Text to be appended, more text");
//	    out.flush();


	    //After done writing, remember to close!
//	    out.close();
	}
	@Override
	public void newEvent(Exception aTraceable) {
//		if (aTraceable.getClass().getPackage() != AGraderTracer.class.getPackage()) return;
		if (!(aTraceable instanceof CSVSerializable)) return;
		out.println(((CSVSerializable) aTraceable).toCSVRow());
		out.flush();
		if (aTraceable instanceof ProjectStepperEnded) 
			out.close();
		
	}
//	public void startTracing() {
//		
//	}
	public static void main (String[] args) {
		new AGraderTracer();
	}

}
