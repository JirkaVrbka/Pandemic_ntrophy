/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl.worldComponents;

import java.awt.Point;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import pandemic.Interfaces.IBackground;
import pandemic.Interfaces.IPR;
import pandemic.Interfaces.IPerson;

/**
 * Represent people in map
 * @author Jiri Vrbka
 */
public class MapPR implements IPR{
    
    private Map<Point, IPerson> people;
    private IBackground background;
    private int zoom;
    /**
     * Create object with people
     * y
     * /\         
     * |         
     * |         
     * |         
     * |          
     * ----------> x
     * 
     * @param background background 
     */
    public MapPR(IBackground background, int zoom){
        this.background = background;
        people = new ConcurrentHashMap<>();
        this.zoom = zoom;
    }
    
    /**
     * Not allowed to have PR w/o limit
     */
    private MapPR(){
        
    }
    
    @Override
    public boolean addPerson(IPerson person, Point point) {
        if(person == null || point == null
            || people.containsKey(point) || people.containsValue(person)){
            return false;
        }
        
        people.put(point, person);
        return true;
        
    }

    @Override
    public Collection<IPerson> getPeople() {
        return Collections.unmodifiableCollection(people.values());
    }

    @Override
    public Collection<Point> getActivePoints() {
        return Collections.unmodifiableCollection(people.keySet());
    }


    @Override
    public boolean moveFromPointToPoint(Point currentPoint, Point futurePoint) {
        
        
        if (checkPoint(futurePoint) && checkPoint(currentPoint) && !people.containsKey(futurePoint)) {
            IPerson person = getPersonAt(currentPoint);
            if(person != null){
                people.remove(currentPoint);
                people.put(futurePoint, person);
                return true;
            }
        }
        
        return false;
    }

    @Override
    public Map<Point, IPerson> getAsMap() {
        return people;
    }

    @Override
    public Point getPointOfPerson(IPerson person) {
        if(person  != null && people.containsValue(person)){
            return people.entrySet().stream().filter(e -> e.getValue().equals(person)).findFirst().get().getKey();
        }
        
        return null;
    }

    /**
     * Finds person at point
     * @param point
     * @return person found or null if no person
     */
    @Override
    public IPerson getPersonAt(Point point) {
        return people.get(point);
    }
    
    /**
     * Check point if is on plan 
     * @param point to be checked
     * @return true if correct point on plan, false otherwise
     */
    private boolean checkPoint(Point point){
        return (point != null
                && point.getX() >= 0 
                    && point.getY() >= 0
                    && point.getX() < (background.getWidth())
                    && point.getY() < (background.getHeight())
                    && background.checkPoint(point)
                );
    }
//
//    @Override
//   public List<Point> movePeople(){
//       Map<Point, IPerson> helpMap = new HashMap<>(people);
//       List<Point> movedFrom = new ArrayList<Point>();
//       
//       for(Iterator<Map.Entry<Point, IPerson>> it = helpMap.entrySet().iterator(); it.hasNext();)
//       {
//            Point point = it.next().getKey();
//            IPerson person = people.get(point);
//            
//              ////preparations////
//            if(person.getHealth() == EHealth.DEAD){
//                continue;
//            }
//            if(person.getDestination() == null || person.getDestination().equals(point)){
//                person.setDestination(Utility.getRandomDestination(limitX,limitY));
//            }
//            
//            ////////////////////
//           
//            boolean moved;
//            moved = moveFromPointToPoint(point, Utility.getPointInDirection(point, Utility.getDirectionToPoint(point, person.getDestination())));
//            if(!moved){
//                moved = moveFromPointToPoint(point, Utility.getPointInDirection(point, EDirection.getRandom()));
//            }
//            
//            if(moved){
//                movedFrom.add(point);
//            }
//        }
//       
//       return movedFrom;       
//   }

   
   /**
    * Iterate through all people and call its aging method
    */
    @Override
    public void agingForPeople(int mortality) {
        people.entrySet().stream().forEach((entry) -> entry.getValue().aging(mortality));
    }
    
    
    @Override
    public List<IPerson> getPeopleAt(List<Point> points){
        return people.entrySet().stream().filter(entry -> points.contains(entry.getKey()))
              .map(Map.Entry::getValue)
              .collect(Collectors.toList());
    }

    @Override
    public void removePersonAt(Point point) {
        people.remove(point);
    }
}
