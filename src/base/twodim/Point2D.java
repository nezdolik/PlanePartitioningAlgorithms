package base.twodim;

import base.KDPoint;

/**
 * Created by nezdolik on 24.09.16.
 */
public class Point2D implements KDPoint {

    private int x;
    private int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString(){
        return "(" + this.x + ":" + this.y + ")";
    }

}
