# Data Transfer Objects - Primarily used to communicate between API team 
# and the Server Objects. Any class in here is to be exclusively as a struct
import json

class DTO_AssetImage:
    #files is a
    #name is a
    #gid is a
    def __init__(self, file, name, gid):
        self.file = file
        self.name = name
        self.gid = gid
    
    def encode(self):
        r = {}
        r['file'] = self.file
        r['name'] = self.name
        r['gid'] = self.gid
        return json.dumps(r)

class DTO_ShipClass:
    #types is a
    #abilities is a
    #maxhp is a
    #radius is a
    #placement_cost is a
    #gid is a
    def __init__(self, types, abilities, maxhp, radius, placement_cost, gid):
        self.types = types
        self.abilities = abilities
        self.maxhp = maxhp
        self.radius = radius
        self.placement_cost = placement_cost  
        self.gid = gid

    def encode(self):
        r = {}
        r['types'] = self.types
        r['abilities'] = self.abilities
        r['maxhp'] = self.maxhp
        r['radius'] = self.radius
        r['placement_cost'] = self.placement_cost
        r['gid'] = self.gid
        return json.dumps(r)

class DTO_Ability:
    #radius is a
    #name is a
    #default_damage is an int
    #special_damage is a map from string to int
    #gid is a 
    def __init__(self, radius, name, default_damage, special_damages, gid):
        self.radius = radius
        self.name = name
        self.default_damage = default_damage
        self.special_damages = special_damages
        self.gid = gid
        
    def encode(self):
        r = {}
        r['radius'] = self.radius
        r['name'] = self.name
        r['default_damage'] = self.default_damage
        r['special_damages'] = self.special_damages
        r['gid'] = self.gid
        return json.dumps(r)
    
class DTO_Assets:
    #width is an int
    #height is an int
    #ship_classes is a list of ShipClass instances
    #   See DTO_ShipClasses
    #images is a list of AssetImage
    #   See DTO_AssetImage
    #abilities is a list of abilities
    #   See DTO_Ability
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

class DTO_ShipPlacement:
    #x is an int
    #y is an int
    #classid is an int
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
    
class DTO_Results:
    #results = map of ship id to PATH of ship
    #   PATH = list of x,y tuples
    def __init__(self,results):
        self.results = results

    def encode(self):
        r = {}
        r['results'] = self.results
        return json.dumps(r)
    
class DTO_MovementOrder:
    #unitid is an int
    #PATH is a list of x,y tuples
    #gid is a
    def __init__(self, unitid, path, gid):
        self.unitid = unitid
        self.path = path
        self.gid = gid

class DTO_AbilityUseOrder:
    #srcid is an int
    #targetid is an int
    #ability is a string
    #gid is a
    def __init__(self, srcid, targetid, ability, gid):
        self.srcid = srcid
        self.targetid = targetid
        self.ability = ability
        self.gid = gid
        
    def encode(self):
        r = {}
        r['srcid'] = self.srcid
        r['targetid'] = self.targetid
        r['ability'] = self.ability
        r['gid'] = self.gid
        return json.dumps(r)

class DTO_Ship:
    #player_num is a
    #hp is a
    #classid is a
    #gid is a
    def __init__(self, player_num, hp, classid, gid):
        self.player_num = player_num
        self.hp = hp
        self.classid = classid
        self.gid = gid
        
class DTO_Status:
    #turn is an int
    #phase is an int
    #me is an int
    #   me is the playerid of the asking player
    def __init__(self, turn, phase, player_list, me):
        self.turn = turn
        self.phase = phase
        self.player_list = player_list
        self.me = me

#WARNING/TO-DO: Some of the attributes which are not basic python objects may be junk    
def JSON_Construct_DTO_AssetImage(jsonstring)
    attribute_dictionary = json.loads(jsonstring)
    return DTO_AssetImage(attribute_dictionary.pop('file'), attribute_dictionary.pop('name'), attribute_dictionary.pop('gid'))

def JSON_Construct_DTO_ShipClass(jsonstring)
    attribute_dictionary = json.loads(jsonstring)
    return DTO_ShipClass(attribute_dictionary.pop('types'), attribute_dictionary.pop('abilities'), attribute_dictionary.pop('maxhp'), attribute_dictionary.pop('radius'), attribute_dictionary.pop('placement_cost'), attribute_dictionary.pop('gid'))

def JSON_Construct_DTO_Ability(jsonstring)   
    attribute_dictionary = json.loads(jsonstring)
    return DTO_Ability(attribute_dictionary.pop('radius'), attribute_dictionary.pop('name'), attribute_dictionary.pop('default_damage'), attribute_dictionary.pop('special_damage'), attribute_dictionary.pop('gid'))

def JSON_Construct_DTO_Assets(jsonstring)
    attribute_dictionary = json.loads(jsonstring)
    return DTO_Assets(attribute_dictionary.pop('width'),attribute_dictionary.pop('height'),attribute_dictionary.pop('ship_classes'),attribute_dictionary.pop('images'), attribute_dictionary.pop('abilities'))

def JSON_Construct_DTO_ShipPlacement(jsonstring)
    attribute_dictionary = json.loads(jsonstring)
    return DTO_ShipPlacement(attribute_dictionary.pop('x'),attribute_dictionary.pop('y'),attribute_dictionary.pop('classid'))

def JSON_Construct_DTO_Results(jsonstring)
    attribute_dictionary = json.loads(jsonstring)
    return DTO_Results(attribute_dictionary.pop('results'))

def JSON_Construct_DTO_MovementOrder(jsonstring)
    attribute_dictionary = json.loads(jsonstring)
    return DTO_MovementOrder(attribute_dictionary.pop('unitid'), attribute_dictionary.pop('path'), attribute_dictionary.pop('gid'))

def JSON_Construct_DTO_AbilityUseOrder(jsonstring)
    attribute_dictionary = json.loads(jsonstring)
    return DTO_AbilityUseOrder(attribute_dictionary.pop('srcid'),attribute_dictionary.pop('targetid'),attribute_dictionary.pop('ability'),attribute_dictionary.pop('gid'))

def JSON_Construct_DTO_Ship(jsonstring)
    attribute_dictionary = json.loads(jsonstring)
    return DTO_SHIP(attribute_dictionary.pop('player_num'),attribute_dictionary.pop('hp'),attribute_dictionary.pop('classid'),attribute_dictionary.pop('gid'))

def JSON_Construct_DTO_Status(jsonstring)
    attribute_dictionary = json.loads(jsonstring)
    return DTO_Status(attribute_dictionary.pop('turn'), attribute_dictionary.pop('phase'), attribute_dictionary.pop('player_list'), attribute_dictionary.pop('me'))
