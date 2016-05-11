package quadtree;

import java.util.List;

/**
 *
 * @author cbes
 */
public class QuadtreeFactory {
    
    public static Quadtree newQuadtree(int size) {
        Quadtree newQuadtree = new Quadtree(0, size, 0, size);
        return newQuadtree;
    }
    
    public static Quadtree newQuadtree(int size, List<Point> points) {
        Quadtree newQuadtree = new Quadtree(0, size, 0, size);
        newQuadtree.addPoints(points);
        return newQuadtree;
    }
    
    public static Quadtree newRandomQuadtree(int size, int nbPoints) {
        Quadtree newQuadtree = new Quadtree(0, size, 0, size);
        newQuadtree.addPoints(PointFactory.newRandomPoints(size, nbPoints));
        return newQuadtree;
    }
    
}
