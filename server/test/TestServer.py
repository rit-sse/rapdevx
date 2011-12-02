import unittest
from server import *

class TestServer( unittest.TestCase ):
    
    def setUp( self ):
        self.server = Server()

    def testStart( self ):
        self.assertEqual( "Started", self.server.start() )
