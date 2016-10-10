package src.intervaltree;

import intervaltree.IntervalNode;
import intervaltree.IntervalST;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by nezdolik on 11.10.16.
 */
public class IntervalNodeTest extends TestCase {

    public void testTreeConstruction(){
        IntervalST<Integer, Integer> tree = new IntervalNode<Integer, Integer>(17,19);
        tree.add(5,8);
        tree.add(21,24);
        tree.add(15,8);
        Assert.assertEquals(tree.size(), 4);
        System.out.println(tree);
    }
}
