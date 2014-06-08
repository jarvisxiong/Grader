package framework.execution;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

public interface RunnerInputStreamProcessor extends RunnerStreamProcessor{
	public void nextInput(String anInput) ;
	
	public void terminateInput() ;
	
	
	public OutputStream getInput() ;
	
	public OutputStreamWriter getInputWriter();

}
