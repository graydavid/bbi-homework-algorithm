package com.brotmanbaty.homework.algorithm;

import java.util.Optional;

/**
 * PROBLEM STATEMENT
 * 
 * Problem 3: Algorithm
 * 
 * You are given a sequence of N numbers s[1], s[2], ..., s[N]. A contiguous subsequence is defined as a sequence of
 * numbers s[i], s[i+1], ..., s[j] for any i and j with 1 <= i <= j <= N.
 * 
 * Write a program that takes a sequence of numbers, finds the contiguous subsequence with the largest sum, and outputs
 * i and j. If there are multiple such subsequences, return the i and j for the shortest subsequence which occurs first
 * in the original sequence. Try to use the most efficient algorithm, and describe the time and space complexity of your
 * solution.
 * 
 * ANSWER
 * 
 * See code below that solves the problem. Assumption: "number" in the problem statement means "int". If this is not
 * true, then the program can easily be changed/generalized. The most interesting part of the algorithm is in the
 * ContinguousSubsequenceSums class. It uses dynamic programming. As a result, given a sequence of size N, the algorithm
 * has time and space complexity equal to the size of the table: i.e a time complexity of O(N^2) and space complexity of
 * O(N^2).
 */
public class Main {

    public static void main(String args[]) {
        int sequence[] = {5, 2, -1};
        ContinguousSubsequenceSums sums = ContinguousSubsequenceSums.forSequence(sequence);
        Optional<Range> maxZeroBasedRange = sums.findShortestFirstMaxRange();
        Optional<Range> maxOneBasedRange = maxZeroBasedRange
                .map(zero -> Range.of(zero.getStartIndex() + 1, zero.getStopIndex() + 1));
        System.out.println("<i,j> = " + maxOneBasedRange);
    }
}
