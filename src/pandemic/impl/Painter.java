/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.impl;

import java.awt.Point;
import java.util.List;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import pandemic.Interfaces.IBackground;
import pandemic.Interfaces.IPainter;
import pandemic.Interfaces.IPerson;

/**
 * Class to draw things on canvas
 * @author Jirka Vrbka
 */
public class Painter implements IPainter{
    
    private PixelWriter pixelWriter;
    private Canvas canvas;
    private IBackground background;
    private int zoom;
    
    public Painter(Canvas canvas, IBackground background){
        this(canvas,background,1);
    }
    
    public Painter(Canvas canvas, IBackground background, int zoom){
        checkConditionsToDraw(canvas, background, zoom);
        this.zoom = zoom;
        this.background = background;
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        pixelWriter = gc.getPixelWriter();
        this.canvas = canvas;
    }
    
    /**
     * Checks if every condition by size and nulls is OK.
     * Check that canvas is not smaller than background. 
     * Check zoom is bigger than zero
     * @param canvas to check its size
     * @param background to check its size
     * @param zoom how much has to be canvas bigger than background
     */
    private void checkConditionsToDraw(Canvas canvas, IBackground background, int zoom){
        if(background == null){
            throw new NullPointerException("Background is null!");
        }
        
        if(canvas == null){
            throw new NullPointerException("Canvas is null!");
        }
        
        if(zoom < 1){
            throw new IllegalArgumentException("zoom has to be bigger that zero!");
        }
        
        if((int)canvas.getHeight() < background.getHeight()){
            throw new IllegalArgumentException("Canvas cannot be smaller (height) than background!");
        }
        
        if((int)canvas.getWidth()< background.getWidth()){
            throw new IllegalArgumentException("Canvas cannot be smaller (width) than background!");
        }
//        
//        if((int)canvas.getHeight() < (background.getHeight()*zoom)){
//            throw new IllegalArgumentException("Canvas cannot be smaller (height) than background with zoom!");
//        }
//        
//        if((int)canvas.getWidth() < (background.getWidth()*zoom)){
//            throw new IllegalArgumentException("Canvas cannot be smaller (width) than background with zoom!");
//        }
    }
    
    /**
     * Check point if is on plan 
     * @param point to be checked
     * @return true if correct point on plan, false otherwise
     */
    private boolean checkPoint(Point point){
        if(point.getX() < 0 
                || point.getY() < 0 
                || point.getX() >= background.getWidth() 
                || point.getY() >= background.getHeight()){
            return false;
        }
        
        return true;
    }
    
    /**
     * Checks and sets new zooming
     * @param zoom int bigger than zero (exclusively)
     */
    public void setZoom(int zoom){
        checkConditionsToDraw(this.canvas, this.background, zoom);
        this.zoom = zoom;
    }
    
    /**
     * Checks and sets background
     * @param background new background
     */
    public void setBackground(IBackground background){
        checkConditionsToDraw(this.canvas, background,this.zoom);
        
        this.background = background;
    }
    
    /**
     * Checks and sets new canvas
     * @param canvas to be set
     */
    public void setCanvas(Canvas canvas){
        checkConditionsToDraw(canvas, this.background, this.zoom);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        pixelWriter = gc.getPixelWriter();
        this.canvas = canvas;
    }
    
    /**
     * Redraw given point by background color
     * @param points to be redraw
     */
    @Override
    public void drawPoints(List<Point> points){
        points.forEach((point) -> {
            drawOnePoint(point, background.getColorAt(point));
        });
    }
    
    /**
     * Draw all people (by their points and health status) into canvas
     * @param people what points to draw
     */
    @Override
    public void drawPeople(Map<Point, IPerson> people){
        
        people.forEach((point,person)->{
            
            if(!checkPoint(point)){
                throw new IllegalArgumentException("Point is not on map!");
            }

            drawOnePoint(point, person.getColor(), true);
//            //if no zoom -> one point = one pixel
//            if(zoom == 1){
//                pixelWriter.setColor(point.x, point.y, );
//            }else{
//                gc.setFill(person.getColor());
//                gc.setLineWidth(0.0);
//                gc.fillOval(point.x*zoom, point.y*zoom, zoom , zoom );
//                
//            }
            
        });
        
    }

    
    /**
     * Draw map initialized onto canvas
     * @param canvas to be draw on
     */
    @Override
    public void drawBackground() {
       for(int x = 0 ; x < background.getWidth(); x++){
            for(int y = 0; y < background.getHeight(); y++){
               // pixelWriter.setColor(x, y, background.getColorAt(new Point(x, y)));
               Point point = new Point(x, y);
               //pixelWriter.setColor(point.x, point.y, background.getColorAt(point));
               drawOnePoint(point, background.getColorAt(point));
            }
        } 
    }
    
    
    /**
     * Draw one pixel with zooming
     * Draw rectangle only!
     * @param point coo on background (w/o zoom)
     * @param color how should pixel looks like
     */
    private void drawOnePoint(Point point,  Color color){
        drawOnePoint(point, color, false);
    }
    
    
    /**
     * Draw one pixel with zooming
     * if no zoom (zoom == 1) -> one point = one pixel
     * @param point coo on background (w/o zoom)
     * @param color how should pixel looks like
     * @param person true if should be draw a person, false if background
     */
    private void drawOnePoint(Point point, Color color, boolean person){
        if(!checkPoint(point)){
            throw new IllegalArgumentException("Point is not on map!");
        }
        
        //pixelWriter.setColor(point.x, point.y, color);
        
        //if no zoom -> one point = one pixel
        if(zoom == 1){
            pixelWriter.setColor(point.x, point.y, color);
        }else{
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(color);
            gc.setLineWidth(0.0);
            if(person){
                gc.fillOval(point.x*zoom, point.y*zoom, zoom , zoom );
            }else{
                gc.fillRect(point.x*zoom, point.y*zoom, zoom , zoom );
            }
        }
    
      }
    
    
    
}
