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
public class QuadtreeTest {
    
    @Test
    public void souldAddOnePoint() {
        
        Quadtree qt = QuadtreeFactory.newQuadtree(10);
        Point p = PointFactory.newRandomPoint(10);
        
        qt.addPoint(p);
        
        assertEquals(qt.getPoints().get(0),p);
    }
    
    @Test
    public void souldAddManyPoints() {
        
        Quadtree qt = QuadtreeFactory.newQuadtree(10);
        
        List<Point> listPoints = new ArrayList();
        listPoints.add(PointFactory.newRandomPoint(10));
        listPoints.add(PointFactory.newRandomPoint(10));
        listPoints.add(PointFactory.newRandomPoint(10));
        
        qt.addPoints(listPoints);
        
        assertEquals(qt.getPoints(),listPoints);
    }
    
    @Test
    public void shouldRemoveAllPoints() {
        
        List<Point> points = new ArrayList<>();
        points.add(PointFactory.newRandomPoint(10));
        points.add(PointFactory.newRandomPoint(10));
        points.add(PointFactory.newRandomPoint(10));
        
        Quadtree qt = QuadtreeFactory.newQuadtree(20,points);
        
        qt.removePoints();
        
        assertEquals(qt.getPoints().size(),0);
    }
    
    @Test
    public void shouldGetLocationOfPoint() {
        
        Quadtree qt = QuadtreeFactory.newQuadtree(20);
        Point pointNW = PointFactory.newPoint(1,2);
        Point pointNE = PointFactory.newPoint(11,3);
        Point pointSE = PointFactory.newPoint(15,20);
        Point pointSW = PointFactory.newPoint(0,13);
        
        assertEquals(qt.getLocation(pointNW), 0);
        assertEquals(qt.getLocation(pointNE), 1);
        assertEquals(qt.getLocation(pointSE), 11);
        assertEquals(qt.getLocation(pointSW), 10);
        
    }
    
    @Test
    public void shouldDepthAt1() {
        
        List<Point> points = new ArrayList<>();
        points.add(PointFactory.newRandomPoint(10));
        points.add(PointFactory.newRandomPoint(10));
        points.add(PointFactory.newRandomPoint(10));
        
        Quadtree qt = QuadtreeFactory.newQuadtree(20,points);
        
        qt.ventilation();
        
        assertEquals(qt.getDepthQuadtree(),1);
    }
    
    
    @Test
    public void shouldDepthAt3() {

        List<Point> points = new ArrayList<>();
        points.add(PointFactory.newPoint(6,2));
        points.add(PointFactory.newPoint(1,2));
        points.add(PointFactory.newPoint(6,1));
        points.add(PointFactory.newPoint(6,7));
        points.add(PointFactory.newPoint(6,9));
        points.add(PointFactory.newPoint(6,10));
        points.add(PointFactory.newPoint(7,9));
        points.add(PointFactory.newPoint(15,6));
        
        Quadtree qt = QuadtreeFactory.newQuadtree(20,points);
        
        qt.ventilation();
        
        assertEquals(qt.getDepthQuadtree(),3);
    }
    
    //TODO refacto avec appel PointFactory
    @Test
    public void shouldDepthAtPoint() {

        List<Point> points = new ArrayList<>();
        points.add(new Point(6,2));
        points.add(new Point(1,2));
        points.add(new Point(1,6));
        points.add(new Point(6,7));
        points.add(new Point(6,9));
        points.add(new Point(6,10));
        points.add(new Point(7,9));
        points.add(new Point(15,6));
        
        Quadtree qt = QuadtreeFactory.newQuadtree(20,points);
        
        qt.ventilation();
        
        assertEquals(qt.getDepthOfPoint(new Point(6,7)),3);
    }
    
    @Test
    public void shouldGetAlonePoint() {

        List<Point> points = new ArrayList<>();
        points.add(new Point(6,2));
        points.add(new Point(1,2));
        points.add(new Point(1,6));
        points.add(new Point(15,6));
        points.add(new Point(6,7));
        points.add(new Point(6,9));
        points.add(new Point(6,10));
        points.add(new Point(7,9));
        
        Point alonePoint = new Point(3,17);
        
        points.add(alonePoint);
        
        Quadtree qt = QuadtreeFactory.newQuadtree(20,points);
        
        qt.ventilation();
        
        assertEquals(qt.getNeighbours(alonePoint).size(),1);
        assertEquals(qt.getNeighbours(alonePoint).get(0),alonePoint);
    }
    
    @Test
    public void shouldGetManyNeighbours() {

        List<Point> points = new ArrayList<>();
        points.add(new Point(6,2));
        points.add(new Point(1,2));
        points.add(new Point(1,6));
        points.add(new Point(15,6));
        
        List<Point> neighbours = new ArrayList<>();
        neighbours.add(new Point(6,7));
        neighbours.add(new Point(6,9));
        neighbours.add(new Point(6,10));
        neighbours.add(new Point(7,9));  
        
        points.addAll(neighbours);
        
        Quadtree qt = QuadtreeFactory.newQuadtree(20,points);
        
        qt.ventilation();
        
        assertEquals(qt.getNeighbours(new Point(6,9)),neighbours);
    }
}
