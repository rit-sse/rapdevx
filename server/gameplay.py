from dto import *

def swizzle(lists):
    lists = [x[:] for x in lists]
    results = []
    i = 0
    while lists:
        print(lists)
        if lists[i]:
            results.append(lists[i][0])
            lists[i] = lists[i][1:]
            i = (i+1)
        else:
            lists.pop(i)
            if len(lists)==0:
                return results
                i = i % len(lists)
    return results
    
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
        radius - An integer which represents a units size
        '''

        self.gid = None #set on registry
        self.abilities = abilities
        self.maxhp = maxhp
        self.hp = maxhp # The unit's health is full upon unit creation
        self.owning_player = owning_player
        self.types = types
        self.location = location
        self.radius = radius
        self.classid = None
    def setClassid(self,classid):
        self.classid = classid
    
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
        
    def to_dto(self):
        return DTO_Unit(self.owning_player, self.hp, self.classid, self.gid)
    


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
        self.turn_num = turn_num
        self.attack = AttackTurn(turn_num)
        self.move = MoveTurn(turn_num)
        self.gid = None #set on registry
    
class MoveTurn:
    '''
    '''

    def __init__(self, turn_num):
        self.gid = None #set on registry
        self.turn_num = turn_num
        self.player_move_list = {}
        
    def addMoveOrder(self, move_order, calling_player, registry):
        '''
        '''
        if calling_player not in self.player_move_list:
            self.player_move_list[calling_player] = []

        moveOrderObj = MoveOrder(move_order.unitid, move_order.path) 
        self.player_move_list[calling_player].append( moveOrderObj )
        registry.register(moveOrderObj)
    
    def deleteMoveOrder(self, move_order_gid, calling_player, registry):
        '''
        '''
        move_order = registry.getById(move_order_gid)
        registry.removeById(move_order_gid)
        
        self.player_attack_lists[calling_player].remove(move_order)

    def getPlayerMoveList(self, calling_player, registry):
        '''
        '''
        return [x.to_dto() for x in self.player_move_lists[calling_player]]
        
    #any existing move orders should be evaluated
    #(going round robin on submitting players, in order)
    #R1: No collision checking
    #R2: Stop short of offending segment
    #R3: Stop tangent to offending unit
    def execute(self, registry):
        pass
        
    def getResults(self):
        pass
        
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
        self.player_attack_lists = {}
        self.results = None

    def addAttackOrder(self, dto_attack_order, calling_player, registry):
        '''
        Submit a player's attack. This method assumes that the move order has
        already been registered.

        attack_order - DTO_AbilityUseOrder object for the ability user by the attacker.
        calling_player - integer referencing the attacker's player id.
        
        registry - the ID registry to add the move to for later reference
        '''
        #setup a list of attacks for a player if there isn't one yet
        if calling_player not in self.player_attack_lists:
            self.player_attack_lists[calling_player]=[]


        order = AttackOrder(dto_attack_order.srcid,dto_attack_order.targetid, dto_attack_order.ability)

        target = registry.getById(dto_attack_order.srcid)

        if registry.getById(dto_attack_order.srcid) == None:
            raise Exception("Attacking ship not in registry")

        if registry.getById(dto_attack_order.targetid) == None:
            raise Exception("Target Ship not in registry")
        
        if registry.getById(dto_attack_order.ability) == None:
            raise Exception("Ability not in registry")

        if calling_player != target.getPlayer():
            raise Exception("Player does not own ship")

        registry.register(order)
        self.player_attack_lists.append(order)
        
    def deleteAttackOrder(self, move_order_gid, calling_player, registry):
        '''
        Remove the specified player's move from the attack list.

        move_order_gid - The id of the move order.
        calling_player - An integer reference to the player who made the move. 
        
        registry - the ID registry to remove the move from
        '''
        move_order = registry.getById(move_order_gid)
        registry.removeById(move_order_gid)
        
        self.player_attack_lists[calling_player].remove(move_order)

    def getPlayerAttackList(self, calling_player, registry):
        '''
        Get a list of the specified player's moves' ID's.

        calling_player - an integer referencing a player id.
        
        registry - the ID registry that the moves are in
        '''
        return [x.to_dto() for x in self.player_attack_lists[calling_player]]
    
    def execute(self, registry):
        '''
        Any existing ability order should be evaluated (going round robin on
        submitting players, in order) remove units that are destroyed, if not
        then set their health lower.

        registry - The game's Registry object. This has all of the unit data.
        '''
        
        player_nums = sorted(self.player_attack_lists.keys())
        #todo: "rotate" player nums based on the turn number, so 
        #a different player gets to go "first" every turn
        
        lists = [self.player_attack_lists[x] for x in player_nums]
        combined_list = swizzle(lists)
        
        self.results = []
        
        for attack_order in combined_list:
            attacker = registry.getById(attack_order.srcid)
            target = registry.getById(attack_order.targetid)
            
            if None not in [attacker,target]:
                self.results.append(attack_order)
                ability = registry.getById(attack_order.ability)
                damage = ability.damageForTypes(target.types)
                target.setHP(target.getHP()-damage)
                if target.getHP()<=0:
                    registry.removeById(target.gid)
            
    def getResults(self):
        return self.results

        
class UnitClass:
    def __init__(self, types, abilities, maxhp, radius, placement_cost, image, name):
        self.types = types
        self.abilities = abilities
        self.maxhp = maxhp
        self.radius = radius
        self.placement_cost = placement_cost  
        self.name = name
        self.gid = None
        self.image = image
    
    def makeUnit(self,location,player_id):
        unit = Unit(self.abilities[:],self.maxhp,player_id, self.types, location, self.radius)
        unit.setClassid(self.gid)
        return unit
    
    def to_dto(self):
    #types, abilities, maxhp, radius, placement_cost, imageid, gid):
        return DTO_ShipClass(self.types, [x.to_dto() for x in self.abilities], 
            self.maxhp, self.radius, self.placement_cost, self.image.gid, self.gid)
            
class Image:
    def __init__(self, filename):
        self.contents = open(filename).read()
        self.gid = None
    
    def to_dto(self):
        return DTO_AssetImage(self.contents,self.gid)
        
        
class AttackOrder:
    def __init__(self, srcid, targetid, ability):
        self.srcid = srcid
        self.targetid = targetid
        self.ability = ability
        
        self.gid = None
        
    def to_dto(self):
        return DTO_AbilityUseOrder(self.srcid, self.targetid, self.ability, self.gid)
        
class MoveOrder:
    def __init__(self, shipid, path):
        self.shipid = shipid
        self.path = path
        self.gid = None
        
    def to_dto(self):
        return DTO_MovementOrder(self.shipid, self.path, self.gid)
        
