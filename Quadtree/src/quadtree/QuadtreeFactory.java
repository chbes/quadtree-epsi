package quadtree;

/**
 *
 * @author cbes
 */
public class QuadtreeFactory {
    
    public static Quadtree newQuadtree(int size) {
        Quadtree newQuadtree = new Quadtree(0, size, 0, size);
        return newQuadtree;
    }
    
}
