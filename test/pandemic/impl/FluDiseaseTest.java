/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl;

import pandemic.impl.worldComponents.Person;
import pandemic.impl.worldComponents.MapPR;
import pandemic.impl.background.ArrayBackground;
import pandemic.impl.diseases.FluDisease;
import java.awt.Point;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import pandemic.Interfaces.IBackground;
import pandemic.Interfaces.IPR;

/**
 *
 * @author Jiri Vrbka
 */
public class FluDiseaseTest {
    FluDisease flu;
    IPR pr;
    
    @Before
    public void setUp() {
        IBackground background = new ArrayBackground((int)(200),200);
        pr = new MapPR(background, 5);
        flu = new FluDisease();
        
    }

    /**
     * Test of spreadInfection method, of class FluDisease.
     */
    @Test
    public void testSpreadInfection(){
        pr.addPerson(new Person(),new Point(10,10));
        pr.addPerson(new Person(),new Point(9,10));
        pr.addPerson(new Person(),new Point(10,11));
        
        flu.setInfectivity(10000);
        flu.spreadInfection(pr,new Point(10,10) );
        
        assertTrue(pr.getPersonAt(new Point(9,10)).isHealthy() );
        assertTrue(pr.getPersonAt(new Point(10,10)).isHealthy());
        assertTrue(pr.getPersonAt(new Point(10,11)).isHealthy());
        
        pr.getPersonAt(new Point(10,10)).makeIll();
        flu.spreadInfection(pr,new Point(10,10) );
        assertTrue( pr.getPersonAt(new Point(10,10)).isIll());
        assertTrue( pr.getPersonAt(new Point(9,10)).isIll());
        
        assertTrue( pr.getPersonAt(new Point(10,11)).isIll());
        
        
    }

 
    
}
