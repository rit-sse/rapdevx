from dto import Status
from gameplay import Turn

class GameContext:
    def __init__(self):
        pass
    
    def getAssetSet(self):
        pass
    
    def getRegistry(self):
        pass
    
    def getCurrentPhase(self):
        return GamePhase(self)
    
    def getAllDTOShips(self, my_playernum):
        pass
    
class GamePhase:
    #abstract superclass for all phases
    def __init__(self, context):
        self.context = context

    def addPlayerMove(self, action_order, calling_player):
        Exception("Unimplemented")
        
    def getPlayerMoveList(self, calling_player):
        Exception("Unimplemented")
        
    def getPlayerMove(self, move_id, calling_player):
        Exception("Unimplemented")
    
    def deletePlayerMove(self, move_id, calling_player):
        Exception("Unimplemented")
        
    def setShipPlacement(self, ship_place_list):
        Exception("Unimplemented")
        
    def getGameProgress(self, calling_player):
        Exception("Unimplemented")
    
    def setReady(self, player_num, val):
        Exception("Unimplemented")
        
    def getNextPhase(self):
        return GamePhase()
        
class WaitingPhase(GamePhase):
    #wait for all players to send ready, then move on
    def __init__(self, context, playerlist):
        self.playerlist = playerlist
        self.ready = [False for x in playerlist]

    def getGameProgress(self, calling_player):
        return Status(None, self, self.playerlist, calling_player)

    def setReady(self, player_num, val):
        self.ready[player_num] = val
    
    def getNextPhase(self):
        if(self.ready.contains(False)):
            return self
        else:
            return PlacementPhase(playerlist)
    
class PlacementPhase(GamePhase):
    #players can choose ship positions, then ready
    def __init__(self, context, playerlist):
        self.playerlist = playerlist
        self.ready = [False for x in playerlist]

    def setShipPlacement(self, ship_place_list):
        # todo what do?
        return ship_place_list

    def getGameProgress(self, calling_player):
        return Status(None, self, self.playerlist, calling_player)

    def setReady(self, player_num, val):
        self.ready[player_num] = val

    def getNextPhase(self):
        if(self.ready.contains(False)):
            return self
        else:
            return MovementPhase(playerlist)

class MovementPhase(GamePhase):
    #players choose movements, then ready
    def __init__(self, context, playerlist, turn = Turn(0)):
        self.playerlist = playerlist
        self.ready = [False for x in playerlist]
        self.turn = turn

    def addPlayerMove(self, action_order, calling_player):
        if (isinstance(action_order, MovementOrder)):
            self.turn.addMoveOrder(action_order)
        else:
            Exception("Movement phase is in order, attack orders not allowed")
        
    def getPlayerMoveList(self, calling_player):
        return self.turn.getPlayerMoveList(calling_player)
        
    def getPlayerMove(self, move_id, calling_player):
        # todo is move_id an index?
        return self.turn.getPlayerMoveList(calling_player)[move_id]
    
    def deletePlayerMove(self, move_id, calling_player):
        if (isinstance(action_order, MovementOrder)):
            self.turn.deleteMoveOrder(move_id, action_order)
        else:
            Exception("Movement phase is in order, attack orders not allowed")

    def getGameProgress(self, calling_player):
        return Status(self.turn.turn_num, self, self.playerlist, calling_player)

    def setReady(self, player_num, val):
        self.ready[player_num] = val

    def getNextPhase(self):
        if(self.ready.contains(False)):
            return self
        else:
            return AttackPhase(playerlist, self.turn)
    

class AttackPhase(GamePhase):
    #players choose attacks, then ready
    def __init__(self, context, playerlist, turn):
        self.playerlist = playerlist
        self.ready = [False for x in playerlist]
        self.turn = turn

    def addPlayerMove(self, action_order, calling_player):
        if (isinstance(action_order, AbilityUseOrder)):
            self.turn.addAttackOrder(action_order)
        else:
            Exception("Attack phase is in order, move orders not allowed")
        
    def getPlayerMoveList(self, calling_player):
        return self.turn.getPlayerMoveList(calling_player)
        
    def getPlayerMove(self, move_id, calling_player):
        # todo is move_id an index?
        return self.turn.getPlayerMoveList(calling_player)[move_id]
    
    def deletePlayerMove(self, move_id, calling_player):
        if (isinstance(action_order, AbilityUseOrder)):
            self.turn.deleteAttackOrder(move_id, action_order)
        else:
            Exception("Attack phase is in order, move orders not allowed")


    def getGameProgress(self, calling_player):
        return Status(self.turn.turn_num, self, self.playerlist, calling_player)

    def setReady(self, player_num, val):
        self.ready[player_num] = val

    def getNextPhase(self):
        if(self.ready.contains(False)):
            return self
        else:
            return MovementPhase(playerlist, Turn(self.turn.turn_num + 1))
    
class WonPhase(GamePhase):
    #the game is done, somebody has won
    def __init__(self, context, playerlist, turn):
        self.playerlist = playerlist
        self.turn = turn

    def getGameProgress(self, calling_player):
        return Status(self.turn, self, self.playerlist, calling_player)
    
