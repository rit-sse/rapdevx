from bottle import run, get, post, delete, request
from bottle import *
from session import *
from sessionmanager import *

session_manager = SessionManager()

def get_game(game_id):
    game = session_manager.game_pool.find_game(game_id)

    if game == None:
        abort(404, "Game not found")

    return game

def get_session(session_id):
    session = session_manager.find_session(session_id)

    if session == None:
        abort(404, "Session not found")

    return session

# Gets a list of all games as json
@get('/games')
def list_active_games():
    games = session_manager.game_pool.active_games()

    games_json_array = [game.encode() for game in games]
    return json.dumps(games_json_array)

# Create a new session given posted data
@post('/sessions')
def create_session():
    # pull data out of request
    nickname = request.forms.nickname
    game_id = request.forms.game_id

    # scrub data from forms
    if game_id == '':
        game_id = None
    
    session = Session(nickname, game_id)

    session_manager.register_session(session, game_id)

    return session.to_json

# Return session itself as json
@get('/session/:session_id')
def get_session_info(session_id=None):
    session = get_session(session_id)
    
    return session.to_json

# Mark a session as closed
@delete('/session/:session_id')
def delete_session(session_id=None):
    session = get_session(session_id)

    session.active = False

# Get assets from game as json
@get('/game/:game_id/assets')
def get_game_assets(game_id=None):
    game = get_game(game_id)

    assets = game.getAssetSet()

    return assets.encode()

# Return game status (progress) as json
@get('/game/:game_id')
def g_game(game_id=None):
    game = get_game(game_id)

    status = game.getGameProgress

    return status.encode()

# Indicate that the user is ready to start the gaem
@post('/game/:game_id')
def g_game(game_id=None):
    ready = request.forms.ready
    session_id = request.forms.session_id

    game = get_game(game_id)
    session = get_session(session_id)

    if ready:
        game.setReady(session)

# Set the initial position of all units from given POST data
@post('/game/:game_id/ships')
def p_game_ships(game_id=None):
    session_id = request.forms.session_id
    unit_layout_json = request.forms.unit_layout

    game = get_game(game_id)
    session = get_session(session_id)

    unit_layout = JSON_Construct_DTO_ShipPlacement(unit_layout_json)
    
    game.setShipPlacement(unit_layout, session_id.player_num)

# Return the location and type of all ships as json
# { # Example
#   location: ????,
#   type: ????
# }
@get('/game/:game_id/ships')
def g_game_ships(game_id=None):
    game = get_game(game_id)
    # TODO: waiting on mitch integration

    return "GET /game/" + str(game_id) + "/ships"

# Create a new movement order from POST data
@post('/game/:game_id/turns/:turn_id/moves')
def p_game_turns_moves(game_id=None, turn_id=None):
    session_id = request.forms.session_id
    move_descriptor_json = request.forms.movement_order

    session = get_session(session_id)
    game = get_game(game_id)

    movement_order = JSON_Construct_DTO_MovementOrder(move_descriptor_json)

    game.addPlayerMove(movement_order, session.player_num)

# Get a list of move order from the provided turn as json
@get('/game/:game_id/turns/:turn_id/moves')
def g_game_turns_moves(game_id=None, turn_id=None):
    session_id = request.forms.session_id

    session = get_session(session_id)
    game = get_game(game_id)

    movement_orders = game.getPlayerMoveList(session.player_num)

    orders_json_array = [order.encode() for order in movement_orders]
    return json.dumps(orders_json_array)

# Delete a move from the current turn
@delete('/game/:game_id/turns/:turn_id/moves/:move_id')
def d_game_turns_moves(game_id=None, turn_id=None, move_id=None):
    session_id = request.forms.session_id

    session = get_session(session_id)
    game = get_game(game_id)

    game.deletePlayerMove(move_id, session.player_num)

# Create a new attack order from POST data
@post('/game/:game_id/turns/:turn_id/attacks')
def p_game_turns_attacks(game_id=None, turn_id=None):
    session_id = request.forms.session_id
    attack_order_json = request.forms.attack_order

    session = get_session(session_id)
    game = get_game(game_id)

    attack_order = JSON_Construct_DTO_AbilityUseOrder(attack_order_json)

    game.addPlayerMove(attack_order, session.player_num)
    
# Get a list of attack orders from the provided turn as json
@get('/game/:game_id/turns/:turn_id/attacks')
def g_game_turns_attacks(game_id=None, turn_id=None):
    session_id = request.forms.session_id
    
    session = get_session(session_id)
    game = get_game(game_id)

    attack_orders = game.getPlayerMoveList(session.player_num)

    orders_json_array = [order.encode() for order in attack_orders]
    return json.dumps(orders_json_array)

# Delete an attack from the current turn
@delete('/game/:game_id/turns/:turn_id/attacks/:attack_id')
def d_game_turns_attacks(game_id=None, turn_id=None, attack_id=None):
    session_id = request.forms.session_id

    session = get_session(session_id)
    game = get_game(game_id)

    game.deletePlayerMove(attack_id, session.player_num)

# Mark current session (by session_id POST) as ready
@post('/game/:game_id/turns/:turn_id/ready')
def p_game_turns_ready(game_id=None, turn_id=None):
    session_id = request.forms.session_id

    session = get_session(session_id)
    game = get_game(game_id)

    game.setReady(session.player_num, True)

# Get the finalized movement orders of given turn as json
@get('/game/:game_id/turns/:turn_id/moves/results')
def g_game_turns_moves_results(game_id=None, turn_id=None):
    session_id = request.forms.session_id

    session = get_session(session_id)
    game = get_game(game_id)

    results = getTurnMoveResults(turn_id)

    return results.encode

# Get the finalized attack orders of given turn as json
@get('/game/:game_id/turns/:turn_id/attacks/results')
def g_game_turns_attacks_results(game_id=None, turn_id=None):
    session_id = request.forms.session_id

    session = get_session(session_id)
    game = get_game(game_id)

    results = getTurnAttackResults(turn_id)

    return results.encode

if __name__ == "__main__":
    run(host='localhost', port=8080)
