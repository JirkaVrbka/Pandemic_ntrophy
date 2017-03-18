/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.Interfaces.handlers;

/**
 *
 * @author Jiri Vrbka
 */
public interface IRoundHandler {
 
    /**
     * Gets round current value.
     * 
     * @return integer bigger equal zero
     */
    public int getRound();
    
    /**
     * adds value by speed
     */
    public void addRound();
    
    /**
     * Sets round to zero
     */
    public void resetToZero();
    
}
