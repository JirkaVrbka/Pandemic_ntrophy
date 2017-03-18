/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.Interfaces.handlers;

/**
 * Handles speed and its string representation
 * @author Jiri Vrbka
 */
public interface ISpeedHandler {
    /**
     * Gets speed by int value
     * @return number
     */
    int getSpeed();
    /**
     * sets speed 
     * @param speed to be saved
     */
    void setSpeed(int speed);
    /**
     * Gets representation of speed as string 
     * @return 
     */
    String getAsString();
    
}
