package base.twodim;

import base.KDPoint;

/**
 * Created by nezdolik on 24.09.16.
 */
public class Point2D implements KDPoint {

    double x;
    double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString(){
        return "(" + this.x + ":" + this.y + ")";
    }

}
