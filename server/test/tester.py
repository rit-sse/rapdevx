import xmlrunner
import unittest

# Import all tests here
from TestAttackTurn import *
from TestJSON import *
from TestRegistry import *
from TestGameplay import *
from TestServer import *


if __name__ == '__main__':
    unittest.main(testRunner=xmlrunner.XMLTestRunner(output='reports/pyunit'))
