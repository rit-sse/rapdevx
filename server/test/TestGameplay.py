import unittest
import os
import sys


path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), "../"))

from gameplay import *
import registry

reg = registry.GameRegistry()

TestUnit1 = Unit(abilities=[Ability()], owning_player=0, location=(0, 0))
reg.register(TestUnit1)
TestUnit2 = Unit(abilities=[Ability()], owning_player=1, location=(100, 100))
reg.register(TestUnit2)

move1 = MoveOrder(TestUnit1.gid, (100, 0))
move2 = MoveOrder(TestUnit2.gid, (0, 100))

class TestGameplay( unittest.TestCase ):
    def test_swizzle1(self):
        lists = [ [0, 1, 2] , [3, 4, 5] ]
        swizList = swizzle(lists)
        self.assertEqual([0, 3, 1, 4, 2, 5], swizList)
    
    def test_swizzle2(self):
        lists = [ [1, 2, 3, 4] , [] ]
        swizList = swizzle(lists)
        self.assertEqual([1, 2, 3, 4], swizList)
    
    def test_swizzle3(self):
        lists = [ [] , [1, 2, 3, 4] ]
        swizList = swizzle(lists)
        self.assertEqual([1, 2, 3, 4], swizList)

    def test_swizzle4(self):
        lists = [ [] , [] ]
        swizList = swizzle(lists)
        self.assertEqual([], swizList)

    def test_MoveTurn_execute(self):
        turn = Turn(0)
        turn.move.addMoveOrder(move1, 0, reg)
        turn.move.addMoveOrder(move2, 1, reg)
        turn.move.execute(reg)
        self.assertTrue(TestUnit1.location == (100, 0) and TestUnit2.location == (0, 100))

if __name__ == "__main__":
    unittest.main()
