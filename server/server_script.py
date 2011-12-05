from context import *
if __name__ == '__main__':
    c = GameContext(['a','b'])
    print("Should be in waiting phase")
    print(c.getGameProgress(0))
    
    c.setReady(1,True)
    c.setReady(0,True)

    print("Should be in placement phase")
    print(c.getGameProgress(0))

    import dto

    place1 = dto.DTO_ShipPlacement(1000,0,'UnitClass0')
    place0 = dto.DTO_ShipPlacement(0,0,'UnitClass0')
    
    c.setShipPlacement([place1],1)
    c.setShipPlacement([place0],0)

    print("Should be in movement phase")
    print(c.getGameProgress(0))
    
    
    ships = c.getAllDTOShips(0)
    
    team0ship = [x.gid for x in ships if x.player_num==0][0]
    team1ship = [x.gid for x in ships if x.player_num==1][0]

    print("\nlist of ships:")
    print(ships)
    print("team0ship id:",team0ship)
    print("team1ship id:",team1ship)

    dto_move1 = DTO_MovementOrder(team1ship,[(1000,0),(0,0)],None)
    dto_move0 = DTO_MovementOrder(team0ship,[(0,0),(1000,0)],None)
    
    c.addPlayerMove(dto_move1,1)
    c.addPlayerMove(dto_move0,0)
    
    print("\nMoves in, but ready not yet submited... results and progress:")
    print(c.getTurnMoveResults(0))
    print(c.getGameProgress(0))
    
    
    c.setReady(1,True)
    c.setReady(0,True)

    print("\nready sent, should be in attack phase... results and progress:")
    print(c.getTurnMoveResults(0))
    print(c.getGameProgress(0))

    c.setReady(1,True)
    c.setReady(0,True)

    print("\nskipping attack, should be on move")
    print(c.getTurnMoveResults(0))
    print(c.getGameProgress(0))
    ships = c.getAllDTOShips(0)
    print(ships[0].location)
    print(ships[1].location)
    
    
