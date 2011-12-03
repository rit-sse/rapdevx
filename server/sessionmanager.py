import session
import gamemanager

class SessionManager:	
	"""Currently unmatched sessions"""
	player_pool = []
	"""All known sessions"""
	sessions = {}

	""" Start a matchmaking session for the given session.  Currently
		this just means to match them with the next available session """
	def begin_matchmaking(session):
			match = SessionManager.match(session)

			if match != None:
                GameManager.create_game(session, match)
			else:
				SessionManager.add_session_to_pool(self)

	def match(session):
        """ Find a matching session from the currently available pool
            of connected sessions. """
		# TODO: do matchmaking here
		if player_pool.size >= 1:
			return player_pool.pop(0)
		else:
			return None

	def add_session_to_pool(session)
        """ Add a session to the matchmaking pool for future connections."""
		player_pool.append(session)

	def register_session(session, game_id=None):
        """ Register a new session as being ready to participate in a game
            and attempt to connect to a game. """
		sessions[session.session_id] = session

		if game_id != None:
			raise NotImplementedException

			# Get selected game
			# Check game player status
			# Add player to game
		else:
			begin_matchmaking(session)
	
    """ Find the session object for the given session ID. """
	def find_session(session_id):
        if session_id in sessions:
            session = sessions[session_id]

            if sessions.active == false:
                return None
            else:
                return session
        else:
            return None

