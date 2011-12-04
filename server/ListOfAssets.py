
from gameplay import Ability
from gameplay import GameRegistry

abilityRegistry = GameRegistry()

def genListOfAssets(self, reg):

    #list of Abilities
    atk = Ability(1000,"attack", 10, {})
    reg.register(atk)

    pntBlankMissile = Ability(10, "point_blank_missile", {})
    reg.register(pntBlankMissle)

    fwShot = Ability(5000, "firework_shot", 1, {})
    reg.register(fwShot)

    stdMissile = Ability(500, "standard_missile", 15, {})
    reg.register(stdMissile)

    selfRepair = Ability(0, "self_repair", -4, {})
    reg.register(selfRepair)

    derp = Ability(0, "derp", 999, {})
    reg.register(derp)


    #list of Images
    sprite = Image("../assets/ship.png")
    self.registry.register(sprite)

    #list of Ships


    scout = UnitClass([],[atk],200,10,10, sprite, "Scout")
    self.registry.register(scout)       

    #DTO_Assets
    self.assets = []
    self.assets.append(DTO_Assets(1000,1000,[scout.to_dto()],[sprite.to_dto()],[atk.to_dto()]))

