package graphics.avatar;

import util.annotations.Tags;
import graphics.shapes.IImageShape;

@Tags({ "Head" })
public interface IHead extends IImageShape {
	enum AvatarName {
		ARTHUR, LANCELOT, ROBIN, GALAHAD, GUARD
	};

	public String getImageFileName();

	public AvatarName getAvatarName();

	public void setAvatarName(AvatarName newAvatarName);
}
