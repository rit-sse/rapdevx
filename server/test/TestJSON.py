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

if __name__ == "__main__":
    unittest.main()

