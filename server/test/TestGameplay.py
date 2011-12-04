import unittest
import os
import sys


path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), "../"))

import gameplay

class TestGameplay( unittest.TestCase ):
    def test_swizzle1(self):
        lists = [ [0, 1, 2] , [3, 4, 5] ]
        swizList = gameplay.swizzle(lists)
        self.assertEqual([0, 3, 1, 4, 2, 5], swizList)
    
    def test_swizzle2(self):
        lists = [ [1, 2, 3, 4] , [] ]
        swizList = gameplay.swizzle(lists)
        self.assertEqual([1, 2, 3, 4], swizList)
    
    def test_swizzle3(self):
        lists = [ [] , [1, 2, 3, 4] ]
        swizList = gameplay.swizzle(lists)
        self.assertEqual([1, 2, 3, 4], swizList)

    def test_swizzle4(self):
        lists = [ [] , [] ]
        swizList = gameplay.swizzle(lists)
        self.assertEqual([], swizList)

if __name__ == "__main__":
    unittest.main()
