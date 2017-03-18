/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.Interfaces;

import java.awt.Point;
import java.util.List;
import java.util.Map;

/**
 * Represents entity that can draw points into program
 * @author Jiri Vrbka
 */
public interface IPainter {
    
    /**
     * Draw map initialized onto canvas
     * @param canvas to be draw on
     */
    public void drawBackground();
    
    /**
     * Redraw given point by background color
     * @param points to be redraw
     */
    public void drawPoints(List<Point> points);
    
    /**
     * Draw all people (by their points and health status) into canvas
     * @param people what points to draw
     */
    public void drawPeople(Map<Point, IPerson> people);
}
