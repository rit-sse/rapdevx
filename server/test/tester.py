import xmlrunner
import unittest

# Import all tests here
from TestJSON import *
from TestRegistry import *
from TestGameplay import *
from TestServer import *
from TestGeometry import *
from TestMoveTurn import *
from TestAttackTurn import *

if __name__ == '__main__':
    unittest.main(testRunner=xmlrunner.XMLTestRunner(output='reports/pyunit'))
