import xmlrunner
import unittest

myListOfClasses=["TestAttackTurn","TestJSON", "TestRegistry", "TestGameplay","TestGeometry","TestMoveTurn" ]
for aClass in myListOfClasses:
    try:
        exec("from "+str(aClass)+ " import *")
    except:
        print("Can not import "+str(aClass))

if __name__ == '__main__':
    unittest.main(testRunner=xmlrunner.XMLTestRunner(output='reports/pyunit'))
