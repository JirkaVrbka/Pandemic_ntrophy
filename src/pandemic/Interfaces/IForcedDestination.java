/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.Interfaces;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author Jiri Vrbka
 */
public interface IForcedDestination {
    List<Point> getDestinations();
    int getPropability();
    void setProbability(int probability);
    boolean shouldGoToDestination();
    Point getDestinationToGo();
}
