import unittest
import os
import sys

path = os.path.abspath( __file__ )
sys.path.append( os.path.join( os.path.dirname( path ), "../" ) )

from geometry import *

class TestGeometry( unittest.TestCase ):
    def setUp( self ):
        self.source = 0, 0
        self.destination = 10, 10

    def test_dropPointInSegment_LeftOfLineSegment( self ):
        point = -20, 20
        self.assertEqual( self.source, dropPointInSegment( self.source, self.destination, point ) )
        
    def test_dropPointInSegment_NotMiddle_GoingLeft( self ):
        self.source = 10, 10
        self.destination = 5, 5
        point = 4, 4
        self.assertEqual( self.destination, dropPointInSegment( self.source, self.destination, point ) )

    def test_dropPointInSegment_RightOfLineSegment( self ):
        point = 20, 20
        self.assertEqual( self.destination, dropPointInSegment( self.source, self.destination, point ) )
        
    def test_dropPointInSegment_CloserDestination( self ):
        self.source = 100, 1
        self.destination = 1000, 6
        point = 99, 0
        self.assertEqual( self.source, dropPointInSegment( self.source, self.destination, point ) )

    def test_dropPointInSegment_45Deg( self ):
        point = 0, 10
        expected = 5, 5
        self.assertEqual( expected, dropPointInSegment( self.source, self.destination, point ) )
    
    def test_dropPointInSegment_Another45Deg( self ):
        point = 3, 8
        expected = 5.5, 5.5
        self.assertEqual( expected, dropPointInSegment( self.source, self.destination, point ) )

    def test_dropPointInSegment_0Slope( self ):
        self.source = 0, 5
        self.destination = 10, 5
        point = 5, 10
        expected = 5, 5
        self.assertEqual( expected, dropPointInSegment( self.source, self.destination, point ) )
    
    def test_dropPointInSegment_InfSlope( self ):
        self.source = 5, 0
        self.destination = 5, 10
        point = 11, 6
        expected = 5, 6
        self.assertEqual( expected, dropPointInSegment( self.source, self.destination, point) )

    def test_dropPointInOrOutSegment_0Slope_Outside( self ):
        self.source = 0, 5
        self.destination = 10, 5
        point = 11, 5
        expected = 11, 5
        self.assertEqual( expected, dropPointInOrOutSegment( self.source, self.destination, point) )
        
    def test_dropPointInOrOutSegment_InfSlope_Outside( self ):
        self.source = 5, 0
        self.destination = 5, 10
        point = 6, 11
        expected = 5, 11
        self.assertEqual( expected, dropPointInOrOutSegment( self.source, self.destination, point) )
        
    def test_dropPointInOrOutSegment_NotMiddle_GoingLeft( self ):
        self.source = 10, 10
        self.destination = 5, 5
        point = 4, 4
        self.assertEqual( point, dropPointInOrOutSegment( self.source, self.destination, point ) )
        
    def test_dropPointInOrOutSegment_NotMiddle_GoingLeft_OffLine( self ):
        self.source = 10, 10
        self.destination = 5, 5
        point = 3, 5
        expected = 4, 4
        self.assertEqual( expected, dropPointInOrOutSegment( self.source, self.destination, point ) )

if __name__ == "__main__":
    unittest.main()