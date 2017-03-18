/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl.background;

import java.awt.Point;
import javafx.scene.paint.Color;
import pandemic.Interfaces.IBackground;
import pandemic.enums.EArea;
import pandemic.globals.Utility.Utility;

/**
 *
 * @author Jiri Vrbka
 */
public class ArrayBackground implements IBackground{
    
    EArea[][] background;
    
    /**
     * Create map as 2D array square
     * y
     * /\         
     * |         
     * |         
     * |         
     * |          
     * ----------> x
     * 
     * @param initSize how big should be the map
     */
    public ArrayBackground(int initSizeX, int initSizeY){
        if(initSizeX <= 0){
            throw new IllegalArgumentException("Bad init size x, has to be bigger than zero. Is \""+initSizeX+"\"");
        }
        if(initSizeY <= 0){
            throw new IllegalArgumentException("Bad init size y, has to be bigger than zero. Is \""+initSizeY+"\"");
        }
        
        background = new EArea[initSizeX][initSizeY];
        initBasicMap();
        
    }
    
    /**
     * Create map as 2D array square with init size 100
     * y
     * /\         
     * |         
     * |         
     * |         
     * |          
     * ----------> x
     * 
     */
    public ArrayBackground(){
        this(100,100);
    }

    /**
     * Gets what color should be on specific point
     * Check if point is in background
     * @param point to be returned its color on BG
     * @return color of point on BG or null if bad point
     */
    @Override
    public Color getColorAt(Point point) {
        if(checkPoint(point)){
            
            return background[point.x][point.y].getColor();
            
        }else{
            return null;
        }
    }

    
    /**
     * Check point if is valid and is on BG
     * @param point to be check
     * @return true is correct point on BG, false otherwise
     */
    @Override
    public boolean checkPoint(Point point) {
        if(point == null ){
            return false;
        }
        if(point.x < 0 || point.y < 0){
            return false;
        }
        
        if(point.x >= getWidth() || point.y >= getHeight()){
            return false;
        }
        
        return true;
    }

    /**
     * Height of map
     * @return maximum y coordinate
     */
    @Override
    public int getHeight(){
        return background[0].length;
    }
    
    
    /**
     * Width of map
     * @return maximum x coordinate
     */
    @Override
    public int getWidth(){
        return background.length;
    }
    
    /**
     * Initialize basic map
     * 
     * looks like:
     * -----------------> x
     * |land water land
     * |land water land
     * |land water land
     * |land water land
     * \/
     * y
     */
    public void initBasicMap(){
        for(int x = 0 ; x < getWidth(); x++){
            for(int y = 0; y < getHeight(); y++){
                background[x][y] = EArea.LAND;
            }
        }
    }
    
    /**
     * Initialize basic map
     * 
     * looks like:
     * -----------------> x
     * |land water land
     * |land water land
     * |land water land
     * |land water land
     * \/
     * y
     */
    public void initWaterInMiddleMap(){
        for(int x = 0 ; x < getWidth(); x++){
            for(int y = 0; y < getHeight(); y++){
                EArea toSave;
                
                if(x > getWidth()/3 && x < getWidth()*2/3){
                    toSave = EArea.WATER;
                }else{
                    toSave = EArea.LAND;
                }
                
                background[x][y] = toSave;
            }
        }
    }

    @Override
    public Point getRandomAllowedPoint() {
        return Utility.getRandomDestination(getWidth(),getHeight());
    }
    
}
