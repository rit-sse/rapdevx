# Data Transfer Objects - Primarily used to communicate between API team 
# and the Server Objects. Any class in here is to be exclusively as a struct

class AssetImage:
    def __init__(self, file, name, registry):
        self.file = file
        self.name = name
        self.id = None #set by registering it
    def jsonEncode(self):
        r = {}
        r['file'] = self.file
        r['name'] = self.name
        r['id'] = self.id
        return r

class ShipClass:
    def __init__(self, types, abilities, maxhp, radius, placement_cost):
        self.types = types
        self.abilities = abilities
        self.maxhp = maxhp
        self.radius = radius
        self.placement_cost = placement_cost
        self.id = None #set by registering it

class Ability:
    #default_damage is int
    #special_damage map from string to int
    def __init__(self, radius, name, default_damage, special_damages):
        self.radius = radius
        self.name = name
        self.default_damage = default_damage
        self.special_damages = special_damages
        self.id = None #set by registering it
        
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
    def __init__(self, unitid, path):
        self.unitid = unitid
        self.path = path
        self.id = None #set by registering it

class AbilityUseOrder:
    #srcid,targetid = int
    #ability = string
    def __init__(self, srcid, targetid, ability, path):
        self.srcid = srcid
        self.targetid = targetid
        self.ability = ability
        self.id = None #set by registering it
        
