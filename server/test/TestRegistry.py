import unittest
import os
import sys

path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), "../"))

import registry

class TestRegistry( unittest.TestCase ):
    
    def test_register(self):
        class Foo: pass
        class Bar: pass
        regObject = registry.GameRegistry()
        regObject.register(Foo()) #registers an instance of the Foo class
        regObject.register(Foo())
        regObject.register(Bar())
        regObject.register(Bar())
        

        reg = list(regObject.registry.keys())
        reg.sort() #Keys maybe out of order - doesn't matter, it's a dictionary

        self.assertEqual(reg, ["Bar0","Bar1","Foo0","Foo1"])




if __name__ == "__main__":
    unittest.main()
