package main;

import tokens.CallEditable;
import tokens.End;
import tokens.EndInterface;
import tokens.Number;
import tokens.NumberEditable;
import tokens.Quote;
import tokens.QuoteInterface;
import tokens.Start;
import tokens.StartInterface;
import tokens.ThreadEditable;
import tokens.Word;
import tokens.WordEditable;
import tokens.approach;
import tokens.call;
import tokens.define;
import tokens.defineEditable;
import tokens.move;
import tokens.moveEditable;
import tokens.proceedAll;
import tokens.proceedAllEditable;
import tokens.redo;
import tokens.redoEditable;
import tokens.repeat;
import tokens.repeatEditable;
import tokens.rotateLeftArm;
import tokens.rotateLeftArmEditable;
import tokens.rotateRightArm;
import tokens.rotateRightArmEditable;
import tokens.say;
import tokens.sayEditable;
import tokens.sleep;
import tokens.sleepEditable;
import tokens.thread;
import tokens.undo;
import tokens.undoEditable;
import tokens.wait;
import tokens.waitEditable;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;


@Tags({"ScannerBean"})
@PropertyNames({"ScannedString","Tokens"})
@EditablePropertyNames({"ScannedString"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class ScannerBean implements ScannerBeanInterface {
	
	// prints out exit statement for the program
	public static void exit(){
		System.out.println("END");
	}
	
	// initialize private parameter
	private String ScannedString;
	private String[] Tokens;
	private String[] ParsedString;
	private String[] Parse;
	private int quote = 0;
	
	// getter that returns ScannedString
	@Visible(false)
	public String getScannedString(){return ScannedString;}
	@Visible(false)
	public String[] getTokens(){return Tokens;}
	@Visible(false)
	public String[] getParse(){return Parse;}
	
	// setter that takes an input, parses input, and prints out tokens
	public void setScannedString(String input){
		if(input.charAt(0) == '.'){
			// Ends the program if a '.' is the first scanned character
			exit();
		}else{
				// Initialize parameters
				String token = "";
				String[] Large = new String[20];
				String[] ParsedLarge = new String[20];
				int large = 0;
				int compact = 0;
				
				// Loop that scans each character of the inputed string
				for(int i = 0; i < input.length(); i++){
					
					// parameter that indicates the ith character in the input
					char check = input.charAt(i);
					char check2 = input.charAt(i);
					
					// checks when there is a space in between tokens
					if(check == ' '){
						char last = input.charAt(i-1);
						//char next = input.charAt(i+1);
						
						// determines if the last character is a digit
						if(Character.isDigit(last)){
							ScannedString = token;
							Large[large] = "number token";
							ParsedLarge[large] = ScannedString;
							large++;
							// Creates a new instant of the Number object
							NumberEditable n = new Number(ScannedString);
							
							// Prints out parameters
							System.out.println(n);
							token = "";
							
						// determines if the last character is a letter
						}else if(check2 == ' ' && quote == 1){
							token += check2;
						}else if(Character.isLetter(last) && quote == 0){
							
							// Establishing command tokens as Strings
							ScannedString = token;
							
							String app = "approach";
							String def = "define";
							String mov = "move";
							String sa = "say";
							String cal = "call";
							String pro = "proceedall";
							String red = "redo";
							String repe = "repeat";
							String rla = "rotateleftarm";
							String rra = "rotaterightarm";
							String sle = "sleep";
							String thre = "thread";
							String und = "undo";
							String wai = "wait";

							if(def.equalsIgnoreCase(ScannedString)){
								Large[large] = "define token";
								ParsedLarge[large] = ScannedString;
								large++;
								defineEditable d = new define(ScannedString);
								System.out.println(d);
								token = "";
							}else if(mov.equalsIgnoreCase(ScannedString)){
								Large[large] = "move token";
								ParsedLarge[large] = ScannedString;
								large++;
								moveEditable m = new move(ScannedString);
								System.out.println(m);
								token= "";
							}else if(app.equalsIgnoreCase(ScannedString)){
								Large[large] = "approach token";
								ParsedLarge[large] = ScannedString;
								large++;
								approach a = new approach(ScannedString);
								System.out.println(a);
								token= "";
							}else if(sa.equalsIgnoreCase(ScannedString)){
								Large[large] = "say token";
								ParsedLarge[large] = ScannedString;
								large++;
								sayEditable s = new say(ScannedString);
								//System.out.println(s);
								token = "";
							}else if(cal.equalsIgnoreCase(ScannedString)){
								Large[large] = "call token";
								ParsedLarge[large] = ScannedString;
								large++;
								CallEditable c = new call(ScannedString);
								System.out.println(c);
								token = "";
							}else if(pro.equalsIgnoreCase(ScannedString)){
								Large[large] = "proceedAll token";
								ParsedLarge[large] = ScannedString;
								large++;
								proceedAllEditable p = new proceedAll(ScannedString);
								System.out.println(p);
								token = "";
							}else if(red.equalsIgnoreCase(ScannedString)){
								Large[large] = "redo token";
								ParsedLarge[large] = ScannedString;
								large++;
								redoEditable r = new redo(ScannedString);
								System.out.println(r);
								token = "";
							}else if(repe.equalsIgnoreCase(ScannedString)){
								Large[large] = "repeat token";
								ParsedLarge[large] = ScannedString;
								large++;
								repeatEditable re = new repeat(ScannedString);
								System.out.println(re);
								token = "";
							}else if(rla.equalsIgnoreCase(ScannedString)){
								Large[large] = "rotateLeftArm token";
								ParsedLarge[large] = ScannedString;
								large++;
								rotateLeftArmEditable rl = new rotateLeftArm(ScannedString);
								System.out.println(rl);
								token = "";
							}else if(rra.equalsIgnoreCase(ScannedString)){
								Large[large] = "rotateRightArm token";
								ParsedLarge[large] = ScannedString;
								large++;
								rotateRightArmEditable rr = new rotateRightArm(ScannedString);
								System.out.println(rr);
								token = "";
							}else if(sle.equalsIgnoreCase(ScannedString)){
								Large[large] = "sleep token";
								ParsedLarge[large] = ScannedString;
								large++;
								sleepEditable sl = new sleep(ScannedString);
								System.out.println(sl);
								token = "";
							}else if(thre.equalsIgnoreCase(ScannedString)){
								Large[large] = "thread token";
								ParsedLarge[large] = ScannedString;
								large++;
								ThreadEditable th = new thread(ScannedString);
								System.out.println(th);
								token = "";
							}else if(und.equalsIgnoreCase(ScannedString)){
								Large[large] = "undo token";
								ParsedLarge[large] = ScannedString;
								large++;
								undoEditable u = new undo(ScannedString);
								System.out.println(u);
								token = "";
							}else if(wai.equalsIgnoreCase(ScannedString)){
								Large[large] = "wait token";
								ParsedLarge[large] = ScannedString;
								large++;
								waitEditable w = new wait(ScannedString);
								System.out.println(w);
								token = "";
							}else{
								Large[large] = "word token";
								ParsedLarge[large] = ScannedString;
								large++;
								WordEditable wo = new Word(ScannedString);
								System.out.println(wo);
								token = "";
							}
						// determines if the last character is a quote
						}else if(last == '"'){
							quote = 0;
							ScannedString = token;
							Large[large] = "quote token";
							ParsedLarge[large] = ScannedString;
							large++;
							
							QuoteInterface q = new Quote(ScannedString);
							
							// Prints out parameters
							//System.out.println(q);
							token = "";
						}
					
					// determines if the ith character is a digit
					}else if(i+1 == input.length()){
						
						char Last = input.charAt(i);
						
						// determines if the last character is a digit
						if(Character.isDigit(Last)){
							token += Last;
							ScannedString = token;
							Large[large] = "number token";
							ParsedLarge[large] = ScannedString;
							large++;
							// Creates a new instant of the Number object
							NumberEditable n = new Number(ScannedString);
							
							// Prints out parameters
							System.out.println(n);
							token = "";
							
						// determines if the last character is a letter
						}else if(Character.isLetter(Last)){
							
							token += Last;
							// Establishing command tokens as Strings
							ScannedString = token;
							
							String app = "approach";
							String def = "define";
							String mov = "move";
							String sa = "say";
							String cal = "call";
							String pro = "proceedall";
							String red = "redo";
							String repe = "repeat";
							String rla = "rotateleftarm";
							String rra = "rotaterightarm";
							String sle = "sleep";
							String thre = "thread";
							String und = "undo";
							String wai = "wait";

							if(def.equalsIgnoreCase(ScannedString)){
								Large[large] = "define token";
								ParsedLarge[large] = ScannedString;
								large++;
								defineEditable d = new define(ScannedString);
								System.out.println(d);
								token = "";
							}else if(mov.equalsIgnoreCase(ScannedString)){
								Large[large] = "move token";
								ParsedLarge[large] = ScannedString;
								large++;
								moveEditable m = new move(ScannedString);
								System.out.println(m);
								token= "";
							}else if(app.equalsIgnoreCase(ScannedString)){
								Large[large] = "approach token";
								ParsedLarge[large] = ScannedString;
								large++;
								approach a = new approach(ScannedString);
								System.out.println(a);
								token= "";
							}else if(sa.equalsIgnoreCase(ScannedString)){
								Large[large] = "say token";
								ParsedLarge[large] = ScannedString;
								large++;
								sayEditable s = new say(ScannedString);
								//System.out.println(s);
								token = "";
							}else if(cal.equalsIgnoreCase(ScannedString)){
								Large[large] = "call token";
								ParsedLarge[large] = ScannedString;
								large++;
								CallEditable c = new call(ScannedString);
								System.out.println(c);
								token = "";
							}else if(pro.equalsIgnoreCase(ScannedString)){
								Large[large] = "proceedAll token";
								ParsedLarge[large] = ScannedString;
								large++;
								proceedAllEditable p = new proceedAll(ScannedString);
								System.out.println(p);
								token = "";
							}else if(red.equalsIgnoreCase(ScannedString)){
								Large[large] = "redo token";
								ParsedLarge[large] = ScannedString;
								large++;
								redoEditable r = new redo(ScannedString);
								System.out.println(r);
								token = "";
							}else if(repe.equalsIgnoreCase(ScannedString)){
								Large[large] = "repeat token";
								ParsedLarge[large] = ScannedString;
								large++;
								repeatEditable re = new repeat(ScannedString);
								System.out.println(re);
								token = "";
							}else if(rla.equalsIgnoreCase(ScannedString)){
								Large[large] = "rotateLeftArm token";
								ParsedLarge[large] = ScannedString;
								large++;
								rotateLeftArmEditable rl = new rotateLeftArm(ScannedString);
								System.out.println(rl);
								token = "";
							}else if(rra.equalsIgnoreCase(ScannedString)){
								Large[large] = "rotateRightArm token";
								ParsedLarge[large] = ScannedString;
								large++;
								rotateRightArmEditable rr = new rotateRightArm(ScannedString);
								System.out.println(rr);
								token = "";
							}else if(sle.equalsIgnoreCase(ScannedString)){
								Large[large] = "sleep token";
								ParsedLarge[large] = ScannedString;
								large++;
								sleepEditable sl = new sleep(ScannedString);
								System.out.println(sl);
								token = "";
							}else if(thre.equalsIgnoreCase(ScannedString)){
								Large[large] = "thread token";
								ParsedLarge[large] = ScannedString;
								large++;
								ThreadEditable th = new thread(ScannedString);
								System.out.println(th);
								token = "";
							}else if(und.equalsIgnoreCase(ScannedString)){
								Large[large] = "undo token";
								ParsedLarge[large] = ScannedString;
								large++;
								undoEditable u = new undo(ScannedString);
								System.out.println(u);
								token = "";
							}else if(wai.equalsIgnoreCase(ScannedString)){
								Large[large] = "wait token";
								ParsedLarge[large] = ScannedString;
								large++;
								waitEditable w = new wait(ScannedString);
								System.out.println(w);
								token = "";
							}else{
								Large[large] = "word token";
								ParsedLarge[large] = ScannedString;
								large++;
								WordEditable wo = new Word(ScannedString);
								System.out.println(wo);
								token = "";
							}
						// determines if the last character is a quote
						}else if(Last == '"'){
							//token += Last;
							ScannedString = token;
							Large[large] = "quote token";
							ParsedLarge[large] = ScannedString;
							quote = 0;
							large++;
							
							QuoteInterface q = new Quote(ScannedString);
							
							// Prints out parameters
							//System.out.println(q);
							// Create an new instant of the Quote object
							//String quote = q.getQuote();
							
							// Prints out parameters
							//System.out.println(quote);
							token = "";
						}else if(Last == '{'){
							Large[large] = "start token";
							ParsedLarge[large] = ScannedString;
							large++;
							token += check2;
							ScannedString = token;
							
							// Established a new instant Start object 
							StartInterface s = new Start(ScannedString);
							
							// Prints out parameters
							System.out.println(s);
							//String start = s.getStart();
							//System.out.println(start);
							token = "";
						}else if(Last == '}'){
							Large[large] = "end token";
							ParsedLarge[large] = ScannedString;
							large++;
							//System.out.println(token);
							token = "";
							token += check2;
							ScannedString = token;
							
							// Established a new instant Start object 
							EndInterface e = new End(ScannedString);
							
							// Prints out parameters
							System.out.println(e);
							//String end = e.getEnd();
							//System.out.println(end);
							token = "";
						}
					}else if(Character.isDigit(check2)){
							token += check2;
					// determines if the ith character is a letter				
					}else if(Character.isLetter(check2)){
							token += check2;
					}else if(check2 == '"' && quote == 1){
						//token += check2;
						ScannedString = token;
						Large[large] = "quote token";
						ParsedLarge[large] = ScannedString;
						large++;
						quote = 0;
						token = "";
					// determines if the last character is a quotation mark
					}else if(check2 == '"' && quote == 0){
						// do nothing about the q marks but realize that this is the beginning of a quote in scanner
						quote = 1;
						char next = input.charAt(i+1);
						if(next == '"'){
							// Do nothing
						}
					}else if(check2 == '{'){
						Large[large] = "start token";
						ParsedLarge[large] = ScannedString;
						large++;
						token += check2;
						ScannedString = token;
						
						// Established a new instant Start object 
						StartInterface s = new Start(ScannedString);
						
						// Prints out parameters
						System.out.println(s);
						//String start = s.getStart();
						//System.out.println(start);
						token = "";
					}else if(check2 == '}'){
						Large[large] = "end token";
						ParsedLarge[large] = ScannedString;
						large++;
						System.out.println(token);
						token = "";
						token += check2;
						ScannedString = token;
						
						// Established a new instant Start object 
						EndInterface e = new End(ScannedString);
						
						// Prints out parameters
						//System.out.println(e);
						//String end = e.getEnd();
						//System.out.println(end);
						token = "";
					}else{	
							token += check2;
					}
				}
			for(int j = 0; Large[j] != null; j++ ){
				// counts the number of tokens in the Large Array
				compact = j +1;
				}
			// Compact Array in created with indicated size equal to the number of tokens scanned
			String[] Compact = new String[compact];
			ParsedString = new String[compact];
			for(int k = 0; k != compact; k++){
				Compact[k] = Large[k];
				ParsedString[k] = ParsedLarge[k];
			}
			// Tokens Array will then be used for the Command Interpreter
			Tokens = Compact;
			Parse = ParsedString;
			// Printing out the tokens in the arrays
			
			/*
			System.out.print("Large Array: {");
			for(int index = 0; index <= 19; index ++){
				System.out.print(" " + Large[index] +",");
				}
			System.out.print(" }");
			System.out.println();
			System.out.print("Compact Array: {");
			for(int index2 = 0; index2 < compact; index2++){
				System.out.print(" " + Tokens[index2] + ",");
				}
			System.out.print(" }");
			System.out.println();
			System.out.print("Parsed Array: {");
			for(int index3 = 0; index3 < compact; index3++){
				System.out.print(" " + Parse[index3] + ",");
				}
			System.out.print(" }");
			System.out.println();
			
			*/
			}
		}
	}

