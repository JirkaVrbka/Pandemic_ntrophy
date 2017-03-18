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
import pandemic.globals.Utility.Utility;

/**
 * Represents bad love
 * If two healthy people meet alone them one gets ill
 * @author Jiri Vrbka
 */
public class LoverDisease extends ADisease{

    @Override
    public void spreadInfection(IPR pr, Point point) {
        IPerson person = pr.getPersonAt(point);
        
        if(person.isHealthy()){
            List<IPerson> around = pr.getPeopleAt(Utility.getPointsAround(point));
            if(around.size() == 1 && around.get(0).isHealthy() &&shouldBeInfected()){
                person.makeIll();
            }
        }
    }

}
