import os #path stuff
import sys #path stuff

#Path Stuff
path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), '../'))

import dto
def testAll():
        f='testSync.txt'
        f=open(f, "w")
        """Asset Image"""
        aidt=dto.DTO_AssetImage('imgtest.png','testTheGid')
        f.write('AssetImage:  '+aidt.encode()+'\n')
        """shipclass"""
        sct=dto.DTO_ShipClass(['this is a gid'], 999, 10, 5, 'imgtest.png','thisisanid')
        f.write('ShipClass:  '+sct.encode()+'\n')
        """ability"""
        at=dto.DTO_Ability(10,'name',5,20,'this is a gid')
        f.write('Ability:  '+at.encode()+'\n')
        """Assets"""
        ast=dto.DTO_Assets(12,53,[sct],[aidt],[at])
        f.write('Assets:  '+ast.encode()+'\n')
        """ShipPlacement"""
        spt=dto.DTO_ShipPlacement(23,62,'thisisanid')
        f.write('ShipPlacement:  '+spt.encode()+'\n')
        """results"""
        rt=dto.DTO_Results({'thisisanid':(23,62)})
        f.write('Results:  '+rt.encode()+'\n')
        """DTO_MovementOrder"""
        mot=dto.DTO_MovementOrder('thisisanid',[(23,62)],'thisisagid')
        f.write('MovementOrder:  '+mot.encode()+'\n')
        """abilityuseorder"""
        aut=dto.DTO_AbilityUseOrder('srcidtest','targetidtest','this is an ability','gidtest')
        f.write('AbilityUse:  '+aut.encode()+'\n')
        """attackresults"""
        art=dto.DTO_AttackResults([aut])
        f.write('AttackResults:  '+art.encode()+'\n')
        """Unit"""
        ut=dto.DTO_Unit(23,43,'idtest','thisisgid')
        f.write('Unit:  '+ut.encode()+'\n')
        """status"""
        st=dto.DTO_Status(3,'phase2',['p1','p2'],22)
        f.write('Status:  '+st.encode()+'\n')
        """session"""
        sest=dto.DTO_Session(23, 'cman', 42, True)
        f.write('Session:  '+sest.encode()+'\n')


if __name__ == "__main__":
    testAll()
