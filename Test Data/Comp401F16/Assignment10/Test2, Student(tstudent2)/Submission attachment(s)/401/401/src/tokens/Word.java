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

public class Word implements Tokens, WordEditable {
	
	// Establishing private string editable properties
	private String Input;
	private String Value = "";

	// Establishing constructor
	public Word(String in){
		setWord(in);
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
	public String getWord(){
		return Value;
	}
	
	// Establishing Word setter that sets Input
	public void setWord(String token){
		Input = token;
	}

	// Establishing Word getter that return Input
	public String getInput(){
		return Input;
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

	@Override
	public void setApproach(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getApproach() {
		// TODO Auto-generated method stub
		return null;
	}
}
