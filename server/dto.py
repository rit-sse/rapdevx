# Data Transfer Objects - Primarily used to communicate between API team 
# and the Server Objects. Any class in here is to be exclusively as a struct

class AssetImage:
    def __init__(self, file, name, registry, id):
        self.file = file
        self.name = name
        self.gid = id

class DTO_ShipClass:
    def __init__(self, types, abilities, maxhp, radius, placement_cost, gid):
        self.types = types
        self.abilities = abilities
        self.maxhp = maxhp
        self.radius = radius
        self.placement_cost = placement_cost  
        self.gid = gid

class DTO_Ability:
    #default_damage is int
    #special_damage map from string to int
    def __init__(self, radius, name, default_damage, special_damages, id):
        self.radius = radius
        self.name = name
        self.default_damage = default_damage
        self.special_damages = special_damages
        self.gid = id

class Assets:
    #width, height are ints
    #ship_classes is a list of ShipClass instances
    #images is a list of AssetImage
    #abilities is a list of abilities
    def __init__(self, width, height, ship_classes, images, abilities):
        self.width = width
        self.height = height
        self.ship_classes = ship_classes
        self.images = images

class ShipPlacement:
    #x,y, classid are all ints
    def __init__(self, x, y, classid):
        self.x = x
        self.y = y
        self.classid = classid
        
class Results:
    #results = map of ship id to PATH of ship
    #PATH = list of x,y tuples
    def __init__(self,results):
        self.results = results

class MovementOrder:
    #unitid = int
    #PATH = list of x,y tuples
    def __init__(self, unitid, path, id):
        self.unitid = unitid
        self.path = path
        self.gid = id

class AbilityUseOrder:
    #srcid,targetid = int
    #ability = string
    def __init__(self, srcid, targetid, ability, path, id):
        self.srcid = srcid
        self.targetid = targetid
        self.ability = ability
        self.gid = id
        
class DTO_Ship:
    def __init__(self, player_num, hp, classid, id):
        self.player_num = player_num
        self.hp = hp
        self.classid = classid
        self.gid = id
        
class Status:
    #turn, phase, me = ints - me is the playerid of the asking player
    def __init__(self, turn, phase, player_list, me):
        self.turn = turn
        self.phase = phase
        self.player_list = player_list
        self.me = me