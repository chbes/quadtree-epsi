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
        
        boolean inListe;
        
        List<Point> points = new ArrayList();
        
        for(int i=0;i<nb;++i) {
            
            inListe = false;
            
            Point newPoint = newRandomPoint(maxSize);
            
            for(Point p : points) {
                
                if(newPoint.isEquals(p)) {
                    --i;
                    inListe = true;
                }
            }
            
            if(!inListe) {
                points.add(newPoint);
            }
        }
        
        return points;
    }
    
}
