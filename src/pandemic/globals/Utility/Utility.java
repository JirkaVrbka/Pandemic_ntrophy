/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.globals.Utility;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pandemic.enums.EDirection;

/**
 *
 * @author Jirka
 */
public class Utility {
    private static Random r = new Random();
    
    public static EDirection getDirectionToPoint(Point currentPoint, Point destinationPoint){
        if(currentPoint == null || destinationPoint == null){
            throw new IllegalArgumentException("One of point is null!");
        }
        
        if(currentPoint.x == destinationPoint.x){
            if(currentPoint.y == destinationPoint.y) return null;
            if(currentPoint.y < destinationPoint.y) return EDirection.NORTH;
            if(currentPoint.y > destinationPoint.y) return EDirection.SOUTH;
        }
        
        if(currentPoint.x < destinationPoint.x){
            if(currentPoint.y == destinationPoint.y) return EDirection.EAST;
            if(currentPoint.y < destinationPoint.y) return EDirection.EAST_NORTH;
            if(currentPoint.y > destinationPoint.y) return EDirection.EAST_SOUTH;
        }
        
        if(currentPoint.x > destinationPoint.x){
            if(currentPoint.y == destinationPoint.y) return EDirection.WEST;
            if(currentPoint.y < destinationPoint.y) return EDirection.WEST_NORTH;
            if(currentPoint.y > destinationPoint.y) return EDirection.WEST_SOUTH;
        }
        
        return null;
        
    }
    
    /**
     * Generate new point from [0,0] to [limitX-1,limitY-1]
     * @param limitX integer bigger than zero
     * @param limitYint bigger than zero
     * @return new point
     */
    public static Point getRandomDestination(int limitX, int limitY){
        if(limitX <= 0 || limitY <= 0){
            return new Point(0, 0);
        }
        
        return getRandomPoint(limitX, limitY);
    }
    
    /**
     * Get point in way of direction by single step. No check for point to be in map
     * @param point current point
     * @param dir direction of move
     * @return new point in direction moved by one
     */
    public static Point getPointInDirection(Point point, EDirection dir){
        if(point == null || dir == null){
            return null;
        }
        
        int down;
        int right;
        switch(dir){
            case EAST:
            case EAST_SOUTH:
            case EAST_NORTH:
                right = 1;
                break;
            case WEST:
            case WEST_NORTH:
            case WEST_SOUTH:
                right = -1;
                break;
            default: 
                right = 0;
        }
        
        switch(dir){
            case NORTH:
            case EAST_NORTH:
            case WEST_NORTH:
                down = 1;
                break;
            case SOUTH:
            case EAST_SOUTH:
            case WEST_SOUTH:
                down = -1;
                break;
            default: 
                down = 0;
        }
        
        return new Point(point.x + right, point.y + down);
    }
    
    
    public static List<Point> getPointsAround(Point point){
        List<Point> points = new ArrayList<>();
        
        for(int x = -1; x < 2; x++){
            for(int y = -1; y < 2; y++){
                if(x == 0 && y ==0){
                    continue;
                }else{
                    points.add(new Point((int)point.getX() + x,(int)point.getY() + y));
                }
            }
        }
        
        return points;
    }
    
    /**
     * Gets random number from 0 to limit (exclusively)
     * @param limit max number, Has to be bigger than zero
     * @return random number 0-limit (exclusively)
     */
    public static int getRandomNumber(int limit){
        return r.nextInt(limit);
    }
    
    /**
     * Gets random number 0-100
     * @return random number between 0 to 100
     */
    public static int getRandomPercent(){
        
        return r.nextInt(101);
    }
    
    /**
     * Generate random point by given bounds
     * @param limitX x bound
     * @param limitY y bound
     * @return new point created with random values
     */
    public static Point getRandomPoint(int limitX, int limitY){
        return new Point(r.nextInt(limitX), r.nextInt(limitY));
    }
}
