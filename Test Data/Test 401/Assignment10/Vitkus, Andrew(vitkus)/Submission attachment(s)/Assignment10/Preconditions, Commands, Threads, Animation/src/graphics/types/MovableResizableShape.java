package graphics.types;

public class MovableResizableShape implements IMovableResizableShape {
	int x, y, height, width;

	public MovableResizableShape(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	@Override
	public void setX(int newX) {
		x = newX;
	}

	@Override
	public void setY(int newY) {
		y = newY;
	}

	@Override
	public void setHeight(int newHeight) {
		height = newHeight;
	}

	@Override
	public void setWidth(int newWidth) {
		width = newWidth;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

}
