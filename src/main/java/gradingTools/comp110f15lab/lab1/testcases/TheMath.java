package gradingTools.comp110f15lab.lab1.testcases;

import java.util.Scanner;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class TheMath extends BasicTestCase {

	public TheMath() {
		super("correct math?");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject p0=RunningProjectUtils.runProject(project, 10,"");
		String out0=p0.await();
		String[] splice = out0.split("\n");
		int num1,num2;
		String[] target = splice[0].split(" ");
		boolean dot=false;
		if(target[target.length-1].contains("."))dot=true;
		try{
		if(dot)num2=Integer.parseInt(target[target.length-1].substring(0, target[target.length-1].length()-1));
		else num2=Integer.parseInt(target[target.length-1]);
		num1=Integer.parseInt(target[target.length-3]);
		}
		catch(NumberFormatException n){
			return fail("cannot isolate num1 and num2, they did not format as per the instructions. Handgrade the math, and they should be getting points off of the format score");
		}
		double sum =num1+num2;
		double product=num1*num2;
		double quotient=num2/num1;
		double rem=num2%num1;
		RunningProject goo = RunningProjectUtils.runProject(project, 10, "");
		String output=goo.await();
		int numright=0;
		if(output.contains(""+sum)||output.contains(""+(int)sum)){
			numright++;
		}
		if(output.contains(""+product)||output.contains(""+(int)product)){
			numright++;
		}
		if(output.contains(""+quotient)||output.contains(""+(int)quotient)){
			numright++;
		}
		if(output.contains(""+rem)||output.contains(""+(int)rem)){
			numright++;
		}
		if(numright==4)return pass();
		if(numright==0)return fail("none of the math was correct, TA double check to make sure numbers are not present prior to num1 and num2, could have thrown off parsing...");
		else{ 
		double percentage=(double)numright/4;	
		return partialPass(percentage,"one or more of the computed values are incorrect");
		}
	}

}
