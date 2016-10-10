package intervaltree;

import java.util.Collection;

/**
 * Created by nezdolik on 11.10.16.
 */
public interface IntervalST<K extends Comparable, V>{

    void add(K lo, K hi, V val);

    int size();

    Collection<V> intersects(K lo, K hi);
}
