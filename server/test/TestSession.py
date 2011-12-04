import sys
import os

path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), ".."))

import unittest

from session import *

class TestSession(unittest.TestCase):

    def setUp(self):
        self.session = Session()
    
    def test_session_has_generated_session_id(self):
        self.assertEqual("s1", self.session.session_id); # Our session
        self.assertEqual("s2", Session().session_id); # Next session

if __name__ == "__main__":
    unittest.main()

