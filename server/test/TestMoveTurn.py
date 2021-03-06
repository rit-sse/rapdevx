import unittest
import os
import sys

path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), "../"))

import gameplay
import registry


class TestMoveTurn( unittest.TestCase ):

    def setUp( self ):
        self.testTurn = gameplay.MoveTurn(0)
        self.reg = registry.GameRegistry()
        self.p1Ship = gameplay.Unit( [], 10, 1, None, (0,0), 5)
        self.p2Ship = gameplay.Unit( [], 10, 2, None, (20,20), 5)
        self.reg.register(self.p1Ship)
        self.reg.register(self.p2Ship)

    def test_genericMove(self):
        moP1M1 = gameplay.MoveOrder( self.p1Ship.gid, [(0,0),(5,5)] )
        moP1M2 = gameplay.MoveOrder( self.p2Ship.gid, [(20,20),(15,15)] )

        self.testTurn.addMoveOrder( moP1M1, 1, self.reg )
        self.testTurn.addMoveOrder( moP1M2, 1, self.reg )

        self.testTurn.execute(self.reg)
        self.assertEqual( self.p2Ship.getLocation(), (15,15))

    def test_twoPlayers(self):
        moP1M1 = gameplay.MoveOrder( self.p1Ship.gid, [(0,0),(5,5)] )
        moP2M1 = gameplay.MoveOrder( self.p2Ship.gid, [(20,20),(15,15)] )
        
        self.testTurn.addMoveOrder( moP1M1, 1, self.reg )
        self.testTurn.addMoveOrder( moP2M1, 2, self.reg )
        
        self.testTurn.execute(self.reg)
        
        self.assertEqual( self.p1Ship.getLocation(), (5,5))
        self.assertEqual( self.p2Ship.getLocation(), (15,15))

    def test_removeMoves(self):
        moP1M1 = gameplay.MoveOrder( self.p1Ship.gid, [(0,0),(5,5)] )
        moP2M1 = gameplay.MoveOrder( self.p2Ship.gid, [(20,20),(15,15)] )

        self.testTurn.addMoveOrder( moP1M1, 1, self.reg )
        self.testTurn.addMoveOrder( moP2M1, 2, self.reg )

        moveOrders = self.reg.getAllByType(gameplay.MoveOrder)
        toBeDeleted = [x for x in moveOrders if x.unitid == self.p1Ship.gid][0]
        self.testTurn.deleteMoveOrder( toBeDeleted.gid, 1, self.reg )

        self.testTurn.execute(self.reg)
        self.assertEqual( self.p1Ship.getLocation(), (0,0))

if (__name__ == "__main__") :
    unittest.main()
