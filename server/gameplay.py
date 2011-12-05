from dto import *
import geometry

def swizzle(lists):
    ''' swizzle: List of lists -> List
    Returns a list whose elements are alternating elements of the lists passed.
    Example: swizzle( [0, 1, 2] , [3, 4, 5] ) -> [0, 3, 1, 4, 2, 5]
    '''
    nonEmptyLists = []
    for x in range(len(lists)):
        if not len(lists[x]) == 0:
            # Remove empty lists; they fizzle the swizzle (infinite loop) and don't affect the result
            nonEmptyLists.append(lists[x])
    lists = nonEmptyLists
    results = []
    i = 0
    while lists:
        if lists[i]:
            results.append(lists[i][0])
            lists[i] = lists[i][1:]
            if lists[i]==[]:
                lists.pop(i)
            else:
                i = (i+1)
        if(len(lists)==0):
            break
        i = i% len(lists)
            
    return results
    
class Unit:
    '''

    '''

    def __init__(self, abilities=[], maxhp=100, owning_player=0, types=[], location=(0,0), radius=1):
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
        radius - An integer which represents a unit's size
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
    
    def setLocation(self, location):
        '''
        Set the unit's location.
        '''
        self.location = location

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
        return DTO_Unit(self.owning_player, self.hp, self.classid, self.location, self.gid)

class Ability:
    '''
    An object containing information about a unit's ability, which can be
    an "offensive" attack or a "healing" attack.
    '''

    def __init__(self, radius=50, name="default", default_damage=10, special_damages={}):
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
        self.results = {}
        
    def addMoveOrder(self, move_order, calling_player, registry):
        '''
        '''
        if calling_player not in self.player_move_list:
            self.player_move_list[calling_player] = []
        print("add")

        
        
        moveOrderObj = MoveOrder(move_order.unitid, move_order.path) 

        #unit must exist:
        unit = registry.getById(moveOrderObj.unitid)
        
        if unit == None:
            raise Exception("unit does not exist")
            
        #path must go somewhere
        if len(moveOrderObj.path)<2:
            raise Exception("Path is not a path")
        
        #path must start at current location:
        if moveOrderObj.path[0]!=unit.location:
            raise Exception("Path does not start at unit")
        
        self.player_move_list[calling_player].append( moveOrderObj )
        registry.register(moveOrderObj)
    
    def deleteMoveOrder(self, move_order_gid, calling_player, registry):
        '''
        '''
        print("MOVE_GID:", move_order_gid)
        move_order = registry.getById(move_order_gid)
        registry.removeById(move_order_gid)
        
        print("MOVE_LIST_FOR_CALLING_PLAYER:", self.player_move_list[calling_player])
        print("MOVE_ORDER TO BE REMOVED:", move_order)

        self.player_move_list[calling_player].remove(move_order)

    def getPlayerMoveList(self, calling_player, registry):
        '''
        '''
        return [x.to_dto() for x in self.player_move_list[calling_player]]
        
    def execute(self, registry):
        '''
        Evaluate move orders.

        #any existing move orders should be evaluated
        #(going round robin on submitting players, in order)
        #R1: No collision checking
        #R2: Stop short of offending segment
        #R3: Stop tangent to offending unit
        '''

        player_nums = sorted(self.player_move_list.keys())
        # "rotate" player nums based on the turn number, so 
        # a different player gets to go "first" every turn
        num_of_players = len(player_nums)
        if not num_of_players:
            return
        num_of_shuffles = self.turn_num % num_of_players
        for x in range(num_of_shuffles):
            player_num.append(player_num.pop(0))
        
        lists = [self.player_move_list[x] for x in player_nums]
        combined_list = swizzle(lists)
        
        self.results = {}
        
        for playerMove in combined_list:
            
            unit = registry.getById(playerMove.unitid)
            path_pairs = [(playerMove.path[i-1],playerMove.path[i]) for i in range(1,len(playerMove.path))]
            path_taken = [playerMove.path[0]]
            for start,end in path_pairs:
                intersecting = []
                for other in registry.getAllByType(Unit):
                    if unit!=other:
                        if geometry.shipsWillCollideOnSegment(start, end, unit, other):
                            intersecting.append(other)
                
                if not intersecting:
                    unit.setLocation(end)
                    path_taken.append(end)
                else:
                    shortest = geometry.distance(start,end)
                    shortest_point = end
                    
                    for other in intersecting:
                        pt = geometry.whereWillItStop(start, end, unit, other)
                        print(pt)
                        if geometry.distance(start,pt)<shortest:
                            shortest = geometry.distance(start,pt)
                            print("shortest",shortest)
                            shortest_point = pt
                        
                    print("using:",shortest_point,"on:",unit.gid)
                    unit.setLocation(shortest_point)
                    path_taken.append(shortest_point)
                    break
            
            self.results[unit.gid] = path_taken
        
    def getResults(self):
        return DTO_Results(self.results)
        
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
        self.registered_ids = {}

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

        source = dto_attack_order.srcid
        tar = dto_attack_order.targetid
        
        order = AttackOrder(source,dto_attack_order.targetid, dto_attack_order.ability)

        target = registry.getById(source)

        if registry.getById(source) == None:
            raise Exception("Attacking ship not in registry")

        if registry.getById(dto_attack_order.targetid) == None:
            raise Exception("Target Ship not in registry")
        
        if registry.getById(dto_attack_order.ability) == None:
            raise Exception("Ability not in registry")

        if calling_player != target.getPlayer():
            raise Exception("Player does not own ship")

        if dto_attack_order.ability in source.getAbilities():
            raise Exception("Ship does not have ability")

        if source.getLocation() > tar.getLocation():
            dist = source.getLocation() - tar.getLocation()
        else:
            dist = tar.getLocation() - source.getLocation()
        if dist > dto_attack_order.ability.getRadius():
            raise Exception("Target is not in range")

        if source in self.registered_ids:
            raise Exception("Unit already registered an attack")
        else:
            self.registered_ids.append(calling_player)
           
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

        if calling_player != move_order.srcid:
            raise Exception("Move does not belong to player")
        else:
            if calling_player in self.registered_ids:
                self.registered.remove(calling_player)
            else:
                raise Exception("Player doesn't have a move to remove")
        
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

        # Shuffle the play priority based on turn num (shifting which player moves first)
        num_of_players = len(player_nums)
        if not num_of_players:
            return
        num_of_shuffles = self.turn_num % num_of_players
        for x in range(num_of_shuffles):
            player_nums.append(player_nums.pop(0))
        
        lists = [self.player_attack_lists[x] for x in player_nums]
        combined_list = swizzle(lists)
        
        self.results = []
        
        for attack_order in combined_list:
            attacker = registry.getById(attack_order.srcid)
            target = registry.getById(attack_order.targetid)
            
            if None not in [attacker,target]:
                self.results.append(attack_order.to_dto())
                ability = registry.getById(attack_order.ability)
                damage = ability.damageForTypes(target.types)
                target.setHP(target.getHP()-damage)
                if target.getHP()<=0:
                    registry.removeById(target.gid)
            
    def getResults(self):
        return DTO_AttackResults(self.results)

        
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
        self.contents = open(filename,'rb').read()
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
    def __init__(self, unitid, path):
        self.unitid = unitid
        self.path = path
        self.gid = None
        
    def __eq__(self, other):
        print("CHECKING TYPE OF OTHER:", type(other))
        return self.gid == other.gid

    def to_dto(self):
        return DTO_MovementOrder(self.unitid, self.path, self.gid)
        
