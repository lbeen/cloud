package com.mes.kanban.utils;

@FunctionalInterface
public interface Callable<V> {
    V call();
}
