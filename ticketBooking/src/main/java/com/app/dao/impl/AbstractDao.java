package com.app.dao.impl;

import com.app.Counter;
import com.app.Storage;

public abstract class AbstractDao {

    private Counter counter;
    private Storage storage;

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public long getUniqueId() {
        return counter.getUniqueId();
    }

}
