package src.intervaltree;

import intervaltree.IntervalNode;
import intervaltree.IntervalST;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Collection;

/**
 * Created by nezdolik on 11.10.16.
 */
public class IntervalNodeTest extends TestCase {

    public void testTreeConstruction(){
        IntervalST<Integer, Character> tree = new IntervalNode<Integer, Character>(17,19, 'a');
        tree.add(5,8,'b');
        tree.add(21,24,'c');
        tree.add(15,8,'d');
        Assert.assertEquals(tree.size(), 4);
        System.out.println(tree);

        Collection<Character> intersections = tree.intersects(7, 12);
        Assert.assertEquals(intersections.size(), 1);
        Assert.assertTrue(intersections.contains('b'));

        intersections = tree.intersects(24,30);
        Assert.assertEquals(intersections.size(), 1);
        Assert.assertTrue(intersections.contains('c'));

    }
}
