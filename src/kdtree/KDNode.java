package kdtree;

import base.twodim.Point2D;

/**
 * Created by nezdolik on 24.09.16.
 */
public class KDNode {

    private Point2D p;
    private boolean verticalPartition;

    private KDNode left;
    private KDNode right;

    private KDNode root;

    public KDNode(Point2D p, boolean isVerticalPartition){
        this.p = p;
        this.verticalPartition = isVerticalPartition;
        this.root = this;
    }

    public KDNode(Point2D p, boolean isVerticalPartition, KDNode root){
        this.p = p;
        this.verticalPartition = isVerticalPartition;
        this.root = root;
    }

    public void add(Point2D point2D){
        if (root == null){
            this.root = new KDNode(p, true);
        } else {
            add(root, point2D);
        }
    }

    public void add(KDNode node, Point2D toAdd){
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
        KDNode newNode = new KDNode(toAdd, !parent.verticalPartition, root);
        if (loc == Location.LESS_OR_EQ){
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
    }

    private Location locate(Point2D p) {
        if (verticalPartition && p.getX() > this.p.getX()){
            return Location.GREATER;
        } else if (verticalPartition && p.getX() <= this.p.getX()){
            return Location.LESS_OR_EQ;
        } else if (p.getY() > this.p.getY()){
            return Location.GREATER;
        } else {
            return Location.LESS_OR_EQ;
        }
    }

    public double getX(){
        return p.getX();
    }

    public double getY(){
        return p.getY();
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

    public boolean containsOverlap() {
        return containsOverlap(root.getLeft()) || containsOverlap(root.getRight());
    }

    private boolean containsOverlap(KDNode node) {
        if (node == null || node.getLeft() == null || node.getRight() == null){
            return false;
        }
        boolean isInXInterval = node.getX() > node.getLeft().getX() && node.getX() < node.getRight().getX();
        boolean isInYInterval = node.getY() > node.getLeft().getY() && node.getY() < node.getRight().getY();

        if (isInXInterval && isInYInterval){
            return true;
        } else {
            return containsOverlap(node.getLeft()) || containsOverlap(node.getRight());
        }
    }

    private enum Location{
        LESS_OR_EQ, GREATER
    }


}
