import os #path stuff
import sys #path stuff

#Path Stuff
path = os.path.abspath(__file__)
sys.path.append(os.path.join(os.path.dirname(path), '../'))

import dto
def testAll():
        f='testJSON.txt'
        f=open(f, "w")
        aidt=dto.DTO_AssetImage('imgtest.png','testTheGid')
        f.write(aidt.encode()+'\n')
        """shipclass"""
        sct=dto.DTO_ShipClass(['this is a gid'], 999, 10, 5, 'imgtest.png','thisisanid')
        f.write(sct.encode()+'\n')
        """ability"""
        at=dto.DTO_Ability(10,'name',5,20,'this is a gid')
        f.write(at.encode()+'\n')
        """Assets"""
        ast=dto.DTO_Assets(12,53,[sct],[aidt],[at])
        f.write(ast.encode()+'\n')
        """ShipPlacement"""
        spt=dto.DTO_ShipPlacement(23,62,'thisisanid')
        f.write(spt.encode()+'\n')
        """results"""
        rt=dto.DTO_Results({'thisisanid':(23,62)})
        f.write(rt.encode()+'\n')
        """DTO_MovementOrder"""
        mot=dto.DTO_MovementOrder('thisisanid',[(23,62)],'thisisagid')
        f.write(mot.encode()+'\n')
        """abilityuseorder"""
        aut=dto.DTO_AbilityUseOrder('srcidtest','targetidtest','this is an ability','gidtest')
        f.write(aut.encode()+'\n')
        """Unit"""
        ut=dto.DTO_Unit(23,43,'idtest','thisisgid')
        f.write(ut.encode()+'\n')
        """status"""
        st=dto.DTO_Status(3,'phase2',['p1','p2'],22)
        f.write(st.encode()+'\n')


if __name__ == "__main__":
    testAll()
