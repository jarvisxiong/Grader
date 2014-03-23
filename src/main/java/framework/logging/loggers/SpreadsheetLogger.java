package framework.logging.loggers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import au.com.bytecode.opencsv.CSVReader;
import framework.grading.ProjectRequirements;
import framework.grading.testing.CheckResult;
import framework.grading.testing.Feature;
import framework.grading.testing.Restriction;
import framework.logging.recorder.RecordingSession;
import framework.utils.GraderSettings;
import framework.utils.GradingEnvironment;
import grader.assignment.GradingFeature;
import grader.modules.ModuleProblemManagerSelector;

/**
 * This maintains a spreadsheet of all students and their grades
 */
public class SpreadsheetLogger implements Logger {

	private static final String SakaiGradesSpreadsheetFilename = "grades.csv";

	private final ProjectRequirements projectRequirements;
	private final File spreadsheetFile;
	private XSSFWorkbook workbook;
	private boolean workbookCreated = false;
//	String logFile;
//	String logFileName;

	public SpreadsheetLogger(ProjectRequirements projectRequirements) throws ConfigurationException {
		this.projectRequirements = projectRequirements;

		// Get the spreadsheet file
		PropertiesConfiguration configuration = new PropertiesConfiguration(
				"./config/config.properties");
		String spreadsheetPath = configuration.getString("grader.logger.spreadsheetFilename")
				.replace("{projectName}", GradingEnvironment.get().getAssignmentName());
		spreadsheetPath = ModuleProblemManagerSelector.getModuleProblemManager().replaceModuleProblemVars(spreadsheetPath);
		spreadsheetFile = new File(spreadsheetPath);

		// Load or create the workbook
		if (spreadsheetFile.exists())
			loadWorkbook();
		else
			createWorkbook();
	}
	
	int resultsBasedSave(XSSFRow row, RecordingSession recordingSession) {
		int rawScore = 0;
		int columnCounter = 7;
		for (CheckResult result : recordingSession.getFeatureResults()) {
			rawScore += result.getScore();
			row.getCell(columnCounter++).setCellValue(result.getScore());
		}
		for (CheckResult result : recordingSession.getRestrictionResults()) {
			rawScore += result.getScore();
			row.getCell(columnCounter++).setCellValue(result.getScore());
		}
		return rawScore;
	}
	
	double featuresBasedSave(XSSFRow row, RecordingSession recordingSession) {
//		int rawScore = 0;
		int columnCounter = 7;
		for (GradingFeature gradingFeature:recordingSession.getGradingFeatures()) {
       	 if (!gradingFeature.isRestriction()) {
//       		rawScore += gradingFeature.getScore();
			row.getCell(columnCounter++).setCellValue(gradingFeature.getScore());       		
       	 }
		}
		for (GradingFeature gradingFeature:recordingSession.getGradingFeatures()) {
	       	 if (gradingFeature.isRestriction()) {
//	       		rawScore += gradingFeature.getScore();
				row.getCell(columnCounter++).setCellValue(gradingFeature.getScore());
	       		
	       	 }
			}
		 
		return recordingSession.getScore();
	}

	@Override
	public void save(RecordingSession recordingSession) {

		if (!workbookCreated) {
			createWorkbook();
		}

		// Get the row and save the feature and restriction results
		XSSFRow row = findRow(recordingSession);
		double rawScore = 0;
		if (recordingSession.getGradingFeatures() == null)
			rawScore = resultsBasedSave(row, recordingSession);
		else
			rawScore = featuresBasedSave(row, recordingSession);
		
		int rowNum = row.getRowNum() + 1;
//		int columnCounter = 7;
//		for (CheckResult result : recordingSession.getFeatureResults()) {
//			rawScore += result.getScore();
//			row.getCell(columnCounter++).setCellValue(result.getScore());
//		}
//		for (CheckResult result : recordingSession.getRestrictionResults()) {
//			rawScore += result.getScore();
//			row.getCell(columnCounter++).setCellValue(result.getScore());
//		}

		// Save the final scores
		row.getCell(4).setCellValue(rawScore);
		row.getCell(5).setCellValue(recordingSession.getLatePenalty());
		row.getCell(6).setCellFormula("E" + rowNum + "*F" + rowNum);

		// Save the workbook to the file
		try {

			if (!spreadsheetFile.getParentFile().exists()) {
				spreadsheetFile.getParentFile().mkdirs();
			}

			FileOutputStream outputStream = new FileOutputStream(spreadsheetFile);
			workbook.write(outputStream);
			outputStream.close();

			// Reload the workbook. We have to do this to avoid issues.
			// See:
			// http://stackoverflow.com/questions/18261152/org-apache-xmlbeans-impl-values-xmlvaluedisconnectedexception-when-write-workboo
			loadWorkbook();
			System.out.println("Spreadsheet grades saved to " + spreadsheetFile.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	private XSSFRow findRow(RecordingSession recordingSession) {

		if (!workbookCreated) {
			createWorkbook();
		}

		String id = recordingSession.getUserId();
		String onyen = id.substring(id.indexOf("(") + 1, id.indexOf(")"));
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			if (row.getCell(0).getStringCellValue().equals(onyen))
				return row;
		}
		return null;
	}

	private void loadWorkbook() {
		try {
			workbook = new XSSFWorkbook(new FileInputStream(spreadsheetFile));
		} catch (Exception e) {
			System.out.println("Error creating spreadsheet. Creating new");
			spreadsheetFile.delete();
			createWorkbook();
		}
	}

	private void createWorkbook() {
		String csvFile = GraderSettings.get().get("path") + "/" + SakaiGradesSpreadsheetFilename;
		if (!new File(csvFile).exists()) {
			return;
		}

		// Create workbook and add the header
		workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("Display ID");
		row.createCell(1).setCellValue("ID");
		row.createCell(2).setCellValue("Last Name");
		row.createCell(3).setCellValue("First Name");
		row.createCell(4).setCellValue("Raw score");
		row.createCell(5).setCellValue("Early/Late modifier");
		row.createCell(6).setCellValue("Final score");
		int columnCounter = 7;
		for (Feature feature : projectRequirements.getFeatures())
			row.createCell(columnCounter++).setCellValue(feature.getName());
		for (Restriction restriction : projectRequirements.getRestrictions())
			row.createCell(columnCounter++).setCellValue(restriction.getName());

		// Load the Sakai grades file to get the list of students
		try {
			CSVReader reader = new CSVReader(new FileReader(csvFile));
			reader.readNext();
			reader.readNext();
			reader.readNext();
			String[] line;
			int rowCounter = 1;
			while ((line = reader.readNext()) != null) {
				row = sheet.createRow(rowCounter++);
				row.createCell(0).setCellValue(line[0]);
				row.createCell(1).setCellValue(line[1]);
				row.createCell(2).setCellValue(line[2]);
				row.createCell(3).setCellValue(line[3]);
				row.createCell(4);
				row.createCell(5);
				row.createCell(6);
				for (int i = 7; i < columnCounter; i++)
					row.createCell(i);
			}
			reader.close();
			workbookCreated = true;

		} catch (FileNotFoundException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	@Override
	public String logFileName(String aUserId) {
		return spreadsheetFile.getAbsolutePath();
	}

	@Override
	//  this is not correct, should really look at specific entry
	public boolean isSaved(String aUserId) {
		return true;
	}

}
