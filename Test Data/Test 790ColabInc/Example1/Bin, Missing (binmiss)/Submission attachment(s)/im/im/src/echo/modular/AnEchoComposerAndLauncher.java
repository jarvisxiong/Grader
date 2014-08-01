package echo.modular;

import trace.echo.modular.EchoTracerSetter;
import util.annotations.Tags;
import util.tags.ApplicationTags;
import util.tags.InteractiveTags;
import util.trace.Tracer;


@Tags({ApplicationTags.ECHOER, InteractiveTags.COMPOSER})
public class AnEchoComposerAndLauncher implements EchoerComposerAndLauncher{
	protected History<String> history;	
	protected EchoerInteractor interactor;	
	public void composeAndLaunch(String[] args) {		
		compose(args);
		launch();
		
	}
	public void launch() {
		interactor.doInput();
	}
	// factory method
	protected History<String> createHistory() {
		return new AHistory();
	}
	// factory method
	protected EchoerInteractor createInteractor() {
		return new AnEchoInteractor(history);
	}	
	public void compose(String[] args) {
		history = createHistory();
//		interactor = createInteractor();
//		history.addObserver(interactor);
		connectModelInteractor();
	}
	protected void connectModelInteractor() {
		interactor = createInteractor();
		history.addObserver(interactor);
	}
	public History<String> getHistory() {
		return history;
	}
	public EchoerInteractor getInteractor() {
		return interactor;
	}
	public static void main (String[] args) {
//		System.out.println("setting traces");
//		Tracer.showInfo(true);
		EchoTracerSetter.traceEchoer();
		(new AnEchoComposerAndLauncher()).composeAndLaunch(args);		
	}
}
