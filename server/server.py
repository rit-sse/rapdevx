from bottle import run, get, post, delete

games = []
sessions = []

# LOLWUT? Why do I need to do this?
def to_json(obj):
  encoder = DTOEncoder()
  return encoder.default(obj)

# Gets a list of all games as json
@get('/games')
def g_games():
    return "GET /games"

# Create a new session given posted data
@post('/sessions')
def p_sessions():
    # Get post data how???
    return "POST /sessions"

# Return session itself as json
@get('/session/:session_id')
def g_sessions(session_id=None):
    return "GET /session/" + str(session_id)

# Mark a session as closed
@delete('/session/:session_id')
def d_sessions(session_id=None):
    return sessions.pop(session_id) # TODO Fix

# Get assets from game as json
@get('/game/:game_id/assets')
def g_game_assests(game_id=None):
    return to_json(games[game_id].getAssetSet())

# Return game status (progress) as json
@get('/game/:game_id')
def g_game(game_id=None):
    return to_json(games[game_id].status)

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
