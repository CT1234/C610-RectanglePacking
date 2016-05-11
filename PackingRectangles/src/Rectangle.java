public class Rectangle {
	
	private int horiz, vert, area;
	
	public int getHoriz() {
		return horiz;
	}

	public int getVert() {
		return vert;
	}
	
	public Integer getVertObj() {
		return new Integer(vert);
	}

	public int getArea() {
		return area;
	}

	public Rectangle(int horiz, int vert) {
		this.horiz = horiz;
		this.vert = vert;
		this.area = horiz * vert;
	}
}