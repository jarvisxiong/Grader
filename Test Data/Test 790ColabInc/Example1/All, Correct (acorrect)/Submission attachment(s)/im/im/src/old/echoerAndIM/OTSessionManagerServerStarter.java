package old.echoerAndIM;
import util.session.ASessionManager;
public class OTSessionManagerServerStarter {
	static ASessionManager server;
	public static void main (String[] args) {
		server = new ASessionManager();	
		server.register();
	}
}
