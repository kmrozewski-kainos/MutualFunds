package com.mutualfunds.util;

@FunctionalInterface
public interface ComparatorSupplier<T> {
    T get();
}
