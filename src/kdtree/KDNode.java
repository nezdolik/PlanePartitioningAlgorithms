package kdtree;

import base.Point;

/**
 * Created by nezdolik on 24.09.16.
 */
public class KDNode {

    private Point p;
    private boolean verticalPartition;

    private KDNode left;
    private KDNode right;

    private KDNode root;

    public KDNode(Point p, boolean isVerticalPartition){
        this.p = p;
        this.verticalPartition = isVerticalPartition;
    }

    public void add(KDNode node){
        if (root == null){
            this.root = node;
        } else {
            add(root, node);
        }
    }

    public void add(KDNode node, KDNode toAdd){
        KDNode curr = node;
        KDNode parent = null;
        Location loc = null;
        while (curr != null){
            parent = curr;
            loc = curr.locate(toAdd);
            if (loc == Location.LESS_OR_EQ){
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }
        }
        if (loc == Location.LESS_OR_EQ){
            parent.setLeft(toAdd);
        } else {
            parent.setRight(toAdd);
        }
    }

    private Location locate(KDNode node) {
        if (verticalPartition && node.p.getY() > this.p.getY()){
            return Location.GREATER;
        } else if (verticalPartition && node.p.getY() <= this.p.getY()){
            return Location.LESS_OR_EQ;
        } else if (node.p.getX() > this.p.getX()){
            return Location.GREATER;
        } else {
            return Location.LESS_OR_EQ;
        }
    }

    public KDNode getLeft() {
        return left;
    }

    public void setLeft(KDNode left) {
        this.left = left;
    }

    public KDNode getRight() {
        return right;
    }

    public void setRight(KDNode right) {
        this.right = right;
    }

    private enum Location{
        LESS_OR_EQ, GREATER
    }


}
