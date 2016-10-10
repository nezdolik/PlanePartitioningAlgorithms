package intervaltree;

/**
 * Created by nezdolik on 11.10.16.
 */
public interface IntervalST<K extends Comparable, V>{

    void add(K lo, K hi);

    int size();
}
