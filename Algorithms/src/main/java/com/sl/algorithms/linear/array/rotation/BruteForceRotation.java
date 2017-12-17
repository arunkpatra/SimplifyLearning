package com.sl.algorithms.linear.array.rotation;

/**
 * <br>Problem: Rotate linear of size 'n' by 'k' positions, leftwards (counter-clockwise).<br>
 * <br><a href="https://eli.thegreenplace.net/2008/08/29/space-efficient-list-rotation">Reference</a>
 */
public class BruteForceRotation {

    BruteForceRotation() {
        /**
         * This is a utility class.<br>
         */
    }
    
    // O(kn) time and O(1) space
    public static int[] rotateLeft(int a[], int k) {
        for (int i = 0; i < k; i++) {
            int j = 0;
            int temp = a[j];
            for (; j < a.length - 1; j++) { // leave last element to be assigned with the staged value (temp)
                a[j] = a[j + 1];
            }
            a[j] = temp;
        }
        return a;
    }

    // O(kn) time and O(1) space
    public static int[] rotateRight(int a[], int k) {
        for (int i = 0; i < k; i++) {
            int j = a.length - 1;
            int temp = a[j];
            for (; j > 0; j--) { // leave last element to be assigned with the staged value (temp)
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
        return a;
    }

    // O(n) time and O(n) space
    public static int[] rotateLeftWithSpace(int a[], int k) {
        int n = a.length;
        int[] b = new int[n]; // this is the bottleneck
        k = k%n;
        for(int i=0; i<n; i++) {
            if (i-k < 0){
                b[i-k+n] = a[i];
            } else {
                b[i-k] = a[i];
            }
        }
        return b;
    }
}
