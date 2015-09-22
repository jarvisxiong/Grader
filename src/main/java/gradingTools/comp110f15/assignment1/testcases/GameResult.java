

package gradingTools.comp110f15.assignment1.testcases;


import java.math.BigDecimal;
import java.math.MathContext;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;


public class GameResult extends BasicTestCase {


public GameResult() {

super("Correct Game Result");

// TODO Auto-generated constructor stub

}

//unc wins p1

//dook wins p2

//tie p3


@Override

public TestCaseResult test(Project project, boolean autoGrade)

throws NotAutomatableException, NotGradableException {

int iW=0; //9

int iL =0; //8

int iT = 0; //9

int imath1=0; //5

int imath2=0; //5

int imath3=0; //5
int iprintscore=0; //5
//int iprintscore=5; //5

String message = "";


RunningProject p0=RunningProjectUtils.runProject(project, 10,"");

String out0=p0.await();

int base=out0.length()-1;

RunningProject p1=RunningProjectUtils.runProject(project,10,"1000\n100\n10\n1\n5\n2\n9\n4\n");

String out1=p1.await().substring(base);

if(out1.toLowerCase().contains("unc won the game")){

iW=9;

}

else {

message += "Did not correctly determine that UNC won the game";

}

String[]lines=out1.toLowerCase().split("\n");

int totalcounter=0;

for (String line : lines) {

if(line.contains("total"))totalcounter++;

if(line.contains("1111")){

imath1=5;

}

else {

message += "\nDid not correctly calculate UNC's score";

}

if(line.contains("20")){

imath2 = 5;

}

else {

message += "\nDid not correctly calculate Duke's score";

}

}

if(totalcounter==2){

iprintscore = 5;

}

else {

message += "\nDid not correctly print the total score";

}

if(out1.contains("1091")){

imath3=5;

}

else {

message += "\nDid not correctly calculate the score difference";

}

RunningProject p2=RunningProjectUtils.runProject(project,10,"1\n0\n0\n1\n5\n2\n9\n4\n");

String out2=p2.await().substring(base);

if(out2.toLowerCase().contains("duke won the game")||out2.toLowerCase().contains("dook won the game")){

iL = 8;

}

RunningProject p3=RunningProjectUtils.runProject(project,10,"1\n0\n0\n1\n1\n0\n0\n1\n");

String out3=p3.await().substring(base);

if(out3.toLowerCase().contains("a tie")){

iT = 9;

}

int sum = iW + iL + iT + imath1 + imath2 + imath3 + iprintscore;

if (sum == 45){

return pass();

}

else if (sum > 0){
System.out.println("Double is "+sum/45);
BigDecimal bd= new BigDecimal(sum/45);
bd=bd.round(new MathContext(3));
System.out.println("rounded: "+bd.doubleValue());
return partialPass((sum / 45), message);

}

else {

return fail("Student received no points, TA please verify");

}

}


}

