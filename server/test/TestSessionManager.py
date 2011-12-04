import sys
import os

path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), ".."))

os.chdir("..")

import unittest

from sessionmanager import *
from session import *
from context import * # GameContext

class TestSessionManager(unittest.TestCase):

    def setUp(self):
        self.session_a = Session()
        self.session_b = Session()
        self.manager = SessionManager()
    
    def test_register_session_with_matchmaking(self):
        self.manager.register_session(self.session_a)

        self.assertTrue(self.session_a.session_id in self.manager.sessions)

    def test_register_session_with_game_id_not_implemented_yet(self):
        with self.assertRaises(NotImplementedException):
            self.manager.register_session(self.session_a, "g0")

    def test_register_session_with_available_partner_connects_to_new_game(self):
        self.manager.register_session(self.session_a)
        self.manager.register_session(self.session_b)

        self.assertIsNotNone(self.session_a.game_id)
        self.assertIsNotNone(self.session_b.game_id)

        self.assertEqual(self.session_a.game_id, self.session_b.game_id)

        game = self.manager.gamepool.find_game(self.session_a.game_id)

        self.assertIsNotNone(game)

    def test_find_session(self):
        self.manager.register_session(self.session_a)

        self.assertIsNotNone(self.manager.find_session(self.session_a.session_id))

if __name__ == "__main__":
    unittest.main()
