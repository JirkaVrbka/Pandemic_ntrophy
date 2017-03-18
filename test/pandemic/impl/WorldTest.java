/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl;

import pandemic.impl.worldComponents.Person;
import pandemic.impl.worldComponents.MapPR;
import pandemic.impl.worldComponents.SingleDestination;
import pandemic.impl.background.ArrayBackground;
import pandemic.impl.diseases.FluDisease;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import pandemic.Interfaces.IBackground;
import pandemic.Interfaces.IDisease;
import pandemic.Interfaces.IPR;
import pandemic.Interfaces.IPerson;

/**
 *
 * @author Jiri Vrbka
 */
public class WorldTest {
    World world;
    public WorldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        IBackground background = new ArrayBackground((int)(400),400);
        IPR pr = new MapPR(background,5);
        FluDisease flu = new FluDisease();
        world = new World(pr, background, flu, new SingleDestination(new Point(0,0), 0));
    }

    /**
     * Test of addPerson method, of class World.
     */
    @Ignore
    @Test
    public void testAddPerson() {
        System.out.println("addPerson");
        Point point = null;
        IPerson person = null;
        World instance = null;
        boolean expResult = false;
        boolean result = instance.addPerson(point, person);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doRound method, of class World.
     */
    @Test
    public void testDoRoundMoveLeft() {
        System.out.println("doRound");
        Point initPoint = new Point(10, 10);
        Person person = new Person(Person.EHealth.HEALTHY);
        Point destPoint = new Point(100, 10);
        person.setDestination(destPoint);
        world.addPerson(initPoint, person);
        
        for(int i = 1; i < 90; i++){
            world.doRound();
            Point newPoint = new Point(initPoint.x+i,initPoint.y);
            assertEquals("round "+i,newPoint, world.getPR().getPointOfPerson(person));
        }
        
        
    }
    
    /**
     * Test of doRound method, of class World.
     */
    @Test
    public void testDoRoundMoveRight() {
        System.out.println("doRound");
        Point initPoint = new Point(100, 10);
        Person person = new Person(Person.EHealth.HEALTHY);
        Point destPoint = new Point(10, 10);
        person.setDestination(destPoint);
        world.addPerson(initPoint, person);
        
        for(int i = 1; i < 90; i++){
            world.doRound();
            Point newPoint = new Point(initPoint.x-i,initPoint.y);
            assertEquals("round "+i,newPoint, world.getPR().getPointOfPerson(person));
        }
        
        
    }


    /**
     * Test of doRound method, of class World.
     */
    @Test
    public void testDoRoundMoveUp() {
        System.out.println("doRound");
        Point initPoint = new Point(10, 10);
        Person person = new Person(Person.EHealth.HEALTHY);
        Point destPoint = new Point(10, 100);
        person.setDestination(destPoint);
        world.addPerson(initPoint, person);
        
        for(int i = 1; i < 90; i++){
            world.doRound();
            Point newPoint = new Point(initPoint.x,initPoint.y+i);
            assertEquals("round "+i,newPoint, world.getPR().getPointOfPerson(person));
        }
        
        
    }
    
    /**
     * Test of doRound method, of class World.
     */
    @Test
    public void testDoRoundMoveDown() {
        System.out.println("doRound");
        Point initPoint = new Point(10, 100);
        Person person = new Person(Person.EHealth.HEALTHY);
        Point destPoint = new Point(10, 10);
        person.setDestination(destPoint);
        world.addPerson(initPoint, person);
        
        for(int i = 1; i < 90; i++){
            world.doRound();
            Point newPoint = new Point(initPoint.x,initPoint.y-i);
            assertEquals("round "+i,newPoint, world.getPR().getPointOfPerson(person));
        }
        
        
    }
    
    /**
     * Test of doRound method, of class World.
     */
    @Test
    public void testDoRoundMoveLeftUp() {
        System.out.println("doRound");
        Point initPoint = new Point(10, 10);
        Person person = new Person(Person.EHealth.HEALTHY);
        Point destPoint = new Point(100, 100);
        person.setDestination(destPoint);
        world.addPerson(initPoint, person);
        
        for(int i = 1; i < 90; i++){
            world.doRound();
            Point newPoint = new Point(initPoint.x+i,initPoint.y+i);
            assertEquals("round "+i,newPoint, world.getPR().getPointOfPerson(person));
        }
        
        
    }
    
    /**
     * Test of doRound method, of class World.
     */
    @Test
    public void testDoRoundMoveLeftDown() {
        System.out.println("doRound");
        Point initPoint = new Point(10, 100);
        Person person = new Person(Person.EHealth.HEALTHY);
        Point destPoint = new Point(100, 10);
        person.setDestination(destPoint);
        world.addPerson(initPoint, person);
        
        for(int i = 1; i < 90; i++){
            world.doRound();
            Point newPoint = new Point(initPoint.x+i,initPoint.y-i);
            assertEquals("round "+i,newPoint, world.getPR().getPointOfPerson(person));
        }
        
        
    }
    
    /**
     * Test of doRound method, of class World.
     */
    @Test
    public void testDoRoundMoveRightUp() {
        System.out.println("doRound");
        Point initPoint = new Point(100, 10);
        Person person = new Person(Person.EHealth.HEALTHY);
        Point destPoint = new Point(10, 100);
        person.setDestination(destPoint);
        world.addPerson(initPoint, person);
        
        for(int i = 1; i < 90; i++){
            world.doRound();
            Point newPoint = new Point(initPoint.x-i,initPoint.y+i);
            assertEquals("round "+i,newPoint, world.getPR().getPointOfPerson(person));
        }
        
        
    }
    
    
    
    
    /**
     * Test of doRound method, of class World.
     */
    @Test
    public void testDoRoundMoveRightDown() {
        System.out.println("doRound");
        Point initPoint = new Point(100, 100);
        Person person = new Person(Person.EHealth.HEALTHY);
        Point destPoint = new Point(10, 10);
        person.setDestination(destPoint);
        world.addPerson(initPoint, person);
        
        for(int i = 1; i < 90; i++){
            world.doRound();
            Point newPoint = new Point(initPoint.x-i,initPoint.y-i);
            assertEquals("round "+i,newPoint, world.getPR().getPointOfPerson(person));
        }
        
        
    }
    
    
    /**
     * Test of doRound method, of class World.
     */
    @Test
    public void testDoRoundMovesHealthyOnly() {
        System.out.println("doRound");
        List<IPerson> list = generatePeopleToWorldHealth(world);
        Map<IPerson,Point> map1 = new HashMap<>();
        Map<IPerson,Point> map2 = new HashMap<>();
        
        for(IPerson person : list){
            map1.put(person,world.getPR().getPointOfPerson(person));
        }
        
        for(int i = 0; i < 2000; i++){
            world.doRound();
            
            map1.forEach((person, savedPoint) -> {
                Point givenPoint = world.getPR().getPointOfPerson(person);
                
                assertTrue("Too long jump!! savedPoint/givenPoint = "+savedPoint.toString()+"/"+givenPoint.toString(), (Math.abs(givenPoint.x - savedPoint.x) <= 1 || Math.abs(givenPoint.x - savedPoint.x) <= 1));
                
                map2.put(person, givenPoint);
            });
            
            map1.clear();
            map1.putAll(map2);
            map2.clear();
        }
        
    }
    
    /**
     * Test of doRound method, of class World.
     */
    @Test
    public void testDoRoundMovesHealthyMixed() {
        System.out.println("doRound");
        List<IPerson> list = generatePeopleToWorldMixed(world);
        Map<IPerson,Point> map1 = new HashMap<>();
        Map<IPerson,Point> map2 = new HashMap<>();
        
        for(IPerson person : list){
            map1.put(person,world.getPR().getPointOfPerson(person));
        }
        
        for(int i = 0; i < 2000; i++){
            world.doRound();
            
            map1.forEach((person, savedPoint) -> {
                Point givenPoint = world.getPR().getPointOfPerson(person);
                
                assertTrue("Too long jump!! savedPoint/givenPoint = "+savedPoint.toString()+"/"+givenPoint.toString(), (Math.abs(givenPoint.x - savedPoint.x) <= 1 || Math.abs(givenPoint.x - savedPoint.x) <= 1));
                
                map2.put(person, givenPoint);
            });
            
            map1.clear();
            map1.putAll(map2);
            map2.clear();
        }
        
    }
    
    
    private List<IPerson> generatePeopleToWorldMixed(World world){
        List<IPerson> people = new ArrayList<>();
        
        for(int j = -10; j <-5 ; j = j + 5){
            for(int i = 10; i < 70; i++){
                IPerson person;

                if(i % 25 == 0){
                    person = new pandemic.impl.worldComponents.Person(Person.EHealth.ILL);
                }else{
                    person = new pandemic.impl.worldComponents.Person(Person.EHealth.HEALTHY);
                }

                boolean added;
                added = world.addPerson(new Point(i+j, i), person);

                if(added){
                    people.add(person);
                }
            }
        }
        
        return people;
    }
    
    
    private List<IPerson> generatePeopleToWorldHealth(World world){
        List<IPerson> people = new ArrayList<>();
        
        for(int j = -10; j <-5 ; j = j + 5){
            for(int i = 10; i < 70; i++){
                boolean added;
                IPerson person = new pandemic.impl.worldComponents.Person(Person.EHealth.HEALTHY);
                added = world.addPerson(new Point(i+j, i), person);

                if(added){
                    people.add(person);
                }

            }
        }
        
        return people;
    }
    
    /**
     * Test of getMovedFrom method, of class World.
     */
    @Ignore
    @Test
    public void testGetMovedFrom() {
        System.out.println("getMovedFrom");
        World instance = null;
        List<Point> expResult = null;
        List<Point> result = instance.getMovedFrom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
