package com.kmsichi.common.util;

import java.util.HashMap;
import java.util.Map;

public final class HashMapUtil {
    public static <K, V> K findKeyByValue(HashMap<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public <T> T getTrue() {
        return null;
    }
}
