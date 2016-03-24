package com.Map;

import java.util.Map;
import java.util.Objects;


/**
 * Created by baylrock on 21.02.2016.
 */
public class Bank<K, V> {

    public K getKey() {
        return key;
    }

    public void setKey( K key ) {
        this.key = key;
    }

    public K key = (K) new Object();

    public V getVal() {
        return val;
    }

    public void setVal( V val ) {
        this.val = val;
    }

    private V val;

    private Bank<?, ?> next = null;

    public Bank getNext() {
        if ( next == null ) return null;
        return next;
    }

    public void setNext( Bank<K, V> next ) {
        this.next = next;
    }

    public Bank( K k, V v ) {
        key = k;
        val = v;
    }

    @Override
    public final boolean equals( Object o ) {
        if ( o == this )
            return true;
        if ( o instanceof Map.Entry ) {
            Bank<?, ?> e = (Bank<?, ?>) o;
            if ( Objects.equals( key, e.getKey() ) &&
                    Objects.equals( val, e.getVal() ) )
                return true;
        }
        return false;
    }

    @Override
    public final String toString() {
        return key + " = " + val;
    }

    @Override
    public final int hashCode() {
        return super.hashCode();
    }
}

