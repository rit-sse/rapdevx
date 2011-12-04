import unittest
import os
import sys


path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), "../"))

import gameplay

class TestGameplay( unittest.TestCase ):
    
    def test_MakeUnit( self ):
        testUnit = gameplay.Unit( [], 0, 0, [], (0,0), 0 )

    def test_Collisions( self ):
        moveTurnObject = gameplay.MoveTurn(0)
        locations1 = [(0,0), (2,2), (5,5), (2,2)]
        locations2 = [(5,5), (5,5), (2,2), (5,5)]
        endingLocation = [(10,10), (10,2), (5,10), (10,2)]
        radius1 = [2,3,1,1]
        radius2 = [2,2,1,1]
        assertBool = [True,True,False,False]
        for i in range(4):
            print("TEST_CASE_NUMBER_"+str(i))
            ship1 = gameplay.Unit( [], 1, 0, [], locations1[i], radius1[i] )
            ship2 = gameplay.Unit( [], 1, 0, [], locations2[i], radius2[i] )
            self.assertTrue( moveTurnObject.collisionCheck(ship1, ship2, endingLocation[i]) == assertBool[i] )


if __name__ == "__main__":
    unittest.main()
