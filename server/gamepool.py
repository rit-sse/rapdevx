from session import *
from context import *

class GamePool:
    '''
    A class containing all the games known to the system.
    '''

    def __init__(self):
        '''
        Constructor.

        games - A list of all known games
        last_id - The most recent game id.
        '''
        self.games = []
        self.last_id = 0

    def next_game_id(self):
        '''
        Provides a unique identifier for a game
        '''

        self.last_id += 1
        return "g" + str( self.last_id )

    def create_game(self, session1=None, session2=None):
        '''
        Creates a new game with the provided sessions connected
        '''

        playerlist = []

        if session1:
            playerlist.append(session1.nickname)

            if session2:
                playerlist.append(session2.nickname)

        game = GameContext(playerlist)
        game_id = self.next_game_id()

        self.games[game_id] = game
        
        if session1:
            session1.game_id = game_id
            session1.player_num = 0
        
        if session2:
            session2.game_id = game_id
            session2.player_num = 1

        return game

    def find_game(self, game_id):
        '''
        Finds a game in our database given the game_id.
        '''
        
        if game_id in self.games:
            return self.games[game_id]
        
        else:
            return None

    def active_games(self):
        '''
        Return a list of all registered games waiting for more players
        '''
        return [game for game in self.games.values() if game.phase == "waiting"]

        
