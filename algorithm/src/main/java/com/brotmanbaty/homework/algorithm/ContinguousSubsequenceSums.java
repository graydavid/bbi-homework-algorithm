package com.brotmanbaty.homework.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/** Holds the values of all sums of all contiguous subsequences in a sequence. */
public class ContinguousSubsequenceSums {

    /**
     * The contiguous subsequence sums of a sequence. If the sequence has length L, then `sums` is a two dimensional
     * array of size L x L. A length of 0 is possible for empty sequences, in which case `sums` is also empty. The value
     * of `sums[i][j]` is the sum for the subsequence from index i to index j in the original sequence. Entries with i >
     * j are invalid/unspecified in `sums`.
     */
    private final int[][] sums;

    private ContinguousSubsequenceSums(int[][] sums) {
        this.sums = sums;
    }

    public static ContinguousSubsequenceSums forSequence(int[] sequence) {
        int sums[][] = computeSumsArrayForSequence(sequence);
        return new ContinguousSubsequenceSums(sums);
    }

    private static int[][] computeSumsArrayForSequence(int[] sequence) {
        if (sequence.length == 0) {
            return new int[0][0];
        }

        // Construct the sums (table/array)) according to recurrence relations
        int[][] sums = new int[sequence.length][sequence.length];
        for (int i = 0; i < sequence.length; ++i) {
            for (int j = i; j < sequence.length; ++j) {
                if (j == 0) {
                    // The first sum is just the first element of the sequence
                    sums[0][0] = sequence[0];
                } else if (i == j) {
                    // Values in a new row of sums are the same as the value in the previous row minus the
                    // corresponding value in the sequence (since we're increasing i, meaning less values in the sum)
                    sums[i][j] = sums[i - 1][j] - sequence[i - 1];
                } else {
                    // Values in a new column of sums are the same as the value in the previous column plus the
                    // new value of the sequence (since we're increasing j, meaning more values in the sum)
                    sums[i][j] = sums[i][j - 1] + sequence[j];
                }
            }
        }
        return sums;
    }

    /**
     * Returns all ranges in the original subsequence that produce the maximum subsequence sum. If the sequence was
     * empty, then an empty Collection is returned.
     */
    public Collection<Range> findMaxRanges() {
        if (sums.length == 0) {
            return List.of();
        }

        // Iterate through all valid sums to find the maximum(s)
        List<Range> maxRanges = new ArrayList<>();
        int currentMaxSum = 0;
        for (int i = 0; i < sums.length; ++i) {
            for (int j = i; j < sums.length; ++j) {
                if (j == 0) {
                    // Always initialize the maximum to the first sum
                    currentMaxSum = sums[0][0];
                    maxRanges.add(Range.of(0, 0));
                } else if (sums[i][j] == currentMaxSum) {
                    // If we find a sum equivalent to the current recorded sum, include it in the current response
                    maxRanges.add(Range.of(i, j));
                } else if (sums[i][j] > currentMaxSum) {
                    // If we find a new maximum, record it and create a new response for it.
                    currentMaxSum = sums[i][j];
                    maxRanges.clear();
                    maxRanges.add(Range.of(i, j));
                }
            }
        }
        return maxRanges;
    }

    /**
     * Same as {@link #findMaxRanges()}, except when that method returns multiple entries, this method will return the
     * shortest Range that occurs first. This method may return an optional empty if the original sequence was empty.
     */
    public Optional<Range> findShortestFirstMaxRange() {
        return findMaxRanges().stream().min(Comparator.comparing(Range::getLength).thenComparing(Range::getStartIndex));
    }
}
