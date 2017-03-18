/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.Interfaces;

import java.awt.Point;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Represents people and their actions
 * @author Jiri Vrbka
 */
public interface IPR {
    
    /**
     * Add person into PR structure
     * @param person to be saved
     * @param point to be placed in
     * @return true if placed, false if there is a person on point
     */
    boolean addPerson(IPerson person, Point point);
    
    /**
     * Gets all people handled by PR
     * @return all people
     */
    Collection<IPerson> getPeople();
    
    /**
     * Gets all points that has a person on it
     * @return Collection of points where is a person
     */
    Collection<Point> getActivePoints();
   
    
    /**
     * Moves Person from current point to future point if possible.
     * Possible means: there is a person on currentPoint, no person on futurePoint
     * @param currentPoint move person from if there is any
     * @param futurePoint move person from currentPoint into, if empty
     * @return  true if person is moved, false if anything went wrong
     */
    boolean moveFromPointToPoint(Point currentPoint, Point futurePoint);
    
    Map<Point, IPerson> getAsMap();
    
    Point getPointOfPerson(IPerson person);
    
    /**
     * Finds person at point
     * @param point
     * @return person found or null if no person
     */
    IPerson getPersonAt(Point point);
    
   // List<Point> movePeople();
    
    void agingForPeople(int motrality);
    
    List<IPerson> getPeopleAt(List<Point> points);
    
    /**
     * Removes person on point if any
     * @param point 
     */
    void removePersonAt(Point point);
}
