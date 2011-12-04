class GameRegistry:
    def __init__(self):
        '''
        Constructor.

        registry - A dictionary which contains registered objects.
        count - 
        '''
        self.registry = {}
        self.count = {}
        
    def register(self, entry):
        '''
        Adds an entry to the internal dictionary so getById(id) returns entry
        and set entry.id to id
        '''
        prefix = entry.__class__.__name__

        if (prefix not in self.count):
            self.count[prefix] = 0
        else:
            self.count[prefix] += 1

        self.registry[prefix + str(self.count[prefix])] = entry
        entry.gid = prefix + str(self.count[prefix])
        
    def getAllByType(self, t):
        '''
        Return any registered entry that is the given python type.
        '''
        type_list = []
        for i in self.registry:
            if self.registry[i].__class__==t:
                type_list.append(self.registry[i])
        return type_list
    
    def getById(self, game_id):
        '''
        '''
        if game_id in self.registry:
            return self.registry[game_id]
        else:
            return None
    
    def removeById(self, game_id):
        '''
        '''
        if (game_id in self.registry):
            self.registry.pop(game_id)
    
    def getRelevantUnits(self, path):
        '''
        For now, list all units - this can be made into a filter for
        effency reasons
        '''
        return self.getAllByType("Unit")
    
    
