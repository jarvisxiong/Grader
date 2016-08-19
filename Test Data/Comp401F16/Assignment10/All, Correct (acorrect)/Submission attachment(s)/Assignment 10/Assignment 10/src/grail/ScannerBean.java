package grail;

public interface ScannerBean {
	public void setScannedString(String ScannedString);
	public String getScannedString();
	public void scanner(String input);
	public void setDigit(String input);
	public void setWords(String input);
	public void setQuote(String input);
	public void setPlus(String input);
	public void setMinus(String input);
	public boolean judgeLetter(char letter);
	public void countQuo();
	public void setStartBr(String input);
	public void setEndBr(String input);
	//public void createTokens();
	public Token[] getTokens();
	public TokenError[] getError();
	public void judgeCommand(String input);
}
