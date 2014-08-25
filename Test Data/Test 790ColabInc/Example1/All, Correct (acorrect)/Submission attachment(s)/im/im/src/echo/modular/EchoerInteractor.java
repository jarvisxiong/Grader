package echo.modular;

import echo.monolithic.MonolithicEchoer;

public interface EchoerInteractor extends HistoryObserver {
	public static final String ECHO_INDICATOR = MonolithicEchoer.ECHO_INDICATOR;
	public static final String QUIT = MonolithicEchoer.QUIT;
	public static final String HISTORY = MonolithicEchoer.HISTORY;
	public void doInput() ;
	public  void printHistory() ;
//	String toString(History aHistory);

}
