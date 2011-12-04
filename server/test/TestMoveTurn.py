import unittest
import os
import sys

path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), "../"))

import gameplay
import registry

class TestMoveTurn( unittest.TestCase ):

    def test_genericMove(self):
        testTurn = gameplay.MoveTurn(0)
        reg = registry.GameRegistry()
        p1Ship = gameplay.Unit( [], 10, 1, None, (0,0), 5)
        p2Ship = gameplay.Unit( [], 10, 2, None, (20,20), 5)
        reg.register(p1Ship)
        reg.register(p2Ship)
        moP1M1 = gameplay.MoveOrder( p1Ship.gid, (5,5) )
        moP1M2 = gameplay.MoveOrder( p1Ship.gid, (15,15) )
        moP2M1 = gameplay.MoveOrder( p1Ship.gid, (35,35) )
        moP2M2 = gameplay.MoveOrder( p1Ship.gid, (55,45) )

        testTurn.addMoveOrder( moP1M1, 1, reg )
        testTurn.addMoveOrder( moP1M2, 1, reg )
        testTurn.addMoveOrder( moP2M1, 2, reg )
        testTurn.addMoveOrder( moP2M2, 2, reg )

        testTurn.execute(reg)
        self.assertEqual( p1Ship.getLocation(), (15,15))


if (__name__ == "__main__") :
    unittest.main()
