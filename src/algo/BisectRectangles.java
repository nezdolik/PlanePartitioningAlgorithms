package algo;

import base.twodim.Line2D;
import base.twodim.Point2D;
import base.twodim.Rectangle;


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

        int bisectLeftMostX = 0;
        int bisectLeftMostY = 0;
        int bisectRightMostX = 0;
        int bisectRightMostY = 0;

        int b = midP1.getY() - (int)bisectLine.slope()*midP1.getX();

        //if slope is steep (delta(y)>delta(x)) extend line on x axis
        // else if slope is shallow(delta(y)<delta(x)) extend line on y axis
        //also check direction of slope
        if (Math.signum(bisectLine.slope()) == 1){//increasing line
            if(bisectLine.slope() > 1){//crosses x axis
                bisectLeftMostY = (r1.getLeftBottom().getY() < r2.getLeftBottom().getY()) ?
                        r1.getLeftBottom().getY() : r2.getLeftBottom().getY();
                //y = slope*x + b, x = (y - b) / slope
                bisectLeftMostX = (int)((bisectLeftMostY - b) / bisectLine.slope());
            } else{//crosses y axis
                bisectLeftMostX = (r1.getLeftBottom().getX() < r2.getLeftBottom().getX()) ?
                        r1.getLeftBottom().getX() : r2.getLeftBottom().getX();
                bisectLeftMostY = (int)(bisectLine.slope() * bisectLeftMostX) + b;
            }

        } else {
            if(bisectLine.slope() > 1) {//crosses x axis
                bisectLeftMostY = (r1.getLeftUp().getY() > r2.getLeftUp().getY()) ?
                        r1.getLeftUp().getY() : r2.getLeftUp().getY();
                //y = slope*x + b, x = (y - b) / slope
                bisectLeftMostX = (int)((bisectLeftMostY - b) / bisectLine.slope());

            } else {//crosses y axis
                bisectLeftMostX = (r1.getLeftUp().getX() < r2.getLeftUp().getX()) ?
                        r1.getLeftUp().getX() : r2.getLeftUp().getX();
                bisectLeftMostY = (int)(bisectLine.slope() * bisectLeftMostX) + b;
            }

        }

        bisectLine.extend(new Point2D(bisectLeftMostX, bisectLeftMostY));
        bisectLine.extend(new Point2D(bisectRightMostX, bisectRightMostY));

        return bisectLine;
    }
}
