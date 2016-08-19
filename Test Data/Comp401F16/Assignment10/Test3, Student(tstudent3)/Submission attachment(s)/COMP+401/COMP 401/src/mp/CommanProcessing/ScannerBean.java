package mp.CommanProcessing;

import grail.CharacterAnimation.ProceedAll;

import java.lang.reflect.Array;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"ScannerBean"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"UserInput" , "CompactArray" , /*"ErrorArray" ,*/ "ScannedString"})
@EditablePropertyNames({"UserInput"})

public class ScannerBean implements ScannerBeanInterface {
	final int arraySize = 100;
	String scannedString = "";
	int universalCounter = 0;
	String userInput = "";
	DefaultInterface[] oversizeArray = new DefaultInterface[arraySize];
	//String[] errorArray = new String[100];
	
	public void tokenFinder (String userInput)
	{
		int index = 0;
		int tokenStart = 0;
		int tokenEnd = 0;
		int quoteCounter = 0;
		boolean working = true;
		universalCounter = 0;
		int errorCounter = 0;
		
		for (int i = 0; i < oversizeArray.length; i++)
		{
			oversizeArray[i] = null;
		}
		
		/*for (int i = 0; i < errorArray.length; i++)
		{
			errorArray[i] = null;
		}*/

		for (int i=0; i < userInput.length(); i++)
	    {
	        if (userInput.charAt(i) == '"')
	        {
	             quoteCounter++;
	        }
	    }
		
		if (quoteCounter % 2 == 1)
		{
			System.out.println("Error: Missing quotation, string improperly parsed.");
		}
		
		
		while (index <= userInput.length() -1)
		{
			String wordSub;
			if (Character.isDigit(userInput.charAt(index)) && index < userInput.length()  ) 
			{
				tokenStart = index;
				tokenEnd = tokenStart;

				while (working) 
				{
					if (tokenEnd <= userInput.length() - 1 && Character.isDigit(userInput.charAt(tokenEnd)))
					{
						tokenEnd++;
						index = tokenEnd;
					}
					else
					{
						working = false;
					}
				}
				Number numToken = new Number();
				numToken.setInput(userInput.substring(tokenStart, tokenEnd));
				oversizeArray[universalCounter] =  numToken;
				universalCounter++;
			}
						
			else if (userInput.charAt(index) == '"' && index < userInput.length() )
			{
				tokenStart = index + 1;
				tokenEnd = tokenStart;
				index++;

				{
					while (!(userInput.charAt(index) == '"') && tokenEnd < userInput.length() - 1)
					{
						tokenEnd++;
						index = tokenEnd;
					}
				}
				QuotedString quoteToken = new QuotedString();
				quoteToken.setInput(userInput.substring(tokenStart, tokenEnd));
				oversizeArray[universalCounter] =  quoteToken;
				universalCounter++;
			}
			else if (letterCheck(userInput.charAt(index)) && index < userInput.length() )
			{
				tokenStart = index;
				tokenEnd = tokenStart;

				while (working) 
				{
					if (tokenEnd <= userInput.length() - 1 && letterCheck(userInput.charAt(tokenEnd)))
					{
						tokenEnd++;
						index = tokenEnd;
					}
					else
					{
						working = false;	
					}
				}
				wordSub = userInput.substring(tokenStart, tokenEnd);
				if (wordSub.equalsIgnoreCase("move"))
				{
					WordInterface moveCommand = new Move();
					moveCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  moveCommand;
				}
				else if (wordSub.equalsIgnoreCase("say"))
				{
					WordInterface sayCommand = new Say();
					sayCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  sayCommand;
				}
				else if (wordSub.equalsIgnoreCase("rotateleftarm"))
				{
					WordInterface rotateLeftArmCommand = new RotateLeftArm();
					rotateLeftArmCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  rotateLeftArmCommand;
				}
				else if (wordSub.equalsIgnoreCase("rotaterightarm"))
				{
					WordInterface rotateRightArmCommand = new RotateRightArm();
					rotateRightArmCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  rotateRightArmCommand;
				}
				else if (wordSub.equalsIgnoreCase("repeat"))
				{
					WordInterface repeatCommand = new Repeat();
					repeatCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  repeatCommand;
				}
				else if (wordSub.equalsIgnoreCase("define"))
				{
					WordInterface defineCommand = new Define();
					defineCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  defineCommand;
				}
				else if (wordSub.equalsIgnoreCase("call"))
				{
					WordInterface callCommand = new Call();
					callCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  callCommand;
				}
				else if (wordSub.equalsIgnoreCase("thread"))
				{
					WordInterface threadCommand = new Thread();
					threadCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  threadCommand;
				}
				else if (wordSub.equalsIgnoreCase("wait"))
				{
					WordInterface waitCommand = new Wait();
					waitCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  waitCommand;
				}
				else if (wordSub.equalsIgnoreCase("proceedall"))
				{
					WordInterface proceedAllCommand = new ProceedAll();
					proceedAllCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  proceedAllCommand;
				}
				else if (wordSub.equalsIgnoreCase("sleep"))
				{
					WordInterface sleepCommand = new Sleep();
					sleepCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  sleepCommand;
				}
				else if (wordSub.equalsIgnoreCase("undo"))
				{
					WordInterface undoCommand = new Undo();
					undoCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  undoCommand;
				}
				else if (wordSub.equalsIgnoreCase("redo"))
				{
					WordInterface redoCommand = new Redo();
					redoCommand.setInput(wordSub);
					oversizeArray[universalCounter] =  redoCommand;
				}	
				else if (wordSub.equalsIgnoreCase("approach"))
				{
					DefaultInterface approachCommand = new Approach();
					approachCommand.setInput(wordSub);
					oversizeArray[universalCounter] = approachCommand;
				}		
				else
				{
					WordInterface wordToken = new Word();
					wordToken.setInput(wordSub);
					oversizeArray[universalCounter] =  wordToken;
				}
				universalCounter++;
			}
			else if ( userInput.charAt(index) == '+' && index <= userInput.length() )
			{
				//printer("sign: "+ userInput.charAt(index));
				SignInterface plusToken = new Plus();
				plusToken.setInput(userInput.substring(index , index + 1 ));				
				index++;
				oversizeArray[universalCounter] =  plusToken;
				universalCounter++;
			}
			
			else if (userInput.charAt(index) == '-' && index <= userInput.length() )
			{
				//printer("sign: "+ userInput.charAt(index));
				SignInterface minusToken = new Minus();
				minusToken.setInput(userInput.substring(index , index + 1 ));				
				index++;
				oversizeArray[universalCounter] =  minusToken;
				universalCounter++;
			}
			
			else if (userInput.charAt(index) == '{' && index <= userInput.length() )
			{
				SignInterface startToken = new Start();
				startToken.setInput(userInput.substring(index , index + 1 ));
				index++;
				oversizeArray[universalCounter] =  startToken;
				universalCounter++;
			}
			
			else if (userInput.charAt(index) == '}' && index <= userInput.length() )
			{
				//printer("End Token: "+ userInput.charAt(index));
				SignInterface endToken = new End();
				endToken.setInput(userInput.substring(index , index + 1 ));	
				index++;
				oversizeArray[universalCounter] =  endToken;
				universalCounter++;
			}
			
			
			else if ((userInput.charAt(index) == ' '))
			{
				while ((userInput.charAt(index + 1) == ' ') && (index < userInput.length()))
				{
					index++;
				}
			}
			else
			{
				//errorArray[index] = userInput.substring(index, index + 1);
			}
			working = true;
			index++;
		}

		//System.out.println("Input processing completed.");

	} 
	
	public void setUserInput(String rawString)
	{
		userInput = rawString;
		tokenFinder(userInput);
	}
	
	public String getScannedString()
	{
		return scannedString;
	}

	private static void printer (String output)
	{
		System.out.println(output);
	}

	public boolean letterCheck (char input)
	{
		final int mod65 = 65;
		final int mod90 = 90;
		final int mod97 = 97;
		final int mod122 = 122;
		
		
		int ascii = (int) input;
		return (ascii>=mod65 && ascii<=mod90) || (ascii>=mod97 && ascii<=mod122);
	}

	public DefaultInterface[] getCompactArray()
	{
		DefaultInterface[] compactArray = new DefaultInterface[universalCounter];
		for ( int i = 0; i < universalCounter; i++){
			compactArray[i] = oversizeArray[i];
		}
		return compactArray;
	}

	public String getUserInput()
	{
		return userInput;
	}
	/*public String[] getErrorArray()
	{
		return errorArray;
	}*/
}