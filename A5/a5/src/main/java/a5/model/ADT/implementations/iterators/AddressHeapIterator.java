package a5.model.ADT.implementations.iterators;

import a5.helpers.Pair;
import a5.model.ADT.implementations.AddressHeap;
import a5.model.exceptions.InvalidIteratorException;

public class AddressHeapIterator<T> extends IteratorBase<Pair<Integer, T>> 
{
    private final AddressHeap<T> heap;
    private final Integer[] keys;

    public AddressHeapIterator(AddressHeap<T> heap) 
    { 
        this.heap = heap;
        this.keys = heap.Keys();
    }

    @Override
    public boolean Valid() { return index >= 0 && index < keys.length; }

    @Override
    public void First() { index = 0; }

    @Override
    public void Next() { index++; }

    @Override
    public Pair<Integer, T> Current()
    {
        if (!Valid()) throw new InvalidIteratorException();
        return new Pair<>(keys[index], heap.Get(keys[index]));
    }
}