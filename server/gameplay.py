class Unit:
    '''

    '''

    def __init__(self, abilities, maxhp, owning_player, types, location, radius):
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
        self.radius = radius
    
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
    #R3: Stop tangent to offending unit
    def execute(self, registry):
        pass
        
    def getResults(self):
        pass
        
    def collisionCheck(self, unit1, unit2, radius1, radius2, end_loc):
        '''
        '''
        unit1_loc = unit1.location
        unit2_loc = unit2.location

        def distance(point1, point2):
            return ((point1[0] - point2[0])**2 + (point1[1] - point2[1])**2)**0.5
        
        length_ratio = ((distance(unit1_loc,unit2_loc)**2-distance(unit2_loc,end_loc)**2-distance(end_loc,unit1_loc)**2)/(-2*distance(end_loc,unit1_loc))) / distance(unit1_loc,end_loc)
        test_point = ((unit1_loc[0]+unit2_loc[0])*(length_ratio),(unit1_loc[1]+unit2_loc[1])*(length_ratio))

        if (distance(unit1_loc,test_point) - distance(unit1_loc,end_loc)) > 0:
            if distance(unit1_loc,unit2_loc) < (radius1 + radius2):
                return True
            else:
                return False

        else:
            if distance(test_point,unit2_loc) >= (radius1 + radius2):
                return False
            else:
                return True
        
class AttackTurn:
    '''
    Represents a player's turn if they choose to attack. 
    '''

    def __init__(self, turn_num):
        '''
        Constructor.

        turn_num - integer referencing the turn where this attack occured.
        '''
        self.gid = None #set on registry
        self.turn_num = turn_num
        self.attack_list = []

    def addAttackOrder(self, attack_order, calling_player):
        '''
        Submit a player's attack. This method assumes that the move order has
        already been registered.

        attack_order - AbilityUseOrder object for the ability user by the attacker.
        calling_player - integer referencing the attacker's player id.
        '''
        # Associate an attack order with the player who made the attack
        playerAttack = { attack_order, calling_player }

        # TODO: Validate that the client is actually in range/not lying
        self.attack_list.append( playerAttack )
    
    def deleteAttackOrder(self, attack_order_gid, calling_player):
        '''
        Remove the specified player's move from the attack list.

        attack_order_gid - The id of the move order.
        calling_player - An integer reference to the player who made the move. 
        '''
        for attack in attack_list:
            attack_order = attack[0].gid
            playerId = attack[1]

            attackOrderMatches = attack_order == attack_order_gid
            playerIdMatches = playerId == calling_player

            if( attackOrderMatches and playerIdMatches ):
                attack_list.remove( attack )
                return

        pass

    def getPlayerMoveList(self, calling_player):
        '''
        Get a list of the specified player's moves.

        calling_player - an integer referencing a player id.
        '''
        pass
    
    def execute(self, registry):
        '''
        Any existing ability order should be evaluated (going round robin on
        submitting players, in order) remove units that are destroyed, if not
        then set their health lower.

        registry - The game's Registry object. This has all of the unit data.
        '''

        #move the 
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
        
class UnitClass:
    def __init__(self, types, abilities, maxhp, radius, placement_cost):
        self.types = types
        self.abilities = abilities
        self.maxhp = maxhp
        self.radius = radius
        self.placement_cost = placement_cost  
        self.gid = None
    
    def makeUnit(self,location,player_id):
        unit = Unit(self.abilities[:],self.maxhp,player_id, self.types, location, self.radius)
        return unit
