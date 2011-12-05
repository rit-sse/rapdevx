package edu.rit.se.sse.rapdevx.clientmodels;

import java.awt.Point;
import java.util.List;
import java.util.Stack;

import java.util.Hashtable;
import java.util.LinkedList;

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

	public LinkedList< Hashtable< Integer, Integer > > getMovementOrderPath() {
		// MovementOrder uses a LinkedList< Hashtable< Integer, Integer > >
		
		LinkedList< Hashtable< Integer, Integer > > orderPath = 
			new LinkedList< Hashtable< Integer, Integer > >();
		
		for ( Point point : getPath() ) {
			Hashtable< Integer, Integer > orderPoint = new Hashtable< Integer, Integer >();
			orderPoint.put( (int)point.getX(), (int)point.getY() );
			
			orderPath.add( orderPoint );
		}
		
		return orderPath;
		
	}

}
