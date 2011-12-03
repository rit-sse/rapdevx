class Unit:
    '''
    '''

    def __init__(self, abilities, maxhp, owning_player, types, location):
        '''
        Constructor.
        
        gid - The unit's id.
        abilities - A list of Ability objects which indicate what abilities are
                    supported by the unit.
        maxhp - An integer which contains the unit's maximum health value.
        hp - An integer which contains the unit's current health value.
        owning_player - An integer refering to the player which owns this unit.
        types - List of strings which tell what kind of thing the unit is.
        location - The unit's location.
        '''

        self.gid = None #set on registry
        self.abilities = abilities
        self.maxhp = maxhp
        self.hp = maxhp # The unit's health is full upon unit creation
        self.owning_player = owning_player
        self.types = types
        self.location = location
    
    def getMaxHP(self):
        '''
        Get the unit's maximum health.
        '''
        return self.maxhp
        
    def getAbilities(self):
        '''
        Get the unit's abilities.
        '''
        return self.abilities
        
    def getHP(self):
        '''
        Get the unit's current health.
        '''
        return self.hp
    
    def setHP(self, hp):
        '''
        Set the unit's current health.
        '''
        self.hp = hp
    
    def getTypes(self):
        '''
        Retrieve the list of type strings associated with this unit.
        '''
        return self.types
        
    def getPlayer(self):
        '''
        Get an integer which references the player that owns this unit.
        '''
        return self.owning_player

    def getLocation(self):
        '''
        Get the unit's location.
        '''
        return self.location

class Ability:
    '''
    '''

    def __init__(self, radius, name, default_damage, special_damages):
        self.radius = radius
        self.name = name
        self.default_damage = default_damage
        self.special_damages = special_damages
        self.gid = None #set on registry
    
    # 
    def damageForTypes(self,type_list):
        '''
        '''
        for type in type_list:
            if type in self.special_damages:
                return self.special_damages[type]
        
        return self.default_damage

    # Get the radius.   
    def getRadius(self):
        '''
        '''
        return self.radius
        
    # Get a DTO object containing Ability data.
    def to_dto(self):
        '''
        '''
        dto = DTO_Ability(self.radius, self.name, self.default_damage, self.special_damages, self.gid)
        return dto
    
class Turn:
    '''
    '''

    def __init__(self, turn_num):
        self.attack = AttackTurn(turn_num)
        self.move = MoveTurn(turn_num)
        self.gid = None #set on registry
    
class MoveTurn:
    '''
    '''

    def __init__(self, turn_num):
        self.gid = None #set on registry
        self.turn_num = turn_num
        
    def addMoveOrder(self, move_order, calling_player):
        '''
        '''
        pass
    
    def deleteMoveOrder(self, move_order_gid, calling_player):
        '''
        '''
        pass

    def getPlayerMoveList(self, calling_player):
        '''
        '''
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
        
    def collisionCheck(self, playerShip1, playerShip2, radius1, radius2, endLocation):
        #A = playerShip1.location
        #B = playerShip2.location
        A = playerShip1
        B = playerShip2
        C = endLocation

        def distance(point1, point2):
            return ((point1[0] - point2[0])**2 + (point1[1] - point2[1])**2)**0.5
        
        K = ((distance(A,B)**2-distance(B,C)**2-distance(C,A)**2)/(-2*distance(C,A))) / distance(A,C)
        Z = {(A[0]+B[0])*(K),(A[1]+B[1])*(K)}

        if (distance(A,Z) - distance(A,C)) > 0:
            if distance(A,B) < (radius1 + radius2):
                return True
            else:
                return False

        else:
            if distance(Z,B) >= (radius1 + radius2):
                return False
            else:
                return True
        
class AttackTurn:
    '''
    '''

    def __init__(self, turn_num):
        '''
        turn_num - integer referencing the turn where this attack occured.
        '''
        self.gid = None #set on registry
        self.turn_num = turn_num

    def addAttackOrder(self, move_order, calling_player):
        '''
        Submit a player's attack. This method assumes that the move order has
        already been registered.

        move_order - AbilityUseOrder object for the ability user by the attacker.
        calling_player - integer referencing the attacker's player id.
        '''
        # Keep track of player's attack list
        # add code/data structures to support keeping track of adding/deleting
    
    def deleteAttackOrder(self, move_order_gid, calling_player):
        pass

    def getPlayerMoveList(self, calling_player):
        '''
        Get a list containing a player's moves.

        calling_player - integer referencing a player id.
        '''
        pass
    
    #any existing ability order should be evaluated
    #(going round robin on submitting players, in order)
    #remove ships that are destroyed, if not then set their health
    #lower
    def execute(self, registry):
        '''
        '''
        pass

    def getResults(self):
        '''
        '''
        pass

    def getTurnNum( self ):
        '''
        '''
        pass

    def setTurnNum( self, turn_num ):
        '''
        Constructor.
        
        gid - The unit's id.
        abilities - A list of Ability objects which indicate what abilities are
                    supported by the unit.
        maxhp - An integer which contains the unit's maximum health value.
        hp - An integer which contains the unit's current health value.
        owning_player - An integer refering to the player which owns this unit.
        types - List of strings which tell what kind of thing the unit is.
        location - The unit's location.
        '''

        self.gid = None #set on registry
        self.abilities = abilities
        self.maxhp = maxhp
        self.hp = maxhp # The unit's health is full upon unit creation
        self.owning_player = owning_player
        self.types = types
        self.location = location
    
    def getMaxHP(self):
        '''
        Get the unit's maximum health.
        '''
        return self.maxhp
        
    def getAbilities(self):
        '''
        Get the unit's abilities.
        '''
        return self.abilities
        
    def getHP(self):
        '''
        Get the unit's current health.
        '''
        return self.hp
    
    def setHP(self, hp):
        '''
        Set the unit's current health.
        '''
        self.hp = hp
    
    def getTypes(self):
        '''
        Retrieve the list of type strings associated with this unit.
        '''
        return self.types
        
    def getPlayer(self):
        '''
        Get an integer which references the player that owns this unit.
        '''
        return self.owning_player

    def getLocation(self):
        '''
        Get the unit's location.
        '''
        return self.location

class Ability:
    '''
    An object containing information about a unit's ability, which can be
    an "offensive" attack or a "healing" attack.
    '''

    def __init__(self, radius, name, default_damage, special_damages):
        '''
        Constructor.

        radius - An integer containing the ability's range. In order for an
                 ability to be usable, a unit must be 
        name - A string containing the ability's name.
        default_damage - An integer containing the amount of damage that this
                         move will normally do.
        special_damages - A dictionary which maps unit type strings to integer
                          special damage values.
        gid - The unit's id.
        '''
        self.radius = radius
        self.name = name
        self.default_damage = default_damage
        self.special_damages = special_damages
        self.gid = None #set on registry
    
    def damageForTypes(self,type_list):
        '''
        Retrieve the damage value, taking unit types into account.

        type_list - A list of strings indicating the types associated with
                    a unit.
        '''
        for type in type_list:
            if(type in self.special_damages):
                return self.special_damages[type]
        
        return self.default_damage
 
    def getRadius(self):
        '''
        Get the radius. 
        '''
        return self.radius
        
    def to_dto(self):
        '''
        Get a DTO object containing Ability data.
        '''
        dto = DTO_Ability(self.radius, self.name, self.default_damage, self.special_damages, self.gid)
        return dto
    
class Turn:
    '''
    '''

    def __init__(self, turn_num):
        self.attack = AttackTurn(turn_num)
        self.move = MoveTurn(turn_num)
        self.gid = None #set on registry
    
class MoveTurn:
    '''
    '''

    def __init__(self, turn_num):
        self.gid = None #set on registry
        self.turn_num = turn_num
        
    def addMoveOrder(self, move_order, calling_player):
        '''
        '''
        pass
    
    def deleteMoveOrder(self, move_order_gid, calling_player):
        '''
        '''
        pass

    def getPlayerMoveList(self, calling_player):
        '''
        '''
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
        
    def collisionCheck(self, player1Ship, player2Ship, radius1, radius2, endLocation):
        #A = playerShip1.location
        #B = playerShip2.location
        A = playerShip1
        B = playerShip2
        C = endLocation

        def distance(point1, point2):
            return ((point1[0] - point2[0])**2 + (point1[1] - point2[1])**2)**0.5
        
        K = ((C**2-A**2-B**2)/(-2*B)) / distance(A,C)
        Z = {(A[0]+B[0])*(K),(A[1]+B[1])*(K)}

        if (distance(A,Z) - distance(A,C)) > 0:
            if distance(A,B) < (radius1 + radius2):
                return True
            else:
                return False

        else:
            if distance(Z,B) >= (radius1 + radius2):
                return False
            else:
                return True
        
class AttackTurn:
    '''
    '''

    def __init__(self, turn_num):
        '''
        turn_num - integer referencing the turn where this attack occured.
        '''
        self.gid = None #set on registry
        self.turn_num = turn_num

    def addAttackOrder(self, move_order, calling_player):
        '''
        Submit a player's attack. This method assumes that the move order has
        already been registered.

        move_order - AbilityUseOrder object for the ability user by the attacker.
        calling_player - integer referencing the attacker's player id.
        '''
        # Keep track of player's attack list
        # add code/data structures to support keeping track of adding/deleting
    
    def deleteAttackOrder(self, move_order_gid, calling_player):
        pass

    def getPlayerMoveList(self, calling_player):
        '''
        Get a list containing a player's moves.

        calling_player - integer referencing a player id.
        '''
        pass
    
    #any existing ability order should be evaluated
    #(going round robin on submitting players, in order)
    #remove ships that are destroyed, if not then set their health
    #lower
    def execute(self, registry):
        '''
        '''
        pass

    def getResults(self):
        '''
        '''
        pass

    def getTurnNum( self ):
        '''
        '''
        pass

    def setTurnNum( self, turn_num ):
        '''
        '''
