package edu.rit.se.sse.rapdevx.clientmodels;

import java.awt.Point;
import java.util.ArrayList;

public class Path {
	
	private ArrayList<Point> path;
	
	public Path(Point start) {
		
		this.path = new ArrayList<Point>();
		path.add(start);
		
	}
	
	public void addPoint(Point point) {
		
		this.path.add(point);
		
	}
	
	public Point getLastPoint() {
		
		return this.path.get(path.size()-1);
		
	}
	
	public void removePoint() {
		
		path.remove(path.size()-1);
		
	}

	public ArrayList<Point> getPath() {
		return path;
	}

}
