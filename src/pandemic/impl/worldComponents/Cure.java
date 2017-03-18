/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pandemic.impl.worldComponents;

import pandemic.Interfaces.ICure;
import pandemic.Interfaces.IDisease;
import pandemic.Interfaces.IPerson;
import pandemic.globals.Utility.Utility;
import pandemic.impl.World.Statistics;

/**
 * 
 * @author Jiri Vrbka
 */
public class Cure implements ICure{
    
    private final IDisease disease;
    private double progress;
    //private final Statistics stats;
    
    
    
    public Cure(IDisease disease, Statistics stats){
        this.disease = disease;
        //this.stats = stats;
        progress = 0;
    }

    @Override
    public boolean isCureDone() {
        return progress >= 100;
    }

    @Override
    public double getCureProgress() {
        return  progress;
    }

    @Override
    public double getCurrentCureDevelopSpeed() {
        /*
        //this version counts with count of ill. Is based on it.
        double helpSpeed = 0;
        double singleUnit;
        if(stats.getAll() > 0 && progress < 200){
            singleUnit = (5*(stats.getIll()/(double)stats.getAll()))/(1+((double)disease.getCureDifficuty()/5));
            helpSpeed = singleUnit;helpSpeed += (stats.getDead() > 0 ? 0.15 : 0);
            if(helpSpeed < 0){
                helpSpeed = 0;
            }
        }
        speed += helpSpeed;
        return helpSpeed;*/
        
        //this version counts with pernament speed based by difficulity.
        double toGetOne = Math.log10((double)(disease.getCureDifficuty()+10)/10);
        return 1-(toGetOne < 0 ? 0 : (toGetOne > 1) ? 1 : toGetOne);
        
        
    }

    @Override
    public void developCure() {
        if(progress < 200){
            progress += getCurrentCureDevelopSpeed();
        }
        
    }

    @Override
    public void heal(IPerson person) {
        if(progress >= 100){
            if(Utility.getRandomPercent() < (progress - 100)){
                person.makeHealthy();
            }
        }
    }

}
