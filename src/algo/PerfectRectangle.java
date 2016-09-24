package algo;

import base.Point;
import base.Rectangle;
import kdtree.KDNode;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by nezdolik on 24.09.16.
 *
 * Given N rectangles in the plane, check whether all the rectangles form a big rectangle
 * (without overlaps and gaps).
 *
 * Proposed solution: find median of all points and construct KD tree with plane partitioning.
 * Median is used for balanced tree construction, so all further operations in tree will take ~O(lgN).
 *
 * 2 conditions must hold for N rectangles to form a perfect rectangle:
 *
 * 1) area size of N rectangles must be equal to area size of perfect rectangle
 *    (so if there are any gaps, this will not hold). X,Y coordinates of perfect
 *    rectangle are [max,min] X,Y coordinates across all rectangles;
 * 2) there should be no overlapping rectangles (overlapping is checked via KD tree)
 */
public class PerfectRectangle {

    public boolean isPerfectRectangle(Collection<Rectangle> rectangles){
        if (rectangles == null || rectangles.isEmpty()){
            return false;
        }

        Point median = getMedian(rectangles); //O(N)

        KDNode tree = new KDNode(median, true);// start partitioning vertically from median

        //compute summary area size on fly, detect overlaps and gaps
        boolean canConstruct = constructPerf(tree, rectangles);

        return canConstruct;
    }

    private boolean constructPerf(KDNode tree, Collection<Rectangle> rectangles) {
        int summaryArea = 0;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Rectangle r : rectangles){
            summaryArea += r.area();
            tree.add(r.getLeftUp());
            tree.add(r.getLeftBottom());
            tree.add(r.getRightUp());
            tree.add(r.getRightBottom());

            maxX = Math.max(maxX, r.getRightBottom().getX());
            maxY = Math.max(maxY, r.getLeftUp().getY());
            minX = Math.min(minX, r.getLeftBottom().getX());
            minY = Math.min(minY, r.getRightBottom().getY());
        }
        boolean isOverlap = tree.containsOverlap();

        if (isOverlap){
            return false;
        } else {
            int perfectArea = (maxX - minX) * (maxY - minY);
            return summaryArea == perfectArea;
        }
    }

    private Point getMedian(Collection<Rectangle> rectangles) {
        int xSum = 0;
        int ySum = 0;
        int nPoints = rectangles.size() * 4;
        for (Rectangle r : rectangles){
            xSum += r.getLeftUp().getX();
            xSum += r.getLeftBottom().getX();
            xSum += r.getRightUp().getX();
            xSum += r.getRightBottom().getX();

            ySum += r.getLeftUp().getY();
            ySum += r.getLeftBottom().getY();
            ySum += r.getRightUp().getY();
            ySum += r.getRightBottom().getY();
        }

        return new Point(xSum / nPoints, ySum / nPoints);
    }

    //basic test
    public static void main(String[] args) {
        PerfectRectangle pr = new PerfectRectangle();
        Rectangle[] rectArr = new Rectangle[]{
                new Rectangle(new Point(2,6), new Point(5,2), new Point(2,2), new Point(5,6)),
                new Rectangle(new Point(5,6), new Point(7,2), new Point(5,2), new Point(7,6))};
        System.out.println(pr.isPerfectRectangle(Arrays.asList(rectArr)));
    }

}
