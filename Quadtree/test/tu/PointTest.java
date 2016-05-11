package tu;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import quadtree.Point;
import quadtree.PointFactory;
import quadtree.Quadtree;
import quadtree.QuadtreeFactory;

/**
 *
 * @author cbes
 */
public class PointTest {
    
    @Test
    public void souldMakeAPoint() {
        
        Point onePoint = PointFactory.newPoint(5, 8);
        
        assertEquals(onePoint.getX(), 5);
        assertEquals(onePoint.getY(), 8);
    }
    
    @Test
    public void souldMakeARandomPoint() {
        
        Point onePoint = PointFactory.newRandomPoint(10);
        
        assertTrue(onePoint.getX() <= 10);
        assertTrue(onePoint.getY() <= 10);
    }
    
    @Test
    public void souldMakeManyRandomPoint() {
        
        List<Point> manyPoints = PointFactory.newRandomPoints(10,50);
        
        assertEquals(manyPoints.size(), 50);
    }
    
    @Test
    public void souldIsEquelsPoints() {
        
        Point p1 = PointFactory.newPoint(12, 34);
        Point p2 = PointFactory.newPoint(12, 34);
        
        assertTrue(p1.isEquals(p2));
    }
    
}
