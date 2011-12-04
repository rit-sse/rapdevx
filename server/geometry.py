from math import acos, sin

DEFAULT_TOLERANCE_FACTOR = 0.005

def distance( point1, point2 ):
    '''
    Get the distance between two points.

    point1 - A tuple containing (x,y) coordinates for the first point.
    point2 - A tuple containing (x,y) coordinates for the second point.
    '''
    deltax = point2[0] - point1[0]
    deltay = point2[1] - point1[1]

    return ( deltax ** 2 + deltay ** 2 ) ** 0.5

def isHorizontal( point1, point2):
    '''
    A check for whether two points form a vertical line (slope calculation
    would fail wiwth a divide by 0).
    
    point1 - A tuple containing (x,y) coordinates for the first point.
    point2 - A tuple containing (x,y) coordinates for the second point.
    '''
    return ( point2[0] - point1[0] ) == 0

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
    Determine if a point is on the line segment defined by source and destination.

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

    return abs( s2dDistance -d2pDistance - s2pDistance)<delta
    
def dropPointInOrOutSegment(P1, P2, P3):
    '''
    given a line with points P1 and P2 on it, find the closest point on the
    line to P3
    '''
    x1,y1 = P1
    x2,y2 = P2
    
    xp,yp = P3
    
    dx = x1-x2
    dy = y1-y2
    
    t = (dx*(xp-x1)+dy*(yp-y1))/(dx*dx + dy*dy)
    return (x1+dx*t,y1+dy*t)


def dropPointInSegment( source, destination, point ):
    '''
    returns the closest point to "point" on the line segment [source,destination]
    '''
    onLine = dropPointInOrOutSegment(source,destination,point)
    if isPointOnSegment(source,destination,onLine):
        return onLine
    else:
        #closest is one of the end points
        if distance(source,onLine)<distance(destination,onLine):
            return source
        else:
            return destination

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

def getClosestPointOnSegment( source, destination, point, delta = DEFAULT_TOLERANCE_FACTOR ):
    '''
    Given a line segment and a point on that line segment, determine which end
    of the line segment is closer.

    Note: This function assumes that point is on the line segment and doesn't
    actually verify if it is or not.

    source - A tuple containing the first pair of (x,y) coordinates for the
             line segment.
    destination - A tuple containing the other pair of (x,y) coordinates for
                  the line segment.
    point - A tuple containing the (x,y) coordinates for some point on the line.
    delta - Tolerance factor. Uses DEFAULT_TOLERANCE_FACTOR by default.
    '''

    s2pDistance = distance( source, point )
    p2dDistance = distance( point, destination )

    if( s2pDistance <= p2dDistance ):
        return source

    else:
        return destination

        
