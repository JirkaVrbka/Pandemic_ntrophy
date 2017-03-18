/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pandemic.impl.worldComponents;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import pandemic.Interfaces.IForcedDestination;
import pandemic.globals.Utility.Utility;

/**
 * 
 * @author Jiri Vrbka
 */
public class SingleDestination implements IForcedDestination{

    private final Point destination;
    private int probability;
    
    public SingleDestination(Point destination, int probability){
        this.destination = destination;
        this.probability = probability;
    }
    
    @Override
    public List<Point> getDestinations() {
        List<Point> list = new ArrayList<>();
        list.add(destination);
        
        return list;
    }

    @Override
    public int getPropability() {
        return probability;
    }

    @Override
    public void setProbability(int probability) {
        this.probability = probability;
    }

    @Override
    public boolean shouldGoToDestination() {
        return Utility.getRandomPercent() < probability;
    }

    @Override
    public Point getDestinationToGo() {
        return destination;
    }

}
