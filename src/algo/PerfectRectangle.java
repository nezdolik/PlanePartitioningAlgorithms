package algo;

import base.Point;
import base.Rectangle;
import kdtree.KDNode;

import java.util.Collection;

/**
 * Created by nezdolik on 24.09.16.
 *
 * Given N rectangles in the plane, check whether all the rectangles form a big rectangle
 * (without overlaps and gaps).
 *
 * Proposed solution: find median of all points and construct KDTree with plane partitioning.
 * Median is used for balanced tree construction, so all further operations in tree will take ~O(lgN).
 *
 * 2 conditions must hold for N rectangles to form a perfect rectangle:
 *
 * 1) area size of N rectangles must be equal to area size of perfect rectangle
 *    (so if there are any gaps, this will not hold);
 * 2) there should be no overlapping rectangles (overlapping is checked via KD tree)
 */
public class PerfectRectangle {

    public static boolean isPerfectRectangle(Collection<Rectangle> rectangles){
        if (rectangles == null || rectangles.isEmpty()){
            return false;
        }

        Point median = getMedian(rectangles); //O(N)

        KDNode tree = new KDNode(median, true);// start partitioning vertically from median



        return false;

    }

    private static Point getMedian(Collection<Rectangle> rectangles) {
        int xSum = 0;
        int ySum = 0;
        int nPoints = rectangles.size() * 4;
        for (Rectangle r : rectangles){
            xSum += r.getLeftUp().getX();
            xSum += r.getLeftBottom().getX();
            xSum += r.getRightUp().getX();
            xSum += r.getRightBottom().getY();

            ySum += r.getLeftUp().getY();
            ySum += r.getLeftBottom().getY();
            ySum += r.getRightUp().getY();
            ySum += r.getRightBottom().getY();
        }

        return new Point(xSum / nPoints, ySum / nPoints);
    }
}
