import session

class GameManager:
    games = {}
    last_id = 0

    def next_game_id():
        last_id += 1
        return "g" + last_id

    def create_game(session1=None, session2=None):
        """Creates a new game with the provided sessions connected"""
        game = GameContext()
        game_id = GameManager.next_game_id()

        games[game_id] = game
        session1.game_id = game_id
        session2.game_id = game_id

    def find_game(game_id):
        """Finds a game in our database given the game_id."""
        if game_id in games:
            return games[game_id]
        else:
            return None

        
