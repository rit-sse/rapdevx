import xmlrunner
import unittest

# Import all tests here
from TestAttackTurn import *
from TestClientToServerJSON import *
from TestGameplay import *
from TestGamePool import *
from TestGeometry import *
from TestJSON import *
from TestMoveTurn import *
from TestRegistry import *
from TestServer import *
from TestSession import *
from TestSessionManager import *

if __name__ == '__main__':
    unittest.main(testRunner=xmlrunner.XMLTestRunner(output='reports/pyunit'))
