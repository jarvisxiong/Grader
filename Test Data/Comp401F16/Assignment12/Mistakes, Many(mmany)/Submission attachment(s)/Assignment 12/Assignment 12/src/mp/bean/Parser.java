package mp.bean;

import java.beans.PropertyChangeListener;

import mp.bean.commands.ACommandList;
import mp.bean.commands.ApproachCommand;
import mp.bean.commands.CallCommand;
import mp.bean.commands.CommandList;
import mp.bean.commands.DefineCommand;
import mp.bean.commands.FailCommand;
import mp.bean.commands.MoveCommand;
import mp.bean.commands.PassCommand;
import mp.bean.commands.ProceedAllCommand;
import mp.bean.commands.RepeatCommand;
import mp.bean.commands.RotateLeftArmCommand;
import mp.bean.commands.RotateRightArmCommand;
import mp.bean.commands.SayCommand;
import mp.bean.commands.SleepCommand;
import mp.bean.commands.ThreadCommand;
import mp.tokens.ANumToken;
import mp.tokens.AQuoteToken;
import mp.tokens.AValueToken;
import mp.tokens.AWordToken;
import mp.tokens.ApproachToken;
import mp.tokens.EndToken;
import mp.tokens.FailToken;
import mp.tokens.MoveToken;
import mp.tokens.NumValueInt;
import mp.tokens.PassToken;
import mp.tokens.RepeatToken;
import mp.tokens.SayToken;
import mp.tokens.StartToken;
import mp.ui.Avatar;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

public interface Parser extends PropertyListenerRegisterer {

	void setCommandText(String commandText);

	String getErrors();
	
	Runnable getCommandObject();
	public Runnable parseCommand();
	public Runnable parseSay();
	public Runnable parseMove();
	public Runnable parseApproach();
	public Runnable parsePass();
	public Runnable parseFail();
	public CommandList parseCommandList();
	public Runnable parseRepeat();
	public int parseNumber();
	public Runnable parseRotateLeftArm();
	public Runnable parseRotateRightArm();
	public Runnable parseSleep();
	public Runnable parseDefine();
	public Runnable parseCall();
	public Runnable parseThread();
	public Runnable parseProceedAll();

}
