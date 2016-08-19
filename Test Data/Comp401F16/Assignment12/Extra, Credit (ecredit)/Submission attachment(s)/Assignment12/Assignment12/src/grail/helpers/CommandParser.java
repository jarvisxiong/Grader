package grail.helpers;

import grail.exceptions.ParsingException;

public interface CommandParser {
	public void setCommandText(String text);
	public Runnable getCommandObject();
	public String getErrors();
	public Runnable parseCommand() throws ParsingException;
	public Runnable parseSay() throws ParsingException;
	public Runnable parseMove() throws ParsingException;
	public Runnable parseApproach() throws ParsingException;
	public Runnable parsePass();
	public Runnable parseFail();
	public Runnable parseCommandList() throws ParsingException;
	public Runnable parseRepeat() throws ParsingException;
	public int numberParser() throws ParsingException;
	public Runnable parseLeftArm() throws ParsingException;
	public Runnable parseRightArm() throws ParsingException;
	public Runnable parseSleep() throws ParsingException;
	public Runnable parseDefine() throws ParsingException;
	public Runnable parseCall() throws ParsingException;
	public Runnable parseThread() throws ParsingException;
	public Runnable parseProceedAll();
	public Runnable parsePrint();
}
