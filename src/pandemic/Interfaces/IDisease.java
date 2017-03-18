/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.Interfaces;

import java.awt.Point;

/**
 *
 * @author Jiri Vrbka
 */
public interface IDisease {
    
    /**
     * Sets mortality of disease
     * @param mortality number between 0-100. If not, let value untouched
     */
    void setMortality(int mortality);
    
    /**
     * Gets mortality of disease
     * @return number between 0-100
     */
    int getMortality();
    
    
    /**
     * Sets infectivity of disease
     * @param infectivity number between 0-100. If not, let value untouched
     */
    void setInfectivity(int infectivity);
    
    /**
     * Gets infectivity
     * @return number between 0-100
     */
    int getInfectivity();
    
    /**
     * Spread infection by its specification on point
     * @param pr collection of all people on map
     * @param point coordinate on background
     */
    void spreadInfection(IPR pr, Point point);
    
    /**
     * Spread infection for all people in map 
     * @param people all people on world
     */
    //void spreadInfection(Map<Point, IPerson> people);
    
    /**
     * Gets how hard is to develop cure
     * @return 0-100
     */
    int getCureDifficuty();
    
    /**
     * Set how hard is to cure disease
     * @param diff 0-100
     */
    void setCureDifficuty(int diff);
    
}
