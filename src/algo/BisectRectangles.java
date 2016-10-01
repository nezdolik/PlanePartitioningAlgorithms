package algo;

import base.twodim.Line2D;
import base.twodim.Point2D;
import base.twodim.Rectangle;

import static base.twodim.Line2D.Direction;

//TODO add processing of top cross point

/**
 * Created by nezdolik on 01.10.16.
 *
 * Given 2 rectangles in 2D, find the line that would cut those
 * rectangles in half. Top and bottom sides of squares are parallel to x axis
 */
public class BisectRectangles {

    public static Line2D bisect(Rectangle r1, Rectangle r2){

        if (r1 == null || r2 == null){
            throw new IllegalArgumentException("Not valid rectangles passed");
        }

        Point2D midP1 = r1.middlePoint();
        Point2D midP2 = r2.middlePoint();

        Line2D bisectLine = new Line2D(midP1, midP2);

        Point2D crossLeftPoint = getCrossEdgePoint(r1, r2, bisectLine, false);
        //Point2D crossRightPoint = getCrossEdgePoint(r1, r2, bisectLine, true);

        bisectLine.extend(crossLeftPoint);
        //bisectLine.extend(crossRightPoint);

        return bisectLine;
    }

    /*
    *   Extend line to cross either left or right side edges of rectangle
    *
    *   @param r1, r2      rectangles that are to be bisected by line
    *   @param bisectLine  cuts both rectangles in half
    *   @param edgeSide    false - left side of vertical plane partitioning, true - right side.
    *
    *   @return point where line crosses x or y axis of rectangle either on left or right side
    * */
    private static Point2D getCrossEdgePoint(Rectangle r1, Rectangle r2, Line2D bisectLine, boolean edgeSide) {

        Point2D midP = r1.middlePoint();
        double crossY;
        double crossX;

        double b = midP.getY() - bisectLine.slope() * midP.getX();
        Point2D r1UpPoint = (edgeSide) ? r1.getRightUp() : r1.getLeftUp();
        Point2D r1BottomPoint = (edgeSide) ? r1.getRightBottom() : r1.getLeftBottom();
        Point2D r2UpPoint = (edgeSide) ? r2.getRightUp() : r2.getLeftUp();
        Point2D r2BottomPoint = (edgeSide) ? r2.getRightBottom() : r2.getLeftBottom();

        //if slope is steep (delta(y)>delta(x)) extend line on x axis
        // else if slope is shallow(delta(y)<delta(x)) extend line on y axis
        //also check direction of slope
        if(bisectLine.slope() > 1) {//crosses x axis
            crossY = (bisectLine.direction() == Direction.INCREASING) ?
                    Math.min(r1BottomPoint.getY(), r2BottomPoint.getY()) :
                    Math.max(r1UpPoint.getY(), r2UpPoint.getY());
            crossX = (int)((crossY - b) / bisectLine.slope());

        } else {//crosses y axis
            crossX = (bisectLine.direction() == Direction.INCREASING) ?
                    Math.min(r1BottomPoint.getX(), r2BottomPoint.getX()) :
                    Math.min(r1UpPoint.getX(), r2UpPoint.getX());
            crossY = (int)(bisectLine.slope() * crossX) + b;
        }

        return new Point2D(crossX, crossY);
    }
}
