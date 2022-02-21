package com.brotmanbaty.homework.algorithm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class ContinguousSubsequenceSumsTest {

    @Test
    public void findMaxRangesReturnsEmptyResponseForEmptySequence() {
        ContinguousSubsequenceSums sums = ContinguousSubsequenceSums.forSequence(new int[] {});

        assertThat(sums.findMaxRanges(), empty());;
    }

    @Test
    public void findMaxRangesReturnsSingleEntryForSingleEntrySequence() {
        ContinguousSubsequenceSums sums = ContinguousSubsequenceSums.forSequence(new int[] {-3});

        assertThat(sums.findMaxRanges(), containsInAnyOrder(Range.of(0, 0)));
    }

    @Test
    public void findMaxRangesReturnsWholeRangeForSequenceOfOnlyPositiveNumbers() {
        ContinguousSubsequenceSums sums = ContinguousSubsequenceSums.forSequence(new int[] {1, 2, 3});

        assertThat(sums.findMaxRanges(), containsInAnyOrder(Range.of(0, 2)));
    }

    @Test
    public void findMaxRangesReturnsSingleLargestEntryForSequenceOfOnlyNegativeNumbers() {
        ContinguousSubsequenceSums sums = ContinguousSubsequenceSums.forSequence(new int[] {-1, -2, -3});

        assertThat(sums.findMaxRanges(), containsInAnyOrder(Range.of(0, 0)));
    }

    @Test
    public void findMaxRangesIncludesNegativeNumbersInMiddleOfSequenceWhenSubtractLessThanPartsOnEitherSide() {
        ContinguousSubsequenceSums sums = ContinguousSubsequenceSums.forSequence(new int[] {3, -2, 3});

        assertThat(sums.findMaxRanges(), containsInAnyOrder(Range.of(0, 2)));
    }

    @Test
    public void findMaxRangesDoesntIncludesNegativeNumbersInMiddleOfSequenceWhenSubtractMoreThanPartsOnEitherSide() {
        ContinguousSubsequenceSums sums = ContinguousSubsequenceSums.forSequence(new int[] {1, -2, 3});

        assertThat(sums.findMaxRanges(), containsInAnyOrder(Range.of(2, 2)));
    }

    @Test
    public void findMaxRangesCanIncludeMultipleRangesForTheSameSum() {
        ContinguousSubsequenceSums sums = ContinguousSubsequenceSums.forSequence(new int[] {2, -2, 3});

        assertThat(sums.findMaxRanges(), containsInAnyOrder(Range.of(0, 2), Range.of(2, 2)));
    }

    @Test
    public void findMaxRangesCanIncludeAllRangesForTheSameSum() {
        ContinguousSubsequenceSums sums = ContinguousSubsequenceSums.forSequence(new int[] {0, 0, 0});

        assertThat(sums.findMaxRanges(), containsInAnyOrder(Range.of(0, 0), Range.of(0, 1), Range.of(0, 2),
                Range.of(1, 1), Range.of(1, 2), Range.of(2, 2)));
    }

    @Test
    public void findShortestFirstMaxRangeReturnsAnEmptyResponseForEmptySequences() {
        ContinguousSubsequenceSums sums = ContinguousSubsequenceSums.forSequence(new int[] {});

        assertThat(sums.findShortestFirstMaxRange(), is(Optional.empty()));
    }

    @Test
    public void findShortestFirstMaxRangePrioritizesShorterRangesOverLargerOnesThatAppearFirst() {
        ContinguousSubsequenceSums sums = ContinguousSubsequenceSums.forSequence(new int[] {2, -2, 3});

        assertThat(sums.findShortestFirstMaxRange().get(), is(Range.of(2, 2)));
    }

    @Test
    public void findShortestFirstMaxRangePrioritizesFirstRangesWhenThereAreMultipleOfSameLength() {
        ContinguousSubsequenceSums sums = ContinguousSubsequenceSums.forSequence(new int[] {0, 0, 0});

        assertThat(sums.findShortestFirstMaxRange().get(), is(Range.of(0, 0)));
    }
}
