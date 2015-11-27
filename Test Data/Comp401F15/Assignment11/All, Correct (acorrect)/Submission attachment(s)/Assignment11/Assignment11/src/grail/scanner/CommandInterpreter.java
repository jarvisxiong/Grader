package grail.scanner;

import java.lang.reflect.Array;




import mp.animator.ADemoAnimator;
import mp.animator.AGuardDemoAnimator;
import mp.animator.DemoAnimator;
import mp.animator.GuardDemoAnimator;
import mp.commands.ACommandList;
import mp.commands.ApproachCommand;
import mp.commands.FailCommand;
import mp.commands.PassCommand;
import mp.commands.RepeatCommand;
import mp.commands.DemoCommand;
import mp.commands.GuardDemoCommand;
import mp.commands.ErrorCommand;
//import commands.ErrorCommand;
import mp.commands.MoveCommand;
import mp.commands.SayCommand;
import mp.tokens.Approach;
import mp.tokens.EndToken;
import mp.tokens.Fail;
import mp.tokens.Minus;
import mp.tokens.Move;
import mp.tokens.Number;
import mp.tokens.Pass;
import mp.tokens.Plus;
import mp.tokens.QuotedString;
import mp.tokens.RawInput;
import mp.tokens.Repeat;
import mp.tokens.Say;
import mp.tokens.StartToken;
import mp.tokens.Word;
import grail.shape.Avatar;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;
import grail.ds.Table;
import grail.ds.TableInterface;
import grail.shape.AvatarInterface;
import grail.shape.BridgeScene;
import grail.shape.BridgeSceneInterface;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"command","errorCommand"})
@EditablePropertyNames({"command"})
@Tags({"Command Interpreter","SignedMove","ErrorResilient"})
public class CommandInterpreter implements CommandInterpreterInterface{
	private TableInterface table = new Table();
	private BridgeSceneInterface briScene;
	private ScannerInterface beanScanner;
	private String errorCommand="";
	private String command="";
	private int currIndex=0;
	
	
	//Asynchronous animators
	DemoAnimator arthurAnimator = new ADemoAnimator();
	DemoAnimator lancelotAnimator = new ADemoAnimator();
	DemoAnimator robinAnimator = new ADemoAnimator();
	DemoAnimator galahadAnimator = new ADemoAnimator();
	GuardDemoAnimator guardAnimator = new AGuardDemoAnimator();
	CommandParser parser=null;
	public CommandInterpreter(BridgeSceneInterface bsi, ScannerInterface si)
	{
		briScene=bsi;
		beanScanner=si;
		parser=new ACommandParser(bsi,si);
		table.put("arthur",briScene.getArthur());
		table.put("lancelot",briScene.getLancelot());
		table.put("robin",briScene.getRobin());
		table.put("galahad",briScene.getGalahad());
		table.put("guard",briScene.getGuard());
	}
	
	public CommandInterpreter()
	{
		
	}
	@Visible (false)
	public ScannerInterface getBeanScanner()
	{
		return this.beanScanner;
	}
	
	@Visible (false)
	public BridgeSceneInterface getBriScene()
	{
		return this.briScene;
	}
	
	public void setCommand(String str)
	{
		this.command=str;
		currIndex=0;
		this.errorCommand="";
		//parseTokens(str);//this is old style to parse command which parse and respond the the commands
		
		//the following are new style to parse the commands, and then respond to the commands by another way
		//currIndex=0;
		//this.beanScanner.setScannedString(str);
		//parseAndRunCommand();
		parser.setCommandText(this.command);
		this.errorCommand=parser.getErrors();
		Runnable cmdObject=parser.getCommandObject();
	}
	
	/*
	public void parseAndRunCommand() {
		currIndex = 0;
		Runnable theCommand;
		theCommand = parseCommand();
		Thread commandThread = new Thread(theCommand);
		commandThread.start();
	}
	@Tags("Command Parser")
	Runnable parseCommand(){
		Runnable theCommand;
		if (this.beanScanner.getCompactTokenArray()[currIndex] instanceof Say){
			theCommand = parseSayCommand();
		}
		else if (this.beanScanner.getCompactTokenArray()[currIndex] instanceof Move){
			theCommand = parseMoveCommand();
		}
		else if (this.beanScanner.getCompactTokenArray()[currIndex] instanceof Approach){
			theCommand = parseApproachCommand();
		}
		else if (this.beanScanner.getCompactTokenArray()[currIndex] instanceof Pass){
			theCommand = parsePassCommand();
		}
		else if (this.beanScanner.getCompactTokenArray()[currIndex] instanceof Fail){
			theCommand = parseFailCommand();
		}
		else if (this.beanScanner.getCompactTokenArray()[currIndex] instanceof StartToken){
			theCommand = parseCommandList();
		}
		else if (this.beanScanner.getCompactTokenArray()[currIndex] instanceof Repeat){
			theCommand = parseRepeatCommand();
		}
		else
			theCommand = new ErrorCommand(this.briScene);
		return theCommand;
	}
	
	
	//this method can only need to parse the line, and no need to make response for the commands,
	//the response action should be done by SayCommand.run/execute
	@Tags("parseSay")
	Runnable parseSayCommand(){
		if (Array.getLength(this.beanScanner.getCompactTokenArray()) - currIndex >= 2 &&
				this.beanScanner.getCompactTokenArray()[currIndex + 1] instanceof QuotedString) {
			String speech = this.beanScanner.getCompactTokenArray()[currIndex + 1].getInput();
			currIndex += 2; //Increment by number of tokens in command for parsing command list 
			return new SayCommand(this.briScene, speech);
		}
		else
		{	
			currIndex += 2;//Increment by number of tokens in command for parsing command list
			return new ErrorCommand(this.briScene);
		}
	}
	
	//this method can only need to parse the line, and no need to make response for the commands,
	//the response action should be done by MoveCommand.run/execute
	//Also ,I'd like to parse + -.
	@Tags("parseMove")
	Runnable parseMoveCommand()
	{
//		if (Array.getLength(this.beanScanner.getCompactTokenArray()) - currIndex >= 4 
//				&& this.beanScanner.getCompactTokenArray()[currIndex + 1] instanceof Word
//				&& this.beanScanner.getCompactTokenArray()[currIndex + 2] instanceof Number
//				&& this.beanScanner.getCompactTokenArray()[currIndex + 3] instanceof Number) 
//		{
//			AvatarInterface anAvatar = (AvatarInterface)table.get(((Word) (this.beanScanner
//					.getCompactTokenArray()[currIndex + 1])).getInput().toLowerCase());
//			int aChangeInX = ((Number) this.beanScanner.getCompactTokenArray()[currIndex + 2]).getValue();
//			int aChangeInY = ((Number) this.beanScanner.getCompactTokenArray()[currIndex + 3]).getValue();
//			currIndex += 4;//Increment by number of tokens in command for parsing command list
//			return new MoveCommand(anAvatar, aChangeInX, aChangeInY);
//		} else
//		{	
//			currIndex += 4;//Increment by number of tokens in command for parsing command list
//			return new ErrorCommand(this.briScene);
//		}
//		
		
		
		Runnable res=null;
		//another way of parsing move methods
		RawInput[] tokens=this.beanScanner.getCompactTokenArray();//get the tokens

		//currIndex=0;
	    if (tokens[currIndex] instanceof Move)
		{
			currIndex+=1;
			if(tokens[currIndex] instanceof Word)
			{
				AvatarInterface currAvatar=(AvatarInterface)table.get(tokens[currIndex].getInput().toLowerCase());
				if (currAvatar!=null)
				{
					currIndex+=1;
					//go on to read
					int firstSign=1;
					if (tokens[currIndex] instanceof Number)
					{
						firstSign=1;
						parse2Numbers(currAvatar, tokens, firstSign);
					}
					else if(tokens[currIndex] instanceof Plus)
					{
						currIndex+=1;
						firstSign=1;

						if (tokens[currIndex] instanceof Number)
						{
							res=parse2NumbersRunnable(currAvatar, tokens, firstSign);
						}
						else
						{
							//System.out.println("command not following the format");
							//this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is Number";
							res=new ErrorCommand(this.briScene);
						}
					}
					else if (tokens[currIndex] instanceof Minus)
					{
						currIndex+=1;
						firstSign=-1;
						if (tokens[currIndex] instanceof Number)
						{
							res=parse2NumbersRunnable(currAvatar, tokens, firstSign);
						}
						else
						{
							//System.out.println("command not following the format");
							//this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is Number";
							res=new ErrorCommand(this.briScene);
						}
					}
					else 
					{
						//System.out.println("command not following the format");
						//this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is Number or Sign";
						res=new ErrorCommand(this.briScene);
					}
				}
				else
				{
					//System.out.println("command not following the format");
					//this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is one of the five Avatar names";
					res=new ErrorCommand(this.briScene);
				}
			}
		}
	    return res;
	}
	
	@Tags("parseApproach")
	Runnable parseApproachCommand() {
		if (Array.getLength(this.beanScanner.getCompactTokenArray()) - currIndex >= 2
				&& this.beanScanner.getCompactTokenArray()[currIndex + 1] instanceof Word) {
			AvatarInterface anAvatar = (AvatarInterface) table.get(((Word) (this.beanScanner.getCompactTokenArray()
					[currIndex + 1])).getInput().toLowerCase());
			currIndex += 2;//Increment by number of tokens in command for parsing command list
			return new ApproachCommand(this.briScene, anAvatar);
		} else
		{	
			currIndex +=2;//Increment by number of tokens in command for parsing command list
			return new ErrorCommand(this.briScene);
		}
	}
	@Tags("parsePass")
	Runnable parsePassCommand(){
		currIndex++;//Increment by number of tokens in command for parsing command list
		return new PassCommand(this.briScene);
	}
	@Tags("parseFail")
	Runnable parseFailCommand(){
		currIndex++;//Increment by number of tokens in command for parsing command list
		return new FailCommand(this.briScene);
	}
	
	@Tags("parseCommandList")
	Runnable parseCommandList(){
		ACommandList theCommandList = new ACommandList();
		currIndex++;//Move to first token after start token
		while (currIndex < Array.getLength(this.beanScanner.getCompactTokenArray())
				&& this.beanScanner.getCompactTokenArray()[currIndex] instanceof EndToken == false) {
			theCommandList.add(parseCommand());//Adds commands until an end token is found
		}
		return theCommandList;
	}
	
	@Tags("parseRepeat")
	Runnable parseRepeatCommand(){
		int repetitions;
		Runnable theCommand;
		if (Array.getLength(this.beanScanner.getCompactTokenArray()) - currIndex >= 2
				&& this.beanScanner.getCompactTokenArray()[currIndex + 1] instanceof Number) {
			repetitions = ((Number) this.beanScanner.getCompactTokenArray()[currIndex + 1]).getValue();
			currIndex += 2; //Increment by number of tokens in command for parsing command list
			theCommand = parseCommand();
			return new RepeatCommand(repetitions, theCommand);
		}
		else
			return new ErrorCommand(this.briScene);
	}
	
	
	private void parseTokens(String line)
	{
		this.beanScanner.setScannedString(line);
		RawInput[] tokens=this.beanScanner.getCompactTokenArray();//get the tokens

		currIndex=0;
		if (tokens[currIndex] instanceof Say)
		{
			currIndex+=1;
			if (tokens[currIndex] instanceof QuotedString)
			{
				this.briScene.sayScene(tokens[currIndex].getInput());//not have to indicate which to say
			}else
			{
				System.out.println("command not following the format");
				this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is QuotedString";
			}
		}else if (tokens[currIndex] instanceof Move)
		{
			currIndex+=1;
			if(tokens[currIndex] instanceof Word)
			{
				AvatarInterface currAvatar=(AvatarInterface)table.get(tokens[currIndex].getInput().toLowerCase());
				if (currAvatar!=null)
				{
					currIndex+=1;
					//go on to read
					int firstSign=1;
					if (tokens[currIndex] instanceof Number)
					{
						firstSign=1;
						parse2Numbers(currAvatar, tokens, firstSign);
					}
					else if(tokens[currIndex] instanceof Plus)
					{
						currIndex+=1;
						firstSign=1;

						if (tokens[currIndex] instanceof Number)
						{
							parse2Numbers(currAvatar, tokens, firstSign);
						}
						else
						{
							System.out.println("command not following the format");
							this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is Number";
						}
					}
					else if (tokens[currIndex] instanceof Minus)
					{
						currIndex+=1;
						firstSign=-1;
						if (tokens[currIndex] instanceof Number)
						{
							parse2Numbers(currAvatar, tokens, firstSign);
						}
						else
						{
							System.out.println("command not following the format");
							this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is Number";
						}
					}
					else 
					{
						System.out.println("command not following the format");
						this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is Number or Sign";
					}
				}
				else
				{
					System.out.println("command not following the format");
					this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is one of the five Avatar names";
				}
			}
		}
		
	}
	
	//parse the two numbers following move commands, we are supposed to have processed the first Number(including the +/-),
	//and the current index goes to this first number
	private void parse2Numbers(AvatarInterface currAvatar, RawInput[] tokens, int firstSign)
	{

		int num1=Integer.parseInt(tokens[currIndex].getInput());
		num1*=firstSign;
		int num2=0;
		currIndex+=1;
		if (tokens[currIndex] instanceof Number)
		{
			num2=Integer.parseInt(tokens[currIndex].getInput());
			currAvatar.move(num1,num2);
		}else if(tokens[currIndex] instanceof Plus)
		{
			currIndex+=1;
			if (tokens[currIndex] instanceof Number)
			{
				currAvatar.move(num1,Integer.parseInt(tokens[currIndex].getInput()));
			}
			else
			{
				System.out.println("command not following the format");
				this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is Number";
			}
		}else if(tokens[currIndex] instanceof Minus)
		{
			currIndex+=1;
			if (tokens[currIndex] instanceof Number)
			{
				num2=Integer.parseInt(tokens[currIndex].getInput());
				num2=-1*num2;
				currAvatar.move(num1,num2);
			}
			else
			{
				System.out.println("command not following the format");
				this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is Number";
			}
		}
			
	
	}
	
	//parse2Number and return a Runnable interface
	private Runnable parse2NumbersRunnable(AvatarInterface currAvatar, RawInput[] tokens, int firstSign)
	{

		Runnable res=null;
		int num1=Integer.parseInt(tokens[currIndex].getInput());
		num1*=firstSign;
		int num2=0;
		currIndex+=1;
		if (tokens[currIndex] instanceof Number)
		{
			num2=Integer.parseInt(tokens[currIndex].getInput());
			//currAvatar.move(num1,num2);
			res=new MoveCommand(currAvatar,num1,num2);
		}else if(tokens[currIndex] instanceof Plus)
		{
			currIndex+=1;
			if (tokens[currIndex] instanceof Number)
			{
				//currAvatar.move(num1,Integer.parseInt(tokens[currIndex].getInput()));
				num2=Integer.parseInt(tokens[currIndex].getInput());
				res=new MoveCommand(currAvatar,num1,num2);

			}
			else
			{
				//System.out.println("command not following the format");
				//this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is Number";
				res=new ErrorCommand(this.briScene);
			}
		}else if(tokens[currIndex] instanceof Minus)
		{
			currIndex+=1;
			if (tokens[currIndex] instanceof Number)
			{
				num2=Integer.parseInt(tokens[currIndex].getInput());
				num2=-1*num2;
				//currAvatar.move(num1,num2);
				res=new MoveCommand(currAvatar,num1,num2);
			}
			else
			{
				//System.out.println("command not following the format");
				//this.errorCommand="unexpected command: "+tokens[currIndex]+"\t and the expected one is Number";
				res=new ErrorCommand(this.briScene);
			}
			
		}
			
		return res;
	}
	*/
	final int moveX=100,moveY=100;
	@Tags("asynchronous Arthur")
	public void asynchronousArthur() {
		Thread thread = new Thread(new DemoCommand(this.briScene.getArthur(), moveX, moveY,  arthurAnimator));
		thread.start();
	}

	@Tags("asynchronousRobin")
	public void asynchronousRobin() {
		Thread thread = new Thread(new DemoCommand(this.briScene.getRobin(), moveX, moveY,  robinAnimator));
		thread.start();
	}

	@Tags("asynchronousLancelot")
	public void asynchronousLancelot() {
		Thread thread = new Thread(new DemoCommand(this.briScene.getLancelot(), moveX, moveY,  lancelotAnimator));
		thread.start();
	}

	@Tags("asynchronousGalahad")
	public void asynchronousGalahad() {
		Thread thread = new Thread(new DemoCommand(this.briScene.getGalahad(), moveX, moveY,  galahadAnimator));
		thread.start();
	}
	@Tags("asynchronousGuard")
	public void asynchronousGuard() {
		Thread thread = new Thread(new GuardDemoCommand(this.briScene.getGuard(), 20, guardAnimator));
		thread.start();
	}
	
	public String getErrorCommand()
	{
		return this.errorCommand;
	}
	public String getCommand()
	{
		return this.command;
	}
}