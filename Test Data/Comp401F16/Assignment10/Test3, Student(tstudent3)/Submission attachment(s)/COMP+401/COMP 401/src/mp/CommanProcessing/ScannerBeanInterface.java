package mp.CommanProcessing;


public interface ScannerBeanInterface {

	public DefaultInterface[] getCompactArray();
	public void tokenFinder (String userInput);
	public void setUserInput(String rawString);
	public String getScannedString();
	public String getUserInput();
}
