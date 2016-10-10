package intervaltree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nezdolik on 11.10.16.
 *
 * Represents interval search tree
 *
 * #TODO add support for duplicate left keys
 */
public class IntervalNode<K extends Comparable, V> implements IntervalST<K, V>{

    private static final StringBuilder buf = new StringBuilder();

    private K lo;
    private K hi;
    private V val;

    private IntervalNode<K,V> left;
    private IntervalNode<K,V> right;

    private K maxEndPoint;

    private IntervalNode<K,V> root;

    private int size;

    public IntervalNode(K lo, K hi, V val){
        this.lo = lo;
        this.hi = hi;
        this.val = val;
        this.root = this;
        this.maxEndPoint = hi;
        this.size = 1;
    }

    public IntervalNode(K lo, K hi, IntervalNode<K,V> root){
        this.lo = lo;
        this.hi = hi;
        this.root = root;
        this.maxEndPoint = (hi.compareTo(root.hi) > 0) ? hi : root.hi;
        this.size = 1;
    }


    //todo fix extensive object allocation
    public StringBuilder flush(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if(right!=null) {
            right.flush(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").
        append("(").append(lo).append(":").append(hi)
                .append(":").append(val).append(":").append(maxEndPoint).append(")").append("\n");
        if(left!=null) {
            left.flush(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        buf.delete(0, buf.length());
        flush(new StringBuilder(), true, buf);
        return buf.toString();
    }


    @Override
    public void add(K lo, K hi, V val) {
        IntervalNode<K,V> newInterval = add(root, lo, hi, val);
        newInterval.root = root;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Collection<V> intersects(K lo, K hi) {
        return intersects(root, lo, hi, new LinkedList<V>());
    }

    private Collection<V> intersects(IntervalNode<K,V> node, K lo, K hi, List<V> res) {
        if (node == null){
            return res;
        }
        if (node.lo.compareTo(lo) <= 0 && node.hi.compareTo(lo) >= 0){
            res.add(node.val);
        }
        if (left == null){
            intersects(node.right, lo, hi, res);
        } else if (left.maxEndPoint.compareTo(lo) >= 0){
            intersects(node.left, lo, hi, res);
        } else {
            intersects(node.right, lo, hi, res);
        }

        return res;
    }

    private IntervalNode<K,V> add(IntervalNode<K,V> node, K lo, K hi, V val){

        if (node == null){
            return new IntervalNode<K,V>(lo, hi, val);
        }

        node.maxEndPoint = (node.maxEndPoint.compareTo(hi) < 0)
                ? hi : node.maxEndPoint;
        ++node.size;

        if (node.lo.compareTo(lo) > 0){
            node.left = add(node.left, lo, hi, val);
        } else {
            node.right = add(node.right, lo, hi, val);
        }

        return node;

    }
}
