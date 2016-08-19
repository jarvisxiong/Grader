package grail.draw.interfaces;

import grail.avatar.Avatar;

public interface Platform extends OvalShape {
	public void setLocation(int x, int y);
	public OvalShape getPlatform();
	public OvalShape getShadow();
	public int getAvatarX(Avatar aAvatar);
	public int getAvatarY(Avatar aAvatar);
	public void move(int x, int y);
}
