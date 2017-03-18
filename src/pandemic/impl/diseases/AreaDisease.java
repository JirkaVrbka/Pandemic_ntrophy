/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl.diseases;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import pandemic.Interfaces.IDisease;
import pandemic.Interfaces.IPR;
import pandemic.Interfaces.IPerson;

/**
 * represents radioactivity
 * @author Jiri Vrbka
 */
public class AreaDisease  extends ADisease implements IDisease{

    Set<Point> infectedArea;
    
    public AreaDisease(){
        infectedArea = new HashSet<>();
    }
    
    public AreaDisease(Set<Point> area){
        this();
        infectedArea.addAll(area);
    }
    
    public void addInfectedPoints(Set<Point> area){
        infectedArea.addAll(area);
    }

    @Override
    public void spreadInfection(IPR pr, Point point) {
        if(pr == null || point == null){
            throw new NullPointerException("Argument is null!");
        }
        
        if(infectedArea.contains(point)){
            IPerson person = pr.getPersonAt(point);
            if(person != null && person.isHealthy()){
                if(super.shouldBeInfected()){
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
//            IPerson person = e.getValue();
//            Point point = e.getKey();
//            
//            if(infectedArea.contains(point)){
//                if(person != null && person.isHealthy()){
//                    if(Utility.getRandomPercent() < getInfectivity()){
//                         person.makeIll();
//                    }
//                }
//            }
//            
//        });
//    }
    
}
