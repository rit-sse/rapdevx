# Data Transfer Objects - Primarily used to communicate between API team 
# and the Server Objects. Any class in here is to be exclusively as a struct
import json

class DTO_AssetImage:
    #file is a string that contains contents of file (base64) (may change)
    #gid is a string
    def __init__(self, file, gid):
        self.file = file
        self.gid = gid
    
    def encode(self):
        r = {}
        r['file'] = self.file
        r['gid'] = self.gid
        return json.dumps(r)
    

class DTO_ShipClass:
    #abilities is a list of strings which are DTO_Ability ids
    #maxhp is an int
    #radius is an int
    #placement_cost is an int
    #imageid is an string
    #gid is a string
    def __init__(self, types, abilities, maxhp, radius, placement_cost, imageid, gid):
        self.types = types
        self.abilities = abilities
        self.maxhp = maxhp
        self.radius = radius
        self.placement_cost = placement_cost  
        self.imageid = imageid
        self.gid = gid
    
    def encode(self):
        r = {}
        r['abilities'] = self.abilities
        r['maxhp'] = self.maxhp
        r['radius'] = self.radius
        r['placement_cost'] = self.placement_cost
        r['imageid'] = self.imageid
        r['gid'] = self.gid
        return json.dumps(r)
    
        
class DTO_Ability:
    #radius is an int
    #name is a string
    #default_damage is an int
    #special_damages is a dictionary from string to int
    #gid is a string
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
    #images is a list of DTO_AssetImage objects
    #   See DTO_AssetImage
    #abilities is a list of DTO_Ability objects
    #   See DTO_Ability
    def __init__(self, width, height, ship_classes, images, abilities):
        self.width = width
        self.height = height
        self.ship_classes = ship_classes
        self.images = images
        self.abilities = abilities
        
    def encode(self):
        r = {}
        r['width'] = self.width
        r['height'] = self.height
        r['ship_classes'] = []
        for sc in self.ship_classes:
            r['ship_classes'].append(sc.encode())
        r['images'] = []
        for im in self.images:
            r['images'].append(im.encode())
        r['abilities'] = []
        for ab in self.abilities:
            r['abilities'].append(ab.encode())
        print(r)
        return json.dumps(r)
    

class DTO_ShipPlacement:
    #x is an int
    #y is an int
    #classid is a string
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
   
class DTO_AttackResults:
    #results = list of DTO_AbilityUseOrders that were executed
    def __init__(self,results):
        self.results = results

    def encode(self):
        r = {}
        r['results'] = []
        for auo in self.results:
            r['results'].append(auo.encode())
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
    #unitid is a string
    #PATH is a list of x,y tuples
    #gid is a string
    def __init__(self, unitid, path, gid):
        self.unitid = unitid
        self.path = path
        self.gid = gid

    def encode(self):
        r = {}
        r['unitid'] = self.unitid
        r['path'] = self.path
        r['gid'] = self.gid
        return json.dumps(r)
        
class DTO_AbilityUseOrder:
    #srcid is a string
    #targetid is a string
    #ability is a string
    #gid is a string
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

class DTO_Unit:
    #player_num is an int
    #hp is an int
    #classid is a string
    #gid is a string
    def __init__(self, player_num, hp, classid, gid):
        self.player_num = player_num
        self.hp = hp
        self.classid = classid
        self.gid = gid
    def encode(self):
        r = {}
        r['player_num'] = self.player_num
        r['hp'] = self.hp
        r['classid'] = self.classid
        r['gid'] = self.gid
        return json.dumps(r)
        
class DTO_Status:
    #turn is an int
    #phase is a string
    #player_list is list of strings
    #me is an int
    #   me is the playerid of the asking player
    def __init__(self, turn, phase, player_list, me):
        self.turn = turn
        self.phase = phase
        self.player_list = player_list
        self.me = me
        
    def encode(self):
        r = {}
        r['turn'] = self.turn
        r['phase'] = self.phase
        r['player_list'] = self.player_list
        r['me'] = self.me
        return json.dumps(r)

def JSON_Construct_DTO_AssetImage(jsonstring):
    attribute_dictionary = json.loads(jsonstring)
    return DTO_AssetImage(attribute_dictionary.pop('file'), attribute_dictionary.pop('gid'))

def JSON_Construct_DTO_ShipClass(jsonstring):
    attribute_dictionary = json.loads(jsonstring)
    return DTO_ShipClass(attribute_dictionary.pop('abilities'), attribute_dictionary.pop('maxhp'), attribute_dictionary.pop('radius'), attribute_dictionary.pop('placement_cost'), attribute_dictionary.pop('imageid'), attribute_dictionary.pop('gid'))

def JSON_Construct_DTO_Ability(jsonstring):
    attribute_dictionary = json.loads(jsonstring)
    return DTO_Ability(attribute_dictionary.pop('radius'), attribute_dictionary.pop('name'), attribute_dictionary.pop('default_damage'), attribute_dictionary.pop('special_damages'), attribute_dictionary.pop('gid'))

def JSON_Construct_DTO_Assets(jsonstring):
    attribute_dictionary = json.loads(jsonstring)

    jsonlist = attribute_dictionary.pop('ship_classes')
    shipclasslist = []
    for v in jsonlist:
        shipclasslist.append(json.loads(v))

    jsonlist = attribute_dictionary.pop('images')
    imageslist = []
    for v in jsonlist:
        imageslist.append(json.loads(v))

    jsonlist = attribute_dictionary.pop('abilities')
    abilitieslist = []
    for v in jsonlist:
        abilitieslist.append(json.loads(v))

    return DTO_Assets(attribute_dictionary.pop('width'),attribute_dictionary.pop('height'),shipclasslist,imageslist,abilitieslist)

def JSON_Construct_DTO_ShipPlacement(jsonstring):
    attribute_dictionary = json.loads(jsonstring)    
    return DTO_ShipPlacement(attribute_dictionary.pop('x'), attribute_dictionary.pop('y'), attribute_dictionary.pop('classid'))

def JSON_Construct_DTO_AttackResults(jsonstring):
    attribute_dictionary = json.loads(jsonstring)
    newabilityuseorderlist = []
    for v in newabilityuseorderlist:
        newabilityuseorderlist.append(json.loads(v))
    return DTO_AttackResults(newabilityuseorderlist)

def JSON_Construct_DTO_Results(jsonstring):
    attribute_dictionary = json.loads(jsonstring)
    resultslist = attribute_dictionary.pop('results')
    newresultslistoftuples = []
    for v in resultslist:
        newresultslistoftuples.append(tuple(v))
    return DTO_Results(newresultslistoftuples)

def JSON_Construct_DTO_MovementOrder(jsonstring):
    attribute_dictionary = json.loads(jsonstring)
    pathlist = attribute_dictionary.pop('path')
    newpathlistoftuples = []
    for v in pathlist:
        newpathlistoftuples.append(tuple(v))
    return DTO_MovementOrder(attribute_dictionary.pop('unitid'), newpathlistoftuples, attribute_dictionary.pop('gid'))

def JSON_Construct_DTO_AbilityUseOrder(jsonstring):
    attribute_dictionary = json.loads(jsonstring)
    return DTO_AbilityUseOrder(attribute_dictionary.pop('srcid'),attribute_dictionary.pop('targetid'),attribute_dictionary.pop('ability'),attribute_dictionary.pop('gid'))

def JSON_Construct_DTO_Unit(jsonstring):
    attribute_dictionary = json.loads(jsonstring)
    return DTO_Unit(attribute_dictionary.pop('player_num'),attribute_dictionary.pop('hp'),attribute_dictionary.pop('classid'),attribute_dictionary.pop('gid'))

def JSON_Construct_DTO_Status(jsonstring):
    attribute_dictionary = json.loads(jsonstring)
    return DTO_Status(attribute_dictionary.pop('turn'), attribute_dictionary.pop('phase'), attribute_dictionary.pop('player_list'), attribute_dictionary.pop('me'))

