import unittest
import pickle
import os #path stuff
import sys #path stuff

#Path Stuff
path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), '../'))

import dto

class TestJSON(unittest.TestCase):
    def test_AssetImage(self):
        obj = dto.DTO_AssetImage("aGVsbG8gd29ybGQ=", "ship1", "123")
        obj1 = dto.JSON_Construct_DTO_AssetImage(obj.encode())
        self.assertEquals(pickle.dumps(obj),pickle.dumps(obj1))
    def test_DTO_ShipClass(self):
        strlist = ["One","Two","Three"]
        obj = dto.DTO_ShipClass(strlist, strlist, 5, 5, 5, "Four")
        obj1 = dto.JSON_Construct_DTO_ShipClass(obj.encode())
        self.assertEquals(pickle.dumps(obj),pickle.dumps(obj1))
    def test_DTO_Ability(self):
        obj = dto.DTO_Ability(5, "One", 5, {"Two":2,"Three":3,"Four":4}, "five")
        obj1 = dto.JSON_Construct_DTO_Ability(obj.encode())
        self.assertEquals(pickle.dumps(obj),pickle.dumps(obj1))
    def test_DTO_Assets(self):
        strlist = ["One","Two","Three"]
        shipclasslist = [dto.DTO_ShipClass(strlist, strlist, 5, 5, 5, "Four"), dto.DTO_ShipClass(strlist, strlist, 5, 5, 5, "Four"),dto.DTO_ShipClass(strlist, strlist, 5, 5, 5, "Four") ] 
        assetimagelist = [dto.DTO_AssetImage("aGVsbG8gd29ybGQ=", "ship1", "123"),dto.DTO_AssetImage("aGVsbG8gd29ybGQ=", "ship1", "123"),dto.DTO_AssetImage("aGVsbG8gd29ybGQ=", "ship1", "123")]
        abilitylist = [dto.DTO_Ability(5, "One", 5, {"Two":2,"Three":3,"Four":4}, "five"), dto.DTO_Ability(5, "One", 5, {"Two":2,"Three":3,"Four":4}, "five"), dto.DTO_Ability(5, "One", 5, {"Two":2,"Three":3,"Four":4}, "five")]
        obj = dto.DTO_Assets(5, 5, shipclasslist, assetimagelist, abilitylist)
        obj1 = dto.JSON_Construct_DTO_Assets(obj.encode())
        self.assertEquals(pickle.dumps(obj),pickle.dumps(obj1))
    def test_ShipPlacement(self):
        obj = dto.DTO_ShipPlacement(5, 5, "SomeClassID")
        obj1 = dto.JSON_Construct_DTO_ShipPlacement(obj.encode())
        self.assertEquals(pickle.dumps(obj),pickle.dumps(obj1))
    def test_Results(self):
        obj = dto.DTO_Results({"One":(5,5),"Two":(6,6)})
        obj1 = dto.JSON_Construct_DTO_Results(obj.encode())
        self.assertEquals(pickle.dumps(obj),pickle.dumps(obj1))
    def test_DTO_MovementOrder(self):
        tuplelist = [(1,1),(2,2),(3,3)]
        obj = dto.DTO_MovementOrder("One", tuplelist, "two")
        obj1 = dto.JSON_Construct_DTO_MovementOrder(obj.encode())
        self.assertEquals(pickle.dumps(obj),pickle.dumps(obj1))
    def test_DTO_AbilityUseOrder(self):
        obj = dto.DTO_AbilityUseOrder("One", "two", "three", "four")
        obj1 = dto.JSON_Construct_DTO_AbilityUseOrder(obj.encode())
        self.assertEquals(pickle.dumps(obj),pickle.dumps(obj1))
    def test_DTO_Unit(self):
        obj = dto.DTO_Unit(5, 6, "one", "two")
        obj1 = dto.JSON_Construct_DTO_Unit(obj.encode())
        self.assertEquals(pickle.dumps(obj),pickle.dumps(obj1))
    def test_DTO_Status(self):
        obj = dto.DTO_Status(5, 6, [1,2,3,4], 7)
        obj1 = dto.JSON_Construct_DTO_Status(obj.encode())
        self.assertEquals(pickle.dumps(obj),pickle.dumps(obj1))
if __name__ == "__main__":
    unittest.main()
