DEFAULT_TOLERANCE_FACTOR = 0.05

def distance( point1, point2 ):
	'''
	Get the distance between two points.

	point1 - Tuple containing (x,y) coordinates for the first point.
	point2 - Tuple containing (x,y) coordinates for the second point.
	'''
	deltax = point2[0] - point1[0]
	deltay = point2[1] - point1[1]

	return ( deltax**2 + deltay**2 ) ** 0.5

def isPointOnSegment( source, destination, point, delta ):
	'''
	Determine if a point is on the line segment between source and destination.

	source - Tuple containing (x, y) coordinates for the first point of the
			 line segment.
	destination - Tuple containing (x,y) coordinates for the second point
				  of the line segment.
	point - Tuple containing (x,y) coordinates for the point to check against.
	delta - Tolerance factor. 
	'''
	s2dDistance = distance( source, destination )
	d2pDistance = distance( destination, point )
	s2pDistance = distance( source, point )

	difference = ( s2dDistance + d2pDistance ) - s2pDistance

	# If the difference between the two line segment lengths is within the 
	# range [-delta, delta], then the point is considered to be on the line
	# segment.
	if( ( -1 * delta <= difference ) and ( difference <= delta ) ):
		return True

	return False