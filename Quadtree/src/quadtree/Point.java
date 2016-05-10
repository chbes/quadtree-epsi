/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

/**
 *
 * @author cbes
 */
public class Point {
    
    private int _x;
    private int _y;

    public Point() {
    }

    public Point(int _x, int _y) {
        this._x = _x;
        this._y = _y;
    }

    public int getX() {
        return _x;
    }

    public void setX(int _x) {
        this._x = _x;
    }

    public int getY() {
        return _y;
    }

    public void setY(int _y) {
        this._y = _y;
    } 
    
}
