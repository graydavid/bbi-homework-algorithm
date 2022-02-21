package com.brotmanbaty.homework.algorithm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RangeTest {

    @Test
    public void ofThrowsExceptionWhenStartIndexNegative() {
        assertThrows(IllegalArgumentException.class, () -> Range.of(-1, 0));
    }

    @Test
    public void ofThrowsExceptionWhenStopBeforeStart() {
        assertThrows(IllegalArgumentException.class, () -> Range.of(1, 0));
    }

    @Test
    public void ofAllowsSameStartAndStop() {
        Range.of(0, 0);
        Range.of(5, 5);
    }

    @Test
    public void getLengthIncludesStartAndStopIndicesInLength() {
        assertThat(Range.of(2, 2).getLength(), is(1));
        assertThat(Range.of(2, 3).getLength(), is(2));
    }

    @Test
    public void toStringIncludesStartAndStopIndices() {
        assertThat(Range.of(2, 3).toString(), is("<2,3>"));
    }

    @Test
    public void hashCodeObeysContract() {
        Range range1 = Range.of(1, 5);
        Range range2 = Range.of(1, 5);

        // Same object produces same hash code on multiple calls
        assertThat(range1.hashCode(), is(range1.hashCode()));
        // Equal objects have same hash code
        assertThat(range1.hashCode(), is(range2.hashCode()));
    }

    @Test
    public void equalsObeysContract() {
        Range range1 = Range.of(1, 5);
        Range range2 = Range.of(1, 5);
        Range differentStartRange = Range.of(2, 5);
        Range differentStopRange = Range.of(1, 6);

        // Reflexive
        assertThat(range1, is(range1));
        // Symmetric
        assertThat(range1, not(differentStartRange));
        assertThat(differentStartRange, not(range1));
        assertThat(range1, not(differentStopRange));
        assertThat(differentStopRange, not(range1));
        assertThat(range1, equalTo(range2));
        assertThat(range2, equalTo(range1));
    }
}
