package src.algo;

import algo.BisectRectangles;
import base.twodim.Line2D;
import base.twodim.Point2D;
import base.twodim.Rectangle;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by nezdolik on 01.10.16.
 */
public class BisectRectanglesTest extends TestCase {

    public void testBisectIncreasingLineShallowSlope(){

        Rectangle r1 = new Rectangle(new Point2D(3,5), new Point2D(6,2), new Point2D(3,2), new Point2D(6,5));
        Rectangle r2 = new Rectangle(new Point2D(3,4), new Point2D(5,2), new Point2D(3,2), new Point2D(5,4));

        Line2D line = BisectRectangles.bisect(r1, r2);

        Point2D leftCrossPoint = line.getPoints().get(2);

        Assert.assertTrue(leftCrossPoint.getX() == 3 && leftCrossPoint.getY() == 2);

    }

    public void testBisectIncreasingLineSteepSlope(){

        Rectangle r1 = new Rectangle(new Point2D(2,9), new Point2D(6,6), new Point2D(2,6), new Point2D(6,9));
        Rectangle r2 = new Rectangle(new Point2D(4.5, 9.5), new Point2D(5.5, 8.5), new Point2D(4.5, 8.5), new Point2D(5.5, 10.5));

        Line2D line = BisectRectangles.bisect(r1, r2);

        Point2D leftCrossPoint = line.getPoints().get(2);

        Assert.assertTrue(leftCrossPoint.getX() == 3 && leftCrossPoint.getY() == 6);
    }
}
