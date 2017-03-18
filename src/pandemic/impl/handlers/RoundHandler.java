/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pandemic.impl.handlers;

import pandemic.Interfaces.handlers.IRoundHandler;
import pandemic.Interfaces.handlers.ISpeedHandler;

/**
 * 
 * @author Jiri Vrbka
 */
public class RoundHandler implements IRoundHandler{
    private final ISpeedHandler speed;
    private double currentRound;
   
    public RoundHandler(ISpeedHandler speed){
        this.speed = speed;
        currentRound = 0;
    }  
    

    @Override
    public int getRound() {
        return (int)currentRound;
    }

    @Override
    public void addRound() {
        currentRound += speed.getSpeed() > 0 ? speed.getSpeed() : 1; //+= getSpeedLikeDouble();
    }
    
    /**
     * Get speed as double. 
     * for example: 2,1/2,1/8,10
     * @return speed as double
     */
    private double getSpeedLikeDouble(){
        int speedInt =  speed.getSpeed();
        
        return speedInt > 0 ? speedInt + 1 : ( speedInt < 0 ? ( -1 / ( (double) speedInt-1)) : 1);
    }

    @Override
    public void resetToZero() {
        currentRound = 0;
    }

}
