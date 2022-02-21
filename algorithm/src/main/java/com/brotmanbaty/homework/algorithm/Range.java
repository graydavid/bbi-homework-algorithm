package com.brotmanbaty.homework.algorithm;

import java.util.Objects;

/**
 * The 0-based index range of a sequence of numbers from [startIndex, stopIndex] inclusive. E.g. given the sequence (1,
 * 6, 9, 4), the range [1, 2] would represent the indices of the subsequence (6, 9).
 */
public class Range {
    private final int startIndex;
    private final int stopIndex;

    private Range(int startIndex, int stopIndex) {
        this.startIndex = requireValidIndex(startIndex);
        this.stopIndex = requireValidIndex(stopIndex);
        requireValidStartStopIndices(startIndex, stopIndex);
    }

    private static int requireValidIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Ranges must have positive indices, but found: " + index);
        }

        return index;
    }

    private static void requireValidStartStopIndices(int startIndex, int stopIndex) {
        if (stopIndex < startIndex) {
            String message = String.format("startIndex must not be greater than stop index, but found: <%s, %s>",
                    startIndex, stopIndex);
            throw new IllegalArgumentException(message);
        }
    }

    public static Range of(int startIndex, int stopIndex) {
        return new Range(startIndex, stopIndex);
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getStopIndex() {
        return stopIndex;
    }

    /** Returns the length of the range: the number of numbers in the sequence. */
    public int getLength() {
        return stopIndex - startIndex + 1;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Range) {
            Range other = (Range) object;
            return Objects.equals(this.startIndex, other.startIndex) && Objects.equals(this.stopIndex, other.stopIndex);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startIndex, stopIndex);
    }

    @Override
    public String toString() {
        return "<" + startIndex + "," + stopIndex + ">";
    }
}
