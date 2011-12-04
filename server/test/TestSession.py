import sys
import os

path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), ".."))

import unittest

from session import *

class TestSession(unittest.TestCase):

    def setUp(self):
        Session.last_id = 0
        self.session = Session()
    
    def test_session_has_generated_session_id(self):
        self.assertEqual("s1", self.session.session_id); # Our session

    def test_nextID_provides_unique_ids(self):
        sid1 = Session.nextID()
        sid2 = Session.nextID()

        self.assertNotEqual(sid1, sid2)

if __name__ == "__main__":
    unittest.main()

