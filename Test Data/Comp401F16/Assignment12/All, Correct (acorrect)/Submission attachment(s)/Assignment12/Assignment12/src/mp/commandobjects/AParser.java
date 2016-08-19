package mp.commandobjects;

import main.AScannerBean;
import main.ASingletonsCreator;
import main.BridgeScene;
import main.ScannerBean;
import mp.commands.ApproachCommand;
import mp.commands.FailCommand;
import mp.commands.MoveCommand;
import mp.commands.PassCommand;
import mp.commands.RepeatCommand;
import mp.commands.SayCommand;
import mp.helpers.ATable;
import mp.helpers.Table;
import mp.shapes.Avatar;
import mp.tokens.EndToken;
import mp.tokens.MinusToken;
import mp.tokens.NumberToken;
import mp.tokens.QuoteToken;
import mp.tokens.StartToken;
import mp.tokens.TokenHistory;
import mp.tokens.WordToken;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"Parser"})
@PropertyNames({"CommandObject"})
@EditablePropertyNames({"CommandText"})
public class AParser implements Parser{
	private BridgeScene scene;
	private TokenHistory tokens;
	private int index;
	private ScannerBean scannerBean;
	
	private String commandText;
	private Runnable commandObject;
	private Table<String, Avatar> table;
	
	public AParser(BridgeScene scene){
		this.scene = scene;
		this.scannerBean = new AScannerBean();
		table = ASingletonsCreator.getAvatarTable();
	}
	
	@Override
	public Runnable parseCommand() {
		if(index > 0) {index++;}else if(index < 0){index = -index;}
		if(tokens.get(index) instanceof MoveCommand){
			return parseMoveCommand();
		}else if(tokens.get(index) instanceof SayCommand){
			return parseSayCommand();
		}else if(tokens.get(index) instanceof ApproachCommand){
			return parseApproachCommand();
		}else if(tokens.get(index) instanceof FailCommand){
			return parseFailCommand();
		}else if(tokens.get(index) instanceof PassCommand){
			return parsePassCommand();
		}else if(tokens.get(index) instanceof RepeatCommand){
			return parseRepeatCommand();
		}else if(tokens.get(index) instanceof StartToken){
			return parseCommandList();
		}else{
			return null;
		}
	}

	@Override
	public SayCommandObject parseSayCommand(){
		index++;
		if(tokens.get(index) instanceof QuoteToken){
			return new SayCommandObject(scene, tokens.get(index).getInput());
		}else{
			return null;
		}
	}

	@Override
	public MoveCommandObject parseMoveCommand() {
		index++;
		String avatarKey = ((WordToken) tokens.get(index)).getValue();
		int xOffset = parseNumber();
		int yOffset = parseNumber();
		return new MoveCommandObject((Avatar) table.get(avatarKey), xOffset, yOffset);
	}

	@Override
	public ApproachCommandObject parseApproachCommand() {
		if(tokens.get(index+1) instanceof WordToken){
			index += 1;
			return new ApproachCommandObject(scene, (Avatar) table.get(tokens.get(index).getInput()));
		}else{
			return null;
		}
	}

	@Override
	public PassCommandObject parsePassCommand() {
		return new PassCommandObject(scene);
	}

	@Override
	public FailCommandObject parseFailCommand() {
		return new FailCommandObject(scene);
	}

	@Override
	public CommandList parseCommandList() {
		index++;
		CommandList list = new ACommandList();
		int i = 0;
		while(!(tokens.get(index) instanceof EndToken)){
			if(i == 0){index = -index;}i++;
			list.add(parseCommand());
		}
		return list;
	}

	@Override
	public RepeatCommandObject parseRepeatCommand() {
		index++;
		int indexNow = index;
		if(tokens.get(indexNow) instanceof NumberToken){
			return new RepeatCommandObject(parseCommand(), ((NumberToken) tokens.get(indexNow)).getValue());
		}else{
			return null;
		}
	}

	@Override
	public void setCommandText(String commandText) {
		index = 0;
		this.commandText = commandText;
		scannerBean.setScannedString(commandText);
		tokens = scannerBean.getTokenList();
		commandObject = parseCommand();
	}

	@Override
	public Runnable getCommandObject() {
		return commandObject;
	}

	@Tags({"numberParser"})
	@Override
	public int parseNumber() {
		index++;
		int number = 0;
		if(tokens.get(index) instanceof NumberToken){
			number = ((NumberToken) tokens.get(index)).getValue();
		}else{
			number = ((NumberToken) tokens.get(index+1)).getValue();
			if(tokens.get(index) instanceof MinusToken){
				number = -number;
			}
			index++;
		}
	
		return number;
	}

}
