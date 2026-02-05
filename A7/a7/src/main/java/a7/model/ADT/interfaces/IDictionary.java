package a7.model.ADT.interfaces;

import a7.helpers.Pair;

public interface IDictionary<TKey, TValue> extends IIterable<Pair<TKey, TValue>>
{
    public void Set(TKey key, TValue value);

    public boolean Remove(TKey key);

    public boolean Exists(TKey key);

    public TValue Get(TKey key);

    public void Clear();

    public int KeyCount();

    public TKey[] Keys();
}