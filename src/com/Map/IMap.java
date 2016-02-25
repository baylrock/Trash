package com.Map;

import java.util.Iterator;

/**
 * Created by baylrock on 21.02.2016.
 */
public interface IMap<K,V> {


    boolean put(K k, V v);
    V get(K k);
    K[] getKeys();
    V[] getValues();
    boolean remove(K k);
    void clear();
    boolean containsKey(K v);
    boolean containsValue(V v);
    void initiate(K[] k,V[] v);
}
