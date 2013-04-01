package model;


import java.util.ArrayList;
import java.util.List;

public class Coordinate extends java.awt.Point {
	private static final long serialVersionUID = 1L;
	
	public Coordinate(int x, int y) {
		super(x, y);
	}

	
	public List<Coordinate> getNeighbours() {
		List<Coordinate> resp = new ArrayList<Coordinate>();
			resp.add(new Coordinate(this.x, this.y-1));
			resp.add(new Coordinate(this.x-1, this.y));
			resp.add(new Coordinate(this.x+1,this.y));
			resp.add(new Coordinate(this.x, this.y+1));
		return resp;
	}

}
