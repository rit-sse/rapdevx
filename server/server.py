from bottle import run, get, post, delete

@get('/games')
def g_games():
    return "GET /games"

@post('/sessions')
def p_sessions():
    return "POST /sessions"

@get('/session/:id')
def g_sessions(id=None):
    return "GET /session/" + str(id)

@delete('/session/:id')
def d_sessions(id=None):
    return "DELETE /session/" + str(id)

@get('/game/:id/assests')
def g_game_assests(id=None):
    return "GET /game/" + str(id) + "/assests"

@get('/game/:id')
def g_game(id=None):
    return "GET /game/" + str(id)

run(host='localhost', port=8080)

