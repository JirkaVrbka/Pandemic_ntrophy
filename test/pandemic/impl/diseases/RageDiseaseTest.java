/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl.diseases;

import java.awt.Point;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pandemic.Interfaces.IPR;

/**
 *
 * @author Jiri Vrbka
 */
public class RageDiseaseTest {
    
    public RageDiseaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of spreadInfection method, of class RageDisease.
     */
    @Test
    public void testSpreadInfection() {
        System.out.println("spreadInfection");
        IPR pr = null;
        Point point = null;
        RageDisease instance = new RageDisease();
        instance.spreadInfection(pr, point);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testShouldRage() {
        System.out.println("spreadInfection");
        IPR pr = null;
        Point point = null;
        RageDisease instance = new RageDisease();
        instance.spreadInfection(pr, point);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
