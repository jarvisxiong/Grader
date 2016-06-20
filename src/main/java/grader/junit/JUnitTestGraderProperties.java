package grader.junit;


public interface JUnitTestGraderProperties  {
	public void init() ;
	public Class getJUnitClass() ;
	
	public void setDefaultScore(int aDefaultScore);
	
	public int getDefaultScore() ;
	
	public void setMaxScore (Class aJUnitClass) ;
	public void setMaxScore (double aMaxScore);
	public void setIsRestriction (Class aJUnitClass) ;
	public void setIsExtra (Class aJUnitClass) ;
	
	public void setExplanation (Class aJUnitClass) ;

	public void setGroup (Class aJUnitClass) ;
	
	public void setJUnitClass(Class aJUnitClass) ;
	public boolean isRestriction() ;
	public boolean isExtra();
	public Double getMaxScore() ;
	public String getExplanation() ;
	String getGroup();
	public void setGroup(String newVal);
	void setRestriction(boolean newVal);
	void setExtra(boolean newVal);

}
