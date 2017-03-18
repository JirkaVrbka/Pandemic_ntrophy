/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pandemic.impl.diseases;

import java.awt.Point;
import java.util.List;
import pandemic.Interfaces.IPR;
import pandemic.Interfaces.IPerson;
import pandemic.enums.EDirection;
import pandemic.globals.Utility.Utility;

/**
 * Zombie virus
 * Spreads like flu but not killing. Has change to turn into zombie.
 * If somebody around zombie then he has 50% change to be infected, will be eaten otherwise
 * @author Jiri Vrbka
 */
public class ZombieDisease extends ADisease{

    private static final int CHANGE_TO_BE_ZOMBIE = 1;
    
    @Override
    public void spreadInfection(IPR pr, Point point) {
        if(pr == null || point == null){
            System.out.println((pr == null ? "PR" : "Point") + " is null");
            return;
        }    
        if(pr.getPersonAt(point) == null){
            System.out.println("Person at point "+point+" is null");
            return;
        }
        
        IPerson person = pr.getPersonAt(point);
        
        if(person.isIll() || person.isZombie()){
           List<Point> around = Utility.getPointsAround(point);
           
            if(person.isIll()){
                pr.getPeopleAt(around).forEach(p ->{
                     if (p.isHealthy() && super.shouldBeInfected()) {
                             p.makeIll();
                     }

                 });
                
                if(shouldBeZombie()){
                    person.makeZombie();
                }
                
            }
            
            if(person.isZombie()){
                bite(pr, around);
                person.setDestination(Utility.getPointInDirection(point, EDirection.getRandom()));
            }
        }
        
        
    }
    
    private boolean shouldBeZombie(){
        return  Utility.getRandomPercent() < CHANGE_TO_BE_ZOMBIE;
    }
    
    /**
     * Has 50% change to infect, eat otherwise
     * @param pr
     * @param around 
     */
    private void bite(IPR pr, List<Point> around){
        around.forEach(point -> { 
            if(Utility.getRandomPercent() > 50 && pr.getPersonAt(point) != null){
                pr.getPersonAt(point).makeIll();
            }else{
                pr.removePersonAt(point);
            }
        });
        
        
    }

}
