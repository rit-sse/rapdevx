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
        f.write('AssetImage:  '+str(encoder.default(aidt))+'\n')
        """shipclass"""
        sct=dto.DTO_ShipClass(['goodShip','badShip','SpecialShip','NotSpecialShip'],['this is a gid'], 999, 10, 5, 'imgtest.png','thisisanid')
        f.write('ShipClass:  '+str(encoder.default(sct))+'\n')
        """ability"""
        at=dto.DTO_Ability(10,'name',5,20,'this is a gid')
        f.write('Ability:  '+str(encoder.default(at))+'\n')
        """Assets"""
        ast=dto.DTO_Assets(12,53,[sct],[aidt],[at])
        f.write('Assets:  '+str(encoder.default(ast))+'\n')
        """ShipPlacement"""
        spt=dto.DTO_ShipPlacement(23,62,'thisisanid')
        f.write('ShipPlacement:  '+str(encoder.default(spt))+'\n')
        """results"""
        rt=dto.DTO_Results({'thisisanid':(23,62)})
        f.write('Results:  '+str(encoder.default(rt))+'\n')
        """DTO_MovementOrder"""
        mot=dto.DTO_MovementOrder('thisisanid',[(23,62)],'thisisagid')
        f.write('MovementOrder:  '+str(encoder.default(mot))+'\n')
        """abilityuseorder"""
        aut=dto.DTO_AbilityUseOrder('srcidtest','targetidtest','this is an ability','gidtest')
        f.write('AbilityUse:  '+str(encoder.default(aut))+'\n')
        """attackresults"""
        art=dto.DTO_AttackResults([aut])
        f.write('AttackResults:  '+str(encoder.default(art))+'\n')
        """Unit"""
        ut=dto.DTO_Unit(23,43,'idtest','thisisgid')
        f.write('Unit:  '+str(encoder.default(ut))+'\n')
        """status"""
        st=dto.DTO_Status(3,'phase2',['p1','p2'],22)
        f.write('Status:  '+str(encoder.default(st))+'\n')
        f.close()


if __name__ == "__main__":
    testAll()
