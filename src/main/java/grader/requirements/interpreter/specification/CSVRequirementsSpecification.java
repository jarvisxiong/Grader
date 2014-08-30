package grader.requirements.interpreter.specification;

import grader.file.FileProxy;

import java.util.List;

public interface CSVRequirementsSpecification {
	public FileProxy getSpecificationSpreadsheet() ;

	public String getFileName() ;

	public boolean isValid();

	public void setValid(boolean newValue) ;

	public List<String[]> getTable();

	int getNumberOfRequirements();

	String getArg(int aRequirementNum, int anArgNum);
	public String getType(int aFeatureNum);
	
	public String getDescription(int aRequirementNum);
	
	public Double getMaxScore(int aRequirementNum) ;

	
	public String getInput(int aRequirementNum) ;
	public String getModelOutput(int aRequirementNum) ;
	public String getComparator(int aRequirementNum);

	Integer getTimeOut(int aRequirementNum);

}
