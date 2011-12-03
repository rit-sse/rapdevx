class GameRegistry:
    def __init__(self):
        self.registry = {}
        self.count = {}

        
    #add to dictionary so getById(id) returns entry
    #and set entry.id to id
    def register(self, entry):
        prefix = entry.__class__.__name__
        if (prefix not in self.count): self.count[prefix] = 0
        else: self.count[prefix] += 1
        self.registry[prefix + str(self.count[prefix])] = entry
        entry.gid = prefix + str(self.count[prefix])
        
    #return any registered entry that is the given python type
    def getAllByType(self, game_type):
        type_list = []
        for i in self.registry:
            if(game_type in i): type_list.append(self.registry[i])
        return type_list
    
    def getById(self, game_id):
        return self.registry[game_id]
    
    def removeById(self, game_id):
        if (game_id in self.registry): self.registry.pop(game_id)
    
    #for now, list all ships - this can be made into a filter for
    #effency reasons
    def getRelevantShips(self, path):
        return self.getAllByType("Ship")
    
