package intervaltree;

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

    public IntervalNode(K lo, K hi){
        this.lo = lo;
        this.hi = hi;
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
        append("(").append(lo).append(":").append(hi).append(":").append(maxEndPoint).append(")").append("\n");
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
    public void add(K lo, K hi) {
        IntervalNode<K,V> newInterval = add(root, lo, hi);
        newInterval.root = root;
    }

    @Override
    public int size() {
        return size;
    }

    private IntervalNode<K,V> add(IntervalNode<K,V> node, K lo, K hi){

        if (node == null){
            return new IntervalNode<K,V>(lo, hi);
        }

        node.maxEndPoint = (node.maxEndPoint.compareTo(hi) < 0)
                ? hi : node.maxEndPoint;
        ++node.size;

        if (node.lo.compareTo(lo) > 0){
            node.left = add(node.left, lo, hi);
        } else {
            node.right = add(node.right, lo, hi);
        }

        return node;

    }
}
