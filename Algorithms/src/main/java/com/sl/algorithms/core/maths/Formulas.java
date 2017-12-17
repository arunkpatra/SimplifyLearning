package com.sl.algorithms.core.maths;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Formulas {

    Formulas() {
        /**
         * This is a utility class.<br>
         */
    }

    /**
     * Find mid-point between 2 numbers; prevent integer overflow.
     */
    public static int midPoint(int start, int end) {
        int mid = start + (end-start)/2;
        return mid;
    }

    //O(log(n)), based on solution from Euclid & Aryabhatta
    public static int hcf(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        while (a != b) {
            // equals can be in either conditions
            if (a < b) {
                b -= a;
            } else {
                a -= b;
            }
        }
        return a;
    }

    /**
     * <a href="https://en.wikipedia.org/wiki/Narcissistic_number">Armstrong NumberOps</a>
     */
    public static boolean isArmstrongNumber(int n) {
        int sum = 0;
        int power = orderOf(n);
        for (int i=n; i>0; i/=10) {
            sum += raiseTo(i%10, power);
        }
        return (sum == n);
    }

    public static int orderOf(int n) {
        int order = 0; // unknown
        int temp = n;
        while (temp != 0) {
            temp = temp/10;
            ++order;
        }
        return order;
    }

    //TODO: this can be improved further to handle large powers
    public static int raiseTo(int n, int p) {
        if (n == 0) return 0;
        if (p == 0) return 1;
        if (n == 1) return n;
        long result = n;
        int counter = 1;
        while (counter < p) {
            if (result*n >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            result *= n;
            counter++;
        }
        return (int)result;
    }

    /**
     * <a href="http://www.geeksforgeeks.org/neon-number/">Neon NumberOps</a>
     */
    public static boolean isNeonNumber(int number) {
        int sum = 0;
        for (int i=number*number; i>0; i/=10) {
            sum += i%10;
        }
        return (sum == number);
    }

    /**
     * Scope: (1) 32-bit numbers (2) digits and length are same.<br>
     */
    public static boolean haveSameDigitsAndLengthPrimes(int a, int b) {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int hashA=1, hashB=1;
        while (a > 0) {
            hashA *= primes[a%10];
            a /= 10;
        }
        while (b > 0) {
            hashB *= primes[b%10];
            b /= 10;
        }
        return (hashA == hashB);
    }

    public static boolean haveSameDigitsAndLength(int a, int b) {
        if ((a == 0 || b == 0) && a != b) {
            return false;
        }
        int[] digits = new int[10];
        int i=a, j=b;
        for (; i>0 && j>0; i/=10, j/=10) {
            ++digits[i%10];
            --digits[j%10];
        }
        if (i != 0 || j != 0) {
            return false;
        }
        for (int digit : digits) {
            if (digit != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * <a href="https://en.wikipedia.org/wiki/Palindromic_number">Palindromic NumberOps</a>
     * //O(n) time and O(1) space
     */
    public static boolean isPalindrome(int number) {
        if (number < 0) return false;
        if (number == 0) return true;
        if (number%10 == 0) return false;
        int reverse = 0;
        while (number > reverse) {
            reverse = reverse*10 + number%10;
            number /= 10;
        }
        return (number == reverse || number == reverse/10);
    }

/*
    // for reference only
    public static List<Integer> printArmstrongNumbers() throws InterruptedException {
        return printArmstrongNumbers(Integer.MAX_VALUE);
    }
*/

    public static List<Integer> printArmstrongNumbers(int upperBound) throws InterruptedException {
        List<Integer> outputList = new ArrayList<>();
        final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final int batchSize=1000;
        List<Future<Integer>> futuresList = new ArrayList<>();
        long index=1;
        while (index < upperBound) {
            final long start = index;
            final long end = (index+batchSize)<=upperBound ? index+batchSize: upperBound;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (long i=start; i<end; i++) {
                        int numberToInspect = (int)i;
                        if (isArmstrongNumber(numberToInspect)) {
                            outputList.add(numberToInspect);
                        }
                    }
                }
            });
            index = end;
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException ie) {
            throw ie;
        }
        return outputList;
    }

    // O(n^1/2)
    public static boolean isPrimeNumber(long n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n%2 == 0 || n%3 == 0) {
            //System.out.println(n + " is divisible by 2 or 3");
            return false;
        }
        for (long i=5; i*i <= n; i+=4) {
            if (n%i == 0 || n%(i+2) == 0) {
                //System.out.println(n + " is divisible by " + i + " or " + (i + 2));
                return false;
            }
        }
        return true;
    }
}
