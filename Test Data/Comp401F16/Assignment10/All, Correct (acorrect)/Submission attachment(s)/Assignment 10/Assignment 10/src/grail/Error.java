package grail;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"error"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 

public class Error implements TokenError{
		char error;
		public Error(char input){
			error=input;
		}
		public void setInput(char input){
			error=input;
		}

		public char getInput(){
			return error;
		}
	}


