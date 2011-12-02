class GameRegistry:
	def __init__(self):
        pass
        
    #add to dictionary so getById(id) returns entry
    #and set entry.id to id
    def register(self, entry):
        pass
        
    #return any registered entry that is the given python type
    def getAllByType(self, type):
        pass
    
    
    def getById(self, id):
        pass
        
    #for now, list all ships - this can be made into a filter for
    #effency reasons
    def getRelevantShips(self, path):
        pass
    
    #registers the turn object
    def registerTurn(self, turn, turn_number):
        pass
    
    #gets the turn registered to a number
    def getTurn(self, turn_number):
        pass
