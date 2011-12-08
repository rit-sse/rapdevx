
from gameplay import *
from registry import GameRegistry

abilityRegistry = GameRegistry()

def genListOfAssets(reg):

    #list of Abilities
    atk = Ability(1000,"attack", 10, {})
    reg.register(atk)

    pntBlankMissile = Ability(10, "point_blank_missile", 10, {})
    reg.register(pntBlankMissile)

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
    reg.register(sprite)

    #list of Ships


    scout = UnitClass([],[atk,fwShot],200,10,10, sprite, "Scout")
    bb = UnitClass([],[stdMissile,pntBlankMissile],400,20,40, sprite, "Big Boy")
    reg.register(scout)
    reg.register(bb)

    #DTO_Assets
    return DTO_Assets(1000,1000,[scout.to_dto(),bb.to_dto()],[sprite.to_dto()],[atk.to_dto(),pntBlankMissile.to_dto(),fwShot.to_dto()])

