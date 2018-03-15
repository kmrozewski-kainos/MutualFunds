package com.mutualfunds.investment.util;

@FunctionalInterface
public interface ComparatorFunction<T, U> {
    ComparatorSupplier<T> apply(U u);
}
