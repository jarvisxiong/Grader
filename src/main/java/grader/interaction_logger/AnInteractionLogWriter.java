package grader.interaction_logger;

import framework.utils.GradingEnvironment;
import grader.config.ConfigurationManagerSelector;
import grader.modules.ModuleProblemManagerSelector;
import grader.trace.CSVSerializable;
import grader.trace.interaction_logger.InteractionLogEntryAdded;
import grader.trace.interaction_logger.InteractionLogFileCreatedOrLoaded;
import grader.trace.interaction_logger.InteractionLogFolderCreated;
import grader.trace.stepper.ProjectStepperEnded;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.misc.Common;
import util.trace.TraceableBus;

public class AnInteractionLogWriter implements InteractionLogWriter {
	File logFile;
	Logger logger;
	public static final String LOG_FILE_PREFIX = "interactionLog";
	String fileName;
	
	PrintWriter out = null;
    BufferedWriter bufWriter;
    PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    final Class[] staticDoNotLogEventsArray = {
    		InteractionLogEntryAdded.class, 
    		InteractionLogFileCreatedOrLoaded.class, 
    		InteractionLogFolderCreated.class
    };    
    Set<Class> doNotLogEventsSet = Common.arrayToSet(staticDoNotLogEventsArray);
    
    String interactionLogFolder;
	
	
	public AnInteractionLogWriter() {
		long currentTime = System.currentTimeMillis();
		Date currentDate = new Date(currentTime);
//		String monthDay = currentDate.getMonth() + "" + currentDate.getDay();
		String dateString = currentDate.toString();
		String[] parts = dateString.split(" ");
		String suffix = parts[1] + parts[2] + parts[5];
		interactionLogFolder = 
				ConfigurationManagerSelector.getConfigurationManager().
					getStaticConfiguration().getString("grader.logger.interactionLogDirectory"); // + "/" + GradingEnvironment.get().getUserName();
		
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
		if (
				!(aTraceable instanceof CSVSerializable) ||
				doNotLogEventsSet.contains(aTraceable.getClass()))
			return;
		String csvRow = ((CSVSerializable) aTraceable).toCSVRow();
		InteractionLogEntryAdded.newCase(fileName, csvRow, this);
		propertyChangeSupport.firePropertyChange("logAddition", null, csvRow);
//		out.println(((CSVSerializable) aTraceable).toCSVRow());
		out.println(csvRow);

		out.flush();
		if (aTraceable instanceof ProjectStepperEnded) 
			out.close();
		
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	@Override
	public Set<Class> getDoNotLogEventsSet() {
		return doNotLogEventsSet;
	}
	@Override
	public void setDoNotLogEventsSet(Set<Class> doNotLogEventsSet) {
		this.doNotLogEventsSet = doNotLogEventsSet;
	}
//	public void startTracing() {
//		
//	}
	@Override
	public String getInteractionLogFolder() {
		return interactionLogFolder;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public static void main (String[] args) {
		new AnInteractionLogWriter();
	}
	

}
