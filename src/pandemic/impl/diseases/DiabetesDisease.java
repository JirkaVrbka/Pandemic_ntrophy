/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl.diseases;

import java.awt.Point;
import pandemic.Interfaces.IDisease;
import pandemic.Interfaces.IPR;
import pandemic.Interfaces.IPerson;
import pandemic.globals.Utility.Utility;

/**
 *
 * @author Jiri Vrbka
 */
public class DiabetesDisease extends ADisease implements IDisease {

    private static final int MULTIPLICATOR_IMMUNITY = 7000;
    
//    public DiabetesDisease(){
//        super.setInfectivity(1);
//    }
    
    @Override
    public void spreadInfection(IPR pr, Point point) {
        IPerson person = pr.getPersonAt(point);
        
        if(person != null && person.isHealthy()){
            if((Utility.getRandomNumber(MULTIPLICATOR_IMMUNITY) < getInfectivity())){
                person.makeIll();
            }
        }
    }
//
//    @Override
//    public void spreadInfection(Map<Point, IPerson> people) {
//        people.entrySet().forEach(e -> {
//        
//            IPerson person = e.getValue();
//            Point point = e.getKey();
//            
//            if(person != null && person.isHealthy()){
//                if(Utility.getRandomNumber(MULTIPLICATOR_IMMUNITY) < getInfectivity()){
//                    person.makeIll();
//                }
//            }
//            
//            
//        });
//    }
//    
}
