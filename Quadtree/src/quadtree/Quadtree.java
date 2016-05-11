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
        
        //System.out.println("New QuadTree ->"+_x0+"-"+_x1+"|"+_y0+"-"+_y1);
    }
    
    public void addPoints(List<Point> newPoints) {
        _points.addAll(newPoints);
    }
    
    public void addPoint(Point newPoint) {
        _points.add(newPoint);
    }
    
     public void removePoints() {
        _points.clear();
    }  
     
    public List<Point> getPoints() {
        return _points;
    }
    
    public void ventilation() {
        
        //System.out.print("Ventilation ->"+_x0+"-"+_x1+"|"+_y0+"-"+_y1);
        //System.out.println("  -> "+_points.size()+" | "+_points);
        
        if(_points.size() > NB_POINT_MAX) {
        
            _nw = new Quadtree(_x0,(_x0+_x1)/2,_y0,(_y0+_y1)/2);
            _ne = new Quadtree((_x0+_x1)/2,_x1,_y0,(_y0+_y1)/2);
            _se = new Quadtree((_x0+_x1)/2,_x1,(_y0+_y1)/2,_y1);
            _sw = new Quadtree(_x0,(_x0+_x1)/2,(_y0+_y1)/2,_y1);

            for(Point p : _points) {
                moveInQuadTreeChild(p);            
            }
            
            this.removePoints();
            
            _nw.ventilation();
            _ne.ventilation();
            _se.ventilation();
            _sw.ventilation();
            
        }
    }
    
    public int getLocation(Point p) {
        
        //0->NW | 1->NE | 10->SW | 11->SE
        int codeLocalisation = 0;
        
        if(p.getX() > (_x0+_x1)/2) {
            codeLocalisation += 1;
        }
        if(p.getY() > (_y0+_y1)/2) {
            codeLocalisation += 10;
        }
        
        return codeLocalisation;
    }
    
    public void moveInQuadTreeChild(Point p) {
        
        switch( getLocation(p) ) {
            case 0:
                _nw.addPoint(p);
                break;
            case 1:
                _ne.addPoint(p);
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
    
    public int getDepthQuadtree() {
        
        if(_ne != null && _nw != null && _se != null && _sw != null) {
            
            int depth = _ne.getDepthQuadtree();
            depth = Math.max(depth,_nw.getDepthQuadtree());
            depth = Math.max(depth,_se.getDepthQuadtree());
            depth = Math.max(depth,_sw.getDepthQuadtree());
            
            return depth + 1;
            
        } else {
            
            return 1;
        }
    }
    
    public int getDepthOfPoint(Point pointWanted) {
        
        //Point pointWanted = new Point(x,y);
        
        if(_ne != null && _nw != null && _se != null && _sw != null) {
            
            int depth = -1;
            
            switch( getLocation(pointWanted) ) {
                case 0:
                    depth = _nw.getDepthOfPoint(pointWanted);
                    break;
                case 1:
                    depth = _ne.getDepthOfPoint(pointWanted);
                    break;
                case 10:
                    depth = _sw.getDepthOfPoint(pointWanted);
                    break;
                case 11:
                    depth = _se.getDepthOfPoint(pointWanted);
                    break;
                default:
                    break;
            }
            
            return depth + 1;
            
        } else {
            
            return 1;
        }
    }
    
    public List<Point> getNeighbours(Point pointWanted) {
        
        //Point pointWanted = new Point(x,y);
        
        if(_ne != null && _nw != null && _se != null && _sw != null) {

            
            switch( getLocation(pointWanted) ) {
                case 0:
                    return _nw.getNeighbours(pointWanted);
                case 1:
                    return _ne.getNeighbours(pointWanted);
                case 10:
                    return _sw.getNeighbours(pointWanted);
                case 11:
                    return _se.getNeighbours(pointWanted);
                default:
                    return null;
            }

        } else {
            
            return _points;
        }
    }
    
    public int getSize() {
        return _x1;
    }
    
    
    
    
}
