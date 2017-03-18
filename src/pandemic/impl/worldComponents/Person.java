/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl.worldComponents;

import java.awt.Point;
import javafx.scene.paint.Color;
import pandemic.Interfaces.IPerson;
import pandemic.globals.Global;
import pandemic.globals.Utility.Utility;

/**
 * Represents one single person in world
 * @author Jirka
 */
public class Person implements IPerson{
    
    private Point destination;
    private EHealth health;
    private int age = 0;
    private int illTick = 0;
    
    private final int personalDiseaseResistance;
    private final int personalAgeResistance;

    
    
    public static enum EHealth{
        HEALTHY(Color.GREEN),
        ILL(Color.RED),
        ZOMBIE(Color.GREY),
        DEAD(Color.BLACK);

        private final Color color;

        private EHealth(Color color){
            this.color = color;
        }

        public Color getColor(){
            return color;
        }
        
    }
    
    /**
     * Creates new person with specific parameters
     * @param health health of person when creating
     */
    public Person(EHealth health){
        this.health = health;
        //natural difference between deads
        personalAgeResistance = Utility.getRandomPercent();
        personalDiseaseResistance = Utility.getRandomNumber(10);
    }
    
    /**
     * Creates new person with specific parameters
     * health of person when creating - HEALTHY
     */
    public Person(){
        this( EHealth.HEALTHY);
    }


    @Override
    public Point getDestination() {
        return destination;
    }

    @Override
    public void setDestination(Point destination) {
        this.destination = destination;
    }
    
    /**
     * Action when one tick is played
     * 
     * @param mortality tick after person die by disease
     */
    @Override
    public void aging(int mortality){
        if(isDead()){
            return;
        }
        
        age++;
        
        if(isIll() || isZombie()){
            illTick++;
            if(isZombie()){
                illTick += 5;
            }
        }
        
        
        if(shouldBeDead(mortality)){
            this.makeDead();
        }
    }
    
    
    private boolean shouldBeDead(int mortality){
        
        return (illTick > (mortality/*Going in my mind if should be used + personalDiseaseResistance*/) 
                || age > (Global.Variables.PERSON_AVERAGE_LIVE + personalAgeResistance) );
    }
    
    

    @Override
    public Color getColor() {
        return this.health.getColor();
    }

    @Override
    public boolean isHealthy() {
        return health == EHealth.HEALTHY;
    }

    @Override
    public boolean isIll() {
        return health == EHealth.ILL;
    }

    @Override
    public boolean isZombie() {
        return health == EHealth.ZOMBIE;
    }

    @Override
    public boolean isDead() {
        return health == EHealth.DEAD;
    }

    @Override
    public void makeHealthy() {
        health = EHealth.HEALTHY;
    }

    @Override
    public void makeIll() {
        health = EHealth.ILL;
    }

    @Override
    public void makeZombie() {
        health = EHealth.ZOMBIE;
    }

    @Override
    public void makeDead() {
        health = EHealth.DEAD;
    }
    
}
