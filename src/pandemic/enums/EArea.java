/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.enums;

import javafx.scene.paint.Color;

/**
 *
 * @author Jirka
 */
public enum EArea {
    LAND(Color.BURLYWOOD),
    WATER(Color.BLUE);
    
    private Color color;
    
    private EArea(Color color){
        this.color = color;
    }
    
    public Color getColor(){
        return color;
    }
}
