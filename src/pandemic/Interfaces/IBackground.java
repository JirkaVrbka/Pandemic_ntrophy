/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.Interfaces;


import java.awt.Point;
import javafx.scene.paint.Color;

/**
 * Represents map of pandemic 
 * Interface to get what every background need to have
 * @author Jirka Vrbka
 */
public interface IBackground {
    
    /**
     * Gets what color should be on specific point
     * Check if point is in background
     * @param point to be returned its color on BG
     * @return color of point on BG
     */
    public Color getColorAt(Point point);
    
    /**
     * Check point if is valid and is on BG and can be reached by IPerson
     * @param point to be check
     * @return true is correct point on BG, false otherwise
     */
    public boolean checkPoint(Point point);
    
    /**
     * Height of map
     * @return maximum y coordinate
     */
    public int getHeight();
    
    /**
     * Width of map
     * @return maximum x coordinate
     */
    public int getWidth();
    
    public Point getRandomAllowedPoint();
}
