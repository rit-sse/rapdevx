package edu.rit.se.sse.rapdevx.clientmodels;

import java.awt.Point;
import java.util.List;
import java.util.Stack;

public class Path {
	
	private Stack<Point> path;
	
	public Path(Point start) {
		
		this.path = new Stack<Point>();
		path.add(start);
		
	}
	
	public void addPoint(Point point) {
		path.push(point);
	}
	
	public Point getLastPoint() {
		return path.peek();
	}
	
	public void removePoint() {
		path.pop();
	}

	public List<Point> getPath() {
		return path;
	}

}
