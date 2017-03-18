/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pandemic.impl.handlers;

import pandemic.Interfaces.handlers.ISpeedHandler;

/**
 * 
 * @author Jiri Vrbka
 */
public class SpeedHandler implements ISpeedHandler{
    
    private int speed;
    public int added = 0;
    
    public SpeedHandler(int speed){
        this.speed = speed;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
        added++;
    }

    @Override
    public String getAsString() {
        return (speed >= 0) ? String.valueOf(speed+1) : "1/"+String.valueOf(-(speed)+1);
    }

}
