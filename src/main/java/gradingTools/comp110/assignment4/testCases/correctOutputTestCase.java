package gradingTools.comp110.assignment4.testCases;

import java.util.Random;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.Project;

public class correctOutputTestCase extends BasicTestCase {

	private Random rand = new Random();
	String input="5\nCheese\n20\nFava Beans\n50\nBacon\n50\nGerbil Food\n60\nGerbil\n120\n3\nSB329\nHannibal\n0\n50\n0\n0\n120\nTrue\nTrue\nSB2\nMalcom\n20\n25\n30\n20\n2\nFalse\nFalse\nSB1X3\nPrince Firstly\n19\n25\n25\n30\n102\nFalse\nTrue\naverage\nsearch\nSB329\nsearch\nSB129\nrestart\n3\n";

	public correctOutputTestCase() {
		super("correct output test case");
	}
	
	
	/*protected String[] RandomInputwOutput(){ //will generate the entirety of the input
		String randomin=""; 
		int numfooditem=rand.nextInt(5)+1;
		ArrayList <Double> foodnames = null;
		ArrayList <Integer> foodamounts = null;
		randomin=randomin+numfooditem+"\n"; //number of food items
		int maxfood=0;//will use in average calculation
		for(int i=numfooditem;i>0;i--){
			double name=rand.nextDouble()+rand.nextDouble()+rand.nextDouble();	//doesnt matter what names are, so the names of foods and gerbils will be doubles...
			randomin=randomin+name+"\n";	//foodname
			foodnames.add(name);
			int amount=rand.nextInt()+2;
			randomin=randomin+amount+"\n";	//foodamount
			foodamounts.add(amount);
			maxfood+=amount;
		}
		int numgerbils=rand.nextInt(5)+1;
		randomin=randomin+numgerbils+"\n"; //number of gerbils
		ArrayList <Double> gerbilnames = null;	//so am I going in the right direction here? I could go ahead and make random ints that coresspond to ASCII, but am lazy lol
		ArrayList<Double> gerbilid = null;
		ArrayList<ArrayList> gerbilfoods = null;
		ArrayList<Boolean> biteinfo=null;
		ArrayList<Boolean> escapeinfo=null;
		ArrayList<Integer> totaleaten=null;
		for(int i=numgerbils;i>0;i--){
			double labid= rand.nextDouble()+rand.nextDouble()+rand.nextDouble();
			randomin=randomin+labid+"\n";
			gerbilid.add(labid);
			double gname=rand.nextDouble()+rand.nextDouble()+rand.nextDouble();
			randomin=randomin+gname+"\n";
			gerbilnames.add(gname);
			ArrayList<Integer> famount = null;
			int amounteaten=0;
			for(int j=numfooditem;j>0;j--){
				int afood=rand.nextInt(foodamounts.indexOf(j));
				famount.add(afood);
				randomin=randomin+famount+"\n";
				amounteaten+=afood;
			}
			totaleaten.add(amounteaten);
			gerbilfoods.add(famount);
			boolean bite=rand.nextBoolean();
			boolean escape=rand.nextBoolean();
			biteinfo.add(bite);
			escapeinfo.add(escape);
			randomin=randomin+bite+"\n";
			randomin=randomin+escape+"\n";
		}
		randomin=randomin+"average\n";
		randomin=randomin+"search\n";
		int searchfor=rand.nextInt(gerbilid.size()-1);
		randomin=randomin+gerbilid.indexOf(searchfor)+"\n";
		randomin=randomin+"search\n";	//finishing up input, now want to search for non-existent gerbil...
		randomin=randomin+"g\n";
		randomin+="quit\n";
		String[] returnable= new String[5+numgerbils];//returnable[0] is input, the rest will be used for keywords...
		returnable[0]=randomin;	//we now have input
		
		
		//begin generating output keywords
		returnable[1]="How many food";//How many food items do the gerbils eat? used to check repeat.
		int currentload=1;
		for(int i=0;i<numgerbils;i++){
			String exout="";
			int avg=(totaleaten.get(i)/maxfood)*100;//need to adjust this to be in alphabetical order.
		}
	
	}	Going to stop making random input, too much to deal with. Will just run all on one input set;
	*/	
	
	

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		return null;
	}

}
