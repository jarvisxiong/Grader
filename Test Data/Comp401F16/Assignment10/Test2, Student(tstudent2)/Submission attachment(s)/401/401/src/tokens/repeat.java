package tokens;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags ({"Word"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@EditablePropertyNames({"Input"})
@PropertyNames({"Input","Value"})

public class repeat extends Word implements Tokens{

	public repeat(String in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setCall(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefine(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDefine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMove(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProceedAll(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getProceedAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRedo(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRedo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRepeat(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRepeat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRotateLeftArm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRotateLeftArm(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRotateRightArm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRotateRightArm(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSay(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSleep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSleep(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getThread() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setThread(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUndo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUndo(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getWait() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWait(String token) {
		// TODO Auto-generated method stub
		
	}
	
	
	/*
	// Establishing private string editable properties
		private String Input;
		private String Value = "";

		// Establishing constructor
		public repeat(String in){
			setRepeat(in);
			getInput();
			int index = 0;
			char LowercaseLetter;
			Value = "";
			while(index < Input.length()){
				char Letter = Input.charAt(index);
				if(Character.isUpperCase(Letter)){
					LowercaseLetter = Character.toLowerCase(Letter);
					Value += LowercaseLetter;
					index++;
				}else{
					Value += Letter;
					index++;
				}
			}
		}
			
		// Establishing Word token getter that return Value
		public String getRepeat(){
			return Value;
		}
		
		// Establishing Word setter that sets Input
		public void setRepeat(String token){
			Input = token;
		}

		// Establishing Word getter that return Input
		public String getInput(){
			return Input;
		}
		*/
}
