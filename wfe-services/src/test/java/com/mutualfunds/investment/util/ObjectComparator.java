package com.mutualfunds.investment.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.stream.IntStream;

import lombok.val;

public final class ObjectComparator {

    private ObjectComparator() {
    }

    public static <T, U> void compare(U actual, U expected, ComparatorFunction<T, U> getValue) {
        assertThat(get(actual, getValue), equalTo(get(expected, getValue)));
    }

    public static <T, U, R> void compareList(U actual, U expected, ComparatorFunction<List<R>, U> getList, ComparatorFunction<T, R> getValue) {
        val actualList = get(actual, getList);
        val expectedList = get(expected, getList);

        assertThat(actualList.size(), equalTo(expectedList.size()));

        IntStream
            .range(0, actualList.size())
            .forEach(i -> compare(actualList.get(i), expectedList.get(i), getValue));
    }

    private static <T, U> T get(U object, ComparatorFunction<T, U> getValue) {
        return getValue.apply(object).get();
    }
}
