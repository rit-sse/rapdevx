import unittest
from urllib.request import urlopen
# from server import *

SERVER_URL = "http://localhost:8080"

def read_from_url(url):
    contents = ""
    for line in urlopen(url):
        line.decode("utf-8") # HAHAHA!!!
        contents += str(line)

    return line

class TestServer( unittest.TestCase ):
    def test_GenericRequests( self ):
        try:
            read_from_url(SERVER_URL + "/games")
            # read_from_url(SERVER_URL + "/sessions") # POST
        except:
            self.fail()

    def _test_GetRequests( self ):
        try:
            read_from_url(SERVER_URL + "/session/1")
            read_from_url(SERVER_URL + "/game/1")
            read_from_url(SERVER_URL + "/game/1/assets")
            read_from_url(SERVER_URL + "/game/1/turns/8/moves")
            read_from_url(SERVER_URL + "/game/-7/turns/9/attacks/results")
        except:
            self.fail()

unittest.main()
