import sys
import os

path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), ".."))

os.chdir("..")

import unittest

from gamemanager import *
from game import *

class TestGameManager(unittest.TestCase):

    def setUp(self):
        self.session_a = Session(nickname="Miley Cyrus")
        self.session_b = Session(nickname="Justin Bieber")

    def test_creat_game(self):
        pass

if __name__ == "__main__":
    unittest.main();
