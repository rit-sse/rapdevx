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
    
    def addPlayerMove(self, movement_order):
        Exception("Unimplemented")
        
    def getPlayerMoveList(self):
        Exception("Unimplemented")
        
    def getPlayerMove(self, move_id):
        Exception("Unimplemented")
        
    def setShipPlacement(self, move_id):
        Exception("Unimplemented")
        
    def getGameProgress(self):
        Exception("Unimplemented")
    
    def setReady(self):
        Exception("Unimplemented")
        
    def getNextPhase(self):
        return GamePhase()
        
class WaitingPhase(GamePhase):
    def __init__(self, context, playerlist):
        self.playerlist = playerlist
        self.ready = [False for x in playerlist]
    def setReady(self, player_num, val):
        self.ready[player_num] = val
    
    def getNextPhase(self):
        if(self.ready.contains(False)):
            return self
        else:
            return ShipPlacementPhase(playerlist)
        