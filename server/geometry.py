from math import *

DEFAULT_TOLERANCE_FACTOR = 0.005

def distance( point1, point2 ):
	'''
	Get the distance between two points.

	point1 - A tuple containing (x,y) coordinates for the first point.
	point2 - A tuple containing (x,y) coordinates for the second point.
	'''
	deltax = point2[0] - point1[0]
	deltay = point2[1] - point1[1]

	return ( deltax**2 + deltay**2 ) ** 0.5

def slope( point1, point2 ):
	'''
	Get the slope of the line segment defined by the given two points.

	point1 - A tuple containing (x,y) coordinates for the first point.
	point2 - A tuple containing (x,y) coordinates for the second point.
	'''
	x1 = point1[0]
	x2 = point2[0]
	y1 = point1[1]
	y2 = point2[1]

	return ( y2 - y1 ) / ( x2 - x1 )

def isPointOnSegment( source, destination, point, delta = DEFAULT_TOLERANCE_FACTOR ):
	'''
	Determine if a point is on the line segment between source and destination.

	source - Tuple containing (x, y) coordinates for the first point of the
			 line segment.
	destination - Tuple containing (x,y) coordinates for the second point
				  of the line segment.
	point - Tuple containing (x,y) coordinates for the point to check against.
	delta - Tolerance factor. Uses DEFAULT_TOLERANCE_FACTOR by default.
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

def dropPoint( source, destination, point ):
	'''
	Finds the shortest line segment that intersects with a line segment
	starting at source and ending at destination.

	source - A tuple containing the first pair of (x,y) coordinates for the
			 line segment.
	destination - A tuple containing the other pair of (x,y) coordinates for
				  the line segment.
	point - A tuple containing the (x,y) coordinates from which the short line
			segment is projected.
	'''
	a = distance( destination, point )
	b = distance( source, point )
	c = distance( source, destination )

	# Find angle between c and a
	numerator = ( b ** 2 ) - ( a ** 2 ) - ( c ** 2 )
	denominator = -2 * a * c
	tau = acos( numerator / denominator )

	# Get the distance between point and the projected point on the
	# line segment.
	x = a * sin( tau )

	# Get the distance between the source point and the projected point on
	# the line segment.
	c1 = ( ( b ** 2 ) - ( x ** 2 ) ) ** 0.5

	# Get the y-coordinate of the projected point.
	destinationY = destination[1]
	dy = ( c1 * destinationY ) / c

	# Get the x-coordinate of the projected point.
	sourceY = source[1]
	m = slope( source, destination )
	dx = ( dy - sourceY ) / m

	d = dx, dy
	return d

# if __name__ == "__main__":
# 	source = 0, 0
# 	dest = 11, 11

# 	for x in range( 0, 20 ):
# 		for y in range( 0, 20 ):
# 			point = x, y
# 			if( not isPointOnSegment( source, dest, point ) ):
# 				print( "Point (" + str(x) +", " + str(y) + "):" + str( dropPoint( source, dest, point ) ) )

def getCollisionPoint( source, destination, point, sourceRadius, pointRadius, radiusBuffer = 0, delta = DEFAULT_TOLERANCE_FACTOR ):
	'''
	Find the (x,y) coordinates where a moving point (source) with a given
	collision radius (source radius) will stop if it is going to collide
	with some obstacle (point) with its own collision radius (pointRadius).

	NOTE: This function assumes that there will be a collision and doesn't
	perform any checks to see if there will actually be a collision.

	source - A tuple oontaining the (x,y) coordinates for the first point of
			 the line segment. This point is the moving point.
	destination - A tuple containing (x,y) coordinates for the final location
				  of the moving point.
	point - A tuple containg (x,y) coordinates of the obstacle.
	sourceRadius - The collision radius of the moving point.
	pointRadius - The collision radius of the obstacle.
	radiusBuffer - An extra buffer between the source and point radii.
	delta - Tolerance factor. Uses DEFAULT_TOLERANCE_FACTOR by default.
	'''
	pass

def getClosestPointOnSegment( source, destination, point, delta ):
	'''
	Given three points on the same line segment, determine which point is closest to 

	Note: This function assumes that point is on the line segment and doesn't
	actually verify if it is or not.
	'''
	pass