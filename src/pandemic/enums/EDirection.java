/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.enums;

import java.util.Random;

/**
 *
 * @author Jirka
 */
public enum EDirection {
    WEST,
    EAST,
    NORTH,
    SOUTH,
    
    WEST_NORTH,
    WEST_SOUTH,
    EAST_NORTH,
    EAST_SOUTH;
    
    public static EDirection getRandom(){
        Random rand = new Random();
        return EDirection.values()[rand.nextInt(EDirection.values().length)];
    }

}
