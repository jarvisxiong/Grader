package windowApp;

import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JOptionPane;

import util.awt.ADelegateFrame;
import util.awt.AnInputQueue;

public class LocalMirror {
	public static void main(String[] args) {
//		if (args.length != 1) {
//			System.out.println("Missing class name argument");
//			System.exit(1);
//		}
		  String className = JOptionPane.showInputDialog(null,
				  "Window app class name?",
				  "Enter class name",
				  JOptionPane.QUESTION_MESSAGE);
		  
		try {
			Class c = Class.forName(className);
			Class[] parameterTypes = {args.getClass()};
			Method mainMethod = c.getMethod("main", parameterTypes);
			Object[] parameters = {args};
			mainMethod.invoke(null, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		AnInputQueue.getEventQueue().clearEventQueuehandlers();
		//LocalCharacterDrawer.main(args);
		List<ADelegateFrame> frames = ADelegateFrame.getAllFrames();
		if (frames.size() != 2) {
			System.out.println("Mirror requires exactly two frames to be created by application");
		}		
		AnInputQueue.getEventQueue().addEventQueueHandler(new ALocalEventMirrorer(frames.get(0), frames.get(1)));

	}	
}
