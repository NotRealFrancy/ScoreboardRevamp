package org.github.notrealfrancy.scoreboardrevamp.manager;

import com.google.common.collect.Lists;

import java.util.List;

public class Manager<T> {

    private final List<T> array;

    public void add(T obj) {
        this.array.add(obj);
    }

    public void remove(T obj) {
        this.array.remove(obj);
    }

    public T get(int index) {
        return this.array.get(index);
    }

    public Manager() {
        this.array = Lists.newArrayList();
    }
}
