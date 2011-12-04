from session import *
from gamepool import *

class NotImplementedException(Exception):
    def __init__(self):
        super(NotImplementedException, self).__init__()

class SessionManager:   
    """
    A class containing all sessions known to the system
    """

    def __init__(self):
        """
        Constructor.
        
        player_pool - Currently unmatched sessions
        sessions - all known sessions
        gamepool - pool of games known
        """
        self.player_pool = []
        self.sessions = {}
        self.gamepool = GamePool()

    def begin_matchmaking(self, session):
        """ 
        Start a matchmaking session for the given session.  Currently
        this just means to match them with the next available session 
        """
        match = self.match(session)

        if match != None:
            self.gamepool.create_game(session, match)
        else:
            self.add_session_to_pool(session)

    def match(self, session): # Class method
        """ 
        Find a matching session from the currently available pool
        of connected sessions.
        """
        # TODO: do matchmaking here
        if len(self.player_pool) >= 1:
            return self.player_pool.pop(0)
        else:
            return None

    def add_session_to_pool(self, session): # Class method
        """ 
        Add a session to the matchmaking pool for future connections.
        """
        self.player_pool.append(session)

    def register_session(self, session, game_id=None):
        """ 
        Register a new session as being ready to participate in a game
        and attempt to connect to a game. 
        """
        self.sessions[session.session_id] = session

        if game_id != None:
            raise NotImplementedException

            # Get selected game
            # Check game player status
            # Add player to game
        else:
            self.begin_matchmaking(session)

    def find_session(self, session_id):
        """ 
        Find the session object for the given session ID. 
        """
        if session_id in self.sessions:
            session = self.sessions[session_id]

            if session.active == False:
                return None
            else:
                return session
        else:
            return None

