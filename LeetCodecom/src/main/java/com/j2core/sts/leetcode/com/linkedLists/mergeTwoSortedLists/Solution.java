package com.j2core.sts.leetcode.com.linkedLists.mergeTwoSortedLists;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

/*
   The class merge two sorted linked lists and return it as a new list.
 */
public class Solution {

    /**
     * The method merge two sorted linked lists.
     *
     * @param l1     first sorted list
     * @param l2     second sorted list
     * @return       result sorted list
     */
    public ListNode mergeTwoListsOld(ListNode l1, ListNode l2) {

        int[] arrayResult = null;
        int[] array1 = createNumArray(l1);
        int[] array2 = createNumArray(l2);
        if (array1 == null && array2 == null)return null;
        int index1 = 0;
        int index2 = 0;
        int index = 0;
        if (array1 != null && array2 != null) {

            arrayResult = new int[array1.length + array2.length];
            while (index1 < array1.length && index2 < array2.length) {
                int num1 = array1[index1];
                int num2 = array2[index2];
                int num;
                if (num1 < num2) {
                    num = num1;
                    index1++;
                } else {
                    num = num2;
                    index2++;
                }
                arrayResult[index] = num;
                index++;
            }
        }
        int[] tmpArray = null;
        int tmpIndex = -1;
        if (array1 != null && index1 < array1.length) {
            tmpArray = array1;
            tmpIndex = index1;
        } else if (array2 != null && index2 < array2.length) {
            tmpArray = array2;
            tmpIndex = index2;
        }
        if (arrayResult == null){
            arrayResult = new int[tmpArray.length];
        }
        if (tmpArray != null && tmpIndex < tmpArray.length) {
            while (tmpIndex < tmpArray.length) {
                arrayResult[index] = tmpArray[tmpIndex];
                tmpIndex++;
                index++;
            }
        }
        ListNode result = new ListNode(arrayResult[0]);
        ListNode node = result;
        for (int i = 1; i < arrayResult.length; i++) {
            node.next = new ListNode(arrayResult[i]);
            node = node.next;
        }
        return result;
    }

    /**
     * The method create array from number's List
     *
     * @param listNode      collection with numbers
     * @return              array with numbers
     */
    private int[] createNumArray(ListNode listNode){

        if (listNode != null) {
            List<Integer> numbers = new LinkedList<Integer>();
            ListNode node = listNode;
            while (node.next != null) {
                numbers.add(node.val);
                node = node.next;
            }
            numbers.add(node.val);

            int[] result = new int[numbers.size()];

            for (int i = 0; i < numbers.size(); i++ ){

                result[i] = numbers.get(i);
            }

            return result;
        }else return null;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode root = null;
        ListNode tmpRoot = null;

        while (l1 != null && l2 != null){
            ListNode tmp;
            if (l1.val <= l2.val){
                tmp = l1;
                l1 = l1.next;
                tmp.next = null;
            }else {
                tmp = l2;
                l2 = l2.next;
                tmp.next = null;
            }
            if (root == null){
                root = tmp;
                tmpRoot = root;
            }else {
                tmpRoot.next = tmp;
                tmpRoot = tmpRoot.next;
            }
        }

        ListNode node = l1 != null ? l1 : l2;

        tmpRoot.next = node;
        return root;
    }

    @Test
    public void test(){

        ListNode root1 = null;
        ListNode root2 = new ListNode(0);
        Assert.assertNotNull(mergeTwoLists(root1, root2));

    }
}
