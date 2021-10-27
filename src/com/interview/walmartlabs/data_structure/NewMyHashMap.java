package com.interview.walmartlabs.data_structure;

import com.study.practice.data_structure.MyHashMap;

public class NewMyHashMap {
    private static class Entry {
        final String key;
        Integer value;
        Entry next;

        public Entry(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
    private final static float DEFAULT_LOAD_FACTOR = 0.75f;
    private final static int DEFAULT_CAPACITY = 16; // 2^n
    private float loadFactor;
    private int size;
    Entry[] array;

    public NewMyHashMap(float loadFactor, int initCapacity) {
        this.loadFactor = loadFactor;
        this.array = new Entry[initCapacity];
        this.size = 0;
    }

    public NewMyHashMap() {
        this(DEFAULT_LOAD_FACTOR, DEFAULT_CAPACITY); // use "this"
    }

    public Integer get(String key) {
        int index = getIndex(key);
        Entry head = array[index];
        while (head != null) {
            if (equalsKey(head.key, key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    private boolean equalsKey(String k1, String k2) {
        if (k1 == null && k2 == null) {
            return true;
        } else if (k1 == null || k2 == null) {
            return false;
        } else if (k1.equals(k2)) {
            return true;
        }
        return false;
    }

    private int getIndex(String key) {
        if (key == null) {
            return 0;
        }

        return key.hashCode() & 0x7FFFFFFF % array.length;
    }

    public Integer put(String key, Integer value) {
        int index = getIndex(key);
        Entry head = array[index];
        Entry cur = head;
        while (cur != null) {
            if (equalsKey(cur.key, key)) {
                int res = cur.value;
                cur.value = value;
                return res;
            }
            cur = cur.next;
        }

        Entry newEntry = new Entry(key, value);
        newEntry.next = head;
        array[index] = newEntry;

        size++;
        if (needRehash()) {
            rehash();
        }
        return null;
    }

    private void rehash() {
        Entry[] oldArray = array;
        this.array = new Entry[array.length * 2];
        for (Entry cur : array) {
            while (cur != null) {
                Entry next = cur.next;
                int idx = getIndex(cur.key);
                cur.next = array[idx];
                array[idx] = cur;
                cur = next;
            }
        }
    }

    private boolean needRehash() {
        float ratio = (float) size / array.length;
        if (ratio >= loadFactor) {
            return true;
        }
        return false;
    }


}
