package gradingTools.comp110.assignment4.testCases;

import java.lang.reflect.Field;
import java.util.ArrayList;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;

public class TestFoodClass extends BasicTestCase {
	
	public TestFoodClass() {
		super("Contains Food class test");
	}
	
	private boolean findField(Class<?> javaClass, String fieldName) {
		try {
			javaClass.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			// No field name
			return false;
		} catch (SecurityException e) {
			// Field exists but not accessible
		}
		return true;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		if (project.getClassesManager().isEmpty()) {
			throw new NotAutomatableException();
		}
		ClassesManager manager = project.getClassesManager().get();

		boolean containsClass = false;
		boolean containsFields = false;
		for (ClassDescription description : manager.getClassDescriptions()) {
			Class<?> javaClass = description.getJavaClass();
			if (javaClass.getName().equalsIgnoreCase("food")) {
				containsClass = true;


				boolean containsName = false ;
				boolean containsColor = false;
				boolean containsMaximumAmount = false;
				int numStrings = 0;
				int numNumbers = 0;
				
				for(Field field : javaClass.getFields()) {
					if(field.getName().toLowerCase().contains("name")) {
						containsName = true;
					} else if(field.getName().toLowerCase().contains("color")) {
						containsColor = true;
					} else if(field.getName().toLowerCase().contains("max")) {
						containsName = true;
					}
					
					Class<?> type = field.getType();
					if (type.equals(String.class)) {
						numStrings++;
					} else if (type.equals(Integer.TYPE) || type.equals(Double.TYPE)) {
						numNumbers ++;
					}
				}
				
				if((!containsColor || !containsName) && numStrings >= 2) {
					containsName = true;
					containsColor = true;
				}
				if (!containsMaximumAmount && (numStrings > 2 || numNumbers > 1)) {
					containsMaximumAmount = true;
				}
				
				if (!containsName) {
					containsName = findField(javaClass, "name");
				}
				
				if (!containsColor) {
					containsColor = findField(javaClass, "color");
				}

				String[] maxAmountNames = {"max", "maximum", "maxAmount", "maximumAmount"};
				if (!containsMaximumAmount) {
					for (String fieldName : maxAmountNames){
						containsMaximumAmount = findField(javaClass, fieldName);
						if (containsMaximumAmount) {
							break;
						}
					}
				}
				containsFields = containsName && containsColor && containsMaximumAmount;
				break;
			}
			
		}
		
		
		if (!containsClass) {
			return fail("No Food class found");
		}
		if (!containsFields) {
			return partialPass(0.5, "Food class does not contain fields for name, color, and maximum amount");
		}
		return pass();
	}
}
