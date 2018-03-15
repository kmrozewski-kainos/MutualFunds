package com.mutualfunds.investment.util;

@FunctionalInterface
public interface ComparatorSupplier<T> {
    T get();
}
