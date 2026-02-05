package a7.model.ADT.implementations.iterators;

import a7.model.ADT.implementations.List;
import a7.model.exceptions.InvalidIteratorException;

public class ListIterator<T> extends IteratorBase<T>
{
    private final List<T> list;

    public ListIterator(List<T> list) { this.list = list; }

    @Override
    public boolean Valid() { return index >= 0 && index < list.Length(); }

    @Override
    public void First() { index = 0; }

    @Override
    public void Next() { index++; }

    @Override
    public T Current()
    {
        if (!Valid()) throw new InvalidIteratorException(); 
        return list.Get(index);
    }
}