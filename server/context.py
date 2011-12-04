from dto import DTO_Status
from gameplay import *
from registry import GameRegistry
from dto import *

class GameContext:
    def __init__(self,playerlist):
        self.playerlist = playerlist
        self.registry = GameRegistry()
        #todo real asset loading
        atk = Ability(1000,"attack", 10,{})
        self.registry.register(atk)
        
        sprite = Image("../assets/ship.png")
        self.registry.register(sprite)
        
        unitClass = UnitClass([],[atk],200,10,10, sprite, "Scout")
        self.registry.register(unitClass)
        
        self.assets = DTO_Assets(1000,1000,[unitClass.to_dto()],[],[atk.to_dto()])
        self.phase = WaitingPhase(self)
    
        self.turns = {}
    
    def getAssetSet(self):
        return self.assets
    
    def getRegistry(self):
        return self.registry
    
    def getCurrentPhase(self):
        return GamePhase(self)
    
    def getAllDTOShips(self, my_playernum):
        return [x.to_dto() for x in self.registry.getAllByType(Unit)]

    def _register_turn(self, turn_number, turn):
        self.turns[turn_number] = turn
    
    def getTurnAttackResults(self, number):
        return self.turns[number].attack.getResults()
    
    def getTurnMoveResults(self, number):
        return self.turns[number].move.getResults()
    
    #Passthrough to phase
    def addPlayerMove(self, action_order, calling_player):
        x = self.phase.addPlayerMove(action_order, calling_player)
        self.phase = self.phase.getNextPhase()
        return x
        
    def getPlayerMoveList(self, calling_player):
        x = self.phase.getPlayerMoveList(calling_player)
        self.phase = self.phase.getNextPhase()
        return x
        
    def getPlayerMove(self, move_id, calling_player):
        x = self.phase.getPlayerMove(move_id, calling_player)
        self.phase = self.phase.getNextPhase()
        return x
    
    def deletePlayerMove(self, move_id, calling_player):
        x = self.phase.deletePlayerMove(move_id, calling_player)
        self.phase = self.phase.getNextPhase()
        return x
        
    def setShipPlacement(self, ship_place_list, calling_player):
        x = self.phase.setShipPlacement(ship_place_list, calling_player)
        self.phase = self.phase.getNextPhase()
        return x
        
    def getGameProgress(self, calling_player):
        x = self.phase.getGameProgress(calling_player)
        self.phase = self.phase.getNextPhase()
        return x
        
    def setReady(self, player_num, val):
        x = self.phase.setReady(player_num, val)
        self.phase = self.phase.getNextPhase()
        return x
    
class GamePhase:
    #abstract superclass for all phases
    def __init__(self, context):
        self.context = context

    def addPlayerMove(self, action_order, calling_player):
        Exception("Unimplemented")
        
    def getPlayerMoveList(self, calling_player):
        Exception("Unimplemented")
        
    def getPlayerMove(self, move_id, calling_player):
        Exception("Unimplemented")
    
    def deletePlayerMove(self, move_id, calling_player):
        Exception("Unimplemented")
        
    def setShipPlacement(self, ship_place_list, calling_player):
        Exception("Unimplemented")
        
    def getGameProgress(self, calling_player):
        Exception("Unimplemented")
    
    def setReady(self, player_num, val):
        Exception("Unimplemented")
        
    def getNextPhase(self):
        return GamePhase()
        
class WaitingPhase(GamePhase):
    #wait for all players to send ready, then move on
    def __init__(self, context):
        GamePhase.__init__(self,context)
        self.ready = [False for x in context.playerlist]

    def getGameProgress(self, calling_player):
        return Status(None, "waiting", self.context.playerlist, calling_player)

    def setReady(self, player_num, val):
        self.ready[player_num] = val
    
    def getNextPhase(self):
        if(False in self.ready):
            return self
        else:
            return PlacementPhase(self.context)
    
class PlacementPhase(GamePhase):
    #players can choose ship positions, then ready
    def __init__(self, context):
        GamePhase.__init__(self,context)
        self.assignments = [None for x in context.playerlist]

    def setShipPlacement(self, ship_place_list, calling_player):
        #todo: verify integrity of ship placement list (it replaces current)
        self.assignments[calling_player] = ship_place_list
        
    def getGameProgress(self, calling_player):
        return Status(None, "placement", self.context.playerlist, calling_player)
        
    def getNextPhase(self):
        if(None in self.assignments):
            return self
        else:
            for player, assignment in enumerate(self.assignments):
                for placement in assignment:
                    unit = self.context.registry.getById(placement.classid).makeUnit((placement.x,placement.y),player)
                    self.context.registry.register(unit)
            return MovementPhase(self.context)

class MovementPhase(GamePhase):
    #players choose movements, then ready
    def __init__(self, context, turn = Turn(0)):
        GamePhase.__init__(self,context)
        self.ready = [False for x in context.playerlist]
        self.turn = turn
        self.context._register_turn(turn.turn_num,turn)

    def addPlayerMove(self, action_order, calling_player):
        if (isinstance(action_order, DTO_MovementOrder)):
            self.turn.move.addMoveOrder(action_order,calling_player, self.context.registry)
        else:
            Exception("Movement phase is in order, attack orders not allowed")
        
    def getPlayerMoveList(self, calling_player):
        return self.turn.getPlayerMoveList(calling_player)
        
    def getPlayerMove(self, move_id, calling_player):
        # todo is move_id an index?
        return self.turn.getPlayerMoveList(calling_player)[move_id]
    
    def deletePlayerMove(self, move_id, calling_player):
        if (isinstance(action_order, MovementOrder)):
            self.turn.deleteMoveOrder(move_id, action_order)
        else:
            Exception("Movement phase is in order, attack orders not allowed")

    def getGameProgress(self, calling_player):
        return Status(self.turn.turn_num, "move", self.context.playerlist, calling_player)

    def setReady(self, player_num, val):
        self.ready[player_num] = val

    def getNextPhase(self):
        if(False in self.ready):
            return self
        else:
            self.turn.move.execute(self.context.registry)
            return AttackPhase(self.context, self.turn)
    

class AttackPhase(GamePhase):
    #players choose attacks, then ready
    def __init__(self, context, turn):
        GamePhase.__init__(self,context)
        self.ready = [False for x in context.playerlist]
        self.turn = turn
        self.context._register_turn(turn.turn_num,turn)

    def addPlayerMove(self, action_order, calling_player):
        if (isinstance(action_order, AbilityUseOrder)):
            self.turn.addAttackOrder(action_order)
        else:
            Exception("Attack phase is in order, move orders not allowed")
        
    def getPlayerMoveList(self, calling_player):
        return self.turn.getPlayerMoveList(calling_player)
        
    def getPlayerMove(self, move_id, calling_player):
        # todo is move_id an index?
        return self.turn.getPlayerMoveList(calling_player)[move_id]
    
    def deletePlayerMove(self, move_id, calling_player):
        if (isinstance(action_order, AbilityUseOrder)):
            self.turn.deleteAttackOrder(move_id, action_order)
        else:
            Exception("Attack phase is in order, move orders not allowed")


    def getGameProgress(self, calling_player):
        return Status(self.turn.turn_num, "attack", self.context.playerlist, calling_player)

    def setReady(self, player_num, val):
        self.ready[player_num] = val

    def getNextPhase(self):
        if(self.ready.contains(False)):
            return self
        else:
            self.turn.attack.execute()
            #todo: check of any player lost all ships, and move to won
            #if so
            return MovementPhase(self.context, Turn(self.turn.turn_num + 1))
    
class WonPhase(GamePhase):
    #the game is done, somebody has won
    def __init__(self, context, turn):
        GamePhase.__init__(self,context)
        self.turn = turn

    def getGameProgress(self, calling_player):
        return Status(self.turn, "win", self.context.playerlist, calling_player)
    
    def getNextPhase(self):
        return self

c = None

    
    
