package tu;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import quadtree.Point;
import quadtree.Quadtree;
import quadtree.QuadtreeFactory;

/**
 *
 * @author cbes
 */
public class QuadTreeTest {
    
    @Test
    public void hello() {
        assertTrue(true);
    }
    
    @Test
    public void souldAddOnePoint() {
        Quadtree qt = QuadtreeFactory.newQuadtree(10);
        Point p = new Point();
        
        qt.addPoint(p);
        
        assertEquals(qt.getPoints().get(0),p);
    }
    
    @Test
    public void souldAddManyPoints() {
        Quadtree qt = QuadtreeFactory.newQuadtree(10);
        List<Point> listPoints = new ArrayList();
        
        listPoints.add(new Point());
        listPoints.add(new Point());
        listPoints.add(new Point());
        
        qt.addPoints(listPoints);
        
        assertEquals(qt.getPoints(),listPoints);
    }
    
    @Test
    public void souldRemoveOnePoint() {
        Quadtree qt = QuadtreeFactory.newQuadtree(10);
        List<Point> listPoints = new ArrayList();
        
        listPoints.add(new Point());
        listPoints.add(new Point());
        listPoints.add(new Point());
        
        qt.addPoints(listPoints);
        
        Point p = new Point();
        
        qt.addPoint(p);
        
        qt.removePoint(p);
        
        assertEquals(qt.getPoints(),listPoints);
    }
    
    @Test
    public void shouldDepthAt1() {
        
        Quadtree qt = QuadtreeFactory.newQuadtree(10);
        
        qt.addPoint(new Point(6,2));
        qt.addPoint(new Point(1,2));
        qt.addPoint(new Point(1,6));
        
        qt.ventilation();
        
        assertEquals(qt.getDepth(),1);
    }
    
    @Test
    public void shouldDepthAt2() {
        
        Quadtree qt = QuadtreeFactory.newQuadtree(10);
        
        qt.addPoint(new Point(6,2));
        qt.addPoint(new Point(1,2));
        qt.addPoint(new Point(1,6));
        qt.addPoint(new Point(6,7));
        qt.addPoint(new Point(6,9));
        
        qt.ventilation();
        
        assertEquals(qt.getDepth(),2);
    }
}
