package gradingTools.shared.testcases;



public class EqualsAvoidNullTestCase extends CheckStyleTestCase {
	 public EqualsAvoidNullTestCase() {
	        super(null, "Equal avoids null test case");
	    }
    
	@Override
	public String regexLineFilter() {
		return "(.*)String literal expressions should be on the left side(.*)";
	}



	@Override
	public String failMessageSpecifier() {
		// TODO Auto-generated method stub
		return "Literal should be target rather than argument of equals(Ignorecase)";
	}
  //String literal expressions should be on the left side

}

