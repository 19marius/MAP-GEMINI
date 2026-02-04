package a5.model.ADT.interfaces;

import a5.helpers.Pair;

public interface IHeap<TKey, TValue> extends IIterable<Pair<TKey, TValue>>
{
    public TKey Insert(TValue value);
    
    public boolean Set(TKey key, TValue value);

    public TKey NextKey();

    public TValue Get(TKey key);

    public boolean Exists(TKey key);

    public boolean Remove(TKey key);

    public void Clear();

    public int KeyCount();

    public TKey[] Keys();
}