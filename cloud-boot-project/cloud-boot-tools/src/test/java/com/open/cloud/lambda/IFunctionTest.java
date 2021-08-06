package com.open.cloud.lambda;

@FunctionalInterface
interface IFunctionTest<T> {
    public void print(T x);
}