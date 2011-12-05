from math import acos, sin, ceil

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
    
    dx = x2-x1
    dy = y2-y1

    t = float(dx*(xp-x1)+dy*(yp-y1))/(dx*dx + dy*dy)
    return (x1+dx*t,y1+dy*t)


def dropPointInSegment( source, destination, point ):
    '''
    returns the closest point to "point" on the line segment [source,destination]
    '''
    onLine = dropPointInOrOutSegment(source,destination,point)
    if isPointOnSegment(source,destination,onLine):
        print("is on line:",onLine)
        return onLine
    else:
        #closest is one of the end points
        if distance(source,onLine)<distance(destination,onLine):
            return source
        else:
            return destination

def shipsWillCollideOnSegment(source, destination, travelingShip, stationaryShip):
    '''
    returns True if a traveling ship a (gameplay.Unit) would collide with
    stationary ship (another gameplay.Unit) when traveling from (x,y) source
    to (x,y) destination
    
    If travelingShip when located at source would collide with stationaryShip
    at the source point, this will return false, as to allow ships that are
    colliding already to escape one another'''
    radius_sum = travelingShip.radius + stationaryShip.radius
    
    #ships start colliding, let them escape
    if distance(source, stationaryShip.location)<radius_sum:
        print("collides on start")
        return False
    
    closest_pass = dropPointInSegment(source,destination,stationaryShip.location)
    print("closest pass",closest_pass,":",stationaryShip.location)
    return distance(closest_pass,stationaryShip.location) < radius_sum
    
def whereWillItStop(source, destination, travelingShip, stationaryShip):
    '''
    Precondition: shipsWillCollideOnSegment with the same parameters must be True
    
    finds the final resting point of a traveling ship so that it stops before it hits 
    stationaryShip when traveling from source to destination.
    '''
    drop = dropPointInOrOutSegment(source, destination, stationaryShip.location)
    
    dropSourceDist = distance(drop,source)
    
    height = distance(stationaryShip.location, drop)
    hypotenuse = ceil(travelingShip.radius + stationaryShip.radius)
    
    distFromDrop = (hypotenuse**2 - height**2)**0.5
    
    portion = 1-distFromDrop/dropSourceDist
    dx = portion*(drop[0]-source[0])
    dy = portion*(drop[1]-source[1])
    r = (round(source[0]+dx),round(source[1]+dy))
    return r

if __name__ == "__main__":
    import gameplay
    u1 = gameplay.Unit(location=(0,0),radius=1)
    u2 = gameplay.Unit(location=(11,0),radius=3)
    print(whereWillItStop((0,0),(10,0),u1,u2))

    u1 = gameplay.Unit(location=(10,0),radius=1)
    u2 = gameplay.Unit(location=(-1,0),radius=3)
    print(whereWillItStop((10,0),(0,0),u1,u2))

    u1 = gameplay.Unit(location=(0,0),radius=1)
    u2 = gameplay.Unit(location=(0,11),radius=3)
    print(whereWillItStop((0,0),(0,10),u1,u2))

    u1 = gameplay.Unit(location=(0,0),radius=1)
    u2 = gameplay.Unit(location=(0,-1),radius=3)
    print(whereWillItStop((0,10),(0,0),u1,u2))

    u1 = gameplay.Unit(location=(0,0),radius=1)
    u2 = gameplay.Unit(location=(11,11),radius=3)
    print(whereWillItStop((0,0),(10,10),u1,u2))

    u1 = gameplay.Unit(location=(0,0),radius=1)
    u2 = gameplay.Unit(location=(10,10),radius=3)
    print(whereWillItStop((0,0),(10,10),u1,u2))

    u1 = gameplay.Unit(location=(0,0),radius=10)
    u2 = gameplay.Unit(location=(1000,0),radius=10)
    print(whereWillItStop((0,0),(1000,0),u1,u2))

    
