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

move1 = MoveOrder(TestUnit1.gid, [(0,0),(100, 0)])
move2 = MoveOrder(TestUnit2.gid, [(100,100),(0, 100)])

class TestGameplay( unittest.TestCase ):
    def test_swizzle1( self ):
        '''
        Tests the basic functionality of swizzle.
        '''
        lists = [ [0, 1, 2] , [3, 4, 5] ]
        swizList = swizzle(lists)
        self.assertEqual([0, 3, 1, 4, 2, 5], swizList)
    
    def test_swizzle2( self ):
        '''
        Tests the functionality of swizzle with the second arguments as an empty list.
        (the second player logs no move orders)
        '''
        lists = [ [1, 2, 3, 4] , [] ]
        swizList = swizzle(lists)
        self.assertEqual([1, 2, 3, 4], swizList)
    
    def test_swizzle3( self ):
        '''
        Tests the functionality of swizzle with the first argument as an empty list.
        (the first player logs no move orders)
        '''
        lists = [ [] , [1, 2, 3, 4] ]
        swizList = swizzle(lists)
        self.assertEqual([1, 2, 3, 4], swizList)

    def test_swizzle4( self ):
        '''
        Tests the functionality of swizzle with two empty lists
        (both players log no move orders)
        '''
        lists = [ [] , [] ]
        swizList = swizzle(lists)
        self.assertEqual([], swizList)

    def test_MoveTurn_execute( self ):
        '''
        Checks to be sure the execute function effectively changes the locations of units.
        '''
        turn = Turn(0)
        turn.move.addMoveOrder(move1, 0, reg)
        turn.move.addMoveOrder(move2, 1, reg)
        turn.move.execute(reg)
        self.assertTrue(TestUnit1.location == (100, 0) and TestUnit2.location == (0, 100))

    def test_MoveTurn_execute_playerShuffle2( self ):
        '''
        Tests the basic functionality of player priority shuffling by using the same code    
        and simulating turns using a for loop. Tests with two players.
        '''
        player_priorities = []
        
        for turn_num in range(4):
            player_nums = [0, 1]
            num_of_players = len( player_nums )
            num_of_shuffles = turn_num % num_of_players
            for x in range( num_of_shuffles ):
                player_nums.append( player_nums.pop(0) )
            player_priorities.append( player_nums[0] )

        self.assertEqual( player_priorities, [0, 1, 0, 1] )

    def test_MoveTurn_execute_playerShuffle3( self ):
        '''
        Tests the basic functionality of player priority shuffling by using the same code    
        and simulating turns using a for loop. Tests with three players.
        '''
        player_priorities = []
        
        for turn_num in range(6):
            player_nums = [0, 1, 2]
            num_of_players = len( player_nums )
            num_of_shuffles = turn_num % num_of_players
            for x in range( num_of_shuffles ):
                player_nums.append( player_nums.pop(0) )
            player_priorities.append( player_nums[0] )

        self.assertEqual( player_priorities, [0, 1, 2, 0, 1, 2] )

if __name__ == "__main__":
    unittest.main()
