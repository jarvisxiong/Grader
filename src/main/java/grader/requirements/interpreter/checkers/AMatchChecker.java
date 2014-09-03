package grader.requirements.interpreter.checkers;

import java.io.File;
import java.io.IOException;

import tools.DirectoryUtils;
import util.misc.Common;
import grader.requirements.interpreter.specification.CSVRequirementsSpecification;
import grader.trace.CSVSerializable;

public class AMatchChecker implements InterpretedChecker{
	
	public int getNumArgs() {
		return 2;
	}	
	
	@Override
	public InterpretedCheckerResult check(String[] anArgs) {
		try {
			
			boolean aResult = anArgs[0].matches(anArgs[1]);
			String aNotes = "";
			if (!aResult) {
				aNotes =  anArgs[0] + " does not match " + anArgs[1];
			} else 
				aNotes = anArgs[0] + " matches " + anArgs[1];
					
			return new ACheckerResult(aNotes, aResult);

		} catch (Exception e) {
			e.printStackTrace();
			return new ACheckerResult(e.getMessage(), false);
		}

	}

	public static void main (String[] args) {
		String s = "Please input an integer\nPlease input a decimal\nThe int addition:3\nThe double addition:3.500000\nThe int multiplication:2\nThe double multiplication:2.500000\n";
		s = " \n true ";
		System.out.println(s.matches("[\\s\\S]*true.*"));

		System.out.println(s.matches(".*Please.*"));
	}
	

}
