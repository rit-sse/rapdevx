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
        game = self.game_pool.create_game(self.session_a, self.session_b)

        # Make sure we can get to the game from each session
        self.assertEqual(game, self.game_pool.find_game(self.session_a.game_id))
        self.assertEqual(game, self.game_pool.find_game(self.session_b.game_id))

        # Make sure player are given the correct numbers
        self.assertEqual(0, self.session_a.player_num)
        self.assertEqual(1, self.session_b.player_num)

    def test_create_game_with_one_session(self):
        game = self.game_pool.create_game(self.session_a)

        self.assertEqual(game, self.game_pool.find_game(self.session_a.game_id))

        self.assertEqual(0, self.session_a.player_num)

    def test_game_id_is_unique(self):
        gid1 = self.game_pool.next_game_id()
        gid2 = self.game_pool.next_game_id()

        self.assertNotEqual(gid1, gid2)
        
    def test_find_game_locates_a_game(self):
        self.game_pool.games["ugly test"] = GameContext(["Miley", "Justin"])

        self.assertIsNotNone(self.game_pool.find_game("ugly test"))

    def test_active_games_finds_no_games_when_empty(self):
        games = self.game_pool.active_games()

        self.assertEqual(0, len( games ))

    def test_active_games_finds_game_when_unmatched(self):
        game = self.game_pool.create_game(self.session_a)

        games = self.game_pool.active_games()

        self.assertEqual(1, len( games ))


if __name__ == "__main__":
    unittest.main();
