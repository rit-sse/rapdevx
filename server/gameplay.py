class Unit:
    def __init__(self, abilities, maxhp, owning_player, types, location):
        self.id = None #set on registry
        pass
        
    def getMaxHP(self):
        pass
        
    def getAbilities(self):
        pass
        
    def getHP(self):
        pass
    
    def setHP(self):
        pass
    
    def getTypes(self):
        pass
        
    def getPlayer(self):
        pass

    def getLocation(self):
        pass
        
class Ability:
    def __init__(self, radius, name, default_damage, special_damages):
        self.radius = radius
        self.name = name
        self.default_damage = default_damage
        self.special_damages = special_damages
        self.id = None #set on registry
        
    def damageForTypes(self,type_list):
        pass
        
    def getRadius(self):
        pass
        
    def to_dto(self):
        pass
    
class Turn:
    def __init__(self, turn_num):
        self.attack = AttackTurn(turn_num)
        self.move = MoveTurn(turn_num)
        self.id = None #set on registry
    
class MoveTurn:
    def __init__(self, turn_num):
        self.id = None #set on registry
        self.turn_num = turn_num
        
    def addMoveOrder(self, move_order):
        pass
    
    def deleteMoveOrder(self, move_order_id):
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
        self.id = None #set on registry
        self.turn_num = turn_num
        
    def addAttackOrder(self, move_order):
        pass
    
    def deleteAttackOrder(self, move_order_id):
        pass
    
    #any existing ability order should be evaluated
    #(going round robin on submitting players, in order)
    #remove ships that are destroyed, if not then set their health
    #lower
    def execute(self, registry):
        pass

    def getResults(self):
        pass
