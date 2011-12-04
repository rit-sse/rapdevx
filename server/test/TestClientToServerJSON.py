import os #path stuff
import sys #path stuff

#Path Stuff
path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), '../'))

import dto
import unittest

class TestDecoders(unittest.TestCase):
    def test_AssetImageDec(self):
        f=open('json_sync/AssetImageFromJava.json')
        for i in f:
            ait=i
        f.close()
        aidt=dto.JSON_Construct_DTO_AssetImage(ait)
        self.assertEqual(dto.DTO_Encoder().encode(aidt), ait)

    def test_ShipClassDec(self):
        f=open('json_sync/ShipClassFromJava.json')
        for i in f:
            sct=i
        f.close()
        scdt=dto.JSON_Construct_DTO_ShipClass(sct)
        self.assertEqual(dto.DTO_Encoder().encode(scdt), sct)

    def test_AbilityDec(self):
        f=open('json_sync/AbilityFromJava.json')
        for i in f:
            at=i
        f.close()
        adt=dto.JSON_Construct_DTO_Ability(at)
        self.assertEqual(dto.DTO_Encoder().encode(adt), at)

    def test_AssetsDec(self):
        f=open('json_sync/AssetsFromJava.json')
        for i in f:
            ast=i
        f.close()
        asdt=dto.JSON_Construct_DTO_Assets(ast)
        self.assertEqual(dto.DTO_Encoder().encode(asdt), ast)

    def test_ShipPlacementDec(self):
        f=open('json_sync/ShipPlacementFromJava.json')
        for i in f:
            spt=i
        f.close()
        spdt=dto.JSON_Construct_DTO_ShipPlacement(spt)
        self.assertEqual(dto.DTO_Encoder().encode(spdt), spt)

    def test_ResultsDec(self):
        f=open('json_sync/ResultsFromJava.json')
        for i in f:
            rt=i
        f.close()
        rdt=dto.JSON_Construct_DTO_Results(rt)
        self.assertEqual(dto.DTO_Encoder().encode(rdt), rt)

    def test_MovementOrderDec(self):
        f=open('json_sync/MovementOrderFromJava.json')
        for i in f:
            mot=i
        f.close()
        modt=dto.JSON_Construct_DTO_MovementOrder(mot)
        self.assertEqual(dto.DTO_Encoder().encode(modt), mot)

    def test_AbilityUseOrderDec(self):
        f=open('json_sync/AbilityUseOrderFromJava.json')
        for i in f:
            auot=i
        f.close()
        auodt=dto.JSON_Construct_DTO_AbilityUseOrder(auot)
        self.assertEqual(dto.DTO_Encoder().encode(auodt), auot)

    def test_AttackResultsDec(self):
        f=open('json_sync/AttackResultsFromJava.json')
        for i in f:
            art=i
        f.close()
        ardt=dto.JSON_Construct_DTO_AttackResults(art)
        self.assertEqual(dto.DTO_Encoder().encode(ardt), art)

    def test_UnitDec(self):
        f=open('json_sync/UnitFromJava.json')
        for i in f:
            ut=i
        f.close()
        udt=dto.JSON_Construct_DTO_Unit(ut)
        self.assertEqual(dto.DTO_Encoder().encode(udt), ut)

    def test_StatusDec(self):
        f=open('json_sync/StatusFromJava.json')
        for i in f:
            st=i
        f.close()
        sdt=dto.JSON_Construct_DTO_Status(st)
        self.assertEqual(dto.DTO_Encoder().encode(sdt), st)

if __name__ == "__main__":
    unittest.main()
