/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pandemic.globals;

import pandemic.globals.Utility.Utility;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import pandemic.Interfaces.IDisease;
import pandemic.Interfaces.IPR;
import pandemic.Interfaces.IPerson;
import pandemic.enums.EDisease;
import pandemic.impl.worldComponents.Person;
import pandemic.impl.diseases.AreaDisease;
import pandemic.impl.diseases.DiabetesDisease;
import pandemic.impl.diseases.FluDisease;
import pandemic.impl.diseases.LoverDisease;
import pandemic.impl.diseases.PatogenDisease;
import pandemic.impl.diseases.RageDisease;
import pandemic.impl.diseases.ZombieDisease;

/**
 * Class for instancing
 * @author Jiri Vrbka
 */
public class Initiator {

    
    public static ZombieDisease initZombie(){
        return new ZombieDisease();
    }

    public static  LoverDisease initLover(){
        return new LoverDisease();
    }
   
    public static  FluDisease initFlu(){
        return new FluDisease();
    }
    
    public static  RageDisease initRage(){
        return new RageDisease();
    }
    
    public static  PatogenDisease initPatogen(IPR pr){
        Set<IPerson> set = new HashSet();
        Point randomPoint = Utility.getRandomDestination(50, 50);
        if(pr.getPersonAt(randomPoint) == null){
            IPerson person = new Person();
            pr.addPerson(person, randomPoint);
        }
        set.add(pr.getPersonAt(randomPoint));
        return new PatogenDisease(set);
    }
    
    public static  AreaDisease initRadioactivity(){
        Set<Point> area = new HashSet<>();
        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                area.add(new Point(x, y));
            }
        }
        return new AreaDisease(area);
    }
    
    public static  DiabetesDisease initDiabetes(){
        return new DiabetesDisease();
    }
    
    public static IDisease initDisease(EDisease disease, IPR pr){
        IDisease dis;
        switch(disease){
            case FLU:
                dis = Initiator.initFlu();
                break;
            case DIABETES:
                dis = Initiator.initDiabetes();
                break;
            case PATOGEN:
                dis = Initiator.initPatogen(pr);
                break;
            case RADIATION:
                dis = Initiator.initRadioactivity();
                break;
            case RAGE:
                dis = Initiator.initRage();
                break;
            case ZOMBIE:
                dis = Initiator.initZombie();
                break;
            case LOVERS:
                dis = Initiator.initLover();
                break;
            default:
                throw new IllegalArgumentException("Unsupported disease!! "+disease.toString());
                
        }
        
        return dis;
    }
}
