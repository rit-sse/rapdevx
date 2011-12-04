import sys
import os

path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), ".."))

os.chdir("..")

import unittest

from sessionmanager import *
from gamemanager import *
from session import *
from context import * # GameContext

class TestSessionManager(unittest.TestCase):

    def setUp(self):
        self.session_a = Session()
        self.session_b = Session()
        self.manager = SessionManager()
    
    def test_register_session(self):
        self.manager.register_session(self.session_a, game_id=0)

        self.assertTrue(self.session_a in self.manager.sessions)

    def test_register_new_session(self):
        self.manager.register_session(self.session_a)

        self.assertTrue(self.session_a in self.manager.sessions)

    def test_register_session_with_available_partner_connects_to_new_game(self):
        self.manager.register_session(self.session_a)
        self.manager.register_session(self.session_b)

        game = GameManager.games[0]

        # self.assertTrue(self.session_a.session_id in game.playerlist)

    def test_find_session(self):
        self.manager.register_session(self.session_a)

        self.assertIsNotNone(self.manager.find_session(self.session_a.session_id))

if __name__ == "__main__":
    unittest.main()
