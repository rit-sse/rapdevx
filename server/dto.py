# Data Transfer Objects - Primarily used to communicate between API team 
# and the Server Objects. Any class in here is to be exclusively as a struct
import json

class DTOEncoder(json.JSONEncoder):
    def default(self, obj):
        r = {}
        if isinstance(obj, AssetImage):
            r['file'] = obj.file
            r['name'] = obj.name
            r['id'] = obj.id
                
        return json.JSONEncoder.encode(r)

class AssetImage:
    def __init__(self, file, name, registry, id):
        self.file = file
        self.name = name
        self.id = id

    def encode(self):
        r = {}
        r['file'] = self.file
        r['name'] = self.name
        r['id'] = self.id
        return json.dumps(r)

class DTO_ShipClass:
    def __init__(self, types, abilities, maxhp, radius, placement_cost):
        self.types = types
        self.abilities = abilities
        self.maxhp = maxhp
        self.radius = radius
        self.placement_cost = placement_cost  
        self.id = id

    def encode(self):
        r = {}
        r['types'] = self.types
        r['abilities'] = self.abilities
        r['maxhp'] = self.maxhp
        r['radius'] = self.radius
        r['placement_cost'] = self.placement_cost
        r['id'] = self.id
        return json.dumps(r)

class DTO_Ability:
    #default_damage is int
    #special_damage map from string to int
    def __init__(self, radius, name, default_damage, special_damages, id):
        self.radius = radius
        self.name = name
        self.default_damage = default_damage
        self.special_damages = special_damages
        self.id = id
        
    def encode(self):
        r = {}
        r['radius'] = self.radius
        r['name'] = self.name
        r['default_damage'] = self.default_damage
        r['special_damages'] = self.special_damages
        r['id'] = self.id
        return json.dumps(r)
    
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
      
    def encode(self):
        r = {}
        r['width'] = self.width
        r['height'] = self.height
        r['ship_classes'] = self.ship_classes
        r['images'] = self.images
        return json.dumps(r)

class ShipPlacement:
    #x,y, classid are all ints
    def __init__(self, x, y, classid):
        self.x = x
        self.y = y
        self.classid = classid
        
    def encode(self):
        r = {}
        r['x'] = self.x
        r['y'] = self.y
        r['classid'] = self.classid
        return json.dumps(r)
    
class Results:
    #results = map of ship id to PATH of ship
    #PATH = list of x,y tuples
    def __init__(self,results):
        self.results = results

    def encode(self):
        r = {}
        r['results'] = self.results
        return json.dumps(r)
    
class MovementOrder:
    #unitid = int
    #PATH = list of x,y tuples
    def __init__(self, unitid, path, id):
        self.unitid = unitid
        self.path = path
        self.id = id

    def encode(self):
        r = {}
        r['unitid'] = self.unitid
        r['path'] = self.path
        r['id'] = self.id
        return json.dumps(r)

class AbilityUseOrder:
    #srcid,targetid = int
    #ability = string
    def __init__(self, srcid, targetid, ability, id):
        self.srcid = srcid
        self.targetid = targetid
        self.ability = ability
        self.id = id
        
    def encode(self):
        r = {}
        r['srcid'] = self.srcid
        r['targetid'] = self.targetid
        r['ability'] = self.ability
        r['id'] = self.id
        return json.dumps(r)

class DTO_Ship:
    def __init__(self, player_num, hp, classid, id):
        self.player_num = player_num
        self.hp = hp
        self.classid = classid
        self.id = id
        
class Status:
    #turn, phase, me = ints - me is the playerid of the asking player
    def __init__(self, turn, phase, player_list, me):
        self.turn = turn
        self.phase = phase
        self.player_list = player_list
        self.me = me
