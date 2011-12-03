import unittest
import pickle
from server.dto import *
class TestJSON(unittest.TestCase):
    def test_AssetImage(self):
        obj = DTO_AssetImage("aGVsbG8gd29ybGQ=", "ship1", "123")
        obj1 = JSON_Construct_DTO_AssetImage(obj.encode())
        self.assertEquals(pickle.dumps(obj),pickle.dumps(obj1))

if __name__ == "__main__":
    unittest.main()

