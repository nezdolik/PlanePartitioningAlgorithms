package src.algo;

import algo.PerfectRectangle;
import base.Point;
import base.Rectangle;
import junit.framework.TestCase;

import java.util.Arrays;


/**
 * Created by nezdolik on 25.09.16.
 */
public class PerfectRectangleTest extends TestCase {

    public void testPerfectRectangle(){
        PerfectRectangle pr = new PerfectRectangle();
        Rectangle[] rectArr = new Rectangle[]{
                new Rectangle(new Point(2,6), new Point(5,2), new Point(2,2), new Point(5,6)),
                new Rectangle(new Point(5,6), new Point(7,2), new Point(5,2), new Point(7,6))};

        assertTrue(pr.isPerfectRectangle(Arrays.asList(rectArr)));
    }

    public void testNonPerfectRectangleWithGap(){
        PerfectRectangle pr = new PerfectRectangle();
        Rectangle[] rectArr = new Rectangle[]{
                new Rectangle(new Point(2,4), new Point(5,1), new Point(2,1), new Point(5,4)),
                new Rectangle(new Point(4,5), new Point(6,4), new Point(4,4), new Point(6,5))};

        assertFalse(pr.isPerfectRectangle(Arrays.asList(rectArr)));

    }

    public void testNonPerfectRectangleWithOverlap(){
        PerfectRectangle pr = new PerfectRectangle();
        Rectangle[] rectArr = new Rectangle[]{
                new Rectangle(new Point(2,6), new Point(5,2), new Point(2,2), new Point(5,6)),
                new Rectangle(new Point(5,6), new Point(7,2), new Point(5,2), new Point(7,6)),
                new Rectangle(new Point(2,3), new Point(3,2), new Point(2,2), new Point(3,3)),
        };
        assertFalse(pr.isPerfectRectangle(Arrays.asList(rectArr)));

    }

    public void testNonPerfRectWithGapAndOverlap(){
        PerfectRectangle pr = new PerfectRectangle();
        Rectangle[] rectArr = new Rectangle[]{
                new Rectangle(new Point(2,6), new Point(5,3), new Point(2,3), new Point(5,6)),
                new Rectangle(new Point(5,6), new Point(7,2), new Point(5,2), new Point(7,6)),
                new Rectangle(new Point(3,3), new Point(5,2), new Point(3,2), new Point(5,3)),
                new Rectangle(new Point(6,6), new Point(8,5), new Point(6,5), new Point(8,6)),
        };

        assertFalse(pr.isPerfectRectangle(Arrays.asList(rectArr)));

    }
}
