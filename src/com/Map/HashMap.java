package com.Map;

import java.util.Arrays;

/**
 * Created by baylrock on 21.02.2016.
 */
public class HashMap<K, V> implements IMap<K, V> {

    private static final int CONS_BANK_SIZE = 16;
    private double LOAD_FACTOR = 0.75;
    private transient Bank<K, V>[] bank;
    private int bank_size = 0;
    private int loaded = 0;

    public HashMap() {
        bank = new Bank[CONS_BANK_SIZE];
        bank_size = CONS_BANK_SIZE;
    }

    public HashMap(int size) {
        bank = (size < CONS_BANK_SIZE)
                ? new Bank[CONS_BANK_SIZE]
                : new Bank[size];
        bank_size = bank.length;
    }

    public HashMap(K[] k, V[] v) {
        initiate(k, v);
    }

    @Override
    public boolean put(K k, V v) {

        checkLoad();
        int hash = hash(k);
        int index = hash % bank_size;
        Bank<K, V> newDat = new Bank<>(k, v);
        Bank<K, V> iteration_element = bank[index];

        if (iteration_element == null) {

            try {
                bank[index] = new Bank<>(k, v);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Shit happens with k=" + k.toString() + " and v=" + v.toString());
            }
            loaded++;
            return true;
        }
        Bank<K, V> prevInList = null;
        do {
            if (newDat.equals(iteration_element)) {
                newDat.setNext(iteration_element.getNext());
                if (prevInList != null) {
                    prevInList.setNext(newDat);
                    loaded++;
                } else {
                    bank[index] = newDat;
                }
                return true;
            }
            prevInList = iteration_element;
                iteration_element = iteration_element.getNext();
        } while (iteration_element != null);
        return true;
    }

    @Override
    public V get(K k) {
        int hash = k.hashCode();
        int index = hash % bank_size;
        Bank<K, V> bank_elem = bank[index];
        if (bank_elem == null) return null;
        do {
            if (k.equals(bank_elem.getKey())) {
                return bank_elem.getVal();
            }
            bank_elem = bank_elem.getNext();
        } while (bank_elem != null);
        return null;
    }

    @Override
    public K[] getKeys() {
        return null;
    }

    @Override
    public V[] getValues() {
        return null;
    }

    @Override
    public boolean remove(K k) {
        int hash = k.hashCode();
        int index = hash % bank_size;
        Bank element = bank[index];
        if (element != null) {
            Bank prevInList = null;
            do {
                if (element.getKey().equals(k)) {
                    if (prevInList == null) {
                        bank[index] = element.getNext();
                        element = null;
                        loaded--;
                        return true;
                    }
                    prevInList.setNext(element.getNext());
                    element = null;
                    loaded--;
                    return true;
                }
                prevInList = element;
                element = element.getNext();
            } while (element != null);
        }
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(K v) {
        return false;
    }

    @Override
    public boolean containsValue(V v) {
        return false;
    }

    @Override
    public void initiate(K[] k, V[] v) {

    }

    private void checkLoad() {
        if (LOAD_FACTOR >= 1) LOAD_FACTOR = 0.75;
        if (loaded == bank_size * LOAD_FACTOR) {
            bank = Arrays.copyOf(bank, bank_size + bank_size / 3);
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private boolean isEquals(Object ob1, Object ob2) {
        return ob1 == ob2 || (ob1 != null && ob2 != null && ob1.equals(ob2));
    }

}
