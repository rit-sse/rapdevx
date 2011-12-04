import sys
import os

path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), ".."))

os.chdir("..")

import unittest

from gamemanager import *

class TestGameManager(unittest.TestCase):

    def setUp(self):
        self.session_a = Session(nickname="Miley Cyrus")
        self.session_b = Session(nickname="Justin Bieber")

    def test_creat_game(self):
        GameManager.create_game(self.session_a, self.session_b)
        self.assertEqual(0, self.session_a.game_id)
        self.assertEqual(0, self.session_b.game_id)
        self.assertEqual(1, self.session_b.player_num)

if __name__ == "__main__":
    unittest.main();
