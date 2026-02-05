package a7.model.ADT.implementations.iterators;

import java.util.LinkedList;

import a7.model.exceptions.InvalidIteratorException;

public class QueryIterator<T> extends IteratorBase<T>
{
    IteratorBase<?> original;
    LinkedList<QueryAction<Object, Object>> queue;

    QueryIterator(IteratorBase<?> original, LinkedList<QueryAction<Object, Object>> queue) 
    {
        this.original = original; 
        this.queue = queue;
    }

    @Override
    public boolean Valid() { return false; }

    @Override
    public void First() { throw new InvalidIteratorException(); }

    @Override
    public void Next() { throw new InvalidIteratorException(); }

    @Override
    public T Current() { throw new InvalidIteratorException(); }
}