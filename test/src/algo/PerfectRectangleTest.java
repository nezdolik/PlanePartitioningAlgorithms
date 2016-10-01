package src.algo;

import algo.PerfectRectangle;
import base.twodim.Point2D;
import base.twodim.Rectangle;
import junit.framework.TestCase;

import java.util.Arrays;


/**
 * Created by nezdolik on 25.09.16.
 */
public class PerfectRectangleTest extends TestCase {

    public void testPerfectRectangle(){
        PerfectRectangle pr = new PerfectRectangle();
        Rectangle[] rectArr = new Rectangle[]{
                new Rectangle(new Point2D(2,6), new Point2D(5,2), new Point2D(2,2), new Point2D(5,6)),
                new Rectangle(new Point2D(5,6), new Point2D(7,2), new Point2D(5,2), new Point2D(7,6))};

        assertTrue(pr.isPerfectRectangle(Arrays.asList(rectArr)));
    }

    public void testNonPerfectRectangleWithGap(){
        PerfectRectangle pr = new PerfectRectangle();
        Rectangle[] rectArr = new Rectangle[]{
                new Rectangle(new Point2D(2,4), new Point2D(5,1), new Point2D(2,1), new Point2D(5,4)),
                new Rectangle(new Point2D(4,5), new Point2D(6,4), new Point2D(4,4), new Point2D(6,5))};

        assertFalse(pr.isPerfectRectangle(Arrays.asList(rectArr)));

    }

    public void testNonPerfectRectangleWithOverlap(){
        PerfectRectangle pr = new PerfectRectangle();
        Rectangle[] rectArr = new Rectangle[]{
                new Rectangle(new Point2D(2,6), new Point2D(5,2), new Point2D(2,2), new Point2D(5,6)),
                new Rectangle(new Point2D(5,6), new Point2D(7,2), new Point2D(5,2), new Point2D(7,6)),
                new Rectangle(new Point2D(2,3), new Point2D(3,2), new Point2D(2,2), new Point2D(3,3)),
        };
        assertFalse(pr.isPerfectRectangle(Arrays.asList(rectArr)));

    }

    public void testNonPerfRectWithGapAndOverlap(){
        PerfectRectangle pr = new PerfectRectangle();
        Rectangle[] rectArr = new Rectangle[]{
                new Rectangle(new Point2D(2,6), new Point2D(5,3), new Point2D(2,3), new Point2D(5,6)),
                new Rectangle(new Point2D(5,6), new Point2D(7,2), new Point2D(5,2), new Point2D(7,6)),
                new Rectangle(new Point2D(3,3), new Point2D(5,2), new Point2D(3,2), new Point2D(5,3)),
                new Rectangle(new Point2D(6,6), new Point2D(8,5), new Point2D(6,5), new Point2D(8,6)),
        };

        assertFalse(pr.isPerfectRectangle(Arrays.asList(rectArr)));

    }
}
