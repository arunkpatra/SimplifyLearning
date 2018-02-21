package com.sl.algorithms.core.tree.lca;

import com.sl.algorithms.core.interfaces.search.LowestCommonAncestor;
import com.sl.algorithms.core.tree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

public class LCAFinderTest {
    private LowestCommonAncestor<Integer> lowestCommonAncestorI;
    private LowestCommonAncestor<String> lowestCommonAncestorS;

    @Test
    public void assertLCAIterative() {
        lowestCommonAncestorI = new LCAFinderIterative<>();
        lowestCommonAncestorS = new LCAFinderIterative<>();
        testNull();
        testSimpleTreeLCA();
        testParentChild();
    }

    @Test
    public void assertLCARecursive() {
        lowestCommonAncestorI = new LCAFinderRecursive<>();
        lowestCommonAncestorS = new LCAFinderRecursive<>();
        testNull();
        testSimpleTreeLCA();
        testParentChild();
    }

    private void testNull() {
        Assert.assertNull(lowestCommonAncestorI.findLCA(null, null, null));
    }

    private void testSimpleTreeLCA() {
        TreeNode<Integer> bTree = new TreeNode<>(1);
        bTree.left = new TreeNode<>(2);
        bTree.right = new TreeNode<>(3);
        Assert.assertEquals(bTree, lowestCommonAncestorI.findLCA(bTree, bTree.left, bTree.right));
    }

    private void testParentChild() {
        TreeNode<String> bTree = new TreeNode<>("Sarika");
        bTree.left = new TreeNode<>("Roohani");
        bTree.right = new TreeNode<>("Nikunj");
        Assert.assertEquals(bTree, lowestCommonAncestorS.findLCA(bTree, bTree.left, bTree.right));
        Assert.assertEquals(bTree, lowestCommonAncestorS.findLCA(bTree, bTree, bTree.right));
        Assert.assertEquals(bTree, lowestCommonAncestorS.findLCA(bTree, bTree.left, bTree));
    }
}
