/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl.diseases;

import java.awt.Point;
import java.util.Set;
import pandemic.Interfaces.IPR;
import pandemic.Interfaces.IPerson;
import pandemic.globals.Utility.Utility;

/**
 *
 * @author Jiri Vrbka
 */
public class PatogenDisease extends ADisease{

    Set<IPerson> patogens;
    
    public PatogenDisease(Set patogens){
        this.patogens = patogens;
    }
    
    @Override
    public void spreadInfection(IPR pr, Point point) {
        if(patogens.contains(pr.getPersonAt(point))){
            
            for(Point around :Utility.getPointsAround(point)){
                IPerson person = pr.getPersonAt(around);
                if(person != null && !person.isDead()  && !patogens.contains(person) && super.shouldBeInfected()){
                    
                    person.makeIll();
                }
            }
        }
    }
//
//    @Override
//    public void spreadInfection(Map<Point, IPerson> people) {
//        people.entrySet().forEach(e -> {
//        
//            if(patogens.contains(e.getValue())){
//            
//                for(Point around :Utility.getPointsAround(e.getKey())){
//                    IPerson person = people.get(around);
//                    if(person != null && !patogens.contains(person)){
//
//                        person.makeIll();
//                    }
//                }
//            }
//        
//        
//        } );
//    }
    
}
