package grail;


import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.ComponentWidth;
@Tags({"ScannerBean"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 

public class ScannerBeanImpl implements ScannerBean {
	int i;
	int end;
	int quo;
	int arrayI;
	int errorI;
	final int arrayCapacity=50;
	String scannedString="";
	Token[] bigTokens=new Token[arrayCapacity];
	Token[] smallTokens;
	TokenError[] bigError=new TokenError[arrayCapacity];
	TokenError[] smallError;
	boolean period;
	
//	public ScannerBeanImpl(){
//		
//	}
	
	public void setScannedString(String s){
		scannedString=s;
	}
	final int component=500;	
	@ComponentWidth(component)
	
	
	public String getScannedString(){
		return scannedString;
	}
		
	public void judge(String input){
		if(input.charAt(0)=='.'){
			period=true;
		}
	}
	
	
	public void scanner(String input){
		period=false;
		input=scannedString;
		arrayI=0;
		errorI=0;
		bigTokens= new Token[arrayCapacity];
		bigError=new TokenError[arrayCapacity];
		i=0;
		end=0;
		quo=0;
		while(i<input.length()){
			judge(input);
			if(!period){
				System.out.println("Please input again.");
				break;
			}
			else if(input.charAt(end)==' '){
				i++;
				end++;
			}
			else if(Character.isDigit(input.charAt(end))){
				setDigit(input);
				arrayI++;
			}
			else if(judgeLetter(input.charAt(end))){
				setWords(input);
				arrayI++;
			}
			else if(input.charAt(end)=='+'){
				setPlus(input);
				arrayI++;
			}
			else if(input.charAt(end)=='-'){
				setMinus(input);
				arrayI++;
			}
			else if(input.charAt(end)=='{'){
				setStartBr(input);
				arrayI++;
			}
			else if(input.charAt(end)=='}'){
				setEndBr(input);
				arrayI++;
			}
			else if(input.charAt(end)=='"'){
				end++;
				quo++;
				setQuote(input);
				arrayI++;
			}
			else{
				TokenError token= new Error(input.charAt(end));
				bigError[errorI]=token;
				errorI++;
				end++;
				i++;
			}
			
		}
		
		smallTokens= new Token[arrayI];
		for(int i=0;i<arrayI;i++){
			smallTokens[i]=bigTokens[i];
		}
		smallError= new TokenError[errorI];
		for(int i=0;i<errorI;i++){
			smallError[i]=bigError[i];
		}
		
		if(quo%2==1){//detecting missing quote
			System.out.println("Error: missing quotation mark.");
		}
	}//end of setToken
	
	public Token[] getTokens(){
		return smallTokens;
	}
	

	public void setDigit(String input){// Scanning digit. 
			
			while(Character.isDigit(scannedString.charAt(end)) && end!=(input.length()-1)){
				end++;
		}
			if(end==(input.length()-1)&& input.charAt(input.length()-1)!=' '){
				Token token= new NumberTokenImpl(input.substring(i));
				
				bigTokens[arrayI]=token;
				i=end+1;
			}
			else{
				Token token= new NumberTokenImpl(input.substring(i,end));
				
				bigTokens[arrayI]=token;
				i=end;
			}
	}
	
	
	public void setWords(String input){// scan words
			
		while(judgeLetter(scannedString.charAt(end)) && end!=(input.length()-1)){
			end++;
	}
		if(end==(input.length()-1)&& input.charAt(input.length()-1)!=' '){
			Token token= new WordTokenImpl(input.substring(i));
			if(isCommand(token.getInput())){
			judgeCommand(token.getInput());
			}
			else{
			bigTokens[arrayI]=token;
			}
			i=end+1;
			
		}
		else{
			Token token= new WordTokenImpl(input.substring(i,end));
			if(isCommand(token.getInput())){
				judgeCommand(token.getInput());
				}
				else{
				bigTokens[arrayI]=token;
				}
			i=end;
		}
	}
	

	public void judgeCommand(String input){// for telling commands
		
		if( "move".equalsIgnoreCase(input)){
			Token command= new Move(input);
			bigTokens[arrayI]=command;
		}
		else if("approach".equalsIgnoreCase(input)){
			Token command= new Approach(input);
			bigTokens[arrayI]=command;
		}
		else if("say".equalsIgnoreCase(input)){
			Token command= new Say(input);
			bigTokens[arrayI]=command;
		}
		else if("rotateleftarm".equalsIgnoreCase(input)){
			Token command= new RotateLeftArm(input);
			bigTokens[arrayI]=command;
		}
		else if("rotaterightarm".equalsIgnoreCase(input)){
			Token command= new RotateRightArm(input);
			bigTokens[arrayI]=command;
		}
		else if("repeat".equalsIgnoreCase(input)){
			Token command= new Repeat(input);
			bigTokens[arrayI]=command;
		}
		else if("define".equalsIgnoreCase(input)){
			Token command= new Define(input);
			bigTokens[arrayI]=command;
		}
		else if("call".equalsIgnoreCase(input)){
			Token command= new Call(input);
			bigTokens[arrayI]=command;
		}
		else if("thread".equalsIgnoreCase(input)){
			Token command= new Thread(input);
			bigTokens[arrayI]=command;
		}
		else if("wait".equalsIgnoreCase(input)){
			Token command= new Wait(input);
			bigTokens[arrayI]=command;
		}
		else if("proceedall".equalsIgnoreCase(input)){
			Token command= new ProceedAll(input);
			bigTokens[arrayI]=command;
		}
		else if("sleep".equalsIgnoreCase(input)){
			Token command= new Sleep(input);
			bigTokens[arrayI]=command;
		}
		else if("undo".equalsIgnoreCase(input)){
			Token command= new Undo(input);
			bigTokens[arrayI]=command;
		}
		else if("redo".equalsIgnoreCase(input)){
			Token command= new Redo(input);
			bigTokens[arrayI]=command;
		}
	}
	
	public boolean isCommand(String input){
		if("move".equalsIgnoreCase(input)||"redo".equalsIgnoreCase(input)||"undo".equalsIgnoreCase(input)||"sleep".equalsIgnoreCase(input)
				||"proceedall".equalsIgnoreCase(input)||"wait".equalsIgnoreCase(input)||"thread".equalsIgnoreCase(input)
				||"call".equalsIgnoreCase(input)||"define".equalsIgnoreCase(input)||"repeat".equalsIgnoreCase(input)
				||"rotaterightarm".equalsIgnoreCase(input)||"rotateleftarm".equalsIgnoreCase(input)||"approach".equalsIgnoreCase(input)
				||"say".equalsIgnoreCase(input)){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public void setQuote(String input){// scan quoted words
		while(input.charAt(end)!='"' && end!=(input.length()-1)){
				end++;
				countQuo();
		}
		Token token=new QuoteTokenImpl(input.substring(i+1,end));
		bigTokens[arrayI]=token;
		end++;
		i=end;
		
	}
	
	public void setPlus(String input){// scanning plus signs
		while(input.charAt(end)=='+' && end!=(input.length()-1)){
			end++;
			countQuo();
		}
		if(end==input.length()-1&& input.charAt(end)=='+'){
			Token token=new PlusTokenImpl(input.substring(i));
			i=end+1;
			bigTokens[arrayI]=token;
		}
		else{
			Token token=new PlusTokenImpl(input.substring(i,end));
			i=end;
			bigTokens[arrayI]=token;
		}
	}
	
	public void setMinus(String input){// scanning Minus signs
		while(input.charAt(end)=='-' && end!=(input.length()-1)){
			end++;
			countQuo();
		}
		if(end==input.length()-1&& input.charAt(end)=='+'){
			Token token=new MinusTokenImpl(input.substring(i));
			i=end+1;
			bigTokens[arrayI]=token;
		}
		else{
			Token token=new MinusTokenImpl(input.substring(i,end));
			i=end;
			bigTokens[arrayI]=token;
		}
	}
	
	public void setStartBr(String input){//scanning start brace
		while(input.charAt(end)=='{'&& end!=(input.length()-1)){
			end++;
		}		
			if(end==input.length()-1&& input.charAt(end)=='{'){
				Token token=new StartTokenImpl(input.substring(i));
				i=end+1;
				bigTokens[arrayI]=token;
			}
			else{
				Token token=new StartTokenImpl(input.substring(i,end));
				i=end;
				bigTokens[arrayI]=token;
			}			
	}
	
	public void setEndBr(String input){//scanning end brace
		while(input.charAt(end)=='}'&& end!=(input.length()-1)){
			end++;
		}
			
			if(end==input.length()-1&& input.charAt(end)=='}'){
				Token token=new StartTokenImpl(input.substring(i));
				i=end+1;
				bigTokens[arrayI]=token;
			}
			else{
				Token token=new StartTokenImpl(input.substring(i,end));
				i=end;
				bigTokens[arrayI]=token;
			}	
	}

	public TokenError[] getError(){
		return smallError;
	}
	
	
	
	
	public boolean judgeLetter(char letter){//Create a method that functions as Character.isLetter
		return (letter>='A'&&letter<='Z')||(letter>='a'&&letter<='z');
	}
	
	public void countQuo(){//this is for counting the amount of quotation marks, so that I can detect missing marks
		if(scannedString.charAt(end)=='"'){
			quo++;
		}
	}
}
