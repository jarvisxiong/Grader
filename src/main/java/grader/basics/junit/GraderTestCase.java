package grader.basics.junit;

import framework.grading.testing.TestCase;

public interface GraderTestCase extends TestCase, GradableJUnitTest {
	public void init() ;
	public Class getJUnitClass() ;
	
//	public void setDefaultScore(int aDefaultScore);
//	
//	public int getDefaultScore() ;
//	
//	public void setMaxScore (Class aJUnitClass) ;
//	public void setIsRestriction (Class aJUnitClass) ;
//	public void setIsExtra (Class aJUnitClass) ;
//	
//	public void setExplanation (Class aJUnitClass) ;
//
//	public void setGroup (Class aJUnitClass) ;
//	
//	public void setJUnitClass(Class aJUnitClass) ;
//	public boolean isRestriction() ;
//	public boolean isExtra();
//	public double getMaxScore() ;
//	public String getExplanation() ;
//	String getGroup();

}
