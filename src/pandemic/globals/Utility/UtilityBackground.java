/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pandemic.globals.Utility;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 * 
 * @author Jiri Vrbka
 */
public class UtilityBackground {

    /**
     * Takes file given, reads data in correct format and saves it into two collections by its purpose.
     * format:
     *   allowed (30,7)
     *   allowed (30,8)
     *   color (0,0) = (255, 0, 0)
     *   color (0,1) = (255, 0, 0)
     * @param path where file is
     * @param allowedPoints there will be added points marked as allowed
     * @param mapColors there will be added points marked as color
     * @return true if no error occurs
     */
    public static boolean loadBackgroundFromFile(String path, Collection<Point> allowedPoints, Map<Point, Color> mapColors){
        final String allowed = "allowed";
        final String color = "color";
        
        try(BufferedReader br =
                   new BufferedReader(new FileReader(path))){
            String line;
            while((line = br.readLine()) != null){
                String point;
                
                
                
                if(line.equals("") || line.startsWith("#")){
                    continue;
                }
                
                if(line.startsWith(allowed)){
                    line = line.replace(allowed+" ", "");
                    allowedPoints.add(createPointFromString(line));
                    
                    
                }else if(line.startsWith(color)){
                    line = line.replace(color+" ", "");
                    String[] splits = line.split(" = ",2);
                    mapColors.put(createPointFromString(splits[0]), getColorFromString(splits[1]));
                    
                }else{
                    System.out.println("Not valid command: " + line);
                }
                
                
            }
        
        }catch(IOException e){
            System.out.println(e.toString());
            return false;
        }
        
        return true;
    }
    
    /**
     * Creates new point be string
     * @param stringPoint point in format (X,Y). ex. (3,4)
     * @return new Point created, null in bad format
     */
    private static Point createPointFromString(String stringPoint){
        final String matcherForPoint = "^[(][0-9]+,[0-9]+[)]{1}$";
        
        if(stringPoint.matches(matcherForPoint)){
            stringPoint = stringPoint.replace("(", "");
            stringPoint = stringPoint.replace(")", "");
            String[] splits = stringPoint.split(",", 2);
            return new Point(Integer.parseInt(splits[0]), Integer.parseInt(splits[1]));
            
        }else{
            return null;
        }
        
    }
    
    /**
     * Creates new color by string
     * @param stringPoint color in format (X,Y,Z). ex. (3,4,12)
     * @return new color created, null in bad format
     */
    private static Color getColorFromString(String stringColor){
        final String matcherForColor = "^[(][0-9]+,[0-9]+,[0-9]+[)]{1}$";
        
        if(stringColor.matches(matcherForColor)){
            stringColor = stringColor.replace("(", "");
            stringColor = stringColor.replace(")", "");
            String[] splits = stringColor.split(",", 3);
            return new Color(Double.parseDouble(splits[0])/255, Double.parseDouble(splits[1])/255, Double.parseDouble(splits[2])/255,1);
            
        }else{
            return null;
        }
    }
    
}
