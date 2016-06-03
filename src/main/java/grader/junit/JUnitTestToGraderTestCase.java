package grader.junit;

import util.annotations.Explanation;
import util.annotations.Group;
import util.annotations.IsExtra;
import util.annotations.IsRestriction;
import util.annotations.MaxValue;
import framework.grading.testing.TestCase;

public interface JUnitTestToGraderTestCase extends TestCase {
	public void init() ;
	public Class getJUnitClass() ;
	
	public void setDefaultScore(int aDefaultScore);
	
	public int getDefaultScore() ;
	
	public void setMaxScore (Class aJUnitClass) ;
	public void setIsRestriction (Class aJUnitClass) ;
	public void setIsExtra (Class aJUnitClass) ;
	
	public void setExplanation (Class aJUnitClass) ;

	public void setGroup (Class aJUnitClass) ;
	
	public void setJUnitClass(Class aJUnitClass) ;
	public boolean isRestriction() ;
	public boolean isExtra();
	public long getMaxScore() ;
	public String getExplanation() ;
	String getGroup();

}
