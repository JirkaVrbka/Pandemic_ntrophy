/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.Interfaces;

/**
 * Represents current state of cure
 * @author Jiri Vrbka
 */
public interface ICure {
    
    boolean isCureDone();
    double getCureProgress();
    double getCurrentCureDevelopSpeed();
    void developCure();
    
    /**
     * When cure is done, heal person with certain probability.
     * Probability is getting higher over time (over calling developCure after cure done)
     * @param person to be healed
     */
    void heal(IPerson person);
}
