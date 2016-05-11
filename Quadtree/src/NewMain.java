
import java.util.Scanner;
import quadtree.Point;
import quadtree.PointFactory;
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
        
        String in;
        
        int quadtreeSize;
        int nbPointsInQuadtree;
        
        Scanner sc = new Scanner(System.in);
               
        
        do {  
            
            clearConsole();
            
            
            System.out.println("Taille du Quadtree ->");
        
            in = sc.nextLine();
            quadtreeSize = Integer.parseInt(in);

            System.out.println("Nombre de points ->");

            in = sc.nextLine();
            nbPointsInQuadtree = Integer.parseInt(in);
            
            System.out.println("FocusPoint (syntaxe X;Y) -> ");
            in = sc.nextLine();
            int focusPointX = Integer.parseInt(in.split(";")[0]);
            int focusPointY = Integer.parseInt(in.split(";")[1]);
            Point focusPoint = PointFactory.newPoint(focusPointX, focusPointY);
            
            clearConsole();
            
            
            Quadtree myQuadtree;
            myQuadtree = QuadtreeFactory.newRandomQuadtree(quadtreeSize, nbPointsInQuadtree);
            myQuadtree.addPoint(focusPoint);
            myQuadtree.ventilation();

            System.out.println("Taille du QuadTree -> "+quadtreeSize);
            System.out.println("Nombres de Points -> "+nbPointsInQuadtree);
            System.out.println("Pronfondeur du QuadTree -> "+myQuadtree.getDepthQuadtree());
            System.out.println("Pronfondeur du FocusPoint("+focusPoint.getX()+";"+focusPoint.getY()+") -> "+myQuadtree.getDepthOfPoint(focusPoint));
            System.out.println("Voisins du FocusPoint("+focusPoint.getX()+";"+focusPoint.getY()+")     -> "+myQuadtree.getNeighbours(focusPoint));  
            
            System.out.println("Relancer un QuadTree -> 'r' | Quitter -> autre");
            
            in = sc.nextLine();

        } while(in.equals("r"));
        
        System.out.println("--------STOP---------");
    }
    
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("--------QUADTREE--------");
        System.out.println("Les points entrées dans le quadtree sont générés aléatoirement\n");
    }
    
}
