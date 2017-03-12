package com.app;

import static org.apache.commons.lang.StringUtils.EMPTY;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Counter {

    private static AtomicLong counter;
    private Storage storage;

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(final Storage storage) {
        this.storage = storage;
    }

    public void initCounter() {
        counter = new AtomicLong(findMaxId());
    }

    private long findMaxId() {
        Set<Long> ids = new HashSet<Long>();
        Set<String> keys = storage.getAllKeys();
        if (keys != null) {
            for (String currentKey : keys) {
                ids.add(Long.parseLong(currentKey.replaceAll("\\D+", EMPTY)));
            }
        }
        return Collections.max(ids);
    }

    public long getUniqueId() {
        if (counter == null) {
            initCounter();
        }
        return counter.incrementAndGet();
    }
}
