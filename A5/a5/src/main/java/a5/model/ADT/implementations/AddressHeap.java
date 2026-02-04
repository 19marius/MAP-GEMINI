package a5.model.ADT.implementations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

import a5.helpers.Pair;
import a5.model.ADT.implementations.iterators.AddressHeapIterator;
import a5.model.ADT.interfaces.IHeap;
import a5.model.ADT.interfaces.IIterable;
import a5.model.ADT.interfaces.IIterator;

public class AddressHeap<T> implements IHeap<Integer, T>
{
    private final HashMap<Integer, T> dict = new HashMap<>();
    private final Stack<Integer> addresses = new Stack<>();
    
    private int keys = 0;

    public AddressHeap() { addresses.push(1); }

    @Override
    public final Integer NextKey() { return addresses.peek(); }

    @Override
    public final Integer Insert(T value) 
    {
        Integer key = addresses.pop();
        if (addresses.size() == 0L) addresses.push(key + 1);

        dict.put(key, value);
        keys++;
        
        return key;
    }

    @Override
    public final void Clear() 
    {
        dict.clear();
        addresses.clear();

        addresses.push(1);
    }

    @Override
    public final boolean Remove(Integer key) 
    {
        if (dict.remove(key) == null) return false;
        
        addresses.push(key);
        keys--;

        return true;
    }

    @Override
    public final boolean Set(Integer key, T value) 
    {
        if (!Exists(key)) return false;

        dict.put(key, value);
        return true;
    }

    @Override
    public final T Get(Integer key) { return dict.get(key); }

    @Override
    public final boolean Exists(Integer key) { return dict.get(key) != null; }

    @Override
    public final int KeyCount() { return keys; }
    
    @Override
    public final Integer[] Keys()
    { 
        Object[] k = dict.keySet().toArray();
        return Arrays.copyOf(k, k.length, Integer[].class);
    }

    @Override
    public final IIterable<Pair<Integer, T>> Clone()
    {
        AddressHeap<T> clone = new AddressHeap<>();

        clone.keys = keys;
        ForEach(x -> clone.dict.put(x.first, x.second));
        for (int x : addresses) clone.addresses.push(x);

        return clone;
    }

    @Override
    public final IIterator<Pair<Integer, T>> GetIterator() { return new AddressHeapIterator<>(this); }
}