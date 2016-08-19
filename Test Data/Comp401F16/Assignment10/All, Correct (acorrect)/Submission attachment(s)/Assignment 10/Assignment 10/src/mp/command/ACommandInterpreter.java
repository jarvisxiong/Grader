package mp.command;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import grail.MinusTokenImpl;
import grail.Move;
import grail.NumberTokenImpl;
import grail.PlusTokenImpl;
import grail.QuoteTokenImpl;
import grail.Say;
import grail.ScannerBean;
import grail.TokenIntValue;
import mp.APropertyListenerSupport;
import mp.PropertyListenerSupport;
import mp.Scene.Avatar;
import mp.Scene.BridgeScene;
import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

//@Tags({"ObservableCommandInterpreter"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"table","take","roll","Command","Error"})
@EditablePropertyNames({"Command"})
public class ACommandInterpreter implements CommandInterpreter{
	BridgeScene take;
	ScannerBean roll;
	Table aTable= new ATable();
	String command="";
	private String error;
	Animator ani;
	
	final int troi=3;
	final int liubai=600;
	final int four=4;
	final int five=5;
	final int yibai=100;
	
	PropertyListenerSupport aListener= new APropertyListenerSupport();
	
	public ACommandInterpreter(BridgeScene theTake, ScannerBean theRoll){
		take=theTake;
		roll=theRoll;
		error="";
		aTable.put("Graud", take.getGuard());
		aTable.put("Arthur", take.getArthur());
		aTable.put("Galahad", take.getGalahad());
		aTable.put("Lancelot", take.getLancelot());
		aTable.put("Robin", take.getRobin());
		ani=new AnAnimator();
	}
	
	@Visible(false)
	public Table getTable(){
		return aTable;
	}
	@Visible(false)
	public BridgeScene getTake(){
		return take;
	}
	@Visible(false) 
	public ScannerBean getRoll(){
		return roll;
	}
	
	@Tags({"parseSay"})
	public Runnable parseSay(){
		String text=(roll.getTokens()[1]).getInput();
		SayCommand saySM= new ASayCommand(take, text);
		return saySM;
	}
	@Tags({"parseMove"})
	public Runnable parseMove(){
		Avatar xMan= ((Avatar)aTable.get((roll.getTokens()[1]).getInput()));
		MoveCommand moveSM=null;
		if(roll.getTokens()[2] instanceof NumberTokenImpl ){
			if(roll.getTokens()[troi] instanceof NumberTokenImpl){
				 moveSM=new AMoveCommand(xMan,((TokenIntValue)roll.getTokens()[2]).getValue(),
						((TokenIntValue)roll.getTokens()[troi]).getValue());
				}
			else if(roll.getTokens()[troi] instanceof PlusTokenImpl){
				 moveSM=new AMoveCommand(xMan,((TokenIntValue)roll.getTokens()[2]).getValue(),
						((TokenIntValue)roll.getTokens()[four]).getValue());
			}
			else if(roll.getTokens()[troi] instanceof MinusTokenImpl){
				 moveSM=new AMoveCommand(xMan,((TokenIntValue)roll.getTokens()[2]).getValue(),
						-((TokenIntValue)roll.getTokens()[four]).getValue());
			}
		}
		if(roll.getTokens()[2] instanceof PlusTokenImpl ){
			if(roll.getTokens()[four] instanceof NumberTokenImpl){
				 moveSM=new AMoveCommand(xMan,((TokenIntValue)roll.getTokens()[troi]).getValue(),
						((TokenIntValue)roll.getTokens()[four]).getValue());
			}
			else if(roll.getTokens()[four] instanceof PlusTokenImpl){
				 moveSM=new AMoveCommand(xMan,((TokenIntValue)roll.getTokens()[troi]).getValue(),
						((TokenIntValue)roll.getTokens()[five]).getValue());
			}
			else if(roll.getTokens()[four] instanceof MinusTokenImpl){
				 moveSM=new AMoveCommand(xMan,((TokenIntValue)roll.getTokens()[troi]).getValue(),
						-((TokenIntValue)roll.getTokens()[five]).getValue());
					}
		}
		if(roll.getTokens()[2] instanceof MinusTokenImpl ){
			if(roll.getTokens()[four] instanceof NumberTokenImpl){
				 moveSM=new AMoveCommand(xMan,-((TokenIntValue)roll.getTokens()[troi]).getValue(),
						((TokenIntValue)roll.getTokens()[four]).getValue());
					}
			else if(roll.getTokens()[four] instanceof PlusTokenImpl){
				 moveSM=new AMoveCommand(xMan,-((TokenIntValue)roll.getTokens()[troi]).getValue(),
						((TokenIntValue)roll.getTokens()[five]).getValue());
					}
			else if(roll.getTokens()[four] instanceof MinusTokenImpl){
				 moveSM=new AMoveCommand(xMan,-((TokenIntValue)roll.getTokens()[troi]).getValue(),
						-((TokenIntValue)roll.getTokens()[five]).getValue());
					}
		}
		
		return moveSM;
	}
	
	
	
	@Tags({"SignedMove"})
	public void setCommand(String input){
		error="";
		String oldVal=error;
		command=input;
		roll.setScannedString(input);
		roll.scanner(input);
		if(roll.getTokens()[0] instanceof Say && roll.getTokens()[1] instanceof QuoteTokenImpl){
			parseSay().run();
		}
		else if(roll.getTokens()[0] instanceof Move && (roll.getTokens()[1].getInput().equalsIgnoreCase("Arthur")
				|| roll.getTokens()[1].getInput().equalsIgnoreCase("Robin")
				|| roll.getTokens()[1].getInput().equalsIgnoreCase("Guard")
				|| roll.getTokens()[1].getInput().equalsIgnoreCase("Galahad")
				|| roll.getTokens()[1].getInput().equalsIgnoreCase("Lancelot"))){
			parseMove().run();
		}
			
		else{
			if(roll.getTokens()[0] instanceof Say&& !((roll.getTokens()[1]) instanceof QuoteTokenImpl)){
				error=(roll.getTokens()[1]).getInput()+" is not a quoted string. Please input a quoted string after \"say\"";
			}
			else if(roll.getTokens()[0] instanceof Move&&( !((roll.getTokens()[2]) instanceof NumberTokenImpl))						&& (!(roll.getTokens()[2] instanceof PlusTokenImpl)) && (!(roll.getTokens()[2] instanceof MinusTokenImpl))){
					error=(roll.getTokens()[2]).getInput()+" is not an integer. Input an integer.";
			}
			else if(roll.getTokens()[0] instanceof Move&&( !((roll.getTokens()[troi]) instanceof NumberTokenImpl))
						&&!(roll.getTokens()[troi] instanceof PlusTokenImpl) && !(roll.getTokens()[troi] instanceof MinusTokenImpl)){
					 error=(roll.getTokens()[troi]).getInput()+" is not an integer. Input an integer.";
			}

			else if(roll.getTokens()[0] instanceof Move&&!(((roll.getTokens()[1]).getInput().equalsIgnoreCase("Arthur"))
						||((roll.getTokens()[1]).getInput().equalsIgnoreCase("Guard"))
						||((roll.getTokens()[1]).getInput().equalsIgnoreCase("Robin"))
						||((roll.getTokens()[1]).getInput().equalsIgnoreCase("Galahad"))
						||((roll.getTokens()[1]).getInput().equalsIgnoreCase("Lancelot"))
						)){
					 error=(roll.getTokens()[1]).getInput()+" is not a name. Input one knight's name";
				}
			else if((roll.getTokens()[0]!=null&&!(roll.getTokens()[0] instanceof Say) && !((roll.getTokens()[0]) instanceof Move))){
					 error="Illegal Command Detected. Please start with say or move";
			}
			else{
					 error="";
				}
		}
		String newVal=error;
		aListener.notifyAllListeners(new PropertyChangeEvent(this, "Error",oldVal, newVal));
	}
	
	@Tags({"asynchronousArthur"})
	public void asyncArthur(){
		
		AnimatingCommand aniC=new AnAnimatingCommand(ani,take.getArthur(),yibai,yibai);
		Thread thread= new Thread(aniC);
		thread.start();
	}
	@Tags({"asynchronousGalahad"})
	public void asyncGalahad(){
		
		AnimatingCommand aniC=new AnAnimatingCommand(ani,take.getGalahad(),yibai,yibai);
		Thread thread= new Thread(aniC);
		thread.start();
	}
	@Tags({"asynchronousLancelot"})
	public void asyncLancelot(){
		
		AnimatingCommand aniC=new AnAnimatingCommand(ani,take.getLancelot(),yibai,yibai);
		Thread thread= new Thread(aniC);
		thread.start();
	}
	@Tags({"asynchronousRobin"})
	public void asyncRobin(){
		
		AnimatingCommand aniC=new AnAnimatingCommand(ani,take.getRobin(),yibai,yibai);
		Thread thread= new Thread(aniC);
		thread.start();
	}
	@Tags({"AsynchronousGuard"})
	public void asyncGuard(){
		AnimatingCommand aniC=new TwoAnimatingCommand(ani,take.getGuard());
		Thread thread= new Thread(aniC);
		thread.start();
	}


	@Tags({"ErrorResilient"})
	@ComponentWidth(liubai)
	public String getError(){ 
		return error;
	}

	public String getCommand(){
		return command;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		aListener.add(arg0);
	}
	
}
