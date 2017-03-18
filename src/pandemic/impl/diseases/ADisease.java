/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl.diseases;

import java.util.Random;
import pandemic.Interfaces.IDisease;

/**
 * Partial implementation of IDisease that has all instance in common
 * @author Jiri Vrbka
 */
public abstract class ADisease implements IDisease{
    
    private static int mortality = 1000;
    private static int infectivity = 5;
    private static int cureDifficuty = 50;
    /**
     * To get random values to compare with infectivity
     */
    private static final Random rand = new Random();

    @Override
    public int getMortality() {
        return mortality;
    }

    @Override
    public void setMortality(int mortality) {
        if(mortality < 0){
            throw new IllegalArgumentException("Mortality has to be bigger than zero");
        }
        
        ADisease.mortality = mortality;
    }

    @Override
    public int getInfectivity() {
        return infectivity;
    }

    @Override
    public void setInfectivity(int infectivity) {
        if(infectivity < 0){
            throw new IllegalArgumentException("Infectivity has to be bigger than zero");
        }
        
        ADisease.infectivity = infectivity;
    }

    protected boolean shouldBeInfected(){
        return (rand.nextInt(100) < infectivity);
    }
    
    /**
     * Gets how hard is to develop cure
     * @return 0-100
     */
    @Override
    public int getCureDifficuty(){
        return cureDifficuty;
    }
    
    /**
     * Set how hard is to cure disease
     * @param diff 0-100
     */
    @Override
    public void setCureDifficuty(int diff){
        cureDifficuty = diff < 0 ? 0 : (diff > 100 ? 100 : diff);
    }
    
}
