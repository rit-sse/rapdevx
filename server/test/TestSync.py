import os #path stuff
import sys #path stuff

#Path Stuff
path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), '../'))

import dto
def testAll():
        encoder=dto.DTO_Encoder()
        f='testSync.txt'
        f=open(f, "w")
        """Asset Image"""
        aidt=dto.DTO_AssetImage(b'aimgtest.png','testTheGid')
        f.write('AssetImage:  '+str(encoder.encode(aidt))+'\n')
        """shipclass"""
        sct=dto.DTO_ShipClass(['goodShip','badShip','SpecialShip','NotSpecialShip'],['this is a gid'], 999, 10, 5, 'imgtest.png','thisisanid')
        f.write('ShipClass:  '+str(encoder.encode(sct))+'\n')
        """ability"""
        at=dto.DTO_Ability(10,'name',5,{'one': 1, 'two': 2},'this is a gid')
        f.write('Ability:  '+str(encoder.encode(at))+'\n')
        """Assets"""
        ast=dto.DTO_Assets(12,53,[sct],[aidt],[at])
        f.write('Assets:  '+str(encoder.encode(ast))+'\n')
        """ShipPlacement"""
        spt=dto.DTO_ShipPlacement(23,62,'thisisanid')
        f.write('ShipPlacement:  '+str(encoder.encode(spt))+'\n')
        """results"""
        rt=dto.DTO_Results({'thisisanid':(23,62)})
        f.write('Results:  '+str(encoder.encode(rt))+'\n')
        """DTO_MovementOrder"""
        mot=dto.DTO_MovementOrder('thisisanid',[(23,62)],'thisisagid')
        f.write('MovementOrder:  '+str(encoder.encode(mot))+'\n')
        """abilityuseorder"""
        aut=dto.DTO_AbilityUseOrder('srcidtest','targetidtest','this is an ability','gidtest')
        f.write('AbilityUse:  '+str(encoder.encode(aut))+'\n')
        """attackresults"""
        art=dto.DTO_AttackResults([aut])
        f.write('AttackResults:  '+str(encoder.encode(art))+'\n')
        """Unit"""
        ut=dto.DTO_Unit(23,43,'idtest','thisisgid')
        f.write('Unit:  '+str(encoder.encode(ut))+'\n')
        """status"""
        st=dto.DTO_Status(3,'phase2',['p1','p2'],22)
        f.write('Status:  '+str(encoder.encode(st))+'\n')
        f.close()


if __name__ == "__main__":
    testAll()
