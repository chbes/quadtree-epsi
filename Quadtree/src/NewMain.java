
import quadtree.Point;
import quadtree.Quadtree;
import quadtree.QuadtreeFactory;

/**
 *
 * @author cbes
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("---START---");
        Quadtree qt = QuadtreeFactory.newQuadtree(10);
        qt.addPoint(new Point(6,2));
        qt.addPoint(new Point(1,2));
        qt.addPoint(new Point(1,6));
        qt.addPoint(new Point(6,7));
        qt.ventilation();
        qt.showState();
        System.out.println("---STOP----");
    }
    
}
