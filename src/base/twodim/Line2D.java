package base.twodim;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nezdolik on 01.10.16.
 */
public class Line2D {

    private List<Point2D> points;

    private double slope;

    public Line2D(Point2D p1, Point2D p2){
        this.points = new ArrayList<Point2D>();
        getPoints().add(p1);
        getPoints().add(p2);
        this.slope = slope();
    }

    public double slope() {
        Point2D p1 = getPoints().get(0);
        Point2D p2 = getPoints().get(1);
        slope = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
        return slope;
    }


    public void extend(Point2D point2D) {
        if (point2D != null){
            getPoints().add(point2D);
        }
    }

    public List<Point2D> getPoints() {
        return points;
    }
}
