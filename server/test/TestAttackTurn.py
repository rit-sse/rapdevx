import unittest
import os
import sys

path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), "../"))

import gameplay
import registry


class TestAttackTurn( unittest.TestCase ):

    def setUp( self ):
        self.testTurn = gameplay.AttackTurn(0)
        self.reg = registry.GameRegistry()
        self.p1Ship = gameplay.Unit( location=(0,0), radius=5)
        self.p2Ship = gameplay.Unit( location=(20,20), radius=5)
        self.reg.register(self.p1Ship)
        self.reg.register(self.p2Ship)
        print("DOING A TEST")

    def test_genericAttack( self ):
        pass

if (__name__ == "__main__") :
    unittest.main()
