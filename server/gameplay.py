class Unit:
    def __init__(self, abilities, maxhp, owning_player, types, location):
        self.gid = None #set on registry
        self.abilities = abilities
        self.maxhp = maxhp
        self.hp = maxhp # The unit's health is full upon unit creation
        self.owning_player = owning_player
        self.types = types
        self.location = location
    
    def getMaxHP(self):
        return self.maxhp
        
    def getAbilities(self):
        return self.abilities
        
    def getHP(self):
        return self.hp
    
    def setHP(self, hp):
        self.hp = hp
    
    def getTypes(self):
        return self.types
        
    def getPlayer(self):
        return self.owning_player

    def getLocation(self):
        return self.location

class Ability:
    def __init__(self, radius, name, default_damage, special_damages):
        self.radius = radius
        self.name = name
        self.default_damage = default_damage
        self.special_damages = special_damages
        self.gid = None #set on registry
    
    # 
    def damageForTypes(self,type_list):
        for type in type_list:
            if type in self.special_damages:
                return self.special_damages[type]
        
        return self.default_damage

    # Get the radius.   
    def getRadius(self):
        return self.radius
        
    # Get a DTO object containing Ability data.
    def to_dto(self):
        dto = DTO_Ability(self.radius, self.name, self.default_damage, self.special_damages, self.gid)
        return dto
    
class Turn:
    def __init__(self, turn_num):
        self.attack = AttackTurn(turn_num)
        self.move = MoveTurn(turn_num)
        self.gid = None #set on registry
    
class MoveTurn:
    def __init__(self, turn_num):
        self.gid = None #set on registry
        self.turn_num = turn_num
        
    def addMoveOrder(self, move_order, calling_player):
        pass
    
    def deleteMoveOrder(self, move_order_gid, calling_player):
        pass

    def getPlayerMoveList(self, calling_player):
        pass
        
    #any existing move orders should be evaluated
    #(going round robin on submitting players, in order)
    #R1: No collision checking
    #R2: Stop short of offending segment
    #R3: Stop tanget to offending ship
    def execute(self, registry):
        pass
        
    def getResults(self):
        pass 
        
class AttackTurn:
    def __init__(self, turn_num):
        self.gid = None #set on registry
        self.turn_num = turn_num
        
    def addAttackOrder(self, move_order, calling_player):
        pass
    
    def deleteAttackOrder(self, move_order_gid, calling_player):
        pass

    def getPlayerMoveList(self, calling_player):
        pass
    
    #any existing ability order should be evaluated
    #(going round robin on submitting players, in order)
    #remove ships that are destroyed, if not then set their health
    #lower
    def execute(self, registry):
        pass

    def getResults(self):
        pass
