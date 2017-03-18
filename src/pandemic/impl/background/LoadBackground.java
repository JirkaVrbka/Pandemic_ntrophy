/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pandemic.impl.background;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javafx.scene.paint.Color;
import pandemic.Interfaces.IBackground;
import pandemic.globals.Utility.Utility;
import pandemic.globals.Utility.UtilityBackground;

/**
 * 
 * @author Jiri Vrbka
 */
public class LoadBackground implements IBackground{
    
    private Map<Point, Color> background;
    private Set<Point> allowed;
    private int height, width;
    
    /**
     * Create map as 2D array square with init values from file
     * y
     * /\         
     * |         
     * |         
     * |         
     * |          
     * ----------> x
     * 
     * @param propertiesPath
     * @param height
     * @param width
     */
    public LoadBackground(String propertiesPath, int height, int width){
        background = new HashMap<>();
        allowed = new HashSet<>();
        this.height = height;
        this.width = width;
        
        if(!UtilityBackground.loadBackgroundFromFile(propertiesPath, allowed, background)){
            throw new IllegalArgumentException("Cannot load map from file: "+propertiesPath);
        }
        
    }

    @Override
    public Color getColorAt(Point point) {
        
        return background.getOrDefault(point, Color.BLUE);
    }

    @Override
    public boolean checkPoint(Point point) {
        return allowed.contains(point) 
                && point.x >= 0
                && point.x < width
                && point.y >= 0
                && point.y < height;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public Point getRandomAllowedPoint() {
        int index = Utility.getRandomNumber(allowed.size());//rand.nextInt(set.size());
        Iterator<Point> iter = allowed.iterator();
        for (int i = 0; i < index; i++) {
            iter.next();
        }
        return iter.next();
    }

}
