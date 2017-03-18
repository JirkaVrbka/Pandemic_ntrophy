/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pandemic.impl.diseases;

import java.awt.Point;
import pandemic.Interfaces.IPR;
import pandemic.Interfaces.IPerson;
import pandemic.globals.Utility.Utility;

/**
 * Represents rage.
 * Make ill when something is in his way.
 * When ill and somebody in his way, kills him.
 * When in his destination, makes healthy again.
 * @author Jiri Vrbka
 */
public class RageDisease extends ADisease{

    @Override
    public void spreadInfection(IPR pr, Point point) {
        IPerson person = pr.getPersonAt(point);
        
        
        if(shouldRage(pr, point) && super.shouldBeInfected()){
            person.makeIll();
        }
        
        if(person.isIll()){
            if(person.getDestination().equals(point)){
                person.makeHealthy();
            }else{
                killInFrontOf(pr, point);  
            }            
            
        }
        
        
    }

    
    private boolean shouldRage(IPR pr, Point point){
        IPerson person = pr.getPersonAt(point);
        if(person.isHealthy() && !person.getDestination().equals(point)){
            
            Point frontPoint = Utility.getPointInDirection(point, Utility.getDirectionToPoint(point, person.getDestination()));
            if(pr.getPersonAt(frontPoint) != null){
                return true;
            }
        }
        
        return false;
    }
    
    private void killInFrontOf(IPR pr, Point point){
        IPerson person = pr.getPersonAt(point);
        if(person.isIll()){
            
            Point frontPoint = Utility.getPointInDirection(point, Utility.getDirectionToPoint(point, person.getDestination()));
            IPerson inFront = pr.getPersonAt(frontPoint);
            if(inFront != null && !inFront.isDead()){
                inFront.makeDead();
            }
        }
    }
    
    

}
