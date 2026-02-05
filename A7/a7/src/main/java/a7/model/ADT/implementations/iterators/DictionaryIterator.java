package a7.model.ADT.implementations.iterators;

import a7.helpers.Pair;
import a7.model.ADT.implementations.Dictionary;
import a7.model.exceptions.InvalidIteratorException;

public class DictionaryIterator<TKey, TValue> extends IteratorBase<Pair<TKey, TValue>> 
{
    private final Dictionary<TKey, TValue> dict;
    private final TKey[] keys;

    public DictionaryIterator(Dictionary<TKey, TValue> dict) 
    { 
        this.dict = dict;
        this.keys = dict.Keys();
    }
    
    @Override
    public boolean Valid() { return index >= 0 && index < keys.length; }

    @Override
    public void First() { index = 0; }

    @Override
    public void Next() { index++; }

    @Override
    public Pair<TKey, TValue> Current()
    {
        if (!Valid()) throw new InvalidIteratorException();
        return new Pair<>(keys[index], dict.Get(keys[index]));
    }
}