from bottle import run, get, post, delete

@get('/games')
def g_games():
    return "GET /games"

@post('/sessions')
def p_sessions():
    return "POST /sessions"

@get('/session/:session_id')
def g_sessions(session_id=None):
    return "GET /session/" + str(session_id)

@delete('/session/:session_id')
def d_sessions(i=None):
    return "DELETE /session/" + str(session_id)

@get('/game/:game_id/assests')
def g_game_assests(game_id=None):
    return "GET /game/" + str(game_id) + "/assests"

@get('/game/:game_id')
def g_game(game_id=None):
    return "GET /game/" + str(game_id)

@post('/game/:game_id/ships')
def p_game_ships(game_id=None):
	return "POST /game/" + str(game_id) + "/ships"

@get('/game/:game_id/ships')
def g_game_ships(game_id=None):
	return "GET /game/" + str(game_id) + "/ships"

@post('/game/:game_id/turns/:turn_id/moves')
def p_game_turns_moves(game_id=None, turn_id=None):
    return "POST /game/" + str(game_id) + "/turns/" + str(turn_id) + "/moves"

@get('/game/:game_id/turns/:turn_id/moves')
def g_game_turns_moves(game_id=None, turn_id=None):
    return "GET /game/" + str(game_id) + "/turns/" + str(turn_id) + "/moves"

@delete('/game/:game_id/turns/:turn_id/moves/:move_id')
def d_game_turns_moves(game_id=None, turn_id=None, move_id=None):
    return "DELETE /game/" + str(game_id) + "/turns/" + str(turn_id) + "/moves/" + str(move_id)

@post('/game/:game_id/turns/:turn_id/attacks')
def p_game_turns_attacks(game_id=None, turn_id=None):
    return "POST /game/" + str(game_id) + "/turns/" + str(turn_id) + "/attacks"
    
@get('/game/:game_id/turns/:turn_id/attacks')
def g_game_turns_attacks(game_id=None, turn_id=None):
    return "GET /game/" + str(game_id) + "/turns/" + str(turn_id) + "/attacks"

@delete('/game/:game_id/turns/:turn_id/attacks/:attack_id')
def d_game_turns_attacks(game_id=None, turn_id=None, attack_id=None):
    return "DELETE /game/" + str(game_id) + "/turns/" + str(turn_id) + "/attacks/" + attack_id

@post('/game/:game_id/turns/:turn_id/ready')
def p_game_turns_ready(game_id=None, turn_id=None):
    return "POST /game/" + str(game_id) + "/turns/" + str(turn_id) + "/ready"

@get('/game/:game_id/turns/:turn_id/moves/results')
def g_game_turns_moves_results(game_id=None, turn_id=None):
    return "GET /game" + str(game_id) + "/turns/" + str(turn_id) + "/moves/results"

@get('/game/:game_id/turns/:turn_id/attacks/results')
def g_game_turns_attacks_results(game_id=None, turn_id=None):
    return "GET /game" + str(game_id) + "/turns/" + str(turn_id) + "/attacks/results"

run(host='localhost', port=8080)

