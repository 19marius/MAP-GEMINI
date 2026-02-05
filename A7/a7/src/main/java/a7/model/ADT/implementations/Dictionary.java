package a7.model.ADT.implementations;

import java.util.HashMap;

import a7.helpers.Pair;
import a7.model.ADT.implementations.iterators.DictionaryIterator;
import a7.model.ADT.interfaces.IDictionary;
import a7.model.ADT.interfaces.IIterable;
import a7.model.ADT.interfaces.IIterator;

public class Dictionary<TKey, TValue> implements IDictionary<TKey, TValue>
{
    private final HashMap<TKey, TValue> dict = new HashMap<>();
    private int keys = 0;

    @Override
    public final void Set(TKey key, TValue value) 
    {
        if (!Exists(key)) keys++; 
        dict.put(key, value);
    }

    @Override
    public final void Clear() { dict.clear(); }

    @Override
    public final boolean Remove(TKey key) 
    { 
        boolean removed = dict.remove(key) != null; 
        if (removed) keys--;

        return removed;
    }

    @Override
    public final TValue Get(TKey key) { return dict.get(key); }

    @Override
    public final boolean Exists(TKey key) { return dict.get(key) != null; }

    @Override
    public final int KeyCount() { return keys; }

    @Override
    @SuppressWarnings("unchecked")
    public final TKey[] Keys() { return (TKey[]) dict.keySet().toArray(); }

    @Override
    public final IIterable<Pair<TKey, TValue>> Clone()
    {
        Dictionary<TKey, TValue> clone = new Dictionary<>();
        GetIterator().ForEach((x, i) -> clone.Set(x.first, x.second));

        return clone;
    }

    @Override
    public final IIterator<Pair<TKey, TValue>> GetIterator() { return new DictionaryIterator<>(this); }
}