package com.sl.algorithms.core.linear.linkedlist.merge;

import com.sl.algorithms.core.baseObj.ListNode;
import com.sl.algorithms.core.interfaces.rwops.MergeEngine;
import com.sl.algorithms.core.utils.Formulas;

public class LinkedListMergeDnQ<T extends Comparable> implements MergeEngine<T> {

    /**
     * <br><a href="https://leetcode.com/problems/merge-k-sorted-lists/description/">Merge K sorted lists, using divide-n-conquer technique</a><br>
     * <br>Complexity:
     * <br>> Time: O(N * logK): N = total number of nodes & K = total number of lists.
     * <br>> Space: O(N): because we're using the @{@link LinkedListMergeDnQ#merge2SortedLists(ListNode, ListNode)} method.
     */
    @Override
    public ListNode<T> mergeKSortedLists(ListNode<T>[] sortedListsArray) {
        return partitionThenMerge(sortedListsArray, 0, sortedListsArray.length-1);
    }

    private ListNode<T> partitionThenMerge(ListNode<T>[] sortedListArray, int start, int end) {
        if (start > end) return null;
        if (start == end) return sortedListArray[start];
        int midPoint = Formulas.midPoint(start, end);
        ListNode<T> listNode1 = partitionThenMerge(sortedListArray, start, midPoint);
        ListNode<T> listNode2 = partitionThenMerge(sortedListArray, midPoint+1, end);
        return merge2SortedLists(listNode1, listNode2);
    }

    /**
     * O(n+m) time and space recursive method to merge 2 sorted lists.<br>
     */
    @Override
    public ListNode<T> merge2SortedLists(ListNode<T> list1, ListNode<T> list2) {
        if (list1 == null || list1.isDummyNode()) return list2;
        if (list2 == null || list2.isDummyNode()) return list1;
        ListNode<T> mergedListHead;
        if (list1.compareTo(list2) <= 0) {
            mergedListHead = list1;
            mergedListHead.next = merge2SortedLists(list1.next, list2);
        } else {
            mergedListHead = list2;
            mergedListHead.next = merge2SortedLists(list1, list2.next);
        }
        return mergedListHead;
    }
}