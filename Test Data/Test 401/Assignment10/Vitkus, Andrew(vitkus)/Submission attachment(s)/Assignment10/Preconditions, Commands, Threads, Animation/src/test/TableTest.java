package test;

import java.util.ArrayList;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;
import bus.uigen.OEFrame;
import commandEngine.interpreter.ITable;
import commandEngine.interpreter.Table;
import graphics.avatar.IAvatar;
import graphics.view.IBridgeScene;

@PropertyNames({ "AvatarName", "Avatar", "OEFrame" })
@EditablePropertyNames({ "AvatarName", "OEFrame" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({ "Table Test" })
public class TableTest {
	private final IBridgeScene scene;
	private final ITable avatarTable;
	private OEFrame frame;
	private String name;
	private IAvatar avatar;

	public TableTest(IBridgeScene scene) {
		this.scene = scene;
		avatarTable = new Table();
		buildTable();
		setAvatarName("arthur");
	}

	public void runTest() {
		ArrayList<String> names = (ArrayList<String>) avatarTable.getKeys();

		for (String testName : names) {
			// System.out.println(testName);
			setAvatarName(testName);
			frame.refresh();
			sleep(5000);
		}
	}

	public void setOEFrame(OEFrame frame) {
		this.frame = frame;
		frame.setSize(800, 800);
	}

	@Visible(false)
	public OEFrame getOEFrame() {
		return frame;
	}

	public void setAvatarName(String name) {
		this.name = name;
		avatar = (IAvatar) avatarTable.get(name);
	}

	public String getAvatarName() {
		return name;
	}

	public IAvatar getAvatar() {
		return avatar;
	}

	private void buildTable() {
		if (avatarTable.isEmpty()) {
			avatarTable.put("arthur", scene.getArthur());
			avatarTable.put("lancelot", scene.getLancelot());
			avatarTable.put("robin", scene.getRobin());
			avatarTable.put("galahad", scene.getGalahad());
			avatarTable.put("guard", scene.getGuard());
		}
	}

	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
