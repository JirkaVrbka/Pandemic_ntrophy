/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl.diseases;

import java.awt.Point;
import pandemic.Interfaces.IDisease;
import pandemic.Interfaces.IPR;
import pandemic.globals.Utility.Utility;

/**
 * Disease type.
 * Disease spreads when two people meets and one is ill.
 * There is immunity constant that makes spreading only change basic
 * @author Jiri Vrbka
 */
public class FluDisease extends ADisease implements IDisease{

    public FluDisease(){
    }
    
    @Override
    public void spreadInfection(IPR pr, Point point){
        if(pr == null || point == null){
            System.out.println("pes");
        }    
        if(pr.getPersonAt(point) == null){
            System.out.println("kocka");
        }
        
        if(pr.getPersonAt(point).isIll()){

           pr.getPeopleAt(Utility.getPointsAround(point)).forEach(p ->{
                if (p.isHealthy() && super.shouldBeInfected()) {
                        p.makeIll();
                }
        
            });
        }
    }

       
    
}
