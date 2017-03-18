/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.Interfaces;

import javafx.scene.paint.Color;
import java.awt.Point;

/**
 * represents one human being in pandemic
 * @author Jiri Vrbka
 */
public interface IPerson {
//    boolean isMoving();
//   Color getColor();
//   void makeIll();
//   boolean isIll();
//   Point getDestination();
//   void setDestination(Point destination);
//   void age();
//    EHealth getHealth();
//    
//    void setHealth(EHealth health);
//    
    Point getDestination();
    
    void setDestination(Point point);
    
    Color getColor();
    
    boolean isHealthy();
    boolean isIll();
    boolean isZombie();
    boolean isDead();
    
    void makeHealthy();
    void makeIll();
    void makeZombie();
    void makeDead();
    
    
    /**
     * Action when one tick is played
     * 
     */
    void aging(int mortality);
}
