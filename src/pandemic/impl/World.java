/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl;

import pandemic.impl.worldComponents.Person;
import pandemic.impl.worldComponents.Cure;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import pandemic.Interfaces.IBackground;
import pandemic.Interfaces.ICure;
import pandemic.Interfaces.IDisease;
import pandemic.Interfaces.IForcedDestination;
import pandemic.Interfaces.IPR;
import pandemic.Interfaces.IPerson;
import pandemic.enums.EDirection;
import pandemic.enums.EDisease;
import pandemic.globals.Initiator;
import pandemic.globals.Utility.Utility;
import pandemic.impl.background.LoadBackground;
import pandemic.impl.worldComponents.MapPR;
import pandemic.impl.worldComponents.SingleDestination;

/**
 *
 * @author Jiri Vrbka
 */
public final class World {
    IPR pr;
    IBackground background;
    IDisease disease;
    List<Point> movedFrom;
    Statistics statistics;
    ICure cure;
    IForcedDestination forcedDestinations;
    
    /**
     * Create world with parameters:
     * forcedDestination = singleDestination
     * background = loadBackground
     * 
     * @param diseaseType initialize game with this disease
     * @param pathToBackground where to load background from
     */
    public World(EDisease diseaseType, String pathToBackground){
        this.forcedDestinations = new SingleDestination(new Point(100,100), 0);
        this.background = new LoadBackground(pathToBackground, 600, 600);
        this.pr = new MapPR(background,5);
        this.disease = Initiator.initDisease(diseaseType, pr);
        this.movedFrom = new ArrayList<>();
        this.statistics = new Statistics();
        this.cure = new Cure(disease, statistics); 
        
        pr.getPeople().forEach(person -> statistics.addPerson(person));
    }
    
    public World(IPR pr, IBackground background, IDisease disease, IForcedDestination forcedDestination){
        this.pr = pr;
        this.background = background;
        this.disease = disease;
        movedFrom = new ArrayList<>();
        statistics = new Statistics();
        pr.getPeople().forEach(person -> statistics.addPerson(person));
        
        this.cure = new Cure(disease, statistics); 
        forcedDestinations = forcedDestination;
    }
    
    
    
    /**
     * Add person into PR structure
     * @param point to be saved 
     * @param person to be placed in
     * @return true if placed, false if there is a person on point
     */
    public boolean addPerson(Point point, IPerson person) {
        boolean added = pr.addPerson(person, point);
        if(added){
            statistics.addPerson(person);
        }
        return added;
        
    } 
    
    /**
     * For every person do:
     * (set destination)
     * move
     * spread infection
     * aging
     */
    public void doRound(){
        movedFrom.clear();
        statistics.clean();

       Map<Point, IPerson> helpMap = new HashMap<>(pr.getAsMap());
       
       for(Iterator<Map.Entry<Point, IPerson>> it = helpMap.entrySet().iterator(); it.hasNext();)
       {
            Point point = it.next().getKey();
            IPerson person = helpMap.get(point);
            Point movedPoint = null;
            
            if(isValidPerson(person, point)){
                
                setDestination(person, point);
                spreadInfectionForPerson(point);
                movedPoint = movePerson(person, point);
                agePerson(person);
                useCure(person);
                
            }else if(pr.getPersonAt(point) == null){
                movedPoint = point;
            }
            
            
            if(movedPoint != null){
                movedFrom.add(movedPoint);
            }
            
            statistics.addPerson(person);
            
        }
       
       cure.developCure();
            
       
       
    }
    
    private void agePerson(IPerson person){
        person.aging(disease.getMortality());
    }
    
    private void spreadInfectionForPerson(Point point){
        disease.spreadInfection(pr, point);
    }
    
    private void useCure(IPerson person){
        if(cure.isCureDone() && person.isIll()){
            cure.heal(person);
        }
    }
    
    private boolean isValidPerson(IPerson person, Point point){
        return !(pr.getPersonAt(point) == null || pr.getPersonAt(point).isDead());
    }
    
    private void setDestination(IPerson person, Point point){
        if(person.getDestination() == null 
                || person.getDestination().equals(point) 
                || (pr.getPersonAt(person.getDestination()) != null && pr.getPersonAt(person.getDestination()).isDead())){
            
            Point dest;
            if(forcedDestinations.shouldGoToDestination() && 
                    !(dest = forcedDestinations.getDestinationToGo()).equals(point)){
                 
                //dest is already set
            }else{
                dest = background.getRandomAllowedPoint();//(Utility.getRandomDestination(background.getWidth(),background.getHeight()));
            }
            
            person.setDestination(dest);
            
        }
    }
    
    private Point movePerson(IPerson person, Point point){
        boolean moved;
        moved = pr.moveFromPointToPoint(point, Utility.getPointInDirection(point, Utility.getDirectionToPoint(point, person.getDestination())));
        if(!moved){
            moved = pr.moveFromPointToPoint(point, Utility.getPointInDirection(point, EDirection.getRandom()));
        }

        return moved ? point : null;
    }
    
    public void generatePeopleToWorldRandom(int healthy, int ill){
        boolean added;
        
        for(int i = 0; i < healthy; i++){
            Point point = getBackground().getRandomAllowedPoint();
            do{
                added = addPerson(point, new pandemic.impl.worldComponents.Person(Person.EHealth.HEALTHY));
            }while(added);
        }
        
        for(int i = 0; i < ill; i++){
            Point point = getBackground().getRandomAllowedPoint();
            do{
                added = addPerson(point, new pandemic.impl.worldComponents.Person(Person.EHealth.ILL));
            }while(added);
        }
    }
    
    
    
    
    public IBackground getBackground(){
        return background;
    }
    
    public IDisease getDisease(){
        return disease;
    }
    
    public IPR getPR(){
        return pr;
    }
    
    public List<Point> getMovedFrom(){
        return movedFrom;
    }
    
    public List<Point> getRefactoredMovedFrom(){
        List<Point> refactored = new ArrayList<>();
        
        movedFrom.forEach(point ->{
            int zoom = 5;
            for(int x = 0; x < zoom; x++){
                for(int y = 0; y < zoom; y++){
                    refactored.add(new Point(point.x + x, point.y + y));
                }
            }
        
        
        });
        
        
        return refactored;
    }
    
    public int getCountPeople(){
        return pr.getAsMap().size();
    }
    
    public Statistics getStatistics(){
        return statistics;
    }
    
    public ICure getCure(){
        return cure;
    }
    
    public IForcedDestination getForcedDestination(){
        return forcedDestinations;
    }
    
    
    
    
    
    
    public class Statistics{
        private int ill, healthy, dead;

        public int getAll(){
            return ill + healthy + dead;
        }
        
        public int getIll() {
            return ill;
        }

        private void addOneIll(){
            this.ill++;
        }

        public int getHealthy() {
            return healthy;
        }

        private void addOneHealthy() {
            this.healthy++;
        }

        public int getDead() {
            return dead;
        }

        private void addOneDead(){
            this.dead++;
        }
        
        private void addPerson(IPerson person){
            
            if(person == null){
                return;
            }
            
            if(person.isHealthy()){
                addOneHealthy();
            }else if(person.isDead()) {
                addOneDead();
            }else if(person.isIll() || person.isZombie()){
                addOneIll();
            }
        }
        
        private void clean(){
            ill = healthy = dead = 0;
        }
        
        
        
    }
    
}
