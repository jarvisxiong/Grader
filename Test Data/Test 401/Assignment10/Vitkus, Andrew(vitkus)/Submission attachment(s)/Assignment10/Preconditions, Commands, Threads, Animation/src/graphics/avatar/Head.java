package graphics.avatar;

import graphics.shapes.AnImageShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@PropertyNames({ "ImageFileName", "X", "Y", "Width", "Height", "AvatarName" })
@EditablePropertyNames({ "ImageFileName", "X", "Y", "Width", "Height",
		"AvatarName" })
@Tags({ "Head" })
public class Head extends AnImageShape implements IHead {
	private AvatarName avatarName;

	public Head(AvatarName avatarName, int x, int y) {
		super(getFileNameForAvatarName(avatarName), x, y,
				getImageHeightForAvatarName(avatarName),
				getImageWidthForAvatarName(avatarName));
		this.avatarName = avatarName;
	}

	@Override
	public AvatarName getAvatarName() {
		return avatarName;
	}

	@Override
	public void setAvatarName(AvatarName newAvatarName) {
		avatarName = newAvatarName;
		setImageFileName(getFileNameForAvatarName(avatarName));
	}

	private static String getFileNameForAvatarName(AvatarName avatarName) {
		switch (avatarName) {
		case ARTHUR:
			return "arthur.jpg";
		case GALAHAD:
			return "galahad.jpg";
		case GUARD:
			return "guard.jpg";
		case LANCELOT:
			return "lancelot.jpg";
		case ROBIN:
			return "robin.jpg";
		default:
			return null;
		}
	}

	private static int getImageHeightForAvatarName(AvatarName avatarName) {
		switch (avatarName) {
		case ARTHUR:
			return (64);
		case GALAHAD:
			return (65);
		case GUARD:
			return (65);
		case LANCELOT:
			return (64);
		case ROBIN:
			return (64);
		default:
			return 0;
		}
	}

	private static int getImageWidthForAvatarName(AvatarName avatarName) {
		switch (avatarName) {
		case ARTHUR:
			return (40);
		case GALAHAD:
			return (38);
		case GUARD:
			return (33);
		case LANCELOT:
			return (37);
		case ROBIN:
			return (43);
		default:
			return 0;
		}
	}

	public String getImageFileName() {
		return super.getImageFileName();
	}

}
