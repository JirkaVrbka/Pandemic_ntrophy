/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl;

import pandemic.impl.worldComponents.Person;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pandemic.globals.Global;

/**
 *
 * @author Jiri Vrbka
 */
public class PersonTest {
    
    public PersonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

  
    /**
     * Test of aging method, of class Person.
     */
    @Test
    public void testILL() {
        System.out.println("aging");
        Person instance = new Person(Person.EHealth.ILL);
        instance.aging(1000);
        
        //assertEquals("Health should be dead", EHealth.DEAD, instance.getHealth());
        
        instance = new Person(Person.EHealth.ILL);
        instance.aging(1000);
        
        assertTrue("Health should be alive", instance.isIll());
    }
 
    
    /**
     * Test of aging method, of class Person.
     */
    @Test
    public void testHealth() {
        System.out.println("aging");
        Person instance = new Person(Person.EHealth.HEALTHY);
        
        for(int i = 0; i < Global.Variables.PERSON_AVERAGE_LIVE+10; i++){
            instance.aging(1000);
        }
        
        assertTrue("Health should be dead", instance.isDead());
        
        instance = new Person(Person.EHealth.HEALTHY);
        instance.aging(100);
        
        assertTrue("Health should be alive", instance.isHealthy());
    }

    
}
