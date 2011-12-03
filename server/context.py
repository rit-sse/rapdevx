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
    def __init__(self, context):
        self.context = context
    
    def addPlayerMove(self, movement_order):
        pass
        
    def getPlayerMoveList(self):
        pass
        
    def getPlayerMove(self, move_id):
        pass
        
    def setShipPlacement(self, move_id):
        pass
        
    def getGameProgress(self):
        pass
    
    def setReady(self):
        pass
        
    