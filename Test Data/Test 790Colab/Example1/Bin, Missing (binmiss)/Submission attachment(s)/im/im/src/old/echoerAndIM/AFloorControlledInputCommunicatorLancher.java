package old.echoerAndIM;
import util.session.Communicator;
import util.session.ReceivedMessageListener;
import bus.uigen.ObjectEditor;
public class AFloorControlledInputCommunicatorLancher extends AnIMComposerAndLauncher {	
	static String APPLICATION_NAME = "Controlled Input Communicator";
	
	public static void main (String[] args) {
//		if (args.length < 3) {
//			Message.fatalError("Please supply server host name and user name as main argument");			
//		}
//		//MessageSender multicastClient = new AMulticastClient(args);
//		MessageSenderFactory messageSenderFactory = AMessageSenderFactorySelector.getMessageSenderFactory();
//		MessageSender multicastClient = messageSenderFactory.getMessageSender(args[0],args[1],args[2], APPLICATION_NAME);
//		ControlledInputCommunicator inputBroadcaster =  new AControlledInputBroadcaster(multicastClient);
//		FloorController floorController = new AFloorController(multicastClient);
//		ReceivedMessageListener inputReceiver = new AnInputAndFloorControlMessageReceiver(multicastClient, floorController);
//		multicastClient.addReceivedMessageListener(inputReceiver);
//		inputBroadcaster.addVetoer(floorController);
//		ObjectEditor.edit(floorController);
//		inputBroadcaster.doInput();		
		Communicator messageSender = createCommunicator(args, APPLICATION_NAME);		
		ControlledInputCommunicator inputCommunicator =  new AControlledInputCommunicator(messageSender);
		FloorController floorController = new AFloorController(messageSender);
		AccessController accessController = new AnAccessController(messageSender);
		ReceivedMessageListener inputReceiver = new AnInputAndFloorControlMessageReceiver(messageSender, accessController, floorController);
		messageSender.addReceivedMessageListener(inputReceiver);
		messageSender.join();
		inputCommunicator.addVetoer(accessController);
		inputCommunicator.addVetoer(floorController);
		ObjectEditor.edit(floorController);
		ObjectEditor.edit(accessController);
		inputCommunicator.doInput();		
	}
}
