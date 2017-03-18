/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.globals;

import pandemic.globals.Utility.Utility;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pandemic.enums.EDirection;

/**
 *
 * @author Jiri Vrbka
 */
public class UtilityTest {
    
    public UtilityTest() {
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
     * Test of getDirectionToPoint method, of class Utility.
     */
    @Test
    public void testGetDirectionToPoint() {
        System.out.println("getDirectionToPoint");
        Point currentPoint = new Point(0, 0);
        Point destinationPoint = new Point(0, 0);
        EDirection expResult = null;
        EDirection result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Result should be null",expResult, result);
        
        currentPoint = new Point(0, 0);
        destinationPoint = new Point(1, 0);
        expResult = EDirection.EAST;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
        
        currentPoint = new Point(0, 0);
        destinationPoint = new Point(100, 0);
        expResult = EDirection.EAST;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
        
        currentPoint = new Point(0, 0);
        destinationPoint = new Point(-1, 0);
        expResult = EDirection.WEST;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
        
        currentPoint = new Point(0, 0);
        destinationPoint = new Point(-51, 0);
        expResult = EDirection.WEST;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
        
        currentPoint = new Point(0, 0);
        destinationPoint = new Point(0, 1);
        expResult = EDirection.NORTH;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
        
        currentPoint = new Point(0, 0);
        destinationPoint = new Point(0, 541574);
        expResult = EDirection.NORTH;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
        
        currentPoint = new Point(0, 0);
        destinationPoint = new Point(0, -1);
        expResult = EDirection.SOUTH;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
        
        currentPoint = new Point(0, 0);
        destinationPoint = new Point(0, -450);
        expResult = EDirection.SOUTH;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
        
        currentPoint = new Point(0, 0);
        destinationPoint = new Point(1, 1);
        expResult = EDirection.EAST_NORTH;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
        
        currentPoint = new Point(0, 0);
        destinationPoint = new Point(10, 540);
        expResult = EDirection.EAST_NORTH;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
        
        currentPoint = new Point(0, 0);
        destinationPoint = new Point(-1, -1);
        expResult = EDirection.WEST_SOUTH;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
        
        currentPoint = new Point(0, 0);
        destinationPoint = new Point(-145, -10);
        expResult = EDirection.WEST_SOUTH;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
        
        currentPoint = new Point(5, 4);
        destinationPoint = new Point(1, 2);
        expResult = EDirection.WEST_SOUTH;
        result = Utility.getDirectionToPoint(currentPoint, destinationPoint);
        assertEquals("Checking direction",expResult, result);
    }

    /**
     * Test of getRandomDestination method, of class Utility.
     */
    @Test
    public void testGetRandomDestination() {
        System.out.println("getRandomDestination");
        int limitX = 0;
        int limitY = 0;
        Point expResult = new Point(0, 0);
        Point result = Utility.getRandomDestination(limitX, limitY);
        assertEquals(expResult, result);
        
        limitX = 1;
        limitY = 1;
        expResult = new Point(1, 1);
        result = Utility.getRandomDestination(limitX, limitY);
        assertTrue(result.x >= 0 );
        assertTrue(result.y >= 0 );
        assertTrue(result.x < limitX );
        assertTrue(result.y < limitY );
        
        limitX = -1;
        limitY = 10;
        expResult = new Point(0, 0);
        result = Utility.getRandomDestination(limitX, limitY);
        assertEquals(expResult, result);
        
        limitX = 10;
        limitY = 10;
        result = Utility.getRandomDestination(limitX, limitY);
        assertTrue(result.x >= 0 );
        assertTrue(result.y >= 0 );
        assertTrue(result.x < limitX );
        assertTrue(result.y < limitY );
    }

    /**
     * Test of getPointInDirection method, of class Utility.
     */
    @Test
    public void testGetPointInDirection() {
        System.out.println("getPointInDirection");
        Point point = new Point(0, 0);
        EDirection dir = EDirection.EAST;
        Point expResult = new Point(1, 0);
        Point result = Utility.getPointInDirection(point, dir);
        assertEquals(expResult, result);
        
        point = new Point(0, 0);
        dir = EDirection.NORTH;
        expResult = new Point(0, 1);
        result = Utility.getPointInDirection(point, dir);
        assertEquals(expResult, result);
        
        point = new Point(0, 0);
        dir = EDirection.SOUTH;
        expResult = new Point(0, -1);
        result = Utility.getPointInDirection(point, dir);
        assertEquals(expResult, result);
     
        point = new Point(0, 0);
        dir = EDirection.WEST;
        expResult = new Point(-1, 0);
        result = Utility.getPointInDirection(point, dir);
        assertEquals(expResult, result);
        
        point = new Point(0, 0);
        dir = EDirection.WEST_NORTH;
        expResult = new Point(-1, 1);
        result = Utility.getPointInDirection(point, dir);
        assertEquals(expResult, result);
        
        point = new Point(0, 0);
        dir = EDirection.WEST_SOUTH;
        expResult = new Point(-1, -1);
        result = Utility.getPointInDirection(point, dir);
        assertEquals(expResult, result);
        
        point = new Point(0, 0);
        dir = EDirection.EAST_SOUTH;
        expResult = new Point(1, -1);
        result = Utility.getPointInDirection(point, dir);
        assertEquals(expResult, result);
        
        point = new Point(0, 0);
        dir = EDirection.EAST_NORTH;
        expResult = new Point(1, 1);
        result = Utility.getPointInDirection(point, dir);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPointsAround method, of class Utility.
     */
    @Test
    public void testGetPointsAround() {
        System.out.println("getPointsAround");
        Point point = new Point(5, 5);
        List<Point> expResult = new ArrayList<>();
        expResult.add(new Point(4, 4));
        expResult.add(new Point(4, 5));
        expResult.add(new Point(4, 6));
        expResult.add(new Point(5, 4));
        expResult.add(new Point(5, 6));
        expResult.add(new Point(6, 4));
        expResult.add(new Point(6, 5));
        expResult.add(new Point(6, 6));
        List<Point> result = Utility.getPointsAround(point);
        assertEquals(expResult, result);
    }
    
}
