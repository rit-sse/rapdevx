import sys
import os

path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), ".."))

os.chdir("..")

import unittest

from gamepool import *

class TestGamePool(unittest.TestCase):

    def setUp(self):
        self.session_a = Session(nickname="Miley Cyrus")
        self.session_b = Session(nickname="Justin Bieber")
        self.game_pool = GamePool()

    def test_create_game(self):
        self.game_pool.create_game(self.session_a, self.session_b)
        # TODO: this is a logic issue, unless you patch next_game_id
        self.assertEqual(0, self.session_a.game_id)
        self.assertEqual(0, self.session_b.game_id)
        self.assertEqual(0, self.session_a.player_num)
        self.assertEqual(1, self.session_b.player_num)

if __name__ == "__main__":
    unittest.main();
