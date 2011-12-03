from bottle import run, get, post, delete, request

games = []

class NotImplementedException:
	def __init__(self):
		super(NotImplementedException, self).__init__()
		
class Session:
	# Previously assigned session id, for use in unique ids
	last_id = 0

	def __init__(self, nickname="LazyPlayer"):
		self.nickname = nickname
		self.active = true
		self.session_id = Session.nextID()

	def nextID():
		last_id += 1
		return "s" + last_id

	def to_json(self):
		"""Convert to json representation"""
		json_dict = {}
		json_dict['session_id'] = self.session_id
		json_dict['nickname'] = self.nickname
		json_dict['game_id'] = self.game_id
		json_dict['active'] = self.active

		return json.dumps(json_dict)

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
                # TODO: replace this with a new GameManager
				games.append(GameContext())

				session.game_id = games.length - 1
				match.game_id = games.length - 1
			else:
				SessionManager.add_session_to_pool(self)

    """ Find a matching session from the currently available pool
        of connected sessions. """
	def match(session):
		# TODO: do matchmaking here
		if player_pool.size >= 1:
			return player_pool.pop(0)
		else:
			return None

    """ Add a session to the matchmaking pool for future connections."""
	def add_session_to_pool(session)
		player_pool.append(session)

    """ Register a new session as being ready to participate in a game
        and attempt to connect to a game. """
	def register_session(session, game_id=None):
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
		session = sessions[session_id]

		if session == None or session.active == false:
			return None
		else:
			return session

# Gets a list of all games as json
@get('/games')
def g_games():
    return to_json(games)

# Create a new session given posted data
@post('/sessions')
def p_sessions():
	# pull data out of request
	nickname = request.forms.nickname
	game_id = request.forms.game_id

	# scrub data from forms
	if game_id == '':
		game_id = None
	
	session = Session(nickname, game_id)

	SessionManager.register_session(session, game_id)

	return session.to_json

# Return session itself as json
@get('/session/:session_id')
def g_sessions(session_id=None):
	session = SessionManager.find_session(session_id)

	if session == None:
		# Do we need to return after this?
		abort(404, "No session with that ID found")

	return session.to_json

# Mark a session as closed
@delete('/session/:session_id')
def d_sessions(session_id=None):
	session = SessionManager.find_session(session_id)

	if session == None:
		# Do we need to return after this?
		abort(404, "No session with that ID found")

    session.active = false

# Get assets from game as json
@get('/game/:game_id/assets')
def g_game_assests(game_id=None):
    return to_json(games[game_id].getAssetSet())

# Return game status (progress) as json
@get('/game/:game_id')
def g_game(game_id=None):
    return to_json(games[game_id].status)

# ?? Magic?
@post('/game/:game_id')
def g_game(game_id=None):
    pass
    # TODO help

# Set the initial position of all units from given POST data
@post('/game/:game_id/ships')
def p_game_ships(game_id=None):
	return "POST /game/" + str(game_id) + "/ships"

# Return the location and type of all ships as json
# { # Example
#   location: ????,
#   type: ????
# }
@get('/game/:game_id/ships')
def g_game_ships(game_id=None):
	return "GET /game/" + str(game_id) + "/ships"

# Create a new movement order from POST data
@post('/game/:game_id/turns/:turn_id/moves')
def p_game_turns_moves(game_id=None, turn_id=None):
    return "POST /game/" + str(game_id) + "/turns/" + str(turn_id) + "/moves"

# Get a list of move order from the provided turn as json
@get('/game/:game_id/turns/:turn_id/moves')
def g_game_turns_moves(game_id=None, turn_id=None):
    return "GET /game/" + str(game_id) + "/turns/" + str(turn_id) + "/moves"

# Delete a move from the current turn
@delete('/game/:game_id/turns/:turn_id/moves/:move_id')
def d_game_turns_moves(game_id=None, turn_id=None, move_id=None):
    return "DELETE /game/" + str(game_id) + "/turns/" + str(turn_id) + "/moves/" + str(move_id)

# Create a new attack order from POST data
@post('/game/:game_id/turns/:turn_id/attacks')
def p_game_turns_attacks(game_id=None, turn_id=None):
    return "POST /game/" + str(game_id) + "/turns/" + str(turn_id) + "/attacks"
    
# Get a list of attack orders from the provided turn as json
@get('/game/:game_id/turns/:turn_id/attacks')
def g_game_turns_attacks(game_id=None, turn_id=None):
    return "GET /game/" + str(game_id) + "/turns/" + str(turn_id) + "/attacks"

# Delete an attack from the current turn
@delete('/game/:game_id/turns/:turn_id/attacks/:attack_id')
def d_game_turns_attacks(game_id=None, turn_id=None, attack_id=None):
    return "DELETE /game/" + str(game_id) + "/turns/" + str(turn_id) + "/attacks/" + attack_id

# Mark current session (by session_id POST) as ready
@post('/game/:game_id/turns/:turn_id/ready')
def p_game_turns_ready(game_id=None, turn_id=None):
    return "POST /game/" + str(game_id) + "/turns/" + str(turn_id) + "/ready"

# Get the finalized movement orders of given turn as json
@get('/game/:game_id/turns/:turn_id/moves/results')
def g_game_turns_moves_results(game_id=None, turn_id=None):
    return "GET /game" + str(game_id) + "/turns/" + str(turn_id) + "/moves/results"

# Get the finalized attack orders of given turn as json
@get('/game/:game_id/turns/:turn_id/attacks/results')
def g_game_turns_attacks_results(game_id=None, turn_id=None):
    return "GET /game" + str(game_id) + "/turns/" + str(turn_id) + "/attacks/results"

run(host='localhost', port=8080)
