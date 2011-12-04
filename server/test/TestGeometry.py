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

	def test_dropPoint_LeftOfLineSegment( self ):
		point = -20, 20
		self.assertEqual( self.source, dropPoint( self.source, self.destination, point ) )

	def test_dropPoint_RightOfLineSegment( self ):
		point = 20, 20
		self.assertEqual( self.destination, dropPoint( self.source, self.destination, point ) )

	def test_dropPoint_45Deg( self ):
		point = 0, 10
		expected = 5, 5
		self.assertEqual( expected, dropPoint( self.source, self.destination, point ) )
	
	def test_dropPoint_Another45Deg( self ):
		point = 3, 8
		expected = 5.5, 5.5
		self.assertEqual( expected, dropPoint( self.source, self.destination, point ) )

if __name__ == "__main__":
	unittest.main()