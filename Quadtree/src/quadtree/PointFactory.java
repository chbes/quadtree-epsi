package quadtree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cbes
 */
public class PointFactory {
    
    public static Point newPoint(int x, int y) {
        
        Point newPoint = new Point(x,y);
        return newPoint;
    }
    
    public static Point newRandomPoint(int maxSize) {
        
        int x = (int)(Math.random() * (maxSize + 1));
        int y = (int)(Math.random() * (maxSize + 1));
        Point newPoint = new Point(x,y);
        return newPoint;
    }
    
    public static List<Point> newRandomPoints(int maxSize, int nb) {
        
        List<Point> points = new ArrayList();
        
        for(int i=0;i<nb;i++) {
            points.add(newRandomPoint(maxSize));
        }
        
        return points;
    }
    
}
