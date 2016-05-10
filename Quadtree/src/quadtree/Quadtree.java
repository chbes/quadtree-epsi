package quadtree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cbes
 */
public class Quadtree {
    
    private final static int NB_POINT_MAX = 4;

    private Quadtree _nw;
    private Quadtree _ne;
    private Quadtree _se;
    private Quadtree _sw;
    
    /*  x0              x1
      y0 ---------------->
        |
        |
        |
        |
        |
        |
      y1\/
    */ 
    private int _x0;
    private int _x1;
    private int _y0;
    private int _y1;
    
    private List<Point> _points;

    public Quadtree(int x0, int x1, int y0, int y1) {
         
        _nw = null;
        _ne = null;
        _se = null;
        _sw = null;
        
        _x0 = x0;
        _x1 = x1;
        _y0 = y0;
        _y1 = y1;
        
        _points = new ArrayList();
        
        System.out.println("New QuadTree ->"+_x0+"-"+_x1+"|"+_y0+"-"+_y1);
    }
    
    public void addPoints(List<Point> newPoints) {
        _points.addAll(newPoints);
    }
    
    public void removePoints(List<Point> oldPoints) {
        _points.removeAll(oldPoints);
    }
    
    public void addPoint(Point newPoint) {
        _points.add(newPoint);
    }
    
    public void removePoint(Point oldPoint) {
        _points.remove(oldPoint);
    }
    
    public List<Point> getPoints() {
        return _points;
    }
    
    public void ventilation() {
        
        System.out.println("Ventilation");
        
        if(_points.size() > NB_POINT_MAX) {
        
            _nw = new Quadtree(_x0,_x1/2,_y0,_y1/2);
            _ne = new Quadtree(_x1/2,_x1,_y0,_y1/2);
            _se = new Quadtree(_x1/2,_x1,_y1/2,_y1);
            _sw = new Quadtree(_x0,_x1/2,_y1/2,_y1);

            for(Point p : _points) {
                moveInQuadTreeChild(p);            
            }
            this.removePoints(_points);
        }
    }
    
    private int getLocation(Point p) {
        
        //0->NW | 1->NE | 10->SW | 11->SE
        int codeLocalisation = 0;
        
        if(p.getX() > _x1/2) {
            codeLocalisation += 1;
        }
        if(p.getY() > _y1/2) {
            codeLocalisation += 10;
        }
        
        return codeLocalisation;
    }
    
    private void moveInQuadTreeChild(Point p) {
        
        switch( getLocation(p) ) {
            case 0:
                _ne.addPoint(p);
                break;
            case 1:
                _nw.addPoint(p);
                break;
            case 10:
                _sw.addPoint(p);
                break;
            case 11:
                _se.addPoint(p);
                break;
            default:
                break;
        }
    }
    
    public void showState() {
        System.out.println("QuadTree ->"+_x0+"-"+_x1+"|"+_y0+"-"+_y1);
        if(_ne != null && _nw != null && _se != null && _sw != null) {
            
             _ne.showState();
             _nw.showState();
             _se.showState();
             _sw.showState();
             
        } else {
            System.out.println("         ->"+_points.size());
        }
    }
    
    public int getDepth() {
        
        if(_ne != null && _nw != null && _se != null && _sw != null) {
            
            int depth = _ne.getDepth();
            depth = Math.max(depth,_nw.getDepth());
            depth = Math.max(depth,_se.getDepth());
            depth = Math.max(depth,_sw.getDepth());
            
            return depth + 1;
            
        } else {
            
            return 1;
        }
    }
    
    
    
    
}
